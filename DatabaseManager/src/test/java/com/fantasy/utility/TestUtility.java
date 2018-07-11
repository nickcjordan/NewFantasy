package com.fantasy.utility;

import org.junit.Test;

public class TestUtility {
	
//	TeamDatabaseController teamController = new TeamDatabaseController();
//	PlayerDatabaseController playerController = new PlayerDatabaseController();
//	
	
	@Test
	public void test() {
		
	}
	
//	@Test
//	public void testPlayerJSON() {
//		List<Player> testPlayers = buildTestPlayers();
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		
//		for (Player testy : testPlayers) {
//			String json = gson.toJson(testy);
//			System.out.println(json);
//		}
//	}
//
//	@Test
//	public void testTeamJSON() {
//		List<Team> teams = new ArrayList<Team>();
//		teams.add(getTestTeam());
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(teams);
//		System.out.println(json);
//	}
//	
//	@Test
//	public void testUserJSON() {
//		PerkTree perkTree = new PerkTree();
//		perkTree.setId(0);
//
//		User user = new User();
//		user.setCoins(5);
//		user.setId(0);
//		user.setPerkTree(perkTree);
//		user.setTeam(getTestTeam());
//		user.setUserName("nick");
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(user);
//		System.out.println(json);
//	}
//
//	private Team getTestTeam() {
//		Team t = new Team("testTeam", "testCity", 1, "TC");
//		t.addQb(new Player(1, Position.QUARTERBACK, "player1", "teamName1", "playerRank1", "positionRank1", "byeWeek1"));
//		t.addRb(new Player(2, Position.RUNNINGBACK, "player2", "teamName2", "playerRank2", "positionRank2", "byeWeek2"));
//		t.addWr(new Player(3, Position.WIDERECEIVER, "player3", "teamName3", "playerRank3", "positionRank3", "byeWeek3"));
//		t.addTe(new Player(4, Position.TIGHTEND, "player4", "teamName4", "playerRank4", "positionRank4", "byeWeek4"));
//		t.addD(new Player(5, Position.DEFENSE, "player5", "teamName5", "playerRank5", "positionRank5", "byeWeek5"));
//		t.addK(new Player(6, Position.KICKER, "player6", "teamName6", "playerRank6", "positionRank6", "byeWeek6"));
//		return t;
//	}
//	
//	@Test
//	public void addPlayersToDatabase() {
//		List<Player> testPlayers = buildTestPlayers();
//		playerController.putPlayer(testPlayers);
//	}
//
//	private List<Player> buildTestPlayers() {
//		List<Player> players = new ArrayList<Player>();
//		players.add(new Player(1, Position.QUARTERBACK, "player1", "teamName1", "playerRank1", "positionRank1", "byeWeek1"));
//		players.add(new Player(2, Position.RUNNINGBACK, "player2", "teamName2", "playerRank2", "positionRank2", "byeWeek2"));
//		players.add(new Player(3, Position.WIDERECEIVER, "player3", "teamName3", "playerRank3", "positionRank3", "byeWeek3"));
//		players.add(new Player(4, Position.TIGHTEND, "player4", "teamName4", "playerRank4", "positionRank4", "byeWeek4"));
//		players.add(new Player(5, Position.DEFENSE, "player5", "teamName5", "playerRank5", "positionRank5", "byeWeek5"));
//		players.add(new Player(6, Position.KICKER, "player6", "teamName6", "playerRank6", "positionRank6", "byeWeek6"));
//		return players;
//	}
//	
//	@Test
//	public void addTeamToDatabase() {
//		List<Team> testPlayers = buildTestTeams();
//		teamController.putTeam(testPlayers);
//	}
//
//	private List<Team> buildTestTeams() {
//		List<Team> teams = new ArrayList<Team>();
//		Team t = new Team();
//		t.addQb(new Player(1, Position.QUARTERBACK, "player1", "teamName1", "playerRank1", "positionRank1", "byeWeek1"));
//		t.addRb(new Player(2, Position.RUNNINGBACK, "player2", "teamName2", "playerRank2", "positionRank2", "byeWeek2"));
//		t.addWr(new Player(3, Position.WIDERECEIVER, "player3", "teamName3", "playerRank3", "positionRank3", "byeWeek3"));
//		t.addTe(new Player(4, Position.TIGHTEND, "player4", "teamName4", "playerRank4", "positionRank4", "byeWeek4"));
//		t.addD(new Player(5, Position.DEFENSE, "player5", "teamName5", "playerRank5", "positionRank5", "byeWeek5"));
//		t.addK(new Player(6, Position.KICKER, "player6", "teamName6", "playerRank6", "positionRank6", "byeWeek6"));
//		teams.add(t);
//		return teams;
//	}
	
}
