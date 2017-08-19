package com.bharosa.api;

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

import com.bharosa.model.Campaign;
import com.bharosa.model.PaymentRequest;
import com.bharosa.model.PaymentResponse;
import com.bharosa.model.PaytmRequestModel;
import com.bharosa.model.PaytmResponseModel;
import com.bharosa.paytm.PaytmUtil;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.PaymentRequestRepository;
import com.bharosa.repository.PaymentResponseRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class PaymentResponseControllar {

	
	@Autowired
	PaymentResponseRepository paymentResponseRepository;
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	PaymentRequestRepository paymentRequestRepository;

	
	@RequestMapping(value = "/paymentresponses", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Iterable<PaymentResponse>> getPaymentResponses() {
		return new ResponseEntity<>(paymentResponseRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/paymentresponse/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<PaymentResponse> getPaymentResponse(@PathVariable long id) {
		PaymentResponse paymentResponse = paymentResponseRepository.findOne(id);
		if (paymentResponse != null) {
			return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/paymentresponse", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<PaymentResponse> createPaymentResponse(@RequestBody PaymentResponse paymentResponse) {
		
		if(paymentResponse.getCampaign() != null && paymentResponse.getCampaign().getId() > 0)
			paymentResponse.setCampaign(campaignRepository.findOne(paymentResponse.getCampaign().getId()));
			
		PaymentResponse savedPR = paymentResponseRepository.save(paymentResponse);
		return new ResponseEntity<>(savedPR, HttpStatus.OK);
	}

	@ApiOperation(value = "Process paytm response", notes = "Returns success or failure")
	@RequestMapping(value = "/paytmresponse", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	@CrossOrigin
	public ResponseEntity<PaymentResponse> processPaymentResponse(@RequestBody PaytmResponseModel paytmResponseModel) {
		
		PaymentResponse pr = PaytmUtil.paymentResponseBuilder(paytmResponseModel);
		System.out.println("paytmResponseModel"+paytmResponseModel.toString());
		System.out.println("paytmResponseModel"+ paytmResponseModel.getORDERID());
		System.out.println("paytmResponseModel"+ pr.getOrderId());
		PaymentRequest paymentRequest = paymentRequestRepository.findByOrderId(pr.getOrderId());
		pr.setCampaign(paymentRequest.getCampaign());
		pr.setPaymentRequest(paymentRequest);
		System.out.println("Payment Processing Status" + PaytmUtil.isValidChecksum(paytmResponseModel, paymentRequest));
		PaymentResponse savedPR = paymentResponseRepository.save(pr);
//		pr.setCampaign(campaignRepository.findOne(paytmRequestModel.getCAMPAIGN_ID()));
		
		return new ResponseEntity<>(savedPR, HttpStatus.OK);
		
	}

	
}
