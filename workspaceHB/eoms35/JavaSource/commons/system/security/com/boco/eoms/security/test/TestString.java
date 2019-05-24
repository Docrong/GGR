package com.boco.eoms.security.test;

import java.util.ArrayList;
import java.util.Vector;

import antlr.collections.List;

/**
 * <p>
 * Title: Authentication and Authorization System via LDAP
 * </p>
 * <p>
 * Description: Authentication and Authorization System via LDAP
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: BOCO
 * </p>
 * 
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class TestString {
	public TestString() {

		Vector a = new Vector();
		a.add("z");
		a.add("y");
		a.add("x");
		a.add("w");

		//

		System.out.println(a);

	}

	public static void main(String[] args) {
		// System.out.println(System.getProperties());
//		Vector a = new Vector();
//		a.add("z");
//		a.add("y");
//		a.add("x");
//		a.add("w");
//		Vector b = new Vector();
//		b.add("z");
//		for (int i = 0; i < a.size(); i++) {
//			String flag = "0";
//			for (int j = 0; j < b.size(); j++) {
//
//				if (b.get(j).equals(a.get(i))) {
//					flag = "1";
//					break;
//				}
//			}
//			if (flag.equals("0")) {
//				b.add(a.get(i));
//			}
//		}
		String userString ="CN=用户名,OU=国脉,OU=合作伙伴,OU=部门树,OU=nms_users,DC=testnms,DC=fj,DC=cm";
		userString =userString.substring(userString.indexOf(",")+1, userString.indexOf("OU=nms_users"));
		int cn=userString.indexOf(",");;
		
		// TestString testString1 = new TestString();
		//
		// StringBuffer sb = new
		// StringBuffer("uid=wzw,ou=users,dc=boco,dc=com");
		// int b = sb.indexOf("=");
		// System.out.println(sb.indexOf("="));
		// int e = sb.indexOf(",");
		// System.out.println(sb.indexOf(","));
		//
		// System.out.println(sb.substring(b+1, e));
		// String a = "123456";
		//
		// for (int i=0;i<a.length();i++){
		// System.out.println("InCircle:"+a.charAt(i));
		// }
		//
		// can't use "+" with char array for println()
		// System.out.println(a.toCharArray());
		// System.out.println(a.toCharArray());
		// System.out.println(a.toCharArray());
		// System.out.println(a.toCharArray().toString());

	}

}