package com.fantasy.matchupexecutor.modapplicator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fantasy.matchupexecutor.model.NFLPlayerWeeklyStats;
import com.fantasy.matchupexecutor.model.NewFantasyWeeklyStats;
import com.fantasy.matchupexecutor.model.Player;
import com.fantasy.matchupexecutor.model.Team;
import com.fantasy.matchupexecutor.model.User;
import com.fantasy.matchupexecutor.model.matchup.Matchup;
import com.fantasy.matchupexecutor.model.modifier.Modifier;
import com.fantasy.matchupexecutor.model.modifier.PositionGroup;

@Component
public class ModifierApplicator {
	
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
				case PLAYER : applyModifierOnPlayer(mod); break;
				case TEAM: applyModifierOnOpponent(mod); break;
				case POSITION: applyModifierOnPositionGroup(mod); break;
			}
		}
	}

	private void applyModifierOnOpponent(Modifier mod) {
		Team opponentTeam = (Team) mod.getTarget();
		for (List<Player> positionGroup : opponentTeam.getListOfPlayersByPosition()) {
			for (Player player : positionGroup) {
				player.getNewFantasyWeeklyStats().get(weekNumber).setNewFantasyPointTotal(calculatePointTotal(player, mod));
			}
		}
	}

	private double calculatePointTotal(Player player, Modifier mod) {
		double newTotal = player.getNewFantasyWeeklyStats().get(weekNumber).getNewFantasyPointTotal();
		double originalTotal = player.getWeeklyStats().get(weekNumber).getWeeklyPoints();
		double initialPointTotal = (newTotal != 0) ? newTotal : (originalTotal != 0) ? originalTotal : 0;
		double adjustedPointTotal = initialPointTotal + (initialPointTotal * mod.getChangePercentage());
		return adjustedPointTotal;
	}

	private void applyModifierOnPositionGroup(Modifier mod) {
		PositionGroup positionGroup = (PositionGroup) mod.getTarget();
		for (Player player : positionGroup.getPlayers()) {
			player.getNewFantasyWeeklyStats().get(weekNumber).setNewFantasyPointTotal(calculatePointTotal(player, mod));
		}
	}

	private void applyModifierOnPlayer(Modifier mod) {
		Player player = (Player) mod.getTarget();
		player.getNewFantasyWeeklyStats().get(weekNumber).setNewFantasyPointTotal(calculatePointTotal(player, mod));
	}
	
}
