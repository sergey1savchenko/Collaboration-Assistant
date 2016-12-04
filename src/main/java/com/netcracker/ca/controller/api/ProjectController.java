package com.netcracker.ca.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.EvaluationScope;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.ProjectMarkTypesDto;
import com.netcracker.ca.service.MarkTypeService;
import com.netcracker.ca.service.ProjectService;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController extends BaseApiController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private MarkTypeService markTypeService;

    @GetMapping({"admin/api/projects", "hr/api/projects"})
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @PostMapping("admin/api/project")
    public Project create(@RequestBody ProjectMarkTypesDto dto) {
        projectService.add(dto.getProject(), dto.getMeetingMarkTypeIds(), dto.getProjectMarkTypeIds());
        return dto.getProject();
    }
    
    @PostMapping("admin/api/projectCopy/{copyOfId}")
    public Project createCopy(@RequestBody Project project, @PathVariable int copyOfId) {
    	List<MarkType> m = markTypeService.getAllowed(copyOfId, EvaluationScope.MEETINGS);
    	List<MarkType> p = markTypeService.getAllowed(copyOfId, EvaluationScope.PROJECTS);
    	List<Integer> meetingMarkTypeIds = new ArrayList<Integer>();
    	for ( MarkType i : m){
    		meetingMarkTypeIds.add(i.getId());
    	}
    	List<Integer> projectMarkTypeIds = new ArrayList<Integer>();
    	for ( MarkType i : p){
    		projectMarkTypeIds.add(i.getId());
    	}
        projectService.add(project, meetingMarkTypeIds, projectMarkTypeIds);
        return project;
    }
    
    @GetMapping({"admin/api/project/{projectId}", "hr/api/project/{projectId}"})
    public Project get(@PathVariable int projectId) {
        Project project = projectService.getByIdWithTeams(projectId);
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