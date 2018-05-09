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
@RequestMapping("/player")
public class PlayerDatabaseController  {
	
	private static Logger log = Logger.getLogger(PlayerDatabaseController.class);
	
	@Autowired
	private PlayerDatabaseManager playerManager;

    @RequestMapping("/count")
    public long count() {
    	log.info("DatabaseManager :: getting player count...");
    	long count = playerManager.countPlayers();
    	log.info("DatabaseManager :: success :: count was [" + count + "]");
    	return count;
    }
    
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public boolean putPlayer(@RequestBody List<Player> players) {
    	log.info("DatabaseManager :: putting " + players.size() + " players in database...");
    	playerManager.putPlayersInDB(players);
    	log.info("DatabaseManager :: success :: put " + players.size() + " players in database");
    	return true;
    }
    
    @RequestMapping("/get")
    public List<Player> getAll() {
    	log.info("DatabaseManager :: getting all players...");
    	List<Player> players = playerManager.getAll();
    	log.info("DatabaseManager :: success :: got [" + players.size() + "] players");
    	return players;
    }
    
    @RequestMapping("/clear")
    public boolean removeAllPlayers() {
    	log.info("DatabaseManager :: removing all players from database...");
    	boolean success = playerManager.removeAllPlayersFromDB();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }
    
    @RequestMapping("/update")
    public boolean updateAllPlayers() {
    	log.info("DatabaseManager :: updating all players in database...");
    	boolean success = playerManager.updateAllPlayers();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }

}