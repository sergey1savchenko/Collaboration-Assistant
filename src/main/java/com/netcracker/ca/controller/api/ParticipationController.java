package com.netcracker.ca.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.dto.ParticipationDtos;
import com.netcracker.ca.service.ParticipationService;

@RestController
public class ParticipationController extends BaseApiController {
	
	@Autowired
	private ParticipationService participationService;
	
	@PutMapping("admin/project/{projectId}/participations")
	public void updateAll(@RequestBody ParticipationDtos partDtos, @PathVariable int projectId) {
		participationService.setAll(partDtos.getParticipationDtos(), projectId);
	}
	
}
