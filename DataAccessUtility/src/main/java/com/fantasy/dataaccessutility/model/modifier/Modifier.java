package com.fantasy.dataaccessutility.model.modifier;

import com.fantasy.dataaccessutility.model.Position;

public class Modifier {
	
	private int price;
	private double changePercentage;
	private String targetId;
	private TargetType targetType;
	private Position targetPosition;
	
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
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	public Position getTargetPosition() {
		return targetPosition;
	}
	public void setTargetPosition(Position targetPosition) {
		this.targetPosition = targetPosition;
	}
	
}
