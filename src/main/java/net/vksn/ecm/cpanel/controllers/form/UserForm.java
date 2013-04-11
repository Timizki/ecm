package net.vksn.ecm.cpanel.controllers.form;

import java.util.ArrayList;
import java.util.List;

import net.vksn.bedrock.model.Group;
import net.vksn.bedrock.model.User;

public class UserForm {

	private User user;
	private String password;
	private List<Group> groups;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Group> getGroups() {
		if(this.groups == null) {
			this.groups = new ArrayList<Group>();
		}
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
