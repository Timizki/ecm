package net.vksn.fileservice.service;

import java.util.Set;

import net.vksn.fileservice.model.File;


public interface FileService {

	File getFile(String name, String fileSuffix);
	void store(File file);
	Set<File> getAllFiles();
}
