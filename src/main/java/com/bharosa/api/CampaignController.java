package com.bharosa.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bharosa.model.Campaign;
import com.bharosa.model.CampaignImage;
import com.bharosa.model.PaymentResponse;
import com.bharosa.paytm.PaytmUtil;
import com.bharosa.repository.CampaignImageRepository;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.PaymentRequestRepository;
import com.bharosa.repository.PaymentResponseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class CampaignController {

	
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private CampaignImageRepository campaignImageRepository;

	@Autowired
	private PaymentRequestRepository paymentRequestRepository;
	
	@Autowired
	PaymentResponseRepository paymentResponseRepository;
	
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
			return new ResponseEntity<>(campaign, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	

	@ApiOperation(value = "create campaign", notes = "return success")
	@CrossOrigin
	@RequestMapping(value = "/campaign", method = RequestMethod.POST)
	public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
		System.out.println("campaign is " + campaign);
		Campaign savedCampaign = campaignRepository.save(campaign);
		return new ResponseEntity<>(savedCampaign, HttpStatus.OK);	
	}

	@ApiOperation(value = "create campaign multipart", notes = "return success")
	@CrossOrigin
	@RequestMapping(value = "/campaign/multipart", method = RequestMethod.POST,
	        headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public ResponseEntity<Campaign> createCampaignMultipart(
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        @RequestParam(value = "campaign", required = true) String campaignString) throws SerialException, SQLException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Campaign campaign = mapper.readValue(campaignString, Campaign.class);
		
//	  LOG.info("POST_v1_scouting_activities: headers.getContentType(): {}", headers.getContentType());
//	  LOG.info("POST_v1_scouting_activities: userId: {}", userId);
//	  LOG.info("POST_v1_scouting_activities: image.originalFilename: {}, image: {}",
//	          (image!=null) ? image.getOriginalFilename() : null, image);
//	  LOG.info("POST_v1_scouting_activities: scouting_activity_json.getType().getName(): {}, scouting_activity: {}",
		
		Campaign savedCampaign = campaignRepository.save(campaign);
		if (image != null) {
			CampaignImage campaignImage = new CampaignImage(); 
			campaignImage.setCampaign(savedCampaign);
			campaignImage.setContentType(image.getContentType());
			campaignImage.setName(image.getName());
			campaignImage.setOriginalFilename(image.getOriginalFilename());
			campaignImage.setImageData(new SerialBlob(image.getBytes()));
			campaignImageRepository.save(campaignImage);
			
		}
		return new ResponseEntity<>(savedCampaign, HttpStatus.OK);	
	}

	
	
}
