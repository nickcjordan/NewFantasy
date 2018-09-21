package com.fantasy.dataaccessutility.model.modifier;

public class Perk extends Modifier {

	private int perkId;
	private String perkName;
	private int tier;
	
	public Perk() {}
	
	public int getPerkId() {
		return perkId;
	}

	public void setPerkId(int perkId) {
		this.perkId = perkId;
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
