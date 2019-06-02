package com.boco.eoms.wap.model;

/**
 * Detail页面隐藏参数或者判断参数的公用model
 * 
 * @author xugengxian
 * @date Feb 17, 2009 9:31:58 AM
 */
public class Parameter {

	/**
	 * 字段标题
	 */
	private String id;

	/**
	 * 字段值
	 */
	private String value;

	/**
	 * @return 字段标题
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 字段标题
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 字段值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value 字段值
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
