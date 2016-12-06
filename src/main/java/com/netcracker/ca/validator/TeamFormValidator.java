package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.Team;

@Component
public class TeamFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Team.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Team team = (Team) target;
		String title = team.getTitle().replaceAll(" ", "");
		team.setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.teamForm.title");

		if (team.getTitle().length() > 64) {
			errors.rejectValue("title", "Size.teamForm.title");
		}
	}
}