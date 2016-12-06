package com.netcracker.ca.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.service.FeedbackService;
import com.netcracker.ca.validator.FeedbackFormValidator;

@RestController
public class FeedbackController extends BaseApiController {

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
    private FeedbackFormValidator feedbackFormValidator;
    
    @InitBinder("feedbackForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(feedbackFormValidator);
	}

	@RequestMapping(value = "/hr/api/feedback/{app_form_id}", method = RequestMethod.GET, produces = "application/json")
	public Feedback get(@PathVariable(value = "app_form_id") int app_form_id) {
		return feedbackService.getByStudentId(app_form_id);
	}

	@RequestMapping(value = "/hr/api/feedback/{app_form_id}", method = RequestMethod.POST, produces = "application/json")
	public Feedback add(@RequestBody @Validated Feedback feedback, @PathVariable int app_form_id) {
		feedbackService.add(feedback, app_form_id);
		return feedback;
	}

	@RequestMapping(value = "/hr/api/feedback", method = RequestMethod.PUT)
	public void update(@RequestBody @Validated Feedback feedback) {
		feedbackService.update(feedback);
	}

	@RequestMapping(value = "/hr/api/feedback/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") int id) {
		feedbackService.delete(id);
	}
}