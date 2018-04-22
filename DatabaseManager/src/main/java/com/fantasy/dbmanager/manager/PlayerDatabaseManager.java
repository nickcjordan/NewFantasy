package com.fantasy.dbmanager.manager;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.model.Player;
import com.mongodb.client.result.DeleteResult;

@Component
public class PlayerDatabaseManager {
	
	private static Logger log = Logger.getLogger(PlayerDatabaseManager.class);
	
	@Autowired
	private PlayerDao playerDao;

	public void putPlayersInDB(List<Player> players) {
		playerDao.put(players);
	}
	
	public long countPlayers() {
		return playerDao.getPlayerCount();
	}
	
	public List<Player> getAll() {
		List<Player> players = new ArrayList<Player>();
		for (Player p : playerDao.getAll()) {
			players.add(p);
		}
		return players;
	}

//	public boolean removeAllPlayersFromDB() {
//		boolean allSuccess = true;
//		for (Player p : players) {
//			boolean success = playerDao.remove(p);
//			allSuccess &= success;
//			if (success) {
//				log.info("DatabaseManager :: PlayerDatabaseManager :: success :: removed player " + p.getPlayerName() + " from database");
//			} else {
//				log.error("DatabaseManager :: PlayerDatabaseManager :: failed to remove player " + p.getPlayerName() + " from database");
//			}
//		}
//		return allSuccess;
//	}
	
	public boolean removeAllPlayersFromDB() {
		return playerDao.removeAll();
	}

}
