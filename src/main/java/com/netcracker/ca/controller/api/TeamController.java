package com.netcracker.ca.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.netcracker.ca.model.Student;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.CuratorService;
import com.netcracker.ca.service.StudentService;
import com.netcracker.ca.service.TeamService;

@RestController
public class TeamController extends BaseApiController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private CuratorService curatorService;
    @Autowired
    private StudentService studentService;
    
    @PostMapping("admin/api/project/{projectId}/team")
    public Team create(@RequestBody Team team, @PathVariable int projectId) {
    	teamService.add(team, projectId);
        return team;
    }
    
    @GetMapping("admin/api/project/{projectId}/teams")
    public List<Team> getByProject(@PathVariable int projectId) {
    	return teamService.getByProject(projectId);
    }
    
    @GetMapping({"admin/api/team/{teamId}/curators", "hr/api/team/{teamId}/curators"})
    public List<User> teamCurators(@PathVariable int teamId) {
        return curatorService.getByTeam(teamId);
    }
    
    @GetMapping({"curator/api/curators", "student/api/curators"})
    public List<User> teamCurators(@SessionAttribute Team team) {
        return curatorService.getByTeam(team.getId());
    }
    
    @GetMapping({"admin/api/team/{teamId}/students", "hr/api/team/{teamId}/students"})
    public List<Student> teamStudents(@PathVariable int teamId) {
        return studentService.getByTeam(teamId);
    }
    
    @GetMapping({"curator/api/students", "student/api/students"})
    public List<Student> teamStudents(@SessionAttribute Team team) {
        return studentService.getByTeam(team.getId());
    }
    
    @PutMapping("admin/api/team")
    public void update(@RequestBody Team team) {
    	teamService.update(team);
    }
    
    @DeleteMapping("admin/api/team/{teamId}")
    public void delete(@PathVariable int teamId) {
    	teamService.delete(teamId);
    }
}
