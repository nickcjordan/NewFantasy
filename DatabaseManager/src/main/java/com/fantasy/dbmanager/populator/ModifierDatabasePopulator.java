package com.fantasy.dbmanager.populator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.manager.ModifierDatabaseManager;

@Component
public class ModifierDatabasePopulator {
	
	private static final Logger log = LoggerFactory.getLogger(ModifierDatabasePopulator.class);
	
	@Autowired
	private ModifierDatabaseManager modifierManager;
	
	@Autowired
	private ModifierGenerator generator;
	
	public void populate() {
		log.info("START :: Clearing database of modifiers...");
		modifierManager.clear();
		log.info("Populating database with newly generated test modifiers...");
		modifierManager.put(generator.generateWeeklyModifiers());
		log.info("END :: SUCCESS :: Populated database with modifiers");
	}
	
//	private List<Modifier> buildTestModifiers() {
//		int i = 1;
//		List<Modifier> mods = new ArrayList<Modifier>();
//		mods.add(initMod("Full Team Booster", i++, 2.5, 50));
//		mods.add(initMod("Starting QB Booster", i++, 5, 100));
//		mods.add(initMod("Steelers WR Booster", i++, 8, 150));
//		mods.add(initMod("Starting RB Booster", i++, 10, 200));
//		
//		mods.add(initMod("Full Team Booster", i++, 2.5, 50));
//		mods.add(initMod("Starting QB Booster", i++, 5, 100));
//		mods.add(initMod("Falcons WR Booster", i++, 8, 150));
//		mods.add(initMod("Starting K Booster", i++, 10, 200));
//		
//		mods.add(initMod("Browns RB Negator", i++, -2.5, 50));
//		mods.add(initMod("Opponent Team Negator", i++, -5, 100));
//		mods.add(initMod("Jaguars QB Negator", i++, -8, 150));
//		mods.add(initMod("Opponent WR Negator", i++, -10, 200));
//		
//		mods.add(initMod("Eagles RB Negator", i++, -2.5, 50));
//		mods.add(initMod("Opponent Team Negator", i++, -5, 100));
//		mods.add(initMod("Jaguars QB Negator", i++, -8, 150));
//		mods.add(initMod("Opponent WR Negator", i++, -10, 200));
//		return mods;
//	}
//
//	private Modifier initMod(String name, int id, double changePercentage, int price) {
//		Modifier m = new Modifier();
//		m.setModifierId(String.valueOf(id));
//		m.setModifierName(name);
//		m.setChangePercentage(changePercentage);
//		m.setPrice(price);
//		m.setTargetType(TargetType.TEAM);
//		m.setModifierDescription("Description of what the modifier does");
//		return m;
//	}

}
