package com.boco.eoms.message.mgr;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.IMMonitor;

public interface IIMMonitorManager extends Manager {

	/**
	 * 用户发消息
	 * 
	 * @param userId
	 *            用户ID
	 * @param content
	 *            发送内容
	 * @param toOrgIds
	 *            接收者ID
	 */
	public void userSendIMMsg(String userId, String content, String toOrgIds);

	/**
	 * 用户发文件
	 * 
	 * @param userId
	 *            用户ID
	 * @param filePath
	 *            文件路径
	 * @param toOrgIds
	 *            接受者ID
	 * @return
	 */
	public void userSendIMFile(String userId, String filePath, String toOrgIds);

	/**
	 * 系统发消息
	 * 
	 * @param userId
	 *            用户ID
	 * @param content
	 *            发送内容
	 * @param toOrgIds
	 *            接收者ID
	 * @return
	 */
	public void systemSendIMMsg(String content, String toOrgIds);

	/**
	 * 系统发文件
	 * 
	 * @param userId
	 *            用户ID
	 * @param filePath
	 *            文件路径
	 * @param toOrgIds
	 *            接受者ID
	 * @return
	 */
	public void systemSendIMFile(String filePath, String toOrgIds);

	/**
	 * 轮训发送IM消息/文件
	 * 
	 * @return
	 */
	public String sendScheduler();

	public List getIMMonitors(IMMonitor iMMonitor);

	/**
	 * 获得指定的IMMonitor
	 * 
	 * @param id
	 * @return
	 */
	public IMMonitor getIMMonitor(final String id);

	/**
	 * 保存
	 * 
	 * @param iMMonitor
	 */
	public void saveIMMonitor(IMMonitor iMMonitor);

	/**
	 * 移除
	 * 
	 * @param id
	 */
	public void removeIMMonitor(final String id);

	/**
	 * 得到需要发送的IMMonitor集合
	 * 
	 * @return
	 */
	public List listNeedSendIM();

	/**
	 * 轮询方式发送IM消息/文件
	 * 
	 * @param 服务id
	 * @param buizid
	 *            业务ID
	 * @param content
	 *            发送内容
	 * @param filePath
	 *            发送文件路径
	 * @param toOrgIds
	 *            发送人员或者部门的串 格式：1,admin#1,sunshengtai#2,254 （其中1代表人员 2代表部门
	 *            254代表部门id）
	 * @return
	 */
	public String sendIMScheduler(String serviceId, String buizid,
			String content, String filePath, String dispatchTime,
			String toOrgIds);

}
