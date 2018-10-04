package com.fantasy.dbmanager.populator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.model.Metadata;
import com.fantasy.dataaccessutility.model.Position;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.TargetType;
import com.fantasy.dataaccessutility.model.team.NflTeam;
import com.fantasy.dataaccessutility.model.team.constants.NflTeams;

@Component
public class ModifierGenerator {
	
	private static final String NEGATOR = "Negator";
	private static final String BOOSTER = "Booster";

	private static final Logger log = LoggerFactory.getLogger(ModifierGenerator.class);

	@Value("${generator.modifier.maxTeamModifiers}")
	private String maxTeamModifiers;
	@Value("${generator.modifier.maxPlayerModifiers}")
	private String maxPlayerModifiers;
	@Value("${generator.modifier.maxPositionModifiers}")
	private String maxPositionModifiers;
	
	@Value("${generator.modifier.maxPrice}")
	private String maxPrice;
	
	private Map<String, NflTeam> teamMap;
	private int currentIdToUse;
	private List<Integer> prices;
	private List<Integer> shuffledPrices;
	
	@Autowired
	private DataAccessUtility data;
	
	public ModifierGenerator() {
		this.teamMap = NflTeams.nflTeams;
		this.prices = Arrays.asList(100, 250, 500, 1000, 2000);
		this.shuffledPrices = new ArrayList<Integer>(prices);
	}
	
	public List<Modifier> generateWeeklyModifiers() {
		this.currentIdToUse = Integer.valueOf(data.getMetadata("currentIdToUse").getValue());
		List<Modifier> modifiers = new ArrayList<Modifier>();
		addTeamModifiers(modifiers);
		addPlayerModifiers(modifiers);
		addPositionModifiers(modifiers);
		Metadata m =new Metadata();
		m.setMetadataId("currentIdToUse");
		m.setValue(String.valueOf(this.currentIdToUse));
		data.updateMetadata(m);
		return modifiers;
	}

	private void addPositionModifiers(List<Modifier> modifiers) {
		log.info("Generating Position Modifiers");
		List<Position> positions = new ArrayList<Position>(Arrays.asList(Position.values()));
		Collections.shuffle(positions);
		int count = 1;
		for (int i = 0; i < Integer.valueOf(maxPositionModifiers)/2; i++) {
			if (count++ >= positions.size()) { Collections.shuffle(positions); count = 1; }
			modifiers.add(buildPositionModifier(positions.get(i), initModifier(true)));
		}
		for (int i = 0; i < Integer.valueOf(maxPositionModifiers)/2; i++) {
			if (count++ >= positions.size()) { Collections.shuffle(positions); count = 1; }
			modifiers.add(buildPositionModifier(positions.get(i), initModifier(false)));
		}
	}

	private void addTeamModifiers(List<Modifier> modifiers) {
		log.info("Generating Team Modifiers");
		List<NflTeam> teams = new ArrayList<NflTeam>(teamMap.values());
		Collections.shuffle(teams);
		int count = 1;
		for (int i = 0; i < Integer.valueOf(maxTeamModifiers)/2; i++) {
			if (count++ >= teams.size()) { Collections.shuffle(teams); count = 1; }
			modifiers.add(buildTeamModifier(teams.get(i), initModifier(true)));
		}
		for (int i = 0; i < Integer.valueOf(maxTeamModifiers)/2; i++) {
			if (count++ >= teams.size()) { Collections.shuffle(teams); count = 1; }
			modifiers.add(buildTeamModifier(teams.get(i), initModifier(false)));
		}
	}
	
	private Modifier buildPositionModifier(Position position, Modifier mod) { //TODO do I want to implement any logic to self-correct selecting randomness of some positions more often than others?
		mod.setTargetPosition(position);
		mod.setTargetType(TargetType.POSITION);
		mod.setModifierName(buildModifierName(mod));
		mod.setModifierDescription(buildModifierDescription(mod));
		return mod;
	}

