package com.gonza.security.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HttpController {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userhome(Locale locale, Model model, Principal principal) {
		String name = "anonymous";
		if(principal != null){
			name = principal.getName();
		}
		
		logger.info("Welcome home " + name +"! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("pageName", "home" );
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public String publicSite(Locale locale, Model model, Principal principal) {
		String name = "anonymous";
		if(principal != null){
			name = principal.getName();
		}
		
		logger.info("Welcome public " + name +"! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("pageName", "public" );
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userSite(Locale locale, Model model, Principal principal) {
		String name = "anonymous";
		if(principal != null){
			name = principal.getName();
		}
		
		logger.info("Welcome user " + name +"! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("pageName", "user" );
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminSite(Locale locale, Model model, Principal principal) {
		String name = "anonymous";
		if(principal != null){
			name = principal.getName();
		}
		
		logger.info("Welcome admin " + name +"! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("pageName", "admin" );
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Locale locale, Model model, Principal principal) {
		String name = "anonymous";
		if(principal != null){
			name = principal.getName();
		}
		
		logger.info("Welcome to login page " + name +"! The client locale is {}.", locale);
		
		return "login";
	}
	
	@RequestMapping(value = "/ajaxTests", method = RequestMethod.GET)
	public String ajaxTests(Locale locale, Model model, Principal principal) {
		String name = "anonymous";
		if(principal != null){
			name = principal.getName();
		}
		
		logger.info("Welcome to ajaxTests page " + name +"! The client locale is {}.", locale);
		
		return "ajaxTests";
	}
}
