package com.netcracker.ca.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.netcracker.ca.model.Participation;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.model.dto.StudentParticipationDto;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.validator.TeamFormValidator;

@RestController
public class TeamController extends BaseApiController {

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private CuratorService curatorService;
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private TeamFormValidator teamFormValidator;

	@InitBinder("teamForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(teamFormValidator);
	}

	@PostMapping("admin/api/curator/{curatorId}/project/{projectId}/team/{teamId}")
	public void addCuratorToTeam(@PathVariable int curatorId, @PathVariable int projectId, @PathVariable int teamId) {
		curatorService.add(curatorId, projectId, teamId);
	}

	@PostMapping("admin/api/student/{studentId}/project/{projectId}/team/{teamId}")
	public void addStudentToTeam(@PathVariable int studentId, @PathVariable int projectId, @PathVariable int teamId)
			throws SQLException {
		int afId = studentService.getById(studentId).getAppFormId();
		studentService.addToTeam(afId, projectId, teamId);
	}

	@PostMapping("admin/api/project/{projectId}/team")
	public Team create(@RequestBody @Validated Team team, @PathVariable int projectId) {
		teamService.add(team, projectId);
		return team;
	}

	@GetMapping("admin/api/freeCurators")
	public List<User> freeCurators() {
		return curatorService.getFreeCurators();
	}

	@GetMapping("admin/api/project/{projectId}/teams")
	public List<Team> getByProject(@PathVariable int projectId) {
		return teamService.getByProject(projectId);
	}

	@GetMapping({ "admin/api/team/{teamId}/curators", "hr/api/team/{teamId}/curators" })
	public List<User> teamCurators(@PathVariable int teamId) {
		return curatorService.getByTeam(teamId);
	}

	@GetMapping({ "curator/api/curators", "student/api/curators" })
	public List<User> teamCurators(@SessionAttribute Team team) {
		return curatorService.getByTeam(team.getId());
	}

	@GetMapping({ "admin/api/team/{teamId}/students", "hr/api/team/{teamId}/students" })
	public List<Student> teamStudents(@PathVariable int teamId) {
		return studentService.getByTeam(teamId);
	}

	@GetMapping({ "curator/api/students", "student/api/students" })
	public List<Student> teamStudents(@SessionAttribute Team team) {
		return studentService.getByTeam(team.getId());
	}

	@GetMapping({ "admin/api/team/{teamId}/students-part", "hr/api/team/{teamId}/students-part" })
	public List<StudentParticipationDto> teamStudentsWithPart(@PathVariable int teamId) {
		return teamStudentsWithPartById(teamId);
	}

	@GetMapping({ "curator/api/students-part", "student/api/students-part" })
	public List<StudentParticipationDto> teamStudentsWithPart(@SessionAttribute Team team) {
		return teamStudentsWithPartById(team.getId());
	}

	private List<StudentParticipationDto> teamStudentsWithPartById(int teamId) {
		List<StudentParticipationDto> result = new ArrayList<>();
		Map<Student, Participation> studParts = studentService.getByTeamWithParticipation(teamId);
		for (Entry<Student, Participation> studPart : studParts.entrySet())
			result.add(new StudentParticipationDto(studPart.getKey(), studPart.getValue()));
		return result;
	}

	@PutMapping("admin/api/team")
	public void update(@RequestBody @Validated Team team) {
		teamService.update(team);
	}

	@DeleteMapping("admin/api/team/{teamId}")
	public void deleteTeam(@PathVariable int teamId) {
		teamService.delete(teamId);
	}

	@DeleteMapping("admin/api/project/{projectId}/curator/{curatorId}")
	public void deleteCuratorFromTeam(@PathVariable int projectId, @PathVariable int curatorId) {
		curatorService.delete(curatorId, projectId);
	}
}