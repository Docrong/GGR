package com.boco.eoms.workbench.netdisk.model;

import com.boco.eoms.base.model.BaseObject;

public class TawWorkbenchNetDiskFolderShare extends BaseObject {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 删除标志（0表示实际存在的，1表示被删除的）
	 */
	private String deleted;

	/**
	 * 共享来源用户Id
	 */
	private String fromUserId;

	/**
	 * 共享来源用户姓名
	 */
	private String fromUserName;

	/**
	 * 共享目标用户Id
	 */
	private String toUserId;

	/**
	 * 共享文件夹名
	 */
	private String shareFolderName;

	/**
	 * 共享文件夹路径
	 */
	private String shareFolderPath;

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getShareFolderName() {
		return shareFolderName;
	}

	public void setShareFolderName(String shareFolderName) {
		this.shareFolderName = shareFolderName;
	}

	public String getShareFolderPath() {
		return shareFolderPath;
	}

	public void setShareFolderPath(String shareFolderPath) {
		this.shareFolderPath = shareFolderPath;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
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
