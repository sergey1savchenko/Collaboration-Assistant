package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcracker.ca.model.Project;
import com.netcracker.ca.service.ParticipationService;
import com.netcracker.ca.service.ProjectService;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ParticipationService participationService;

	@GetMapping({ "", "projects" })
	public String projects() {
		return "admProjects";
	}

	@GetMapping("properties")
	public String properties() {
		return "admProperties";
	}

	@GetMapping("create-project")
	public String createProject() {
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
