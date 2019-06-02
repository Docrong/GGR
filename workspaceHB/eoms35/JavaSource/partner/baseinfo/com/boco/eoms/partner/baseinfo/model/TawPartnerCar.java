package com.boco.eoms.partner.baseinfo.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:车辆管理
 * </p>
 * <p>
 * Description:车辆管理
 * </p>
 * <p>
 * Thu Feb 05 13:54:40 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
public class TawPartnerCar extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -808678260828905560L;
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
	 * 车牌号
	 *
	 */
	private java.lang.String car_number;
   
	public void setCar_number(java.lang.String car_number){
		this.car_number= car_number;       
	}
   
	public java.lang.String getCar_number(){
		return this.car_number;
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
	 * 开始使用时间
	 *
	 */
	private java.util.Date start_time;
   
	public void setStart_time(java.util.Date start_time){
		this.start_time= start_time;       
	}
   
	public java.util.Date getStart_time(){
		return this.start_time;
	}

	/**
	 *
	 * 所属片区
	 *
	 */
	private java.lang.String piece;
   
	public void setPiece(java.lang.String piece){
		this.piece= piece;       
	}
   
	public java.lang.String getPiece(){
		return this.piece;
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
		if( o instanceof TawPartnerCar ) {
			TawPartnerCar tawPartnerCar=(TawPartnerCar)o;
			if (this.id != null || this.id.equals(tawPartnerCar.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}