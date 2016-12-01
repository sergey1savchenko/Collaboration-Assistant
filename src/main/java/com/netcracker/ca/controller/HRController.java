package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcracker.ca.service.ProjectService;

@Controller
@RequestMapping("hr")
public class HRController extends BaseController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("")
	public String projects() {
		// TODO "hrProjects"
		return "hrProjects";
	}
	
	@GetMapping("project/{projectId}")
	public String project(@PathVariable int projectId, Model model) {
		model.addAttribute("project", projectService.getByIdWithUsers(projectId));
		return "hrProjectTeams";
	}
	
}
