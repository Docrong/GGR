package com.boco.eoms.im.adaptor.mgr;

import java.io.File;
import java.io.Serializable;

import com.boco.eoms.im.adaptor.listener.IMFileListener;
import com.boco.eoms.im.adaptor.listener.IMMsgListener;

public interface IMAdaptorMgr  {
	
		/**
		 * 指定用户发送消息(无消息内容修饰消息构建方法)
		 * 
		 * @param user
		 *            用户id
		 * @param passwd
		 *            密码
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param body
		 *            消息内容
		 * @return 消息唯一标识，供取消发送消息
		 */
		public String sendMsg(String user, String passwd, String toOrgIds,
				String body);

		/**
		 * 指定用户发送消息
		 * 
		 * @param user
		 *            用户id
		 * @param passwd
		 *            密码
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param body
		 *            消息内容
		 * @param imMsgListener
		 *            消息监听，提供在消息发送中插入代码
		 * @return 消息唯一标识，供取消发送消息
		 */
		public String sendMsg(String user, String passwd, String toOrgIds,
				String body, IMMsgListener imMsgListener);

		/**
		 * 系统用户发送消息(无消息内容修饰消息构建方法)
		 * 
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param body
		 *            消息内容
		 * @return 消息唯一标识，供取消发送消息
		 */
		public String sendMsg(String toOrgIds, String body);

		/**
		 * 系统用户发送消息（带监听）
		 * 
		 * @param user
		 *            用户id
		 * @param passwd
		 *            密码
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param body
		 *            消息内容
		 * @param imMsgListener
		 *            消息监听，提供在消息发送中插入代码
		 * @return 消息唯一标识，供取消发送消息
		 */
		public String sendMsg(String toOrgIds, String body,
				IMMsgListener imMsgListener);

		/**
		 * 取消发送某条消息
		 * 
		 * @param id
		 *            消息id唯一标识
		 * @throws CancelMsgErrorException
		 *             取消发送消息失败
		 * @return 删除成功否
		 */
		public void cancelMsg(String id);

		/**
		 * 使用系统用户发送文件
		 * 
		 * @param filePath
		 *            文件路径，提供所在服务器的文件路径
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(String filePath, String toOrgIds);

		/**
		 * 使用系统帐户发送文件
		 * 
		 * @param file
		 *            文件对象
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(File file, String toOrgIds);

		/**
		 * 指定系统用户user发送文件
		 * 
		 * @param user
		 *            用户名
		 * @param passwd
		 *            密码
		 * @param filePath
		 *            文件路径
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(String user, String passwd, String filePath,
				String toOrgIds) ;

		/**
		 * 指定系统用户user发送文件
		 * 
		 * @param user
		 *            用户名
		 * @param passwd
		 *            密码
		 * @param file
		 *            文件对象
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(String user, String passwd, File file, String toOrgIds);

		/**
		 * 系统用户发送文件，允许插入代码在发送文件中
		 * 
		 * @param filePath
		 *            文件路径
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param imFileListener
		 *            文件监听，插入代码
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(String filePath, String toOrgIds,
				IMFileListener imFileListener);

		/**
		 * 指定用户user发送文件，允许插入代码在发送文件中
		 * 
		 * @param user
		 *            用户
		 * @param passwd
		 *            密码
		 * @param filePath
		 *            文件路径
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param imFileListener
		 *            文件监听，插入代码
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(String user, String passwd, String filePath,
				String toOrgIds, IMFileListener imFileListener);

		/**
		 * 发送文件，允许插入代码在发送文件中
		 * 
		 * @param file
		 *            文件对象
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param imFileListener
		 *            文件监听，插入代码
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(File file, String toOrgIds,
				IMFileListener imFileListener);

		/**
		 * 指定用户user发送文件，允许插入代码在发送文件中
		 * 
		 * @param user
		 *            用户
		 * @param passwd
		 *            密码
		 * @param file
		 *            文件对象
		 * @param toOrgIds
		 *            发送的组织ID,如userId#1,deptId#2,roleId#3 （其中1代表用户 2代表部门 3代表角色）
		 * @param imFileListener
		 *            文件监听，插入代码
		 * @throws IMAdaptorSendFileErrorException
		 *             发送错误则抛出异常
		 */
		public void sendFile(String user, String passwd, File file,
				String toOrgIds, IMFileListener imFileListener);

	}

