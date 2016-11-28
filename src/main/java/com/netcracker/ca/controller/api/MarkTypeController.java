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

import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;
import com.netcracker.ca.service.MarkTypeService;

@RestController
public class MarkTypeController extends BaseApiController {

	@Autowired
    private MarkTypeService markTypeService;

    @GetMapping(value = "admin/api/properties")
    public List<MarkType> getAll() {
        return markTypeService.getAll();
    }
    
    @GetMapping(value = "admin/api/project/{projectId}/properties/projects")
    public List<MarkType> getByProjectProjects(@PathVariable int projectId) {
        return markTypeService.getAllowed(projectId, MarkTypeScope.PROJECTS);
    }
    
    @GetMapping(value = "admin/api/project/{projectId}/properties/meetings")
    public List<MarkType> getByProjectMeetings(@PathVariable int projectId) {
        return markTypeService.getAllowed(projectId, MarkTypeScope.MEETINGS);
    }

    @PostMapping("admin/api/property")
    public MarkType create(@RequestBody MarkType markType) {
    	markTypeService.add(markType);
        return markType;
    }

    @PutMapping("admin/api/property")
    public void update(@RequestBody MarkType markType) {
    	markTypeService.update(markType);
    }

    @DeleteMapping("admin/api/property/{id}")
    public void delete(@PathVariable int id) {
    	markTypeService.delete(id);
    }
	
}
