package com.fantasy.dbmanager.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.model.TeamTO;

@Component
public class TeamTransformer {
	
	@Autowired
	private PlayerDao playerDao;

	public TeamTO getTO(Team team) {
		TeamTO teamTo = new TeamTO();
		teamTo.setAbbrev(team.getAbbrev());
		teamTo.setCity(team.getCity());
		teamTo.setFullName(team.getFullName());
		teamTo.setId(team.getId());
		teamTo.setName(team.getName());
		teamTo.setD(buildIdListFromPlayers(team.getD()));
		teamTo.setK(buildIdListFromPlayers(team.getK()));
		teamTo.setQb(buildIdListFromPlayers(team.getQb()));
		teamTo.setRb(buildIdListFromPlayers(team.getRb()));
		teamTo.setTe(buildIdListFromPlayers(team.getTe()));
		teamTo.setWr(buildIdListFromPlayers(team.getWr()));
		return teamTo;
	}
	

	private List<String> buildIdListFromPlayers(List<Player> players) {
		List<String> ids = new ArrayList<String>();
		for (Player p : players) {
			ids.add(p.getPlayerId());
		}
		return ids;
	}
	
	public Team getTeam(TeamTO teamTo) {
		Team team = new Team();
		team.setAbbrev(teamTo.getAbbrev());
		team.setCity(teamTo.getCity());
		team.setFullName(teamTo.getFullName());
		team.setId(teamTo.getId());
		team.setName(teamTo.getName());
		team.setD(buildPlayerListFromIds(teamTo.getD()));
		team.setK(buildPlayerListFromIds(teamTo.getK()));
		team.setQb(buildPlayerListFromIds(teamTo.getQb()));
		team.setRb(buildPlayerListFromIds(teamTo.getRb()));
		team.setTe(buildPlayerListFromIds(teamTo.getTe()));
		team.setWr(buildPlayerListFromIds(teamTo.getWr()));
		return team;
	}
	

	private List<Player> buildPlayerListFromIds(List<String> ids) {
		List<Player> players = new ArrayList<Player>();
		for (String id : ids) {
			players.add(playerDao.getPlayerByID(id));
		}
		return players;
	}

	
}
