package com.boco.eoms.commons.file.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.DateConverter;
import com.boco.eoms.commons.file.exception.FMUtilException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 11:11:00 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FileUtil {

	private final static Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 字符类型
	 */
	public final static String STRING_TYPE = "string";

	/**
	 * 长整型
	 */
	public final static String LONG_TYPE = "long";

	/**
	 * 整型
	 */
	public final static String INTEGER_TYPE = "integer";

	/**
	 * 双精度型
	 */
	public final static String DOUBLE_TYPE = "double";

	/**
	 * 日期型 根据系统时间格式,如:MM/dd/yyyy
	 */
	public final static String DATE_TYPE = "date";

	/**
	 * 日期+时间 根据系统时间格式,如:MM/dd/yyyy HH:mm:ss.S
	 */
	public final static String TIMESTAMP_TYPE = "timestamp";

	/**
	 * 类型map
	 */
	private static Map typeMap;

	/**
	 * 通过type类型取content对应的实例对象
	 * 
	 * @param content
	 *            串
	 * @param type
	 *            类型，integer,double,date,timstamp
	 * @return
	 * @throws FMUtilException
	 */
	public static Object getObjectByType(String content, String type)
			throws FMUtilException

	{
		if (LONG_TYPE.equals(type)) {
			return new Long(content);
		} else if (INTEGER_TYPE.equals(type)) {
			return new Integer(content);
		} else if (DOUBLE_TYPE.equals(type)) {
			return new Double(content);
		} else if (DATE_TYPE.equals(type)) {
			return new DateConverter().convert(Date.class, content);
		} else if (TIMESTAMP_TYPE.equals(type)) {
			return new DateConverter().convert(Timestamp.class, content);
		}
		return content;
	}

	/**
	 * 通过fieldName字段名称取set方法名,例fieldName="memo",则返回setMemo
	 * 
	 * @param fieldName
	 * @return
	 * @throws FMUtilException
	 */
	public static String getSetMethodName(String fieldName)
			throws FMUtilException {
		if (fieldName == null || fieldName.length() < 1) {
			// TODO 需增加exception msg
			logger.error("fieldName is null");
			throw new FMUtilException("fieldName is null");
		}
		// 前首字母改为大写
		String prefix = fieldName.substring(0, 1);
		return "set" + prefix.toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 通过fieldName字段名称取set方法名,例fieldName="memo",则返回getMemo
	 * 
	 * @param fieldName
	 * @return
	 * @throws FMUtilException
	 */
	public static String getGetMethodName(String fieldName)
			throws FMUtilException {
		if (fieldName == null || fieldName.length() < 1) {
			// TODO 需增加exception msg
			logger.error("fieldName is null");
			throw new FMUtilException("fieldName is null");
		}
		// 前首字母改为大写
		String prefix = fieldName.substring(0, 1);
		return "get" + prefix.toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 通过type(string,long,integer,double)取Class
	 * 
	 * @param type
	 * @return
	 */
	public static Class getClass(String type) throws FMUtilException {

		if (type == null) {
			// TODO 需增加exception msg
			logger.error("type is null");
			throw new FMUtilException("type is null");
		}
		if (typeMap == null) {
			typeMap = new HashMap();
			typeMap.put(STRING_TYPE, String.class);
			typeMap.put(LONG_TYPE, Long.class);
			typeMap.put(INTEGER_TYPE, Integer.class);
			typeMap.put(DOUBLE_TYPE, Double.class);
			typeMap.put(DATE_TYPE, Date.class);
			typeMap.put(TIMESTAMP_TYPE, Timestamp.class);
		}

		return (Class) typeMap.get(type.toLowerCase());
	}

}
