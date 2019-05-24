package com.boco.eoms.sheet.tool.complaintmsgconfig.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:投诉工单短信配置类
 * </p>
 * <p>
 * Description:投诉工单短信配置类
 * </p>
 * <p>
 * Mon Sep 14 10:06:54 CST 2009
 * </p>
 * 
 * @author qinmin
 * @version 1.0
 * 
 */
public class ComplaintSheetMsgConfig extends BaseObject {

	/**
	 * ID
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 订阅者ID
	 *
	 */
	private String userId;
   
	public void setUserId(String userId){
		this.userId= userId;       
	}
   
	public String getUserId(){
		return this.userId;
	}

	/**
	 *
	 * 故障区域ID
	 *
	 */
	private String areaId;
   
	public void setAreaId(String areaId){
		this.areaId= areaId;       
	}
   
	public String getAreaId(){
		return this.areaId;
	}

	/**
	 *
	 * 投诉类型
	 *
	 */
	private String complaintType;
   
	public void setComplaintType(String complaintType){
		this.complaintType= complaintType;       
	}
   
	public String getComplaintType(){
		return this.complaintType;
	}

	/**
	 *
	 * 需通知的领导ID
	 *
	 */
	private String notifyUserIds;
   
	public void setNotifyUserIds(String notifyUserIds){
		this.notifyUserIds= notifyUserIds;       
	}
   
	public String getNotifyUserIds(){
		return this.notifyUserIds;
	}


	private String accessoriesId;
	
	public String getAccessoriesId() {
		return accessoriesId;
	}
	
	public void setAccessoriesId(String accessoriesId) {
		this.accessoriesId = accessoriesId;
	}

	public boolean equals(Object o) {
		if( o instanceof ComplaintSheetMsgConfig ) {
			ComplaintSheetMsgConfig complaintSheetMsgConfig=(ComplaintSheetMsgConfig)o;
			if (this.id != null || this.id.equals(complaintSheetMsgConfig.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}