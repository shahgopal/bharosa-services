package com.bharosa.api;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.dto.Campaign;
import com.bharosa.model.CampaignData;
import com.bharosa.repository.UserRepository;
import com.bharosa.repository.CampaignDataRepository;
import com.bharosa.repository.CampaignImageDataRepository;
import com.bharosa.repository.CampaignSupportersDataRepository;
import com.bharosa.repository.PaymentRequestDataRepository;
import com.bharosa.repository.PaymentResponseDataRepository;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
public class CampaignController {

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

//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@ApiOperation(value = "get all the campaigns", notes = "return campaigns")
	@CrossOrigin
	@RequestMapping(value = "/campaigns", method = RequestMethod.GET)
	public ResponseEntity<Iterable<CampaignData>> getCampaignsEntity() {
		return new ResponseEntity<>(campaignRepository.findAll(), HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@ApiOperation(value = "get all the campaigns", notes = "return campaigns")
	@CrossOrigin
	@RequestMapping(value = "/campaigns_dto", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Campaign>> getCampaigns() {
		Iterable<CampaignData> campaignsData=	campaignRepository.findAll();
		List<Campaign> campaignsList = new ArrayList<Campaign>();
		Iterable<Campaign>  campaigns=null;
		for(CampaignData campaignData: campaignsData){
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			Campaign campaign = modelMapper.map(campaignData, Campaign.class);
			campaignsList.add(campaign);
			campaigns=campaignsList;
		}			
			return new ResponseEntity<>(campaigns, HttpStatus.OK);
	}
	
	
	
//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@ApiOperation(value = "provide selected campaign entity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/{id}", method = RequestMethod.GET)
	public ResponseEntity<CampaignData> getCampaignEntity(@PathVariable long id) {
		CampaignData campaignData = campaignRepository.findOne(id);
		
		if (campaignData != null) {
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignData(campaignData));
			return new ResponseEntity<>(campaignData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
//	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	@ApiOperation(value = "provide selected campaign dto", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign_dto/{id}", method = RequestMethod.GET)
	public ResponseEntity<Campaign> getCampaign(@PathVariable long id) {
		CampaignData campaignData = campaignRepository.findOne(id);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		if (campaignData != null) {
			campaignData.setPaymentRequestsData(paymentRequestRepository.findByCampaignData(campaignData));
			campaignData.setPaymentResponsesData(paymentResponseRepository.findByCampaignData(campaignData));
			campaignData.setCampaignSupportersData(campaignSupportersRepository.findByCampaignData(campaignData));
			Campaign campaigndto = modelMapper.map(campaignData, Campaign.class);
			return new ResponseEntity<>(campaigndto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "provide selected recent campaign entity", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/recent", method = RequestMethod.GET)
	public ResponseEntity<List<CampaignData>> getRecentCampaignsEntity() {
		List<CampaignData> campaign = campaignRepository.findTop2ByOrderByIdDesc();
			return new ResponseEntity<>(campaign, HttpStatus.OK);
	}
	

	@ApiOperation(value = "provide selected recent campaign dto", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign_dto/recent", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaigns() {
		List<CampaignData> campaignsData = campaignRepository.findTop2ByOrderByIdDesc();
		List<Campaign> campaignsList = new ArrayList<Campaign>();
		for(CampaignData campaignData: campaignsData){
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			Campaign campaign = modelMapper.map(campaignData, Campaign.class);
			campaignsList.add(campaign);
		}			
			return new ResponseEntity<>(campaignsList, HttpStatus.OK);
	}

	
	@ApiOperation(value = "provide campaign with highest goals", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign/highgoal", method = RequestMethod.GET)
	public ResponseEntity<List<CampaignData>> getRecentCampaignsEntityByHighGoal() {
		List<CampaignData> campaignData = campaignRepository.findTop2ByOrderByGoalDesc();
			return new ResponseEntity<>(campaignData, HttpStatus.OK);
	}


	@ApiOperation(value = "provide campaign with highest goals", notes = "return campaign")
	@CrossOrigin
	@RequestMapping(value = "/campaign_dto/highgoal", method = RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getRecentCampaignsByHighGoal() {
		List<CampaignData> campaignsData = campaignRepository.findTop2ByOrderByGoalDesc();
		List<Campaign> campaignsList = new ArrayList<Campaign>();
		for(CampaignData campaignData: campaignsData){
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			Campaign campaign = modelMapper.map(campaignData, Campaign.class);
			campaignsList.add(campaign);
		}			
			return new ResponseEntity<>(campaignsList, HttpStatus.OK);
	}
	


//	@ApiOperation(value = "create campaign with", notes = "return success")
//	@CrossOrigin
//	@RequestMapping(value = "/create/campaign", method = RequestMethod.POST)
//	public ResponseEntity<CampaignData> createCampaign(@RequestBody CampaignData campaign) {
//		Optional<AppUser> user=null;
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (!(authentication instanceof AnonymousAuthenticationToken)) {
//		    String currentUserName = authentication.getName();
//		    user = userRepository.findByUsername(currentUserName);
//		}
//		System.out.println("campaign is " + campaign);
//		campaign.setUser(user.get());
//		CampaignData savedCampaign = campaignRepository.save(campaign);
//		return new ResponseEntity<>(savedCampaign, HttpStatus.OK);	//Return entity
//	}

	
	
//	@ApiOperation(value = "create campaign multipart", notes = "return success")
//	@CrossOrigin
//	@RequestMapping(value = "/create/campaign/multipart", method = RequestMethod.POST,
//	        headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
//	public ResponseEntity<Campaign> createCampaignMultipart(
//	        @RequestParam(value = "image", required = false) MultipartFile image,
//	        @RequestParam(value = "campaign", required = true) String campaignString) throws SerialException, SQLException, IOException {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Campaign campaign = mapper.readValue(campaignString, Campaign.class);
//		
////	  LOG.info("POST_v1_scouting_activities: headers.getContentType(): {}", headers.getContentType());
////	  LOG.info("POST_v1_scouting_activities: userId: {}", userId);
////	  LOG.info("POST_v1_scouting_activities: image.originalFilename: {}, image: {}",
////	          (image!=null) ? image.getOriginalFilename() : null, image);
////	  LOG.info("POST_v1_scouting_activities: scouting_activity_json.getType().getName(): {}, scouting_activity: {}",
//		
//		Campaign savedCampaign = campaignRepository.save(campaign);
//		if (image != null) {
//			CampaignImage campaignImage = new CampaignImage(); 
//			campaignImage.setCampaign(savedCampaign);
//			campaignImage.setContentType(image.getContentType());
//			campaignImage.setName(image.getName());
//			campaignImage.setOriginalFilename(image.getOriginalFilename());
//			campaignImage.setImageData(new SerialBlob(image.getBytes()));
//			campaignImageRepository.save(campaignImage);
//			
//		}
//		return new ResponseEntity<>(savedCampaign, HttpStatus.OK);	
//	}



	
	
}
