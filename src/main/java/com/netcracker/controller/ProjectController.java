package com.netcracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netcracker.service.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = { "/", "/projects" }, method = RequestMethod.GET)
	public String projectsPage(Model model) {
		model.addAttribute("projects", projectService.getAll());
		return "projects";
	}
}