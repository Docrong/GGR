package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;

public class Field implements Serializable{
	
	private String id = null;
	
	private String id2name = null;
	
	private String value = null;
	
	private String url = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId2name() {
		return id2name;
	}

	public void setId2name(String id2name) {
		this.id2name = id2name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
