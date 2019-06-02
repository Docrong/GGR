package com.boco.eoms.km.train.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:反馈信息
 * </p>
 * <p>
 * Description:反馈信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:47 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public class TrainFeedbackForm extends BaseForm implements java.io.Serializable {

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
	 * 反馈人
	 *
	 */
	private java.lang.String feedbackUser;
   
	public void setFeedbackUser(java.lang.String feedbackUser){
		this.feedbackUser= feedbackUser;       
	}
   
	public java.lang.String getFeedbackUser(){
		return this.feedbackUser;
	}

	/**
	 *
	 * 所属部门
	 *
	 */
	private java.lang.String trainFeedbackDept;
   
	public void setTrainFeedbackDept(java.lang.String trainFeedbackDept){
		this.trainFeedbackDept= trainFeedbackDept;       
	}
   
	public java.lang.String getTrainFeedbackDept(){
		return this.trainFeedbackDept;
	}

	/**
	 *
	 * 联系电话
	 *
	 */
	private java.lang.String trainFeedbackTel;
   
	public void setTrainFeedbackTel(java.lang.String trainFeedbackTel){
		this.trainFeedbackTel= trainFeedbackTel;       
	}
   
	public java.lang.String getTrainFeedbackTel(){
		return this.trainFeedbackTel;
	}

	/**
	 *
	 * 反馈内容
	 *
	 */
	private java.lang.String trainFeedbackContent;
   
	public void setTrainFeedbackContent(java.lang.String trainFeedbackContent){
		this.trainFeedbackContent= trainFeedbackContent;       
	}
   
	public java.lang.String getTrainFeedbackContent(){
		return this.trainFeedbackContent;
	}

	/**
	 *
	 * 反馈时间
	 *
	 */
	private String trainFeedbackTime;
   
	public void setTrainFeedbackTime(String trainFeedbackTime){
		this.trainFeedbackTime= trainFeedbackTime;       
	}
   
	public String getTrainFeedbackTime(){
		return this.trainFeedbackTime;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private java.lang.String trainFeedbackRemark;
   
	public void setTrainFeedbackRemark(java.lang.String trainFeedbackRemark){
		this.trainFeedbackRemark= trainFeedbackRemark;       
	}
   
	public java.lang.String getTrainFeedbackRemark(){
		return this.trainFeedbackRemark;
	}

	/**
	 *
	 * 培训计划id
	 *
	 */
	private java.lang.String trainPlanId;
   
	public void setTrainPlanId(java.lang.String trainPlanId){
		this.trainPlanId= trainPlanId;       
	}
   
	public java.lang.String getTrainPlanId(){
		return this.trainPlanId;
	}

}