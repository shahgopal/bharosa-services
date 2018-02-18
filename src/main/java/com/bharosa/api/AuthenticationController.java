package com.bharosa.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharosa.model.User;
import com.bharosa.model.AuthenticationRequest;
import com.bharosa.model.AuthenticationResponse;
import com.bharosa.security.oauth.TokenUtils;
//TODO 

@RestController
@RequestMapping("${com.bharosa.route.authentication}")
public class AuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Value("${com.bharosa.token.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "${com.bharosa.route.authentication.auth}", method = RequestMethod.POST)
	public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest,
			Device device) throws AuthenticationException {

		logger.info("Executing Authentication");
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		logger.info("get UserDetails post-authentication so we can generate token ");
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.tokenUtils.generateToken(userDetails, device);
		logger.info("returning token="+token);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@RequestMapping(value = "${com.bharosa.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);
		// TODO Check if this token can be refreshed if yes return refresh the token
		String refreshedToken = this.tokenUtils.refreshToken(token);
		return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
	}
	

	@RequestMapping(value = "${com.bharosa.route.authentication.logout}", method = RequestMethod.GET)
	public ResponseEntity<?> logoutRequest(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader);
		this.tokenUtils.addTokenToBlackList(token);
		return ResponseEntity.ok("");
	}

	
	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<?> handleOptions() {
		return (ResponseEntity<?>) ResponseEntity.ok();
	}	

}
