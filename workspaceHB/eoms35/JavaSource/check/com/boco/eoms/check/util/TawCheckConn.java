package com.boco.eoms.check.util;

import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * @author locker
 *
 */

public class TawCheckConn {
	
	private String cdmaUrl="jdbc:informix-sqli://10.4.0.1:8005/npmdb:informixserver=cwnmsserver;user=informix;password=informix";
	private String gsmUrl="jdbc:informix-sqli://10.4.0.7:8004/npmdb:informixserver=npmserver;user=informix;password=informix";
	
	public Connection getCdmaConn(){
		
		Connection conn=null;
		Properties pr = new Properties();
        pr.put("characterEncoding", "GB2312");
        pr.put("useUnicode", "TRUE");
		try {
	    	Class.forName("com.informix.jdbc.IfxDriver");
            conn = DriverManager.getConnection(cdmaUrl+"",pr);    
            System.out.println("JDBC driver name: " + conn.getMetaData().getDriverName());
		}
            catch (ClassNotFoundException drvEx)
       	 {
                   System.err.println("Could not load JDBC driver");
                   System.out.println("Exception: " + drvEx);
                   drvEx.printStackTrace();
       }
            catch(SQLException sqlEx)
            {
              while(sqlEx != null)
               {
         System.err.println("SQLException information");
    	 System.err.println("Error msg: " + sqlEx.getMessage());
    	 System.err.println("SQLSTATE: " + sqlEx.getSQLState());
    	 System.err.println("Error code: " + sqlEx.getErrorCode());
    	 sqlEx.printStackTrace();
         }
        }
            return conn;
	}
	
	public Connection getGsmConn(){
		
		Connection conn=null;
		try {
	    	Class.forName("com.informix.jdbc.IfxDriver");
            conn = DriverManager.getConnection(gsmUrl);    
            System.out.println("JDBC driver name: " + conn.getMetaData().getDriverName());
		}
            catch (ClassNotFoundException drvEx)
       	 {
                   System.err.println("Could not load JDBC driver");
                   System.out.println("Exception: " + drvEx);
                   drvEx.printStackTrace();
       }
            catch(SQLException sqlEx)
            {
              while(sqlEx != null)
               {
         System.err.println("SQLException information");
    	 System.err.println("Error msg: " + sqlEx.getMessage());
    	 System.err.println("SQLSTATE: " + sqlEx.getSQLState());
    	 System.err.println("Error code: " + sqlEx.getErrorCode());
    	 sqlEx.printStackTrace();
         }
        }
            return conn;
	}
}
