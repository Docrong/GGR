package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:证书管理
 * </p>
 * <p>
 * Description:证书管理
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertCet extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422153111316328788L;
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
	 * 证书名称
	 *
	 */
	private String expertCetName;
   
	public void setExpertCetName(String expertCetName){
		this.expertCetName= expertCetName;       
	}
   
	public String getExpertCetName(){
		return this.expertCetName;
	}

	/**
	 *
	 * 获奖时间
	 *
	 */
	private java.util.Date expertCetDate;
   
	public void setExpertCetDate(java.util.Date expertCetDate){
		this.expertCetDate= expertCetDate;       
	}
   
	public java.util.Date getExpertCetDate(){
		return this.expertCetDate;
	}

	/**
	 *
	 * 简介
	 *
	 */
	private String expertCetDetail;
   
	public void setExpertCetDetail(String expertCetDetail){
		this.expertCetDetail= expertCetDetail;       
	}
   
	public String getExpertCetDetail(){
		return this.expertCetDetail;
	}

	/**
	 *
	 * 证书地址
	 *
	 */
	private String expertCetPath;
   
	public void setExpertCetPath(String expertCetPath){
		this.expertCetPath= expertCetPath;       
	}
   
	public String getExpertCetPath(){
		return this.expertCetPath;
	}

	public boolean equals(Object o) {
		if( o instanceof KmExpertCet ) {
			KmExpertCet kmExpertCet=(KmExpertCet)o;
			if (this.id != null || this.id.equals(kmExpertCet.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}