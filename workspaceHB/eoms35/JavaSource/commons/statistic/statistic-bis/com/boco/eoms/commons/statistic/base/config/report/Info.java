package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;

public class Info implements Serializable{
	
	private String name = null;
	
	private String value = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
