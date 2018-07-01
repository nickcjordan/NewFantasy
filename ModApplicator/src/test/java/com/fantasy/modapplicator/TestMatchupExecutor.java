package com.fantasy.modapplicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fantasy.matchupexecutor.model.Player;
import com.fantasy.matchupexecutor.model.Team;
import com.fantasy.matchupexecutor.model.User;
import com.fantasy.matchupexecutor.model.matchup.Matchup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class TestMatchupExecutor {
	
	private static final String MATCHUP_PATH = "http://localhost:8083/matchup/execute";
	private static final String DATABASE_MANAGER_API_PATH = "http://localhost:8080/player/get/";

	@Test
	public void test() {
		Matchup m = new Matchup(2);
		User a = new User(0, "brady");
		a.setTeam(buildTestTeam1());
		
		User b = new User(1, "rodgers");
		b.setId(1);
		b.setTeam(buildTestTeam2());
		
		m.addUser(a);
		m.addUser(b);
		
		Matchup m2 = sendMatchupPost(m);
		System.out.println("winner: " + m2.getResults().getWinner().getUserName());
	}
	
	
	private Matchup sendMatchupPost(Matchup matchup) {
		Matchup result;
		String requestJson = null;
		try {
			requestJson = new ObjectMapper().writeValueAsString(matchup);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(requestJson);
		RestTemplate restTemplate = new RestTemplate();
		result = restTemplate.postForObject(MATCHUP_PATH, matchup, Matchup.class);
		return result;
	}
	
	private Team buildTestTeam1() {
		Team team =  new Team();
		team.addQb(getTestPlayer("Tom Brady"));
		team.addRb(getTestPlayer("Dalvin Cook"));
		team.addWr(getTestPlayer("Antonio Brown"));
		return team;
	}
	
	private Team buildTestTeam2() {
		Team team =  new Team();
		team.addQb(getTestPlayer("Aaron Rodgers"));
		team.addRb(getTestPlayer("Todd Gurley"));
		team.addWr(getTestPlayer("Julio Jones"));
		return team;
	}
	
	private Player getTestPlayer(String name) {
		name = name.replace(" ", "%20");
		Player updated = null;
		String json = makeCall(name);
		try {
			updated = new ObjectMapper().readValue(json, Player.class);
		} catch (Exception e) {
			System.out.println();
		}
		return updated;
	}
	
	private String makeCall(String name) {
		String responseJson = null;
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.sendGET(DATABASE_MANAGER_API_PATH + name);
			responseJson = userAgent.json.toString();
		} catch (ResponseException e) {
		}
		return responseJson;
	}

}
