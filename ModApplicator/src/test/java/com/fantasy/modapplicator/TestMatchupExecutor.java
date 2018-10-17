package com.fantasy.modapplicator;

public class TestMatchupExecutor {
	
	private static final String MATCHUP_PATH = "http://localhost:8083/matchup/execute";
	private static final String DATABASE_MANAGER_API_PATH = "http://localhost:8080/player/get/";

//	
//	@Test
//	public void test() {
//		MatchupRequest request = new MatchupRequest();
//		List<String> list = new ArrayList<String>();
//		list.add("0");
//		list.add("1");
//		request.setUserIds(list);
//		request.setWeekNumber("1");
//		Matchup m = sendMatchupPost(request);
//		System.out.println("winner: " + m.getResults().getWinner().getUserName());
//	}
//	
//	
//	private Matchup sendMatchupPost(MatchupRequest request) {
//		Matchup result;
//		String requestJson = null;
//		try {
//			requestJson = new ObjectMapper().writeValueAsString(request);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		System.out.println(requestJson);
//		RestTemplate restTemplate = new RestTemplate();
//		result = restTemplate.postForObject(MATCHUP_PATH, request, Matchup.class);
//		return result;
//	}

}
