package com.fantasy.matchupexecutor.modapplicator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.ModifiedStats;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.dataaccessutility.model.modifier.Modifier;

@Component
public class ModifierApplicator {
	
	@Autowired
	private TargetDeterminator determinator;
	
	private String weekNumber;
	
	public void applyModsToMatchup(Matchup matchup) {
		weekNumber = Integer.toString(matchup.getWeekNumber());
		User userA = matchup.getUsers().get(0);
		User userB = matchup.getUsers().get(1);
		applyModifiersFromUser(userA, userB);
		applyModifiersFromUser(userB, userA);
	}

	private void applyModifiersFromUser(User user, User opponent) {
		for (Modifier mod : user.getModifiers()) {
			switch(mod.getTargetType()) {
				case PLAYER : 
					Player player = determinator.determineTargetPlayer(mod.getTargetId(), user, opponent);
					applyModifierOnPlayer(mod, player);
					break;
				case TEAM: 
					Team team = determinator.determineTargetTeam(mod.getTargetId(), user, opponent);
					applyModifierOnTeam(mod, team); 
					break;
				case POSITION: 
					List<Player> players = determinator.determineTargetPositionGroup(mod, user, opponent);
					applyModifierOnPositionGroup(mod, players); 
					break;
			}
			
		}
	}

	private void applyModifierOnTeam(Modifier mod, Team team) {
		for (List<Player> positionGroup : team.getListOfPlayersByPosition()) {
			for (Player player : positionGroup) {
				ModifiedStats stats = player.getModifiedStats().get(weekNumber);
				stats.setNewFantasyPointTotal(calculatePointTotal(player, mod));
			}
		}
		System.out.println();
	}

	private double calculatePointTotal(Player player, Modifier mod) {
		double newTotal = player.getModifiedStats().get(weekNumber).getNewFantasyPointTotal();
		double initialPointTotal = (newTotal != 0) ? newTotal : player.getStatsByWeek().get(weekNumber).getTotalPointsScored();
		double adjustedPointTotal = initialPointTotal + (initialPointTotal * ((mod.getChangePercentage())/100.00));
		return adjustedPointTotal;
	}

	private void applyModifierOnPositionGroup(Modifier mod, List<Player> players) {
		for (Player player : players) {
			player.getModifiedStats().get(weekNumber).setNewFantasyPointTotal(calculatePointTotal(player, mod));
		}
	}

	private void applyModifierOnPlayer(Modifier mod, Player player) {
		player.getModifiedStats().get(weekNumber).setNewFantasyPointTotal(calculatePointTotal(player, mod));
	}
	
}
