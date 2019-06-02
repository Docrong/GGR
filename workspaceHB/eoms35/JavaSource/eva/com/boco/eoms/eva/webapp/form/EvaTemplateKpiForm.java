package com.boco.eoms.eva.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class EvaTemplateKpiForm extends BaseForm implements java.io.Serializable {

	/**
	 * 主键
	 */
	protected String id;
	
	/**
	 * 模板Id
	 */
	protected String templateId;
	
	/**
	 * 指标Id
	 */
	protected String kpiId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}
