package com.netcracker.ca.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netcracker.ca.model.Attachment;

@Component
public class AttachmentFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Attachment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Attachment attachment = (Attachment) target;

		if (attachment.getText().length() > 300) {
			errors.rejectValue("text", "Size.attachmentForm.text");
		}

		if (attachment.getLink().length() > 300) {
			errors.rejectValue("link", "Size.attachmentForm.link");
		}
		
		if (attachment.getMimeType().length() > 64) {
			errors.rejectValue("mimeType", "Size.attachmentForm.mimeType");
		}
	}
}