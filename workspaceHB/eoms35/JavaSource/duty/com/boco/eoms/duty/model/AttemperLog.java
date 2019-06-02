package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:网调日志记录
 * </p>
 * <p>
 * Description:网调日志记录
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperLog extends BaseObject {

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
	 * 操作时间
	 *
	 */
	private String operTime;
   
	public void setOperTime(String operTime){
		this.operTime= operTime;       
	}
   
	public String getOperTime(){
		return this.operTime;
	}

	/**
	 *
	 * 操作人
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
	 * 操作人名字
	 *
	 */
	private String userName;
   
	public void setUserName(String userName){
		this.userName= userName;       
	}
   
	public String getUserName(){
		return this.userName;
	}

	/**
	 *
	 * 操作人部门
	 *
	 */
	private String deptId;
   
	public void setDeptId(String deptId){
		this.deptId= deptId;       
	}
   
	public String getDeptId(){
		return this.deptId;
	}

	/**
	 *
	 * 操作部门
	 *
	 */
	private String deptName;
   
	public void setDeptName(String deptName){
		this.deptName= deptName;       
	}
   
	public String getDeptName(){
		return this.deptName;
	}

	/**
	 *
	 * 模块类别
	 *
	 */
	private String type;
   
	public void setType(String type){
		this.type= type;       
	}
   
	public String getType(){
		return this.type;
	}

	/**
	 *
	 * 操作
	 *
	 */
	private String operName;
   
	public void setOperName(String operName){
		this.operName= operName;       
	}
   
	public String getOperName(){
		return this.operName;
	}

	/**
	 *
	 * 网调编号
	 *
	 */
	private String attemperId;
   
	public void setAttemperId(String attemperId){
		this.attemperId= attemperId;       
	}
   
	public String getAttemperId(){
		return this.attemperId;
	}

	/**
	 *
	 * 子过程编号
	 *
	 */
	private String subAttemperId;
   
	public void setSubAttemperId(String subAttemperId){
		this.subAttemperId= subAttemperId;       
	}
   
	public String getSubAttemperId(){
		return this.subAttemperId;
	}

	/**
	 *
	 * 主题
	 *
	 */
	private String title;
   
	public void setTitle(String title){
		this.title= title;       
	}
   
	public String getTitle(){
		return this.title;
	}

	/**
	 *
	 * 预计开始时间
	 *
	 */
	private String intendBeginTime;
   
	public void setIntendBeginTime(String intendBeginTime){
		this.intendBeginTime= intendBeginTime;       
	}
   
	public String getIntendBeginTime(){
		return this.intendBeginTime;
	}


	public boolean equals(Object o) {
		if( o instanceof AttemperLog ) {
			AttemperLog attemperLog=(AttemperLog)o;
			if (this.id != null || this.id.equals(attemperLog.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}