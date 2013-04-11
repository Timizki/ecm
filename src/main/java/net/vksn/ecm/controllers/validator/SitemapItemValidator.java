package net.vksn.ecm.controllers.validator;

import net.vksn.sitemap.model.SitemapItem;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SitemapItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SitemapItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SitemapItem item = (SitemapItem) target;

		if (item.getParent() == null && item.getSitemap() == null) {
			errors.rejectValue("sitemap", "required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "decorationName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		if(item.getName() != null) {
			String name = item.getName().toLowerCase();
			if(name.contains(" ") || name.contains(".")) {
				errors.rejectValue("name", "invalid");
			}
		}
	}

}
