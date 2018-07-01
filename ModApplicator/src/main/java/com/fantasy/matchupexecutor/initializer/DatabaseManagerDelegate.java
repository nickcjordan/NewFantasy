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
		Player updated = null;
		log.debug("Getting updated stats for " + player);
		String url = DATABASE_MANAGER_API_PATH + player.getIdentifier();
		updated = new RestTemplate().postForObject(url, player, Player.class);
		return updated == null ? player : updated;
	}
	
}
