package com.fantasy.dbmanager.controller;



import java.util.ArrayList;
import org.apache.log4j.Logger;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.model.Player;

@RestController
public class DatabaseController  {
	
	private static Logger log = Logger.getLogger(DatabaseController.class);
	
	@Autowired
	private PlayerDatabaseManager playerManager;

    @RequestMapping("/getPlayerCount")
    public long count() {
    	log.info("DatabaseManager :: DatabaseController :: getting player count...");
    	long count = playerManager.countPlayers();
    	log.info("DatabaseManager :: DatabaseController :: success :: count was [" + count + "]");
    	return count;
    }
    
    @RequestMapping(value = "/putPlayers", method = RequestMethod.POST)
    public boolean putPlayer(@RequestBody List<Player> players) {
    	log.info("DatabaseManager :: DatabaseController :: putting " + players.size() + " players in database...");
    	playerManager.putPlayersInDB(players);
    	log.info("DatabaseManager :: DatabaseController :: success :: put " + players.size() + " players in database");
    	return true;
    }
    
    @RequestMapping("/getAllPlayers")
    public List<Player> getAll() {
    	log.info("DatabaseManager :: DatabaseController :: getting all players...");
    	List<Player> players = playerManager.getAll();
    	log.info("DatabaseManager :: DatabaseController :: success :: got [" + players.size() + "] players");
    	return players;
    }
    
    @RequestMapping("/removeAllPlayers")
    public boolean removeAllPlayers() {
    	log.info("DatabaseManager :: DatabaseController :: removing all players from database...");
    	boolean success = playerManager.removeAllPlayersFromDB();
    	log.info("DatabaseManager :: DatabaseController :: success = " + success);
    	return success;
    }

}