package com.capstone.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NefconfController {
	
	@GetMapping("/getconfig")
	public String getConfig() {
		return "hi";
	}
}
