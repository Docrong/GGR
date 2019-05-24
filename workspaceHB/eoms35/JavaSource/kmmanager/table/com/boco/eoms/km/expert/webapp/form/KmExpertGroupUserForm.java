package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:专家团队与专家对应关系
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertGroupUserForm extends BaseForm implements java.io.Serializable {

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
	 * 团队ID
	 */
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 人员ID
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 是否是组长
	 */
	private String isLeader;

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public boolean equals(Object o) {
		if (o instanceof KmExpertGroupUserForm) {
			KmExpertGroupUserForm kmExpertBasic = (KmExpertGroupUserForm) o;
			if (this.id != null || this.id.equals(kmExpertBasic.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}