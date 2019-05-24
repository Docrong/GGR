package com.boco.eoms.commons.system.user.sox.mgr;

import com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory;

/**
 * <p>
 * Title:用户密码修改历史业务类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 20, 2008 8:05:06 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface IUserPasswdHistoryMgr {

	/**
	 * 新增用户码修改历史记录
	 * 
	 * @param userPasswdHistory
	 *            用户码修改历史记录
	 */
	public void addUserPasswdHistory(UserPasswdHistory userPasswdHistory);

	/**
	 * 要修改的是否在前number次密码中
	 * 
	 * @param userId
	 *            用户表中无业务的主键
	 * @param passwd
	 *            密码
	 * @param number
	 *            前几次密码中
	 * @return 是否在前number次密码中重复
	 */
	public boolean isRepeatPasswd(String userId, String passwd, Integer number);

}
