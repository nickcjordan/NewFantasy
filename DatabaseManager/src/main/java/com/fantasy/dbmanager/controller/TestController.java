package com.fantasy.dbmanager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TestController {
	
	@Resource(name="testString")
	private String testString;
	
	@RequestMapping("/test")
	public String test() {
		return testString;
	}

}
