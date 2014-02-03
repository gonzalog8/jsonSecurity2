package com.gonza.security.controller;

import java.security.Principal;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gonza.security.config.LoginStatus;
import com.gonza.security.dto.UserDetailsDto;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/json")
public class JsonController {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonController.class);
	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@RequestMapping(value = "/publicUserDetails", method = RequestMethod.GET)
	public ResponseEntity<UserDetailsDto> userDetails(Locale locale,
			Model model, Principal principal) {
		String name = "anonymous";
		if (principal != null) {
			name = principal.getName();
		}

		System.out.println("publicUserDetails");

		UserDetailsDto dto = new UserDetailsDto();
		dto.setUserName(name);
		dto.setUserEmail("pepe@bla.com");
		dto.setUserAge(new Integer(34));

		return new ResponseEntity<UserDetailsDto>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/privateUserDetails", method = RequestMethod.GET)
	public ResponseEntity<UserDetailsDto> privateUserDetails(Locale locale,
			Model model, Principal principal) {
		String name = "anonymous";
		if (principal != null) {
			name = principal.getName();
		}

		System.out.println("privateUserDetails");

		UserDetailsDto dto = new UserDetailsDto();
		dto.setUserName(name);
		dto.setUserEmail("pepe@bla.com");
		dto.setUserAge(new Integer(34));

		return new ResponseEntity<UserDetailsDto>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginStatus> login(@ModelAttribute("j_username") String username,
			                                 @ModelAttribute("j_password") String password) {
//		String username = "gonza";
//		String password = "1234";
		
		System.out.println("JSON LOGIN - usr: " + username + " psw: " + password);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		UserDetailsDto details = new UserDetailsDto();
		details.setUserName(username);
		details.setUserEmail("bla@bla.com");
		details.setUserAge(35);
		token.setDetails(details);

		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			LoginStatus status = new LoginStatus(auth.isAuthenticated(), auth.getName());
			
			return new ResponseEntity<LoginStatus>(status, HttpStatus.OK);
			
		} catch (BadCredentialsException e) {
			LoginStatus status = new LoginStatus(false, "anonymous");
			return new ResponseEntity<LoginStatus>(status, HttpStatus.OK);
		}
	}
}
