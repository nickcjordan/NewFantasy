package com.fantasy.dbmanager.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

@Configuration
public class DatabaseConfig {
	
	@Bean(name = "metadataTableName") 	public String metadataTableName() { return "metadata-table"; }
	@Bean(name = "modifierTableName") 	public String modifierTableName() { return "modifier-table"; }
	@Bean(name = "playerTableName") 		public String playerTableName() { return "player-table"; }
	@Bean(name = "userTableName") 			public String userTableName() { return "user-table"; }
	
	@Bean(name="metadataCreateTableRequest")
	public CreateTableRequest metadataCreateTableRequest() {
		return buildRequest(metadataTableName(), "metadataId", 10L, 10L);
	}
	
	@Bean(name="modifierCreateTableRequest")
	public CreateTableRequest modifierCreateTableRequest() {
		return buildRequest(modifierTableName(), "modifierId", 10L, 10L);
	}
	
	@Bean(name="playerCreateTableRequest")
	public CreateTableRequest playerCreateTableRequest() {
		return buildRequest(playerTableName(), "playerId", 25L, 10L);
	}
	
	@Bean(name="userCreateTableRequest")
	public CreateTableRequest userCreateTableRequest() {
		return buildRequest(userTableName(), "userId", 10L, 10L);
	}
	
	private CreateTableRequest buildRequest(String tableName, String keyName, Long readCapacity, Long writeCapacity) {
		return new CreateTableRequest()
				.withTableName(tableName)
				.withAttributeDefinitions(new AttributeDefinition().withAttributeName(keyName).withAttributeType("S"))
				.withKeySchema(new KeySchemaElement(keyName, KeyType.HASH))
				.withProvisionedThroughput(
						new ProvisionedThroughput()
						.withReadCapacityUnits(readCapacity)
						.withWriteCapacityUnits(writeCapacity)
				);
	}
	
	
}
