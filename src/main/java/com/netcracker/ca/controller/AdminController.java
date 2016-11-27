package com.netcracker.ca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping("")
	public String projects() {
		
		//TODO "adminProjects"
		return "home";
	}
	
}
