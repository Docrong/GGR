package com.boco.eoms.eva.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class EvaTaskForm extends BaseForm implements java.io.Serializable {

	/**
	 * 主键
	 */
	protected String id;

	/**
	 * 模板id
	 */
	protected String templateId;

	/**
	 * 组织id
	 */
	protected String orgId;

	/**
	 * 组织类型
	 */
	protected String orgType;

	/**
	 * 任务生成时间
	 */
	protected String createTime;

	/**
	 * 任务生成操作人
	 */
	protected String creator;

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
}
