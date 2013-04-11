package net.vksn.ecm.controllers.validator;

import net.vksn.ecm.controllers.form.SitemapItemForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SitemapItemFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SitemapItemForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		SitemapItemForm form = (SitemapItemForm)target;
		errors.pushNestedPath("sitemapItem");
		
		new SitemapItemValidator().validate(form.getSitemapItem(), errors);
		errors.popNestedPath();
	}

}
