package com.bharosa.social.facebook;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.bharosa.model.SocialMediaService;
import com.bharosa.model.User;
import com.bharosa.repository.UserRepository;
@Service
public class FacebookUserDetailsService implements SocialUserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookUserDetailsService.class);

	@Autowired
	private UserRepository repository;

	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		LOGGER.debug("Loading user by user id: {}", userId);

		Optional<User> user = repository.findByUsername(userId);
		LOGGER.debug("Found user: {}", user);

		if (user == null || !user.isPresent()) {
			throw new UsernameNotFoundException("No user found with username: " + userId);
		}
		
		User facebookUser =  user.get();

		User principal = new User();
		principal.setEmail(facebookUser.getEmail());
		principal.setPassword(facebookUser.getPassword());
		principal.setFirstName(facebookUser.getFirstName());
		principal.setUsername((facebookUser.getId().toString()));
		principal.setLastName(facebookUser.getLastName());
		principal.setSignInProvider(SocialMediaService.FACEBOOK);
		principal.setRoles(facebookUser.getRoles());

		LOGGER.debug("Found user details: {}", principal);

		return principal;
	}
	
	
	
}