package com.boco.eoms.portal.sso.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.portal.base.util.http.HttpUtil;
//import com.boco.iam.sso.client.LoginUtil;
//import com.boco.iam.sso.client.TransferTicket;
//import com.boco.iam.sso.client.UserInfo;
//import com.boco.iam.sso.client.ResultCode;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * <p>
 * Title: 单点登陆客户端的工具类
 * </p>
 * <p>
 * Description: 单点登陆客户端的工具类
 * </p>
 * <p>
 * 2010-7-13 下午10:43:54
 * </p>
 *
 * @author 陈辉
 * @version 1.0
 */
public class ClientUtil {

    /**
     * 检查当前url是否需要拦截
     *
     * @param requestPage 请求的url
     * @return
     */
    public static boolean checkURL(String requestPage) {
        Iterator iter = SSOConstants.URLSet.iterator();
        while (iter.hasNext()) {
            String url = (String) iter.next();
            if (url.endsWith(requestPage)) {
                return false;
            }
        }
        return true;
//		if (requestPage.indexOf("Token") >-1) {
//			return true;
//		} else {
//			return false;
//		}
    }

    /**
     * 检查当前用户是否在线
     *
     * @param request
     * @param response
     * @return 如果在线返回用户映射系统对应的userId，否则返回“”或者错误码
     */
    public static String checkOnLine(HttpServletRequest request) {
        String token = request.getParameter("Token");
        System.out.println("ClientUtil.token===" + token);
        String httpUrl = SSOConstants.BC_CHECK_ADDRESS;
        Map attMap = new HashMap();
        attMap.put("method", SSOConstants.BC_CHECK_METHOD);
        attMap.put("Token", token);
        attMap.put("IP", HttpUtil.getRealIpAddres(request));
        attMap.put("APP_KEY", SSOConstants.AC_APP_KEY);

        String retrunContent = HttpUtil.postHttpRequest(httpUrl, attMap);
        if (retrunContent == null || retrunContent.equals("")) {
            return "";
        }

        String betweenString = getBetweenContent(retrunContent, "RESULT");
        System.out.println("betweenString===" + betweenString);
        if (betweenString.length() > 3) {
            betweenString = betweenString.substring(1,
                    betweenString.length() - 2);
        }

        // 检查RESULT字段，0代表失败，1代表成功
        if (betweenString == null || "".equals(betweenString)
                || betweenString.equals("0")) {
            return "";
        } else if (betweenString.equals("1")) {

            // 检查RESULT_MSGCODE，如果是OK，代表成功。其他字段代表相应的错误信息
            betweenString = getBetweenContent(retrunContent, "RESULT_MSGCODE");
            if (betweenString.length() > 3) {
                betweenString = betweenString.substring(1, betweenString
                        .length() - 2);
            }

            if (betweenString == null || "".equals(betweenString)) {
                return "";
            } else if (betweenString.equals("OK")) {
                betweenString = getBetweenContent(retrunContent, "ACCOUNT");
                if (betweenString.length() > 3) {
                    betweenString = betweenString.substring(1, betweenString
                            .length() - 2);
                }

                if (betweenString == null || "".equals(betweenString)) {
                    return "";
                } else {
                    return betweenString;
                }
            } else {
                return betweenString;
            }
        }

        return "";
    }

//	public static String checkOnLineNew(HttpServletRequest request) {
//		String errorMessage = null;		
//		//com.boco.iam.sso.client.LoginUtil loginUtil=LoginUtil.getInstance();
//		if(loginUtil.isEnable()){//4A认证服务可用
//		    if(loginUtil.checkTicket(request)){//判断传递过来的url里面是否包括票据信息
//		       String strTic = loginUtil.getTicket(request);//获取票据信息
//		       TransferTicket ticket = loginUtil.analysTicket(strTic);//解析票据信息
//		       if(ticket!=null && ResultCode.RESULT_OK.equals(ticket.getRetCode())){//票据解析正确
//		         UserInfo userInfo = loginUtil.qryUserByTicket(ticket);//获取对应的用户信息
//		         if(userInfo==null){
//		        	 return SSOConstants.USER_UNLOGIN;
//		         }else if(!ResultCode.RESULT_OK.equals(userInfo.getRetCode())){
//		        	 //获取错误信息提示；
//		              errorMessage = loginUtil.getCodeMess(userInfo.getRetCode(),request.getLocale().getLanguage());
//		              BocoLog.info("com.boco.eoms.portal.sso.client.ClientUtil", "获取用户信息错误，错误信息为："+errorMessage);
//		              return SSOConstants.SYS_ERROR;
//		         }else{
//		            //应用资源根据帐号信息做登录后业务处理；
//		        	 String userId=StaticMethod.nullObject2String(userInfo.getAccountID());
//		        	 return userId;		        	
//		         }
//		       }else{
//		            errorMessage = loginUtil.getCodeMess(ticket.getRetCode(),request.getLocale().getLanguage());
//		            BocoLog.info("com.boco.eoms.portal.sso.client.ClientUtil", "解析票据信息错误，错误信息为："+errorMessage);
//		            return SSOConstants.SYS_ERROR;
//		       }
//		    } 
//		    BocoLog.info("com.boco.eoms.portal.sso.client.ClientUtil", "缺少票据信息"); 
//		    return "";
//		}
//		 BocoLog.info("com.boco.eoms.portal.sso.client.ClientUtil", "4A服务可不用，请联系管理员！");
//		 return "";
//	}

