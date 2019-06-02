package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.km.expert.util.KmExpertEduConstants;

/**
 * <p>
 * Title:教育背景
 * </p>
 * <p>
 * Description:专家教育背景
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertEdu extends BaseObject {

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
	 * 毕业院校
	 *
	 */
	private String expertEduSch;

	public void setExpertEduSch(String expertEduSch) {
		this.expertEduSch = expertEduSch;
	}

	public String getExpertEduSch() {
		return this.expertEduSch;
	}

	/**
	 *
	 * 学历
	 *
	 */
	private String expertEduEdu;

	public void setExpertEduEdu(String expertEduEdu) {
		this.expertEduEdu = expertEduEdu;
	}

	public String getExpertEduEdu() {
		return this.expertEduEdu;
	}

	public String getExpertEduEduName() {
		return KmExpertEduConstants.KMEXPERTEDU_EDU[Integer
				.parseInt(this.expertEduEdu)];
	}

	/**
	 *
	 * 开始时间
	 *
	 */
	private java.util.Date expertEduStart;

	public void setExpertEduStart(java.util.Date expertEduStart) {
		this.expertEduStart = expertEduStart;
	}

	public java.util.Date getExpertEduStart() {
		return this.expertEduStart;
	}

	/**
	 *
	 * 结束时间
	 *
	 */
	private java.util.Date expertEduEnd;

	public void setExpertEduEnd(java.util.Date expertEduEnd) {
		this.expertEduEnd = expertEduEnd;
	}

	public java.util.Date getExpertEduEnd() {
		return this.expertEduEnd;
	}

	/**
	 *
	 * 专业类别
	 *
	 */
	private String expertEduPro;

	public void setExpertEduPro(String expertEduPro) {
		this.expertEduPro = expertEduPro;
	}

	public String getExpertEduPro() {
		return this.expertEduPro;
	}

	/**
	 *
	 * 专业名称
	 *
	 */
	private String expertEduProName;

	public void setExpertEduProName(String expertEduProName) {
		this.expertEduProName = expertEduProName;
	}

	public String getExpertEduProName() {
		return this.expertEduProName;
	}

	/**
	 *
	 * 城市
	 *
	 */
	private String expertEduCity;

	public void setExpertEduCity(String expertEduCity) {
		this.expertEduCity = expertEduCity;
	}

	public String getExpertEduCity() {
		return this.expertEduCity;
	}

	/**
	 *
	 * 专业说明
	 *
	 */
	private String expertEduDes;

	public void setExpertEduDes(String expertEduDes) {
		this.expertEduDes = expertEduDes;
	}

	public String getExpertEduDes() {
		return this.expertEduDes;
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

	public boolean equals(Object o) {
		if (o instanceof KmExpertEdu) {
			KmExpertEdu kmExpertEdu = (KmExpertEdu) o;
			if (this.id != null || this.id.equals(kmExpertEdu.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}