package net.vksn.ecm.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;

import net.vksn.ecm.comparator.NavigationItemComparator;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NavigationLevel extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	private static final String PAGECONTEXT_STYLECLASS_ATTRIBUTE = "styleClass";

	private SitemapItemService sitemapItemService;
	private String var;
	private SitemapItem sitemapItem;
	private SitemapItem activeSitemapItem;
	private SitemapItem itemToHandle;
	private TreeSet<SitemapItem> currentLevelsItems;
	private Iterator<SitemapItem> currentLevelIterator;
	private int itemNumber = 0;

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public SitemapItem getActiveSitemapItem() {
		return activeSitemapItem;
	}

	public void setActiveSitemapItem(SitemapItem activeSitemapItem) {
		this.activeSitemapItem = activeSitemapItem;
	}

	public SitemapItem getSitemapItem() {
		return sitemapItem;
	}

	public void setSitemapItem(SitemapItem sitemapItem) {
		this.sitemapItem = sitemapItem;
	}

	private Set<SitemapItem> getSiblings() {
		Set<SitemapItem> siblings = null;
		SitemapItem parent = sitemapItem.getParent();
		if(parent != null) {
			siblings = parent.getChildrens();
		}
		else {
			siblings = sitemapItem.getSitemap().getSitemapItems();
		}
		return siblings;
	}
	
	
	@Override
	public int doStartTag() throws JspException {
		currentLevelsItems = new TreeSet<SitemapItem>(new NavigationItemComparator());
		currentLevelsItems.addAll(getSiblings());
		currentLevelIterator = currentLevelsItems.iterator();
		if(currentLevelIterator.hasNext()) {
			SitemapItem item = currentLevelIterator.next();
			pageContext.setAttribute(var, item);
			itemToHandle = item;
			String styleClass = "first";
			if (itemToHandle.equals(activeSitemapItem)) {
				styleClass = "first active";
			}
			pageContext.setAttribute("styleClass", styleClass);
			return EVAL_BODY_BUFFERED;
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
		if (currentLevelIterator.hasNext()) {
			itemToHandle = currentLevelIterator.next();

			StringBuffer styleClass = new StringBuffer();
			if (itemNumber == currentLevelsItems.size() - 1) {
				styleClass.append("last");
			}
			if (itemToHandle.equals(activeSitemapItem)) {
				styleClass.append(" active");
			}
			pageContext.setAttribute("styleClass", styleClass.toString());

			pageContext.setAttribute(var, itemToHandle);
			itemNumber++;
			return IterationTag.EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		pageContext.setAttribute(var, null);
		pageContext.setAttribute(PAGECONTEXT_STYLECLASS_ATTRIBUTE, null);
		
		itemNumber = 0;
		activeSitemapItem = null;
		currentLevelIterator = null;
		currentLevelsItems = null;
		return super.doEndTag();
	}
}
