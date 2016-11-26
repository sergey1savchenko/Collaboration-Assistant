package com.netcracker.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.dto.ParticipationDtos;
import com.netcracker.ca.service.ParticipationService;

@RestController
public class ParticipationRestController {
	
	@Autowired
	private ParticipationService participationService;
	
	@RequestMapping("admin/project/{projectId}/participations/update-all")
	public void updateAll(@RequestBody ParticipationDtos partDtos, @PathVariable int projectId) {
		participationService.updateAll(partDtos.getParticipationDtos(), projectId);
	}
	
}
