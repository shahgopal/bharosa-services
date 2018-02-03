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

import com.bharosa.model.CampaignSupportersData;
import com.bharosa.repository.CampaignDataRepository;
import com.bharosa.repository.CampaignSupportersDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class CampaignSupporterController {

	
	
	@Autowired
	private CampaignDataRepository campaignRepository;
	@Autowired
	CampaignSupportersDataRepository campaignSupportersRepository;

	@ApiOperation(value = "Do not use", notes = "do not use")
	@RequestMapping(value = "/create/likes", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<CampaignSupportersData> createCampaignLike(@RequestBody CampaignSupportersData campaignSupporters) {
		
		if(campaignSupporters.getCampaignData() != null && campaignSupporters.getCampaignData().getId() > 0){
			campaignSupporters.setCampaignData(campaignRepository.findOne(campaignSupporters.getCampaignData().getId()));
		}
		campaignSupporters.setLike("LIKE");
		CampaignSupportersData savedCampaignSupport = campaignSupportersRepository.save(campaignSupporters);
		return new ResponseEntity<>(savedCampaignSupport, HttpStatus.OK);
	}

	@ApiOperation(value = "Do not use", notes = "do not use")
	@RequestMapping(value = "/create/comment", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<CampaignSupportersData> createCampaignComment(@RequestBody CampaignSupportersData campaignSupporters) {
		
		if(campaignSupporters.getCampaignData() != null && campaignSupporters.getCampaignData().getId() > 0){
			campaignSupporters.setCampaignData(campaignRepository.findOne(campaignSupporters.getCampaignData().getId()));
		}
		CampaignSupportersData savedCampaignSupport = campaignSupportersRepository.save(campaignSupporters);
		return new ResponseEntity<>(savedCampaignSupport, HttpStatus.OK);
	}

	
}
