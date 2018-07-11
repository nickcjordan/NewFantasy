package com.fantasy.matchupexecutor.modapplicator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Team;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;

@Component
public class TargetDeterminator {

	public Player determineTargetPlayer(String targetId, User user, User opponent) {
		for (Player p : user.getTeam().getListOfPlayers()) {
			if (p.getPlayerId().equals(targetId)) {
				return p;
			}
		}
		for (Player p : opponent.getTeam().getListOfPlayers()) {
			if (p.getPlayerId().equals(targetId)) {
				return p;
			}
		}
		return null;
	}

	public Team determineTargetTeam(String targetId, User user, User opponent) {
		if (user.getTeam().getId().equals(targetId)) {
			return user.getTeam();
		}
		if (opponent.getTeam().getId().equals(targetId)) {
			return opponent.getTeam();
		}
		return null;
	}

	public List<Player> determineTargetPositionGroup(Modifier mod, User user, User opponent) {
		Team team = determineTargetTeam(mod.getTargetId(), user, opponent);
		switch (mod.getTargetPosition()) {
			case QUARTERBACK : return team.getQb();
			case RUNNINGBACK : return team.getRb();
			case WIDERECEIVER : return team.getWr();
			case TIGHTEND : return team.getTe();
			case DEFENSE : return team.getD();
			case KICKER : return team.getK();
		}
		return null;
	}

}
