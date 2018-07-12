package com.fantasy.dataaccessutility.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.access.DatabaseManagerDelegate;
import com.fantasy.dataaccessutility.model.Team;

@Component
public class TeamDataAccessManager {
	
	private static Logger log = Logger.getLogger(TeamDataAccessManager.class);
	
	@Autowired
	private DatabaseManagerDelegate delegate;
	
	public Team getTeamById(String teamId) {
		log.info("Getting updated stats for team " + teamId);
		return delegate.getUpdatedTeam(teamId);
	}
	public Team getTeamFromTeam(Team team) {
		return getTeamById(team.getId());
	}
	public List<Team> getTeamsByIdList(List<String> teamIds) {
		List<Team> teams = new ArrayList<Team>();
		for (String id : teamIds) {
			teams.add(getTeamById(id));
		}
		return teams;
	}
	public List<Team> getTeamsFromTeamList(List<Team> teamsRequest) {
		List<Team> teams = new ArrayList<Team>();
		for (Team p : teamsRequest) {
			teams.add(getTeamById(p.getId()));
		}
		return teams;
	}
	
	public void updateTeam(Team team) {
		log.info("Updating stats for team " + team.getId());
		delegate.updateTeam(team);
	}
	public void updateTeams(List<Team> teams) {
		for (Team team : teams) { updateTeam(team); }
	}

}
