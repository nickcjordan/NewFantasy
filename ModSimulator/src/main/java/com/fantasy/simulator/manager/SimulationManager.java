package com.fantasy.simulator.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.simulator.data.PlayerDataManager;

@Component
public class SimulationManager {
	
	@Autowired
	private PlayerDataManager playerManager;
	
//	@Autowired
//	private TestDataManager userManager;

	public void runSimulation() {
		
	}
	
	

}
