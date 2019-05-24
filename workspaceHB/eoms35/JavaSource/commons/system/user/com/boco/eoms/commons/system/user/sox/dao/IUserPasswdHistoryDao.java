package com.boco.eoms.commons.system.user.sox.dao;

import java.util.List;

import com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory;

/**
 * <p>
 * Title:用户修改密码历史记录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 20, 2008 5:56:01 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface IUserPasswdHistoryDao {

	/**
	 * 添加用户密码修改历史
	 * 
	 * @param userPasswdHistory
	 *            用户密码修改历史
	 */
	public void addUserPasswdHistory(UserPasswdHistory userPasswdHistory);

	/**
	 * 分页取用户密码修改记录列表
	 * 
	 * @param number
	 *            取前几条用户密码修改记录
	 * @param userId
	 *            用户表中的主键
	 * 
	 * @return 前length条记录
	 */
	public List listUserPasswdHistory(final String userId, final Integer number);

}
