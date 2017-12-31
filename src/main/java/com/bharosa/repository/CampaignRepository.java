package com.bharosa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.Campaign;

import io.swagger.annotations.Api;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "campaign", path = "campaign")
@Api(value = "CampaignRepository api" )
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
	
	
	

}