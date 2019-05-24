package com.boco.eoms.km.core.dataservice.util;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import com.boco.eoms.km.core.dataservice.database.RelationalDB;

public class BaseUtil {

	public static String convertObjectToString(Object aObject, int aDataType) {
		if (aObject == null) {
			return null;
		}
		switch (aDataType) {
		case Types.BIT: // -7
			// return ;
		case Types.TINYINT: // -6
			// return ;
		case Types.SMALLINT: // 5
			// return ;
		case Types.INTEGER: // 4
			return aObject.toString();
		case Types.BIGINT: // -5
			// return ;
		case Types.FLOAT: // 6
			// return ;
		case Types.REAL: // 7
			// return ;
		case Types.DOUBLE: // 8
			// return ;
		case Types.NUMERIC: // 2
			BigDecimal big = (BigDecimal) aObject;
			return big.toPlainString();
		case Types.DECIMAL: // 3
			// return ;
		case Types.CHAR: // 1
			return aObject.toString().trim();
		case Types.VARCHAR: // 12
			return aObject.toString();
		case Types.LONGVARCHAR: // -1
			return aObject.toString();

		case Types.DATE: // 91
			return convertDateToString((java.util.Date) aObject, aDataType);
		case Types.TIME: // 92
			return convertDateToString((java.util.Date) aObject, aDataType);
		case Types.TIMESTAMP: // 93
			return convertDateToString((java.util.Date) aObject, aDataType);

		case Types.BINARY: // -2
			// return ;
		case Types.VARBINARY: // -3
			// return ;
		case Types.LONGVARBINARY: // -4
			// return ;

		case Types.NULL: // 0
			return null;
		case Types.OTHER: // 1111
			return null;

		case Types.JAVA_OBJECT: // 2000
			// return ;
		case Types.DISTINCT: // 2001
			// return ;
		case Types.STRUCT: // 2002
			// return ;
		case Types.ARRAY: // 2003
			// return ;
		case Types.BLOB: // 2004
			// return ;
		case Types.CLOB: // 2005
			// return ;
		case Types.REF: // 2006
			// return ;
		case Types.DATALINK: // 70
			return null;
		case Types.BOOLEAN: // 16
			return null;
		}
		return null;
	}

	private static String convertDateToString(java.util.Date aDate, int aDataType) {
		java.text.DateFormat df = null;
	    if (aDate == null)
	        return null;		
		switch (aDataType) {
		case Types.DATE: // 91
			df = new SimpleDateFormat(RelationalDB.DATE_FORMAT);
			return df.format(aDate);
		case Types.TIME: // 92
			df = new SimpleDateFormat(RelationalDB.TIME_FORMAT);
			return df.format(aDate);
		case Types.TIMESTAMP: // 93
			return df.format(RelationalDB.TIMESTAMP_FORMAT);
		}
		df = new SimpleDateFormat(RelationalDB.DATE_FORMAT);
		return df.format(aDate);
	}

	public static Object convertStringToNumber(String aValueString,
			int aDataType, String pattern) throws Exception {
		if (aValueString == null)
			return null;

		String tmp = null;
		if ((pattern == null) || (pattern.trim().equals("")))
			tmp = aValueString;
		else
			try {
				DecimalFormat formatter = new DecimalFormat(pattern);
				Number number = formatter.parse(aValueString);
				tmp = number.toString();
			} catch (Exception e) {
				tmp = aValueString;
			}

		switch (aDataType) {
		case Types.DOUBLE: // 8
			return new Double(tmp);
		case Types.FLOAT: // 6
			return new Float(tmp);
		case Types.INTEGER: // 4
			return new Integer(tmp);
		case 0:
			return null;
		case 2:
			return new BigDecimal(tmp);
		case 3:
			return new BigDecimal(tmp);
		case -5:
			return new Long(tmp);
		case 5:
			return new Integer(tmp);
		case -4:
		case -3:
		case -2:
		case -1:
		case 1:
		case 7:
		}
		String mes = "The type of convertNumberToString is invalidate : "
				+ aDataType + "[str = " + aValueString + "]";
		mes = mes + "[pattern = " + pattern + "]";
		throw new Exception(mes);
	}

	public static String formatString(String str, String pattern) {
		if (str == null)
			return "";
		if ((pattern == null) || (pattern.trim().equals("")))
			return str;
		Object[] objs = new Object[1];
		objs[0] = str;
		return MessageFormat.format(pattern, objs);
	}
	

}
