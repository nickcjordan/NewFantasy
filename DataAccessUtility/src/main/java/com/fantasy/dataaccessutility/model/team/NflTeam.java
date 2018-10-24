package com.fantasy.dataaccessutility.model.team;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class NflTeam {
	
	private String nflTeamId;
	private String nflTeamName;
	private String abbreviation;
	private String city;
	private String mascot;
	private String logoIconUrl;
//	private int modifierGeneratorHitCount;
	
	public NflTeam() {}	
	
	public NflTeam(String nflTeamId, String nflTeamName, String abbreviation, String city, String mascot, String logoIconUrl) {
		this.nflTeamId = nflTeamId;
		this.nflTeamName = nflTeamName;
		this.abbreviation = abbreviation;
		this.city = city;
		this.mascot = mascot;
		this.logoIconUrl = logoIconUrl;
	}

	public String getNflTeamId() {
		return nflTeamId;
	}

	public void setNflTeamId(String nflTeamId) {
		this.nflTeamId = nflTeamId;
	}

	public String getNflTeamName() {
		return nflTeamName;
	}

	public void setNflTeamName(String nflTeamName) {
		this.nflTeamName = nflTeamName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMascot() {
		return mascot;
	}

	public void setMascot(String mascot) {
		this.mascot = mascot;
	}

	public String getLogoIconUrl() {
		return logoIconUrl;
	}

	public void setLogoIconUrl(String logoIconUrl) {
		this.logoIconUrl = logoIconUrl;
	}

//	public int getModifierGeneratorHitCount() {
//		return modifierGeneratorHitCount;
//	}
//	
//	public void resetModifierGeneratorHitCount() {
//		modifierGeneratorHitCount = 0;
//	}
//	
//	public void incrementModifierGeneratorHitCount() {
//		modifierGeneratorHitCount++;
//	}

}
