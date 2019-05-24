package com.boco.eoms.km.file.util;

/**
 * 文件管理属性配置
 */
public class KmFilesAttributes {

	/**
	 * 文件管理存根路径
	 */
	private String netDiskRootPath;
	
	/**
	 * 最大单个上传文件限制（单位MB）
	 */
	private String maxFileSize;
	
	public String getMaxFileSize() {
		return maxFileSize;
	}
	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}
	public String getNetDiskRootPath() {
		return netDiskRootPath;
	}
	public void setNetDiskRootPath(String netDiskRootPath) {
		this.netDiskRootPath = netDiskRootPath;
	}
}
