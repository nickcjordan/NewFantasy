package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class StatsDao {
	
	private static Logger log = Logger.getLogger(StatsDao.class);
	
	@Autowired
	@Qualifier("playerSeasonStatsDBCollection")
	MongoCollection<NFLPlayerSeasonStats> playerSeasonStatsDBCollection;
	
	public int updatePlayerData(Collection<NFLPlayerSeasonStats> stats) {
		int successUpdates = 0;
		for (NFLPlayerSeasonStats stat : stats) {
			if (stat != null && stat.getId() != null) {
				Bson filter = eq(stat.getId());
				if (playerSeasonStatsDBCollection.findOneAndReplace(filter, stat) != null) {
					successUpdates++;
				} else {
					log.error("Could not find data for [id=" + stat.getId() + ", name=" + stat.getName() + "]");
				}
			} else {
				log.error("NFLPlayerSeasonStat is null");
			}
		}
		return successUpdates;
	}

	public FindIterable<NFLPlayerSeasonStats> getAllPlayerStats() {
		return playerSeasonStatsDBCollection.find();
	}

	public boolean removeAllPlayerStats() {
		return playerSeasonStatsDBCollection.deleteMany(eq("id", null)).wasAcknowledged();
	}
	
}
