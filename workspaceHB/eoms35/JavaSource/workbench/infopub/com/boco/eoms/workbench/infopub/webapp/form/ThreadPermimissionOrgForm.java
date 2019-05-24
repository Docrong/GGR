package com.boco.eoms.workbench.infopub.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * 
 * <p>
 * Title:信息（贴子）记录组织结构权限form
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:27:15 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadPermimissionOrgForm extends BaseForm implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3365016435514691979L;

	protected String id;

	protected String orgId;

	protected String orgType;
	
	//2009-04-07 是否包括子部门  1包括，0或null不包括
	private String isIncludeSubDept;

	public String getIsIncludeSubDept() {
		return isIncludeSubDept;
	}

	public void setIsIncludeSubDept(String isIncludeSubDept) {
		this.isIncludeSubDept = isIncludeSubDept;
	}

	/** Default empty constructor. */
	public ThreadPermimissionOrgForm() {
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return this.orgId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType
	 *            the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-ThreadPermimissionOrgForm.java containing the additional code and
	 * place it in your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}
