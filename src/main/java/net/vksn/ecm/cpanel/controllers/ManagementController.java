package net.vksn.ecm.cpanel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagementController {

	@RequestMapping(value="/cpanel/management.do")
	public String showManagementPage(HttpServletRequest request) {
		return "site.management";
	}
}
