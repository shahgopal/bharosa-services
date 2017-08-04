package com.bharosa.repository;

/**
 * Created by gshah on 8/1/17.
 */
import java.util.List;

import com.bharosa.model.PaymentRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "paymentrequest", path = "paymentrequest")
public interface PaymentRequestRepository  extends PagingAndSortingRepository<PaymentRequest, Long> {
}
