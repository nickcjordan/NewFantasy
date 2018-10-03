package com.fantasy.dataaccessutility.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Position {
	@JsonProperty("QB")
	QUARTERBACK("QB", "Quarterbacks"),
	@JsonProperty("RB")
	RUNNINGBACK("RB", "Running Backs"),
	@JsonProperty("WR")
	WIDERECEIVER("WR", "Wide Receivers"),
	@JsonProperty("TE")
	TIGHTEND("TE", "Tight Ends"),
	@JsonProperty("K")
	KICKER("K", "Kickers"),
	@JsonProperty("DST")
	DEFENSE("DST", "Defenses/Special Teams");
	
	private String abbrev;
	private String name;
	
	Position(String abbrev, String name){
		this.abbrev = abbrev;
		this.name = name;
	}
	
	public String getAbbrev(){
		return abbrev;
	}
	
	public String getName(){
		return name;
	}

	public static Position get(String pos) {
		for (Position p : Position.values()) {
			if (p.getAbbrev().equals(pos)) {
				return p;
			}
		}
		return null;
	}
	
}
