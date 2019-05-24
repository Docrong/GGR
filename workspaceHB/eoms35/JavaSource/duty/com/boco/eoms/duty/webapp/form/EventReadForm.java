package com.boco.eoms.duty.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() EOMS3.5
 * 
 */
public class EventReadForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
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


}