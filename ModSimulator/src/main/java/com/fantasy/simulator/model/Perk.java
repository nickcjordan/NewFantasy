package com.fantasy.simulator.model;

public class Perk extends Modifier {

	private int id;
	private String perkName;
	private int tier;
	
	public Perk() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPerkName() {
		return perkName;
	}

	public void setPerkName(String perkName) {
		this.perkName = perkName;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}
	
}
