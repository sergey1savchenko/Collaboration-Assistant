package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.netcracker.ca.service.ProjectService;


@Controller
@SessionAttributes("team")
@RequestMapping("curator")
public class CuratorController extends BaseController {

    @Autowired
	private ProjectService projectService;
    

	@RequestMapping
	public String project(Model model) {
		model.addAttribute("projects", projectService.getAll());
		return "curProject";
	}
	
	@RequestMapping("meeting")
	public String curMeetings(Model model) {
		return "curMeetings";
	}
	
	@RequestMapping("files")
	public String curFilesPage( Model model) {
		return "curFiles";
	}
}
