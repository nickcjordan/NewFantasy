package com.fantasy.dbmanager.fetcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

@Component
public class StatsFetcherDelegate {

	public Map<String, NFLPlayerSeasonStats> getUpdatedStats() {
		String json = getJson("/nfl/getAllPlayerData");
		Map<String, NFLPlayerSeasonStats> map = null;
		TypeReference<HashMap<String, NFLPlayerSeasonStats>> typeRef = new TypeReference<HashMap<String, NFLPlayerSeasonStats>>() {};
		try {
			map = new ObjectMapper().readValue(json, typeRef);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private String getJson(String url) {
		  try{
				UserAgent userAgent = new UserAgent();
				userAgent.sendGET(url);
				String json = userAgent.json.toString();
				return json;
			}
			catch(JauntException e){
				System.err.println(e);
				return null;
			}
	}
}
