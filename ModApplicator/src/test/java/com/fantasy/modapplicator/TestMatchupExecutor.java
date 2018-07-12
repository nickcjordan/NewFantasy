package com.fantasy.modapplicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.TargetType;
import com.fantasy.matchupexecutor.model.MatchupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class TestMatchupExecutor {
	
	private static final String MATCHUP_PATH = "http://localhost:8083/matchup/execute";
	private static final String DATABASE_MANAGER_API_PATH = "http://localhost:8080/player/get/";

	
	@Test
	public void test() {
		MatchupRequest request = new MatchupRequest();
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		request.setUserIds(list);
		request.setWeekNumber("1");
		Matchup m = sendMatchupPost(request);
		System.out.println("winner: " + m.getResults().getWinner().getUserName());
	}
	
	
	private Matchup sendMatchupPost(MatchupRequest request) {
		Matchup result;
		String requestJson = null;
		try {
			requestJson = new ObjectMapper().writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(requestJson);
		RestTemplate restTemplate = new RestTemplate();
		result = restTemplate.postForObject(MATCHUP_PATH, request, Matchup.class);
		return result;
	}

}
