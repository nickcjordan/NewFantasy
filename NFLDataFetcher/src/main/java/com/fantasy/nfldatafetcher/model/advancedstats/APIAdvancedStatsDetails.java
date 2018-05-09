package com.fantasy.nfldatafetcher.model.advancedstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIAdvancedStatsDetails {
	
	private String fanPtsAgainstOpponentPts;
	private String fanPtsAgainstOpponentRank;
	private String carries;
	private String touches;
	private String receptions;
	private String targets;
	
	public APIAdvancedStatsDetails() {}
	
	public APIAdvancedStatsDetails(String fanPtsAgainstOpponentPts, String fanPtsAgainstOpponentRank,
			String carries, String touches, String receptions, String targets) {
		this.fanPtsAgainstOpponentPts = fanPtsAgainstOpponentPts;
		this.fanPtsAgainstOpponentRank = fanPtsAgainstOpponentRank;
		this.carries = carries;
		this.touches = touches;
		this.receptions = receptions;
		this.targets = targets;
	}
	public String getFanPtsAgainstOpponentPts() {
		return fanPtsAgainstOpponentPts;
	}
	@JsonProperty("FanPtsAgainstOpponentPts")
	public void setFanPtsAgainstOpponentPts(String fanPtsAgainstOpponentPts) {
		this.fanPtsAgainstOpponentPts = fanPtsAgainstOpponentPts;
	}
	
	public String getFanPtsAgainstOpponentRank() {
		return fanPtsAgainstOpponentRank;
	}
	@JsonProperty("FanPtsAgainstOpponentRank")
	public void setFanPtsAgainstOpponentRank(String fanPtsAgainstOpponentRank) {
		this.fanPtsAgainstOpponentRank = fanPtsAgainstOpponentRank;
	}
	
	public String getCarries() {
		return carries;
	}
	@JsonProperty("Carries")
	public void setCarries(String carries) {
		this.carries = carries;
	}
	
	public String getTouches() {
		return touches;
	}
	@JsonProperty("Touches")
	public void setTouches(String touches) {
		this.touches = touches;
	}
	
	public String getReceptions() {
		return receptions;
	}
	@JsonProperty("Receptions")
	public void setReceptions(String receptions) {
		this.receptions = receptions;
	}
	
	public String getTargets() {
		return targets;
	}
	@JsonProperty("Targets")
	public void setTargets(String targets) {
		this.targets = targets;
	}

}
