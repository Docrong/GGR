package com.boco.eoms.im.adaptor.mgr.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.im.adaptor.listener.IMFileListener;
import com.boco.eoms.im.adaptor.listener.IMMsgListener;
import com.boco.eoms.im.adaptor.mgr.IMAdaptorMgr;
import com.boco.eoms.im.adaptor.pool.XMPPConnectionPool;
import com.boco.eoms.im.adaptor.util.Imlocator;
import com.boco.eoms.message.util.MsgHelp;

public class IMAdaptorMgrImpl implements IMAdaptorMgr, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8639015428721396254L;

	public void cancelMsg(String id) {
		// TODO Auto-generated method stub

	}

	public void sendFile(String filePath, String toOrgIds) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		XMPPConnection connectionUser = null;
		connectionUser = XMPPConnectionPool.getInstance().getConnection();
		OutgoingFileTransfer transfer = null;
		File file = new File(filePath);
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendFile(File file, String toOrgIds) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		connectionUser = XMPPConnectionPool.getInstance().getConnection();
		OutgoingFileTransfer transfer = null;
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendFile(String user, String passwd, String filePath,
			String toOrgIds) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		connectionUser = XMPPConnectionPool.getInstance().getConnection(user,
				passwd);
		OutgoingFileTransfer transfer = null;
		File file = new File(filePath);
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendFile(String user, String passwd, File file, String toOrgIds) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		connectionUser = XMPPConnectionPool.getInstance().getConnection(user,
				passwd);
		OutgoingFileTransfer transfer = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendFile(String filePath, String toOrgIds,
			IMFileListener imFileListener) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();
		XMPPConnection connectionUser = null;
		connectionUser = XMPPConnectionPool.getInstance().getConnection();
		OutgoingFileTransfer transfer = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		File file = new File(filePath);
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendFile(String user, String passwd, String filePath,
			String toOrgIds, IMFileListener imFileListener) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();

		connectionUser = XMPPConnectionPool.getInstance().getConnection(user,
				passwd);
		OutgoingFileTransfer transfer = null;
		File file = new File(filePath);
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendFile(File file, String toOrgIds,
			IMFileListener imFileListener) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();

		connectionUser = XMPPConnectionPool.getInstance().getConnection();
		OutgoingFileTransfer transfer = null;
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendFile(String user, String passwd, File file,
			String toOrgIds, IMFileListener imFileListener) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();

		connectionUser = XMPPConnectionPool.getInstance().getConnection(user,
				passwd);
		OutgoingFileTransfer transfer = null;
		FileTransferManager fileTransferManager = new FileTransferManager(
				connectionUser);
		for (int i = 0; i < len; i++) {
			String receiveId = ((TawSystemUser) userList.get(i)).getUserid();
			String temp = receiveId + "@" + hostname + "/" + resource;
			transfer = fileTransferManager.createOutgoingFileTransfer(temp);
			try {
				transfer.sendFile(file, "nice to meet you!!");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

	}

	public String sendMsg(String user, String passwd, String toOrgIds,
			String body) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		Chat newChat;
		ChatManager chatmanager;
		connectionUser = XMPPConnectionPool.getInstance().getConnection(user,
				passwd);
		chatmanager = connectionUser.getChatManager();

		for (int i = 0; i < len; i++) {
			try {
				String receiveId = ((TawSystemUser) userList.get(i))
						.getUserid();
				String temp = receiveId + "@" + hostname + "/" + resource;
				newChat = chatmanager.createChat(temp, new IMMsgListener());
				newChat.sendMessage(body);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
		return body;

	}

	public String sendMsg(String user, String passwd, String toOrgIds,
			String body, IMMsgListener imMsgListener) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		Chat newChat;
		ChatManager chatmanager;
		connectionUser = XMPPConnectionPool.getInstance().getConnection(user,
				passwd);
		chatmanager = connectionUser.getChatManager();

		for (int i = 0; i < len; i++) {
			try {
				String receiveId = ((TawSystemUser) userList.get(i))
						.getUserid();
				String temp = receiveId + "@" + hostname + "/" + resource;
				newChat = chatmanager.createChat(temp, new IMMsgListener());
				newChat.sendMessage(body);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
			// connectionUser.disconnect();
		}
		return body;

	}

	public String sendMsg(String toOrgIds, String body) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;
		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();

		Chat newChat;
		ChatManager chatmanager;
		connectionUser = XMPPConnectionPool.getInstance().getConnection();
		chatmanager = connectionUser.getChatManager();
		for (int i = 0; i < len; i++) {

			try {
				String receiveId = ((TawSystemUser) userList.get(i))
						.getUserid();
				String temp = receiveId + "@" + hostname + "/" + resource;
				newChat = chatmanager.createChat(temp, new IMMsgListener());
				newChat.sendMessage(body);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
		return body;

	}

	public String sendMsg(String toOrgIds, String body,
			IMMsgListener imMsgListener) {
		String resource = Imlocator.ImConfigInstance().getResource();
		String hostname = Imlocator.ImConfigInstance().getHostname();

		XMPPConnection connectionUser = null;

		List userList = MsgHelp.getUserList(toOrgIds);
		int len = userList.size();
		Chat newChat;
		ChatManager chatmanager;
		connectionUser = XMPPConnectionPool.getInstance().getConnection();
		chatmanager = connectionUser.getChatManager();

		for (int i = 0; i < len; i++) {
			try {
				String receiveId = ((TawSystemUser) userList.get(i))
						.getUserid();
				String temp = receiveId + "@" + hostname + "/" + resource;
				newChat = chatmanager.createChat(temp, new IMMsgListener());
				newChat.sendMessage(body);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
}
