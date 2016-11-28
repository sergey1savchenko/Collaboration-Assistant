package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ParticipationService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.UniversityService;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

	@Autowired
	private MarkTypeService markTypeService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ParticipationService participationService;

	@GetMapping({ "", "projects" })
	public String projects(Model model) {
		model.addAttribute("universities", universityService.getAll());
		return "admProjects";
	}

	@GetMapping("properties")
	public String properties() {
		return "admProperties";
	}

	@GetMapping("create-project")
	public String createProject(Model model) {
		model.addAttribute("universities", universityService.getAll());
		model.addAttribute("projects", projectService.getAll());
		model.addAttribute("markTypes", markTypeService.getAll());
		return "admNewProject";
	}

	@GetMapping("project/{projectId}")
	public String project(@PathVariable int projectId, Model model) {
		model.addAttribute("project", projectService.getByIdWithUsers(projectId));
		return "admProjectTeams";
	}

	@RequestMapping("project/{projectId}/participations")
	public String getParticipations(@PathVariable int projectId, Model model) {
		model.addAttribute("participations", participationService.getByProject(projectId));
		return "your-page";
	}

}
