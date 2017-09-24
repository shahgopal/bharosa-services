//package com.bharosa.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.social.security.SocialUserDetails;
//import org.springframework.social.security.SocialUserDetailsService;
//import org.springframework.stereotype.Service;
//
//import com.bharosa.model.User;
//import com.bharosa.social.facebook.FacebookTokenUserDetails;
//
//@Service
//public class FacebookSocialUserDetailsService  implements SocialUserDetailsService{
//
//	@Autowired 
//	AppUserDetailsService appUserDetailsService;
//	
//	@Override
//	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
//		User user = appUserDetailsService.findUserByUsername(userId);
//		FacebookTokenUserDetails principal = new FacebookTokenUserDetails(user.getEmail(), user.getPassword(),
//				user.getAuthorities());
//		principal.setFirstName(user.getFirstName());
//		principal.setId(user.getId().toString());
//		principal.setLastName(user.getLastName());
//		principal.setSocialSignInProvider(user.getSignInProvider());
//		return principal;
//	}
//
//}
