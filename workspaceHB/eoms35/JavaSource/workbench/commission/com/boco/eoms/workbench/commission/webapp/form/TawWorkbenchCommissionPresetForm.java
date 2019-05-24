package com.boco.eoms.workbench.commission.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawWorkbenchCommissionPresetForm extends BaseForm implements
		java.io.Serializable {

	protected String id;

	protected String deleted;

	protected String userId;

	protected String userName;

	protected String moduleId;

	protected String moduleName;

	protected String trustorId;

	protected String trustorName;

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTrustorId() {
		return trustorId;
	}

	public void setTrustorId(String trustorId) {
		this.trustorId = trustorId;
	}

	public String getTrustorName() {
		return trustorName;
	}

	public void setTrustorName(String trustorName) {
		this.trustorName = trustorName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
