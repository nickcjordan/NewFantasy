package com.fantasy.dbmanager.controller;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.ui.EditLineupRequest;
import com.fantasy.dbmanager.manager.UserDatabaseManager;
import com.fantasy.dbmanager.populator.DatabasePopulator;
import com.fantasy.dbmanager.processor.EditLineupRequestProcessor;

@RestController
@RequestMapping("/user")
public class UserDatabaseController  {
	
	private static Logger log = Logger.getLogger(UserDatabaseController.class);
	
	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private DatabasePopulator populator;
	
	@Autowired
	private EditLineupRequestProcessor processor;
	
    @RequestMapping("/count")
    public long count() {
    	log.info("DatabaseManager :: getting user count...");
    	long count = userManager.count();
    	log.info("DatabaseManager :: SUCCESS :: count was [" + count + "]");
    	return count;
    }
    
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public boolean putUser(@RequestBody List<User> users) {
    	log.info("DatabaseManager :: putting " + users.size() + " users in database...");
    	userManager.put(users);
    	log.info("DatabaseManager :: SUCCESS :: put " + users.size() + " users in database");
    	return true;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean updateUser(@RequestBody User user) {
    	log.info("DatabaseManager :: updating user " + user.getUserName() + " in database...");
    	userManager.update(user);
    	log.info("DatabaseManager :: SUCCESS :: updated " + user.getUserName());
    	return true;
    }
    
    @RequestMapping("/getAll")
    public List<User> getAll() {
    	log.info("DatabaseManager :: getting all users...");
    	List<User> users = userManager.get();
    	log.info("DatabaseManager :: SUCCESS :: got [" + users.size() + "] users");
    	return users;
    }
    
    @RequestMapping("/get/{val}")
    public User get(@PathVariable String val) {
    	log.info("DatabaseManager :: getting user [" + val + "]...");
    	User user = userManager.get(val);
    	if (user != null) {
    		log.info("DatabaseManager :: SUCCESS :: got user [" + user.getUserName() + "]");
    	} else {
    		log.error("DatabaseManager :: ERROR :: could not find user \"" + val + "\"");
    	}
    	return user;
    }
    
    @RequestMapping("/clear")
    public boolean removeAllUsers() {
    	log.info("DatabaseManager :: removing all users from database...");
    	boolean success = userManager.clear();
    	log.info("DatabaseManager :: SUCCESS = " + success);
    	return success;
    }
	
	@RequestMapping(value = "/lineup/edit", method = RequestMethod.POST)
    public boolean editLineup(@RequestBody EditLineupRequest editRequest) {
    	log.info("DatabaseManager :: Proccessing edit lineup request :: user: " + editRequest.getUserId() + ", player: " + editRequest.getPlayerId() + ", action: " + editRequest.getAction());
    	processor.editLineup(editRequest);
    	log.info("DatabaseManager :: SUCCESS :: edited lineup :: user: " + editRequest.getUserId() + ", player: " + editRequest.getPlayerId() + ", action: " + editRequest.getAction());
    	return true;
    }

}