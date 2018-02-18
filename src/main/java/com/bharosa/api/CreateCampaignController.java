package com.bharosa.api;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bharosa.dto.Campaign;
import com.bharosa.model.User;
import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignImageData;
import com.bharosa.repository.UserRepository;
import com.bharosa.repository.CampaignDataRepository;
import com.bharosa.repository.CampaignImageDataRepository;
import com.bharosa.repository.CampaignSupportersDataRepository;
import com.bharosa.repository.PaymentRequestDataRepository;
import com.bharosa.repository.PaymentResponseDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class CreateCampaignController {

	@Autowired
	private CampaignDataRepository campaignRepository;
	
	@Autowired
	private CampaignImageDataRepository campaignImageRepository;

	@Autowired
	private PaymentRequestDataRepository paymentRequestRepository;
	
	@Autowired
	PaymentResponseDataRepository paymentResponseRepository;

	@Autowired
	CampaignSupportersDataRepository campaignSupportersRepository;

    
    @Autowired
    private UserRepository userRepository;

	
//	@RequestMapping(value = "payment", method = RequestMethod.GET, produces = "application/json")
//	public String greeting() {
//
//		PaytmUtil paytmUtil = new PaytmUtil();
//		return paytmUtil.generatePayLoad();
//	}
	

	@ApiOperation(value = "create campaign Entity", notes = "return success")
	@CrossOrigin
	@RequestMapping(value = "/create/campaign", method = RequestMethod.POST)
	public ResponseEntity<Campaign> createCampaignEntity(@RequestBody CampaignData campaign) {
		Optional<User> user=null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    user = userRepository.findByUsername(currentUserName);
		}
		System.out.println("campaign is " + campaign);
		campaign.setUser(user.get());
		CampaignData savedCampaign = campaignRepository.save(campaign);
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Campaign campaigndto = modelMapper.map(savedCampaign, Campaign.class);
		return new ResponseEntity<>(campaigndto, HttpStatus.OK);	//Return entity
	}
    
	@ApiOperation(value = "create campaign multipart", notes = "return success")
	@CrossOrigin
	@RequestMapping(value = "/create/campaign/multipart", method = RequestMethod.POST,
	        headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public ResponseEntity<Campaign> createCampaignMultipart(
	        @RequestParam(value = "image", required = false) MultipartFile image,
	        @RequestParam(value = "campaign", required = true) String campaignString) throws SerialException, SQLException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		CampaignData campaign = mapper.readValue(campaignString, CampaignData.class);
		
//	  LOG.info("POST_v1_scouting_activities: headers.getContentType(): {}", headers.getContentType());
//	  LOG.info("POST_v1_scouting_activities: userId: {}", userId);
//	  LOG.info("POST_v1_scouting_activities: image.originalFilename: {}, image: {}",
//	          (image!=null) ? image.getOriginalFilename() : null, image);
//	  LOG.info("POST_v1_scouting_activities: scouting_activity_json.getType().getName(): {}, scouting_activity: {}",
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> user=null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			
			
			String useremail = ((User) authentication.getPrincipal()).getEmail();
		    user = userRepository.findByEmail(useremail);
		    campaign.setUser(user.get());
		}
		System.out.println("campaign is " + campaign);

		
		
		CampaignData savedCampaign = campaignRepository.save(campaign);
		if (image != null) {
			CampaignImageData campaignImage = new CampaignImageData(); 
			campaignImage.setCampaignData(savedCampaign);
			campaignImage.setContentType(image.getContentType());
			campaignImage.setName(image.getName());
			campaignImage.setOriginalFilename(image.getOriginalFilename());
			campaignImage.setImageData(new SerialBlob(image.getBytes()));
			campaignImageRepository.save(campaignImage);
			
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Campaign campaigndto = modelMapper.map(savedCampaign, Campaign.class);
		return new ResponseEntity<>(campaigndto, HttpStatus.OK);	
	}

	
	
}
