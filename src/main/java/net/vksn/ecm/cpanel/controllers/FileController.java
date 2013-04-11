package net.vksn.ecm.cpanel.controllers;

import net.vksn.fileservice.controller.form.FileForm;
import net.vksn.fileservice.model.File;
import net.vksn.fileservice.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cpanel/files/*")
public class FileController {

	@Autowired
	private FileService fileService;
	
	
	@ModelAttribute
	public FileForm getEditForm(@RequestParam(value="fileName", required=false) String fileName, @RequestParam(value="fileSuffix", required=false) String fileSuffix) {
		FileForm form = new FileForm();
		if(fileName != null && fileSuffix != null) {
			File file = fileService.getFile(fileName, fileSuffix);
			form.setFile(file);
		}
		return form;
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public String showForm() {
		return "file.add";
	}

	@RequestMapping(value="list.do", method = RequestMethod.GET) 
	public ModelAndView listFiles() {
		ModelAndView mv = new ModelAndView("file.list");
		mv.addObject("files", fileService.getAllFiles());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)	
	public String storeFile(@ModelAttribute FileForm form) {
		fileService.store(form.getFile());
		return "file.list";
	}
}
