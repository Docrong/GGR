package com.boco.eoms.workbench.netdisk.webapp.util;

/**
 * 
 * <p>
 * Title:网络U盘属性
 * </p>
 * <p>
 * Description:网络U盘属性
 * </p>
 * <p>
 * Date:2008-5-6 14:08:50
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 *
 */
public class NetDiskAttributes {

	/**
	 * 网络U盘根路径
	 */
	private String netDiskRootPath;
	
	/**
	 * 最大单个上传文件限制（单位MB）
	 */
	private String maxFileSize;
	
	/**
	 * 用户个人空间（单位MB）
	 */
	private String maxUserSpace;
	
	public String getMaxFileSize() {
		return maxFileSize;
	}
	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}
	public String getMaxUserSpace() {
		return maxUserSpace;
	}
	public void setMaxUserSpace(String maxUserSpace) {
		this.maxUserSpace = maxUserSpace;
	}
	public String getNetDiskRootPath() {
		return netDiskRootPath;
	}
	public void setNetDiskRootPath(String netDiskRootPath) {
		this.netDiskRootPath = netDiskRootPath;
	}
	
}
