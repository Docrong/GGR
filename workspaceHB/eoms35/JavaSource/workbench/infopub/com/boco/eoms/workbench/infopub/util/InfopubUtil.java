package com.boco.eoms.workbench.infopub.util;

/**
 * <p>
 * Title:信息发布的工具类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 2:01:41 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class InfopubUtil {
	/**
	 * 判断字符串是否为"true"
	 * 
	 * @param bool
	 *            字符串
	 * @return 字符串是否为true的boolean值
	 */
	public static boolean isTrue(String bool) {
		return InfopubConstants.TRUE_STR.equals(bool);
	}

	/**
	 * 为一个数字加1后返回
	 * 
	 * @param number
	 *            整型对象
	 * @return 加1后的结果
	 */
	public synchronized static Integer add1(Integer number) {
		if (null == number) {
			number = new Integer(0);
		}
		return new Integer(number.intValue() + 1);
	}
}
