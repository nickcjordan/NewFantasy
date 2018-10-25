package com.fantasy.dataaccessutility.model.to;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fantasy.dataaccessutility.model.matchup.MatchupSchedule;
import com.fantasy.dataaccessutility.model.matchup.MatchupUserResult;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.Perk;
import com.fantasy.dataaccessutility.model.team.Team;

@DynamoDBTable(tableName = "user-table")
public class UserTO {
	
	private String userId;
	private String userName;
//	private Map<String, Perk> perkTree;
	private List<String> modifiers;
	private TeamTO team;
	private int coins;
	private Map<String, MatchupUserResult> matchupResults;
	private MatchupSchedule matchupSchedule;
	
	@DynamoDBHashKey(attributeName = "userId")
	public String getUserId() {
		return userId;
	}
	@DynamoDBAttribute
	public String getUserName() {
		return userName;
	}
//	@DynamoDBAttribute
//	public Map<String, Perk> getPerkTree() {
//		return perkTree;
//	}
	@DynamoDBAttribute
	public List<String> getModifiers() {
		return modifiers;
	}
	@DynamoDBAttribute
	public TeamTO getTeam() {
		return team;
	}
	@DynamoDBAttribute
	public int getCoins() {
		return coins;
	}
	@DynamoDBAttribute
	public Map<String, MatchupUserResult> getMatchupResults() {
		return matchupResults;
	}
	@DynamoDBAttribute
	public MatchupSchedule getMatchupSchedule() {
		return matchupSchedule;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
//	public void setPerkTree(Map<String, Perk> perkTree) {
//		this.perkTree = perkTree;
//	}
	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	public void setTeam(TeamTO team) {
		this.team = team;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public void setMatchupResults(Map<String, MatchupUserResult> matchupResults) {
		this.matchupResults = matchupResults;
	}
	public void setMatchupSchedule(MatchupSchedule matchupSchedule) {
		this.matchupSchedule = matchupSchedule;
	}
	
}
