package com.fantasy.dataaccessutility.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "metadata-table")
public class Metadata {
	
	private String metadataId;
	private String value;
	
	@DynamoDBHashKey(attributeName = "metadataId")
	public String getMetadataId() {
		return metadataId;
	}
	@DynamoDBAttribute
	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
	}
	@DynamoDBAttribute
	public String getValue() {
		return value;
	}
	@DynamoDBAttribute
	public void setValue(String value) {
		this.value = value;
	}
	
}
