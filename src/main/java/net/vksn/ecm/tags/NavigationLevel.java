package net.vksn.ecm.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;

import net.vksn.sitemap.model.SitemapItem;

public class NavigationLevel extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	private static final String PAGECONTEXT_STYLECLASS_ATTRIBUTE = "styleClass";

	private String var;
	private SitemapItem activeSitemapItem;
	private SitemapItem itemToHandle;
	private Set<SitemapItem> levelsItems;
	private Iterator<SitemapItem> levelIterator;
	private int itemNumber = 0;
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public Set<SitemapItem> getLevelItems() {
		return this.levelsItems;
	}
	
	public void setLevelItems(Set<SitemapItem> levelsItems) {
		this.levelsItems = levelsItems;
	}
	
	public SitemapItem getActiveSitemapItem() {
		return activeSitemapItem;
	}

	public void setActiveSitemapItem(SitemapItem activeSitemapItem) {
		this.activeSitemapItem = activeSitemapItem;
	}
	
	@Override
	public int doStartTag() throws JspException {
		levelIterator = levelsItems.iterator();
		if(levelIterator.hasNext()) {
			SitemapItem item = levelIterator.next();
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
		if (levelIterator.hasNext()) {
			itemToHandle = levelIterator.next();

			StringBuffer styleClass = new StringBuffer();
			if (itemNumber == levelsItems.size() - 1) {
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
		levelIterator = null;
		levelsItems = null;
		return super.doEndTag();
	}
}
