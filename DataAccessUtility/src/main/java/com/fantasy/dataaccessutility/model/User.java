package com.fantasy.dataaccessutility.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fantasy.dataaccessutility.model.matchup.MatchupSchedule;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.Perk;
import com.fantasy.dataaccessutility.model.team.Team;

@DynamoDBTable(tableName = "user-table")
public class User {

	private String userId;
	private String userName;
	private Map<String, Perk> perkTree;
	private List<Modifier> modifiers;
	private Team team;
	private int coins;
//	private Map<String, MatchupUserResult> matchupResults;
	private MatchupSchedule matchupSchedule;
	
	public User() {
		this.modifiers = new ArrayList<Modifier>();
		this.matchupSchedule = new MatchupSchedule(userId);
//		this.matchupResults = new HashMap<String, MatchupUserResult>();
	}
	
	public User(String id, String userName, Map<String, Perk> perkTree, Team team, int coins) {
		this.userId = id;
		this.userName = userName;
		this.perkTree = perkTree;
		this.team = team;
		this.coins = coins;
		this.modifiers = new ArrayList<Modifier>();
		this.matchupSchedule = new MatchupSchedule(userId);
//		this.matchupResults = new HashMap<String, MatchupUserResult>();
	}
	
	public User(String id, String userName) {
		this.userId = id;
		this.userName = userName;
		this.perkTree = new HashMap<String, Perk>();
		this.team = new Team(id, userName);
		this.coins = 0;
		this.modifiers = new ArrayList<Modifier>();
		this.matchupSchedule = new MatchupSchedule(userId);
//		this.matchupResults = new HashMap<String, MatchupUserResult>();
	}
	
	public void addMod(Modifier mod) {
		modifiers.add(mod);
	}
	@DynamoDBAttribute
	public List<Modifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}

	@DynamoDBHashKey(attributeName = "userId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String id) {
		this.userId = id;
	}
	@DynamoDBAttribute
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@DynamoDBAttribute
	public Map<String, Perk> getPerkTree() {
		return perkTree;
	}

	public void setPerkTree(Map<String, Perk> perkTree) {
		this.perkTree = perkTree;
	}
	@DynamoDBAttribute
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	@DynamoDBAttribute
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

//	public Map<String, MatchupUserResult> getMatchupResults() {
//		return matchupResults;
//	}
//
//	public void setMatchupResults(Map<String, MatchupUserResult> matchupResults) {
//		this.matchupResults = matchupResults;
//	}
	
	public void addCoins(int coins) {
		this.coins += coins;
	}
	
	public void subtractCoins(int coins) {
		this.coins -= coins;
	}
	@DynamoDBAttribute
	public MatchupSchedule getMatchupSchedule() {
		return matchupSchedule;
	}

	public void setMatchupSchedule(MatchupSchedule matchupSchedule) {
		this.matchupSchedule = matchupSchedule;
	}
}
