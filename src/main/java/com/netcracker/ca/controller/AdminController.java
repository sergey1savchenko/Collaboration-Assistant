package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ProjectService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.TeamService;
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
	private TeamService teamService;
	
	@Autowired
	private CuratorService curatorService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping({ "", "projects" })
	public String projects(Model model) {
		model.addAttribute("universities", universityService.getAll());
		return "admProjects";
	}

	@GetMapping("properties")
	public String properties() {
		return "admAddProperties";
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
		model.addAttribute("project", projectService.getByIdWithTeams(projectId));
		return "admProjectTeams";
	}
	
	@GetMapping("/project/{projectId}/addCurators/{teamId}")
	public String addCurators(@PathVariable int projectId, @PathVariable int teamId, Model model) {
		model.addAttribute("team", teamService.getById(teamId));
		model.addAttribute("project", projectService.getById(projectId));
		model.addAttribute("freeCurators", curatorService.getFreeCurators());
		return "admAddCurators";
	}
	
	@GetMapping("/project/{projectId}/addStudents/{teamId}")
	public String addStudents(@PathVariable int projectId, @PathVariable int teamId, Model model) {
		model.addAttribute("team", teamService.getById(teamId));
		model.addAttribute("project", projectService.getById(projectId));
		model.addAttribute("freeStudents", studentService.getFreeStudents());
		return "admAddStudents";
	}
	
	@GetMapping("/project/{projectId}/meetings")
	public String admMeetings(@PathVariable("projectId") int projectId, Model model) {
		model.addAttribute("projectId", projectId);
		return "admMeetings";
	}
	@GetMapping("/project/{projectId}/properties")
	public String admProjectProperties(@PathVariable("projectId") int projectId, Model model) {
		model.addAttribute("projectId", projectId);
		return "admProjectProperties";
	}
	
	@GetMapping("/project/{projectId}/files")
	public String admFilesPage(@PathVariable("projectId") int projectId, Model model) {
		model.addAttribute("project_id", projectId);
		model.addAttribute("project_name", projectService.getById(projectId).getTitle());
		return "admFiles";
	}
}