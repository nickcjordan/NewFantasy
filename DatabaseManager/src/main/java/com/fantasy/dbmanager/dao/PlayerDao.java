package com.fantasy.dbmanager.dao;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.fantasy.dataaccessutility.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlayerDao {

	private static final Logger log = LoggerFactory.getLogger(PlayerDao.class);

	private ObjectMapper mapper = new ObjectMapper();
	private static final String PLAYER_TABLE = "player-table";

	// @Autowired
	// @Qualifier("dynamoDb")
	// private DynamoDB dynamoDb;

	@Autowired
	@Qualifier("dynamoDb")
	private DynamoDB db;

	@Autowired
	@Qualifier("dynamoDbMapper")
	private DynamoDBMapper dbMapper;

	private Item item(Player p) {
		try {
			return Item.fromJSON(mapper.writeValueAsString(p));
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // TODO
			return null;
		}
	}

	private Player player(Item item) {
		try {
			return mapper.readValue(item.toJSON(), Player.class);
		} catch (IOException e) {
			e.printStackTrace(); // TODO
			return null;
		}
	}

	public void putAll(Collection<Player> players) {

		// Map<String, List<WriteRequest>> map = new HashMap<String,
		// List<WriteRequest>>();
		// playerDBCollection.insertMany(players);
		// TODO fix this for batch :: BatchWriteItem
		// BatchWriteItemRequest request = new BatchWriteItemRequest(map);
		// BatchWriteItemResult response = client.batchWriteItem(request);
		// for (Player p : players) {
		// PutRequest put = new PutRequest();
		//// put.setItem(item(p).asMap());
		//// WriteRequest w = new WriteRequest();
		// playerTable.putItem(item(p));
		// }

		// try {
		// List<Item> items = new ArrayList<Item>();
		// for (Player p : players) {
		// items.add(item(p));
		// }
		// TableWriteItems writeItems = new
		// TableWriteItems("player-table").withItemsToPut(items);
		// BatchWriteItemOutcome outcome = dynamoDb.batchWriteItem(writeItems);
		//
		// do { // Check for unprocessed keys which could happen if you exceed
		// provisioned throughput
		//
		// Map<String, List<WriteRequest>> unprocessedItems =
		// outcome.getUnprocessedItems();
		//
		// if (outcome.getUnprocessedItems().size() == 0) {
		// System.out.println("No unprocessed items found");
		// }
		// else {
		// System.out.println("Retrieving the unprocessed items");
		// outcome = dynamoDb.batchWriteItemUnprocessed(unprocessedItems);
		// }
		//
		// } while (outcome.getUnprocessedItems().size() > 0);
		//
		// }
		// catch (Exception e) {
		// System.err.println("Failed to retrieve items: ");
		// e.printStackTrace(System.err);
		// }

		List<FailedBatch> response = dbMapper.batchSave(players);
		// List<>
		// for (FailedBatch fail : response) {
		//
		// }
		// do { // Check for unprocessed keys which could happen if you exceed
		// provisioned throughput
		//
		// Map<String, List<WriteRequest>> unprocessedItems =
		// fail.getUnprocessedItems();
		//
		// if (fail.getUnprocessedItems().size() == 0) {
		// System.out.println("No unprocessed items found");
		// }
		// else {
		// System.out.println("Retrieving the unprocessed items");
		// fail = dynamoDb.batchSave(unprocessedItems);
		// }
		//
		// } while (fail.getUnprocessedItems().size() > 0);
		//

	}

	public void put(Player player) {
		// playerDBCollection.insertOne(player);
		// playerTable.putItem(item(player));
		dbMapper.save(player);
	}

	public long getPlayerCount() {
		// return playerDBCollection.count();
		// return playerTable.getDescription().getItemCount();
		return dbMapper.count(Player.class, new DynamoDBScanExpression());
	}

	public List<Player> getAll() {
		// return playerDBCollection.find();
		// return playerTable.scan(new ScanFilter("playerId").exists());

		// KeyPair keyPair1 = new KeyPair();
		// keyPair1.withHashKey(1991);
		// keyPair1.withRangeKey("Movie with map attribute");
		//
		// KeyPair keyPair2 = new KeyPair();
		// keyPair2.withHashKey(2010);
		// keyPair2.withRangeKey("The Big New Movie 2010");
		//
		// List<KeyPair> keyPairList = new ArrayList<>();
		// keyPairList.add(keyPair1);
		// keyPairList.add(keyPair2);
		//
		// Map<Class<?>, List<KeyPair>> keyPairForTable = new HashMap<>();
		// keyPairForTable.put(Player.class, keyPairList);
		//
		// Map<String, List<Object>> batchResults = dynamoDb.batchLoad(keyPairForTable);
		//
		// for (Map.Entry<String, List<Object>> entry : batchResults.entrySet()) {
		// System.out.println(entry.getKey());
		// System.out.println(entry.getValue());
		// }

		// List<Object> res = batchResults.get(PLAYER_TABLE);
		return dbMapper.scan(Player.class, new DynamoDBScanExpression());
	}

	public boolean remove(Player player) {
		// DeleteResult results = playerDBCollection.deleteOne(eq("playerId",
		// player.getPlayerId()));
		// return results.getDeletedCount() > 0;
		// DeleteItemOutcome outcome = playerTable.deleteItem("playerId",
		// player.getPlayerId());
		// TODO fix this result
		dbMapper.delete(player);
		return true;
	}

	public boolean removeAll() {
		// playerDBCollection.deleteMany(eq("blank", null));
		// TODO fix this
		createTable(deleteTable(PLAYER_TABLE));
		return true;
	}

	private void createTable(TableDescription description) {
		try {
			log.info("Sending CreateTable request for " + description.getTableName());
			Table res = db.createTable(new CreateTableRequest().withTableName(PLAYER_TABLE).withAttributeDefinitions(description.getAttributeDefinitions()));
			log.info("Waiting for " + res.getTableName() + " to be created...");
			res.waitForActive();
		} catch (Exception e) {
			log.error("CreateTable request failed for " + description.getTableName() + " :: " + e.getMessage());
		}
	}

	private TableDescription deleteTable(String tableName) {
		Table table = db.getTable(tableName);
		try {
			log.info("Sending DeleteTable request for " + table.getTableName());
			table.delete();
			log.info("Waiting for " + table.getTableName() + " to be deleted...");
			table.waitForDelete();
			return table.getDescription();
		} catch (Exception e) {
			log.error("DeleteTable request failed for " + table.getTableName() + " :: " + e.getMessage());
		}
		return null;
	}

	public Player getPlayerByName(String name) {
		// return playerDBCollection.find(eq("playerName", name)).first();
		return player(db.getTable(PLAYER_TABLE).getItem("playerName", name));
	}

	public Player getPlayerByID(String id) {
		// return player(playerTable.getItem("playerId", id));
		return dbMapper.load(Player.class, id);
	}

	public void updatePlayer(Player player) {
		// return playerDBCollection.findOneAndReplace(eq("playerId",
		// player.getPlayerId()), player);

		// UpdateItemSpec update = new UpdateItemSpec();
		// update.addAttributeUpdate(new AttributeUpdate().addElements(newElements))
		// return playerTable.updateItem(updateItemSpec)

		// return playerTable.putItem(item(player));
		dbMapper.save(player);
	}

	public List<Player> getAllPlayersByPosition(String position) {
		// return playerDBCollection.find(eq("position", position), Player.class);
		// return playerTable.scan(new ScanFilter("position").eq(position));

		// return dbMapper.scan(Player.class, new DynamoDBScanExpression());

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withN(position));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("position = :val2").withExpressionAttributeValues(eav);

		return dbMapper.scan(Player.class, scanExpression);
	}

}
