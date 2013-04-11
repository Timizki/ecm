package net.vksn.ecm.controllers;

import javax.servlet.http.HttpServletRequest;

import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class PageController extends AbstractPageController {

	@Autowired
	private SitemapService sitemapService;
	
	@Autowired
	private SitemapItemService sitemapItemService;
	
	/**
	 * Try to load <code>SitemapItem</code>. If success continues to view rendering, <code>SitemapItem</code> contains decorations name.
	 * If fails return error views name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String page(Model model, HttpServletRequest request) {
		SitemapItem item = null;
		try {
			int sitemapId = getSitemapId(request);			
			String[] path = getPagePath(request);			
			item = sitemapItemService.getItemByPath(sitemapId, path);
			model.addAttribute("sitemapItem", item);
		} catch (Exception e) {			
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
		
		return item.getDecorationName();
	}

}