    /**
     * 登出系统
     *
     * @param request
     * @param response
     * @return
     */
    public static boolean logout(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(
                SSOConstants.SSO_TOKEN);
        String httpUrl = SSOConstants.BC_LOGOUT_ADDRESS;
        Map attMap = new HashMap();
        attMap.put("method", SSOConstants.BC_LOGOUT_METHOD);
        attMap.put("Token", token);
        attMap.put("IP", HttpUtil.getRealIpAddres(request));
        String retrunContent = HttpUtil.postHttpRequest(httpUrl, attMap);
        String betweenString = getBetweenContent(retrunContent, "RESULT");
        if (betweenString.length() > 3) {
            betweenString = betweenString.substring(1,
                    betweenString.length() - 2);
        }

        // 检查RESULT字段，0代表失败，1代表成功
        if (betweenString == null || "".equals(betweenString)
                || betweenString.equals("0")) {

            return false;
        } else if (betweenString.equals("1")) {

            // 检查RESULT_MSGCODE，如果是OK，代表成功。其他字段代表相应的错误信息
            betweenString = getBetweenContent(retrunContent, "RESULT_MSGCODE");
            if (betweenString.length() > 3) {
                betweenString = betweenString.substring(1, betweenString
                        .length() - 2);
            }

            if (betweenString == null || "".equals(betweenString)) {
                return false;
            } else if (betweenString.equals("OK")) {
                betweenString = getBetweenContent(retrunContent, "ACCOUNT");
                if (betweenString.length() > 3) {
                    betweenString = betweenString.substring(1, betweenString
                            .length() - 2);
                }

                if (betweenString == null || "".equals(betweenString)) {
                    return false;
                } else {

                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 提供工具方法，sourceString有两个不连续的betweenString
     * 取得一个sourceString中betweenString之间的字符串
     *
     * @param sourceString
     * @param betweenString
     * @return
     */
    private static String getBetweenContent(String sourceString,
                                            String betweenString) {
        String[] contents = sourceString.split(betweenString);
        if (contents.length >= 3) {
            return contents[1];
        }
        return "";
    }

    /**
     * 初始化单点登录服务端信息
     *
     * @param file
     * @return
     */
    public static void initBaseConfig(String file) {
        Properties pro = loadProperties(file);
        SSOConstants.BC_SSO_ADDRESS = pro.getProperty("BC_SSO_ADDRESS");
        System.out.println("init SSOConstants.BC_SSO_ADDRESS  is "
                + SSOConstants.BC_SSO_ADDRESS);

        SSOConstants.BC_CHECK_ADDRESS = pro.getProperty("BC_CHECK_ADDRESS");
        System.out.println("init SSOConstants.BC_CHECK_ADDRESS  is "
                + SSOConstants.BC_CHECK_ADDRESS);

        SSOConstants.BC_CHECK_METHOD = pro.getProperty("BC_CHECK_METHOD");
        System.out.println("init SSOConstants.BC_CHECK_METHOD  is "
                + SSOConstants.BC_CHECK_METHOD);

        SSOConstants.BC_LOGOUT_ADDRESS = pro.getProperty("BC_LOGOUT_ADDRESS");
        System.out.println("init SSOConstants.BC_LOGOUT_ADDRESS  is "
                + SSOConstants.BC_LOGOUT_ADDRESS);

        SSOConstants.BC_LOGOUT_METHOD = pro.getProperty("BC_LOGOUT_METHOD");
        System.out.println("init SSOConstants.BC_LOGOUT_METHOD  is "
                + SSOConstants.BC_LOGOUT_METHOD);

    }

    /**
     * 初始化介入系统相关配置信息
     *
     * @param path
     */
    public static void initAppConfig(String path) {

        Properties pro = loadProperties(path);
        SSOConstants.AC_APP_KEY = pro.getProperty("AC_APP_KEY");
        System.out.println("init SSOConstants.AC_APP_KEY  is "
                + SSOConstants.AC_APP_KEY);
        SSOConstants.AC_FAILURE = pro.getProperty("AC_FAILURE");
        System.out.println("init SSOConstants.AC_FAILURE  is "
                + SSOConstants.AC_FAILURE);

        Object obj = pro.getProperty("AC_CANACCESS");
        if (obj != null) {
            String urls = (String) obj;
            StringTokenizer st = new StringTokenizer(urls, ",");
            while (st.hasMoreTokens()) {
                String url = st.nextToken();
                SSOConstants.URLSet.add(url);
            }
        }

    }

    private static Properties loadProperties(String file) {
        InputStream in = null;
        ClassLoader classLoader = null;
        Properties properties = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
            in = classLoader.getResourceAsStream(file);
            properties = new Properties();
            properties.load(in);
        } catch (Exception e) {
            System.out.println("加载sso属性文件，" + file + "读取配置文件失败!");
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String checkOnLineNew(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
