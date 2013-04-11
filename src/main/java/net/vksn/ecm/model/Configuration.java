package net.vksn.ecm.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Configuration extends net.vksn.bedrock.model.Entity {
	private static final long serialVersionUID = 1L;
	
	private String css;
	
	@Column
	public String getCss() {
		return css;
	}
	
	public void setCss(String css) {
		this.css = css;
	}
}
