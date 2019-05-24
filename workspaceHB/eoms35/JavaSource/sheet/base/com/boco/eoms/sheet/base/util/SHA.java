package com.boco.eoms.sheet.base.util;

import java.security.MessageDigest;

//import com.ibm.ws.util.Base64;


/**
 * SHA加密
 * @author IBM
 *
 */
public class SHA {
	/**
	 * SHA加密算法
	 * @param str
	 * @return
	 */
	public String getKey(String str) throws Exception{
//		MD5是16位,SHA是20位（这是两种报文摘要的算法）
//		MessageDigest md= MessageDigest.getInstance("MD5");
		MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
		messageDigest.update(str.getBytes());
//		String digestedPwdString = new String(messageDigest.digest());
		String shaStr = "";//new String(Base64.encode(messageDigest.digest()));
		System.out.println("SHA:" + shaStr);
		return shaStr;
	}
}
