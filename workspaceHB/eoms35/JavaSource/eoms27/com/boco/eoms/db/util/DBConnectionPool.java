package com.boco.eoms.db.util;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Timer;
import java.util.Vector;

import com.boco.eoms.common.log.BocoLog;

public class DBConnectionPool {
	private Vector connections = new Vector();

	private Vector closeConnections = new Vector();

	private int maxconn;

	private int minconn;

	private int maxusecount;

	private int maxidletime;

	private int maxalivetime;

	private String poolname;

	private String password;

	private String URL;

	private String user;

	private String charset;

	private ConnCheckerTimer connChk; // l锟接讹拷时锟斤拷锟斤拷锟?

	// private LogMan log = LogMan.getInstance("conn") ;
	private boolean checkdbable = true;

	private Timer timer = new Timer();

	private long mtime = 30000;

	/**
	 * 锟斤拷锟斤拷锟铰碉拷l锟接筹拷
	 * 
	 * @param poolname
	 *            l锟接筹拷锟斤拷锟斤拷
	 * @param URL
	 *            锟斤拷菘锟斤拷JDBC URL
	 * @param user
	 *            锟斤拷菘锟斤拷屎锟?锟斤拷 null
	 * @param password
	 *            锟斤拷锟斤拷,锟斤拷 null
	 * @param charset
	 *            l锟接碉拷锟街凤拷
	 * @param maxconn
	 *            锟斤拷l锟接筹拷锟斤拷锟?b锟斤拷锟斤拷锟絣锟斤拷锟斤拷
	 * @param minconn
	 *            锟斤拷l锟接筹拷锟斤拷锟?b锟斤拷锟斤拷小l锟斤拷锟斤拷
	 * @param maxusecount
	 *            锟斤拷l锟接筹拷锟斤拷锟?b锟斤拷锟斤拷锟絣锟斤拷锟斤拷
	 * @param maxidletime
	 *            l锟接筹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷锟?锟斤拷)
	 * @param maxalivetime
	 *            l锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷锟?
	 */

