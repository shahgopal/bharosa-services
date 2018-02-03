package com.bharosa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bharosa.model.CampaignData;
import com.bharosa.model.CampaignSupportersData;
import com.bharosa.model.User;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Optional<User> findByUsername(String username);
	User save(User user);
    int deleteUserById(Long id);

    
}

