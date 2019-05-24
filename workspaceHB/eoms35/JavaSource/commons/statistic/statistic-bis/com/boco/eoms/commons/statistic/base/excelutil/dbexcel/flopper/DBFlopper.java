package com.boco.eoms.commons.statistic.base.excelutil.dbexcel.flopper;

import java.sql.Connection;
import java.sql.DriverManager;

import com.boco.eoms.commons.statistic.base.excelutil.dbexcel.Flopper;

/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
abstract class DBFlopper implements Flopper {
	private String driver = null;
	private String url = null;
	private String user = null;
	private String password = null;
	private Connection conn = null;

	private boolean driverReady = false;

	private void setDriver() {
		try {
			System.setProperty("jdbc.drivers",driver);
			//Class.forName(driver);
		} catch (Exception e) {
			System.err.print("Exception: ");
			System.err.println(e.getMessage());
		}
		driverReady = true;
	}

	public Connection getConnection() {
		if (driver == null || url == null || user == null || password == null)
			return null;
		if (!driverReady)
			setDriver();
		try {
			if (conn == null)
				conn = DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			System.err.println("Exception: " + ex.getMessage());
		}
		return conn;
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * @see com.uncnet.dbreplant.TextFlopper#setParameter(String[])
	 */
	public void setParameter(String[] para) {
		driver = para[0];
		url = para[1];
		//System.err.println(url);
		user = para[2];
		password = para[3];
	}
}
