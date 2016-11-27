package com.netcracker.ca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("student")
@SessionAttributes("team")
public class StudentController {

	@RequestMapping("")
	public String project(HttpSession session) {
		System.out.println(session.getAttribute("team"));
		//TODO "studentProject"
		return "home";
	}
	
}
