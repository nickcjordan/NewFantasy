package com.fantasy.dbmanager.manager;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.dao.TeamDao;
import com.fantasy.dbmanager.dao.TeamDao;
import com.fantasy.dbmanager.model.Team;
import com.fantasy.dbmanager.model.Team;
import com.mongodb.client.result.DeleteResult;

@Component
public class TeamDatabaseManager {
	
	private static Logger log = Logger.getLogger(TeamDatabaseManager.class);
	
	@Autowired
	private TeamDao teamDao;

	public void put(List<Team> teams) {
		teamDao.put(teams);
	}
	
	public long count() {
		return teamDao.getTeamCount();
	}
	
	public List<Team> get() {
		List<Team> teams = new ArrayList<Team>();
		for (Team p : teamDao.getAll()) {
			teams.add(p);
		}
		return teams;
	}

	public boolean clear() {
		return teamDao.removeAll();
	}

}
