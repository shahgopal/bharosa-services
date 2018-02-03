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

import com.bharosa.model.CampaignData;
import com.bharosa.model.PaymentResponseData;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "paymentresponse", path = "paymentresponse")
public interface PaymentResponseDataRepository  extends PagingAndSortingRepository<PaymentResponseData, Long> {

  List<PaymentResponseData> findByCampaignData(@Param("campaign") CampaignData campaign);
  List<PaymentResponseData> findByCampaignDataId(long campaignId);
  List<PaymentResponseData> findByOrderId(UUID orderId);

	
}
