package com.boco.eoms.eva.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * 
 * <p>
 * Title:考核任务
 * </p>
 * <p>
 * Description:记录模板和组织的关系
 * </p>
 * <p>
 * Date:2009-1-6 下午05:02:45
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 *
 */
public class EvaTask extends BaseObject {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 模板id
	 */
	private String templateId;

	/**
	 * 组织id
	 */
	private String orgId;

	/**
	 * 组织类型
	 */
	private String orgType;

	/**
	 * 任务生成时间
	 */
	private String createTime;

	/**
	 * 任务生成操作人
	 */
	private String creator;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public boolean equals(Object o) {
		return false;
	}

	public String toString() {
		return null;
	}

	public int hashCode() {
		return 0;
	}
}
