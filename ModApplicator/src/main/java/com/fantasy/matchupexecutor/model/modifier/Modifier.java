package com.fantasy.matchupexecutor.model.modifier;

public abstract class Modifier {
	
	private int price;
	private double changePercentage;
	ModifiableTarget target;
	private TargetType targetType;
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public double getChangePercentage() {
		return changePercentage;
	}
	
	public void setChangePercentage(double changePercentage) {
		this.changePercentage = changePercentage;
	}
	
	public ModifiableTarget getTarget() {
		return target;
	}
	
	public void setTarget(ModifiableTarget target) {
		this.target = target;
	}
	
	public TargetType getTargetType() {
		return targetType;
	}
	
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	
}