	public DBConnectionPool(String poolname, String URL, String user,
			String password, String charset, int maxconn, int minconn,
			int maxusecount, int maxidletime, int maxalivetime) {
		this.poolname = poolname;
		this.URL = URL;
		this.user = user;
		this.password = password;
		this.charset = charset;
		this.maxconn = maxconn;
		this.minconn = minconn;
		this.maxusecount = maxusecount;
		this.maxidletime = maxidletime;
		this.maxalivetime = maxalivetime;

		for (int i = 0; i < minconn; i++) {
			newConnection();
		}
		connChk = new ConnCheckerTimer(this, mtime);
		connChk.start();
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷使锟矫碉拷l锟接凤拷锟截革拷l锟接筹拷
	 * 
	 * @param con
	 *            锟酵伙拷锟斤拷锟斤拷锟酵放碉拷l锟斤拷
	 */
	public synchronized void freeConnection(BocoConnection conn) {
		if (conn == null)
			return;
		conn.close();
	}

	/**
	 * 锟斤拷l锟接池伙拷锟揭伙拷锟斤拷锟斤拷l锟斤拷.锟斤拷没锟叫匡拷锟叫碉拷l锟斤拷锟揭碉拷前l锟斤拷锟斤拷小锟斤拷锟斤拷锟絣锟斤拷
	 * 锟斤拷锟斤拷锟斤拷,锟津创斤拷锟斤拷l锟斤拷.锟斤拷原4锟角硷拷为锟斤拷锟矫碉拷l锟接诧拷锟斤拷锟斤拷效,锟斤拷锟斤拷锟?删锟斤拷之,
	 * 然锟斤拷莨锟斤拷锟斤拷锟皆硷拷锟皆筹拷锟斤拷锟铰的匡拷锟斤拷l锟斤拷.
	 */

	public synchronized BocoConnection getConnection() {
		BocoConnection conn = null;
		Enumeration connList = connections.elements();
		int i = 1;
		while ((connList != null) && (connList.hasMoreElements())) {
			conn = (BocoConnection) connList.nextElement();
			if (conn.isRelease() && isUsable(conn)) {
				connections.removeElement(conn);
				connections.addElement(conn);
				conn.useConnection();
				return conn;
			}
			i++;
		}

		if (maxconn == 0 || connections.size() < maxconn || conn.isRelease()) {
			conn = newConnection();
			conn.useConnection();
		}
		return conn;
	}

	/**
	 * 锟斤拷l锟接池伙拷取锟斤拷锟斤拷l锟斤拷.锟斤拷锟斤拷指锟斤拷锟酵伙拷锟斤拷锟斤拷锟杰癸拷锟饺达拷锟斤拷畛な憋拷锟?锟轿硷拷前一锟斤拷getConnection()锟斤拷锟斤拷.
	 * 
	 * @param timeout
	 *            锟皆分计的等达拷时锟斤拷锟斤拷锟斤拷
	 */

	public synchronized BocoConnection getConnection(int timeout) {
		long startTime = new Date().getTime();
		BocoConnection conn;
		while ((conn = getConnection()) == null) {
			try {
				wait(500);
			} catch (InterruptedException e) {
			}
			if ((new Date().getTime() - startTime) >= timeout * 60 * 1000)
				return null;
		}
		return conn;
	}

	public synchronized void checkDBPool() { // 锟斤拷锟絣锟接筹拷锟叫碉拷l锟斤拷
		// BocoLog.debug(this, 131, "锟斤拷锟角? + poolname + "锟斤拷锟叫碉拷锟角凤拷锟斤拷:" +
		// checkdbable);
		if (!checkdbable)
			return;
		long idletime = System.currentTimeMillis() - maxidletime * 60 * 1000;
		long activetime = System.currentTimeMillis() - maxalivetime * 60 * 1000;

		for (int i = connections.size() - 1; i >= 0; i--) {
			BocoConnection conn = (BocoConnection) connections.get(i);
			boolean flagUseLongActinveTime = conn.isFlagUseLongActiveTime();
			if (flagUseLongActinveTime)
				activetime = System.currentTimeMillis() - 180 * 60 * 1000;
			if (conn.isUse() && (activetime > conn.getTimeStamp())) {
				closeConnection(conn);
				// 锟斤拷锟斤拷锟绞★拷谐锟斤拷锟絣锟接池碉拷锟斤拷锟斤拷锟斤拷锟解，锟酵斤拷注锟斤拷去锟斤拷锟斤拷蟹锟斤拷锟?
				// BocoLog.debug(this,144,"锟斤拷锟节关憋拷l锟接★拷锟斤拷锟斤拷"+conn.getTimeStamp());
			} else if ((idletime > conn.getTimeStamp() || !conn.isValidate())
					&& conn.isRelease())
				closeConnection(conn);
			else if (conn.useCount() > maxusecount && conn.isRelease())
				closeConnection(conn);
		}
		// BocoLog.info(this, 131, "锟斤拷锟斤拷" + poolname + "锟斤拷锟叫碉拷l锟斤拷锟斤拷为:" +
		// connections.size());
		for (int i = connections.size(); i < minconn; i++) {
			if (newConnection() == null) {
				break;
			}
		}
		// BocoLog.info(this, 173, "锟斤拷锟斤拷锟铰猴拷" + poolname + "锟斤拷锟叫碉拷l锟斤拷锟斤拷为:"
		// +
		// connections.size());
	}

	/**
	 * 锟截憋拷锟斤拷锟斤拷l锟斤拷
	 */

	public synchronized void release() {
		Enumeration connList = connections.elements();
		while (connList.hasMoreElements()) {
			BocoConnection conn = (BocoConnection) connList.nextElement();
			conn.release();
		}
		connections.removeAllElements();
	}

	// 锟斤拷锟截筹拷锟斤拷锟斤拷锟叫碉拷l锟斤拷
	public Iterator getConnectionIterator() {
		return connections.iterator();
	}

	// 锟斤拷锟截筹拷锟叫碉拷l锟斤拷锟斤拷
	public int getConnectionSize() {
		return connections.size();
	}

	// 锟斤拷锟截关闭筹拷锟斤拷锟斤拷锟叫碉拷l锟斤拷
	public Iterator getCloseConnectionIterator() {
		return closeConnections.iterator();
	}

	// 锟斤拷锟截关闭筹拷锟叫碉拷l锟斤拷锟斤拷
	public int getCloseConnectionSize() {
		return closeConnections.size();
	}

	// 锟斤拷锟斤拷锟斤拷l锟接筹拷
	public boolean restartPool() {
		try {
			release();
			for (int i = 0; i < minconn; i++) {
				newConnection();
			}
			checkdbable = true;
			connChk.exit();
			// connChk.destroy();
			connChk = null;
			BocoLog.info(this, 185, "锟斤拷锟斤拷锟斤拷l锟接池成癸拷锟斤拷" + checkdbable);
			connChk = new ConnCheckerTimer(this, mtime);
			connChk.start();
			/*
			 * 锟斤拷锟斤拷锟斤拷锟斤拷timer4锟斤拷亩锟绞憋拷锟斤拷锟斤拷 timer.cancel(); timer = null;
			 * timer = new Timer(); timer.schedule(new ConnCheckerTimer(this),
			 * 1000 , 5000);
			 */
		} catch (Exception e) {
			BocoLog.error(this, 187, "锟斤拷锟斤拷锟斤拷l锟接筹拷失锟杰ｏ拷", e);
			return false;
		}
		return true;
	}

	public boolean resetCheckTimer() {
		try {
			connChk.exit();
			// connChk.destroy();
			connChk = null;
			connChk = new ConnCheckerTimer(this, mtime);
			connChk.start();
		} catch (Exception e) {
			BocoLog.error(this, 185, "错误" + checkdbable, e);
			return false;
		}
		BocoLog.info(this, 185, "锟斤拷锟矫硷拷锟斤拷锟缴癸拷锟斤拷" + checkdbable);
		return true;
	}

	/**
	 * 锟斤拷锟斤拷锟铰碉拷l锟斤拷
	 */
	private BocoConnection newConnection() {
		Connection conn = null;
		BocoConnection Bococonn = null;
		try {
			if (user == null)
				conn = DriverManager.getConnection(URL);
			else {
				Properties props = new Properties();
				props.put("user", user);
				props.put("password", password);
				props.put("CHARSET", charset);
				conn = DriverManager.getConnection(URL, props);
			}
			if (URL.indexOf("oracle") > 0) {
				Statement stmt = conn.createStatement();
				stmt
						.execute("alter session set NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'");
				BocoLog.trace(this, 179, "oracle NLS_DATE_FORMAT changed!");
				conn.commit();
			}
			if (URL.indexOf("informix") > 0) {
				conn.setTransactionIsolation(1);
			}
		} catch (SQLException e) {
			BocoLog.error(this, 184, "锟睫凤拷锟斤拷锟斤拷锟斤拷锟斤拷URL锟斤拷l锟斤拷: " + URL, e);
			return null;
		}
		// BocoLog.trace(this,186,"newConection begin!");
		Bococonn = new BocoConnection(conn);
		// BocoLog.trace(this,188,"newConnection success!");
		connections.addElement(Bococonn);
		return Bococonn;
	}

	/**
	 * 锟斤拷锟絣锟斤拷锟角凤拷锟斤拷锟?锟界不锟斤拷锟斤拷,锟斤拷去锟斤拷
	 */
	private boolean isUsable(BocoConnection conn) {
		long idletime = System.currentTimeMillis() - maxidletime * 60 * 1000;
		if (!conn.isValidate() || idletime > conn.getTimeStamp()
				|| conn.useCount() > maxusecount) {
			closeConnection(conn);
			return false;
		}
		return true;
	}

	/**
	 * 锟截憋拷一锟斤拷l锟斤拷
	 */
	private void closeConnection(BocoConnection conn) {
		try {
			ConnCloseThread cct = new ConnCloseThread(conn, closeConnections);
			cct.start();
			connections.removeElement(conn);
			// 锟斤拷锟斤拷锟绞★拷谐锟斤拷锟絣锟接池碉拷锟斤拷锟斤拷锟斤拷锟解，锟酵斤拷注锟斤拷去锟斤拷锟斤拷蟹锟斤拷锟?
			// BocoLog.trace(this,275,"conn"+conn.getTimeStamp()+"锟接筹拷锟斤拷去锟斤拷");
			conn = null;
		} catch (Exception e) {
			BocoLog.error(this, 248, "锟截憋拷l锟斤拷失锟斤拷!", e);
		}
	}

	private void logBocoInformix(String msg, BocoConnection conn,
			long activetime, long idletime) {
		if (msg.equals("BocoInformix")) {
			BocoLog.debug(this, 249, "conn.isUse():" + conn.isUse());
			if (conn.isUse()) {
				BocoLog.debug(this, 250, "activetime > conn.getTimeStamp():"
						+ activetime + "##" + conn.getTimeStamp());
				BocoLog.debug(this, 250, "锟角凤拷应锟矫关憋拷"
						+ (activetime > conn.getTimeStamp())
						+ conn.getTimeStamp());
			}
			// BocoLog.info(this,251,"idletime>conn.getTimeStamp():"+idletime+"$$"+conn.getTimeStamp());
			// BocoLog.info(this,252,"conn.isValidate():"+conn.isValidate());
			// BocoLog.info(this,253,"conn.isRelease():"+conn.isRelease());
			// BocoLog.info(this,254,"conn.useCount() >
			// maxusecount:"+conn.useCount()+"&&"+maxusecount);
		}
	}

	public String getPoolName() {
		return poolname;
	}

	public boolean getTimerExitFlag() {
		return connChk.getFlag();
	}

	public String getTimerAlive() {
		String active = "isAlive :" + connChk.isAlive() + "|| isInterrupted"
				+ connChk.isInterrupted() + " ||" + connChk + "|| ExitFlag"
				+ connChk.getFlag();
		return active;
	}

	/**
	 * 锟斤拷锟侥憋拷锟斤拷息写锟斤拷锟斤拷志锟侥硷拷
	 */
	private void log(String msg) {
		// log.writeLog(msg);
	}

	/**
	 * 锟斤拷锟侥憋拷锟斤拷息锟斤拷锟届常写锟斤拷锟斤拷志锟侥硷拷
	 */
	private void log(Throwable e, String msg) {
		// log.writeLog(msg + " Exception is : ");
		// log.writeLog(e);
	}
}
