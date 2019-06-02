package com.boco.eoms.version;

import java.io.Serializable;

/**
 * 版本摘要信息
 * 
 * @author leo
 * 
 */
public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5915478878308852250L;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 版本描述
	 */
	private String desc;

	/**
	 * 版本发布日期
	 */
	private String onDate;

	/**
	 * 版本名称
	 */
	private String name;

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the onDate
	 */
	public String getOnDate() {
		return onDate;
	}

	/**
	 * @param onDate
	 *            the onDate to set
	 */
	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}

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
