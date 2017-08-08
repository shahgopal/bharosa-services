package com.bharosa.repository;

/**
 * Created by gshah on 8/1/17.
 */
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.Campaign;
import com.bharosa.model.PaymentRequest;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "paymentrequest", path = "paymentrequest")
public interface PaymentRequestRepository  extends PagingAndSortingRepository<PaymentRequest, Long> {

	  List<PaymentRequest> findByCampaign(Campaign campaign);
	  List<PaymentRequest> findByCampaignId(long campaignId);
	  
	  
	  

}
