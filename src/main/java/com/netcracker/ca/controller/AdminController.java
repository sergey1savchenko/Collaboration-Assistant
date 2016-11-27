package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.UniversityService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping("")
	public String projects() {
		//TODO "adminProjects"
		return "home";
	}
	
}
