package com.gonza.security.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RpcAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		
		System.out.println("#### RpcAuthenticationEntryPoint.commence ####");
		
		String contentType = request.getHeader("content-type");
		System.out.println("#### contentType: " + contentType);
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	    	Principal principal = (Principal) authentication.getPrincipal();
	        System.out.println("#### RpcAuthenticationEntryPoint.commence  principal: " + principal.getName() );
	        response.setStatus(HttpServletResponse.SC_OK);
	    }else{
	    	System.out.println("#### RpcAuthenticationEntryPoint. NO SESSION FOUND " );
	    	//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	    	response.getWriter().print("{\"status\": \"NOT AUTHORIZED\" }");
	    }
	}

}
