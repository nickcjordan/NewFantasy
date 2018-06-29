package com.fantasy.simulator.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fantasy.simulator.model.Perk;
import com.fantasy.simulator.model.Team;
import com.fantasy.simulator.model.User;

@Component
public class TestDataManager {
	
	public List<User> buildTestUsers() {
		List<User> users = new ArrayList<User>();
		
		users.add(buildTestUser(0, "nick"));
		
		return users;
	}

	private User buildTestUser(int id, String name) {
		User user = new User(id, name);
		
		user.setTeam(buildTestTeam(id, name));
		user.setPerkTree(buildTestPerkTree());
		
		return user;
	}
	
	private Map<String, Perk> buildTestPerkTree() {
		 Map<String, Perk> perkTree = new HashMap<String, Perk>();
		 
		 
		 
		 return perkTree;
	}

	private Perk buildTestPerk() {
		Perk perk = new Perk();
		
		// TODO design perk tree and modifiers
		
		return perk;
	}

	private Team buildTestTeam(int id, String name) {
		Team team = new Team(id, name);
		
		
		
		return team;
	}
	
}
