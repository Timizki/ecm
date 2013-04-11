package net.vksn.ecm.cpanel.controller.validator;

import net.vksn.fileservice.controller.form.FileForm;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FileFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FileForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileForm form = (FileForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file.fileName", "required");
		ValidationUtils.rejectIfEmpty(errors, "file.type", "required");
		ValidationUtils.rejectIfEmpty(errors, "file.subType", "required");
		
	}
	
	

}
