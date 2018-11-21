package com.fantasy.dbmanager.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Positions;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.team.PlayerList;
import com.fantasy.dataaccessutility.model.team.Roster;
import com.fantasy.dataaccessutility.model.ui.EditLineupAction;
import com.fantasy.dataaccessutility.model.ui.EditLineupQuery;
import com.fantasy.dataaccessutility.model.ui.EditLineupRequest;
import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.manager.UserDatabaseManager;

@Component
public class EditLineupRequestProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(EditLineupRequestProcessor.class);

	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private PlayerDatabaseManager playerManager;
	

	public void editLineup(EditLineupRequest request) {
		User user = userManager.get(request.getUserId());
		Player player = playerManager.get(request.getPlayerId());
		EditLineupAction action = EditLineupAction.getActionFromText(request.getAction());
		if (user != null && player != null && action != null) {
			executeActionFromRequest(user.getTeam().getRoster(), player, action, request);
		} else {
			log.error("EditLineupRequestProcessor :: ERROR trying to edit lineup :: could not find one or more elements from request :: user: " + request.getUserId() + ", player: " + request.getPlayerId() + ", action: " + request.getAction());
		}
		log.info("Edit lineup request executed :: saving team to database...");
		userManager.update(user);
	}

	private void executeActionFromRequest(Roster roster, Player player, EditLineupAction action, EditLineupRequest request) {
		switch(action) {
			case START_PLAYER_AT_POSITION: movePlayerFromBenchToLineupAtPosition(player, roster);
				break;
			case START_PLAYER_AT_FLEX: movePlayerFromBenchToLineupAtFlex(player, roster);
				break;
			case MOVE_PLAYER_TO_BENCH: movePlayerFromLineupToBench(player, roster);
				break;
			case DROP_PLAYER: dropPlayerFromTeam(player, roster);
				break;
			case ADD_PLAYER_TO_BENCH: addPlayerToBenchFromWaivers(player, roster);
				break;
			case SWAP: swapPlayers(action, player, roster, request);
				break;
		default:
			break;
		}
	}
	
	private void swapPlayers(EditLineupAction action, Player player, Roster roster, EditLineupRequest request) {
		Player p2 = playerManager.get(request.getPlayer2Id());
		if (p2 != null) {
			log.info("Swapping player: " + player.getPlayerName() + " with player: " + p2.getPlayerName());
			roster.swapBenchAndLineupPlayers(player, p2);
		} else {
			log.error("ERROR :: could not find second player in SWAP editLineupRequest: " + request.getPlayer2Id());
		}
	}

	private void movePlayerFromBenchToLineupAtPosition(Player player, Roster roster) {
		log.info("Adding player " + player.getPlayerName() + " to lineup at position: " + player.getPlayerPosition() + "  :: and removing from bench");
		roster.removePlayerFromBench(player);
		roster.addPlayerToLineupAndMoveReplacedPlayerToBench(player);
	}
	
	private void movePlayerFromBenchToLineupAtFlex(Player player, Roster roster) {
		log.info("Adding player " + player.getPlayerName() + " to lineup as flex :: removing from bench");
		roster.removePlayerFromBench(player);
		roster.addFlexPlayerToLineupAndMoveReplacedPlayerToBench(player);
	}

	private void movePlayerFromLineupToBench(Player player, Roster roster) {
		log.info("Removing player " + player.getPlayerName() + " from lineup :: adding to bench");
		roster.removePlayerFromLineup(player);
		roster.addPlayerToBench(player);
	}

	private void dropPlayerFromTeam(Player player, Roster roster) {
		log.info("Dropping player " + player.getPlayerName() + " from team");
		roster.dropPlayerFromRoster(player);
		playerManager.updatePlayer(player);
	}
	
	private void addPlayerToBenchFromWaivers(Player player, Roster roster) {
		log.info("Adding player " + player.getPlayerName() + " to the bench from waivers");
		roster.addPlayerToBench(player);
		playerManager.updatePlayer(player);
	}

	public List<Player> getLineupOptions(EditLineupQuery query) {
		List<Player> players = new ArrayList<Player>();
		User user = userManager.get(query.getUserId());
		Player player = playerManager.get(query.getPlayerId());
		String position = player.getPlayerPosition();
		
		if (position.equals(Positions.QUARTERBACK.getAbbrev())) {
			players.addAll(user.getTeam().getRoster().getStartingLineup().getQb().getPlayers());
		}
		else if (position.equals(Positions.RUNNINGBACK.getAbbrev())) {
			players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); 
			players.addAll(user.getTeam().getRoster().getStartingLineup().getFlex().getPlayers());
		}
		else if (position.equals(Positions.WIDERECEIVER.getAbbrev())) {
			players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); 
			players.addAll(user.getTeam().getRoster().getStartingLineup().getFlex().getPlayers());
		}
		else if (position.equals(Positions.TIGHTEND.getAbbrev())) {
			players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); 
			players.addAll(user.getTeam().getRoster().getStartingLineup().getFlex().getPlayers());
		}
		else if (position.equals(Positions.KICKER.getAbbrev())) {
			players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers());
		}
			
		log.info("Got " + players.size() + " players for swappable call for: " + player.getPlayerName());
		return players;
	}

}
