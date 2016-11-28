package com.netcracker.ca.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.service.MarkTypeService;

@Controller
public class MarkTypeController extends BaseApiController {

	@Autowired
    private MarkTypeService markTypeService;

    @GetMapping(value = "admin/api/properties")
    public List<MarkType> getAll() {
        return markTypeService.getAll();
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
