package com.netcracker.ca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "/errorPage";
	}
	
}
