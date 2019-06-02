package com.boco.eoms.im.adaptor.pool;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.boco.eoms.im.adaptor.util.Imlocator;

public class XMPPConnectionPool {

	/**
	 * 单例
	 */
	private static XMPPConnectionPool instance;

	/**
	 * 连接池
	 */
	private static Map pool = new HashMap();

	/**
	 * 全局XMPP连接
	 */
	private static XMPPConnection connection = null;

	/**
	 * XMPP连接池构造方法
	 */
	public XMPPConnectionPool() {

	}

	/**
	 * 获取连接池实例（单例模式）
	 * 
	 * @return
	 */
	public synchronized static XMPPConnectionPool getInstance() {
		if (null == instance) {
			instance = new XMPPConnectionPool();
		}

		return instance;
	}

	/**
	 * 获得全局XMPP连接
	 * 
	 * @return
	 */
	public XMPPConnection getConnection() {

		if (connection == null) {
				try {
					String userId = Imlocator.ImConfigInstance().getWebMaster();
					String password = Imlocator.ImConfigInstance()
							.getPassword();
					String ip = Imlocator.ImConfigInstance().getIp();
					int port = Integer.parseInt(Imlocator.ImConfigInstance()
							.getPort());
					ConnectionConfiguration config = new ConnectionConfiguration(
							ip, port);
					connection = new XMPPConnection(config);
					connection.connect();
					connection.login(userId, password);
				} catch (XMPPException e) {
					e.printStackTrace();
				}
			}

		// TODO 返回连接前，需判断连接是否关闭，若关闭则开启
		return connection;
	}

	/**
	 * 获得某用户特有的XMPP连接
	 * 
	 * @param host
	 * @param port
	 * @param userId
	 * @param password
	 * @return
	 */
	public  XMPPConnection getConnection(String userId, String password) {
		// TODO 为userId初始化一个XMPP连接

		XMPPConnection connectionUser = null;
		if (pool.containsKey(userId)) {
			connectionUser = (XMPPConnection) pool.get(userId);
			if (!connectionUser.isConnected()) {
				try {
					connectionUser.connect();
					connectionUser.login(userId, password);
				} catch (XMPPException e) {
					e.printStackTrace();
				}
			}
		} else {
			String ip = "";
			int port;
			ip = Imlocator.ImConfigInstance().getIp();
			port = Integer.parseInt(Imlocator.ImConfigInstance().getPort());
			ConnectionConfiguration config = new ConnectionConfiguration(ip,
					port);
			connectionUser = new XMPPConnection(config);
			try {
				connectionUser.connect();
				connectionUser.login(userId, password);
				pool.put(userId, connectionUser);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}

		return connectionUser;
	}

	public static void disConnect(XMPPConnection connectionUser) {
		pool.remove(connectionUser.getUser());
		connectionUser.disconnect();
	}
}
