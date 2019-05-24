package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;

public class ResultSort implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8360973210186556415L;

	private String id = null;
	
	private String type = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
