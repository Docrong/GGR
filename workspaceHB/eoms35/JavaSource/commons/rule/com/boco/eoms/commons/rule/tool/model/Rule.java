package com.boco.eoms.commons.rule.tool.model;

/**
 * <p>
 * Title:规则辅助类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 10:39:02 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class Rule {
	/**
	 * 主键,唯一标识
	 */
	private String id;

	/**
	 * xml所在路径
	 */
	private String xmlPath;

	/**
	 * 规则名称
	 */
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

}
