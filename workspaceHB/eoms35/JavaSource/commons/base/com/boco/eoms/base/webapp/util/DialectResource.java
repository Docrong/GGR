package com.boco.eoms.base.webapp.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;

/**
 * 
 * <p>
 * Title:方言资源文件
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 27, 2008 1:51:06 PM
 * </p>
 * 
 * @author 周春兴
 * @version 1.0
 * 
 */
public class DialectResource {

	/**
	 * 取某个资源文件的key所对应的值
	 * 
	 * @param key
	 *            a.icon.path=d:/ 等号前为key，等号后为值
	 * @return a.icon.path=d:/等号后
	 */
	public static String getValue(String key) {
		Properties props = new Properties();
		try {
			String propertiesName = UtilMgrLocator.getEOMSAttributes()
					.getPropertiesFilePath();
			String path = StaticMethod
					.getFilePath("classpath:config/ApplicationResources_"
							+ propertiesName + ".properties");
			// String propertiesFilePath =
			// UtilMgrLocator.getEOMSAttributes().getPropertiesFilePath();

			InputStream in = new BufferedInputStream(new FileInputStream(path));
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
