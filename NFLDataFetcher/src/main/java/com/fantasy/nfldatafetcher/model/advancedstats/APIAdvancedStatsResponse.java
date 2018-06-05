package com.fantasy.nfldatafetcher.model.advancedstats;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIAdvancedStatsResponse {
	
	private List<APIAdvancedStats> players;
	
	public APIAdvancedStatsResponse() {}

	public APIAdvancedStatsResponse(List<APIAdvancedStats> players) {
		this.players = players;
	}

	public List<APIAdvancedStats> getPlayers() {
		return players;
	}

	@JsonAlias({ "QB", "RB", "WR", "TE", "K", "DEF" })
	public void setPlayers(List<APIAdvancedStats> players) {
		this.players = players;
	}
	
	//TODO refactor to go by position name for list -- 5 lists -- in the advanced response json that is returned

}
