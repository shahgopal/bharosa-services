package com.bharosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignImageData;

import io.swagger.annotations.Api;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "campaignImage", path = "campaignimage")
@Api(value = "CampaignRepository api" )
public interface CampaignImageDataRepository extends PagingAndSortingRepository<CampaignImageData, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
	  List<CampaignImageData> findByCampaignData(CampaignData campaignData);

	  
	  

	
	
}