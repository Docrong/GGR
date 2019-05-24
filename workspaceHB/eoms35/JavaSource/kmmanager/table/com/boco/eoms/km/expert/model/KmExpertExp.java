package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:工作经验
 * </p>
 * <p>
 * Description:工作经验
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertExp extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * 开始时间
	 *
	 */
	private java.util.Date expertStart;
   
	public void setExpertStart(java.util.Date expertStart){
		this.expertStart= expertStart;       
	}
   
	public java.util.Date getExpertStart(){
		return this.expertStart;
	}

	/**
	 *
	 * 结束时间
	 *
	 */
	private java.util.Date expertEnd;
   
	public void setExpertEnd(java.util.Date expertEnd){
		this.expertEnd= expertEnd;       
	}
   
	public java.util.Date getExpertEnd(){
		return this.expertEnd;
	}

	/**
	 *
	 * 职位名称
	 *
	 */
	private String expertPosition;
   
	public void setExpertPosition(String expertPosition){
		this.expertPosition= expertPosition;       
	}
   
	public String getExpertPosition(){
		return this.expertPosition;
	}

	/**
	 *
	 * 工作地点
	 *
	 */
	private String expertAddress;
   
	public void setExpertAddress(String expertAddress){
		this.expertAddress= expertAddress;       
	}
   
	public String getExpertAddress(){
		return this.expertAddress;
	}

	/**
	 *
	 * 工作职责
	 *
	 */
	private String expertResponsiblitily;
   
	public void setExpertResponsiblitily(String expertResponsiblitily){
		this.expertResponsiblitily= expertResponsiblitily;       
	}
   
	public String getExpertResponsiblitily(){
		return this.expertResponsiblitily;
	}

	/**
	 *
	 * userid
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
	 * 公司名称
	 *
	 */
	private String expertCompany;
   
	public void setExpertCompany(String expertCompany){
		this.expertCompany= expertCompany;       
	}
   
	public String getExpertCompany(){
		return this.expertCompany;
	}

	public boolean equals(Object o) {
		if( o instanceof KmExpertExp ) {
			KmExpertExp kmExpertExp=(KmExpertExp)o;
			if (this.id != null || this.id.equals(kmExpertExp.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}