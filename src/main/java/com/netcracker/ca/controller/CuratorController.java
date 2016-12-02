package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.ProjectService;


@Controller
@RequestMapping("curator")
public class CuratorController extends BaseController {

    @Autowired
	private ProjectService projectService;
    

	@RequestMapping
	public String project(@SessionAttribute Team team,  Model model) {
		model.addAttribute("team", team);
		model.addAttribute("projects", projectService.getAll());
		return "curProject";
	}
	@RequestMapping("meeting")
	public String curMeetings(@SessionAttribute Team team, Model model) {
		model.addAttribute("team", team);
		return "curMeetings";
	}
	
	@RequestMapping("team/files")
	public String curFilesPage(@SessionAttribute Team team, Model model) {
		//model.addAttribute("team_id", team_id);
		//model.addAttribute("team_name", teamService.getById(team_id).getTitle());
		model.addAttribute("team",team);
		return "curFiles";
	}
}
