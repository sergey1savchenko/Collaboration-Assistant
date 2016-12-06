package com.netcracker.ca.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.MeetingEvaluation;
import com.netcracker.ca.model.User;
import com.netcracker.ca.model.UserAuth;
import com.netcracker.ca.model.dto.CuratorMeetingEvaluationsDto;
import com.netcracker.ca.model.dto.MeetingEvaluationsDto;
import com.netcracker.ca.service.MeetingEvaluationService;
import com.netcracker.ca.validator.MeetingEvaluationFormValidator;

@RestController
public class MeetingEvaluationController extends BaseApiController {

	@Autowired
	private MeetingEvaluationService meetEvalService;
	
	@Autowired
	private MeetingEvaluationFormValidator meetingEvaluationFormValidator;

	@InitBinder("meetingEvaluationForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(meetingEvaluationFormValidator);
	}

	@PostMapping("curator/api/meeting/{meetingId}/student/{studentId}/meet-eval")
	public void evaluateMeeting(@RequestBody MeetingEvaluationsDto dto, @PathVariable int meetingId,
			@PathVariable int studentId, @AuthenticationPrincipal UserAuth userAuth) {
		meetEvalService.addAll(dto.getMeetingEvaluations(), studentId, meetingId, userAuth.getId());
	}
	
	@GetMapping("curator/api/meeting/{meetingId}/student/{studentId}/meet-eval")
	public List<MeetingEvaluation> getEvaluations(@PathVariable int meetingId,
			@PathVariable int studentId, @AuthenticationPrincipal UserAuth userAuth) {
		return meetEvalService.getByStudentAndMeetingAndCurator(studentId, meetingId, userAuth.getId());
	}
	
	@GetMapping("hr/api/meeting/{meetingId}/student/{studentId}/meet-eval")
	public List<CuratorMeetingEvaluationsDto> getEvaluations(@PathVariable int meetingId,
			@PathVariable int studentId) {
		List<CuratorMeetingEvaluationsDto> dtos = new ArrayList<>();
		for(Entry<User, List<MeetingEvaluation>> curatorEvaluations: meetEvalService.getByStudentAndMeetingPerCurator(studentId, meetingId).entrySet()) {
			dtos.add(new CuratorMeetingEvaluationsDto(curatorEvaluations.getKey(), curatorEvaluations.getValue()));
		}
		return dtos;
	}
}