package com.fantasy.dbmanager.populator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.PositionStatsDetails;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.team.Roster;
import com.fantasy.dataaccessutility.model.team.Team;
import com.fantasy.dbmanager.controller.PlayerDatabaseController;
import com.fantasy.dbmanager.controller.UserDatabaseController;

@Component
public class DatabasePopulator {
	
	private static final Logger log = LoggerFactory.getLogger(DatabasePopulator.class);
	
	@Autowired
	private PlayerDatabaseController playerData;
	
	@Autowired
	private UserDatabaseController userData;
	
	private Map<String, List<Player>> positionMap;

	private String[] names = new String[]{
			"Chris T",
			"Chris R",
			"Will",
			"Matt",
			"Nick J",
			"Ryan",
			"Josh",
			"Mason",
			"Nick W",
			"Austin",
			"Dan",
			"Scott"
	};
	
	public void populate() {
		log.info("START :: Populating database...");
		positionMap = new HashMap<String, List<Player>>();
		positionMap.put("QB", playerData.getAllByPosition("QB").getPlayers());
		positionMap.put("RB", playerData.getAllByPosition("RB").getPlayers());
		positionMap.put("WR", playerData.getAllByPosition("WR").getPlayers());
		positionMap.put("TE", playerData.getAllByPosition("TE").getPlayers());
		positionMap.put("K", playerData.getAllByPosition("K").getPlayers());
		
		for (List<Player> list : positionMap.values()) {
			for (Player p : list) {
				p.setOnUserTeam(false);
			}
		}
		
		userData.updateUsers(buildLeagueList());
		
		
		List<Player> updates = new ArrayList<Player>();
		for (List<Player> list : positionMap.values()) {
			for (Player p : list) {
				if (p.isOnUserTeam()) {
					log.info("Player " + p.getPlayerName() + " added to team :: updating player in database...");
					updates.add(p);
				}
			}
		}
		playerData.putPlayers(updates);
		
		log.info("END :: SUCCESS :: Populated database");
	}
	
	private List<User> buildLeagueList() {
		List<User> users = new ArrayList<User>();
		for (int id = 0; id < names.length; id++) {
			users.add(buildNewUser(Integer.toString(id)));
		}
		log.info("All users and teams built, sending to update users in database...");
		return users;
	}

	private User buildNewUser(String id) {
		User user = new User(id, names[Integer.valueOf(id)]);
		log.info("\n\nBUILDING NEW USER :: " + id + " : " + user.getUserName() + "\n");
		user.setTeam(buildNewTeam(id));
		user.setCoins(Integer.valueOf(id) * 509);
		return user;
	}

	private Team buildNewTeam(String id) {
		Team team = new Team(id, "team_" + id);
		team.setRoster(buildNewRoster());
		return team;
	}
	
	private Roster buildNewRoster() {
		Roster roster = new Roster();
		roster.addFlexPlayerToLineupAndMoveReplacedPlayerToBench(getRandomPlayerByPosition("RB")); //add flex
		addPositionToBenchOrLineup(buildQbs(), roster);
		addPositionToBenchOrLineup(buildRbs(), roster);
		addPositionToBenchOrLineup(buildWrs(), roster);
		addPositionToBenchOrLineup(buildTes(), roster);
		addPositionToBenchOrLineup(buildKs(), roster);
		return roster;
	}

	private void addPositionToBenchOrLineup(List<Player> players, Roster roster) {
		 for (Player p : players) {
			 try {
				roster.addPlayerToLineupAndMoveReplacedPlayerToBench(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}

	private List<Player> buildQbs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 2; i++) {
			players.add(getRandomPlayerByPosition("QB"));
		}
		return players;
	}

	private List<Player> buildRbs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 5; i++) {
			players.add(getRandomPlayerByPosition("RB"));
		}
		return players;
	}

	private List<Player> buildWrs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 5; i++) {
			players.add(getRandomPlayerByPosition("WR"));
		}
		return players;
	}

	private List<Player> buildTes() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 2; i++) {
			players.add(getRandomPlayerByPosition("TE"));
		}
		return players;
	}
	
	private List<Player> buildKs() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 1; i++) {
			players.add(getRandomPlayerByPosition("K"));
		}
		return players;
	}
	
	private Player getRandomPlayerByPosition(String position) {
		List<Player> list = positionMap.get(position);
		Collections.shuffle(list);
		int i =11;
		Player p = null;
		boolean goodPlayer = false;
		try {
			while (!goodPlayer) {
				p = list.get(i++);
				if (hasHighEnoughTotalPoints(p)) {
					goodPlayer = true;
				}
			}
		} catch (Exception e) {
			p = list.get(0);
		}
		p.setOnUserTeam(true);
//		playerData.update(p);
		list.remove(p);
		positionMap.put(position, list);
		return p;
	}

	private boolean hasHighEnoughTotalPoints(Player p) {
		double sum = 0;
		for (PositionStatsDetails stat : p.getStatsByWeek().values()) {
			sum += stat.getTotalPointsScored();
		}
		return (sum > 80);
	}

}
