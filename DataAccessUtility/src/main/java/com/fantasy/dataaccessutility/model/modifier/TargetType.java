package com.fantasy.dataaccessutility.model.modifier;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public enum TargetType {
	PLAYER, TEAM, POSITION;
}
