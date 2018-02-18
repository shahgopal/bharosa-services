package com.bharosa.api;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.dto.CampaignSupporters;
import com.bharosa.dto.DataConverter;
import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignSupportersData;
import com.bharosa.model.PaymentRequestData;
import com.bharosa.model.PaymentResponseData;
import com.bharosa.dto.Campaign;
import com.bharosa.repository.CampaignDataRepository;
import com.bharosa.repository.CampaignImageDataRepository;
import com.bharosa.repository.CampaignSupportersDataRepository;
import com.bharosa.repository.PaymentRequestDataRepository;
import com.bharosa.repository.PaymentResponseDataRepository;
import com.bharosa.repository.UserRepository;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/common")
public class OpenApiController {

	
	
	@Autowired
	private CampaignDataRepository campaignRepository;
	
	@Autowired
	private CampaignImageDataRepository campaignImageRepository;

	@Autowired
	private PaymentRequestDataRepository paymentRequestRepository;
	
	@Autowired
	PaymentResponseDataRepository paymentResponseRepository;

	@Autowired
	CampaignSupportersDataRepository campaignSupportersRepository;

	@Autowired
	UserRepository userRepository;

	
//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
//	@ApiOperation(value = "get all the campaigns", notes = "return campaigns")
//	@CrossOrigin
//	@RequestMapping(value = "/campaigns", method = RequestMethod.GET)
//	public ResponseEntity<Iterable<CampaignData>> getCampaigns() {
//		return new ResponseEntity<>(campaignRepository.findAll(), HttpStatus.OK);
//	}
	
