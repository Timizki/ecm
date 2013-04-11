package net.vksn.ecm.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class ReturnUrlTag extends TagSupport {

	private String var;

	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)super.pageContext.getRequest();
		String uri = request.getRequestURI();
		uri = uri.substring(7); //removes http://
		uri = uri.substring(uri.indexOf("/"));
		
		super.pageContext.setAttribute(var, uri);
		super.pageContext.setAttribute("returnURLParameterName", "returnURL");
		return Tag.SKIP_BODY;
	}
}
