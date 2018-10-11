package com.fantasy.dbmanager.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.team.Team;
import com.fantasy.dataaccessutility.model.to.TeamTO;

@Component
public class TeamTransformer {
	
	@Autowired
	private RosterTransformer rosterTransformer;

	public TeamTO getTO(Team team) {
		TeamTO teamTo = new TeamTO();
		teamTo.setAbbrev(team.getAbbrev());
		teamTo.setCity(team.getCity());
		teamTo.setFullName(team.getFullName());
		teamTo.setId(team.getId());
		teamTo.setName(team.getTeamName());
		teamTo.setRosterTO(rosterTransformer.transformRoster(team.getRoster()));
		return teamTo;
	}
	
	public Team getTeam(TeamTO teamTo) {
		Team team = new Team();
		team.setAbbrev(teamTo.getAbbrev());
		team.setCity(teamTo.getCity());
		team.setFullName(teamTo.getFullName());
		team.setId(teamTo.getId());
		team.setTeamName(teamTo.getName());
		team.setRoster(rosterTransformer.transformRosterTO(teamTo.getRosterTO()));
		return team;
	}
	
}
