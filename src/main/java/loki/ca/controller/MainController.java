package loki.ca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/", "/main" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		return "main";
	}
	
	@RequestMapping(value = { "/help" }, method = RequestMethod.GET)
	public String aboutPage(Model model) {
		return "help";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "admin";
	}

	@RequestMapping(value = "/curator", method = RequestMethod.GET)
	public String curatorPage(Model model) {
		return "curator";
	}
	
	@RequestMapping(value = "/hr", method = RequestMethod.GET)
	public String hrPage(Model model) {
		return "hr";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String studentPage(Model model) {
		return "student";
	}
}
