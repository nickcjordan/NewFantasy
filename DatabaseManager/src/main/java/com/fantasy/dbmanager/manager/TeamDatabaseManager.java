package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dbmanager.dao.TeamDao;
import com.fantasy.dbmanager.model.TeamTO;
import com.fantasy.dbmanager.transformer.TeamTransformer;

@Component
public class TeamDatabaseManager {
	
	private static Logger log = Logger.getLogger(TeamDatabaseManager.class);
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private TeamTransformer teamTransformer;

	public void put(List<Team> teams) {
		List<TeamTO> teamTos = new ArrayList<TeamTO>();
		for (Team team : teams) {
			teamTos.add(teamTransformer.getTO(team));
		}
		teamDao.put(teamTos);
	}

	public long count() {
		return teamDao.getTeamCount();
	}
	
	public List<Team> get() {
		List<Team> teams = new ArrayList<Team>();
		for (TeamTO teamTo : teamDao.getAll()) {
			teams.add(teamTransformer.getTeam(teamTo));
		}
		return teams;
	}

	public boolean clear() {
		return teamDao.removeAll();
	}

}
