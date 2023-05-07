package com.isfa.clientadminpanel.leave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/")
	public String getIndex() {
		logger.info("Index controller executing");
		return "Home Controller";
	}

	@RequestMapping("/home")
    public String home() { 
		logger.info("Index controller executing");
        return "home"; // return view name
    }
	
}
