package com.fantasy.nfldatafetcher.model.basicstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIBasicStats {
	
	private String id;
	private String name;
	private String position;
	private String teamAbbr;
	private double seasonPts;
	private double weekPts;
	
	public APIBasicStats() {}
	
	public APIBasicStats(String id, String name, String position, String teamAbbr, double seasonPts, double weekPts) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.teamAbbr = teamAbbr;
		this.seasonPts = seasonPts;
		this.weekPts = weekPts;
	}

	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	@JsonProperty("position")
	public void setPosition(String position) {
		this.position = position;
	}

	public String getTeamAbbr() {
		return teamAbbr;
	}

	@JsonProperty("teamAbbr")
	public void setTeamAbbr(String teamAbbr) {
		this.teamAbbr = teamAbbr;
	}

	public double getSeasonPts() {
		return seasonPts;
	}

	@JsonProperty("seasonPts")
	public void setSeasonPts(double seasonPts) {
		this.seasonPts = seasonPts;
	}

	public double getWeekPts() {
		return weekPts;
	}

	@JsonProperty("weekPts")
	public void setWeekPts(double weekPts) {
		this.weekPts = weekPts;
	}
	
	
	
}
