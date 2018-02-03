package com.bharosa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignSupportersData;

import io.swagger.annotations.Api;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "campaignSupporters", path = "campaignsupporters")
@Api(value = "CampaignRepository api" )
public interface CampaignSupportersDataRepository extends PagingAndSortingRepository<CampaignSupportersData, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
//	List<CampaignSupporters> findTop5ByOrderByIdDesc();
//	List<CampaignSupporters> findTop5ByOrderByLikesDesc();
	Set<CampaignSupportersData> findTop5ByOrderByCampaignDataDesc();
	
	

	
	  List<CampaignSupportersData> findByCampaignData(@Param("campaign") CampaignData campaignData);
	  List<CampaignSupportersData> findByCampaignDataId(long campaignId);

	
	  

	

}