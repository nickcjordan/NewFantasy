package com.fantasy.simulator.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.simulator.data.model.NFLPlayerSeasonStats;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

@Component
public class PlayerDataDelegate {
	
	private static Logger log = Logger.getLogger(PlayerDataDelegate.class);
	
	private static final String PLAYER_DATA_API_PATH = "http://localhost:8080/player/getAll";

	public Map<String, NFLPlayerSeasonStats> getUpdatedStats() {
		Map<String, NFLPlayerSeasonStats> map = null;
		String json = getJson(PLAYER_DATA_API_PATH);
		try {
			map = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, NFLPlayerSeasonStats>>() {});
		} catch (IOException e) {
			log.error("ERROR mapping seasonStatsMap from rest call response", e);
		}
		return map;
	}
	
	private String getJson(String url) {
		String json = null;
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.sendGET(url);
			json = userAgent.json.toString();
		} catch (ResponseException e) {
			log.error("ERROR calling NFLDataFetcher :: json=" + json, e);
		}
		return json;
	}
}
