package com.fantasy.dbmanager.fetcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.dao.StatsDao;
import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.JauntException;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

@Component
public class StatsFetcherDelegate {
	
	private static Logger log = Logger.getLogger(StatsFetcherDelegate.class);
	
	private static final String NFL_FETCHER_API_PATH = "http://localhost:8081/nfl/getAllPlayerData";

	public Map<String, NFLPlayerSeasonStats> getUpdatedStats() {
		Map<String, NFLPlayerSeasonStats> map = null;
		String json = getJson(NFL_FETCHER_API_PATH);
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
