package com.fantasy.nfldatafetcher.model.basicstats;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIBasicStatsResponse {

	private List<APIBasicStats> players;
	
	public APIBasicStatsResponse() {}

	public APIBasicStatsResponse(List<APIBasicStats> players) {
		this.players = players;
	}

	public List<APIBasicStats> getPlayers() {
		return players;
	}

	@JsonProperty("players")
	public void setPlayers(List<APIBasicStats> players) {
		this.players = players;
	}
	
	
	
}
