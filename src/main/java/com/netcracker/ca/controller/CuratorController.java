package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.netcracker.ca.model.Meeting;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.MeetingService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.UniversityService;

import java.util.List;


@Controller
@SessionAttributes("team")
@RequestMapping("curator")
public class CuratorController extends BaseController {

    @Autowired
	private ProjectService projectService;
    
    @Autowired
	private StudentService studentService;
    
    @Autowired
	private MeetingService meetingService;
    
    @Autowired
	private UniversityService universityService;

	@RequestMapping
	public String project(Model model) {
		model.addAttribute("projects", projectService.getAll());
		return "curProject";
	}
	
	@RequestMapping("meeting")
	public String curMeetings(Model model) {
		return "curMeetings";
	}
	
	@RequestMapping("meeting/{meetingId}/meetingEvaluation")
	public String curMeetingEvaluation(Model model,@PathVariable("meetingId") int meetingId, @SessionAttribute Team team) {
		model.addAttribute("teamStudents", studentService.getByTeam(team.getId()));						// Students
		List<Meeting> teamMeetings = meetingService.getAllTeamMeetings(team.getId());
		for (Meeting i : teamMeetings) {
		    if(i.getId()==meetingId){
		    	model.addAttribute("meeting", i);														// direct team meeting
		    }
		}
		return "curMeetingEvaluation";
	}
	
	@RequestMapping("student/{studentId}")
	public String curStudent(Model model, @PathVariable("studentId") int studentId) {
		model.addAttribute("student", studentService.getById(studentId));
		model.addAttribute("universities", universityService.getAll());
		return "curStudent";														// PROJECT EVALUATION
	}
	
	@RequestMapping("files")
	public String curFilesPage( Model model) {
		return "curFiles";
	}
}
