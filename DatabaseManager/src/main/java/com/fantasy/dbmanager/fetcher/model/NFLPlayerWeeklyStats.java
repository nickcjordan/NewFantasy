package com.fantasy.dbmanager.fetcher.model;

public class NFLPlayerWeeklyStats {

	private int weekNumber;
	private double weeklyPoints;
	private String opponentTeamAbbrev;
	private String fanPtsAgainstOpponentPts;
	private String fanPtsAgainstOpponentRank;
	private String carries;
	private String touches;
	private String receptions;
	private String targets;
	
	public NFLPlayerWeeklyStats() {}
	
	public NFLPlayerWeeklyStats(int weekNumber, double weeklyPoints) {
		this.weekNumber = weekNumber;
		this.weeklyPoints = weeklyPoints;
	}
	
	public String getOpponentTeamAbbrev() {
		return opponentTeamAbbrev;
	}

	public void setOpponentTeamAbbrev(String opponentTeamAbbrev) {
		this.opponentTeamAbbrev = opponentTeamAbbrev;
	}

	public String getFanPtsAgainstOpponentPts() {
		return fanPtsAgainstOpponentPts;
	}

	public void setFanPtsAgainstOpponentPts(String fanPtsAgainstOpponentPts) {
		this.fanPtsAgainstOpponentPts = fanPtsAgainstOpponentPts;
	}

	public String getFanPtsAgainstOpponentRank() {
		return fanPtsAgainstOpponentRank;
	}

	public void setFanPtsAgainstOpponentRank(String fanPtsAgainstOpponentRank) {
		this.fanPtsAgainstOpponentRank = fanPtsAgainstOpponentRank;
	}

	public String getCarries() {
		return carries;
	}

	public void setCarries(String carries) {
		this.carries = carries;
	}

	public String getTouches() {
		return touches;
	}

	public void setTouches(String touches) {
		this.touches = touches;
	}

	public String getReceptions() {
		return receptions;
	}

	public void setReceptions(String receptions) {
		this.receptions = receptions;
	}

	public String getTargets() {
		return targets;
	}

	public void setTargets(String targets) {
		this.targets = targets;
	}

	public int getWeekNumber() {
		return weekNumber;
	}
	
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	
	public double getWeeklyPoints() {
		return weeklyPoints;
	}
	
	public void setWeeklyPoints(double weeklyPoints) {
		this.weeklyPoints = weeklyPoints;
	}
}
