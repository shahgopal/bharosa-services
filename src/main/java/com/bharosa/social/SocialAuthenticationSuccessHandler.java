//package com.bharosa.social;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SocialAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    public static final String REDIRECT_PATH_BASE = "/#/login";
//    public static final String FIELD_TOKEN = "access_token";
//    public static final String FIELD_EXPIRATION_SECS = "expires_in";
//
//    private final AuthorizationServerTokenServices authTokenServices;
//    private final String localClientId;
//
//    public SocialAuthenticationSuccessHandler(AuthorizationServerTokenServices authTokenServices, String localClientId){
//        this.authTokenServices = authTokenServices;
//        this.localClientId = localClientId;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//            HttpServletResponse response, Authentication authentication)
//                    throws IOException, ServletException {
//    	  System.out.println("Social user authenticated: " + authentication.getPrincipal() + ", generating and sending local auth");
//        response.sendRedirect("http://localhost:3000/auth");
//    }
//
//    
//
////    private String encode(String in){
////        String res = in;
////        try {
////            res = UriUtils.encode(in, GeneralConstants.ENCODING_UTF8);
////        } catch(UnsupportedEncodingException e){
////            log.error("ERROR: unsupported encoding: " + GeneralConstants.ENCODING_UTF8, e);
////        }
////        return res;
////    }
//}