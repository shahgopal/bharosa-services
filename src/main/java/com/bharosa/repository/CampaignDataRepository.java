package com.bharosa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.CampaignData;

import io.swagger.annotations.Api;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "campaign", path = "campaign")
@Api(value = "CampaignRepository api" )
public interface CampaignDataRepository extends PagingAndSortingRepository<CampaignData, Long> {

//    List<Person> findByLastName(@Param("name") String name);
	
	List<CampaignData> findTop2ByOrderByIdDesc();
	List<CampaignData> findTop2ByOrderByGoalDesc();
	List<CampaignData> findTop5ByOrderByCampaignSupportersDataDesc();
	
	  
	@Query(nativeQuery = true)
    List<Object> findByMostLikedCampaignData();
	@Query(nativeQuery = true)
    List<Object> findByCampaignDataByMostComments();
	@Query(nativeQuery = true)
    List<Object> findByCampaignDataByPopularity();
	@Query(nativeQuery = true)
    List<Object> findByMostPaymentRequestData();
	
	
	

}