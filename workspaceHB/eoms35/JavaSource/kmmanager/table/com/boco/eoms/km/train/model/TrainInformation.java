package com.boco.eoms.km.train.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:10:34 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainInformation extends BaseObject {

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
	 * 培训名称
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
	 * 培训等级
	 *
	 */
	private java.lang.String trainGrade;
   
	public void setTrainGrade(java.lang.String trainGrade){
		this.trainGrade= trainGrade;       
	}
   
	public java.lang.String getTrainGrade(){
		return this.trainGrade;
	}

	/**
	 *
	 * 培训地点
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
	 * 培训时长
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
	 * 培训开始时间
	 *
	 */
	private java.util.Date trainBeginTime;
   

	/**
	 *
	 * 培训结束时间
	 *
	 */
	private java.util.Date trainEndTime;
   

	/**
	 *
	 * 组织单位
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
	 * 文件编号
	 *
	 */
	private java.lang.String trainDocument;
   
	public void setTrainDocument(java.lang.String trainDocument){
		this.trainDocument= trainDocument;       
	}
   
	public java.lang.String getTrainDocument(){
		return this.trainDocument;
	}

	/**
	 *
	 * 培训厂家
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
	 * 专业
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
	 * 培训成绩
	 */
	private java.lang.String trainScore;
	
	/**
	 * 培训反馈
	 */
	private java.lang.String trainFeedback;
	
	/**
	 * 内训
	 */
	private java.lang.String trainInternal;
	
	public boolean equals(Object o) {
		if( o instanceof TrainInformation ) {
			TrainInformation trainInformation=(TrainInformation)o;
			if (this.id != null || this.id.equals(trainInformation.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public java.lang.String getTrainScore() {
		return trainScore;
	}

	public void setTrainScore(java.lang.String trainScore) {
		this.trainScore = trainScore;
	}

	public java.lang.String getTrainFeedback() {
		return trainFeedback;
	}

	public void setTrainFeedback(java.lang.String trainFeedback) {
		this.trainFeedback = trainFeedback;
	}

	public java.lang.String getTrainInternal() {
		return trainInternal;
	}

	public void setTrainInternal(java.lang.String trainInternal) {
		this.trainInternal = trainInternal;
	}

	public java.util.Date getTrainBeginTime() {
		return trainBeginTime;
	}

	public void setTrainBeginTime(java.util.Date trainBeginTime) {
		this.trainBeginTime = trainBeginTime;
	}

	public java.util.Date getTrainEndTime() {
		return trainEndTime;
	}

	public void setTrainEndTime(java.util.Date trainEndTime) {
		this.trainEndTime = trainEndTime;
	}
}