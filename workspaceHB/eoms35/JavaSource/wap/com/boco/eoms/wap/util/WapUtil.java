package com.boco.eoms.wap.util;

import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * WAP中使用的工具方法
 * 
 * @author leo
 * 
 */

public class WapUtil {

	/**
	 * @param contextPath
	 * @param url
	 * @return
	 * @see 判断路径，是否含有http 如果没有。则拼成本地跳转的url
	 * 
	 * 
	 */

	public static String checkHttpUrl(String contextPath, String url) {

		if (url != null) {
			if (url.startsWith("http") == false) {
				url = contextPath + url;

			}
		}

		return url;

	}

	/**
	 * 取cookie中key对应的值
	 * 
	 * @param key
	 *            保存cookie中对应的key
	 * @param cookies
	 *            cookie数组
	 * @return cookies对应key的值
	 */
	public static String getValue4Cookie(String key, Cookie[] cookies) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					return c.getValue();
				}

			}
		}
		return "";
	}

	/**
	 * @param url
	 * @return
	 * @see 将url内的还有curPage和pageSize的转变成curPage=1,pageSize=0
	 */
	public static String spiturl(String url) {
		String realUrl = "";
		if (url != null) {
			String[] urlArray = url.split("&");
			for (int i = 0; i < urlArray.length; i++) {

				if (urlArray[i].indexOf("pageSize") >= 0) {

					realUrl = realUrl + "&pageSize=0";
				} else {
					realUrl = realUrl + "&" + urlArray[i];
				}
			}
		}
		return realUrl.substring(1, realUrl.length());

	}

	/**
	 * cookie中保存用户
	 * 
	 * @param key
	 *            cookie对应的key
	 * @param value
	 *            key对应的value值
	 * @param response
	 *            httpServletResponse对象
	 */
	public static void saveValue4Cookie(String key, String value,
			HttpServletResponse response) {
		Cookie namecookie = new Cookie(key, value);
		namecookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(namecookie);
	}

	/**
	 * 判断一个字符串是否为合法手机号码
	 * 
	 * @param mobile
	 *            需要验证的手机号码字符串
	 * @return 返回验证结果，如果是则返回true, 否则返回false
	 * @author xugengxian
	 */
	public static boolean isMobileNumber(String mobile) {
		boolean result = false;
		if (mobile != null && mobile.length() == 11
				&& Pattern.compile("\\d+").matcher(mobile).matches()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 判断输入的时间格式是否为: yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 *            要被验证的时间字符串
	 * @return 格式匹配正确则返回 true，否则返回 false
	 * @author xugengxian
	 */
	public static boolean isRightTimeFormat(String date) {
		boolean result = false;
		if (date != null && !"".equalsIgnoreCase(date)) {
			Pattern pattern = Pattern
					.compile("^((((19|20)(([02468][048])|([13579][26]))-0?2-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0?[1-9])|(1[0-2]))-((0?[1-9])|(1\\d)|(2[0-8])))|((((0?[13578])|(1[02]))-31)|(((0?[1,3-9])|(1[0-2]))-(29|30))))) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
			result = pattern.matcher(date).matches();
		}
		return result;
	}

//	public static void main(String[] args) {
//		// AtomUtil.getFeedByURLandFilter4Pager("","liqiuye");
//		// System.out.println(WapUtil.checkHttpUrl("/eoms","/workbench/infopub/thread.do?id=8aa081e81f69e225011f69e946260007&method=detail"));
//		System.out
//				.println(WapUtil
//						.spiturl("http://10.131.62.199:9080/eoms35/sheet/Commonfault/commonfault.do?method=getAtomLists&curPage=1&pageSize=3"));
//
//	}
}
