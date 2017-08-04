package com.bharosa.repository;

/**
 * Created by gshah on 8/1/17.
 */
import java.util.List;

import com.bharosa.model.Campaign;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "campaign", path = "campaign")
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long> {

//    List<Person> findByLastName(@Param("name") String name);

}