package com.fantasy.dbmanager.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.controller.DatabaseController;
import com.fantasy.dbmanager.model.Player;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.*;

@Component
public class PlayerDao {
	
	
	
	@Autowired
	@Qualifier("playerDBCollection")
	MongoCollection<Player> playerDBCollection;
	
	public void put(List<Player> players) {
		playerDBCollection.insertMany(players);
	}

	public long getPlayerCount() {
		return playerDBCollection.count();
	}
	
	public FindIterable<Player> getAll() {
		return playerDBCollection.find();
	}

	public boolean remove(Player player) {
		Bson filter = eq("id", player.getId());
		DeleteResult results = playerDBCollection.deleteOne(filter);
		return results.getDeletedCount() > 0;
	}

	public boolean removeAll() {
		playerDBCollection.deleteMany(eq("id", null));
		return true;
	}

}
