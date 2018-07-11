package com.fantasy.dataaccessutility;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.access.DatabaseManagerDelegate;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;

@Component
public class DataAccessUtility {
	
	private static Logger log = Logger.getLogger(DataAccessUtility.class);
	
	@Autowired
	private DatabaseManagerDelegate delegate;

	//GET
						// PLAYERS
	public Player getPlayerById(String playerId) {
		log.info("Getting updated stats for player " + playerId);
		return delegate.getUpdatedPlayer(playerId);
	}
	public Player getPlayerFromPlayer(Player player) {
		return getPlayerById(player.getPlayerId());
	}
	public List<Player> getPlayersByIdList(List<String> playerIds) {
		List<Player> players = new ArrayList<Player>();
		for (String id : playerIds) {
			players.add(getPlayerById(id));
		}
		return players;
	}
	public List<Player> getPlayersFromPlayerList(List<Player> playersRequest) {
		List<Player> players = new ArrayList<Player>();
		for (Player p : playersRequest) {
			players.add(getPlayerById(p.getPlayerId()));
		}
		return players;
	}
	
						// USERS
	public User getUserById(String userId) {
		log.info("Getting updated stats for user " + userId);
		return delegate.getUpdatedUser(userId);
	}
	public User getUserFromUser(User user) {
		return getUserById(user.getId());
	}
	public List<User> getUsersByIdList(List<String> userIds) {
		List<User> users = new ArrayList<User>();
		for (String id : userIds) {
			users.add(getUserById(id));
		}
		return users;
	}
	public List<User> getUsersFromUserList(List<User> usersRequest) {
		List<User> users = new ArrayList<User>();
		for (User p : usersRequest) {
			users.add(getUserById(p.getId()));
		}
		return users;
	}
	
						// TEAMS
	public Team getTeamById(String teamId) {
		log.info("Getting updated stats for team " + teamId);
		return delegate.getUpdatedTeam(teamId);
	}
	public Team getTeamFromTeam(Team team) {
		return getTeamById(team.getId());
	}
	public List<Team> getTeamsByIdList(List<String> teamIds) {
		List<Team> teams = new ArrayList<Team>();
		for (String id : teamIds) {
			teams.add(getTeamById(id));
		}
		return teams;
	}
	public List<Team> getTeamsFromTeamList(List<Team> teamsRequest) {
		List<Team> teams = new ArrayList<Team>();
		for (Team p : teamsRequest) {
			teams.add(getTeamById(p.getId()));
		}
		return teams;
	}
	
//UPDATE
						// PLAYERS
	public void updatePlayer(Player player) {
		log.info("Updating stats for player " + player.getPlayerId());
		delegate.updatePlayer(player);
	}
	public void updatePlayers(List<Player> players) {
		for (Player p : players) { updatePlayer(p); }
	}
	
						// USERS
	public void updateUser(User user) {
		log.info("Updating stats for user " + user.getId());
		delegate.updateUser(user);
	}
	public void updateUsers(List<User> users) {
		for (User user : users) { updateUser(user); }
	}
	
						// TEAMS
	public void updateTeam(Team team) {
		log.info("Updating stats for team " + team.getId());
		delegate.updateTeam(team);
	}
	public void updateTeams(List<Team> teams) {
		for (Team team : teams) { updateTeam(team); }
	}
}
