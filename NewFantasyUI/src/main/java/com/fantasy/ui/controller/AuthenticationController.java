package com.fantasy.ui.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.ui.auth.Authenticator;
import com.fantasy.ui.auth.model.UserCredential;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationController {
	
	private static Logger log = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private Authenticator auth;
	
	private HttpHeaders headers;

	public AuthenticationController() {
		headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST, produces= {"application/json"}, consumes= {"text/plain", "application/json", "application/json;charset=UTF-8"})
	public ResponseEntity<UserCredential> authenticateUser(@RequestBody UserCredential user) {
		log.info("START /auth/authenticate :: POST :: authenticating user: " + user.getUsername());
		if (auth.isAuthorizedUser(user)) {
			log.info("END /auth/authenticate :: SUCCESS :: user has been authenticated: " + user.getUsername());
			return new ResponseEntity<UserCredential>(auth.getRegisteredUser(user.getUsername()), headers, HttpStatus.OK);
		} else {
			log.info("END /auth/authenticate :: FAILURE :: user could not be authenticated: " + user.getUsername());
			return new ResponseEntity<UserCredential>(user, headers, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<UserCredential>> getAllUsers() {
		log.info("START /auth/users :: GET :: getting all users");
		return new ResponseEntity<List<UserCredential>>(auth.getRegisteredUsers(), headers, HttpStatus.OK);
	}
	
    @RequestMapping(value="/users/{username}", method=RequestMethod.GET)
    public  ResponseEntity<UserCredential> getUser(@PathVariable String username) {
    	log.info("START /auth/users/" + username + " :: GET :: getting user with username: " + username);
    	UserCredential cred = auth.getRegisteredUser(username);
    	if (cred != null) {
    		log.info("END /auth/users/" + username + " :: SUCCESS :: returning registered user: " + cred.getUsername());
    		return new ResponseEntity<UserCredential>(cred, headers, HttpStatus.OK);
    	} else {
    		log.info("END /auth/users/" + username + " :: FAILURE :: could not get user with username: " + username);
    		return new ResponseEntity<UserCredential>(headers, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(value="/users/register", method=RequestMethod.POST)
	public ResponseEntity<Void> registerUser(UserCredential user) {
		log.info("START /auth/users/register :: POST :: registering new user: " + user.getUsername());
		if (auth.registerNewUser(user)) {
			log.info("END /auth/users/register :: SUCCESS :: user has been registered: " + user.getUsername());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} else {
			log.info("END /auth/users/register :: FAILURE :: user could not be registered: " + user.getUsername());
			return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
		}
	}
    
    @RequestMapping(value="/users/{username}", method=RequestMethod.DELETE)
    public  ResponseEntity<Void> deleteUser(@PathVariable String username) {
    	log.info("START /auth/users/" + username + " :: DELETE :: deleting user with username: " + username);
    	if (auth.deleteUser(username)) {
    		log.info("END /auth/users/" + username + " :: SUCCESS :: deleted user with username: " + username);
    		return new ResponseEntity<Void>(headers, HttpStatus.OK);
    	} else {
    		log.info("END /auth/users/" + username + " :: FAILURE :: could not delete user with username: " + username);
    		return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
    	}
    }

}
