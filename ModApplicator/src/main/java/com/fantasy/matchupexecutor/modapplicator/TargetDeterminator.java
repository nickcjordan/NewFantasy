package com.fantasy.matchupexecutor.modapplicator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.team.PlayerList;
import com.fantasy.dataaccessutility.model.team.Team;

@Component
public class TargetDeterminator {

	public Player determineTargetPlayer(String targetId, User user, User opponent) {
		for (Player p : user.getTeam().getRoster().getAllPlayers()) {
			if (p.getPlayerId().equals(targetId)) {
				return p;
			}
		}
		for (Player p : opponent.getTeam().getRoster().getAllPlayers()) {
			if (p.getPlayerId().equals(targetId)) {
				return p;
			}
		}
		return null;
	}

	public Team determineTargetTeam(String targetId, User user, User opponent) {
		return (user.getTeam().getId().equals(targetId)) ? user.getTeam() : (opponent.getTeam().getId().equals(targetId)) ? opponent.getTeam() : null;
	}

	public PlayerList determineTargetPositionGroup(Modifier mod, User user, User opponent) {
		Team team = determineTargetTeam(mod.getTargetId(), user, opponent);
		switch (mod.getTargetPosition()) {
			case QUARTERBACK : return team.getRoster().getStartingLineup().getQb();
			case RUNNINGBACK : return team.getRoster().getStartingLineup().getRb();
			case WIDERECEIVER : return team.getRoster().getStartingLineup().getWr();
			case TIGHTEND : return team.getRoster().getStartingLineup().getTe();
			case DEFENSE : return team.getRoster().getStartingLineup().getDst();
			case KICKER : return team.getRoster().getStartingLineup().getK();
		}
		return null;
	}

}
