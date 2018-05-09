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
import com.fantasy.dbmanager.manager.TeamDatabaseManager;
import com.fantasy.dbmanager.model.Player;
import com.fantasy.dbmanager.model.Team;

@RestController
@RequestMapping("/team")
public class TeamDatabaseController  {
	
	private static Logger log = Logger.getLogger(TeamDatabaseController.class);
	
	@Autowired
	private TeamDatabaseManager teamManager;

    @RequestMapping("/count")
    public long count() {
    	log.info("DatabaseManager :: getting team count...");
    	long count = teamManager.count();
    	log.info("DatabaseManager :: success :: count was [" + count + "]");
    	return count;
    }
    
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public boolean putTeam(@RequestBody List<Team> teams) {
    	log.info("DatabaseManager :: putting " + teams.size() + " teams in database...");
    	teamManager.put(teams);
    	log.info("DatabaseManager :: success :: put " + teams.size() + " teams in database");
    	return true;
    }
    
    @RequestMapping("/get")
    public List<Team> getAll() {
    	log.info("DatabaseManager :: getting all teams...");
    	List<Team> teams = teamManager.get();
    	log.info("DatabaseManager :: success :: got [" + teams.size() + "] teams");
    	return teams;
    }
    
    @RequestMapping("/clear")
    public boolean removeAllTeams() {
    	log.info("DatabaseManager :: removing all teams from database...");
    	boolean success = teamManager.clear();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }

}