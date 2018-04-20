package com.fantasy.dbmanager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.model.Player;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

@Component
public class PlayerDao {
	
	@Autowired
	@Qualifier("playerDBCollection")
	MongoCollection<Player> playerDBCollection;
	
	@Autowired
	private Gson gson;

	public void test() {
		
		Player nick = new Player();
		nick.setId(5);
		playerDBCollection.insertOne(nick);
		
		System.out.println("sent");
		
	}

	public long count() {
		return playerDBCollection.count();
	}
	
	public FindIterable<Player> getAll() {
		return playerDBCollection.find();
	}

	public Player get(int id) {
		return playerDBCollection.find(eq("id", 5)).first();
	}
	
}
