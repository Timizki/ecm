package net.vksn.fileservice.dao;

import java.util.Set;

import net.vksn.fileservice.dao.impl.FileQuery;
import net.vksn.fileservice.model.File;

public interface FileDAO {

	File getFile(String fileName);
	
	void store(File file);

	Set<File> getAllFiles();
	File getFile(FileQuery query);
}
