package net.vksn.fileservice.service.impl;

import java.util.Set;

import net.vksn.fileservice.dao.FileDAO;
import net.vksn.fileservice.model.File;
import net.vksn.fileservice.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDAO dao;
	
	@Override
	public File getFile(String name, String fileSuffix) {
		return dao.getFile(name);
	}
	
	@Override
	public void store(File file) {
		dao.store(file);
		
	}
	@Override
	public Set<File> getAllFiles() {
		return dao.getAllFiles();
	}
}
