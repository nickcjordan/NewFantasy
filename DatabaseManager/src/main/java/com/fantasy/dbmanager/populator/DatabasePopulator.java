package com.fantasy.dbmanager.populator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;

@Component
public class DatabasePopulator {
	
	@Autowired
	private DataAccessUtility data;
	
	private Map<String, List<Player>> positionMap;
	
	public void populate() {
		positionMap = new HashMap<String, List<Player>>();
		positionMap.put("QB", data.getAllPlayersByPosition("QB"));
		positionMap.put("RB", data.getAllPlayersByPosition("RB"));
		positionMap.put("WR", data.getAllPlayersByPosition("WR"));
		positionMap.put("TE", data.getAllPlayersByPosition("TE"));
		
		data.updateUsers(buildLeagueList());
	}
	
	private List<User> buildLeagueList() {
		List<User> users = new ArrayList<User>();
		for (int id = 1; id <= 12; id++) {
			users.add(buildNewUser(Integer.toString(id)));
		}
		return users;
	}

	private User buildNewUser(String id) {
		User user = new User(id, "user_" + id);
		user.setTeam(buildNewTeam(id));
		return user;
	}

	private Team buildNewTeam(String id) {
		Team team = new Team(id, "team_" + id);
		team.setQb(buildQbs());
		team.setRb(buildRbs());
		team.setWr(buildWrs());
		team.setTe(buildTes());
		return team;
	}
	
	private int getRandomNumber(int i) {
		return  (((int)Math.random())%(i + 1)) + 1;
	}
	
	private List<Player> buildQbs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < getRandomNumber(2); i++) {
			players.add(getRandomPlayerByPosition("QB"));
		}
		return players;
	}

	private List<Player> buildRbs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < getRandomNumber(3); i++) {
			players.add(getRandomPlayerByPosition("RB"));
		}
		return players;
	}

	private List<Player> buildWrs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < getRandomNumber(3); i++) {
			players.add(getRandomPlayerByPosition("WR"));
		}
		return players;
	}

	private List<Player> buildTes() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < getRandomNumber(2); i++) {
			players.add(getRandomPlayerByPosition("TE"));
		}
		return players;
	}
	
	private Player getRandomPlayerByPosition(String position) {
		List<Player> list = positionMap.get(position);
		Collections.shuffle(list);
		Player p = list.get(0);
		list.remove(p);
		positionMap.put(position, list);
		return p;
	}

}
