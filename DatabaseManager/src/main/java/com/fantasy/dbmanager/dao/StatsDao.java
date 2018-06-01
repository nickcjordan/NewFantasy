package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class StatsDao {
	
	@Autowired
	@Qualifier("playerSeasonStatsDBCollection")
	MongoCollection<NFLPlayerSeasonStats> playerSeasonStatsDBCollection;
	
	public void updatePlayerData(Collection<NFLPlayerSeasonStats> stats) {
//		for (NFLPlayerSeasonStats stat : collection) {
		playerSeasonStatsDBCollection.insertMany(new ArrayList<NFLPlayerSeasonStats>(stats));
//			playerSeasonStatsDBCollection.insertOne(stats);
//		}
	}

	public FindIterable<NFLPlayerSeasonStats> getAllPlayerStats() {
		return playerSeasonStatsDBCollection.find();
	}

	public boolean removeAllPlayerStats() {
		playerSeasonStatsDBCollection.deleteMany(eq("id", null));
		return true;
	}
	
}
