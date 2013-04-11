package net.vksn.fileservice.controller;

import net.vksn.fileservice.model.File;
import net.vksn.fileservice.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResourceController {

	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "{fileName}.{fileSuffix}", method = RequestMethod.GET)
	public HttpEntity<byte[]> getFile(
			@PathVariable("fileName") String fileName, @PathVariable("fileSuffix") String suffix) {
		File file = fileService.getFile(fileName,suffix);
		byte[] documentBody = file.getFileContent();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType(file.getType(), file.getSubType()));
		header.setContentLength(documentBody.length);

		return new HttpEntity<byte[]>(documentBody, header);
	}
}
