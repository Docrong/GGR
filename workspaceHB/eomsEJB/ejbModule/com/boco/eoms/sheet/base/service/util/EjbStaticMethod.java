package com.boco.eoms.sheet.base.service.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;

public class EjbStaticMethod {
	private static String sql2javaName(String name) {
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
	
	public static void populate(Object bean, ResultSet rs,String columnName) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int ncolumns = metaData.getColumnCount();

		HashMap properties =new HashMap();
		String[] columnNames=columnName.split(",");
		String sTemp = "";

		for (int i = 0; i < ncolumns; i++) {			
			if (metaData.getColumnTypeName(i+1).toLowerCase().equals("date")
					|| metaData.getColumnTypeName(i+1).toLowerCase().equals(
							"datetime")
					|| metaData.getColumnTypeName(i+1).toLowerCase().equals(
							"datetime year to second")
					|| metaData.getColumnTypeName(i+1).toLowerCase().equals(
							"number")) {
				sTemp = StaticMethod.null2String(rs.getString(i+1)).replaceAll(
						"\\.0", "");
			} else {
				// sTemp=StaticMethod.dbNull2String(rs.getString(i));
				sTemp = StaticMethod.null2String(rs.getString(i+1));
			}
			System.out.println("column===="+columnNames[i]+"values====="+sTemp);
			properties.put(columnNames[i],StaticMethod.null2String(rs.getString(i+1)));
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
	
	
}
