package com.boco.eoms.commons.accessories.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="User.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_commons_accessoriesconfig"
 */

public class TawCommonsAccessoriesConfig extends BaseObject {
	
	private String id;
	/**
	 * 应用模块ID
	 */
	private Integer appId;
	
	
	/**
	 * 应用模块名称
	 */
	private String appName;

	/**
	 * 应用模块编码
	 */
	private String appCode;

	/**
	 * 存放路径（相对路径）
	 */
	private String path;

	/**
	 * 允许存放的文件最大长度
	 */
	private Integer maxSize;

	/**
	 * 允许存放的文件类型
	 */
	private String allowFileType;


	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the appId.
	 */
	public Integer getAppId() {
		return appId;
	}
	/**
	 * @param appId The appId to set.
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	/**
	 * @hibernate.property length="50"
	 */
	public String getAllowFileType() {
		return allowFileType;
	}

	public void setAllowFileType(String allowFileType) {
		this.allowFileType = allowFileType;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
