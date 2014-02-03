package com.gonza.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RpcAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private RequestMatcher requestMatcher = new ELRequestMatcher("hasHeader('X-Requested-With','XMLHttpRequest')");

	public void setRequestMatcher(RequestMatcher requestMatcher) {
		this.requestMatcher = requestMatcher;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		System.out.println("#### RpcAuthenticationFailureHandler.onAuthenticationFailure ####");
		
		if(isRpcRequest(request, response)) {
			System.out.println("is an ajax call. returning 401");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}else {
			System.out.println("is an http call redirting");
			super.onAuthenticationFailure(request, response, exception);
		}
	}

	protected boolean isRpcRequest(HttpServletRequest request, HttpServletResponse response) {
		return requestMatcher.matches(request);
	}


}
