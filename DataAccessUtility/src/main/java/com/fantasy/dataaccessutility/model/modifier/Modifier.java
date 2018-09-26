package com.fantasy.dataaccessutility.model.modifier;

import com.fantasy.dataaccessutility.model.Position;

public class Modifier {
	
	private String modifierId;
	private String modifierName;
	private String modifierDescription;
	private int price;
	private double changePercentage;
	private String targetId;
	private TargetType targetType;
	private Position targetPosition;
	private String owningUserId;
	
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifierName() {
		return modifierName;
	}
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}
	public String getModifierDescription() {
		return modifierDescription;
	}
	public void setModifierDescription(String modifierDescription) {
		this.modifierDescription = modifierDescription;
	}
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
	public String getOwningUserId() {
		return owningUserId;
	}
	public void setOwningUserId(String owningUserId) {
		this.owningUserId = owningUserId;
	}
}
