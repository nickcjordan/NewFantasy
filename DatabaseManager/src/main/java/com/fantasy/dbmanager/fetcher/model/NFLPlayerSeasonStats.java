package com.fantasy.dbmanager.fetcher.model;

import java.util.HashMap;
import java.util.Map;

public class NFLPlayerSeasonStats {
	
	private String id;
	private String name;
	private String position;
	private String teamAbbr;
	private Map<String, NFLPlayerWeeklyStats> weeklyStats;
	private double seasonTotalPoints;
	
	public NFLPlayerSeasonStats() {
		this.weeklyStats = new HashMap<String, NFLPlayerWeeklyStats>();
	}
	
	public NFLPlayerSeasonStats(String id, String name, String position, String teamAbbr) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.teamAbbr = teamAbbr;
		this.weeklyStats = new HashMap<String, NFLPlayerWeeklyStats>();
	}
	
	public Map<String, NFLPlayerWeeklyStats> getWeeklyStats() {
		return weeklyStats;
	}
	public void setWeeklyStats(Map<String, NFLPlayerWeeklyStats> weeklyStats) {
		this.weeklyStats = weeklyStats;
	}
	public void addWeeklyStats(NFLPlayerWeeklyStats stat) {
		weeklyStats.put(Integer.toString(stat.getWeekNumber()), stat);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTeamAbbr() {
		return teamAbbr;
	}
	public void setTeamAbbr(String teamAbbr) {
		this.teamAbbr = teamAbbr;
	}
	public double getSeasonTotalPoints() {
		return seasonTotalPoints;
	}
	public void setSeasonTotalPoints(double points) {
		this.seasonTotalPoints = points;
	}
}
