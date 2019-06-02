package com.boco.eoms.workbench.commission.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:代理预设定
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-7-24 下午03:33:36
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class TawWorkbenchCommissionPreset extends BaseObject{

	private String id;

	private String deleted;

	private String userId;

	private String userName;

	private String moduleId;

	private String moduleName;

	private String trustorId;

	private String trustorName;

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTrustorId() {
		return trustorId;
	}

	public void setTrustorId(String trustorId) {
		this.trustorId = trustorId;
	}

	public String getTrustorName() {
		return trustorName;
	}

	public void setTrustorName(String trustorName) {
		this.trustorName = trustorName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
