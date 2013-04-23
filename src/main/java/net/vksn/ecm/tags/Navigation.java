package net.vksn.ecm.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapService;

public class Navigation extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String var;
	private int depth = 1;
	private int round = 1;
	private SitemapItem sitemapItem;
	private Integer sitemapId;
	private Sitemap sitemap;
	private List<SitemapItem> path = new ArrayList<SitemapItem>();
	
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

	public Sitemap getSitemap() {
		if(this.sitemap == null) {
			WebApplicationContext webContext = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
			SitemapService service = webContext.getBean(SitemapService.class);
			try {
				this.sitemap = service.getSitemap(this.sitemapId, true);
			} catch (EntityNotFoundException e) {
				this.sitemap = service.getDefaultSitemap();
			}
		}
		return sitemap;
	}
	
	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
	
	public Integer getSitemapId() {
		return sitemapId;
	}
	
	public void setSitemapId(Integer sitemapId) {
		this.sitemapId = sitemapId;
	}
	
	@Override
	public int doStartTag() throws JspException {
		buildPath();
		Set<SitemapItem> sitemapItems = getSitemap().getSitemapItems();
		if(!sitemapItems.isEmpty()) {
			pageContext.setAttribute(var, sitemapItems);
			return BodyTag.EVAL_BODY_BUFFERED;
		}
		return SKIP_BODY;
	}

	private void buildPath() {
		path.add(sitemapItem);
		SitemapItem parent = sitemapItem.getParent();
		while(parent != null) {
			path.add(parent);
			parent = parent.getParent();
		}
		Collections.reverse(path);
	}

	private Set<SitemapItem> getLevelsSitemapItems() {
		Iterator<SitemapItem> i = path.iterator();
		Set<SitemapItem> items = null;
		if(i.hasNext()) {
			items = i.next().getChildrens();
			i.remove();
		}
		return items;
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
		Iterator<SitemapItem> pathIterator = path.iterator();
		if (round < depth && pathIterator.hasNext()) {			
			pageContext.setAttribute(var, pathIterator.next().getChildrens());
			round++;
			return IterationTag.EVAL_BODY_AGAIN;
		}
		return Tag.SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		path = null;
		pageContext.setAttribute(var, null);
		var = null;
		round = 1;
		return super.doEndTag();
	}
}
