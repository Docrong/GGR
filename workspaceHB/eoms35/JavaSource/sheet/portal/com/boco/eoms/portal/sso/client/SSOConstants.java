package com.boco.eoms.portal.sso.client;

import java.util.HashSet;
import java.util.Set;

public class SSOConstants {
	public static final String SSO_TOKEN = "ticket";
	public static final String SSO_APP_KEY = "APP_KEY";
	public static final String SSO_VISITIP = "IP";
	public static final String SSO_ACCOUNT = "sso_account";
	
	public static final String REQUEST_ATTRIBUTE_MESSAGE_KEY = "sysmsg";
	
	public static final String UNAUTHORIZED_IP_ACCESS = "UNAUTHORIZED_IP_ACCESS";
	public static final String OK = "OK";//
	public static final String USER_UNLOGIN = "USER_UNLOGIN";//
	public static final String SYS_ERROR =  "SYS_ERROR";//
	public static final String SYS_ERROR_DB = "SYS_ERROR_DB";//
	public static final String SYS_ERROR_NO_PARAMETER = "SYS_ERROR_NO_PARAMETER";//
	public static final String SYS_ERROR_CACHE = "SYS_ERROR_CACHE";//
	
	
	public static String BC_SSO_ADDRESS = "http://10.32.1.187:10000/iamssoweb"; 
	public static String BC_CHECK_ADDRESS = "http://10.32.1.187:10000/iamssoweb/check.do"; 
	public static String BC_CHECK_METHOD = "checkToken";
	public static String BC_LOGOUT_ADDRESS = "http://10.32.1.187:10000/iamssoweb/check.do";
	public static String BC_LOGOUT_METHOD = "logout";
	

	public static String AC_APP_KEY = "402881d52aa2eec6012aa2eec6e70000";
	public static String AC_FAILURE = "";
		
	public static Set URLSet=new HashSet();

}
