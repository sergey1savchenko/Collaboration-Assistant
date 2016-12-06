package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.MeetingService;
import com.netcracker.ca.service.ProjectService;

@Controller
@SessionAttributes("team")
@RequestMapping("student")
public class StudentController extends BaseController {

    	@Autowired
    	private ProjectService projectService;
        @Autowired
    	private MeetingService meetingService;
	
	@RequestMapping
	public String project(Model model) {
		model.addAttribute("projects", projectService.getAll());
		return "stdProject";
	}
	@RequestMapping(value = "meeting")
	public String stdMeetings(Model model,@SessionAttribute Team team) {
		
		return "stdMeetings";
	}
	
	@RequestMapping(value = "files", method = RequestMethod.GET)
	public String curFilesPage(Model model) {
		return "stdFiles";
	}
	
}
