package com.boco.eoms.sheet.base.flowchar;

public class StaticMethod {
	public static int NODE_FINISH = 2;

	public static long nullObject2long(Object s) {
		String str = "";
		long i = 0;
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	public static long nullString2long(String s) {
		long i = 0;
		try {
			i = Long.parseLong(s);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	public static int nullObject2int(Object s) {
		String str = "";
		int i = 0;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	public static int nullString2int(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

}
