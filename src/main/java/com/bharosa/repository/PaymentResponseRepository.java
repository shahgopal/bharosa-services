package com.bharosa.repository;

/**
 * Created by gshah on 8/1/17.
 */
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.Campaign;
import com.bharosa.model.PaymentResponse;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "paymentresponse", path = "paymentresponse")
public interface PaymentResponseRepository  extends PagingAndSortingRepository<PaymentResponse, Long> {

  List<PaymentResponse> findByCampaign(@Param("campaign") Campaign campaign);
  List<PaymentResponse> findByCampaignId(long campaignId);
  List<PaymentResponse> findByOrderId(UUID orderId);

	
}
