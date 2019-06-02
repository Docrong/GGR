package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
public class KmExpertTrainForm extends BaseForm implements java.io.Serializable {

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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	/**
	 * 
	 * 培训时间
	 * 
	 */
	private String expertTrainDate;

	public void setExpertTrainDate(String expertTrainDate) {
		this.expertTrainDate = expertTrainDate;
	}

	public String getExpertTrainDate() {
		if (this.expertTrainDate != null) {
			if (expertTrainDate.length() == 10) {
				this.expertTrainDate = this.expertTrainDate + " 00:00:00";
			} else if (expertTrainDate.length() == 19) {
				this.expertTrainDate = this.expertTrainDate.substring(0, 10);
			}
		}
		return this.expertTrainDate;
	}

	/**
	 * 
	 * 培训时长
	 * 
	 */
	private String expertTrainTime;

	public void setExpertTrainTime(String expertTrainTime) {
		this.expertTrainTime = expertTrainTime;
	}

	public String getExpertTrainTime() {
		return this.expertTrainTime;
	}

	/**
	 * 
	 * 培训课程
	 * 
	 */
	private String expertTrainLesson;

	public void setExpertTrainLesson(String expertTrainLesson) {
		this.expertTrainLesson = expertTrainLesson;
	}

	public String getExpertTrainLesson() {
		return this.expertTrainLesson;
	}

	/**
	 * 
	 * 城市
	 * 
	 */
	private String expertCity;

	public void setExpertCity(String expertCity) {
		this.expertCity = expertCity;
	}

	public String getExpertCity() {
		return this.expertCity;
	}

	/**
	 * 
	 * 培训机构
	 * 
	 */
	private String expertUnit;

	public void setExpertUnit(String expertUnit) {
		this.expertUnit = expertUnit;
	}

	public String getExpertUnit() {
		return this.expertUnit;
	}

	/**
	 * 
	 * 获得证书
	 * 
	 */
	private String expertLicense;

	public void setExpertLicense(String expertLicense) {
		this.expertLicense = expertLicense;
	}

	public String getExpertLicense() {
		return this.expertLicense;
	}

	/**
	 * 
	 * 培训描述
	 * 
	 */
	private String expertTrainDes;

	public void setExpertTrainDes(String expertTrainDes) {
		this.expertTrainDes = expertTrainDes;
	}

	public String getExpertTrainDes() {
		return this.expertTrainDes;
	}

}