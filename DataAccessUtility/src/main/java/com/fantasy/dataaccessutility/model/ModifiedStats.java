package com.fantasy.dataaccessutility.model;

public class ModifiedStats {

	private String weekNumber;
	private double oldFantasyPointTotal;
	private double newFantasyPointTotal;
	
	public ModifiedStats() {}
	
	public ModifiedStats(String weekNumber, double oldFantasyPointTotal, double newFantasyPointTotal) {
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
	public void setOldFantasyPointTotal(double oldFantasyPointTotal) {
		this.oldFantasyPointTotal = oldFantasyPointTotal;
	}
	public double getNewFantasyPointTotal() {
		return newFantasyPointTotal;
	}
	public void setNewFantasyPointTotal(double newFantasyPointTotal) {
		this.newFantasyPointTotal = newFantasyPointTotal;
	}
	
}
