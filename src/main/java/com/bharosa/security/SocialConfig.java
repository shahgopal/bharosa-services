package com.bharosa.security;

import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import com.bharosa.social.facebook.FacebookUserDetailsService;
import com.bharosa.services.AppUserDetailsService;
import com.bharosa.social.facebook.FacebookTokenAuthenticationFilter;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
@Configuration
public class SocialConfig {
	
//    @Autowired
//    private FacebookSocialUserDetailsService facebookSocialUserDetailsService;
	@Autowired 
	 private Environment environment; 
	
    @Autowired AuthenticationManager authenticationManager;

    @Autowired UsersConnectionRepository usersConnectionRepository;
    @Autowired SocialAuthenticationServiceLocator connectionFactoryLocator;
    @Autowired DataSource datasource;

    
	@Bean
    public AuthenticationNameUserIdSource authenticationNameUserIdSource(){
        return new AuthenticationNameUserIdSource();
    }
	
	@Autowired
	FacebookUserDetailsService facebookSocialUserDetailsService;

	
	

//    @Bean
//    public SocialAuthenticationProvider socialAuthenticationProvider(){
//        return new SocialAuthenticationProvider(usersConnectionRepository,facebookSocialUserDetailsService);
//    }
    
    @Bean 
    FacebookTokenAuthenticationFilter facebookTokenAuthenticationFilter()
    {
    	return new FacebookTokenAuthenticationFilter(authenticationManager,authenticationNameUserIdSource(),usersConnectionRepository,connectionFactoryLocator);
    }
    
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(
            environment.getProperty("facebook.appId"),
            environment.getProperty("facebook.appSecret")));
        return registry;
    }
    

    
    @Bean
	public UsersConnectionRepository usersConnectionRepository() {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(datasource,
				connectionFactoryLocator(), Encryptors.noOpText());
//		repository.setConnectionSignUp(new SimpleConnectionSignUp());
		return repository;
	}


//    @PostConstruct
//    private void init() {
//        try {
//            String[] fieldsToMap = {
//                "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type","is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format","political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other","sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "viewer_can_send_gift","website", "work"
//            };
//
//            Field field = Class.forName("org.springframework.social.facebook.api.UserOperations").
//                    getDeclaredField("PROFILE_FIELDS");
//            field.setAccessible(true);
//
//            Field modifiers = field.getClass().getDeclaredField("modifiers");
//            modifiers.setAccessible(true);
//            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//            field.set(null, fieldsToMap);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


}
