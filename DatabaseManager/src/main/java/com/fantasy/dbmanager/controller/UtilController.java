package com.fantasy.dbmanager.controller;



import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.manager.UserDatabaseManager;
import com.fantasy.dbmanager.populator.DatabasePopulator;
import com.fantasy.dbmanager.populator.ModifierDatabasePopulator;

@RestController
@RequestMapping("/util")
public class UtilController  {
	
	private static Logger log = Logger.getLogger(UtilController.class);
	
	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private PlayerDatabaseManager playerManager;

	@Autowired
	private DatabasePopulator populator;
	
	@Autowired
	private ModifierDatabasePopulator modifierPopulator;
	
	
	@RequestMapping(value = "/populate", method = RequestMethod.GET)
	public boolean populate() {
		populator.populate();
		return true;
	}
	
	@RequestMapping(value = "/populate/modifiers", method = RequestMethod.GET)
	public boolean populateModifiers() {
		log.info("Populating modifiers...");
		modifierPopulator.populate();
		log.info("SUCCESS :: populating modifiers");
		return true;
	}
	
	@RequestMapping(value = "/clearAndPopulate", method = RequestMethod.GET)
	public boolean clearAndPopulate() {
		log.info("Clearing Player Database...");
		playerManager.removeAllPlayersFromDB();
		log.info("Clearing User Database...");
		userManager.clear();
		log.info("Updating Player Database with updated stats...");
		playerManager.updateAll();
		log.info("Generating new User test data and populating User Database...");
		populator.populate();
		log.info("SUCCESS :: databases have been wiped and reset with test data");
		return true;
	}
	
    @RequestMapping("/printStat")
    public boolean printStat() {
    	for (Player player : playerManager.getAll()) {
    		System.out.println(player.getPlayerName() + "," + player.getImageUrl() + "," + player.getSmallImageUrl());
    	}
    	return true;
    }
    
    @RequestMapping("/downloadImages")
    public boolean downloadPlayerImages() {
    	for (Player player : playerManager.getAll()) {
    		log.info("Downloading image of " + player.getPlayerName() + " at " + player.getImageUrl());
    		try(InputStream in = new URL(player.getImageUrl()).openStream()){
    			String path = "C:/dev/NewFantasy/images/players/" + player.getPlayerName().replace(" ", "_") + "_photo.jpg";
    			File f = new File(path);
    		    Files.copy(in, f.toPath());
    		    log.info("Successfully downloaded image of " + player.getPlayerName() + " at " + player.getImageUrl());
    		} catch (Exception e) {
    			log.error("ERROR downloading image of " + player.getPlayerName() + " at " + player.getImageUrl());
    		}
    	}
    	return true;
    }

}