package com.boco.eoms.wap.platform.contact.webapp.form;

import java.io.Serializable;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * wap通迅录form
 * 
 * @author leo
 * 
 */
public class ContactForm extends BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6817047558970772073L;
	/**
	 * 联系人名称
	 */
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
