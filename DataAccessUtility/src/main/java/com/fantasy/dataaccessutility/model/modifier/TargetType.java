package com.fantasy.dataaccessutility.model.modifier;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class TargetType {
	
	public static final String PLAYER = "PLAYER";
	public static final String TEAM = "TEAM";
	public static final String POSITION = "POSITION";
	
}
