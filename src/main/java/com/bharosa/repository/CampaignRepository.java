package com.bharosa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
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
	
	List<Campaign> findTop2ByOrderByIdDesc();
	List<Campaign> findTop2ByOrderByGoalDesc();
	List<Campaign> findTop5ByOrderByCampaignSupportersDesc();
	
	  
	@Query(nativeQuery = true)
    List<Object> findByMostLikedCampaign();
	@Query(nativeQuery = true)
    List<Object> findByCampaignByMostComments();
	@Query(nativeQuery = true)
    List<Object> findByCampaignByPopularity();
	@Query(nativeQuery = true)
    List<Object> findByMostPaymentRequest();
	
	
	

}