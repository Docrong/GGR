package com.boco.eoms.commons.accessories.model;

import com.boco.eoms.base.model.BaseObject;

import java.util.Date;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_commons_accessories"
 */
public class TawCommonsAccessories extends BaseObject {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 文件名称（时间戳）
	 */
	private String accessoriesName;
	/**
	 * 文件实际名称
	 */
	private String accessoriesCnName;
	/**
	 * 文件英文名称
	 */
	private String accessoriesEnName;
	/**
	 * 存放路径
	 */
	private String accessoriesPath;
	/**
	 * 文件大小
	 */
	private long accessoriesSize;
	/**
	 * 上传者
	 */
	private String accessoriesUploader; 
	/**
	 * 上传时间
	 */
	private Date accessoriesUploadTime;
	/**
	 * 所属应用模块
	 */
	private String appCode;

	/**
	 * @hibernate.property length="1000"
	 */
	public String getAccessoriesCnName() {
		return accessoriesCnName;
	}

	public void setAccessoriesCnName(String accessoriesCnName) {
		this.accessoriesCnName = accessoriesCnName;
	}

	/**
	 * @hibernate.property length="1000"
	 */
	public String getAccessoriesName() {
		return accessoriesName;
	}

	public void setAccessoriesName(String accessoriesName) {
		this.accessoriesName = accessoriesName;
	}

	/**
	 * @hibernate.property length="1000"
	 */
	public String getAccessoriesPath() {
		return accessoriesPath;
	}

	public void setAccessoriesPath(String accessoriesPath) {
		this.accessoriesPath = accessoriesPath;
	}

	/**
	 * @hibernate.property length="1000"
	 */
	public long getAccessoriesSize() {
		return accessoriesSize;
	}

	public void setAccessoriesSize(long accessoriesSize) {
		this.accessoriesSize = accessoriesSize;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public String getAccessoriesUploader() {
		return accessoriesUploader;
	}

	public void setAccessoriesUploader(String accessoriesUploader) {
		this.accessoriesUploader = accessoriesUploader;
	}

	/**
	 * @hibernate.property length="50"
	 */
	public Date getAccessoriesUploadTime() {
		return accessoriesUploadTime;
	}

	public void setAccessoriesUploadTime(Date accessoriesUploadTime) {
		this.accessoriesUploadTime = accessoriesUploadTime;
	}

	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean equals(Object o) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String toString() {
		return null;
	}

	public String getAccessoriesEnName() {
		return accessoriesEnName;
	}

	public void setAccessoriesEnName(String accessoriesEnName) {
		this.accessoriesEnName = accessoriesEnName;
	}

}
