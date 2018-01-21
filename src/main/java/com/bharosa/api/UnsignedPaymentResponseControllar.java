package com.bharosa.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.util.MultiValueMap;
@RestController
@RequestMapping("/common")
public class UnsignedPaymentResponseControllar {

	
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
//	public ResponseEntity<PaymentResponse> processPaymentResponse(@RequestBody PaytmResponseModel paytmResponseModel) {
	public ResponseEntity<String> processPaymentResponse(@RequestBody MultiValueMap<String, Object> bodyMap) {
		
		PaytmResponseModel paytmResponseModel = new PaytmResponseModel();
		System.out.println("MultiValueMap"+bodyMap.toString());

		paytmResponseModel.setMID((String)bodyMap.getFirst("MID"));
		paytmResponseModel.setTXNID((String)bodyMap.getFirst("TXNID")); 
		paytmResponseModel.setORDERID((UUID.fromString((String)bodyMap.getFirst("ORDERID")))); 
		paytmResponseModel.setBANKTXNID((String)bodyMap.getFirst("BANKTXNID")); 
		paytmResponseModel.setTXNAMOUNT((String)bodyMap.getFirst("TXNAMOUNT")); 
		paytmResponseModel.setCURRENCY((String)bodyMap.getFirst("CURRENCY"));
		paytmResponseModel.setSTATUS((String)bodyMap.getFirst("STATUS")); 
		paytmResponseModel.setRESPCODE((String)bodyMap.getFirst("RESPCODE"));
		paytmResponseModel.setRESPMSG((String)bodyMap.getFirst("RESPMSG"));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
		try {
			paytmResponseModel.setTXNDATE(formatter.parse((String)bodyMap.getFirst("TXNDATE")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cudnt set the date");
		}
		
		
		paytmResponseModel.setGATEWAYNAME((String)bodyMap.getFirst("GATEWAYNAME")); 
		paytmResponseModel.setBANKNAME((String)bodyMap.getFirst("BANKNAME"));
		paytmResponseModel.setPAYMENTMODE((String)bodyMap.getFirst("PAYMENTMODE"));
		paytmResponseModel.setCHECKSUMHASH((String)bodyMap.getFirst("CHECKSUMHASH"));
		paytmResponseModel.setTXNTYPE((String)bodyMap.getFirst("TXNTYPE"));
		
		PaymentResponse pr = PaytmUtil.paymentResponseBuilder(paytmResponseModel);
		System.out.println("paytmResponseModel"+paytmResponseModel.toString());
		System.out.println("paytmResponseModel"+ paytmResponseModel.getORDERID());
		System.out.println("paytmResponseModel"+ pr.getOrderId());
		PaymentRequest paymentRequest = paymentRequestRepository.findByOrderId(pr.getOrderId());
		pr.setCampaign(paymentRequest.getCampaign());
		pr.setPaymentRequest(paymentRequest);
		pr.setIsValidchecksumHash(PaytmUtil.isValidChecksum(paytmResponseModel, paymentRequest));
		
		PaymentResponse savedPR = paymentResponseRepository.save(pr);
//		pr.setCampaign(campaignRepository.findOne(paytmRequestModel.getCAMPAIGN_ID()));
		
		if (savedPR.getStatus().equalsIgnoreCase("TXN_SUCCESS")) {
			return new ResponseEntity<>(
					"Thank you for your Donation! We were able to process the transaction successfully", HttpStatus.OK);
		} else if (savedPR.getStatus().equalsIgnoreCase("PENDING")) {
			return new ResponseEntity<>("Thank you for your Donation! Your transaction is in pending status ",
					HttpStatus.OK);
		}
//		else (savedPR.getStatus().equalsIgnoreCase("TXN_FAILURE")) {
		else{
			return new ResponseEntity<>("We were unable to process the transaction Please try again", HttpStatus.OK);
		}			
		
		
	}

	
}
