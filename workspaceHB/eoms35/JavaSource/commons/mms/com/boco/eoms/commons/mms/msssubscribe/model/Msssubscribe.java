package com.boco.eoms.commons.mms.msssubscribe.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:彩信报订阅
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Fri Feb 20 11:32:20 CST 2009
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public class Msssubscribe extends BaseObject {

	/**
	 * 主键
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
	 * 彩信报模板id
	 *
	 */
	private java.lang.String mmsreport_templateId;
   
	public void setMmsreport_templateId(java.lang.String mmsreport_templateId){
		this.mmsreport_templateId= mmsreport_templateId;       
	}
   
	public java.lang.String getMmsreport_templateId(){
		return this.mmsreport_templateId;
	}

	/**
	 *
	 * 接收人
	 *
	 */
	private java.lang.String receivePerson;
   
	public void setReceivePerson(java.lang.String receivePerson){
		this.receivePerson= receivePerson;       
	}
   
	public java.lang.String getReceivePerson(){
		return this.receivePerson;
	}

	/**
	 *
	 * 接收人手机
	 *
	 */
	private java.lang.String receiveMobile;
   
	public void setReceiveMobile(java.lang.String receiveMobile){
		this.receiveMobile= receiveMobile;       
	}
   
	public java.lang.String getReceiveMobile(){
		return this.receiveMobile;
	}

	/**
	 *
	 * 接收时间点
	 *
	 */
	private java.lang.String receiveTime;
   
	public void setReceiveTime(java.lang.String receiveTime){
		this.receiveTime= receiveTime;       
	}
   
	public java.lang.String getReceiveTime(){
		return this.receiveTime;
	}

	/**
	 *
	 * 创建人
	 *
	 */
	private java.lang.String createPerson;
   
	public void setCreatePerson(java.lang.String createPerson){
		this.createPerson= createPerson;       
	}
   
	public java.lang.String getCreatePerson(){
		return this.createPerson;
	}

	/**
	 *
	 * 订阅时间
	 *
	 */
	private java.lang.String creatTime;
   
	public void setCreatTime(java.lang.String creatTime){
		this.creatTime= creatTime;       
	}
   
	public java.lang.String getCreatTime(){
		return this.creatTime;
	}
	private String mmreportName;
	

	public boolean equals(Object o) {
		if( o instanceof Msssubscribe ) {
			Msssubscribe msssubscribe=(Msssubscribe)o;
			if (this.id != null || this.id.equals(msssubscribe.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getMmreportName() {
		return mmreportName;
	}

	public void setMmreportName(String mmreportName) {
		this.mmreportName = mmreportName;
	}
}