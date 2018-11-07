package com.fantasy.ui.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIController {

	private static Logger log = Logger.getLogger(UIController.class);
	
	
	@RequestMapping("/test")
	public String test() {
		return "test passed";
	}
	
	
//	@Autowired
//	@Qualifier("authStatus")
//	private Map<String, Boolean> authStatus;

//	@RequestMapping("/resource")
//	public Map<String, Object> home(UsernamePasswordAuthenticationToken auth) {
//		log.info("entering GET /resource");
//		Map<String, Object> model = new HashMap<String, Object>();
//
//		User u = (User) auth.getPrincipal();
//
//		model.put("user", u);
//		model.put("greeting", "authenticated gooood");
//
//		log.info("details: " + auth.getDetails());
//
//		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		// String username = auth.getName();
//		// Object credentials = auth.getCredentials();
//
//		return model;
//	}

	/*@RequestMapping("/user")
	public Principal user(UsernamePasswordAuthenticationToken user) {
		log.info("username: " + user.getName());
		log.info("authenticated: " + user.isAuthenticated());
		log.info("details: " + user.getDetails());
		return user;
	}*/

//	@RequestMapping("/")
//	public String home() {
//		return "http://localhost:4200";
//	}
	
	/*@PreAuthorize("hasRole('USER')")
	@RequestMapping("/auth")
	public Map<String, Object> auth(UsernamePasswordAuthenticationToken auth) {
		log.info("entering GET /auth");
		Map<String, Object> model = new HashMap<String, Object>();

		User u = (User) auth.getPrincipal();

		model.put("user", u);
		model.put("username", auth.getName());
		model.put("authenticated", auth.isAuthenticated());
		model.put("authentication", auth);
		
//		authStatus
		
		log.info("details: " + auth.getDetails());

		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// String username = auth.getName();
		// Object credentials = auth.getCredentials();

		return model;
	}*/

}
