package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.Meeting;

@Component
public class MeetingFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Meeting.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Meeting meeting = (Meeting) target;
		String title = meeting.getTitle().replaceAll(" ", "").toLowerCase();
		meeting.setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.meetingForm.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.meetingForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datetime", "NotEmpty.meetingForm.datetime");

		if (meeting.getTitle().length() > 64) {
			errors.rejectValue("title", "Size.meetingForm.title");
		}

		if (meeting.getAddress().length() > 300) {
			errors.rejectValue("address", "Size.meetingForm.address");
		}
	}
}