package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:技术交流竞赛表彰
 * </p>
 * <p>
 * Description:技术交流竞赛表彰
 * </p>
 * <p>
 * Mon Jun 15 18:07:24 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
public class KmExpertComForm extends BaseForm implements java.io.Serializable {

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
	 * 表彰时间
	 * 
	 */
	private String expertComDate;

	public void setExpertComDate(String expertComDate) {
		this.expertComDate = expertComDate;
	}

	public String getExpertComDate() {
		if (this.expertComDate != null) {
			if (expertComDate.length() == 10) {
				this.expertComDate = this.expertComDate + " 00:00:00";
			} else if (expertComDate.length() == 19) {
				this.expertComDate = this.expertComDate.substring(0, 10);
			}
		}
		return this.expertComDate;
	}

	/**
	 * 
	 * 简介
	 * 
	 */
	private String expertComDetail;

	public void setExpertComDetail(String expertComDetail) {
		this.expertComDetail = expertComDetail;
	}

	public String getExpertComDetail() {
		return this.expertComDetail;
	}

	/**
	 * 
	 * 表彰地址
	 * 
	 */
	private String expertComPath;

	public void setExpertComPath(String expertComPath) {
		this.expertComPath = expertComPath;
	}

	public String getExpertComPath() {
		return this.expertComPath;
	}

}