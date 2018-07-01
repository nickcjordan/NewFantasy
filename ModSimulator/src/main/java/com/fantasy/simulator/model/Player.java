package com.fantasy.simulator.model;

import java.util.Map;

public class Player {
	
	private String identifier;
	private String position;
	private boolean isOnUserTeam;
	private String playerName;
	private String teamName;
	private String playerRank;
	private String positionRank;
	private String byeWeek;
	private Map<String, NFLPlayerWeeklyStats> weeklyStats;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}

	public String getPositionRank() {
		return positionRank;
	}

	public void setPositionRank(String positionRank) {
		this.positionRank = positionRank;
	}

	public String getByeWeek() {
		return byeWeek;
	}

	public void setByeWeek(String byeWeek) {
		this.byeWeek = byeWeek;
	}

	public Map<String, NFLPlayerWeeklyStats> getWeeklyStats() {
		return weeklyStats;
	}

	public void setWeeklyStats(Map<String, NFLPlayerWeeklyStats> weeklyStats) {
		this.weeklyStats = weeklyStats;
	}

	public boolean isOnUserTeam() {
		return isOnUserTeam;
	}

	public void setOnUserTeam(boolean isOnUserTeam) {
		this.isOnUserTeam = isOnUserTeam;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
