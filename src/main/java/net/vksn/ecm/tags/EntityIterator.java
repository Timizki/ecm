package net.vksn.ecm.tags;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.vksn.bedrock.model.Entity;

public class EntityIterator extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String var;
	private List<? extends Entity> itemsToIterate;
	private Iterator<? extends Entity> iterator;
	
	public String getVar() {
		return var;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public List<? extends Entity> getItemsToIterate() {
		return itemsToIterate;
	}
	
	public void setItemsToIterate(List<? extends Entity> itemsToIterate) {
		this.itemsToIterate = itemsToIterate;
	}
	
	@Override
	public int doStartTag() throws JspException {
		if(itemsToIterate != null && !itemsToIterate.isEmpty()) {
			this.iterator = itemsToIterate.iterator(); 
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_BUFFERED;
		}
		
		return SKIP_BODY;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()) {
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		this.itemsToIterate = null;
		this.iterator = null;
		pageContext.setAttribute(var, null);
		return super.doEndTag();
	}
}
