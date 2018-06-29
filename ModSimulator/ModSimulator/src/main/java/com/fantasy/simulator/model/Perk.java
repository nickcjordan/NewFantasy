package com.fantasy.simulator.model;

public class Perk extends Modifier {

	private int id;
	private String perkName;
	private int price;
	private double percentage;
	private String target;
	private TargetType targetType;
	
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	
}
