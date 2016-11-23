package com.netcracker.ca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class HTTPErrorHandler{
	
	@RequestMapping(value="/404")
	public String error404(){
		//System.out.println("custom error handler");
		return "/errorPage";
	}
	
}