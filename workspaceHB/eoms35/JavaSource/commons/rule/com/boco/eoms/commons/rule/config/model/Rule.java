package com.boco.eoms.commons.rule.config.model;

/**
 * <p>
 * Title:规则</rule>标签
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 2:55:00 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class Rule {
	/**
	 * 输入参数
	 */
	private Input input;

	/**
	 * 输出参数
	 */
	private Output output;

	/**
	 * 监听器配置
	 */
	private Listeners listeners;

	/**
	 * 规则标识
	 */
	private String id;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 规则类名
	 */
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Listeners getListeners() {
		return listeners;
	}

	public void setListeners(Listeners listeners) {
		this.listeners = listeners;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
