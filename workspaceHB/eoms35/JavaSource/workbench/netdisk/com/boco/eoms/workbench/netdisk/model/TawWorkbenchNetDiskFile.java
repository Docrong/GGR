package com.boco.eoms.workbench.netdisk.model;

public class TawWorkbenchNetDiskFile {

	private String id;

	private String fileName;

	private String mappingName;

	private String userId;

	private String folderMappingId;

	private String expand;

	private String uploadTime;

	private String fileSize;
	
	private Integer scanTimes = new Integer(0);
	
	/**
	 * @return the scanTimes
	 */
	public Integer getScanTimes() {
		return scanTimes;
	}

	/**
	 * @param scanTimes the scanTimes to set
	 */
	public void setScanTimes(Integer scanTimes) {
		this.scanTimes = scanTimes;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFolderMappingId() {
		return folderMappingId;
	}

	public void setFolderMappingId(String folderMappingId) {
		this.folderMappingId = folderMappingId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
