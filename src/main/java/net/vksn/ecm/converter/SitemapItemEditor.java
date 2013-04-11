package net.vksn.ecm.converter;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Component;

@Component("sitemapItemEditor")
public class SitemapItemEditor extends ClassEditor {
	
	@Autowired
	private SitemapItemService sitemapItempService;
	
	public void setSitemapItempService(SitemapItemService service) {
		this.sitemapItempService = service;
	}
	
	@Override
	public void setAsText(String itemId) throws IllegalArgumentException {
		SitemapItem item = null;
		try {
			Integer id = Integer.valueOf(itemId);
			item = sitemapItempService.getItem(id);
		}
		catch(NumberFormatException e) {}
		catch(EntityNotFoundException e) {}
		setValue(item);
	}

	@Override
	public String getAsText() {
		if(getValue() != null) {
			return ((SitemapItem)getValue()).getId().toString();
		}
		return "";
	}
}
