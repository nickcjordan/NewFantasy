package com.fantasy.dbmanager.playerstatsapi.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fantasy.dbmanager.playerstatsapi.builder.UrlBuilder;
import com.fantasy.dbmanager.playerstatsapi.model.PlayerMetaData;
import com.fantasy.dbmanager.playerstatsapi.model.WeekStatsResponse;

@Component
public class PlayerStatsAPIDelegate {
	
	private static Logger log = Logger.getLogger(PlayerStatsAPIDelegate.class);
	
	@Autowired
	private UrlBuilder urls;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public WeekStatsResponse retrieveAllWeekStats(int week) {
		log.info("Calling weeklyStats for week " + week);
		WeekStatsResponse response = restTemplate.getForObject(urls.buildWeekStatsUrl(week), WeekStatsResponse.class);
		response.setWeekNumber(week);
		return response;
	}
	
	public PlayerMetaData retrieveMetaDataForPlayer(String playerId) {
		try {
			return restTemplate.getForObject(urls.buildMetaDataUrl(playerId), PlayerMetaData.class);
		} catch (Exception e) {
			log.error("ERROR :: Failed to get data for player " + playerId);
			return null;
		}
	}
	


}
