package com.fantasy.dataaccessutility.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.access.DatabaseManagerDelegate;
import com.fantasy.dataaccessutility.model.modifier.Modifier;

@Component
public class ModifierDataAccessManager {
	
	private static Logger log = Logger.getLogger(ModifierDataAccessManager.class);
	
	@Autowired
	private DatabaseManagerDelegate delegate;
	
	public Modifier getModifierById(String modifierId) {
		log.info("Getting modifier " + modifierId);
		return delegate.getModifier(modifierId);
	}

	public List<Modifier> getAllModifiers() {
		log.info("Getting all modifiers");
		return delegate.getAllModifiers();
	}
	
//	public void updateModifier(Modifier modifier) {
//		log.info("Updating stats for modifier " + modifier.getModifierId());
//		delegate.updateModifier(modifier);
//	}
	
//	public void updateModifiers(List<Modifier> modifiers) {
//		for (Modifier modifier : modifiers) { updateModifier(modifier); } // TODO call batch
//	}

//	public void addModifiers(List<Modifier> mods) {
//		 delegate.addModifiers(mods);
//	}

//	public void clearModifiers() {
//		delegate.clearModifiers();
//	}

}
