package com.fantasy.dataaccessutility.model.modifier;

import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fantasy.dataaccessutility.model.Position;
import com.fantasy.dataaccessutility.model.team.NflTeam;

@DynamoDBDocument
@DynamoDBTable(tableName = "modifier-table")
public class Modifier {
	
	private String modifierId;
	private String modifierName;
	private String modifierDescription;
	private int price;
	private double changePercentage;
	private String targetUserId;
	private String targetPlayerId;
	private TargetType targetType;
	private Position targetPosition;
	private NflTeam targetNflTeam;
	private String owningUserId;
	
	@DynamoDBHashKey(attributeName = "modifierId")
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	@DynamoDBAttribute
	public String getModifierName() {
		return modifierName;
	}
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}
	@DynamoDBAttribute
	public String getModifierDescription() {
		return modifierDescription;
	}
	public void setModifierDescription(String modifierDescription) {
		this.modifierDescription = modifierDescription;
	}
	@DynamoDBAttribute
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@DynamoDBAttribute
	public double getChangePercentage() {
		return truncate(changePercentage);
	}
	public void setChangePercentage(double changePercentage) {
		this.changePercentage = changePercentage;
	}
	@DynamoDBAttribute
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	@DynamoDBAttribute
	public Position getTargetPosition() {
		return targetPosition;
	}
	public void setTargetPosition(Position targetPosition) {
		this.targetPosition = targetPosition;
	}
	@DynamoDBAttribute
	public String getOwningUserId() {
		return owningUserId;
	}
	public void setOwningUserId(String owningUserId) {
		this.owningUserId = owningUserId;
	}
	@DynamoDBAttribute
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
	@DynamoDBAttribute
	public String getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	@DynamoDBAttribute
	public String getTargetPlayerId() {
		return targetPlayerId;
	}
	public void setTargetPlayerId(String targetPlayerId) {
		this.targetPlayerId = targetPlayerId;
	}
	
}
