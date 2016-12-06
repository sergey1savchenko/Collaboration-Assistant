package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.MeetingEvaluation;

@Component
public class MeetingEvaluationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MeetingEvaluation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		MeetingEvaluation evaluation = (MeetingEvaluation) target;

		if (evaluation.getIntValue() < 0 || evaluation.getIntValue() > 5) {
			errors.rejectValue("intValue", "Size.meetingEvaluationForm.intValue");
		}

		if (evaluation.getTextValue().length() > 300) {
			errors.rejectValue("textValue", "Size.meetingEvaluationForm.textValue");
		}
	}
}