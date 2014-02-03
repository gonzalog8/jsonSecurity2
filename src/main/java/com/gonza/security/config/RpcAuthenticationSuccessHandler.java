package com.gonza.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RpcAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RequestMatcher requestMatcher = new ELRequestMatcher("hasHeader('X-Requested-With','XMLHttpRequest')");

	public void setRequestMatcher(RequestMatcher requestMatcher) {
		this.requestMatcher = requestMatcher;
	}

	/**
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("#### RpcAuthenticationSuccessHandler.onAuthenticationSuccess ####");
		
		if(isRpcRequest(request, response, authentication)) {
			System.out.println("is an ajax call. returning 200");
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().flush();
		}else {
			System.out.println("is an http call redirting");
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

	protected boolean isRpcRequest(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		return requestMatcher.matches(request);
	}
}