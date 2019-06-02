package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:培训经历
 * </p>
 * <p>
 * Description:培训经历
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertTrain extends BaseObject {

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
	 * 培训时间
	 *
	 */
	private java.util.Date expertTrainDate;
   
	public void setExpertTrainDate(java.util.Date expertTrainDate){
		this.expertTrainDate= expertTrainDate;       
	}
   
	public java.util.Date getExpertTrainDate(){
		return this.expertTrainDate;
	}

	/**
	 *
	 * 培训时长
	 *
	 */
	private String expertTrainTime;
   
	public void setExpertTrainTime(String expertTrainTime){
		this.expertTrainTime= expertTrainTime;       
	}
   
	public String getExpertTrainTime(){
		return this.expertTrainTime;
	}

	/**
	 *
	 * 培训课程
	 *
	 */
	private String expertTrainLesson;
   
	public void setExpertTrainLesson(String expertTrainLesson){
		this.expertTrainLesson= expertTrainLesson;       
	}
   
	public String getExpertTrainLesson(){
		return this.expertTrainLesson;
	}

	/**
	 *
	 * 城市
	 *
	 */
	private String expertCity;
   
	public void setExpertCity(String expertCity){
		this.expertCity= expertCity;       
	}
   
	public String getExpertCity(){
		return this.expertCity;
	}

	/**
	 *
	 * 培训机构
	 *
	 */
	private String expertUnit;
   
	public void setExpertUnit(String expertUnit){
		this.expertUnit= expertUnit;       
	}
   
	public String getExpertUnit(){
		return this.expertUnit;
	}

	/**
	 *
	 * 获得证书
	 *
	 */
	private String expertLicense;
   
	public void setExpertLicense(String expertLicense){
		this.expertLicense= expertLicense;       
	}
   
	public String getExpertLicense(){
		return this.expertLicense;
	}

	/**
	 *
	 * 培训描述
	 *
	 */
	private String expertTrainDes;
   
	public void setExpertTrainDes(String expertTrainDes){
		this.expertTrainDes= expertTrainDes;       
	}
   
	public String getExpertTrainDes(){
		return this.expertTrainDes;
	}

	public boolean equals(Object o) {
		if( o instanceof KmExpertTrain ) {
			KmExpertTrain kmExpertTrain=(KmExpertTrain)o;
			if (this.id != null || this.id.equals(kmExpertTrain.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}