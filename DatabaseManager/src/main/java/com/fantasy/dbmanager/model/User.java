package com.fantasy.dbmanager.model;

public class User {

	int id;
	String userName;
	PerkTree perkTree;
	Team team;
	int coins;
	
	public User() {}
	
	public User(int id, String userName, PerkTree perkTree, Team team, int coins) {
		super();
		this.id = id;
		this.userName = userName;
		this.perkTree = perkTree;
		this.team = team;
		this.coins = coins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PerkTree getPerkTree() {
		return perkTree;
	}

	public void setPerkTree(PerkTree perkTree) {
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
	
}
