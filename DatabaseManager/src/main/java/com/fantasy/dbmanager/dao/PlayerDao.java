package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Component
public class PlayerDao {
	
	@Autowired
	@Qualifier("playerDBCollection")
	MongoCollection<Player> playerDBCollection;
	
	public void putAll(List<Player> players) {
		playerDBCollection.insertMany(players);
	}
	
	public void put(Player player) {
		playerDBCollection.insertOne(player);
	}

	public long getPlayerCount() {
		return playerDBCollection.count();
	}
	
	public FindIterable<Player> getAll() {
		return playerDBCollection.find();
	}

	public boolean remove(Player player) {
		DeleteResult results = playerDBCollection.deleteOne(eq("playerId", player.getPlayerId()));
		return results.getDeletedCount() > 0;
	}

	public boolean removeAll() {
		playerDBCollection.deleteMany(eq("blank", null));
		return true;
	}

	public Player getPlayerByName(String name) {
		return playerDBCollection.find(eq("playerName", name)).first();
	}

	public Player getPlayerByID(String id) {
		return playerDBCollection.find(eq("playerId", id)).first();
	}
	
	public Player updatePlayer(Player player) {
//		return playerDBCollection.findOneAndReplace(eq("playerName", player.getPlayerName()), player);
		Bson eq = eq("playerId", player.getPlayerId());
		Player res = playerDBCollection.findOneAndReplace(eq, player);
		return res;
//		return playerDBCollection.findOneAndReplace(eq("playerId", player.getPlayerId()), player);
	}

	public FindIterable<Player> getAllPlayersByPosition(String position) {
		return playerDBCollection.find(eq("position", position), Player.class);
	}

}
