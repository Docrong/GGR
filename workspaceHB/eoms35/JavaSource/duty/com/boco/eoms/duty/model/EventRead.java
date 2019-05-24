package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:故障事件阅读
 * </p>
 * <p>
 * Description:故障事件阅读
 * </p>
 * <p>
 * Tue Apr 21 10:34:39 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
public class EventRead extends BaseObject {

	/**
	 * 锟斤拷锟�
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
	 * 事件编号
	 *
	 */
	private String eventid;
   
	public void setEventid(String eventid){
		this.eventid= eventid;       
	}
   
	public String getEventid(){
		return this.eventid;
	}

	/**
	 *
	 * 阅读人
	 *
	 */
	private String userid;
   
	public void setUserid(String userid){
		this.userid= userid;       
	}
   
	public String getUserid(){
		return this.userid;
	}

	/**
	 *
	 * 阅读时间
	 *
	 */
	private String readtime;
   
	public void setReadtime(String readtime){
		this.readtime= readtime;       
	}
   
	public String getReadtime(){
		return this.readtime;
	}

	/**
	 *
	 * 部门
	 *
	 */
	private String deptid;
   
	public void setDeptid(String deptid){
		this.deptid= deptid;       
	}
   
	public String getDeptid(){
		return this.deptid;
	}

	/**
	 *
	 * 阅读通知
	 *
	 */
	private String readaffirm;
   
	public void setReadaffirm(String readaffirm){
		this.readaffirm= readaffirm;       
	}
   
	public String getReadaffirm(){
		return this.readaffirm;
	}


	public boolean equals(Object o) {
		if( o instanceof EventRead ) {
			EventRead eventRead=(EventRead)o;
			if (this.id != null || this.id.equals(eventRead.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}