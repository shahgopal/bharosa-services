package com.bharosa.api;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignImageData;
import com.bharosa.repository.CampaignDataRepository;
import com.bharosa.repository.CampaignImageDataRepository;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/campaign")
public class CampaignImageControllar {

	@Autowired
	private CampaignDataRepository campaignRepository;
	
	@Autowired
	private CampaignImageDataRepository campaignImageRepository;

	
	@ApiOperation(value = "provide selected campaign image", notes = "return campaign image")
	@CrossOrigin
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCampaignImage(@PathVariable long id, final HttpServletResponse response) throws SQLException {
		CampaignData campaign = campaignRepository.findOne(id);
		List<CampaignImageData> images = campaignImageRepository.findByCampaignData(campaign);
		CampaignImageData image  =images.get(0);
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	    System.out.println("image.getContentType()" + image.getContentType());
	    response.setContentType(image.getContentType());
	    byte[] media = image.getImageData().getBytes(1, (int)image.getImageData().length());
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, image.getContentType()).body(media);
//        return responseEntity;
}	


	

	
	
}
