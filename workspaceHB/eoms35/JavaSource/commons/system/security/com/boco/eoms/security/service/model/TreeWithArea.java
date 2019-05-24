package com.boco.eoms.security.service.model;



public class TreeWithArea implements java.io.Serializable {
	private String areaId = "";
	private String areaName = "";
	private String notes = "";
	private String parentAreaId ="";
	private int deleted = 0;
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

}
