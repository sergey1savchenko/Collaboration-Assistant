package com.netcracker.ca.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.service.FeedbackService;

@Component
public class FeedbackFormValidator implements Validator {

	@Autowired
	FeedbackService feedbackService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Feedback.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Feedback feedback = (Feedback) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "generalReport", "NotEmpty.feedbackForm.generalReport");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "techReport", "NotEmpty.feedbackForm.techReport");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interviewer", "NotEmpty.feedbackForm.interviewer");
	}
}