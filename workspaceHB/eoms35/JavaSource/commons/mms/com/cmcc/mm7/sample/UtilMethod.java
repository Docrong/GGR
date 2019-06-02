package com.cmcc.mm7.sample;

public class UtilMethod {
	/**
	 * �ַ����ת�� ISO to GB2312
	 * 
	 * @param para
	 * @return
	 */
	public static String getPageString(String para) {
		String reStr = "";
		try {
			reStr = new String(para.getBytes("ISO-8859-1"), "GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return reStr;
		}
	}

	/**
	 * ����ת�����ַ�
	 * 
	 * @param s
	 * @return
	 */
	public static String nullObject2String(Object s) {
		String str = "";
		try {
			str = s.toString();
		} catch (Exception e) {
			str = "";
		}
		return str;
	}

	/**
	 * ����ת�����ַ��ն���ת����ָ�����ַ�
	 * 
	 * @param s
	 * @param chr
	 * @return
	 */
	public static String nullObject2String(Object s, String chr) {
		String str = chr;
		try {
			str = s.toString();
		} catch (Exception e) {
			str = chr;
		}
		return str;
	}

	/**
	 * ����ת����Ϊ�������
	 * 
	 * @param s
	 * @return
	 */
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

	public static long nullObject2long(Object s) {
		String str = "";
		long i = 0;
		try {
			str = UtilMethod.nullObject2String(s, "0");
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	/**
	 * ����ת����Ϊ������ݣ��ն��󣨻����޷�ת��Ϊ������ݵĶ���ת����Ϊָ�����������
	 * 
	 * @param s
	 * @param in
	 * @return
	 */

	public static int nullObject2int(Object s, int in) {
		String str = "";
		int i = in;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = in;
		}
		return i;
	}

	public static String null2String(String s) {
		return s == null ? "" : s;
	}

	/**
	 * ��ȡһ��double���������ֵ�������
	 * 
	 * @param values
	 * @return
	 */
//	public static double getMaxValue(double[] values) {
//		double value = 0;
//		for (double i : values) {
//			if (value < i)
//				value = i;
//		}
//		return value;
//	}

	/**
	 * ��ȡһ��double�����������Сֵ
	 * 
	 * @param values
	 * @return
	 */
	public static double getMinValue(double[] values) {
		double value = values.length > 0 ? values[0] : 0;
		for (int i = 1; i < values.length; i++) {
			if (value > values[i])
				value = values[i];
		}
		return value;
	}

	/**
	 * �������ַ�ת��ΪUTF-8����
	 * 
	 * @param strArray
	 * @return
	 */
	public static String encodeUtf8(String strArray) {
		String[] as = new String[strArray.length()];
		String sUTF8 = "";
		for (int i = 0; i < strArray.length(); i++) {
			as[i] = Integer.toHexString(strArray.charAt(i) & 0xffff);
			sUTF8 = sUTF8 + "\\u" + as[i];

		}
		return sUTF8;
	}

	/**
	 * ��UTF-8����ת��Ϊ����
	 * 
	 * @param strArray
	 * @return
	 */

	public static String decodeUtf8(String strArray) {
		String str = strArray.replaceAll("\\\\u", ",");
		String[] as = str.split(",");
		StringBuffer chinese = new StringBuffer(as.length - 1);
		for (int i = 1; i < as.length; i++) {
			chinese = chinese.append((char) Integer.parseInt(as[i], 16));
		}
		return chinese.toString();

	}

	public static String encodeAscII(String strArray) {
		byte[] bytes = strArray.getBytes();
		String[] as = new String[bytes.length];
		String asc = "";
		for (int i = 0; i < bytes.length; i++) {
			as[i] = Integer.toHexString(bytes[i] & 0xffff);
			asc = asc + as[i];
		}
		return asc;
	}

	public static String toUtf8(String strArray) {
		char[] arrSour = strArray.toCharArray();
		String sUTF8 = "";
		for (int i = 0; i < arrSour.length; i++) {
			int chNum = (int) arrSour[i];
			chNum = (chNum < 0) ? chNum + 65536 : chNum;
			sUTF8 += (chNum <= 128) ? "" + arrSour[i] : "&#" + chNum + ";";
		}
		return sUTF8;
	}

	/**
	 * ����ͳ��ʱ,�ں�������ƹ�ʱ,Ϊ�˱�֤��ʾЧ��,��ÿ���ֺ������һ��س���
	 * 
	 * @param str
	 * @return
	 */
	public static String transString(String str) {
		if (str.length() < 4) {
			return str;
		}
		StringBuffer strBuffer = new StringBuffer("");
		for (int i = 0; i < str.length(); i++) {
			if (i != 0) {
				strBuffer.append("\n");
			}
			strBuffer.append(str.charAt(i));
		}
		return strBuffer.toString();
	}
	/**
	 * ������
	 * 
	 * @param args
	 */
	/*
	 * public static void main(String[] args){ String str="����һ���й���";
	 * System.out.println(UtilMethod.transString(str)); }
	 */
}
