package com.fantasy.dataaccessutility.access;

public class Urls {
	
	private static final String MANAGER = "http://localhost:8080";
	
	private static final String PLAYER = "/player";
	private static final String USER = "/user";
	private static final String TEAM = "/team";

	private static final String GET = "/get/";
	private static final String PUT = "/put/";
	
	public static String getPlayer(String playerId) {
		return MANAGER + PLAYER + GET + playerId;
	}

	public static String getUser(String userId) {
		return MANAGER + USER + GET + userId;
	}

	public static String getTeam(String teamId) {
		return MANAGER + TEAM + GET + teamId;
	}

	public static String putPlayer() {
		return MANAGER + PLAYER + PUT;
	}

	public static String putUser() {
		return MANAGER + USER + PUT;
	}

	public static String putTeam() {
		return MANAGER + TEAM + PUT;
	}
}
