package com.boco.eoms.remind.service;

import java.util.List;

/**
 * <p>
 * Title:使用提醒服务需实现的接口
 * </p>
 * <p>
 * Description:使用提醒服务需实现的接口
 * </p>
 * <p>
 * Date:2008-9-27 下午03:25:53
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface RemindService {

	/**
	 * 获得某用户的提醒信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return List RemindInfo对象的列表
	 */
	public List getRemindInfoList(String userId);
}
