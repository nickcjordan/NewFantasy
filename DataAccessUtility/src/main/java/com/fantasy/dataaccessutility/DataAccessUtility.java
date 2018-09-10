package com.fantasy.dataaccessutility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.manager.PlayerDataAccessManager;
import com.fantasy.dataaccessutility.manager.TeamDataAccessManager;
import com.fantasy.dataaccessutility.manager.UserDataAccessManager;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.team.Team;

@Component
public class DataAccessUtility {

	@Autowired
	private PlayerDataAccessManager playerManager;

	@Autowired
	private TeamDataAccessManager teamManager;

	@Autowired
	private UserDataAccessManager userManager;

						// PLAYERS
	public Player getPlayerById(String playerId) {
		return playerManager.getPlayerById(playerId);
	}
	public Player getPlayerFromPlayer(Player player) {
		return playerManager.getPlayerFromPlayer(player);
	}
	public List<Player> getPlayersByIdList(List<String> playerIds) {
		return playerManager.getPlayersByIdList(playerIds);
	}
	public List<Player> getPlayersFromPlayerList(List<Player> playersRequest) {
		return playerManager.getPlayersFromPlayerList(playersRequest);
	}
	public List<Player> getAllPlayersByPosition(String position) {
		return playerManager.getAllPlayersByPosition(position);
	}
	public void updatePlayer(Player player) {
		playerManager.updatePlayer(player);
	}
	public void updatePlayers(List<Player> players) {
		playerManager.updatePlayers(players);
	}

						// TEAMS
	public Team getTeamById(String teamId) {
		return teamManager.getTeamById(teamId);
	}
	public Team getTeamFromTeam(Team team) {
		return teamManager.getTeamFromTeam(team);
	}
	public List<Team> getTeamsByIdList(List<String> teamIds) {
		return teamManager.getTeamsByIdList(teamIds);
	}
	public List<Team> getTeamsFromTeamList(List<Team> teamsRequest) {
		return teamManager.getTeamsFromTeamList(teamsRequest);
	}
	public void updateTeam(Team team) {
		teamManager.updateTeam(team);
	}
	public void updateTeams(List<Team> teams) {
		teamManager.updateTeams(teams);
	}

						// USERS
	public User getUserById(String userId) {
		return userManager.getUserById(userId);
	}
	public User getUserFromUser(User user) {
		return userManager.getUserFromUser(user);
	}
	public List<User> getUsersByIdList(List<String> userIds) {
		return userManager.getUsersByIdList(userIds);
	}
	public List<User> getUsersFromUserList(List<User> usersRequest) {
		return userManager.getUsersFromUserList(usersRequest);
	}
	public void updateUser(User user) {
		userManager.updateUser(user);
	}
	public void updateUsers(List<User> users) {
		userManager.updateUsers(users);
	}
}
