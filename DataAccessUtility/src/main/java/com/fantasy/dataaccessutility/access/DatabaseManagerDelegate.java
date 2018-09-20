package com.fantasy.dataaccessutility.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.PlayerListResponse;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.team.Team;

@Component
public class DatabaseManagerDelegate {
	
	private static final Logger log = LoggerFactory.getLogger(DatabaseManagerDelegate.class);
	
	private RestTemplate rest;
	
	public DatabaseManagerDelegate() {
		rest = new RestTemplate();
	}
	
	public Player getUpdatedPlayer(String playerId) {
		return rest.getForObject(Urls.getPlayer(playerId), Player.class);
	}

	public User getUpdatedUser(String userId) {
		return rest.getForObject(Urls.getUser(userId), User.class);
	}

	public Team getUpdatedTeam(String teamId) {
		return rest.getForObject(Urls.getTeam(teamId), Team.class);
	}

	public boolean addPlayer(Player player) {
		return rest.postForObject(Urls.putPlayer(), Arrays.asList(player), boolean.class);
	}
	
	public boolean updatePlayer(Player player) {
		log.info("Sending POST request to UPDATE player :: " + player.getPlayerId() + " :: " + player.getPlayerName() + "\n\tURL :: " + Urls.updatePlayer());
		return rest.postForObject(Urls.updatePlayer(), player, boolean.class);
	}

	public boolean updateUser(User user) {
		List<User> users = new ArrayList<User>();
		users.add(user);
		return rest.postForObject(Urls.updateUser(), users, boolean.class);
	}
	
	public boolean updateUsers(List<User> users) {
		return rest.postForObject(Urls.updateUsers(), users, boolean.class);
	}

	public boolean updateTeam(Team team) {
		return rest.postForObject(Urls.putTeam(), Arrays.asList(team), boolean.class);
	}

	public List<Player> getUpdatedPositionGroup(String position) {
		return rest.getForObject(Urls.getAllPosition(position), PlayerListResponse.class).getPlayers();
	}
	
}
