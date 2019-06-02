package com.boco.eoms.sheet.proxy.model;
import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;
public class Proxy extends BaseObject implements Serializable {
	
	private Integer id;
	private String proxyUser;
	private String beginTime;
	private String endTime;

	private String userId;
	
	
	
	
	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="代理开始时间"
	 * @return
	 */
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="代理结束时间"
	 * @return
	 */
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="代理人"
	 * @return
	 */
	public String getProxyUser() {
		return proxyUser;
	}
	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
