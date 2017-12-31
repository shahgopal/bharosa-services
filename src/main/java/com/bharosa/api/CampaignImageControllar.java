package com.bharosa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.Campaign;
import com.bharosa.model.CampaignImage;
import com.bharosa.repository.CampaignImageRepository;
import com.bharosa.repository.CampaignRepository;

import io.swagger.annotations.ApiOperation;


@RestController
public class CampaignImageControllar {

	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private CampaignImageRepository campaignImageRepository;

	
	@ApiOperation(value = "provide selected campaign image", notes = "return campaign image")
	@CrossOrigin
	@RequestMapping(value = "/campaignimage/{id}", method = RequestMethod.GET, produces = 
			MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CampaignImage> getCampaignImage(@PathVariable long id) {
		Campaign campaign = campaignRepository.findOne(id);
		List<CampaignImage> images = campaignImageRepository.findByCampaign(campaign);
		
		return new ResponseEntity<>(images.get(0), HttpStatus.OK);	
	
}	

	

	
	
}
