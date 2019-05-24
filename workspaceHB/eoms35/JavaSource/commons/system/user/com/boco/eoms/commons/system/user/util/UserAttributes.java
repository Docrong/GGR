package com.boco.eoms.commons.system.user.util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 20, 2008 8:48:01 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserAttributes {

	/**
	 * 用户密码在近几次内不得重复,输入密码的次数，sox要求六次输入密码错误则锁定用户
	 */
	private Integer passwdRepeatNum;

	/**
	 * 密码位数
	 */
	private Integer passwdLength;

	/**
	 * 密码生效时间
	 */
	private Integer passwdAvailableDay;
	
	/**
	 * 密码将无效提醒
	 */
	private Integer passwdUnavailableRemindDay;
	
	/**
	 * 密码将无效提醒语
	 */
	private String passwdRemindWords;

	/**
	 * @return the passwdRemindWords
	 */
	public String getPasswdRemindWords() {
		return passwdRemindWords;
	}

	/**
	 * @param passwdRemindWords the passwdRemindWords to set
	 */
	public void setPasswdRemindWords(String passwdRemindWords) {
		this.passwdRemindWords = passwdRemindWords;
	}

	/**
	 * @return the passwdUnavailableRemindDay
	 */
	public Integer getPasswdUnavailableRemindDay() {
		return passwdUnavailableRemindDay;
	}

	/**
	 * @param passwdUnavailableRemindDay the passwdUnavailableRemindDay to set
	 */
	public void setPasswdUnavailableRemindDay(Integer passwdUnavailableRemindDay) {
		this.passwdUnavailableRemindDay = passwdUnavailableRemindDay;
	}

	/**
	 * @return the passwdLength
	 */
	public Integer getPasswdLength() {
		return passwdLength;
	}

	/**
	 * @param passwdLength
	 *            the passwdLength to set
	 */
	public void setPasswdLength(Integer passwdLength) {
		this.passwdLength = passwdLength;
	}

	/**
	 * @return the passwdRepeatNum
	 */
	public Integer getPasswdRepeatNum() {
		return passwdRepeatNum;
	}

	/**
	 * @param passwdRepeatNum
	 *            the passwdRepeatNum to set
	 */
	public void setPasswdRepeatNum(Integer passwdRepeatNum) {
		this.passwdRepeatNum = passwdRepeatNum;
	}

	/**
	 * @return the passwdAvailableDay
	 */
	public Integer getPasswdAvailableDay() {
		return passwdAvailableDay;
	}

	/**
	 * @param passwdAvailableDay
	 *            the passwdAvailableDay to set
	 */
	public void setPasswdAvailableDay(Integer passwdAvailableDay) {
		this.passwdAvailableDay = passwdAvailableDay;
	}

}
