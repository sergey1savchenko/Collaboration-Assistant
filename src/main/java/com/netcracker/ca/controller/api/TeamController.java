package com.netcracker.ca.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.Project;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.TeamService;

@RestController
public class TeamController extends BaseApiController {

    @Autowired
    private TeamService teamService;
    
    @RequestMapping(value = "/team", method = RequestMethod.GET, produces = "application/json")
    public List<Team> getAll() {
        return teamService.getAll();
    }
    
    @PostMapping("admin/api/project/{projectId}/team")
    public Team create(@RequestBody Team team, @PathVariable int projectId) {
    	team.setProject(new Project(projectId));
    	teamService.add(team);
        return team;
    }
    
    @GetMapping("admin/api/project/{projectId}/teams")
    public List<Team> getByProject(@PathVariable int projectId) {
    	return teamService.getByProject(projectId);
    }
    
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@RequestBody Team team) {
    	teamService.update(team);
    }
    
    @RequestMapping(value = "/team/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
    	teamService.delete(id);
    }
}
