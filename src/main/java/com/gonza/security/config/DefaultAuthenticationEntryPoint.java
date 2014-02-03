package com.gonza.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@SuppressWarnings("deprecation")
public class DefaultAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
		
		System.out.println("#### DefaultAuthenticationEntryPoint.commence ####");
		
		String contentType = request.getHeader("content-type");
		System.out.println("#### content-type: " + contentType);
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication == null) {
	    	System.out.println("#### DefaultAuthenticationEntryPoint. NO SESSION FOUND " );
	    }else{
	    	System.out.println("#### DefaultAuthenticationEntryPoint. VALID SESSION" );
	    }
		super.commence(request, response, authException);
		
	}

}
