package com.bharosa.api;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bharosa.model.Campaign;
import com.bharosa.model.CampaignImage;
import com.bharosa.model.CampaignSupporters;
import com.bharosa.repository.CampaignImageRepository;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.CampaignSupportersRepository;
import com.bharosa.repository.PaymentRequestRepository;
import com.bharosa.repository.PaymentResponseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/common")
public class OpenApiController {

	
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private CampaignImageRepository campaignImageRepository;

	@Autowired
	private PaymentRequestRepository paymentRequestRepository;
	
	@Autowired
	PaymentResponseRepository paymentResponseRepository;

	@Autowired
	CampaignSupportersRepository campaignSupportersRepository;

//	@RequestMapping(value = "payment", method = RequestMethod.GET, produces = "application/json")
//	public String greeting() {
//
//		PaytmUtil paytmUtil = new PaytmUtil();
//		return paytmUtil.generatePayLoad();
//	}
	
//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@ApiOperation(value = "get all the campaigns", notes = "return campaigns")
	@CrossOrigin
	@RequestMapping(value = "/campaigns", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Campaign>> getCampaigns() {
		return new ResponseEntity<>(campaignRepository.findAll(), HttpStatus.OK);
	}
	
	
	
//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@ApiOperation(value = "provide selected campaign", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET)
	public ResponseEntity<Campaign> getCampaign(@PathVariable long id) {
		Campaign campaign = campaignRepository.findOne(id);
		
		if (campaign != null) {
			campaign.setPaymentRequests(paymentRequestRepository.findByCampaign(campaign));
			campaign.setPaymentResponses(paymentResponseRepository.findByCampaign(campaign));
			campaign.setCampaignSupporters(campaignSupportersRepository.findByCampaign(campaign));
			return new ResponseEntity<>(campaign, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	@ApiOperation(value = "provide selected recent campaign", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/recent", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaigns() {
		List<Campaign> campaign = campaignRepository.findTop2ByOrderByIdDesc();
			return new ResponseEntity<>(campaign, HttpStatus.OK);
	}

	@ApiOperation(value = "provide campaign with highest goals", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/highgoal", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByGoals() {
		List<Campaign> campaign = campaignRepository.findTop2ByOrderByGoalDesc();
			return new ResponseEntity<>(campaign, HttpStatus.OK);
	}

	@ApiOperation(value = "provide campaign with most likes", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/likes", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByLikes() {
		
		List<Campaign> campaigns = new ArrayList<Campaign>(); 

		
		List<Object> campaignList = campaignRepository.findByMostLikedCampaign();
		
		for(int i=0;i<campaignList.size();i++)
		{
			Object[] campaignIdArray =(Object[])campaignList.get(i); 
			BigInteger campaignId = (BigInteger)campaignIdArray[0];
			campaigns.add(campaignRepository.findOne(campaignId.longValue()));
		}	

			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	@ApiOperation(value = "provide campaign with popularity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/popular", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByPopularity() {
		
		
		List<Campaign> campaigns = new ArrayList<Campaign>(); 
		List<Object> campaignList = campaignRepository.findByCampaignByPopularity();
		
		for(int i=0;i<campaignList.size();i++)
		{
			Object[] campaignIdArray =(Object[])campaignList.get(i); 
			BigInteger campaignId = (BigInteger)campaignIdArray[0];
			campaigns.add(campaignRepository.findOne(campaignId.longValue()));
		}	
		
		
		System.out.println("Campaign " + campaigns);
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	@ApiOperation(value = "provide campaign with popularity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/pledges", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByPledges() {
		List<Object> campaign = campaignRepository.findByMostPaymentRequest();
			return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "provide campaign Likes for given campaignwith popularity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaigncomments/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<CampaignSupporters>> getCampaignsComments(@PathVariable long id) {
		List<CampaignSupporters>  campaignSupporters = campaignSupportersRepository.findByCampaignId(id);
			return new ResponseEntity<>(campaignSupporters, HttpStatus.OK);
	}
	
		

	
}
