package com.boco.eoms.commonfaulthj.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:commonfaulthj
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 * 
 * @author zhoupan
 * @version 3.5
 * 
 */
public class Commonfaulthj extends BaseObject {

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
	 * mainid
	 *
	 */
	private java.lang.String mainid;
   
	public void setMainid(java.lang.String mainid){
		this.mainid= mainid;       
	}
   
	public java.lang.String getMainid(){
		return this.mainid;
	}

	/**
	 *
	 * sheetid
	 *工单号
	 */
	private java.lang.String sheetid;
   
	public void setSheetid(java.lang.String sheetid){
		this.sheetid= sheetid;       
	}
   
	public java.lang.String getSheetid(){
		return this.sheetid;
	}

	/**
	 * creater
	 * 导入人
	 *
	 */
	private java.lang.String creater;
   
	public void setCreater(java.lang.String creater){
		this.creater= creater;       
	}
   
	public java.lang.String getCreater(){
		return this.creater;
	}

	/**
	 *
	 * savetime
	 *保存时间
	 */
	private java.util.Date savetime;
   
	public void setSavetime(java.util.Date savetime){
		this.savetime= savetime;       
	}
   
	public java.util.Date getSavetime(){
		return this.savetime;
	}

	/**
	 * 更新时间
	 * updatetime
	 *
	 */
	private java.util.Date updatetime;
   
	public void setUpdatetime(java.util.Date updatetime){
		this.updatetime= updatetime;       
	}
   
	public java.util.Date getUpdatetime(){
		return this.updatetime;
	}

	/**
	 *
	 * sendyear
	 *
	 */
	private java.lang.String sendyear;
   
	public void setSendyear(java.lang.String sendyear){
		this.sendyear= sendyear;       
	}
   
	public java.lang.String getSendyear(){
		return this.sendyear;
	}

	/**
	 *
	 * sendmonth
	 *
	 */
	private java.lang.String sendmonth;
   
	public void setSendmonth(java.lang.String sendmonth){
		this.sendmonth= sendmonth;       
	}
   
	public java.lang.String getSendmonth(){
		return this.sendmonth;
	}

	/**
	 *
	 * sendday
	 *
	 */
	private java.lang.String sendday;
   
	public void setSendday(java.lang.String sendday){
		this.sendday= sendday;       
	}
   
	public java.lang.String getSendday(){
		return this.sendday;
	}

	/**
	 *
	 * remark
	 * 核减理由
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	/**
	 * add by zhoupan 20160913
	 * 核减类型
	 *
	 */
	private java.lang.String subtractType;
	
	
  


	public java.lang.String getSubtractType() {
		return subtractType;
	}

	public void setSubtractType(java.lang.String subtractType) {
		this.subtractType = subtractType;
	}

	public boolean equals(Object o) {
		if( o instanceof Commonfaulthj ) {
			Commonfaulthj commonfaulthj=(Commonfaulthj)o;
			if (this.id != null || this.id.equals(commonfaulthj.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}