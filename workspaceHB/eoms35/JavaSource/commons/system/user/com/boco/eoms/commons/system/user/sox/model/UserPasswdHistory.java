package com.boco.eoms.commons.system.user.sox.model;

import java.io.Serializable;
import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:SOX要求，记入用户修改密码记录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 20, 2008 4:21:13 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserPasswdHistory extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7357452739088765236L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 用户表主键，与用户表为多对一关联
	 */
	private String userId;

	/**
	 * 密码
	 */
	private String passwd;

	/**
	 * 修改密码的时间
	 */
	private Date updateTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserPasswdHistory(String userId, String passwd, Date updateTime) {
		super();
		this.userId = userId;
		this.passwd = passwd;
		this.updateTime = updateTime;
	}

	public UserPasswdHistory() {
		super();
	}

	public boolean equals(Object o) {
		if (o instanceof UserPasswdHistory) {
			UserPasswdHistory userPasswdHistory = (UserPasswdHistory) o;
			if (this.id != null || this.id.equals(userPasswdHistory.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
