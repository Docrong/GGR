package com.boco.eoms.commons.statistic.customstat.config;

import java.io.Serializable;

public class Report implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3760465648421347622L;

	private String name = null;
	
	private String beanid = null;

	public String getBeanid() {
		return beanid;
	}

	public void setBeanid(String beanid) {
		this.beanid = beanid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
