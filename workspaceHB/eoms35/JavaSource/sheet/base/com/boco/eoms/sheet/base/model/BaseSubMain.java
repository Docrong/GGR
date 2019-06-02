package com.boco.eoms.sheet.base.model;

import java.io.Serializable;

/**
 * @author: panlong
 * 
 * @Date: 2008-8-18
 */
public class BaseSubMain implements Serializable {


	//子表的主键 主表的外键
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
