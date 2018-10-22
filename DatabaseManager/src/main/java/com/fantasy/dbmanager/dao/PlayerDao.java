package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanFilter;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.fantasy.dataaccessutility.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;

@Component
public class PlayerDao {
	
//	@Autowired
//	@Qualifier("playerDBCollection")
//	MongoCollection<Player> playerDBCollection;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	@Qualifier("playerTable")
	private Table playerTable;
	
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
	
	public void putAll(List<Player> players) {
//		playerDBCollection.insertMany(players);
		// TODO fix this for batch :: BatchWriteItem
		for (Player p : players) {
			playerTable.putItem(item(p));
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
