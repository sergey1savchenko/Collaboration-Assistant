package com.netcracker.ca.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcracker.ca.service.ParticipationService;

@Controller
public class ParticipationController {

	@Autowired
	private ParticipationService participationService;

	@RequestMapping("admin/project/{projectId}/participations")
	public String getParticipations(@PathVariable int projectId, HttpServletRequest request, Model model) {
		model.addAttribute("participations", participationService.getByProject(projectId));
		return "your-page";
	}

}
