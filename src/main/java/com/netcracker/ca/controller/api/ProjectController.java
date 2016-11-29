package com.netcracker.ca.controller.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.ProjectMarkTypesDto;
import com.netcracker.ca.service.ProjectService;

@RestController
public class ProjectController extends BaseApiController {

    @Autowired
    private ProjectService projectService;

    @GetMapping({"admin/api/projects", "hr/api/projects"})
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @PostMapping("admin/api/project")
    public Project create(@RequestBody ProjectMarkTypesDto dto) {
        projectService.add(dto.getProject(), dto.getMeetingMarkTypeIds(), dto.getProjectMarkTypeIds());
        return dto.getProject();
    }
    
    @GetMapping({"admin/api/project/{projectId}", "hr/api/project/{projectId}"})
    public Project get(@PathVariable int projectId) {
        Project project = projectService.getByIdWithUsers(projectId);
        for(Team team: project.getTeams())
        	team.setProject(null);
        return project;
    }
    
    @GetMapping({"curator/api/project", "student/api/project"})
    public Project get(HttpSession session) {
    	Team team = (Team) session.getAttribute("team");
        return team.getProject();
    }

    @PutMapping("admin/api/project")
    public void update(@RequestBody Project project) {
        projectService.update(project);
    }

    @DeleteMapping("admin/api/project/{id}")
    public void delete(@PathVariable int id) {
        projectService.delete(id);
    }
}