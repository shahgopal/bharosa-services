//package com.bharosa.security;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Configuration
//@EnableResourceServer
//public class ResourceConfig extends ResourceServerConfigurerAdapter {
//
//    @Value("${security.oauth2.resource.id}")
//    private String resourceId;
//
//    // The DefaultTokenServices bean provided at the AuthorizationConfig
//    @Autowired
//    private DefaultTokenServices tokenServices;
//
//    // The TokenStore bean provided at the AuthorizationConfig
//    @Autowired
//    private TokenStore tokenStore;
//
//    // To allow the rResourceServerConfigurerAdapter to understand the token,
//    // it must share the same characteristics with AuthorizationServerConfigurerAdapter.
//    // So, we must wire it up the beans in the ResourceServerSecurityConfigurer.
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources
//                .resourceId(resourceId)
//                .tokenServices(tokenServices)
//                .tokenStore(tokenStore);
//
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//
//                .requestMatcher(new OAuthRequestedMatcher())
//                .csrf().disable()
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                // when restricting access to 'Roles' you must remove the "ROLE_" part role
//                // for "ROLE_USER" use only "USER"
//                // use the full name when specifying authority access
//                // restricting all access to /api/** to authenticated users
//                .antMatchers("/api/**").authenticated();
//    }
//
//    private static class OAuthRequestedMatcher implements RequestMatcher {
//        public boolean matches(HttpServletRequest request) {
//            // Determine if the resource called is "/api/**"
//            String path = request.getServletPath();
//            if ( path.length() >= 5 ) {
//                path = path.substring(0, 5);
//                boolean isApi = path.equals("/api/");
//                return isApi;
//            } else return false;
//        }
//    }
//    
//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }
//
//
//}