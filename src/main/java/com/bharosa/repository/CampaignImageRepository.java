package com.bharosa.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.Campaign;
import com.bharosa.model.CampaignImage;
import com.bharosa.model.PaymentRequest;

import io.swagger.annotations.Api;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "campaignImage", path = "campaignimage")
@Api(value = "CampaignRepository api" )
public interface CampaignImageRepository extends PagingAndSortingRepository<CampaignImage, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
	  List<CampaignImage> findByCampaign(Campaign campaign);
	
	
	

}