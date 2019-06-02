package com.boco.eoms.km.expert.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertCom extends BaseObject {

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
	private java.util.Date expertComDate;

	public void setExpertComDate(java.util.Date expertComDate) {
		this.expertComDate = expertComDate;
	}

	public java.util.Date getExpertComDate() {
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

	public boolean equals(Object o) {
		if (o instanceof KmExpertCom) {
			KmExpertCom kmExpertCom = (KmExpertCom) o;
			if (this.id != null || this.id.equals(kmExpertCom.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}