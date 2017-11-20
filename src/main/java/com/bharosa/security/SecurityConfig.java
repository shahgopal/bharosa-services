package com.bharosa.security;

import org.springframework.security.authentication.ProviderManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.bharosa.social.facebook.FacebookUserDetailsService;

@Configuration
@EnableWebSecurity( debug = true )
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired UsersConnectionRepository usersConnectionRepository;
	@Autowired
	FacebookUserDetailsService facebookSocialUserDetailsService;
    
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder( passwordEncoder );
        provider.setUserDetailsService( userDetailsService() );
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
//                .formLogin().disable() // disable form authentication
//                .anonymous().disable() // disable anonymous user
                .httpBasic().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .addFilterBefore(facebookTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // restricting access to authenticated users
        .authorizeRequests()
      .antMatchers("/api/**").authenticated();

    	
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        // provides the default AuthenticationManager as a Bean
//        return super.authenticationManagerBean();
//    }

    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
    	List<AuthenticationProvider> provider = new ArrayList<AuthenticationProvider>();
    	provider.add((AuthenticationProvider)  socialAuthenticationProvider());
    	provider.add((AuthenticationProvider)  authenticationProvider());
    	return new ProviderManager(provider);
    }
    @Bean
    public SocialAuthenticationProvider socialAuthenticationProvider(){
        return new SocialAuthenticationProvider(usersConnectionRepository,facebookSocialUserDetailsService);
    }

    

}
