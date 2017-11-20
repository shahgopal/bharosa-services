package com.bharosa.social.facebook;

import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialAuthenticationFailureHandler;
import org.springframework.social.security.SocialAuthenticationRedirectException;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bharosa.model.Role;
import com.bharosa.model.SocialMediaService;
import com.bharosa.model.User;
import com.bharosa.services.AppUserDetailsService;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class FacebookTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	@Value("${facebook.app.access_token}")
	private String access_token;

	private static final String providerId = "facebook";

	private SocialAuthenticationServiceLocator authServiceLocator;

	private UserIdSource userIdSource;

	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private AppUserDetailsService service;

	@Autowired TokenStore tokenStore;
	
	private SimpleUrlAuthenticationFailureHandler delegateAuthenticationFailureHandler;

	public FacebookTokenAuthenticationFilter(AuthenticationManager authManager, UserIdSource userIdSource,
			UsersConnectionRepository usersConnectionRepository,
			SocialAuthenticationServiceLocator authServiceLocator) {
		super("/");
		setAuthenticationManager(authManager);
		this.userIdSource = userIdSource;
		this.usersConnectionRepository = usersConnectionRepository;
		this.authServiceLocator = authServiceLocator;
		this.delegateAuthenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
		super.setAuthenticationFailureHandler(
				new SocialAuthenticationFailureHandler(delegateAuthenticationFailureHandler));
		SimpleUrlAuthenticationSuccessHandler sas = new SimpleUrlAuthenticationSuccessHandler();
		sas.setRedirectStrategy(new  NoRedirectStrategy());//TODO May need to redirect 
		super.setAuthenticationSuccessHandler(sas);
	}

	public UsersConnectionRepository getUsersConnectionRepository() {
		return usersConnectionRepository;
	}

	public SocialAuthenticationServiceLocator getAuthServiceLocator() {
		return authServiceLocator;
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("OKAY HERER YOU GO");
		Authentication auth = null;
		Set<String> authProviders = authServiceLocator.registeredAuthenticationProviderIds();
		if (!authProviders.isEmpty() && authProviders.contains(providerId)) {
			SocialAuthenticationService<?> authService = authServiceLocator.getAuthenticationService(providerId);
			auth = attemptAuthService(authService, request, response);
			if (auth == null) {
				throw new AuthenticationServiceException("authentication failed");
			}
		}
		return auth;
	}

	RequestMatcher myMatcher = new AntPathRequestMatcher("/register");
	RequestMatcher myMatcher1 = new AntPathRequestMatcher("/oauth/token");
	RequestMatcher myMatcher2 = new AntPathRequestMatcher("/favicon.ico");
	
	
	@Override
