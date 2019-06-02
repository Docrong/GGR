package com.boco.eoms.sheet.commonfault.db;


import java.sql.Connection;

import com.boco.eoms.sheet.base.util.PropertyFile;

public class NetOptimizationDB {

	private static String className = "";
	private static String conURL = "";
	private static String userName = "";
	private static String userPassword = "";
	public static Connection getConnection(){
		Connection conn = null;
	    try {
	    	PropertyFile properties= PropertyFile.getInstance("/config/NetOptimizationDBConfig.properties");
	    	if (properties != null) {
	    		className = properties.getPropertyByName("datasource.driverClass");
	    		conURL = properties.getPropertyByName("datasource.connectionURL");
	    		userName = properties.getPropertyByName("datasource.userName");
	    		userPassword = properties.getPropertyByName("datasource.password");
	    	}
			Class.forName(className).newInstance();
			conn = java.sql.DriverManager.getConnection(conURL, userName, userPassword);
			return conn;
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
