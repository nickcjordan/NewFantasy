package com.fantasy.dbmanager.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

public class CommonDao {
	
	@Autowired
	@Qualifier("dynamoDb")
	protected DynamoDB db;

	@Autowired
	@Qualifier("dynamoDbMapper")
	protected DynamoDBMapper dbMapper;
	
	private static final Logger log = LoggerFactory.getLogger(CommonDao.class);
	
	protected void createTable(TableDescription description) {
			log.info("Sending CreateTable request for " + description.getTableName());
			CreateTableRequest req = new CreateTableRequest()
					.withTableName(description.getTableName())
					.withAttributeDefinitions(description.getAttributeDefinitions())
					.withKeySchema(description.getKeySchema())
					.withProvisionedThroughput(
							new ProvisionedThroughput()
							.withReadCapacityUnits(description.getProvisionedThroughput().getReadCapacityUnits())
							.withWriteCapacityUnits(description.getProvisionedThroughput().getWriteCapacityUnits())
					);
			createTable(req);
	}
	
	protected void createTable(CreateTableRequest request) {
		try {
			log.info("Sending CreateTable request for " + request.getTableName());
			Table res = db.createTable(request);
			log.info("Waiting for " + res.getTableName() + " to be created...");
			res.waitForActive();
		} catch (Exception e) {
			log.error("CreateTable request failed for " + request.getTableName() + " :: " + e.getMessage());
		}
	}

	protected TableDescription deleteTable(String tableName) {
		Table table = db.getTable(tableName);
		try {
			TableDescription config = table.describe();
			log.info("Sending DeleteTable request for " + table.getTableName());
			table.delete();
			log.info("Waiting for " + table.getTableName() + " to be deleted...");
			table.waitForDelete();
			return config;
		} catch (Exception e) {
			log.error("DeleteTable request failed for " + table.getTableName() + " :: " + e.getMessage());
		}
		return null;
	}

}
