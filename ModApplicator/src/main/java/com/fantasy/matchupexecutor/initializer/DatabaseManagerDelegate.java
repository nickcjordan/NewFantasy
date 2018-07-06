package com.fantasy.matchupexecutor.initializer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fantasy.matchupexecutor.model.Player;

@Component
public class DatabaseManagerDelegate {

	private static Logger log = Logger.getLogger(DatabaseManagerDelegate.class);
	
	private static final String DATABASE_MANAGER_API_PATH = "http://localhost:8080/player/get/";

	public Player getUpdatedPlayer(Player player) {
		log.debug("Getting updated stats for " + player);
		Player updated = new RestTemplate().postForObject(DATABASE_MANAGER_API_PATH + player.getIdentifier(), player, Player.class);
		return updated == null ? player : updated;
	}
	
}
