package com.fantasy.playerstatsapi.api;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.playerstatsapi.model.Player;
import com.fantasy.playerstatsapi.model.PlayerStatsAPIResponse;

@RestController
@RequestMapping("/nfl")
public class PlayerStatsAPIController {
	
	private static Logger log = Logger.getLogger(PlayerStatsAPIController.class);
	
	@Autowired
	private PlayerStatsAPIAdapter adapter;
	
    @RequestMapping("/getUpdatedPlayers")
    public PlayerStatsAPIResponse getAllPlayerStats() {
    	log.info("PlayerStatsAPI :: getting player stats...");
    	PlayerStatsAPIResponse response = adapter.getAllPlayerStats();
    	log.info("PlayerStatsAPI :: success :: got data for [" + response.getPlayers().size() + "] players");
    	return response;
    }

}