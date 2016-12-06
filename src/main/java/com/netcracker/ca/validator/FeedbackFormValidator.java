package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.Feedback;

@Component
public class FeedbackFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Feedback.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Feedback feedback = (Feedback) target;
		
		if (feedback.getGeneralReport().length() > 500) {
			errors.rejectValue("generalReport", "Size.feedbackForm.generalReport");
		}

		if (feedback.getTechReport().length() > 500) {
			errors.rejectValue("techReport", "Size.feedbackForm.techReport");
		}
		
		if (feedback.getInterviewer().length() > 64) {
			errors.rejectValue("interviewer", "Size.feedbackForm.interviewer");
		}
	}
}