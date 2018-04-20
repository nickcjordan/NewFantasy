package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.model.Player;

@Component
public class PlayerDatabaseManager {
	
	@Autowired
	private PlayerDao playerDao;

	public void putPlayer() {
		playerDao.test();
	}
	
	public long count() {
		return playerDao.count();
	}
	
	public List<Player> getAll() {
		List<Player> players = new ArrayList<Player>();
		for (Player p : playerDao.getAll()) {
			players.add(p);
		}
		return players;
	}

	public Player get(int id) {
		return playerDao.get(id);
	}
}
