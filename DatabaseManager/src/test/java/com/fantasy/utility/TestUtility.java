package com.fantasy.utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fantasy.dbmanager.model.Player;
import com.fantasy.dbmanager.model.Position;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestUtility {

	@Test
	public void test() {
		List<Player> players = new ArrayList<Player>();
		Player p = new Player();
		p.setByeWeek("9");
		p.setId(2);
		p.setPlayerName("test player");
		p.setPlayerRank("8");
		p.setPosition(Position.KICKER);
		p.setPositionRank("1");
		p.setTeamName("longhorns");
		players.add(p);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(players);
		System.out.println(json);
	}

}
