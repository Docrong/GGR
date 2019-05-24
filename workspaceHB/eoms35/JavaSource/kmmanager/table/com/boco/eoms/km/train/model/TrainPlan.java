package com.boco.eoms.km.train.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:培训计划
 * </p>
 * <p>
 * Description:培训及哈
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainPlan extends BaseObject {

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
	 * 培训计划名称
	 *
	 */
	private java.lang.String trainName;
   
	public void setTrainName(java.lang.String trainName){
		this.trainName= trainName;       
	}
   
	public java.lang.String getTrainName(){
		return this.trainName;
	}

	/**
	 *
	 * 培训发布人
	 *
	 */
	private java.lang.String trainUser;
   
	public void setTrainUser(java.lang.String trainUser){
		this.trainUser= trainUser;       
	}
   
	public java.lang.String getTrainUser(){
		return this.trainUser;
	}

	/**
	 *
	 * 所属部门
	 *
	 */
	private java.lang.String trainDept;
   
	public void setTrainDept(java.lang.String trainDept){
		this.trainDept= trainDept;       
	}
   
	public java.lang.String getTrainDept(){
		return this.trainDept;
	}

	/**
	 *
	 * 联系电话
	 *
	 */
	private java.lang.String trainTel;
   
	public void setTrainTel(java.lang.String trainTel){
		this.trainTel= trainTel;       
	}
   
	public java.lang.String getTrainTel(){
		return this.trainTel;
	}

	/**
	 *
	 * 培训开始时间
	 *
	 */
	private Date trainBeginTime;
   
	public void setTrainBeginTime(Date trainBeginTime){
		this.trainBeginTime= trainBeginTime;       
	}
   
	public Date getTrainBeginTime(){
		return this.trainBeginTime;
	}

	/**
	 *
	 * 培训内容
	 *
	 */
	private java.lang.String trainContent;
   
	public void setTrainContent(java.lang.String trainContent){
		this.trainContent= trainContent;       
	}
   
	public java.lang.String getTrainContent(){
		return this.trainContent;
	}

	/**
	 *
	 * 地点
	 *
	 */
	private java.lang.String trainAddress;
   
	public void setTrainAddress(java.lang.String trainAddress){
		this.trainAddress= trainAddress;       
	}
   
	public java.lang.String getTrainAddress(){
		return this.trainAddress;
	}

	/**
	 *
	 * 培训天数
	 *
	 */
	private java.lang.String trainTime;
   
	public void setTrainTime(java.lang.String trainTime){
		this.trainTime= trainTime;       
	}
   
	public java.lang.String getTrainTime(){
		return this.trainTime;
	}

	/**
	 *
	 * 组织单位
	 *
	 */
	private java.lang.String trainUnit;
   
	public void setTrainUnit(java.lang.String trainUnit){
		this.trainUnit= trainUnit;       
	}
   
	public java.lang.String getTrainUnit(){
		return this.trainUnit;
	}

	/**
	 *
	 * 涉及厂家
	 *
	 */
	private java.lang.String trainVender;
   
	public void setTrainVender(java.lang.String trainVender){
		this.trainVender= trainVender;       
	}
   
	public java.lang.String getTrainVender(){
		return this.trainVender;
	}

	/**
	 *
	 * 涉及专业
	 *
	 */
	private java.lang.String trainSpeciality;
   
	public void setTrainSpeciality(java.lang.String trainSpeciality){
		this.trainSpeciality= trainSpeciality;       
	}
   
	public java.lang.String getTrainSpeciality(){
		return this.trainSpeciality;
	}

	/**
	 *
	 * 设备类型
	 *
	 */
	private java.lang.String trainType;
   
	public void setTrainType(java.lang.String trainType){
		this.trainType= trainType;       
	}
   
	public java.lang.String getTrainType(){
		return this.trainType;
	}

	/**
	 *
	 * 涉及的培训需求编号
	 *
	 */
	private java.lang.String trainRequireNo;
   
	public void setTrainRequireNo(java.lang.String trainRequireNo){
		this.trainRequireNo= trainRequireNo;       
	}
   
	public java.lang.String getTrainRequireNo(){
		return this.trainRequireNo;
	}

	/**
	 *
	 * 报名截止时间
	 *
	 */
	private Date trainEndTime;
   
	public void setTrainEndTime(Date trainEndTime){
		this.trainEndTime= trainEndTime;       
	}
   
	public Date getTrainEndTime(){
		return this.trainEndTime;
	}

	/**
	 *
	 * 附件名称
	 *
	 */
	private java.lang.String fileName;
   
	public void setFileName(java.lang.String fileName){
		this.fileName= fileName;       
	}
   
	public java.lang.String getFileName(){
		return this.fileName;
	}

	public boolean equals(Object o) {
		if( o instanceof TrainPlan ) {
			TrainPlan trainPlan=(TrainPlan)o;
			if (this.id != null || this.id.equals(trainPlan.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}