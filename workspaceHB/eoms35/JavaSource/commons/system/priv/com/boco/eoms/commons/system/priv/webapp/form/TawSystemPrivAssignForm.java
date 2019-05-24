package com.boco.eoms.commons.system.priv.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="tawSystemPrivAssignForm"
 */
public class TawSystemPrivAssignForm extends BaseForm implements
		java.io.Serializable {

	protected String id;

	protected String assigntype;

	protected String objectid;

	protected String operuserid;

	protected String privid;

	protected String url;

	protected String menuid;

	protected String remark;

	/** Default empty constructor. */
	public TawSystemPrivAssignForm() {
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	public String getAssigntype() {
		return this.assigntype;
	}

	/**
	 */

	public void setAssigntype(String assigntype) {
		this.assigntype = assigntype;
	}

	public String getObjectid() {
		return this.objectid;
	}

	/**
	 */

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getOperuserid() {
		return this.operuserid;
	}

	/**
	 */

	public void setOperuserid(String operuserid) {
		this.operuserid = operuserid;
	}

	public String getPrivid() {
		return this.privid;
	}

	/**
	 */

	public void setPrivid(String privid) {
		this.privid = privid;
	}

	public String getRemark() {
		return this.remark;
	}

	/**
	 */

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-TawSystemPrivAssignForm.java containing the additional code and
	 * place it in your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}
