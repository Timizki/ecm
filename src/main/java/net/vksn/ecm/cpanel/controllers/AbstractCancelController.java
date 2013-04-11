package net.vksn.ecm.cpanel.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class AbstractCancelController {

	
	@RequestMapping(value="**/cancel.do",method=RequestMethod.GET )
	public String cancel(@RequestParam String returnURL) {
		
		return returnURL;
	}
}
