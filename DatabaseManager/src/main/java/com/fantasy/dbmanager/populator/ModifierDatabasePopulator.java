package com.fantasy.dbmanager.populator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.modifier.TargetType;
import com.fantasy.dbmanager.manager.ModifierDatabaseManager;

@Component
public class ModifierDatabasePopulator {
	
	private static final Logger log = LoggerFactory.getLogger(ModifierDatabasePopulator.class);
	
	@Autowired
	private ModifierDatabaseManager modifierManager;
	
	public void populate() {
		log.info("START :: Clearing database of modifiers...");
		modifierManager.clear();
		log.info("Populating database with newly generated test modifiers...");
		modifierManager.put(buildTestModifiers());
		log.info("END :: SUCCESS :: Populated database with modifiers");
	}
	
	private List<Modifier> buildTestModifiers() {
		List<Modifier> mods = new ArrayList<Modifier>();
		Modifier mod1 = new Modifier();
		mod1.setChangePercentage(2.5);
		mod1.setPrice(50);
		mod1.setTargetType(TargetType.TEAM);
		
		Modifier mod2 = new Modifier();
		mod2.setChangePercentage(5);
		mod2.setPrice(100);
		mod2.setTargetType(TargetType.TEAM);
		
		Modifier mod3 = new Modifier();
		mod3.setChangePercentage(8);
		mod3.setPrice(150);
		mod3.setTargetType(TargetType.TEAM);
		
		mods.add(mod1);
		mods.add(mod2);
		mods.add(mod3);
		
		return mods;
	}

}
