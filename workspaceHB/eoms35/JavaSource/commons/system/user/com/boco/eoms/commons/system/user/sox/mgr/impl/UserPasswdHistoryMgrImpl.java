package com.boco.eoms.commons.system.user.sox.mgr.impl;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import com.boco.eoms.commons.system.user.sox.dao.IUserPasswdHistoryDao;
import com.boco.eoms.commons.system.user.sox.mgr.IUserPasswdHistoryMgr;
import com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 20, 2008 8:05:40 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserPasswdHistoryMgrImpl implements IUserPasswdHistoryMgr {

	/**
	 * 用户密码修改历史记录dao
	 */
	private IUserPasswdHistoryDao userPasswdHistoryDao;

	/**
	 * @param userPasswdHistoryDao
	 *            the userPasswdHistoryDao to set
	 */
	public void setUserPasswdHistoryDao(
			IUserPasswdHistoryDao userPasswdHistoryDao) {
		this.userPasswdHistoryDao = userPasswdHistoryDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.sox.mgr.IUserPasswdHistoryMgr#addUserPasswdHistory(com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory)
	 */
	public void addUserPasswdHistory(UserPasswdHistory userPasswdHistory) {
		this.userPasswdHistoryDao.addUserPasswdHistory(userPasswdHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.sox.mgr.IUserPasswdHistoryMgr#isRepeatPasswd(java.lang.String,java.lang.String,
	 *      java.lang.Integer)
	 */
	public boolean isRepeatPasswd(String userId, String passwd, Integer number) {
		List list = this.userPasswdHistoryDao.listUserPasswdHistory(userId,
				number);
		if (list != null) {
			// 判断是否有密码重复
			for (Iterator it = list.iterator(); it.hasNext();) {
				UserPasswdHistory userPasswdHistory = (UserPasswdHistory) it
						.next();

				if (userPasswdHistory.getPasswd().equals(
						new Md5PasswordEncoder().encodePassword(passwd,
								new String()))) {
					return true;
				}
			}
		}
		return false;
	}
}
