package com.fantasy.dataaccessutility.model.to;

import java.util.List;
import java.util.Map;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.team.Roster;

public class TeamTO {
	
	private String teamId;
	private String name;
	private String city;
	private String fullName;
	private String abbrev;
	private RosterTO roster;
	
	public String getId() {
		return teamId;
	}
	public void setId(String id) {
		this.teamId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	public RosterTO getRosterTO() {
		return roster;
	}
	public void setRosterTO(RosterTO roster) {
		this.roster = roster;
	}
}
