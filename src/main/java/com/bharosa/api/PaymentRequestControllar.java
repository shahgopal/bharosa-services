package com.bharosa.api;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api")
public class PaymentRequestControllar {

	
	@Autowired
	PaymentRequestRepository paymentRequestRepository;
	@Autowired
	CampaignRepository campaignRepository;
	
	
	@RequestMapping(value = "/paymentrequests", method = RequestMethod.GET)
	public ResponseEntity<Iterable<PaymentRequest>> getPaymentRequests() {
		return new ResponseEntity<>(paymentRequestRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/paymentrequest/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentRequest> getPaymentRequest(@PathVariable long id) {
		PaymentRequest paymentRequest = paymentRequestRepository.findOne(id);
		if (paymentRequest != null) {
			return new ResponseEntity<>(paymentRequest, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/paymentrequest", method = RequestMethod.POST)
	public ResponseEntity<PaymentRequest> createPaymentRequest(@RequestBody PaymentRequest paymentRequest) {
		if(paymentRequest.getCampaign() != null && paymentRequest.getCampaign().getId() > 0){
			paymentRequest.setCampaign(campaignRepository.findOne(paymentRequest.getCampaign().getId()));
		}
		PaymentRequest savedPR = paymentRequestRepository.save(paymentRequest);
		return new ResponseEntity<>(savedPR, HttpStatus.OK);
	}


	@RequestMapping(value = "/requestpaytm", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TreeMap<String, String>> createPaytmLoad(@RequestBody PaytmRequestModel paytmRequestModel) {
		System.out.println(paytmRequestModel.toString());
    	System.out.println(paytmRequestModel.getCAMPAIGN_ID());
    	System.out.println(paytmRequestModel.getCUST_ID());
    	System.out.println(paytmRequestModel.getTXN_AMOUNT());
    	System.out.println(paytmRequestModel.getCALLBACK_URL());
    	System.out.println(paytmRequestModel.getEMAIL());
    	System.out.println(paytmRequestModel.getINDUSTRY_TYPE_ID());
    	System.out.println(paytmRequestModel.getMID());
    	System.out.println(paytmRequestModel.getMOBILE_NO());
    	System.out.println(paytmRequestModel.getORDER_ID());

		TreeMap<String, String> paramMap = PaytmUtil.generatePayLoad(paytmRequestModel);
		PaymentRequest pr = PaytmUtil.paymentRequestBuilder(paytmRequestModel);
		pr.setCampaign(campaignRepository.findOne(paytmRequestModel.getCAMPAIGN_ID()));
		paymentRequestRepository.save(pr);
		return new ResponseEntity<>(paramMap, HttpStatus.OK);
	}

	
}
