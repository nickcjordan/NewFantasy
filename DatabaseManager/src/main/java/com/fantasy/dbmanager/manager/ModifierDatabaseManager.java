package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dbmanager.dao.ModifierDao;

@Component
public class ModifierDatabaseManager {
	
	private static Logger log = Logger.getLogger(ModifierDatabaseManager.class);
	
	@Autowired
	private ModifierDao modifierDao;
	
	public void put(List<Modifier> modifiers) {
		modifierDao.put(modifiers);
	}

	public long count() {
		return modifierDao.getModifierCount();
	}
	

	public boolean clear() {
		return modifierDao.removeAll();
	}

	public List<Modifier> getAll() {
		List<Modifier> mods = new ArrayList<Modifier>();
		for (Modifier mod : modifierDao.getAll()) {
			mods.add(mod);
		}
		return mods;
	}

}