	private Modifier buildTeamModifier(NflTeam nflTeam, Modifier mod) {
//		nflTeam.incrementModifierGeneratorHitCount(); //TODO do I want to implement any logic to self-correct randomness of selecting some teams more often than others?
		mod.setTargetType(TargetType.TEAM);
		mod.setTargetNflTeam(nflTeam);
		mod.setModifierName(buildModifierName(mod));
		mod.setModifierDescription(buildModifierDescription(mod));
		return mod;
	}
	
	private void addPlayerModifiers(List<Modifier> modifiers) {
		 // TODO 
		//
		// so right now its either by team or position... not sure what I am going to do with by "Player" here
		//
		//
	}
	
	private Modifier initModifier(boolean isBooster) {
		Modifier mod = new Modifier();
		mod.setModifierId(String.valueOf(currentIdToUse++));
		mod.setPrice(generatePseudoRandomPrice());
		mod.setChangePercentage(calculatePercentFromPrice(mod.getPrice()));
		if (!isBooster) { mod.setChangePercentage(0 - mod.getChangePercentage()); }
		return mod;
	}
	
	private String buildModifierDescription(Modifier mod) {
		StringBuilder sb = new StringBuilder();
		if (mod.getChangePercentage() > 0) { 	sb.append("This Modifier is a Booster, so it will increase the resulting score of the target player by ").append(mod.getChangePercentage()).append("%."); } 
		else { 													sb.append("This Modifier is a Negator, so it will decrease the resulting score of the target player by ").append(mod.getChangePercentage()).append("%."); }
													sb.append(" This modifier can only be applied to one ");
		if (mod.getTargetPosition() != null) { 		sb.append(mod.getTargetPosition().getName()).append(" "); }
		else { 													sb.append("player "); }
		if (mod.getTargetNflTeam() != null) { 		sb.append("on the ").append(mod.getTargetNflTeam().getNflTeamName()); }
		if (mod.getChangePercentage() > 0) { 	sb.append(" from your lineup."); } 
		else { 													sb.append(" from your opponent's lineup."); }
													sb.append(" Prior to use, you may sell this modifier back to the market at any time.");
		return sb.toString();
	}

	private String buildModifierName(Modifier mod) {
		StringBuilder sb = new StringBuilder();
		if (mod.getTargetNflTeam() != null) { 
			sb.append(mod.getTargetNflTeam().getMascot()).append(" "); 
		} else {
			sb.append("Any ");
		}
		if (mod.getTargetPosition() != null) { 
			sb.append(mod.getTargetPosition().getName()).append(" "); 
		} else {
			sb.append("Player "); 
		}
		if (mod.getChangePercentage() > 0) { 
			sb.append(BOOSTER); } 
		else { 
			sb.append(NEGATOR); }
		return sb.toString();
	}

	private double calculatePercentFromPrice(int price) {
		if (price < prices.get(prices.size()-1)) {
			for (int i = 0; i < prices.size(); i++) {
				if (prices.get(i) == price) {
					return doPriceToPercentCalculation(price, prices.get(i+1));
				}
			}
		} else {
			return doPriceToPercentCalculation(price, price + (price * 0.5));
		}
		return 0;
	}

	private double doPriceToPercentCalculation(int price, double maxPercent) {
		double diff = maxPercent-price;
		double r = Math.random();
		double times = (r*diff);
		double plus = price + times;
		double random = plus/100.0;
		System.out.println("\t\t" + String.format("price = %s  ::  maxPercent = %s  ::  diff = %s  ::  randomVal = %s  ::  times = %s  ::  plus = %s :: randomized = %s", price, maxPercent, diff, r, times, plus, random));
		return random;
	}

	private int generatePseudoRandomPrice() {
		Collections.shuffle(shuffledPrices);
		return shuffledPrices.get(0);
	}

}
