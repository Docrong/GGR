package com.boco.eoms.commons.rule.config.model;

/**
 * <p>
 * Title:RuleMapping.xml中的</parameter>标签mapping
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 2:55:42 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class Parameter {
	/**
	 * 参数名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述
	 */
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 输出表达式
	 */
	private String expression;

	/**
	 * drools的drl文件
	 */
	private String drl;

	/**
	 * 表达式格式ID
	 */
	private String expStyleId;

	public String getExpStyleId() {
		return expStyleId;
	}

	public void setExpStyleId(String expStyleId) {
		this.expStyleId = expStyleId;
	}

	public String getDrl() {
		return drl;
	}

	public void setDrl(String drl) {
		this.drl = drl;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
