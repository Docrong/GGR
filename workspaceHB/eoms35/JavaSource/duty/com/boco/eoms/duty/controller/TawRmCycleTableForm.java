package com.boco.eoms.duty.controller;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.validator.*;
public class TawRmCycleTableForm extends ValidatorForm {
	private String id; // 标识

	private String name; // 中文名

	private String remark; // 备注

	private String model; // 模块

	private String url;// 地址

	private String roomId; // 机房名称

	private String deptId; // 名称
	
	private String creatUser; // 创建人

	private String creatTime; // 创建时间
	
	private String deleted ;

	  //文件
	  private FormFile thisFile; 
	  public FormFile getThisFile() { 
	  return thisFile; 
	  } 

	  public void setThisFile(FormFile thisFile) { 
	  this.thisFile = thisFile; 
	  }

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	} 
}