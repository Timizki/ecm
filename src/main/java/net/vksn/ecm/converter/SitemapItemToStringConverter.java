package net.vksn.ecm.converter;

import net.vksn.sitemap.model.SitemapItem;

import org.springframework.core.convert.converter.Converter;

final class SitemapItemToStringConverter implements Converter<SitemapItem, String>{

	@Override
	public String convert(SitemapItem source) {
		if(source.getId() != null) {
			return source.getId().toString();
		}
		return "";
	}

}
