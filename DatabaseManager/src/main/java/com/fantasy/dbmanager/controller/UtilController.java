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
import com.fantasy.dbmanager.populator.DatabasePopulator;

@RestController
@RequestMapping("/util")
public class UtilController  {
	
	private static Logger log = Logger.getLogger(UtilController.class);
	
//	@Autowired
//	private UserDatabaseManager userManager;
	
//	@Autowired
//	private TeamDatabaseManager teamManager;
	
	@Autowired
	private PlayerDatabaseManager playerManager;
	
	@Autowired
	private DatabasePopulator populator;
	
	@RequestMapping(value = "/populate", method = RequestMethod.GET)
	public boolean populate() {
		populator.populate();
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