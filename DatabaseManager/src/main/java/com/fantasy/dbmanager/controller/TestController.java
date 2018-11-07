package com.fantasy.dbmanager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Resource(name="testString")
	private String testString;
	
	@RequestMapping("/test")
	public String test() {
		return testString;
	}

}
