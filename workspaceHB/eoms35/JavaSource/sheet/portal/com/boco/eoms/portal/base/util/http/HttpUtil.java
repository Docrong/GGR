package com.boco.eoms.portal.base.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Title: 在后台做http请求的工具类
 * </p>
 * <p>
 * Description: 使用了jdk默认自带的实现方式
 * </p>
 * <p>
 * 2010-2-8 下午04:07:54
 * </p>
 * 
 * @author 陈辉
 * @version 1.0
 * 
 */
public class HttpUtil {

	/**
	 * 用Map拼成Post需要的参数串
	 * 
	 * @param attMap
	 *            参数的Map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getHttpQueryString(Map attMap)
			throws UnsupportedEncodingException {

		Iterator it = attMap.entrySet().iterator();

		StringBuffer paramData = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();

			Object attkey = entry.getKey();
			Object value = entry.getValue();

			paramData.append(attkey).append("=").append(value).append("&");
		}

		// for (String attkey : attMap.keySet()) {
		// String value = (String) attMap.get(attkey);
		// }
		if (paramData.length() > 0) // delete the last char '&'
			paramData.deleteCharAt(paramData.length() - 1);
		return paramData.toString();
	}

	/**
	 * 具体url和参数的Map发送一个http请求
	 * 
	 * @param url
	 *            http请求的地址
	 * @param attMap
	 *            参数的Map
	 * @return 请求返回的内容
	 * @throws HttpException
	 */
	public static String postHttpRequest(String url, Map attMap) {

		String retrunContent = "";
		URL httpurl = null;
		HttpURLConnection httpurlconnection = null;
		String httpQuery = "";
		try {
			System.out.println("发起远程请求url： " + url);
			httpurl = new URL(url);
			httpurlconnection = (HttpURLConnection) httpurl.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");
			httpQuery = HttpUtil.getHttpQueryString(attMap);

			httpurlconnection.getOutputStream().write(
					httpQuery.getBytes("UTF-8"));
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			int code = httpurlconnection.getResponseCode();
			if (code == 200) {
				retrunContent = readContents(httpurlconnection);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}

		return retrunContent;
	}

	private static String readContents(HttpURLConnection httpurlconnection) {
		BufferedReader in = null;
		String retrunContent = "";
		try {
			in = new BufferedReader(new InputStreamReader(httpurlconnection
					.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				retrunContent += inputLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retrunContent;
	}

	/**
	 * 
	 * 取得真实的客户端访问ip。
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIpAddres(HttpServletRequest request) {

		/*
		 * //如果getParameter(ip) 不为空说明为传递过来的ip String ip =
		 * request.getParameter(Const.SSO_VISITIP); if(ip!=null &&
		 * !"".equals(ip)){ return ip; }
		 */
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}
}
