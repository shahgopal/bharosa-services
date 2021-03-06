package com.bharosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bharosa.services.AppUserDetailsService;

@SpringBootApplication
public class BharosaServicesApplication {


	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	    public AppUserDetailsService userDetailsService(){
	        return new AppUserDetailsService();
	    }
	    
	  
	public static void main(String[] args) {
        System.out.println("Starting BharosaServicesApplication GOPAL");
        SpringApplication.run(BharosaServicesApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }

//    @Bean
//    CommandLineRunner init(
////          AppUserDetailsService accountService
//    ) {
//       return (evt) -> Arrays.asList(
//             "user,admin,john,robert,ana".split(",")).forEach(
//             username -> {
//                User acct = new User();
//                acct.setUsername(username);
//                acct.setPassword("password");
//                acct.setFirstName(username);
//                acct.setLastName("LastName");
//                acct.grantAuthority(Role.ROLE_USER);
//                if (username.equals("admin"))
//                   acct.grantAuthority(Role.ROLE_ADMIN);
//                accountService.registerUser(acct);
//             }
//       );
//    }    
//    
}
