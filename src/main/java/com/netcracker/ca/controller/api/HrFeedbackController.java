package com.netcracker.ca.controller.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.service.FeedbackService;




@RestController
public class HrFeedbackController extends BaseApiController{
    
    Logger logger = LogManager.getLogger("Error.Files");

    @Autowired
    private FeedbackService feedbackService;
       
    
    @RequestMapping(value = "/hr/feedback/hrFeedbackStudent/{app_form_id}", method = RequestMethod.GET, produces = "application/json")
    public List<Feedback> get(@PathVariable(value = "app_form_id") int app_form_id) {	
	System.out.println(feedbackService.getById(app_form_id));
	
	//here must be method to getByAppFormId();
	//or student`s id

        return feedbackService.getAll();
    }
    
    
    
    @RequestMapping(value = "/hr/hrFeedback/hrFeedbackStude", method = RequestMethod.POST, produces = "application/json")
    public Feedback add(@RequestBody Feedback feedback) {
	//feedbackService.add(new Feedback(0, feedback.getGeneralReport(), feedback.getTechReport(), feedback.getInterviewer(), feedback.getInterviewStatus(), , student, project));
	System.out.println(feedback);
        return feedback;
    }
    
    @RequestMapping(value = "/hr/hrFeedback/hrFeedbackStudent", method = RequestMethod.PUT)
    public void update(@RequestBody Feedback feedback) {
        feedbackService.update(feedback);
    }
     

    @RequestMapping(value = "/hrFeedback{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
	feedbackService.delete(id);
    }
    
}