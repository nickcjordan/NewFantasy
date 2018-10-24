package com.fantasy.dbmanager.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.to.PlayerStatsAPIResponse;
import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.fetcher.PlayerStatsAPIDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;

@Component
public class PlayerDatabaseManager {
	
	private static Logger log = Logger.getLogger(PlayerDatabaseManager.class);
	
	@Autowired
	private PlayerDao playerDao;
	
	@Autowired
	private PlayerStatsAPIDelegate statsDelegate;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Player player(Item item) {
		try {
			return mapper.readValue(item.toJSON(), Player.class);
		} catch (IOException e) {
			e.printStackTrace(); // TODO
			return null;
		}
	}
	
	public void putPlayersInDB(List<Player> players) {
		playerDao.putAll(players);
	}
	
	public long countPlayers() {
		return playerDao.getPlayerCount();
	}
	
	public List<Player> getAll() {
//		return convertIterableToList();
//		return buildPlayerList(playerDao.getAll());
		return playerDao.getAll();
	}

	private List<Player> buildPlayerList(ItemCollection<ScanOutcome> res) {
		List<Player> players = new ArrayList<Player>();
		for (Page<Item, ScanOutcome> page : res.pages()) {
			for (Item i : page) {
				players.add(player(i));
			}
		}
		return players;
	}

	public List<Player> getAll(String position) {
//		return buildPlayerList(playerDao.getAllPlayersByPosition(position));
		return playerDao.getAllPlayersByPosition(position);
	}
	
	private List<Player> convertIterableToList(FindIterable<Player> results) {
		List<Player> players = new ArrayList<Player>();
		for (Player p : results) {
			players.add(p);
		}
		return players;
	}
	
	public boolean removeAllPlayersFromDB() {
		return playerDao.removeAll();
	}

	public Player get(String val) {
		Player player = null;
		try {
			NumberUtils.parseNumber(val, Integer.class);
			log.info("getting player with id=" + val);
			player = playerDao.getPlayerByID(val);
		} catch (IllegalArgumentException e) {
			log.info("getting player with name=" + val);
			player = playerDao.getPlayerByName(val);
		}
		return player;
	}

	public int updateAll() {
		int count = 0;
		PlayerStatsAPIResponse response = statsDelegate.getUpdatedStats();
		try {
			for (Player player : response.getPlayers().values()) {
				if (!updatePlayer(player)) {
					log.info("Could not find player to replace... adding as new: " + player.getPlayerName());
					playerDao.put(player);
				}				
				count++;
			}
		} catch (Exception e) {
			log.error("ERROR calling PlayerStatsAPI :: Check that server is started", e);
		}
		return count;
	}

	public boolean updatePlayer(Player player) {
//		 if (playerDao.updatePlayer(player) != null) {
//			 log.info("Successfully updated player " + player.getPlayerName());
//			 return true;
//		 } else {
//			 log.info("ERROR trying to update player " + player.getPlayerName());
//			 return false;
//		 }
		playerDao.updatePlayer(player);
		return true;
	}	

}
