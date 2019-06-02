package com.boco.eoms.workplan.dao.Jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpGetZNXJ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TawwpGetZNXJDAO extends BaseDaoJdbc implements ITawwpGetZNXJ {

	Connection conn = null;

	public Connection getCon() {
		try {
			Class.forName("com.informix.jdbc.IfxDriver").newInstance();
			String url = "jdbc:informix-sqli://10.101.16.17:8006/nmosdb:INFORMIXSERVER=nmosserver1;user=nmosuser;password=nmosuser";
			// testDB为你的数据库名
			conn = DriverManager.getConnection(url);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public List getFileDetail(String deptname, String date) {
		String regionname = StaticMethod.null2String(deptname);
		List list = new ArrayList();
		Connection con = this.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// String sql="select * from t_region_errnet where exec_date>='2009-8-1
		// 00:00:00' and exec_date<='2009-8-1 23:59:59'";
		// strFromDBToPage
		try {
			String sql = "select * from t_region_errnet where region_name='"
					+ new String(regionname.getBytes("GBK"), "ISO-8859-1")
					+ "'";
			sql += " and exec_date>='" + date + " 00:00:00' and exec_date<='"
					+ date + " 23:59:59'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("sdfsdfsdfdddddddddddddddddddddddddddd");

				list.add(new String(rs.getString("region_name").getBytes("ISO-8859-1")));
				list.add(new String(rs.getString("log_file").getBytes("ISO-8859-1")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return list;
	}

	// public static void main(String[] args) {
	// TawwpGetZNXJ obj = new TawwpGetZNXJ();
	// List list= obj.getFileDetail("眉山","20081027");
	// for(int i=0;i<list.size();i++){
	// System.out.print(list.get(i));
	// }
	// }
}
