package com.fantasy.dbmanager.fetcher;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fantasy.dataaccessutility.model.to.PlayerStatsAPIResponse;

@Component
public class PlayerStatsAPIDelegate {
	
	private static Logger log = Logger.getLogger(PlayerStatsAPIDelegate.class);
	
	private static final String PLAYER_STATS_API_PATH = "http://localhost:8081/nfl/getUpdatedPlayers";

	public PlayerStatsAPIResponse getUpdatedStats() {
		PlayerStatsAPIResponse response = null;
		try {
			response = new RestTemplate().getForObject(PLAYER_STATS_API_PATH, PlayerStatsAPIResponse.class);
		} catch (Exception e) {
			log.error("ERROR :: calling PlayerStatsAPI for player update", e);
		}
		return response;
	}
	
}
