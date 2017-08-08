package com.bharosa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.PaymentRequest;
import com.bharosa.model.PaymentResponse;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.PaymentResponseRepository;

@RestController
@RequestMapping("/api")
public class PaymentResponseControllar {

	
	@Autowired
	PaymentResponseRepository paymentResponseRepository;
	@Autowired
	CampaignRepository campaignRepository;
	
	@RequestMapping(value = "/paymentresponses", method = RequestMethod.GET)
	public ResponseEntity<Iterable<PaymentResponse>> getPaymentResponses() {
		return new ResponseEntity<>(paymentResponseRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/paymentresponse/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentResponse> getPaymentResponse(@PathVariable long id) {
		PaymentResponse paymentResponse = paymentResponseRepository.findOne(id);
		if (paymentResponse != null) {
			return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/paymentresponse", method = RequestMethod.POST)
	public ResponseEntity<PaymentResponse> createPaymentResponse(@RequestBody PaymentResponse paymentResponse) {
		
		if(paymentResponse.getCampaign() != null && paymentResponse.getCampaign().getId() > 0)
			paymentResponse.setCampaign(campaignRepository.findOne(paymentResponse.getCampaign().getId()));
			
		PaymentResponse savedPR = paymentResponseRepository.save(paymentResponse);
		return new ResponseEntity<>(savedPR, HttpStatus.OK);
	}
	
	
}
