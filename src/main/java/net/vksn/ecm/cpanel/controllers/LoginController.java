package net.vksn.ecm.cpanel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
		
	public LoginController() {
		
	}
    @RequestMapping(value="/login.do", method=RequestMethod.GET)
    public String handleLogin(HttpServletRequest request) {
    	log.debug("Entry::LoginController.handleLoginForm()");
        String errParam = request.getParameter("error");
        ModelAndView mv = new ModelAndView("login");
        if(errParam != null) {
            mv.addObject("errorMessage", "Kirjautuminen epäonnistui");
        }
        log.debug("Exiting::LoginController.handleLoginForm()");
        return "login";
    }
    
    @RequestMapping(value="/logout.do", method=RequestMethod.GET)
    public ModelAndView handleLogout(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView("login");
    	  request.getSession().invalidate();
    	  return mv;
    }
}
