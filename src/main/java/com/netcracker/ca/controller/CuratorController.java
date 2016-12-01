package com.netcracker.ca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.netcracker.ca.service.ProjectService;


@Controller
@RequestMapping("curator")
@SessionAttributes("team")
public class CuratorController extends BaseController {

    @Autowired
	private ProjectService projectService;

	@RequestMapping("")
	public String project(HttpSession session, Model model) {
		//System.out.println(session.getAttribute("team"));
		model.addAttribute("team", session.getAttribute("team"));
		model.addAttribute("projects", projectService.getAll());
		return "curProject";
	}
	
	@RequestMapping(value = "/team/{teamId}/files", params="team_id", method = RequestMethod.GET)
	public String curFilesPage(@RequestParam("team_id") int team_id, Model model) {
		//model.addAttribute("team_id", team_id);
		//model.addAttribute("team_name", teamService.getById(team_id).getTitle());
		return "curFiles";
	}
}
