package com.bharosa.api;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.CampaignData;
import com.bharosa.model.PaymentRequestData;
import com.bharosa.model.PaytmRequestModel;
import com.bharosa.paytm.PaytmUtil;
import com.bharosa.repository.CampaignDataRepository;
import com.bharosa.repository.PaymentRequestDataRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Payment api" )
public class PaymentRequestControllar {

	
	@Autowired
	PaymentRequestDataRepository paymentRequestRepository;
	@Autowired
	CampaignDataRepository campaignRepository;
	
	
	@RequestMapping(value = "/paymentrequests", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Iterable<PaymentRequestData>> getPaymentRequests() {
		return new ResponseEntity<>(paymentRequestRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/paymentrequest/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<PaymentRequestData> getPaymentRequest(@PathVariable long id) {
		PaymentRequestData paymentRequest = paymentRequestRepository.findOne(id);
		if (paymentRequest != null) {
			return new ResponseEntity<>(paymentRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Do not use", notes = "do not use")
	@RequestMapping(value = "/paymentrequest", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<PaymentRequestData> createPaymentRequest(@RequestBody PaymentRequestData paymentRequest) {
		System.out.println("paymentRequest.getCampaign().getId() is " + paymentRequest.getCampaignData().getId());
		
		if(paymentRequest.getCampaignData() != null && paymentRequest.getCampaignData().getId() > 0){
			paymentRequest.setCampaignData(campaignRepository.findOne(paymentRequest.getCampaignData().getId()));
		}
		PaymentRequestData savedPR = paymentRequestRepository.save(paymentRequest);
		return new ResponseEntity<>(savedPR, HttpStatus.OK);
	}


	@ApiOperation(value = "Provide paytm request", notes = "Returns checksum and other parameters")
	@RequestMapping(value = "/requestpaytm", method = RequestMethod.POST, consumes = "application/json")
	@CrossOrigin
	public ResponseEntity<TreeMap<String, String>> createPaytmLoad(@RequestBody PaytmRequestModel paytmRequestModel) {

		
		TreeMap<String, String> paramMap = null;
		System.out.println("System.out.println(paytmRequestModel.getCAMPAIGN_ID());"+paytmRequestModel.getCAMPAIGN_ID());
		if (paytmRequestModel.getCAMPAIGN_ID() != 0) {
			CampaignData campaign = campaignRepository.findOne(paytmRequestModel.getCAMPAIGN_ID());
			System.out.println("Campaign"+campaign.getDetails());
//			paytmRequestModel.getORDER_ID().toString() 
//			paymentRequestRepository.save(pr);
			paramMap = PaytmUtil.generatePayLoad(paytmRequestModel);
			PaymentRequestData pr = PaytmUtil.paymentRequestBuilder(paytmRequestModel);
			pr.setCampaignData(campaign);
			pr.setChecksumHash(paramMap.get("CHECKSUMHASH"));
			paymentRequestRepository.save(pr);
			return new ResponseEntity<>(paramMap, HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<>(paramMap, HttpStatus.FAILED_DEPENDENCY);	
		}

		
//		if(paytmRequestModel.getCAMPAIGN_ID() == 0 || paymentRequestRepository.find > 0){
//			paymentRequest.setCampaign(campaignRepository.findOne(campaignRepository.fin));
//		}
//		TreeMap<String, String> paramMap = PaytmUtil.generatePayLoad(paytmRequestModel);
//		PaymentRequest pr = PaytmUtil.paymentRequestBuilder(paytmRequestModel);
//		pr.setCampaign(campaignRepository.findOne(paytmRequestModel.getCAMPAIGN_ID()));
//		paymentRequestRepository.save(pr);
//		return new ResponseEntity<>(paramMap, HttpStatus.OK);
	}

	
	
}
