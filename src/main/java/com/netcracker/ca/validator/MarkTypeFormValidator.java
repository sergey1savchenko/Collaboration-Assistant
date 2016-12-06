package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.MarkType;

@Component
public class MarkTypeFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MarkType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		MarkType type = (MarkType) target;
		String title = type.getTitle().replaceAll(" ", "");
		type.setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.markTypeForm.title");

		if (type.getTitle().length() > 64) {
			errors.rejectValue("title", "Size.markTypeForm.title");
		}
	}
}