public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse res, javax.servlet.FilterChain chain) throws IOException, ServletException {
		System.out.println("IN THE FILTER111");
//        if (extractHeaderToken((HttpServletRequest)req) != null) {
//    		System.out.println("IN THE FILTER222");
//			chain.doFilter(req, res);
//        }	
//        else 
        	if (myMatcher.matches((HttpServletRequest) req) || myMatcher1.matches((HttpServletRequest) req) || myMatcher2.matches((HttpServletRequest) req)) {
			System.out.println("BYPASS");
			chain.doFilter(req, res);
		}
		else {
			System.out.println("DONOT BYPASS");
			super.doFilter(req, res, chain);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("AUTH DONE111");
		super.successfulAuthentication(request, response, chain, authResult);
		System.out.println("AUTH DONE222");
		chain.doFilter(request, response);
	}

	@Deprecated
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	protected Connection<?> addConnection(SocialAuthenticationService<?> authService, String userId,
			ConnectionData data) {
		HashSet<String> userIdSet = new HashSet<String>();
		userIdSet.add(data.getProviderUserId());
		Set<String> connectedUserIds = usersConnectionRepository.findUserIdsConnectedTo(data.getProviderId(),
				userIdSet);
		if (connectedUserIds.contains(userId)) {
			// already connected
			return null;
		} else if (!authService.getConnectionCardinality().isMultiUserId() && !connectedUserIds.isEmpty()) {
			return null;
		}

		ConnectionRepository repo = usersConnectionRepository.createConnectionRepository(userId);

		if (!authService.getConnectionCardinality().isMultiProviderUserId()) {
			List<Connection<?>> connections = repo.findConnections(data.getProviderId());
			if (!connections.isEmpty()) {
				// TODO maybe throw an exception to allow UI feedback?
				return null;
			}
		}

		// add new connection
		Connection<?> connection = authService.getConnectionFactory().createConnection(data);
		connection.sync();
		repo.addConnection(connection);
		return connection;
	}

	private Authentication attemptAuthService(final SocialAuthenticationService<?> authService,
			final HttpServletRequest request, HttpServletResponse response)
					throws SocialAuthenticationRedirectException, AuthenticationException {
		
		System.out.println("attemptAuthService is called");
		
//		String input_token = extractHeaderToken(request);
		String input_token = request.getParameter("input_token");
		
		if (input_token == null) {
			
			System.out.println("No token in the request");
			throw new SocialAuthenticationException("No token in the request");
		}
		URIBuilder builder = URIBuilder.fromUri(String.format("%s/debug_token", "https://graph.facebook.com"));
		builder.queryParam("access_token", access_token);
		builder.queryParam("input_token", input_token);
		URI uri = builder.build();
		RestTemplate restTemplate = new RestTemplate();

		JsonNode resp = null;
		try {
			resp = restTemplate.getForObject(uri, JsonNode.class);
		} catch (HttpClientErrorException e) {
			System.out.println("Error validating token");
			throw new SocialAuthenticationException("Error validating token");
		}
		Boolean isValid = resp.path("data").findValue("is_valid").asBoolean();
		if (!isValid){
			System.out.println("token is not valid");
			throw new SocialAuthenticationException("Token is not valid");
		}
		
		System.out.println("Made it here");
		
		AccessGrant accessGrant = new AccessGrant(input_token, null, null,
				resp.path("data").findValue("expires_at").longValue());

		Connection<?> connection = ((OAuth2ConnectionFactory<?>) authService.getConnectionFactory())
				.createConnection(accessGrant);
		SocialAuthenticationToken token = new SocialAuthenticationToken(connection, null);
		Assert.notNull(token.getConnection());
		System.out.println("Made it here too");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			System.out.println("Great it here too");
			return doAuthentication(authService, request, token);
		} else {
			System.out.println("K cool it here too");
			addConnection(authService, request, token);
			return null;
		}
	}

	private void addConnection(final SocialAuthenticationService<?> authService, HttpServletRequest request,
			SocialAuthenticationToken token) {
		// already authenticated - add connection instead
		String userId = userIdSource.getUserId();
		Object principal = token.getPrincipal();
		if (userId == null || !(principal instanceof ConnectionData))
			return;

		addConnection(authService, userId, (ConnectionData) principal);

	}

	private Authentication doAuthentication(SocialAuthenticationService<?> authService, HttpServletRequest request,
			SocialAuthenticationToken token) {
		try {
			
			
			System.out.println("SO its here111");
			if (!authService.getConnectionCardinality().isAuthenticatePossible())
			{
				System.out.println("Returning null");
				return null;
			}	
			System.out.println("SO its here222");
			token.setDetails(authenticationDetailsSource.buildDetails(request));
			System.out.println("SO its here333");
			Authentication success=null;
			success = getAuthenticationManager().authenticate(token);
			System.out.println("SO its here444");
			Assert.isInstanceOf(SocialUserDetails.class, success.getPrincipal(), "unexpected principle type");
			System.out.println("SO its 4444");
			updateConnections(authService, token, success);
			
			
			
			return success;
		} catch (BadCredentialsException e) {
			
			User registration = createUserForRegistration(token.getConnection());
			
			
			
			User registered;
			try {
				registered = service.registerUser(registration);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new SocialAuthenticationException("An email address was found from the database." + e1);
			}
			ConnectionRepository repo = usersConnectionRepository.createConnectionRepository(registered.getEmail());
			repo.addConnection(token.getConnection());
			Authentication success = getAuthenticationManager().authenticate(token);
			return success;

		}
	}

	private void updateConnections(SocialAuthenticationService<?> authService, SocialAuthenticationToken token,
			Authentication success) {

		String userId = ((SocialUserDetails) success.getPrincipal()).getUserId();
		Connection<?> connection = token.getConnection();
		ConnectionRepository repo = getUsersConnectionRepository().createConnectionRepository(userId);
		repo.updateConnection(connection);

	}

	private User createUserForRegistration(Connection<?> connection) {
//		RegistrationDTO dto = new RegistrationDTO();
		
		
		
		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
			User user = new User();
			if(socialMediaProfile.getUsername() !=null )
			{
				user.setUsername(socialMediaProfile.getUsername());	
			}	
			else
			{
				user.setUsername(socialMediaProfile.getEmail());
			}	
			user.grantAuthority(Role.ROLE_USER);
			user.setEmail(socialMediaProfile.getEmail());
			user.setFirstName(socialMediaProfile.getFirstName());
			user.setLastName(socialMediaProfile.getLastName());
			ConnectionKey providerKey = connection.getKey();
			user.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
			return user;

		}
		return null;
	}
	
	
	protected String extractHeaderToken(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaders("Authorization");
		System.out.println("headers" + headers);
		while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
			String value = headers.nextElement();
			System.out.println("Token found" + value);
			if ((value.toLowerCase().startsWith("bearer"))) {
				String authHeaderValue = value.substring("bearer".length()).trim();
				System.out.println("authHeaderValue" + authHeaderValue);
				
				int commaIndex = authHeaderValue.indexOf(',');
				if (commaIndex > 0) {
					authHeaderValue = authHeaderValue.substring(0, commaIndex);
					System.out.println("Final authHeaderValue" + authHeaderValue);
					
				}
				System.out.println("authHeaderValue Token found" + authHeaderValue);
				return authHeaderValue;
			}
		}

		return null;
	}

}