package com.fantasy.ui.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIController {

	private static Logger log = Logger.getLogger(UIController.class);

	@RequestMapping("/resource")
	public Map<String, Object> home(UsernamePasswordAuthenticationToken auth) {
		log.info("entering GET /resource");
		Map<String, Object> model = new HashMap<String, Object>();

		User u = (User) auth.getPrincipal();

		model.put("user", u);
		model.put("greeting", "authenticated gooood");

		log.info("details: " + auth.getDetails());

		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// String username = auth.getName();
		// Object credentials = auth.getCredentials();

		return model;
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/")
	public String home() {
		return "http://localhost:4200";
	}

}
