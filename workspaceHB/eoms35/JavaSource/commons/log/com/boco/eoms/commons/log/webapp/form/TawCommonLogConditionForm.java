package com.boco.eoms.commons.log.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawCommonLogConditionForm extends BaseForm implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String searchbyuser;

	private String searchbymodel;

	private String searchbyoper;

	private String searchbystarttime;

	private String searchbyendtime;

	private String issucess;

	public String getIssucess() {
		return issucess;
	}

	public void setIssucess(String issucess) {
		this.issucess = issucess;
	}

	public String getSearchbyendtime() {
		return searchbyendtime;
	}

	public void setSearchbyendtime(String searchbyendtime) {
		this.searchbyendtime = searchbyendtime;
	}

	public String getSearchbymodel() {
		return searchbymodel;
	}

	public void setSearchbymodel(String searchbymodel) {
		this.searchbymodel = searchbymodel;
	}

	public String getSearchbyoper() {
		return searchbyoper;
	}

	public void setSearchbyoper(String searchbyoper) {
		this.searchbyoper = searchbyoper;
	}

	public String getSearchbystarttime() {
		return searchbystarttime;
	}

	public void setSearchbystarttime(String searchbystarttime) {
		this.searchbystarttime = searchbystarttime;
	}

	public String getSearchbyuser() {
		return searchbyuser;
	}

	public void setSearchbyuser(String searchbyuser) {
		this.searchbyuser = searchbyuser;
	}
}
