package com.fantasy.dataaccessutility.model.modifier;

import java.math.BigDecimal;

import com.fantasy.dataaccessutility.model.Position;
import com.fantasy.dataaccessutility.model.team.NflTeam;
import com.fasterxml.jackson.annotation.JsonGetter;

public class Modifier {
	
	private String modifierId;
	private String modifierName;
	private String modifierDescription;
	private int price;
	private double changePercentage;
	private String targetId;
	private TargetType targetType;
	private Position targetPosition;
	private NflTeam targetNflTeam;
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
		return truncate(changePercentage);
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
	public NflTeam getTargetNflTeam() {
		return targetNflTeam;
	}
	public void setTargetNflTeam(NflTeam targetNflTeam) {
		this.targetNflTeam = targetNflTeam;
	}
	
	private double truncate(double x) {
		int property = (x > 0) ? BigDecimal.ROUND_FLOOR : BigDecimal.ROUND_CEILING;
		return new BigDecimal(String.valueOf(x)).setScale(2, property).doubleValue();
	}
	
}
