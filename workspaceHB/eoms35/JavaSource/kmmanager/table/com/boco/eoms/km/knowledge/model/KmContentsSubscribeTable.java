package com.boco.eoms.km.knowledge.model;

import java.util.Date;

public class KmContentsSubscribeTable {
	private String id;
	private String createTime;
	private String subscribeUser;
	private String themeId;
	private String tableId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSubscribeUser() {
		return subscribeUser;
	}
	public void setSubscribeUser(String subscribeUser) {
		this.subscribeUser = subscribeUser;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
}
