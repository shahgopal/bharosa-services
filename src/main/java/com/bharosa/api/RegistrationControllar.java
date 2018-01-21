package com.bharosa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.User;
import com.bharosa.services.AppUserDetailsService;

import io.swagger.annotations.ApiOperation;

@RestController
public class RegistrationControllar {

	@Autowired
	AppUserDetailsService userDetailsService;
	
	@ApiOperation(value = "Register User", notes = "Save users")
	@CrossOrigin
	@RequestMapping(value = "/common/user/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception {
		System.out.println("User is " + user);
		User savedUser = userDetailsService.registerUser(user);
		
		
		return new ResponseEntity<>(
				"Thank you for your Registraton successfully", HttpStatus.OK);

	}

	
	
}
