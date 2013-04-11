package net.vksn.fileservice.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.transaction.annotation.Transactional;

import net.vksn.bedrock.model.Entity;

@javax.persistence.Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "fileName",
		"fileSuffix" }) })
public class File extends Entity {

	private static final long serialVersionUID = 1L;
	private Metadata metadata;
	private String fileName;
	private int fileSize;
	private String type;
	private String subType;
	private byte[] fileContent;
	private String fileSuffix;

	@Column
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Column
	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	@Column
	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	@Column
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column
	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
}
