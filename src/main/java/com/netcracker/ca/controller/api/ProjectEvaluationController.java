package com.netcracker.ca.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.ProjectEvaluation;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.model.UserAuth;
import com.netcracker.ca.model.dto.CuratorProjectEvaluationsDto;
import com.netcracker.ca.service.ProjectEvaluationService;
import com.netcracker.ca.validator.ProjectEvaluationFormValidator;

@RestController
public class ProjectEvaluationController extends BaseApiController {

	@Autowired
	private ProjectEvaluationService projectEvalService;
	
	@Autowired
	private ProjectEvaluationFormValidator projectEvaluationFormValidator;

	@InitBinder("projectEvaluationForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(projectEvaluationFormValidator);
	}
	
	@PutMapping("curator/api/student/{studentId}/proj-eval")
	public void update(@RequestBody ProjectEvaluation pe) {
		projectEvalService.update(pe);
	}
	
	@GetMapping({"hr/api/student/{studentId}/proj-eval", "curator/api/student/{studentId}/proj-eval"})
	public List<ProjectEvaluation> getEvaluations(@PathVariable int studentId, HttpSession session, @AuthenticationPrincipal UserAuth userAuth) {
		Team team = (Team) session.getAttribute("team");
		return projectEvalService.getByStudentAndProjectAndCurator(studentId, team.getProject().getId(), userAuth.getId());
	}
	
	@GetMapping({"hr/api/project/{projectId}/student/{studentId}/proj-eval", "curator/api/project/{projectId}/student/{studentId}/proj-eval"})
	public List<CuratorProjectEvaluationsDto> getEvaluations(@PathVariable int projectId, @PathVariable int studentId) {
		List<CuratorProjectEvaluationsDto> dtos = new ArrayList<>();
		for(Entry<User, List<ProjectEvaluation>> curatorEvaluations: projectEvalService.getByStudentAndProjectPerCurator(studentId, projectId).entrySet()) {
			dtos.add(new CuratorProjectEvaluationsDto(curatorEvaluations.getKey(), curatorEvaluations.getValue()));
		}
		return dtos;
	}
	
}