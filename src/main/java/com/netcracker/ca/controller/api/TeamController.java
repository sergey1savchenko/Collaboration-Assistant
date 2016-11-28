package com.netcracker.ca.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.service.UserService;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;
    
    @RequestMapping(value = "/team", method = RequestMethod.GET, produces = "application/json")
    public List<Team> getAll() {
        return teamService.getAll();
    }
    
    @RequestMapping(value = "/team", method = RequestMethod.POST, produces = "application/json")
    public Team create(@RequestBody Team team) {
    	teamService.add(team);
        return team;
    }
    
    @RequestMapping(value = "/team", method = RequestMethod.PUT)
    public void update(@RequestBody Team team) {
    	teamService.update(team);
    }
    
    @RequestMapping(value = "/team/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
    	teamService.delete(id);
    }
}
