package net.vksn.ecm.cpanel.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.query.UserQuery;
import net.vksn.bedrock.services.UserService;
import net.vksn.ecm.cpanel.controllers.form.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends AbstractCancelController {

	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("userForm")
	public UserForm getModelAttribute() throws EntityNotFoundException {
		UserForm form = new UserForm();
		form.setUser(new User());
		return form;
	}
	
	public UserForm getModelAttribute(@RequestParam String username) throws EntityNotFoundException {
		User user = userService.getUserByUsername(username);
		UserForm form = new UserForm();
		form.setUser(user);
		return form;
	}
	
	@RequestMapping(value="/cpanel/users.do", method = RequestMethod.GET)
	public ModelAndView listUser(HttpServletRequest request) {
		Collection<User> users = userService.getByQuery(new UserQuery());
		ModelAndView mv = new ModelAndView("user.list");
		mv.addObject("users", users);
		return mv;
	}
	@RequestMapping(value="/cpanel/user/details.do", method = RequestMethod.GET) 
	public String viewUser(@RequestParam String username, ModelMap model) throws EntityNotFoundException {
		model.addAttribute("userForm", getModelAttribute(username));
		return "user.details";
	}
	
	@RequestMapping(value="/cpanel/user/add.do",  method = RequestMethod.GET)
	public String showAddUserForm(@ModelAttribute User user, ModelMap model) throws EntityNotFoundException {
		UserForm form = new UserForm();
		form.setUser(user);		
		return "user.add";
	}
	
	@RequestMapping(value="/cpanel/user/edit.do", method = RequestMethod.GET)
	public String showEditUserForm(@RequestParam String username, ModelMap model) throws EntityNotFoundException {
		model.addAttribute("userForm", getModelAttribute(username));
		return "user.edit";
	}
	
	@RequestMapping(value="/cpanel/user/disable.do", method = RequestMethod.GET)
	public String disableUser(@RequestParam String username, HttpServletRequest request) throws EntityNotFoundException {
		User user = userService.getUserByUsername(username);
		user.setEnabled(false);
		userService.store(user);
		return "redirect://cpanel/users.do";
	}
	
	@RequestMapping(value="/cpanel/user/delete.do", method = RequestMethod.GET)
	public String deleteUser(@RequestParam Integer userId, HttpServletRequest request) throws EntityNotFoundException {
		userService.delete(userId);
		return "redirect://cpanel/users.do";
	}
	
	@RequestMapping(value="/cpanel/user/store.do", method = RequestMethod.POST)
	public String storeUser(@ModelAttribute("userForm")UserForm form, HttpServletRequest request) throws EntityNotFoundException {
		userService.store(form.getUser());
		return "redirect://cpanel/users.do";
	}
}
