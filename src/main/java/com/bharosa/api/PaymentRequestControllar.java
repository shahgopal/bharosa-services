package com.bharosa.api;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.PaymentRequest;
import com.bharosa.model.PaytmRequestModel;
import com.bharosa.paytm.PaytmUtil;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.PaymentRequestRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Payment api" )
public class PaymentRequestControllar {

	
	@Autowired
	PaymentRequestRepository paymentRequestRepository;
	@Autowired
	CampaignRepository campaignRepository;
	
	
	@RequestMapping(value = "/paymentrequests", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Iterable<PaymentRequest>> getPaymentRequests() {
		return new ResponseEntity<>(paymentRequestRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/paymentrequest/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<PaymentRequest> getPaymentRequest(@PathVariable long id) {
		PaymentRequest paymentRequest = paymentRequestRepository.findOne(id);
		if (paymentRequest != null) {
			return new ResponseEntity<>(paymentRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Do not use", notes = "do not use")
	@RequestMapping(value = "/paymentrequest", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<PaymentRequest> createPaymentRequest(@RequestBody PaymentRequest paymentRequest) {
		if(paymentRequest.getCampaign() != null && paymentRequest.getCampaign().getId() > 0){
			paymentRequest.setCampaign(campaignRepository.findOne(paymentRequest.getCampaign().getId()));
		}
		PaymentRequest savedPR = paymentRequestRepository.save(paymentRequest);
		return new ResponseEntity<>(savedPR, HttpStatus.OK);
	}


	@ApiOperation(value = "Provide paytm request", notes = "Returns checksum and other parameters")
	@RequestMapping(value = "/requestpaytm", method = RequestMethod.POST, consumes = "application/json")
	@CrossOrigin
	public ResponseEntity<TreeMap<String, String>> createPaytmLoad(@RequestBody PaytmRequestModel paytmRequestModel) {
		TreeMap<String, String> paramMap = PaytmUtil.generatePayLoad(paytmRequestModel);
		PaymentRequest pr = PaytmUtil.paymentRequestBuilder(paytmRequestModel);
		pr.setCampaign(campaignRepository.findOne(paytmRequestModel.getCAMPAIGN_ID()));
		paymentRequestRepository.save(pr);
		
		return new ResponseEntity<>(paramMap, HttpStatus.OK);
	}

	
}
