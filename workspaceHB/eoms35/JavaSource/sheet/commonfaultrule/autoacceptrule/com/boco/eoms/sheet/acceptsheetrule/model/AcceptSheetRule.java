package com.boco.eoms.sheet.acceptsheetrule.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:自动接单规则配置
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 * 
 * @author 史闯科
 * @version 3.5
 * 
 */
public class AcceptSheetRule extends BaseObject {

	/**
	 * 主键�
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
	 * 地域
	 *
	 */
	private java.lang.String faultArea;
   
	public void setFaultArea(java.lang.String faultArea){
		this.faultArea= faultArea;       
	}
   
	public java.lang.String getFaultArea(){
		return this.faultArea;
	}

	/**
	 *
	 * 网络分类（一级）
	 *
	 */
	private java.lang.String netSortOne;
   
	public void setNetSortOne(java.lang.String netSortOne){
		this.netSortOne= netSortOne;       
	}
   
	public java.lang.String getNetSortOne(){
		return this.netSortOne;
	}

	/**
	 *
	 * 网络分类（二级）
	 *
	 */
	private java.lang.String netSortTwo;
   
	public void setNetSortTwo(java.lang.String netSortTwo){
		this.netSortTwo= netSortTwo;       
	}
   
	public java.lang.String getNetSortTwo(){
		return this.netSortTwo;
	}

	/**
	 *
	 * 网络分类（三级）
	 *
	 */
	private java.lang.String netSortThree;
   
	public void setNetSortThree(java.lang.String netSortThree){
		this.netSortThree= netSortThree;       
	}
   
	public java.lang.String getNetSortThree(){
		return this.netSortThree;
	}

	/**
	 *
	 * 设备厂家
	 *
	 */
	private java.lang.String equipmentFactory;
   
	public void setEquipmentFactory(java.lang.String equipmentFactory){
		this.equipmentFactory= equipmentFactory;       
	}
   
	public java.lang.String getEquipmentFactory(){
		return this.equipmentFactory;
	}

	/**
	 *
	 * 值班人
	 *
	 */
	private java.lang.String dealHuman;
   
	public void setDealHuman(java.lang.String dealHuman){
		this.dealHuman= dealHuman;       
	}
   
	public java.lang.String getDealHuman(){
		return this.dealHuman;
	}

	   /**
	    * 删除标示位
	    */
   private int deleted;
 


	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	/**
	 * 人员所属子角色
	 */
	private java.lang.String subRole;
	
	
	public boolean equals(Object o) {
		if( o instanceof AcceptSheetRule ) {
			AcceptSheetRule acceptSheetRule=(AcceptSheetRule)o;
			if (this.id != null || this.id.equals(acceptSheetRule.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public java.lang.String getSubRole() {
		return subRole;
	}

	public void setSubRole(java.lang.String subRole) {
		this.subRole = subRole;
	}

}