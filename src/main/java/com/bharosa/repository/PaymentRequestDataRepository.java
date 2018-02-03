package com.bharosa.repository;

/**
 * Created by gshah on 8/1/17.
 */
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.CampaignData;
import com.bharosa.model.PaymentRequestData;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "paymentrequest", path = "paymentrequest")
public interface PaymentRequestDataRepository extends PagingAndSortingRepository<PaymentRequestData, Long> {

	List<PaymentRequestData> findByCampaignData(CampaignData campaign);

	List<PaymentRequestData> findByCampaignDataId(long campaignId);

	PaymentRequestData findByOrderId(UUID orderId);


}
