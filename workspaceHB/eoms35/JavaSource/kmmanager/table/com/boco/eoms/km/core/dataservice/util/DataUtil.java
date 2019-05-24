package com.boco.eoms.km.core.dataservice.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {

	public static int compareFloat(float aFloat, float bFloat, int precision) {
		if (aFloat == bFloat)
			return 0;
		if (aFloat > bFloat)
			return 1;
		return -1;
	}

	public static int compareDouble(double aDouble, double bDouble,
			int precision) {
		if (aDouble == bDouble)
			return 0;
		if (aDouble > bDouble)
			return 1;
		return -1;
	}

	public static double addDouble(double aDouble, double bDouble, int precision) {
		BigDecimal aBig = new BigDecimal(aDouble);
		BigDecimal bBig = new BigDecimal(bDouble);
		return aBig.add(bBig).setScale(precision, 4).doubleValue();
	}

	public static double addDouble(double aDouble, double bDouble) {
		BigDecimal aBig = new BigDecimal(aDouble);
		BigDecimal bBig = new BigDecimal(bDouble);
		return aBig.add(bBig).doubleValue();
	}

	public static double subDouble(double aDouble, double bDouble, int precision) {
		BigDecimal aBig = new BigDecimal(aDouble);
		BigDecimal bBig = new BigDecimal(bDouble);
		return aBig.subtract(bBig).setScale(precision, 4).doubleValue();
	}

	public static double subDouble(double aDouble, double bDouble) {
		BigDecimal aBig = new BigDecimal(aDouble);
		BigDecimal bBig = new BigDecimal(bDouble);
		return aBig.subtract(bBig).doubleValue();
	}

	public static boolean likeString(String aString, String bString) {
		return false;
	}

	public static BigDecimal setScale(BigDecimal aBig, int scale) {
		if (aBig.scale() < scale)
			return aBig.setScale(scale);
		BigDecimal b2 = aBig.divide(BigDecimal.valueOf(1L), scale, 4);
		return b2;
	}

	public static double setScale(double number, int precision) {
		BigDecimal b = new BigDecimal(number).setScale(precision, 4);
		return b.doubleValue();
	}

	public static long addDate(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.add(5, amount);
		return c.getTime().getTime();
	}

	public static long subDate(Date date, int amount) {
		return addDate(date, -amount);
	}

	public static String native2unicode(String s) {
		if ((s == null) || (s.length() == 0))
			return null;

		byte[] buffer = new byte[s.length()];
		for (int i = 0; i < s.length(); ++i) {
			buffer[i] = (byte) s.charAt(i);
		}
		return new String(buffer);
	}

	public static String unicode2native(String s) {
		if ((s == null) || (s.length() == 0))
			return null;

		char[] buffer = new char[s.length() * 2];

		int j = 0;
		for (int i = 0; i < s.length(); ++i)
			if (s.charAt(i) >= 256) {
				char c = s.charAt(i);
				byte[] buf = new Character(c).toString().getBytes();
				buffer[(j++)] = (char) buf[0];
				buffer[(j++)] = (char) buf[1];
			} else {
				buffer[(j++)] = s.charAt(i);
			}

		return new String(buffer, 0, j);
	}

	public static int nativeLength(String s) {
		if ((s == null) || (s.length() == 0))
			return 0;

		int length = 0;
		for (int i = 0; i < s.length(); ++i){
			if (s.charAt(i) >= 256)
				length += 2;
			else
				++length;
		}
		return length;
	}

	public static String addSpace(String str, int aColLength) {
		if ((str == null) || (str.length() == 0))
			return null;

		int length = nativeLength(str);

		int spaceCount = aColLength - length;
		StringBuffer strBuffer = new StringBuffer(str);
		for (int i = 0; i < spaceCount; ++i) {
			strBuffer.append(' ');
		}

		return strBuffer.toString();
	}
}