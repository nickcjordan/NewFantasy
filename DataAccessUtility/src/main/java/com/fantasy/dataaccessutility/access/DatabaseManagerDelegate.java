package com.fantasy.dataaccessutility.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;

@Component
public class DatabaseManagerDelegate {
	
	private RestTemplate rest;
	
	public DatabaseManagerDelegate() {
		rest = new RestTemplate();
		rest.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
	}
	
	private static Logger log = Logger.getLogger(DatabaseManagerDelegate.class);
	
	public Player getUpdatedPlayer(String playerId) {
		return rest.getForObject(Urls.getPlayer(playerId), Player.class);
	}

	public User getUpdatedUser(String userId) {
		return rest.getForObject(Urls.getUser(userId), User.class);
	}

	public Team getUpdatedTeam(String teamId) {
		return rest.getForObject(Urls.getTeam(teamId), Team.class);
	}

	public boolean updatePlayer(Player player) {
		return rest.postForObject(Urls.putPlayer(), Arrays.asList(player), boolean.class);
	}

	public boolean updateUser(User user) {
		List<User> users = new ArrayList<User>();
		users.add(user);
		return rest.postForObject(Urls.putUser(), users, boolean.class);
	}

	public boolean updateTeam(Team team) {
		return rest.postForObject(Urls.putTeam(), Arrays.asList(team), boolean.class);
	}
	
}
