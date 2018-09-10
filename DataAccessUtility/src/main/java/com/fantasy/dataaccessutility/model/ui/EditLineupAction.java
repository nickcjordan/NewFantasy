package com.fantasy.dataaccessutility.model.ui;

import org.apache.commons.lang3.StringUtils;

public enum EditLineupAction {
	
	MOVE_PLAYER_TO_BENCH("MOVE_TO_BENCH"),
	START_PLAYER_AT_POSITION("START_AT_POSITION"),
	START_PLAYER_AT_FLEX("START_AT_FLEX"),
	DROP_PLAYER("DROP"),
	SWAP("SWAP");
	
	private String actionText;
	
	EditLineupAction(String text) {
		this.actionText = text;
	}
	
	public static EditLineupAction getActionFromText(String text) {
		if (StringUtils.isNumeric(text)) {
			EditLineupAction a = EditLineupAction.SWAP;
			a.setActionText(text);
			return a;
		}
		for (EditLineupAction action : EditLineupAction.values()) {
			if (action.getActionText().equals(text)) {
				return action;
			}
		}
		return null;
	}

	public String getActionText() {
		return actionText;
	}

	public void setActionText(String actionText) {
		this.actionText = actionText;
	}
	
}
