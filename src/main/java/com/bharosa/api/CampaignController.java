package com.bharosa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.Campaign;
import com.bharosa.model.PaymentResponse;
import com.bharosa.paytm.PaytmUtil;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.PaymentRequestRepository;
import com.bharosa.repository.PaymentResponseRepository;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class CampaignController {

	
	
	@Autowired
	private CampaignRepository campaignRepository;
	
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
	@RequestMapping(value = "/campaign", method = RequestMethod.POST )
	public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
		Campaign savedCampaign = campaignRepository.save(campaign);
		return new ResponseEntity<>(savedCampaign, HttpStatus.OK);	
	}

	
}
