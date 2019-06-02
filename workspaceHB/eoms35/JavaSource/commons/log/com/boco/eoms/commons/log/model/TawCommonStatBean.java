package com.boco.eoms.commons.log.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

public class TawCommonStatBean extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
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

	public String username;

	public String deptname;

	public String beginnotetime;

	public String remoteip;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getRemoteip() {
		return remoteip;
	}

	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}
	public String getBeginnotetime() {
		return beginnotetime;
	}

	public void setBeginnotetime(String beginnotetime) {
		this.beginnotetime = beginnotetime;
	}
	

}
