package com.fantasy.dataaccessutility.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBDocument
public enum Position {
	QUARTERBACK("QB", "Quarterback"),
	RUNNINGBACK("RB", "Running Back"),
	WIDERECEIVER("WR", "Wide Receiver"),
	TIGHTEND("TE", "Tight End"),
	KICKER("K", "Kicker");
//	@JsonProperty("DST")
//	DEFENSE("DST", "Defenses/Special Teams");
	
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
