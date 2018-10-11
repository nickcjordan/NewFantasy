package com.fantasy.matchupexecutor.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.matchupexecutor.initializer.MatchupInitializer;
import com.fantasy.matchupexecutor.modapplicator.ModifierApplicator;
import com.fantasy.matchupexecutor.model.MatchupRequest;
import com.fantasy.matchupexecutor.results.MatchupResultsProcessor;

@RestController
@RequestMapping("/matchup")
public class MatchupExecutorController {

	private static Logger log = Logger.getLogger(MatchupExecutorController.class);

	@Autowired
	private MatchupInitializer matchupInitializer;
	
	@Autowired
	private ModifierApplicator modApplicator;
	
	@Autowired
	private MatchupResultsProcessor processor;
	
	@RequestMapping(value = "/execute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Matchup executeMatchup(@RequestBody MatchupRequest request) {
		// get nfl stats for players in matchup, populate teams with nfl stats
//		log.info("Initializing matchup...");
//		Matchup matchup = matchupInitializer.initializeMatchup(request);

		// populate mod targets, send mods to applicator to get initiated
//		log.info("Applying modifiers to users in matchup...");
//		modApplicator.applyModsToMatchup(matchup);
		
		log.info("NOT Applying modifiers :: come fix this code");
//		modApplicator.applyModsToMatchup(matchup);
		
		// build and set MatchupResults after mods are applied, do cleanup processes
//		log.info("Processing matchup results...");
//		matchup.setResults(processor.processMatchupResults(matchup));
		
//		return matchup;
		return null;
	}
	

}