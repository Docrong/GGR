package com.boco.eoms.partner.baseinfo.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:油机信息
 * </p>
 * <p>
 * Description:油机信息
 * </p>
 * <p>
 * Thu Feb 05 13:56:15 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawPartnerOil extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422772872331289902L;
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
	 * 添加人
	 *
	 */
	private java.lang.String addMan;
	public java.lang.String getAddMan() {
		return addMan;
	}

	public void setAddMan(java.lang.String addMan) {
		this.addMan = addMan;
	}

	/**
	 *
	 * 添加时间
	 *
	 */
	private java.util.Date addTime;
	public java.util.Date getAddTime() {
		return addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
	}
	/**
	 *
	 * 添加人联系方式
	 *
	 */
	private java.lang.String connectType;
	public java.lang.String getConnectType() {
		return connectType;
	}

	public void setConnectType(java.lang.String connectType) {
		this.connectType = connectType;
	}
	/**
	 *
	 * 油机编号
	 *
	 */
	private java.lang.String oil_number;
   
	public void setOil_number(java.lang.String oil_number){
		this.oil_number= oil_number;       
	}
   
	public java.lang.String getOil_number(){
		return this.oil_number;
	}

	/**
	 *
	 * 油机厂家
	 *
	 */
	private java.lang.String factory;
   
	public void setFactory(java.lang.String factory){
		this.factory= factory;       
	}
   
	public java.lang.String getFactory(){
		return this.factory;
	}

	/**
	 *
	 * 所属公司
	 *
	 */
	private java.lang.String dept_id;
   
	public void setDept_id(java.lang.String dept_id){
		this.dept_id= dept_id;       
	}
   
	public java.lang.String getDept_id(){
		return this.dept_id;
	}

	/**
	 *
	 * 所属地市
	 *
	 */
	private java.lang.String area_id;
   
	public void setArea_id(java.lang.String area_id){
		this.area_id= area_id;       
	}
   
	public java.lang.String getArea_id(){
		return this.area_id;
	}

	/**
	 *
	 * 油机型号
	 *
	 */
	private java.lang.String type;
   
	public void setType(java.lang.String type){
		this.type= type;       
	}
   
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *
	 * 功率
	 *
	 */
	private java.lang.String power;
   
	public void setPower(java.lang.String power){
		this.power= power;       
	}
   
	public java.lang.String getPower(){
		return this.power;
	}

	/**
	 *
	 * 性质
	 *
	 */
	private java.lang.String kind;
   
	public void setKind(java.lang.String kind){
		this.kind= kind;       
	}
   
	public java.lang.String getKind(){
		return this.kind;
	}

	/**
	 *
	 * 运行状态
	 *
	 */
	private java.lang.String state;
   
	public void setState(java.lang.String state){
		this.state= state;       
	}
   
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *
	 * 储备地点
	 *
	 */
	private java.lang.String storage;
   
	public void setStorage(java.lang.String storage){
		this.storage= storage;       
	}
   
	public java.lang.String getStorage(){
		return this.storage;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}

	public boolean equals(Object o) {
		if( o instanceof TawPartnerOil ) {
			TawPartnerOil tawPartnerOil=(TawPartnerOil)o;
			if (this.id != null || this.id.equals(tawPartnerOil.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}