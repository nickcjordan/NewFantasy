package com.fantasy.dbmanager.playerstatsapi.api;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.to.PlayerStatsAPIResponse;
import com.fantasy.dbmanager.playerstatsapi.builder.DataFileCache;
import com.fantasy.dbmanager.playerstatsapi.builder.PlayerMapBuilder;

@Component
public class PlayerStatsAPIAdapter {

	private static Logger log = Logger.getLogger(PlayerStatsAPIAdapter.class);

	@Value("${data.useCache}")
	private boolean useCachedData;

	@Autowired
	private DataFileCache cache;

	@Autowired
	private PlayerMapBuilder playerBuilder;

	public PlayerStatsAPIResponse getAllPlayerStats() {
		Map<String, Player> players = null;
		if (useCachedData) { 	// return cached data
			log.info("Using cached data...");
			players = cache.getCachedData();
		} else {						// build and return updated data
			log.info("Making calls to get updated data...");
			players = playerBuilder.buildAndPopulatePlayerMap();
			cache.updateCacheWithData(players);
		}
		return new PlayerStatsAPIResponse(players);
	}

}
