package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("meeting/{meetingId}/student/{studentId}")
	public String curMeetingEvaluation(Model model,@PathVariable("meetingId") int meetingId, @PathVariable("studentId") int studentId) {
		model.addAttribute("meetingId", meetingId);
		model.addAttribute("studentId", studentId);
		return "curMeetingEvaluation";
	}
	
	@RequestMapping("student/{studentId}")
	public String curStudent(Model model, @PathVariable("studentId") int studentId) {
		model.addAttribute("studentId", studentId);
		return "curStudent";
	}
	
	@RequestMapping("files")
	public String curFilesPage( Model model) {
		return "curFiles";
	}
}
