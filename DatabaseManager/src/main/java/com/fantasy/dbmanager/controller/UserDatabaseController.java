package com.fantasy.dbmanager.controller;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.to.UserTO;
import com.fantasy.dataaccessutility.model.ui.EditLineupQuery;
import com.fantasy.dataaccessutility.model.ui.EditLineupRequest;
import com.fantasy.dataaccessutility.model.ui.UserName;
import com.fantasy.dbmanager.manager.UserDatabaseManager;
import com.fantasy.dbmanager.processor.EditLineupRequestProcessor;

@RestController
@RequestMapping("/user")
public class UserDatabaseController  {
	
	private static Logger log = Logger.getLogger(UserDatabaseController.class);
	
	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private EditLineupRequestProcessor processor;
	
    @RequestMapping("/count")
    public ResponseEntity<Integer> count() {
    	log.info("DatabaseManager :: getting user count...");
    	long count = userManager.count();
    	log.info("DatabaseManager :: SUCCESS :: count was [" + count + "]");
    	HttpHeaders headers = new HttpHeaders();
//    	headers.add("Access-Control-Allow-Origin", "*");
    	return new ResponseEntity<Integer>(new Integer("1"), headers, HttpStatus.OK);
//    	return count;
    }
    
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public boolean putUser(@RequestBody List<User> users) {
    	log.info("DatabaseManager :: putting " + users.size() + " users in database...");
    	userManager.putAll(users);
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
    
    @RequestMapping(value = "/updateAll", method = RequestMethod.POST)
    public boolean updateUsers(@RequestBody List<User> users) {
    	log.info("DatabaseManager :: updating " + users.size() + " users in database...");
    	userManager.updateAll(users);
    	log.info("DatabaseManager :: SUCCESS :: updated " + users.size() + " users");
    	return true;
    }
    
    @RequestMapping("/getAll")
    public List<User> getAll() {
    	log.info("DatabaseManager :: getting all users...");
    	List<User> users = userManager.getAll();
    	log.info("DatabaseManager :: SUCCESS :: got [" + users.size() + "] users");
    	return users;
    }
    
    @RequestMapping("/getAllTO")
    public List<UserTO> getAllTO() {
    	log.info("DatabaseManager :: getting all user TOs...");
    	List<UserTO> users = userManager.getAllTO();
    	log.info("DatabaseManager :: SUCCESS :: got [" + users.size() + "] users");
    	return users;
    }
    
    @RequestMapping("/getAllUserNames")
    public ResponseEntity<List<UserName>> getAllUserNames() {
    	log.info("DatabaseManager :: getting all users...");
    	List<UserName> users = userManager.getAllUserNames();
    	log.info("DatabaseManager :: SUCCESS :: got [" + users.size() + "] users");
//    	return users;
    	HttpHeaders headers = new HttpHeaders();
//    	headers.add("Access-Control-Allow-Origin", "*");
    	return new ResponseEntity<List<UserName>>(users, headers, HttpStatus.OK);
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
    
    @RequestMapping("/getTO/{val}")
    public UserTO getTO(@PathVariable String userId) {
    	log.info("DatabaseManager :: getting user [" + userId + "]...");
    	UserTO user = userManager.getTO(userId);
		log.info("DatabaseManager :: SUCCESS :: got user [" + user.getUserName() + "]");
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
	
	@RequestMapping(value = "/lineup/swappable", method = RequestMethod.POST)
    public List<Player> build(@RequestBody EditLineupQuery query) {
    	log.info("DatabaseManager :: Getting players eligible for swap :: user: " + query.getUserId() + ", player: " + query.getPlayerId());
    	List<Player> swappables = processor.getLineupOptions(query);
    	StringBuilder b = new StringBuilder("Swappable players found: ");
    	for (Player p : swappables) {
    		b.append(p.getPlayerName() + "  ");
    	}
    	log.info(b.toString());
    	return swappables;
    }

}