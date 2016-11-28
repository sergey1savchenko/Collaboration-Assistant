package com.netcracker.ca.controller;

import com.netcracker.ca.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
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

	@RequestMapping(value = { "/", "/main" }, method = RequestMethod.GET)
	public String mainPage(Model model) {
		return "home";
	}
	
	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public String aboutPage(Model model) {
		return "about";
	}
	
	@RequestMapping(value="/404")
	public String error404(){
		//System.out.println("custom error handler");
		return "/errorPage";
	}

	@Autowired
	private UniversityService universityService;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String projectsPage(Model model) {
		model.addAttribute("universities", universityService.getAll());
		return "projects";
	}

}