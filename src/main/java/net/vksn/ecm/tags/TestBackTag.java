package net.vksn.ecm.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TestBackTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String type;
	
	@Override
	public int doStartTag() throws JspException {
		super.pageContext.getRequest();
		return super.doStartTag();
	}
}