	@CrossOrigin
	@ApiOperation(value = "get all the campaigns", notes = "return campaigns")
	@RequestMapping(value = "/campaigns", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Campaign>> getCampaignsEntity() {
		Iterable<CampaignData> campaignsData=	campaignRepository.findAll();
		List<Campaign> campaignsList = new ArrayList<Campaign>();
		Iterable<Campaign>  campaigns=null;
		for(CampaignData campaignData: campaignsData){
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignDataId(campaignData.getId()));
			campaignsList.add(DataConverter.convertCampaign(campaignData, new Campaign()));
			campaigns=campaignsList;
		}			
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	
	
//	@ApiOperation(value = "provide selected campaign", notes = "return campaign")
//	@CrossOrigin
//	@RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Campaign> getCampaign(@PathVariable long id) {
//		Campaign campaign = campaignRepository.findOne(id);
//		if (campaign != null) {
//			campaign.setPaymentRequests(paymentRequestRepository.findByCampaign(campaign));
//			campaign.setPaymentResponses(paymentResponseRepository.findByCampaign(campaign));
//			campaign.setCampaignSupporters(campaignSupportersRepository.findByCampaign(campaign));
//			return new ResponseEntity<>(campaign, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}

	@ApiOperation(value = "provide selected campaign", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET)
	public ResponseEntity<Campaign> getCampaign(@PathVariable long id) {
		CampaignData campaignData = campaignRepository.findOne(id);
		int sum =0;
		if (campaignData != null) {
			List<PaymentResponseData> paymentResponseList = paymentResponseRepository.findByCampaignData(campaignData);
			List<PaymentRequestData> paymentRequestList = paymentRequestRepository.findByCampaignData(campaignData);
			List<CampaignSupportersData> campaignSupportersList = campaignSupportersRepository.findByCampaignData(campaignData);
	        if (!CollectionUtils.isEmpty(paymentResponseList)) {
	        	campaignData.setPaymentResponsesData(paymentResponseList);
	        	sum = paymentResponseList.stream().filter(o -> o.getTxnAmount().intValueExact()>0).mapToInt(o -> o.getTxnAmount().intValueExact()).sum();
	        }
	        if (!CollectionUtils.isEmpty(paymentRequestList)) {
	        	campaignData.setPaymentRequestsData(paymentRequestList);
	        }
	        if (!CollectionUtils.isEmpty(campaignSupportersList)) {
	        	campaignData.setCampaignSupportersData(campaignSupportersList);
	        	
	        }
			Campaign campaign = new Campaign();
			DataConverter.convertCampaign(campaignData, campaign);
			campaign.setAchievedGoal(sum);
			
			return new ResponseEntity<>(campaign, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "provide selected recent campaign", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/recent", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaigns() {
		List<CampaignData> campaignsData = campaignRepository.findTop2ByOrderByIdDesc();
		List<Campaign> campaignsList = new ArrayList<Campaign>();
		for(CampaignData campaignData: campaignsData){
			Campaign campaign = new Campaign(); 
			DataConverter.convertCampaign(campaignData, campaign);
			campaignsList.add(campaign);
		}			
			return new ResponseEntity<>(campaignsList, HttpStatus.OK);
	}

	@ApiOperation(value = "provide campaign with highest goals", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/highgoal", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByGoals() {
		List<CampaignData> campaignsData = campaignRepository.findTop2ByOrderByGoalDesc();
		List<Campaign> campaignsList = new ArrayList<Campaign>();
		for(CampaignData campaignData: campaignsData){
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignDataId(campaignData.getId()));
			campaignsList.add(DataConverter.convertCampaign(campaignData, new Campaign()));
		}			
		return new ResponseEntity<>(campaignsList, HttpStatus.OK);
	}

	
	
	@ApiOperation(value = "provide campaign with most likes", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/likes", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByLikes() {
		
		List<Campaign> campaigns = new ArrayList<Campaign>(); 
		List<Object> campaignList = campaignRepository.findByMostLikedCampaignData();
		
		for(int i=0;i<campaignList.size();i++)
		{
			Object[] campaignIdArray =(Object[])campaignList.get(i); 
			BigInteger campaignId = (BigInteger)campaignIdArray[0];
			CampaignData campaignData = campaignRepository.findOne(campaignId.longValue());
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignDataId(campaignData.getId()));
			campaigns.add(DataConverter.convertCampaign(campaignData, new Campaign()));
		}	
		System.out.println("Campaign " + campaigns);
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	@ApiOperation(value = "provide campaign with popularity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/popular", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByPopularity() {
		List<Campaign> campaigns = new ArrayList<Campaign>(); 
		List<Object> campaignList = campaignRepository.findByCampaignDataByPopularity();
		
		for(int i=0;i<campaignList.size();i++)
		{
			Object[] campaignIdArray =(Object[])campaignList.get(i); 
			BigInteger campaignId = (BigInteger)campaignIdArray[0];
			CampaignData campaignData = campaignRepository.findOne(campaignId.longValue());
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignDataId(campaignData.getId()));
			campaigns.add(DataConverter.convertCampaign(campaignData, new Campaign()));
		}	
		System.out.println("Campaign " + campaigns);
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	@ApiOperation(value = "provide campaign with popularity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/pledges", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByPledges() {
		List<Object> campaignsData = campaignRepository.findByMostPaymentRequestData();
		List<Campaign> campaigns = new ArrayList<Campaign>(); 
		
		for(Object objcampaignData: campaignsData){
			CampaignData campaignData = (CampaignData)objcampaignData;
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignDataId(campaignData.getId()));
			campaigns.add(DataConverter.convertCampaign(campaignData, new Campaign()));
			}			
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	
	@ApiOperation(value = "provide campaign Likes for given campaignwith popularity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaigncomments/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<CampaignSupporters>> getCampaignsComments(@PathVariable long id) {
		List<CampaignSupportersData>  campaignSupportersData = campaignSupportersRepository.findByCampaignDataId(id);
		List<CampaignSupporters> campaignSupporters = new ArrayList<CampaignSupporters>(); 
		
		for(CampaignSupportersData campaignSupporterData: campaignSupportersData){
			campaignSupporters.add(DataConverter.convertCampaignSupporters(campaignSupporterData, new CampaignSupporters()));
		}			
			return new ResponseEntity<>(campaignSupporters, HttpStatus.OK);
	}
	
		

	
}
