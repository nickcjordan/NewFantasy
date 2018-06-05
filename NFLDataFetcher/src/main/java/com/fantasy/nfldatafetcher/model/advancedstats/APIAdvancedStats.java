package com.fantasy.nfldatafetcher.model.advancedstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIAdvancedStats {

	private String id;
	private String firstName;
	private String lastName;
	private String opponentTeamAbbr;
	private APIAdvancedStatsDetails stats;
	
	public String getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}
	public String getOpponentTeamAbbr() {
		return opponentTeamAbbr;
	}
	@JsonProperty("opponentTeamAbbr")
	public void setOpponentTeamAbbr(String opponentTeamAbbr) {
		this.opponentTeamAbbr = opponentTeamAbbr;
	}
	public APIAdvancedStatsDetails getStats() {
		return stats;
	}
	@JsonProperty("stats")
	public void setStats(APIAdvancedStatsDetails stats) {
		this.stats = stats;
	}
	public String getFirstName() {
		return firstName;
	}
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
