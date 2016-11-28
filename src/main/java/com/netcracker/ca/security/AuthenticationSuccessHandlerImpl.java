package com.netcracker.ca.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.UserAuth;
import com.netcracker.ca.service.TeamService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private TeamService teamService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
        UserAuth userAuth = (UserAuth) auth.getPrincipal();
        HttpSession session = request.getSession();
        Team team = null;
        switch(userAuth.getRole().getName()) {
        case "ADMIN":
        	response.sendRedirect("admin");
        	break;
        case "HR":
        	response.sendRedirect("hr");
        	break;
        case "CURATOR":
        	team = teamService.getCurrentForCurator(userAuth.getId());
        	session.setAttribute("team", team);
        	response.sendRedirect("curator");
        	break;
        case "STUDENT":
        	team = teamService.getCurrentForStudent(userAuth.getId());
        	session.setAttribute("team", team);
        	response.sendRedirect("student");
        	break;
        }
	}

}
