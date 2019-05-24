package com.boco.eoms.wap.model;

/**
 * Detail页面显示字段model
 * 
 * @author xugengxian
 * @date Feb 17, 2009 9:31:58 AM
 */
public class Attribute {

	/**
	 * 显示字段标题
	 */
	private String title;

	/**
	 * 显示字段值
	 */
	private String name;

	/**
	 * @return 字段标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title 字段标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 字段值
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 字段值
	 */
	public void setName(String name) {
		this.name = name;
	}

}
