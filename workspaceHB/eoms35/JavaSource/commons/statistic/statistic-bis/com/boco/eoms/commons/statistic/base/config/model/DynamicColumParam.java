package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;

public class DynamicColumParam implements Serializable{

	private String  pageName;
	
	private String cls; 

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
