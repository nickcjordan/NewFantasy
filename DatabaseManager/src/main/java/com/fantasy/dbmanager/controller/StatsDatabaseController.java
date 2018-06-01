package com.fantasy.dbmanager.controller;



import java.util.ArrayList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.manager.StatsDatabaseManager;
import com.fantasy.dbmanager.model.Player;

@RestController
@RequestMapping("/stats")
public class StatsDatabaseController  {
	
	private static Logger log = Logger.getLogger(StatsDatabaseController.class);
	
	@Autowired
	private StatsDatabaseManager statsManager;
    
    @RequestMapping("/get")
    public Map<String, NFLPlayerSeasonStats> getAll() {
    	log.info("DatabaseManager :: getting all players...");
    	Map<String, NFLPlayerSeasonStats> stats = statsManager.getAll();
    	log.info("DatabaseManager :: success :: got stats for [" + stats.size() + "] players");
    	return stats;
    }
    
    @RequestMapping("/clear")
    public boolean removeAllPlayers() {
    	log.info("DatabaseManager :: removing all stats from database...");
    	boolean success = statsManager.removeAllStatsFromDB();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }
    
    @RequestMapping("/update")
    public boolean updateAllPlayers() {
    	log.info("DatabaseManager :: updating all stats in database...");
    	boolean success = statsManager.updateAllPlayers();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }
    
}