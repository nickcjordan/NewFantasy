package com.fantasy.dbmanager.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.TargetType;

@Component
public class DatabasePopulator {
	
	@Autowired
	private DataAccessUtility data;
	
	public void populate() {
		
		User a = new User("0", "team brady");
		a.setTeam(buildTestTeam1());
		a.setId("0");
		
		User b = new User("1", "team rodgers");
		b.setTeam(buildTestTeam2());
		b.setId("1");
		
		Modifier mod = new Modifier();
		mod.setChangePercentage(50.0);
		mod.setTargetId(b.getTeam().getId());
		mod.setTargetType(TargetType.TEAM);
		a.addMod(mod);
		
		data.updateUser(a);
		data.updateUser(b);
	}
	
	private Team buildTestTeam1() {
		Team team =  new Team();
		team.setId("0");
		team.addQb(data.getPlayerById("Tom Brady"));
		team.addRb(data.getPlayerById("Dalvin Cook"));
		team.addWr(data.getPlayerById("Antonio Brown"));
		return team;
	}
	
	private Team buildTestTeam2() {
		Team team =  new Team();
		team.setId("1");
		team.addQb(data.getPlayerById("Aaron Rodgers"));
		team.addRb(data.getPlayerById("Todd Gurley"));
		team.addWr(data.getPlayerById("Julio Jones"));
		return team;
	}
	
}
