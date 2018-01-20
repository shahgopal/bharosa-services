package com.bharosa.api;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.bharosa.model.PaymentRequest;
import com.bharosa.repository.CampaignImageRepository;
import com.bharosa.repository.CampaignRepository;
import com.bharosa.repository.CampaignSupportersRepository;
import com.bharosa.repository.PaymentRequestRepository;
import com.bharosa.repository.PaymentResponseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class CampaignSupporterController {

	
	
	@Autowired
	private CampaignRepository campaignRepository;
	@Autowired
	CampaignSupportersRepository campaignSupportersRepository;

	@ApiOperation(value = "Do not use", notes = "do not use")
	@RequestMapping(value = "/likes", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<CampaignSupporters> createCampaignLike(@RequestBody CampaignSupporters campaignSupporters) {
		
		if(campaignSupporters.getCampaign() != null && campaignSupporters.getCampaign().getId() > 0){
			campaignSupporters.setCampaign(campaignRepository.findOne(campaignSupporters.getCampaign().getId()));
		}
		CampaignSupporters savedCampaignSupport = campaignSupportersRepository.save(campaignSupporters);
		return new ResponseEntity<>(savedCampaignSupport, HttpStatus.OK);
	}
	
}
