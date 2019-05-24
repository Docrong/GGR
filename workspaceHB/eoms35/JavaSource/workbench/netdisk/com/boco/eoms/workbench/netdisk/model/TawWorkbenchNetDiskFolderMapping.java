package com.boco.eoms.workbench.netdisk.model;

import com.boco.eoms.base.model.BaseObject;

public class TawWorkbenchNetDiskFolderMapping extends BaseObject {

	/**
	 * 主键,也是在服务器上实际创建的文件夹名
	 */
	private String id;

	/**
	 * 文件夹名称
	 */
	private String folderName;

	/**
	 * 所属用户Id
	 */
	private String userId;
	
	/**
	 * 父文件夹ID
	 */
	private String parentId;

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}	

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean equals(Object o) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String toString() {
		return null;
	}

}
