package com.netcracker.ca.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.service.AttachmentFactory;
import com.netcracker.ca.service.AttachmentService;

@Controller
public class MainController extends BaseController {

	@GetMapping("login")
	public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		if (error != null) {
			model.addAttribute("error", "Invalid username or password!");
		}
		if (logout != null) {
			model.addAttribute("logout", "You've been logged out successfully.");
		}
		return "login";
	}

	@GetMapping({ "", "main" })
	public String mainPage(Model model) {
		return "home";
	}
	
	@GetMapping("help")
	public String aboutPage(Model model) {
		return "about";
	}
	
	@RequestMapping("404")
	public String error404(){
		//System.out.println("custom error handler");
		return "/errorPage";
	}
	
	@Autowired
	private AttachmentService attService;
	
	@Autowired
	private AttachmentFactory attFactory;
	
	@PostMapping("admin/api/project/{projectId}/file")
	public String uploadForProject(@RequestParam MultipartFile file, @RequestParam("text") String text,
			@PathVariable int projectId) throws IOException {
		//add validation for file (not empty, <maxSize)
		try (InputStream input = file.getInputStream()) {
			Attachment att = attFactory.build(file.getName(), text, file.getContentType(), projectId, false);
			attService.addToProject(att,input,projectId);
			return "admFiles";
		}
	}
	
	@PostMapping("curator/api/team/{teamId}/file")
	public String uploadForTeam(@RequestParam MultipartFile file,@RequestParam("project_id") int project_id, @RequestParam("text") String text,
			@PathVariable int teamId) throws IOException {
		//add validation for file (not empty, <maxSize)
		try (InputStream input = file.getInputStream()) {
			Attachment att = attFactory.build(file.getName(), text, file.getContentType(), project_id, true);
			attService.addToTeam(att,input,teamId);
			return "curFiles";
		}
	}
	
}
