package com.fantasy.dbmanager.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanFilter;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutRequest;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.fantasy.dataaccessutility.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlayerDao {
	
//	@Autowired
//	@Qualifier("playerDBCollection")
//	MongoCollection<Player> playerDBCollection;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	@Qualifier("playerTable")
	private Table playerTable;
	
	@Autowired
	@Qualifier("dynamoDb")
	private DynamoDB dynamoDb;
	
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

//		Map<String, List<WriteRequest>> map = new HashMap<String, List<WriteRequest>>();
		//		playerDBCollection.insertMany(players);
		// TODO fix this for batch :: BatchWriteItem
//		BatchWriteItemRequest request = new BatchWriteItemRequest(map);
//		BatchWriteItemResult response = client.batchWriteItem(request);
//		for (Player p : players) {
//			PutRequest put = new PutRequest();
////			put.setItem(item(p).asMap());
////			WriteRequest w = new WriteRequest();
//			playerTable.putItem(item(p));
//		}
		
		
		
		try {
			List<Item> items = new ArrayList<Item>();
			for (Player p : players) {
				items.add(item(p));
			}
            TableWriteItems writeItems = new TableWriteItems("player-table").withItemsToPut(items);
            BatchWriteItemOutcome outcome = dynamoDb.batchWriteItem(writeItems);

            do { // Check for unprocessed keys which could happen if you exceed provisioned throughput

                Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();

                if (outcome.getUnprocessedItems().size() == 0) {
                    System.out.println("No unprocessed items found");
                }
                else {
                    System.out.println("Retrieving the unprocessed items");
                    outcome = dynamoDb.batchWriteItemUnprocessed(unprocessedItems);
                }

            } while (outcome.getUnprocessedItems().size() > 0);

        }
        catch (Exception e) {
            System.err.println("Failed to retrieve items: ");
            e.printStackTrace(System.err);
        }
	}
	
	public void put(Player player) {
//		playerDBCollection.insertOne(player);
		playerTable.putItem(item(player));
	}

	public long getPlayerCount() {
//		return playerDBCollection.count();
		return playerTable.getDescription().getItemCount();
	}
	
	public ItemCollection<ScanOutcome> getAll() {
		return playerTable.scan(new ScanFilter("playerId").exists());
//		return playerDBCollection.find();
	}

	public boolean remove(Player player) {
//		DeleteResult results = playerDBCollection.deleteOne(eq("playerId", player.getPlayerId()));
//		return results.getDeletedCount() > 0;
		DeleteItemOutcome outcome = playerTable.deleteItem("playerId", player.getPlayerId());
		//TODO fix this result
		return true;
	}

	public boolean removeAll() {
//		playerDBCollection.deleteMany(eq("blank", null));
		// TODO fix this
		return true;
	}

	public Player getPlayerByName(String name) {
//		return playerDBCollection.find(eq("playerName", name)).first();
		return player(playerTable.getItem("playerName", name));
	}

	public Player getPlayerByID(String id) {
		return player(playerTable.getItem("playerId", id));
	}
	
	public PutItemOutcome updatePlayer(Player player) {
//		return playerDBCollection.findOneAndReplace(eq("playerId", player.getPlayerId()), player);
		
//		UpdateItemSpec update = new UpdateItemSpec();
//		update.addAttributeUpdate(new AttributeUpdate().addElements(newElements))
//		return playerTable.updateItem(updateItemSpec)
		
		return playerTable.putItem(item(player));
	}

	public ItemCollection<ScanOutcome> getAllPlayersByPosition(String position) {
//		return playerDBCollection.find(eq("position", position), Player.class);
		return playerTable.scan(new ScanFilter("position").eq(position));
	}

}
