package com.fantasy.simulator.data;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.simulator.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

@Component
public class PlayerDataDelegate {
	
	private static Logger log = Logger.getLogger(PlayerDataDelegate.class);
	
	private static final String PLAYER_DATA_API_PATH = "http://localhost:8080/player/getAll/";
	
	public List<Player> getPlayersByPosition(String pos) {
		List<Player> players = null;
		String json = getJson(PLAYER_DATA_API_PATH + pos);
		try {
			players = new ObjectMapper().readValue(json, new TypeReference<List<Player>>() {});
		} catch (IOException e) {
			log.error("ERROR mapping seasonStatsMap from rest call response for position [" + pos + "]", e);
		}
		return players;
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
