package com.fantasy.playerstatsapi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fantasy.playerstatsapi.model.api.WeekStatsResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneralTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		WeekStatsResponse result = new ObjectMapper().readValue(new File("src/main/resources/stats.json"), WeekStatsResponse.class);
		System.out.println("no error?");
	}

}
