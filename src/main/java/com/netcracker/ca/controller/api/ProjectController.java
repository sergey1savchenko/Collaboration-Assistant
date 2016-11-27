package com.netcracker.ca.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.netcracker.ca.model.Project;
import com.netcracker.ca.service.ProjectService;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/project", method = RequestMethod.GET, produces = "application/json")
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST, produces = "application/json")
    public Project create(@RequestBody Project project) {
        projectService.add(project);
        return project;
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public void update(@RequestBody Project project) {
        projectService.update(project);
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        projectService.delete(id);
    }
}