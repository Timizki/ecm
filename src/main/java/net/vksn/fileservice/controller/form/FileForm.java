package net.vksn.fileservice.controller.form;

import net.vksn.fileservice.model.File;

public class FileForm {
	private File file;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getFileContent() {
		if(file == null || this.file.getFileContent() == null) {
			return "";
		}
		return new String(this.file.getFileContent());
	}
	
	public void setFileContent(String content) {
		if(this.file == null) {
			this.file = new File();
		}
		if(content != null) {
			byte[] bytes = content.getBytes();
			file.setFileSize(bytes.length);
			file.setFileContent(bytes);
		}
		else {
			file.setFileSize(0);
			file.setFileContent(null);
		}
	}
}
