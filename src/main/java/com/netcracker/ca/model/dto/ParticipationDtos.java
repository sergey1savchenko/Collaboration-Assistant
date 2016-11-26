package com.netcracker.ca.model.dto;

import java.util.List;

public class ParticipationDtos {

	private List<ParticipationDto> participationDtos;
	
	public ParticipationDtos() {

	}

	public ParticipationDtos(List<ParticipationDto> participationDtos) {
		this.participationDtos = participationDtos;
	}
	
	public List<ParticipationDto> getParticipationDtos() {
		return participationDtos;
	}

	public void setParticipationDtos(List<ParticipationDto> participationDtos) {
		this.participationDtos = participationDtos;
	}
}
