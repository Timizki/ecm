package net.vksn.ecm.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Navigation extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String var;
	private int depth = 1;
	private int round = 1;
	private SitemapItem sitemapItem;
	private SitemapItem itemToIterate;
	private Integer sitemapId;
	private Sitemap sitemap;
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public SitemapItem getSitemapItem() {
		return sitemapItem;
	}

	public void setSitemapItem(SitemapItem sitemapItem) {
		this.sitemapItem = sitemapItem;
	}

	public Integer getSitemapId() {
		return sitemapId;
	}

	public void setSitemapId(Integer sitemapId) {
		this.sitemapId = sitemapId;
	}

	public Sitemap getSitemap() {
		return sitemap;
	}
	
	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
	
	@Override
	public int doStartTag() throws JspException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(super.pageContext.getServletContext());
		SitemapService sitemapService = context.getBean(SitemapService.class);
		Sitemap sitemap = getSitemap();
		if(sitemap == null) {
			try {
				sitemap = sitemapService.getSitemap(sitemapId, false);
			} catch (EntityNotFoundException e) {
			}
		}
		if(!sitemap.getSitemapItems().isEmpty()) {
			sitemapItem = sitemap.getSitemapItems().iterator().next();
		}
		itemToIterate = sitemapItem;
		if(itemToIterate != null) {
			while (itemToIterate.getParent() != null) {
				itemToIterate = itemToIterate.getParent();
			}
			pageContext.setAttribute(var, itemToIterate);
			if(itemToIterate.getChildrens().isEmpty()) {
				pageContext.setAttribute("isLastItem", true);
			}
			return BodyTag.EVAL_BODY_BUFFERED;
		}
		return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			BodyContent bodyContent = super.getBodyContent();
			String bodyString = bodyContent.getString();
			bodyContent.getEnclosingWriter().print(bodyString);
			bodyContent.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		if (round < depth && !itemToIterate.getChildrens().isEmpty()) {
			itemToIterate = itemToIterate.getChildrens().iterator().next();
			pageContext.setAttribute(var, itemToIterate);
			round++;
			if(round == itemToIterate.getChildrens().size()-1) {
				pageContext.setAttribute("isLastItem", true);
			}
			return IterationTag.EVAL_BODY_AGAIN;
		}
		return Tag.SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		itemToIterate = null;
		pageContext.setAttribute(var, null);
		pageContext.setAttribute("isLastItem", null);
		var = null;
		round = 1;
		return super.doEndTag();
	}
}
