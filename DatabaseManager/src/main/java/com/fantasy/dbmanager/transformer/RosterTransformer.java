package com.fantasy.dbmanager.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.team.BenchPlayers;
import com.fantasy.dataaccessutility.model.team.PlayerList;
import com.fantasy.dataaccessutility.model.team.Roster;
import com.fantasy.dataaccessutility.model.team.StartingLineup;
import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.model.BenchPlayersTO;
import com.fantasy.dbmanager.model.PlayerListTO;
import com.fantasy.dbmanager.model.RosterTO;
import com.fantasy.dbmanager.model.StartingLineupTO;

@Component
public class RosterTransformer {
	
	@Autowired
	private PlayerDao playerDao;
	
	public Roster transformRosterTO(RosterTO rosterTO) {
		Roster roster = new Roster();
		roster.setStartingLineup(buildStartingLineupFromTO(rosterTO.getStartingLineup()));
		roster.setBenchPlayers(buildBenchPlayersFromTO(rosterTO.getBenchPlayers()));
		return roster;
	}

	public RosterTO transformRoster(Roster roster) {
		RosterTO to = new RosterTO();
		to.setStartingLineup(buildStartingLineupTOFromObject(roster.getStartingLineup()));
		to.setBenchPlayers(buildBenchPlayersTOFromObject(roster.getBenchPlayers()));
		return to;
	}
	
	private StartingLineupTO buildStartingLineupTOFromObject(StartingLineup startingLineup) {
		StartingLineupTO to = new StartingLineupTO();
		to.setQb(buildPlayerListTOFromObject(startingLineup.getQb()));
		to.setRb(buildPlayerListTOFromObject(startingLineup.getRb()));
		to.setWr(buildPlayerListTOFromObject(startingLineup.getWr()));
		to.setTe(buildPlayerListTOFromObject(startingLineup.getTe()));
		to.setFlex(buildPlayerListTOFromObject(startingLineup.getFlex()));
		to.setK(buildPlayerListTOFromObject(startingLineup.getK()));
		to.setDst(buildPlayerListTOFromObject(startingLineup.getDst()));
		return to;
	}

	private BenchPlayersTO buildBenchPlayersTOFromObject(BenchPlayers benchPlayers) {
		BenchPlayersTO to = new BenchPlayersTO();
		for (Player p : benchPlayers.getPlayers()) {
			to.addPlayerIdToBenchTO(p.getPlayerId());
		}
		return to;
	}
	
	private PlayerListTO buildPlayerListTOFromObject(PlayerList list) {
		PlayerListTO to = new PlayerListTO();
		for (Player p : list.getPlayers()) {
			to.addPlayerId(p.getPlayerId());
		}
		return to;
	}

	private StartingLineup buildStartingLineupFromTO(StartingLineupTO startingLineupTo) {
		StartingLineup lineup = new StartingLineup();
		lineup.setQb(buildPlayerListFromTO(startingLineupTo.getQb(), lineup.getQb().getMax()));
		lineup.setRb(buildPlayerListFromTO(startingLineupTo.getRb(), lineup.getRb().getMax()));
		lineup.setWr(buildPlayerListFromTO(startingLineupTo.getWr(), lineup.getWr().getMax()));
		lineup.setTe(buildPlayerListFromTO(startingLineupTo.getTe(), lineup.getTe().getMax()));
		lineup.setFlex(buildPlayerListFromTO(startingLineupTo.getFlex(), lineup.getFlex().getMax()));
		lineup.setK(buildPlayerListFromTO(startingLineupTo.getK(), lineup.getK().getMax()));
		lineup.setDst(buildPlayerListFromTO(startingLineupTo.getDst(), lineup.getDst().getMax()));
		return lineup;
	}	

	private BenchPlayers buildBenchPlayersFromTO(BenchPlayersTO benchPlayers) {
		BenchPlayers players = new BenchPlayers();
		for (String id : benchPlayers.getPlayerIds()) {
			players.addPlayerToBench(playerDao.getPlayerByID(id));
		}
		return players;
	}

	private PlayerList buildPlayerListFromTO(PlayerListTO to, int max) {
		PlayerList list = new PlayerList(max);
		for (String id : to.getPlayerIds()) {
			list.addPlayer(playerDao.getPlayerByID(id));
		}
		return list;
	}

}
