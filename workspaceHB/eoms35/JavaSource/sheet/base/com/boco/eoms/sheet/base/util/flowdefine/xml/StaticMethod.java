package com.boco.eoms.sheet.base.util.flowdefine.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.boco.eoms.base.Constants;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * <p>
 * Title: eoms
 * </p>
 * <p>
 * Description: 静态方法类，如字符转换等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: boco
 * </p>
 * 
 * @author chenyaunshu
 * @version 1.0 update dy chenyaunshu 2009-1-4 13:00
 */
public class StaticMethod {

	/**
	 * log4j
	 */
	private static final Logger logger = Logger.getLogger(StaticMethod.class);

	static PropertyFile PROP = PropertyFile.getInstance();

	static String CHARSET_PAGE = PROP.getProperty(
			"database.format.Charset.Page_Charset").toString();

	static String CHARSET_DB = PROP.getProperty(
			"database.format.Charset.DB_Charset").toString();

	static String CHARSET_BEAN = PROP.getProperty(
			"database.format.Charset.FormBean_Charset").toString();

	/**
	 * classpath标识
	 */
	public final static String CLASSPATH_FLAG = "classpath:";





	/**
	 * 获取filePath的url
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 *             创建url失败将抛出MalformedURLException
	 */
	public static URL getFileUrl(String filePath) throws FileNotFoundException {
		URL url = null;
		if (filePath != null) {
			if (filePath.length() > CLASSPATH_FLAG.length()) {
				if (CLASSPATH_FLAG.equals(filePath.substring(0, CLASSPATH_FLAG
						.length()))) {
					// url =
					// loader.getResource(filePath.substring(CLASSPATH_FLAG
					// .length()));
					try {
						// 创建url失败将抛出MalformedURLException
						// url = ClassLoaderUtil
						// .getExtendResource(getPathButClasspath(filePath));
						url = StaticMethod.class.getClassLoader().getResource(
								getPathButClasspath(filePath));
						
						// url = new URL(URI.toString()
						// + filePath.substring(CLASSPATH_FLAG.length()));
					} catch (Exception e) {
						logger.error(e);
						throw new FileNotFoundException(filePath
								+ "is not found.");
					}

				} else {
					// TODO 有问题，需修改
				}
			}
		}
		return url;
	}

	

	/**
	 * 去掉classpath
	 * 
	 * @param path
	 * @return
	 */
	private static String getPathButClasspath(String path) {
		return path.substring(CLASSPATH_FLAG.length());
	}

	/**
	 * 读java包时返回的路径
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getFilePathForUrl(String filePath)
			throws FileNotFoundException {
		URL url = getFileUrl(filePath);
		return url.getFile();
	}

	/**
	 * 取filePath的InputStream
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getFileInputStream(String filePath)
			throws FileNotFoundException {
		InputStream inputStream = null;
		if (filePath != null) {
			if (filePath.length() > CLASSPATH_FLAG.length()) {
				if (CLASSPATH_FLAG.equals(filePath.substring(0, CLASSPATH_FLAG
						.length()))) {
					try {
						// inputStream = loader.getResourceAsStream(filePath
						// .substring(CLASSPATH_FLAG.length()));
						// inputStream = new FileInputStream(
						// getFilePathForUrl(filePath));
						inputStream = ClassLoaderUtil
								.getStream(getFileUrl(filePath));
					} catch (IOException e) {
						logger.error(e);
						throw new FileNotFoundException(filePath
								+ " is not found!!!");
					}

				} else {

					inputStream = new FileInputStream(filePath);

				}
			}
		}
		return inputStream;
	}

	public StaticMethod() {
	}


	
 
}
