package com.fantasy.dbmanager.model;

import java.util.List;
import java.util.Map;

import com.fantasy.dataaccessutility.model.matchup.MatchupUserResult;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.Perk;
import com.fantasy.dataaccessutility.model.team.Team;

public class UserTO {
	
	private String userId;
	private String userName;
	private Map<String, Perk> perkTree;
	private List<String> modifiers;
	private TeamTO team;
	private int coins;
	private Map<String, MatchupUserResult> matchupResults;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		this.userId = id;
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
	public List<String> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	public TeamTO getTeam() {
		return team;
	}
	public void setTeam(TeamTO team) {
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
	
}
