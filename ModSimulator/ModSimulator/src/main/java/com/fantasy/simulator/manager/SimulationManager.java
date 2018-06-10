package com.fantasy.simulator.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.simulator.data.PlayerDataDelegate;

@Component
public class SimulationManager {
	
	@Autowired
	private PlayerDataDelegate dataDelegate;
	
	

}
