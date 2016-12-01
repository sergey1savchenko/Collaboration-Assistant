package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.ca.service.ProjectService;

@Controller
@RequestMapping("hr")
public class HRController extends BaseController {

    	@Autowired
	private ProjectService projectService;
    
    	@RequestMapping("")
	public String projects() {

		// TODO "hrProjects"
		return "home";
	}
	
	@GetMapping("project/{projectId}")
	public String project(@PathVariable int projectId, Model model) {
		model.addAttribute("project", projectService.getByIdWithUsers(projectId));
		return "hrProjectTeams";
	}
	
	@RequestMapping(value = "/hrFeedback", params="app_form_id", method = RequestMethod.GET)
	public String curFilesPage(@RequestParam("app_form_id") int app_form_id, Model model) {
		model.addAttribute("app_form_id", 1);
		return "hrFeedback";
	}
}
