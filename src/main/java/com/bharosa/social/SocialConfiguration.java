//package com.bharosa.social;
////
////import com.bharosa.security.AuthUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.social.config.annotation.SocialConfigurer;
//import org.springframework.social.connect.web.SignInAdapter;
////
////import javax.sql.DataSource;
////
//@Configuration
//public class SocialConfiguration {
////
//    @Bean
//    public SocialConfigurer socialConfigurerAdapter(DataSource dataSource) {
//        System.out.println("Starting socialConfigurerAdapter");
//        System.out.println("Starting socialConfigurerAdapter Gopal");
//
//        // https://github.com/spring-projects/spring-social/blob/master/spring-social-config/src/main/java/org/springframework/social/config/annotation/SocialConfiguration.java#L87
//        return new DatabaseSocialConfigurer(dataSource);
//    }
//
////    @Bean
////    public SignInAdapter authSignInAdapter() {
////        System.out.println("Starting socialConfigurerAdapter authSignInAdapter");
////        System.out.println("Starting socialConfigurerAdapter authSignInAdapter Gopal");
////
////        return (userId, connection, request) -> {
////            AuthUtil.authenticate(connection);
////            
////            return null;
////        };
////    }
//	
//}