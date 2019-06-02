package com.boco.eoms.km.knowledge.model;

import java.util.Date;

public class KmContentsCollect {
	private String id;
	private String collectUser;
	private String contentId;
	private String tableId;
	private String themeId;
	private Date collectTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollectUser() {
		return collectUser;
	}
	public void setCollectUser(String collectUser) {
		this.collectUser = collectUser;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
}
