package com.boco.eoms.commons.statistic.base.anychart.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	/**
	* get a connection from pool
	* @return
	*/
	public static Connection getConnection(){
		Connection con = com.boco.eoms.db.util.ConnectionPool.getInstance().getConnection();
		return con;
	}
	
	public static void closeConnection(Connection con){
		try{
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void closeStatement(Statement stmt){
		try{
			if(stmt != null){
				stmt.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void closeResultSet(ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
}
