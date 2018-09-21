package com.fantasy.dataaccessutility.access;

import java.net.URI;

public class Urls {
	
	private static final String MANAGER = "http://localhost:8080";
	
	private static final String PLAYER = "/player";
	private static final String USER = "/user";
	private static final String TEAM = "/team";

	private static final String GET = "/get/";
	private static final String GET_ALL = "/getAll/";
	private static final String PUT = "/put/";
	private static final String UPDATE = "/update";
	private static final String UPDATE_ALL = "/updateAll";
	
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

	public static String updatePlayer() {
		return MANAGER + PLAYER + UPDATE;
	}

	public static String putUser() {
		return MANAGER + USER + PUT;
	}
	
	public static String updateUser() {
		return MANAGER + USER + UPDATE;
	}
	
	public static String updateUsers() {
		return MANAGER + USER + UPDATE_ALL;
	}

	public static String putTeam() {
		return MANAGER + TEAM + PUT;
	}

	public static String getAllPosition(String position) {
		return MANAGER + PLAYER + GET_ALL + position;
	}

	public static URI putModifer() {
		return null; // TODO
	}

	public static URI putModifier() {
		return null; // TODO
	}

	public static URI getModifier(String modifierId) {
		return null; // TODO
	}

	public static URI putModifiers() {
		return null; // TODO
	}

}
