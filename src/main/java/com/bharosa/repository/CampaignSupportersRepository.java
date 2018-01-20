package com.bharosa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.Campaign;
import com.bharosa.model.CampaignSupporters;

import io.swagger.annotations.Api;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "campaignSupporters", path = "campaignsupporters")
@Api(value = "CampaignRepository api" )
public interface CampaignSupportersRepository extends PagingAndSortingRepository<CampaignSupporters, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
//	List<CampaignSupporters> findTop5ByOrderByIdDesc();
//	List<CampaignSupporters> findTop5ByOrderByLikesDesc();
	Set<CampaignSupporters> findTop5ByOrderByCampaignDesc();
	
	

	
	  List<CampaignSupporters> findByCampaign(@Param("campaign") Campaign campaign);
	  List<CampaignSupporters> findByCampaignId(long campaignId);

	
	  

	

}