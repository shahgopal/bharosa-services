package com.bharosa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bharosa.model.Campaign;

@RepositoryRestResource(collectionResourceRel = "campaign", path = "campaign")
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
	
	
	

}