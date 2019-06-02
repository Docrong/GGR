package com.boco.eoms.message.dao.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import org.apache.commons.beanutils.BeanUtils;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.db.util.BocoConnection;

public class BaseDAO {

	protected static Connection ds = null;

	protected  static void populate(Object bean, ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int ncolumns = metaData.getColumnCount();

		HashMap properties = new HashMap();
		String sTemp = "";
		// Scroll to next record and pump into hashmap
		for (int i = 1; i <= ncolumns; i++) {
			// System.out.println(metaData.getColumnName(i) +":"+
			// metaData.getColumnTypeName(i));
			if (metaData.getColumnTypeName(i).toLowerCase().equals("date")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"datetime")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"datetime year to second")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"number")) {
				sTemp = StaticMethod.null2String(rs.getString(i)).replaceAll(
						"\\.0", "");
			} else {
				// sTemp=StaticMethod.dbNull2String(rs.getString(i));
				sTemp = StaticMethod.null2String(rs.getString(i));
			}
			properties.put(
					sql2javaName(metaData.getColumnName(i).toLowerCase()),
					sTemp);
		}
		// Set the corresponding properties of our bean
		try {
			BeanUtils.populate(bean, properties);
		} catch (InvocationTargetException ite) {
			throw new SQLException("BeanUtils.populate threw " + ite.toString());
		} catch (IllegalAccessException iae) {
			throw new SQLException("BeanUtils.populate threw " + iae.toString());
		}
	}

	public static int getSize(String tableName, String condition) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int size = 0;
		try {
			String sql = "";

			if (condition.equals("deleted"))
				sql = "SELECT count(*) FROM " + tableName + "  WHERE deleted="
						+ StaticVariable.UNDELETED;
			else {
				// condition = condition.toLowerCase();
				/*
				 * Vector vec = new Vector(); vec =
				 * StaticMethod.getVector(condition,"order by"); condition =
				 * vec.get(0).toString();
				 */
				// -------------------------
				// modify by dudajiang 2003-9-16
				// 当条件语句中有order by的时候，在informix中无法计算count，所以要去掉排序的部分
				// ------------------------
				if (condition.lastIndexOf("order by") != -1) {
					condition = condition.substring(0, condition
							.lastIndexOf("order by"));
				}
				sql = "SELECT count(*) FROM " + tableName + " " + condition;
			}
			 
			pstmt = ds.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				size = rs.getInt(1);
			}
			close(rs);
			close(pstmt);

		} catch (SQLException sqle) {
			close(rs);
			close(pstmt);
			rollback(conn);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(conn);
		}

		return size;
	}
 

	protected static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			rs = null;
		}
	}

	protected static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
			pstmt = null;
		}
	}

	protected  static void close(com.boco.eoms.db.util.BocoConnection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected static void rollback(com.boco.eoms.db.util.BocoConnection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	protected static String java2sqlName(String name) {
		String column = "";
		for (int i = 0; i < name.length(); i++) {
			if (i < name.length() - 1
					&& (name.charAt(i) >= 'a' && name.charAt(i) <= 'z')
					&& (name.charAt(i + 1) >= 'A' && name.charAt(i + 1) <= 'Z')) {
				column += name.charAt(i) + "_";
			} else {
				column += name.charAt(i);
			}
		}
		return column.toLowerCase();
	}

	protected static String sql2javaName(String name) {
		int k = name.indexOf(".");
		name = name.substring(k + 1);

		String column = "";
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '_') {
				column += ++i < name.length() ? String.valueOf(name.charAt(i))
						.toUpperCase() : "";
			} else {
				column += name.charAt(i);
			}
		}
		return column;
	}

	protected static void popOracle(Object bean, ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int ncolumns = metaData.getColumnCount();

		HashMap properties = new HashMap();
		String sTemp = "";
		// Scroll to next record and pump into hashmap
		for (int i = 1; i <= ncolumns; i++) {
			// System.out.println(metaData.getColumnName(i) +":"+
			// metaData.getColumnTypeName(i));
			if (metaData.getColumnTypeName(i).toLowerCase().equals("date")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"datetime")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"datetime year to second")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"number")) {
				sTemp = StaticMethod.null2String(rs.getString(i)).replaceAll(
						"\\.0", "");
			} else {
				sTemp = StaticMethod.dbNull2String(rs.getString(i));
			}
			properties.put(sql2javaName(metaData.getColumnName(i)), sTemp);
		}
		// Set the corresponding properties of our bean
		try {
			BeanUtils.populate(bean, properties);
		} catch (InvocationTargetException ite) {
			throw new SQLException("BeanUtils.populate threw " + ite.toString());
		} catch (IllegalAccessException iae) {
			throw new SQLException("BeanUtils.populate threw " + iae.toString());
		}
	}

	protected static void createTempTable(String name, BocoConnection conn)
			throws SQLException {
		String sql = "create temp table " + name + "(id integer) ";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException sqle) {
			close(pstmt);
			 
		}

		// conn.commit();
	}

	protected static void insertTempTable(String name, BocoConnection conn, String ids)
			throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {

			sql = "INSERT INTO " + name + " (id) VALUES (?)";

			ArrayList list = StaticMethod.getArrayList(ids, ",");

			for (int i = 0; i < list.size(); i++) {
				pstmt = null;
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, StaticMethod.nullObject2int(list.get(i)));
				pstmt.executeUpdate();
				pstmt.close();
			}

		} catch (Exception sqle) {
			// close(pstmt);
			 
		}
	}

	protected static void dropTempTable(String name, BocoConnection conn)
			throws SQLException {
		String sql = "drop table " + name;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException sqle) {
			close(pstmt);
			 
		}
	}
}
