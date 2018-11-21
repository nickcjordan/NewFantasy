package com.fantasy.dataaccessutility.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
@DynamoDBDocument
public class Position {
	
	private String abbrev;
	private String name;
	
	public Position() {}
	
	public Position(String abbrev, String name){
		this.abbrev = abbrev;
		this.name = name;
	}
	@DynamoDBAttribute
	public String getAbbrev(){
		return abbrev;
	}
	@DynamoDBAttribute
	public String getName(){
		return name;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	public void setName(String name) {
		this.name = name;
	}

}
