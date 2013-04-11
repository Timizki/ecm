package net.vksn.ecm.tags;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SitemapItemSiblings extends TagSupport {

	public SitemapItemSiblings() {
		super();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		this.sitemapItemService = webApplicationContext.getBean(SitemapItemService.class);
	}
	private SitemapItemService sitemapItemService;	
	private String var;
	private SitemapItem sitemapItem;
	
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public SitemapItem getSitemapItem() {
		return sitemapItem;
	}

	public void setSitemapItem(SitemapItem sitemapItem) {
		this.sitemapItem = sitemapItem;
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		Set<SitemapItem> siblings = sitemapItemService.getSiblings(sitemapItem);
		pageContext.setAttribute(var, siblings);
		return SKIP_BODY;
	}

}
