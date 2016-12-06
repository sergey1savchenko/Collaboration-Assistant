package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.ProjectEvaluation;

@Component
public class ProjectEvaluationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProjectEvaluation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ProjectEvaluation evaluation = (ProjectEvaluation) target;

		if (evaluation.getIntValue() < 0 || evaluation.getIntValue() > 5) {
			errors.rejectValue("intValue", "Size.projectEvaluationForm.intValue");
		}

		if (evaluation.getTextValue().length() > 300) {
			errors.rejectValue("textValue", "Size.projectEvaluationForm.textValue");
		}
	}
}