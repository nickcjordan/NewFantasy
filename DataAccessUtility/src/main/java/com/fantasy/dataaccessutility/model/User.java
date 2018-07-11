package com.fantasy.dataaccessutility.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantasy.dataaccessutility.model.matchup.MatchupUserResult;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.Perk;

public class User {

	String id;
	private String userName;
	private Map<String, Perk> perkTree;
	private List<Modifier> modifiers;
	private Team team;
	private int coins;
	private Map<String, MatchupUserResult> matchupResults;
	
	public User() {
		this.modifiers = new ArrayList<Modifier>();
		this.matchupResults = new HashMap<String, MatchupUserResult>();
	}
	
	public User(String id, String userName, Map<String, Perk> perkTree, Team team, int coins) {
		this.id = id;
		this.userName = userName;
		this.perkTree = perkTree;
		this.team = team;
		this.coins = coins;
		this.modifiers = new ArrayList<Modifier>();
		this.matchupResults = new HashMap<String, MatchupUserResult>();
	}
	
	public User(String id, String userName) {
		this.id = id;
		this.userName = userName;
		this.perkTree = new HashMap<String, Perk>();
		this.team = new Team(id, userName);
		this.coins = 0;
		this.modifiers = new ArrayList<Modifier>();
		this.matchupResults = new HashMap<String, MatchupUserResult>();
	}
	
	public void addMod(Modifier mod) {
		modifiers.add(mod);
	}
	
	public List<Modifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, Perk> getPerkTree() {
		return perkTree;
	}

	public void setPerkTree(Map<String, Perk> perkTree) {
		this.perkTree = perkTree;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public Map<String, MatchupUserResult> getMatchupResults() {
		return matchupResults;
	}

	public void setMatchupResults(Map<String, MatchupUserResult> matchupResults) {
		this.matchupResults = matchupResults;
	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public void subtractCoins(int coins) {
		this.coins -= coins;
	}
	
}
