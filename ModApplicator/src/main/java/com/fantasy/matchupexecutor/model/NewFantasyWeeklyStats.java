package com.fantasy.matchupexecutor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewFantasyWeeklyStats {

	@JsonProperty
	private String weekNumber;
	@JsonProperty
	private double oldFantasyPointTotal;
	@JsonProperty
	private double newFantasyPointTotal;
	
	public NewFantasyWeeklyStats() {}
	
	public NewFantasyWeeklyStats(String weekNumber, double oldFantasyPointTotal, double newFantasyPointTotal) {
		this.weekNumber = weekNumber;
		this.oldFantasyPointTotal = oldFantasyPointTotal;
		this.newFantasyPointTotal = newFantasyPointTotal;
	}
	
	public String getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}
	public double getOldFantasyPointTotal() {
		return oldFantasyPointTotal;
	}
	public void setOldFantasyPointTotal(double initialPointTotal) {
		this.oldFantasyPointTotal = initialPointTotal;
	}
	public double getNewFantasyPointTotal() {
		return newFantasyPointTotal;
	}
	public void setNewFantasyPointTotal(double newFantasyPointTotal) {
		this.newFantasyPointTotal = newFantasyPointTotal;
	}
	
}
