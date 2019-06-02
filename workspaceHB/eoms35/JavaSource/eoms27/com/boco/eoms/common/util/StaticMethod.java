/**
 * <p>Title: eoms</p>
 * <p>Description: 静态方法类，如字符转换等</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 * @author dudajiang
 * @version 1.0
 * update dy dudajiang 2003-4-7 13:00
 */
package com.boco.eoms.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;

import org.apache.soap.Constants;
import org.apache.soap.Envelope;
import org.apache.soap.SOAPException;
import org.apache.soap.messaging.Message;
import org.apache.soap.transport.SOAPTransport;
import org.apache.soap.util.xml.XMLParserUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.boco.eoms.common.log.BocoLog;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class StaticMethod {

	static PropertyFile PROP = PropertyFile.getInstance();

	static String CHARSET_PAGE = PROP.getProperty(
			"database.format.Charset.Page_Charset").toString();

	static String CHARSET_DB = PROP.getProperty(
			"database.format.Charset.DB_Charset").toString();

	static String CHARSET_BEAN = PROP.getProperty(
			"database.format.Charset.FormBean_Charset").toString();

	public final static String CLASSPATH_FLAG = "classpath:";

	public StaticMethod() {
	}

	/**
	 * @see 获得是短信是否可用，根据boco.xml文件中的<SendSMsgAvailable>节点值。
	 * @return String 数据库的类型值（“true”,或“false”）。
	 */
	public static String getSendSMsgAvailable() {
		String strSendSMsgAvailable = "";
		try {
			strSendSMsgAvailable = PROP.getProperty("SendSMsgAvailable")
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return strSendSMsgAvailable;
		}
	}

	/**
	 * @see 获得数据库类型，根据boco.xml文件中的<DatabaseType>节点值。
	 * @return String 数据库的类型值（“oracle”,或“informix”）。
	 */
	public static String getDbType() {
		String dbType = "";
		try {
			dbType = PROP.getProperty("database.type").toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return dbType;
		}
	}

	/**
	 * @see 根据节点名，获得存在boco.xml文件中基础数据值。
	 * @see 比如：INTREGIONID：地区序号； STRREGIONCODE：地区编码； STRREGIONNAME：地区名称
	 * @return String。
	 */
	public static String getNodeName(String str) {
		String dbType = "";
		try {

			dbType = PROP.getProperty(str).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return dbType;
		}
	}

	/**
	 * 得到接口地址
	 * 
	 * @return String
	 */
	public static String getInterfaceAddr(String servId) {
		String dbType = "";
		try {
			PropertyFile prop = PropertyFile.getInstance();
			dbType = prop.getProperty(servId).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return dbType;
		}
	}

	public static String getDBProcedure(String str) {
		String sql = "";
		String dbType = StaticMethod.getDbType();
		if (dbType.equals(StaticVariable.ORACLE)) {
			sql = "call " + str;
		} else {
			str = str.substring(str.lastIndexOf(".") + 1);
			sql = "execute procedure " + str;
		}
		return sql;
	}

	public static String getDBProName(String str) {
		String sql = "";
		String dbType = StaticMethod.getDbType();
		if (dbType.equals(StaticVariable.ORACLE)) {
			sql = str;
		} else {
			str = str.substring(str.lastIndexOf(".") + 1);
			sql = str;
		}
		return sql;
	}

	/**
	 * @see 将中文格式转换成标准格式
	 * @see 例如：StaticMethod.getString("中文");
	 * @param para
	 *            String 中文字符串
	 * @return String para的标准格式的串
	 */
	public static String getString(String para) {
		String reStr = "";
		try {
			reStr = new String(para.getBytes("GB2312"), "ISO-8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return reStr;
		}
	}

	public static String getGBString(String para) {
		String reStr = "";
		try {
			reStr = new String(para.getBytes("ISO-8859-1"), "GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return reStr;
		}
	}

	// 得到一指定分隔符号分隔的vector
	// 如：Vector nn = StaticMethod.getVector("2003-4-5","-");
	public static Vector getVector(String string, String tchar) {
		StringTokenizer token = new StringTokenizer(string, tchar);
		Vector vector = new Vector();
		if (!string.trim().equals("")) {
			try {
				while (token.hasMoreElements()) {
					vector.add(token.nextElement().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vector;
	}

	public static ArrayList getArrayList(String str, int size) {
		ArrayList vec = new ArrayList();
		String temp = "";
		str = str.trim();
		try {
			while (str.length() > size) {
				temp = str;
				vec.add(temp);
				str = str.substring(0, str.length() - size);
			}
			if (!str.equals("")) {
				vec.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vec;
	}

	// 得到一指定分隔符号分隔的ArrayList
	public static ArrayList getArrayList(String string, String tchar) {
		StringTokenizer token = new StringTokenizer(string, tchar);
		ArrayList array = new ArrayList();
		if (!string.trim().equals("")) {
			try {
				while (token.hasMoreElements()) {
					array.add(token.nextElement().toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	/*
	 * @see 判断id值是否包含在数组中 @param id int id值 @param array 集合 数组 @return
	 *      boolean类型值。 @throws SQLException
	 */
	public static boolean fHasId(int id, ArrayList array) throws Exception {

		boolean ret = false;
		int i = 0;
		try {
			while (i < array.size() && ret == false) {
				if (id == StaticMethod.nullObject2int(array.get(i))) {
					ret = true;
				}
				i++;
			}
		} catch (Exception e) {
			BocoLog.error("StaticMethod.java", 0, "错误", e);
		}

		return ret;
	}

	// 得到两个集合的交集，返回一个新的集合
	public static ArrayList getArrayList(ArrayList array1, ArrayList array2) {

		ArrayList array = new ArrayList();
		try {
			for (int i = 0; i < array1.size(); i++) {
				int temp = StaticMethod.nullObject2int(array1.get(i));
				if (StaticMethod.fHasId(temp, array2) == true) {
					array.add(new Integer(temp));
				}
			}
		} catch (Exception e) {
			BocoLog.error("StaticMethod.java", 0, "错误", e);
		}

		return array;
	}

	// 得到两个集合的叉集，返回一个新的集合，即：在array1中，不在array2中
	public static ArrayList getArrayList2(ArrayList array1, ArrayList array2) {

		ArrayList array = new ArrayList();
		int size1 = array1.size();
		int size2 = array2.size();
		if ((size2 == 0) || (size1 == 0)) {
			array = array1;
		} else {
			try {
				for (int i = 0; i < array1.size(); i++) {
					int temp = StaticMethod.nullObject2int(array1.get(i));
					if (StaticMethod.fHasId(temp, array2) == false) {
						array.add(new Integer(temp));
					}
				}
			} catch (Exception e) {
				BocoLog.error("StaticMethod.java", 0, "错误", e);
			}
		}

		return array;
	}

	// 字符转换函数
	// input: 对象
	// output:如果字符串为null,返回为空,否则返回该字符串
	public static String nullObject2String(Object s) {
		String str = "";
		try {
			str = s.toString();
		} catch (Exception e) {
			str = "";
		}
		return str;
	}

	public static String nullObject2String(Object s, String chr) {
		String str = chr;
		try {
			str = s.toString();
		} catch (Exception e) {
			str = chr;
		}
		return str;
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

	public static Timestamp nullObject2Timestamp(Object s) {
		Timestamp str = null;
		try {
			str = Timestamp.valueOf(s.toString());
		} catch (Exception e) {
			str = null;
		}
		return str;
	}

	// 字符转换函数
	// input: 字符串
	// output:如果字符串为null,返回为空,否则返回该字符串
	public static String null2String(String s) {
		return s == null ? "" : s;
	}

	public static ArrayList getSubList(ArrayList list1, ArrayList list2) {
		ArrayList list = new ArrayList();
		int j = list1.size();
		int k = list2.size();
		if ((j > 0) && (k > 0)) {
			Collections.sort(list1);
			Collections.sort(list2);
			for (int i = 0; i < j; i++) {
				String temp = StaticMethod.nullObject2String(list1.get(i));
				for (int l = 0; l < k; l++) {
					String temp2 = StaticMethod.nullObject2String(list2.get(l));
					if (temp.equals(temp2)) {
						list.add(temp);
					}
				}
			}
		}
		return list;
	}

	public static Vector getSubVec(Vector list1, Vector list2) {
		Vector list = new Vector();
		int j = list1.size();
		int k = list2.size();
		if ((j > 0) && (k > 0)) {
			Collections.sort(list1);
			Collections.sort(list2);
			for (int i = 0; i < j; i++) {
				String temp = StaticMethod.nullObject2String(list1.get(i));
				for (int l = 0; l < k; l++) {
					String temp2 = StaticMethod.nullObject2String(list2.get(l));
					if (temp.equals(temp2)) {
						list.add(temp);
					}
				}
			}
		}
		return list;
	}

	// 数据库字符的转换函数
	public static String dbNull2String(String s) {
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		if (CHARSET_PAGE.equals(CHARSET_DB)) {
			return reStr;
		}
		try {
			reStr = new String(reStr.getBytes(CHARSET_DB), CHARSET_PAGE);
		} catch (Exception e) {
			BocoLog.error(e, 250, "转化数据库字符错误!");
		}
		return reStr;
	}

	// 数据库字符的反转换函数
	public static String dbNStrRev(String s) {
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		if (CHARSET_PAGE.equals(CHARSET_DB)) {
			return reStr;
		}
		try {
			reStr = new String(reStr.getBytes(CHARSET_PAGE), CHARSET_DB);
		} catch (Exception e) {
			BocoLog.error(e, 250, "转化数据库字符错误!");
		}
		return reStr;
	}

	// 页面间传参字符的反转换函数
	public static String PageNStrRev(String s) {

		String charPage = "";
		String charDB = "";
		String charForm = "";
		String reStr = "";

		try {
			PropertyFile prop = PropertyFile.getInstance();
			charPage = prop.getProperty("database.format.Charset.Page_Charset")
					.toString();
			charDB = prop.getProperty("database.format.Charset.DB_Charset")
					.toString();
			charForm = prop.getProperty(
					"database.format.Charset.FormBean_Charset").toString();

			if (charPage.equals(StaticVariable.GB2312)
					&& charDB.equals(StaticVariable.GB2312)
					&& charForm.equals(StaticVariable.GB2312)) {
				reStr = s == null ? "" : s;
			}

			if (charPage.equals(StaticVariable.GB2312)
					&& charDB.equals(StaticVariable.ISO)) {
				// charForm.equals(StaticVariable.GB2312)) {
				reStr = s == null ? "" : s;
				reStr = reStr.trim();

				reStr = new String(reStr.getBytes(charDB), charPage);
			}
		}

		catch (Exception e) {
			BocoLog.error(e, 290, "数据库字符的反转换错误!");
		}

		return reStr;
	}

	public static synchronized String strFromBeanToPage(String s) {
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		if (CHARSET_BEAN.equals(CHARSET_PAGE)) {
			return reStr;
		}
		try {
			reStr = new String(reStr.getBytes(CHARSET_BEAN), CHARSET_PAGE);
		} catch (Exception e) {
			BocoLog.error(e, 250, "转化数据库字符错误!");
		}
		return reStr;
	}

	public static synchronized String strFromDBToPage(String s) {
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		if (CHARSET_PAGE.equals(CHARSET_DB)) {
			return reStr;
		}
		try {
			reStr = new String(reStr.getBytes(CHARSET_DB), CHARSET_PAGE);
		} catch (Exception e) {
			BocoLog.error(e, 250, "转化数据库字符错误!");
		}
		return reStr;
	}

	public static synchronized String strReverse(String s, String source,
			String obj) {
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		try {
			reStr = new String(reStr.getBytes(source), obj);
		} catch (Exception e) {
			BocoLog.error(e, 250, "转化字符错误!");
		}
		return reStr;
	}

	public static synchronized String strFromPageToDB(String s) {
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		if (CHARSET_PAGE.equals(CHARSET_DB)) {
			return reStr;
		}
		try {
			reStr = new String(reStr.getBytes(CHARSET_PAGE), CHARSET_DB);
		} catch (Exception e) {
			BocoLog.error(e, 250, "转化数据库字符错误!");
		}
		return reStr;
	}

	public static double null2double(double s) {
		if (String.valueOf(s) == null) {
			return 0.0;
		} else {
			return s;
		}
	}

	// 字符转换函数
	// input: 字符串1,字符串2
	// output:如果字符串1为null,返回为字符串2,否则返回该字符串
	public static String null2String(String s, String s1) {
		return s == null ? s1 : s;
	}

	// 字符转换函数
	// input: 字符串1
	// output:如果字符串1为null或者不能转换成整型,返回为0
	public static int null2int(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	// 字符转换函数
	// input: 字符串1
	// output:如果字符串1为null或者不能转换成整型,返回为0
	public static long null2Long(String s) {
		long i = 0;
		try {
			i = Long.parseLong(s);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}

	// 字符转换函数
	// input: 字符串1,整型2
	// output:如果字符串1为null或者不能转换成整型,返回为整型2
	public static int null2int(String s, int in) {
		int i = in;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			i = in;
		}
		return i;
	}

	public static ArrayList TokenizerString(String str, String dim) {
		return TokenizerString(str, dim, false);
	}

	/***************************************************************************
	 * 将输入的字符串str按照分割符dim分割成字符串数组并返回ArrayList字符串数组**** If the returndim flag is
	 * true, then the dim characters are also returned as tokens. Each delimiter
	 * is returned as a string of length one. If the flag is false, the
	 * delimiter characters are skipped and only serve as separators between
	 * tokens.
	 **************************************************************************/
	public static ArrayList TokenizerString(String str, String dim,
			boolean returndim) {
		str = null2String(str);
		dim = null2String(dim);
		ArrayList strlist = new ArrayList();
		StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);
		while (strtoken.hasMoreTokens()) {
			strlist.add(strtoken.nextToken());
		}
		return strlist;
	}

	/***************************************************************************
	 * 类似上面的方法,将输入的字符串str按照分割符dim分割成字符串数组,**** 并返回定长字符串数组
	 **************************************************************************/

	public static String[] TokenizerString2(String str, String dim) {
		return TokenizerString2(str, dim, false);
	}

	public static String[] TokenizerString2(String str, String dim,
			boolean returndim) {
		ArrayList strlist = TokenizerString(str, dim, returndim);
		int strcount = strlist.size();
		String[] strarray = new String[strcount];
		for (int i = 0; i < strcount; i++) {
			strarray[i] = (String) strlist.get(i);
		}
		return strarray;
	}

	public static String add0(int v, int l) {
		long lv = (long) Math.pow(10, l);
		return String.valueOf(lv + v).substring(1);
	}

	public static String getCookie(HttpServletRequest req, String key) {
		Cookie[] cookies = req.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(key)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}

	public static void setCookie(HttpServletResponse res, String key,
			String value, int age, String domain) {

		Cookie newCookie = new Cookie(key, value);
		newCookie.setMaxAge(age);
		newCookie.setDomain(domain);
		newCookie.setPath("/");

		res.addCookie(newCookie);

	}

	public static void setCookie(HttpServletResponse res, String key,
			String value, int age) {

		Cookie newCookie = new Cookie(key, value);
		newCookie.setMaxAge(age);
		newCookie.setPath("/");
		res.addCookie(newCookie);

	}

	public static void setCookie(HttpServletResponse res, String key,
			String value) {
		setCookie(res, key, value, -1);
	}

	public static String fromScreen(String s) {
		if (s == null) {
			s = null2String(s);
		}
		s = fromBaseEncoding(s);
		s = toHtml(s);
		return s;
	}

	public static String toScreen(String s) {
		if (s == null) {
			s = null2String(s);
		}
		s = toBaseEncoding(s);
		s = fromHtml(s);
		return s;
	}

	public static String toScreenToEdit(String s, int languageid) {
		if (s == null) {
			s = null2String(s).trim();
		}
		s = toBaseEncoding(s);
		s = fromHtmlToEdit(s);
		return s;
	}

	public static String toBaseEncoding(String s) {
		try {
			byte[] target_byte = s.getBytes("GB2312");
			return new String(target_byte, "ISO-8859-1");
		} catch (Exception ex) {
			return s;
		}
	}

	public static String fromBaseEncoding(String s) {
		try {
			byte[] target_byte = s.getBytes("ISO-8859-1");
			return new String(target_byte, "GB212");
		} catch (Exception ex) {
			return s;
		}
	}

	public static String toHtml(String s) {
		char c[] = s.toCharArray();
		char ch;
		int i = 0;
		StringBuffer buf = new StringBuffer();

		while (i < c.length) {
			ch = c[i++];

			if (ch == '"') {
				buf.append("&quot;");
			} else if (ch == '\'') {
				buf.append("\\'");
			} else if (ch == '&') {
				buf.append("&amp;");
			} else if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else if (ch == '\n') {
				buf.append("<br>");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	public static String fromHtml(String s) {
		return StringReplace(s, "\\'", "\'");

	}

	public static String fromHtmlToEdit(String s) {
		return StringReplace(StringReplace(s, "<br>", ""), "\\'", "\'");
	}

	public static boolean contains(Object a[], Object s) {
		if (a == null || s == null) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null && a[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static String extract(String c, String begin_tag, String end_tag) {
		int begin = begin_tag == null ? 0 : c.indexOf(begin_tag);
		int len = begin_tag == null ? 0 : begin_tag.length();
		int end = end_tag == null ? c.length() : c
				.indexOf(end_tag, begin + len);

		if (begin == -1) {
			begin = 0;
			len = 0;
		}
		if (end == -1) {
			end = c.length();
		}
		return c.substring(begin + len, end);
	}

	public static String remove(String s1, String s2) {
		int i = s1.indexOf(s2);
		int l = s2.length();
		if (i != -1) {
			return s1.substring(0, i) + s1.substring(i + l);
		}
		return s1;
	}

	// --------------------------------------------------------------------------
	public static String replaceChar(String s, char c1, char c2) {
		if (s == null) {
			return s;
		}

		char buf[] = s.toCharArray();
		for (int i = 0; i < buf.length; i++) {
			if (buf[i] == c1) {
				buf[i] = c2;
			}
		}
		return String.valueOf(buf);
	}

	public static boolean isEmail(String s) {
		int pos = 0;
		pos = s.indexOf("@");
		if (pos == -1) {
			return false;
		}
		return true;
	}

	public static void swap(Object a[], int i, int j) {
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static String StringReplace(String sou, String s1, String s2) {
		int idx = sou.indexOf(s1);
		if (idx < 0) {
			return sou;
		}
		return StringReplace(sou.substring(0, idx) + s2
				+ sou.substring(idx + s1.length()), s1, s2);
	}

	public static String replaceRange(String sentence, String oStart,
			String oEnd, String rWord, boolean matchCase) {
		int sIndex = -1;
		int eIndex = -1;
		if (matchCase) {
			sIndex = sentence.indexOf(oStart);
		} else {
			sIndex = sentence.toLowerCase().indexOf(oStart.toLowerCase());
		}
		if (sIndex == -1 || sentence == null || oStart == null || oEnd == null
				|| rWord == null) {
			return sentence;
		} else {
			if (matchCase) {
				eIndex = sentence.indexOf(oEnd, sIndex);
			} else {
				eIndex = sentence.toLowerCase().indexOf(oEnd.toLowerCase(),
						sIndex);
			}
			String newStr = null;
			if (eIndex > -1) {
				newStr = sentence.substring(0, sIndex) + rWord
						+ sentence.substring(eIndex + oEnd.length());
			} else {
				newStr = sentence.substring(0, sIndex) + rWord
						+ sentence.substring(sIndex + oStart.length());
			}
			return replaceRange(newStr, oStart, oEnd, rWord, matchCase);
		}
	}

	// add by wangwei

	public static int getIntValue(String v) {
		return getIntValue(v, -1);
	}

	/** ***将给出的字符串v转换成整形值返回，如果例外则返回预给值def*********** */
	public static int getIntValue(String v, int def) {
		try {
			return Integer.parseInt(v);
		} catch (Exception ex) {
			return def;
		}
	}

	/** ***将给出的字符串v转换成浮点值返回，如果例外则返回预给值-1**************** */
	public static float getFloatValue(String v) {
		return getFloatValue(v, -1);
	}

	/** ***将给出的字符串v转换成浮点值返回，如果例外则返回预给值def*********** */
	public static float getFloatValue(String v, float def) {
		try {
			return Float.parseFloat(v);
		} catch (Exception ex) {
			return def;
		}
	}

	public static String getFileidIn(String in) {
		return in;
	}

	public static String getFileidOut(String out) {
		return out;
	}

	public static String makeNavbar(int current, int total, int per_page,
			String action) {
		int begin = 1;
		int border = 100;
		String prevLink = "";
		String nextLink = "";
		String rslt = "";

		String strNext = "Next";
		String strPrev = "Previous";
		String strStart = "";
		if (action.indexOf("?") < 0) {
			strStart = "?start=";
		} else {
			strStart = "&start=";

		}
		Hashtable ht = new Hashtable();
		ht.put("action", action);
		begin = current + 1;
		int j = 0;
		while (j * border < begin) {
			j++;

		}
		begin = (((j - 1) * border) + 1);

		if (current + 1 > border) {
			prevLink = "<a href=" + action + strStart
					+ Math.max(1, begin - border) + ">[ " + strPrev + " "
					+ border + " ]</a>&nbsp;";

		}
		while (begin < (j * border) && begin < total + 1) {
			ht.put("from", String.valueOf(begin));
			ht.put("to", String.valueOf(Math.min(total, begin + per_page - 1)));

			if (current + 1 >= begin && current + 1 <= begin + per_page - 1) {
				rslt += fillValuesToString("[$from - $to]&nbsp;", ht);
			} else {
				rslt += fillValuesToString("<a href='$action" + strStart
						+ "$from'>[$from - $to]</a>&nbsp;", ht);
			}
			begin += per_page;
		}

		if (total >= begin) {
			nextLink = "&nbsp;<a href=" + action + strStart + begin + ">[ "
					+ strNext + " " + Math.min(border, total - begin + 1)
					+ " ]</a>";
		}
		return prevLink + rslt + nextLink;
	}

	public static String makeNavbarReverse(int current, int total,
			int per_page, String action) {
		int border = 100;
		String prevLink = "";
		String nextLink = "";
		String rslt = "";
		int begin = current + 1;
		Hashtable ht = new Hashtable();
		ht.put("action", action);
		String strNext = "Next";
		String strPrev = "Previous";
		String strStart = "";
		if (action.indexOf("?") < 0) {
			strStart = "?start=";
		} else {
			strStart = "&start=";

		}
		int j = 0;
		while (j * border < begin) {
			j++;
		}
		begin = ((j - 1) * border) + 1;
		if (begin > border) {
			prevLink = "<a href=" + action + strStart
					+ Math.max(1, begin - border) + ">[ " + strNext + " "
					+ border + " ]</a>&nbsp;";
		}
		current++;
		for (int i = 0; i < per_page && begin <= total; i++) {
			ht.put("from", String.valueOf(total - begin + 1));
			ht.put("to", String.valueOf(Math.max(1, total - begin + 1 + 1
					- per_page)));
			if (current >= begin && current <= begin + per_page - 1) {
				rslt += fillValuesToString("[$from - $to]&nbsp;", ht);
			} else {
				rslt += fillValuesToString(
						"<a href='$action" + strStart + String.valueOf(begin)
								+ "'>[ $from - $to ]</a>&nbsp;", ht);
			}
			begin += per_page;
		}
		if (total > begin) {
			nextLink = "&nbsp;<a href=" + action + strStart
					+ String.valueOf(begin) + ">[ " + strPrev + " "
					+ Math.min(total - begin + 1, 100) + " ]</a>";
		}
		return prevLink + rslt + nextLink;
	}

	public static String fillValuesToString(String str, Hashtable ht) {
		char VARIABLE_PREFIX = '$';
		char TERMINATOR = '\\';

		if (str == null || str.length() == 0 || ht == null) {
			return str;
		}

		char s[] = str.toCharArray();
		char ch, i = 0;
		String vname;
		StringBuffer buf = new StringBuffer();

		ch = s[i];
		while (true) {
			if (ch == VARIABLE_PREFIX) {
				vname = "";
				if (++i < s.length) {
					ch = s[i];
				} else {
					break;
				}
				while (true) {
					if (ch != '_' && ch != '-'
							&& !Character.isLetterOrDigit(ch)) {
						break;
					}
					vname += ch;
					if (++i < s.length) {
						ch = s[i];
					} else {
						break;
					}
				}

				if (vname.length() != 0) {
					String vval = (String) ht.get(vname);
					if (vval != null) {
						buf.append(vval);
					}
				}
				if (vname.length() != 0 && ch == VARIABLE_PREFIX) {
					continue;
				}
				if (ch == TERMINATOR) {
					if (++i < s.length) {
						ch = s[i];
					} else {
						break;
					}
					continue;
				}
				if (i >= s.length) {
					break;
				}
			}

			buf.append(ch);
			if (++i < s.length) {
				ch = s[i];
			} else {
				break;
			}
		}
		return buf.toString();
	}

	public static char getSeparator() {
		return 2;
	}

	public static String addTime(String time1, String time2) {
		if (time1.equals("") || time2.equals("")) {
			return "00:00";
		} else {
			ArrayList timearray1 = TokenizerString(time1, ":");
			ArrayList timearray2 = TokenizerString(time2, ":");
			int hour1;
			int hour2;
			int min1;
			int min2;
			int hour;
			int min;
			hour1 = getIntValue((String) timearray1.get(0));
			min1 = getIntValue((String) timearray1.get(1));
			hour2 = getIntValue((String) timearray2.get(0));
			min2 = getIntValue((String) timearray2.get(1));
			if ((min1 + min2) >= 60) {
				hour = hour1 + hour2 + 1;
				min = min1 + min2 - 60;
			} else {
				hour = hour1 + hour2;
				min = min1 + min2;
			}
			if (hour < 10) {
				if (min < 10) {
					return "0" + hour + ":" + "0" + min;
				} else {
					return "0" + hour + ":" + "" + min;
				}
			} else {
				if (min < 10) {
					return "" + hour + ":" + "0" + min;
				} else {
					return "" + hour + ":" + "" + min;
				}
			}
		}
	}

	public static String subTime(String time1, String time2) {
		if (time1.equals("") || time2.equals("")) {
			return "00:00";
		} else {
			ArrayList timearray1 = TokenizerString(time1, ":");
			ArrayList timearray2 = TokenizerString(time2, ":");
			int hour1;
			int hour2;
			int min1;
			int min2;
			int hour;
			int min;
			hour1 = getIntValue((String) timearray1.get(0));
			min1 = getIntValue((String) timearray1.get(1));
			hour2 = getIntValue((String) timearray2.get(0));
			min2 = getIntValue((String) timearray2.get(1));
			if ((min1 - min2) < 0) {
				hour = hour1 - hour2 - 1;
				min = min1 - min2 + 60;
			} else {
				hour = hour1 - hour2;
				min = min1 - min2;
			}
			if (hour < 10) {
				if (min < 10) {
					return "0" + hour + ":" + "0" + min;
				} else {
					return "0" + hour + ":" + "" + min;
				}
			} else {
				if (min < 10) {
					return "" + hour + ":" + "0" + min;
				} else {
					return "" + hour + ":" + "" + min;
				}
			}
		}
	}

	public static String getFloatStr(String str, int dimlen) { // 获得每dimlen位的逗号
		int dicimalindex = str.indexOf(".");
		String decimalstr = "";
		if (dicimalindex != -1) {
			decimalstr = extract(str, ".", null);
		}
		String intstr = extract(str, null, ".");
		if (intstr.length() < (dimlen + 1)) {
			return str;
		}
		String beginstr = "";
		int thebeginlen = intstr.length() % dimlen;
		beginstr = intstr.substring(0, thebeginlen);
		intstr = intstr.substring(thebeginlen);
		int intstrcount = intstr.length() / dimlen;

		for (int i = 0; i < intstrcount; i++) {
			if (beginstr.equals("") || beginstr.equals("-")) {
				beginstr += intstr.substring(0, dimlen);
				intstr = intstr.substring(dimlen);
			} else {
				beginstr += "," + intstr.substring(0, dimlen);
				intstr = intstr.substring(dimlen);
			}
		}
		if (dicimalindex != -1) {
			return beginstr + "." + decimalstr;
		} else {
			return beginstr;
		}
	}

	/**
	 * 
	 * @param tel
	 * @param msg
	 * @return
	 */
	public static boolean sendSMsg(String tel, String msg) {

		try {
			ActiveXComponent app = new ActiveXComponent(
					"BCFwdSmsCimd.CimdCenter");
			Dispatch.call(app, "Sendsmsmsg", tel, msg);
			// System.out.println("msg_return:" + sResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * send soap xml message
	 * 
	 * @param url
	 *            soap service's address and
	 *            port,example:http://localhost:8080/eoms_j2ee/servlet/messagerouter
	 * @param msg
	 *            message body in xml format,it is a string.
	 * @return
	 */
	public static String sendStrMessage(String url, String msg) {
		String sResult = "";
		try {
			// get the envelope to send
			System.out.println(getLocalString() + "向客服发送消息（" + url + "）:");
			System.out.println(msg);
			StringReader sReader = new StringReader(msg);
			DocumentBuilder xdb = XMLParserUtils.getXMLDocBuilder();
			Document doc = xdb.parse(new InputSource(sReader));
			if (doc == null) {
				throw new SOAPException(Constants.FAULT_CODE_CLIENT,
						"parsing error");
			}
			Envelope msgEnv = Envelope.unmarshall(doc.getDocumentElement());

			// send the message
			Message msgObj = new Message();
			msgObj.send(new URL(url), "urn:this-is-the-action-uri", msgEnv);

			// receive whatever from the transport and dump it to the screen
			System.out.println("RESPONSE:");
			System.out.println("---------------------------------");
			SOAPTransport st = msgObj.getSOAPTransport();
			BufferedReader br = st.receive();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sResult = sResult + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}

	/**
	 * send soap xml message
	 * 
	 * @param url
	 *            soap service's address and
	 *            port,example:http://localhost:8080/eoms_j2ee/servlet/messagerouter
	 * @param file
	 *            xml message file's address,it is a physic directory string
	 * @return
	 */
	public static String sendFileMessage(String url, String file) {

		String sResult = "";
		try {
			// get the envelope to send
			FileReader fr = new FileReader(file);
			DocumentBuilder xdb = XMLParserUtils.getXMLDocBuilder();
			Document doc = xdb.parse(new InputSource(fr));
			if (doc == null) {
				throw new SOAPException(Constants.FAULT_CODE_CLIENT,
						"parsing error");
			}
			Envelope msgEnv = Envelope.unmarshall(doc.getDocumentElement());

			// send the message
			Message msgObj = new Message();
			msgObj.send(new URL(url), "urn:this-is-the-action-uri", msgEnv);

			// receive whatever from the transport and dump it to the screen
			System.out.println("RESPONSE:");
			System.out.println("--------");
			SOAPTransport st = msgObj.getSOAPTransport();
			BufferedReader br = st.receive();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sResult = sResult + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}

	// 将字符串日期转成日历型
	public static GregorianCalendar String2Cal(String strDate) {
		GregorianCalendar calDate = new GregorianCalendar();
		strDate = StaticMethod.null2String(strDate).replaceAll("\\.0", "");
		Vector vecDate = StaticMethod.getVector(strDate, " ");
		if (vecDate.size() > 0 && vecDate.size() < 3) {
			if (vecDate.size() == 1) {
				Vector vecData = StaticMethod.getVector(String.valueOf(vecDate
						.elementAt(0)), "-");
				if (vecData.size() == 3) {
					int intYear = Integer.parseInt(String.valueOf(vecData
							.elementAt(0)));
					int intMonth = Integer.parseInt(String.valueOf(vecData
							.elementAt(1)));
					int intDay = Integer.parseInt(String.valueOf(vecData
							.elementAt(2)));
					calDate.set(calDate.YEAR, intYear);
					calDate.set(calDate.MONTH, intMonth - 1);
					calDate.set(calDate.DATE, intDay);
				}
				calDate.set(calDate.HOUR_OF_DAY, 0);
				calDate.set(calDate.MINUTE, 0);
				calDate.set(calDate.SECOND, 0);
			}
			if (vecDate.size() == 2) {
				Vector vecData = StaticMethod.getVector(String.valueOf(vecDate
						.elementAt(0)), "-");
				Vector vecTime = StaticMethod.getVector(String.valueOf(vecDate
						.elementAt(1)), ":");
				if (vecData.size() == 3) {
					int intYear = Integer.parseInt(String.valueOf(vecData
							.elementAt(0)));
					int intMonth = Integer.parseInt(String.valueOf(vecData
							.elementAt(1)));
					int intDay = Integer.parseInt(String.valueOf(vecData
							.elementAt(2)));
					calDate.set(calDate.YEAR, intYear);
					calDate.set(calDate.MONTH, intMonth - 1);
					calDate.set(calDate.DATE, intDay);
				}
				if (vecTime.size() == 3) {
					int intHour = Integer.parseInt(String.valueOf(vecTime
							.elementAt(0)));
					int intMinute = Integer.parseInt(String.valueOf(vecTime
							.elementAt(1)));
					int intSecond = Integer.parseInt(String.valueOf(vecTime
							.elementAt(2)));
					calDate.set(calDate.HOUR_OF_DAY, intHour);
					calDate.set(calDate.MINUTE, intMinute);
					calDate.set(calDate.SECOND, intSecond);
				}
			}
		}
		return calDate;
	}

	// 将日历型转成日期字符串
	public static String Cal2String(GregorianCalendar calDate) {
		String strDate = "";
		strDate = String.valueOf(calDate.get(calDate.YEAR));
		strDate = strDate + "-"
				+ String.valueOf(calDate.get(calDate.MONTH) + 1);
		strDate = strDate + "-" + String.valueOf(calDate.get(calDate.DATE));
		strDate = strDate + " "
				+ String.valueOf(calDate.get(calDate.HOUR_OF_DAY));
		strDate = strDate + ":" + String.valueOf(calDate.get(calDate.MINUTE));
		strDate = strDate + ":" + String.valueOf(calDate.get(calDate.SECOND));
		return strDate;
	}

	/**
	 * @see 得到时间字符串
	 * @see 例如：StaticMethod.getDateString(-1),可以返回昨天的时间字符串
	 * @param disday
	 *            int 和当前距离的天数
	 * @return String para的标准时间格式的串 例如：返回'2003-8-10'
	 */
	public static String getDateString(int disday) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		String _yystr = "", _mmstr = "", _ddstr = "";
		int _yy = c.get(Calendar.YEAR);
		_yystr = _yy + "";
		int _mm = c.get(Calendar.MONTH) + 1;
		_mmstr = _mm + "";
		if (_mm < 10) {
			_mmstr = "0" + _mm;
		}
		int _dd = c.get(Calendar.DATE);
		_ddstr = _dd + "";
		if (_dd < 10) {
			_ddstr = "0" + _dd;
		}
		ls_display = "'" + _yystr + "-" + _mmstr + "-" + _ddstr + "'";
		return ls_display;
	}

	/**
	 * @see 得到时间字符串
	 * @see 例如：StaticMethod.getDateString(-1),可以返回昨天的时间字符串
	 * @param disday
	 *            int 和当前距离的天数
	 * @return String para的标准时间格式的串 例如：返回'2003-8-10'
	 */
	public static String getDateString(String strDateLimit, int disday) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date thisdate = dateFormat.parse(strDateLimit);
			c.setTime(thisdate);
		} catch (Exception e) {
		}
		c.set(Calendar.DATE, c.get(Calendar.DATE) + disday);
		String ls_display = dateFormat.format(c.getTime()).toString();
		return ls_display;
	}

	/**
	 * @see 得到时间字符串
	 * @see 例如：StaticMethod.getDateString(-1),可以返回昨天的时间字符串
	 * @param disday
	 *            int 和当前距离的天数
	 * @return String para的标准时间格式的串,例如：返回'2003-8-10 00:00:00'
	 */

	public static String getTimeString(int disday) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		String _yystr = "", _mmstr = "", _ddstr = "";
		int _yy = c.get(Calendar.YEAR);
		_yystr = _yy + "";
		int _mm = c.get(Calendar.MONTH) + 1;
		_mmstr = _mm + "";
		if (_mm < 10) {
			_mmstr = "0" + _mm;
		}
		int _dd = c.get(Calendar.DATE);
		_ddstr = _dd + "";
		if (_dd < 10) {
			_ddstr = "0" + _dd;
		}
		ls_display = "'" + _yy + "-" + _mm + "-" + _dd + " 00:00:00'";
		return ls_display;
	}

	/**
	 * @see 得到时间字符串
	 * @see 例如：StaticMethod.getTimeString(-1,16),可以返回昨天的时间字符串
	 * @param disday
	 *            int 和当前距离的天数
	 * @return String para的标准时间格式的串,例如：返回'2003-8-10 16:00:00'
	 */

	public static String getTimeString(int disday, int hour) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		String _yystr = "", _mmstr = "", _ddstr = "", _hourstr = "";
		int _yy = c.get(Calendar.YEAR);
		_yystr = _yy + "";
		int _mm = c.get(Calendar.MONTH) + 1;
		_mmstr = _mm + "";
		if (_mm < 10) {
			_mmstr = "0" + _mm;
		}
		int _dd = c.get(Calendar.DATE);
		_ddstr = _dd + "";
		if (_dd < 10) {
			_ddstr = "0" + _dd;
		}
		if (hour < 10) {
			_hourstr = "0" + hour;
		} else {
			_hourstr = "" + hour;
		}
		ls_display = "'" + _yy + "-" + _mm + "-" + _dd + " " + _hourstr
				+ ":00:00'";
		return ls_display;
	}

	public static Timestamp getTimestamp(int disday) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		return new Timestamp(realtime);
	}

	public static Timestamp getTimestamp(String str) {
		Timestamp ret = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			Date date = dateFormat.parse(str);
			long datelong = date.getTime();
			ret = new Timestamp(datelong);

		} catch (Exception e) {
		}
		return ret;
	}

	public static Timestamp getTimestamp() {
		return getTimestamp(0);
	}

	public static String getTimestampString(Timestamp sta) {
		String str = sta.toString();
		return str.substring(0, str.lastIndexOf('.'));
	}

	/**
	 * @see 由运维系统发送工单确认信息，到客服系统
	 * @param operateId
	 *            KF工单流水号
	 * @param dealId
	 *            KF工单派单号
	 * @param userId
	 *            EOMS处理人
	 * @param dealDept
	 *            EOMS处理部门 根据编码表获取KF 的部门ID
	 * @param contact
	 *            EOMS联系方式
	 * @return XML格式字符串 <?xml version="1.0" encoding="UTF-8"?>
	 *         <SOAP-ENV:Envelope
	 *         xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
	 *         xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
	 *         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 *         xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	 *         xmlns:ns="urn:KFWebService"> <SOAP-ENV:Body
	 *         encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
	 *         <ns:CONFIRM-CPLT> <CONFIRM-CPLT> <FLOW-NO></FLOW-NO> <DEAL-ID></DEAL-ID>
	 *         <PROCESS-UNIT></PROCESS-UNIT> <PROCESS-NAME></PROCESS-NAME>
	 *         <CONTACTOR-TEL></CONTACTOR-TEL> </CONFIRM-CPLT>
	 *         </ns:CONFIRM-CPLT> </SOAP-ENV:Body> </SOAP-ENV:Envelope>
	 */

	public static String confirmMSGEOMS21860(String operateId, String dealId,
			String userId, int dealDept, String contact) {

		String xmlComfirmString = "";

		xmlComfirmString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlComfirmString = xmlComfirmString
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"";
		xmlComfirmString = xmlComfirmString
				+ " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"";
		xmlComfirmString = xmlComfirmString
				+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
		xmlComfirmString = xmlComfirmString
				+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"  xmlns:ns=\"urn:KFWebService\">";
		xmlComfirmString = xmlComfirmString
				+ "<SOAP-ENV:Body encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding\">";
		xmlComfirmString = xmlComfirmString + "<ns:CONFIRM-CPLT>";
		xmlComfirmString = xmlComfirmString + "<CONFIRM-CPLT>";
		xmlComfirmString = xmlComfirmString + "<FLOW-NO>" + operateId.trim()
				+ "</FLOW-NO>";
		xmlComfirmString = xmlComfirmString + "<DEAL-ID>" + dealId.trim()
				+ "</DEAL-ID>";
		xmlComfirmString = xmlComfirmString + "<PROCESS-UNIT>" + dealDept
				+ "</PROCESS-UNIT>";
		xmlComfirmString = xmlComfirmString + "<PROCESS-NAME>" + userId
				+ "</PROCESS-NAME>";
		xmlComfirmString = xmlComfirmString + "<CONTACTOR-TEL>"
				+ contact.trim() + "</CONTACTOR-TEL>";
		xmlComfirmString = xmlComfirmString + "</CONFIRM-CPLT>";
		xmlComfirmString = xmlComfirmString + "</ns:CONFIRM-CPLT>";
		xmlComfirmString = xmlComfirmString + "</SOAP-ENV:Body>";
		xmlComfirmString = xmlComfirmString + "</SOAP-ENV:Envelope>";

		return xmlComfirmString;
	}

	/**
	 * @see 由运维系统发送工单回复信息，到客服系统
	 * @param operateId
	 *            KF工单流水号
	 * @param dealID
	 *            KF 派单号
	 * @param userId
	 *            处理人
	 * @param dealDept
	 *            处理部门更具编码表获取KF 的部门ID
	 * @param proTime
	 *            处理时间
	 * @param finallyResult
	 *            最终处理结果。如果是退回，可以为空。
	 * @param rejectCause
	 *            故障原因可以为空
	 * @param Status
	 *            对应运维EOMS工单主要有两个回复操作，处理结束（status = 0）和处理未结束(status=1)
	 * @param areaCode
	 *            地区代码
	 * @return XML格式化字符串
	 */
	public static String returnMSGEOMS21860(String operateId, String dealId,
			String userId, int dealDept, String proTime, String finallyResult,
			String rejectCause, int status, int areaCode) {

		String xmlReturnString = "";

		xmlReturnString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlReturnString = xmlReturnString
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"";
		xmlReturnString = xmlReturnString
				+ " xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"";
		xmlReturnString = xmlReturnString
				+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
		xmlReturnString = xmlReturnString
				+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"  xmlns:ns=\"urn:KFWebService\">";
		xmlReturnString = xmlReturnString
				+ "<SOAP-ENV:Body encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding\">";
		xmlReturnString = xmlReturnString + "<ns:FILL-PRCS-RSLT>";
		xmlReturnString = xmlReturnString + "<PRCS-RSLT>";
		xmlReturnString = xmlReturnString + "<FLOW-NO>" + operateId.trim()
				+ "</FLOW-NO>";
		xmlReturnString = xmlReturnString + "<DEAL-ID>" + dealId.trim()
				+ "</DEAL-ID>";
		xmlReturnString = xmlReturnString + "<PROCESS-UNIT>" + dealDept
				+ "</PROCESS-UNIT>";
		xmlReturnString = xmlReturnString + "<PROCESS-NAME>" + userId.trim()
				+ "</PROCESS-NAME>";
		xmlReturnString = xmlReturnString + "<PROCESS-TIME>" + proTime.trim()
				+ "</PROCESS-TIME>";
		xmlReturnString = xmlReturnString + "<PROCESS-RESULT>" + finallyResult
				+ "</PROCESS-RESULT>";
		/*
		 * if(status ==0) { xmlReturnString = xmlReturnString + "<PROCESS-RESULT>"+finallyResult+"</PROCESS-RESULT>";
		 * }else if(status == 1) { xmlReturnString = xmlReturnString + "<PROCESS-RESULT>"+rejectCause+"</PROCESS-RESULT>";
		 * }else { xmlReturnString = xmlReturnString + "<PROCESS-RESULT></PROCESS-RESULT>"; }
		 */
		xmlReturnString = xmlReturnString + "<AREA-CODE>" + areaCode
				+ "</AREA-CODE>";
		xmlReturnString = xmlReturnString + "<END-HANDLE-FLAG>" + status
				+ "</END-HANDLE-FLAG>";
		xmlReturnString = xmlReturnString + "</PRCS-RSLT>";
		xmlReturnString = xmlReturnString + "</ns:FILL-PRCS-RSLT>";
		xmlReturnString = xmlReturnString + "</SOAP-ENV:Body>";
		xmlReturnString = xmlReturnString + "</SOAP-ENV:Envelope>";

		return xmlReturnString;
	}

	/**
	 * @see 得到当前时刻的时间字符串
	 * @return String para的标准时间格式的串,例如：返回'2003-08-09 16:00:00'
	 */
	public static String getLocalString() {
		java.util.Date currentDate = new java.util.Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(currentDate);

		return date;
	}

	public static String getLocalString(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, day);

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(c.getTime());

		return date;
	}

	/**
	 * @see 根据参数str,得到标准的时间字符串
	 * @return String para的标准时间格式的串,例如：返回'2003-08-09 16:00:00'
	 */
	public static String getLocalString(String str) {
		String time = "";

		Vector temp = StaticMethod.getVector(str, " ");

		if (!temp.isEmpty()) {
			Vector first = StaticMethod.getVector(temp.get(0).toString(), "-");
			Vector last = StaticMethod.getVector(temp.get(1).toString(), ":");

			String year = first.get(0).toString();
			;
			String month = first.get(1).toString();
			;
			String day = first.get(2).toString();
			String hour = last.get(0).toString();
			String minute = last.get(1).toString();
			String second = last.get(2).toString();

			if (month.length() == 1) {
				month = "0" + month;
			}
			if (day.length() == 1) {
				day = "0" + day;
			}
			if (hour.length() == 1) {
				hour = "0" + hour;
			}
			if (minute.length() == 1) {
				minute = "0" + minute;
			}
			if (second.length() == 1) {
				second = "0" + second;

			}
			time = year + "-" + month + "-" + day + " " + hour + ":" + minute
					+ ":" + second;
		}

		return time;
	}

	public static String getLocalString(int disday, int hour) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		String _yystr = "", _mmstr = "", _ddstr = "", _hourstr = "";
		int _yy = c.get(Calendar.YEAR);
		_yystr = _yy + "";
		int _mm = c.get(Calendar.MONTH) + 1;
		_mmstr = _mm + "";
		if (_mm < 10) {
			_mmstr = "0" + _mm;
		}
		int _dd = c.get(Calendar.DATE);
		_ddstr = _dd + "";
		if (_dd < 10) {
			_ddstr = "0" + _dd;
		}
		if (hour < 10) {
			_hourstr = "0" + hour;
		} else {
			_hourstr = "" + hour;
			// ls_display = "'" + _yy + "-" + _mm + "-" + _dd + " " + _hourstr +
			// ":00:00'";
		}
		ls_display = _yy + "-" + _mmstr + "-" + _ddstr + " " + _hourstr
				+ ":00:00";
		return ls_display;
	}

	/**
	 * @ see 转换时间格式，如时间“2002-1-12”转换成字符串“020112”
	 * @param DateStr
	 *            “2002-1-12”
	 * @return “020112”
	 */
	public static String getYYMMDD() {
		return getYYMMDD(getCurrentDateTime());
	}

	public static String getYYMMDD(String DateStr) {
		String YY, MM, DD;
		String ReturnDateStr;

		int s = DateStr.indexOf(" ");
		ReturnDateStr = DateStr.substring(0, s);

		Vector ss = getVector(ReturnDateStr, "-");
		YY = ss.elementAt(0).toString();
		YY = YY.substring(2, 4);
		MM = ss.elementAt(1).toString();
		if (Integer.valueOf(MM).intValue() < 10) {
			MM = "0" + Integer.valueOf(MM).intValue();
		} else {
			MM = MM;
		}
		DD = ss.elementAt(2).toString();
		if (Integer.valueOf(DD).intValue() < 10) {
			DD = "0" + Integer.valueOf(DD).intValue();
		} else {
			DD = DD;
		}
		ReturnDateStr = YY + MM + DD;
		// System.out.println("ReturnDateStr =" + ReturnDateStr);
		return ReturnDateStr;
	}

	/**
	 * @see 根据传入的分钟数,得到转化后的天，小时和分钟值
	 * @param times
	 *            int
	 * @return ArrayList 保存这些值的集合，0位置保存天，1位置保存小时，2位置保存分钟
	 */
	public static ArrayList getDHMString(long times) {
		long day;
		long hour;
		long minute;
		ArrayList list = new ArrayList();

		day = times / (24 * 60);
		if (day == 0) {

			hour = times / 60;
			minute = times - hour * 60;
		} else {
			hour = (times % (24 * 60)) / 60;
			minute = times - day * 24 * 60;
			if (hour != 0) {
				minute -= hour * 60;
			}
		}

		list.add(String.valueOf(day));
		list.add(String.valueOf(hour));
		list.add(String.valueOf(minute));
		return list;
	}

	// 得到当前的系统时间 Add By ChengJiWu
	/*
	 * 根据输入的格式(String _dtFormat)得到当前时间格式
	 */
	public static String getCurrentDateTime(String _dtFormat) {
		String currentdatetime = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat dtFormat = new SimpleDateFormat(_dtFormat);
			currentdatetime = dtFormat.format(date);
		} catch (Exception e) {
			System.out.println("时间格式不正确");
			e.printStackTrace();
		}
		return currentdatetime;
	}
	/*
	 * 返回当前时间在一年中的第几周
	 */
	public static int getWeekOfYear() {
		Calendar calendar = Calendar.getInstance();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return calendar.get(Calendar.WEEK_OF_YEAR);

	}


	// 的到默认的时间格式为("yyyy-MM-dd HH:mm:ss")的当前时间
	public static String getCurrentDateTime() {
		return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param strDate
	 *            时间型的字符串
	 * @param _dtFormat
	 *            形如"yyyy-MM-dd HH:mm:ss"的字符串 把 strDate 时间字符串 转换为 _dtFormat 格式
	 * @return
	 */
	public static String getCurrentDateTime(String strDate, String _dtFormat) {
		String strDateTime;
		Date tDate = null;
		if (null == strDate) {
			return getCurrentDateTime();
		}
		SimpleDateFormat smpDateFormat = new SimpleDateFormat(_dtFormat);
		ParsePosition pos = new ParsePosition(0);
		tDate = smpDateFormat.parse(strDate, pos); // 标准格式的date类型时间
		strDateTime = smpDateFormat.format(tDate); // 标准格式的String 类型时间
		return strDateTime;
	}

	/**
	 * @see 获得上传文件类型，根据boco.xml文件中的<UploadType>节点值。
	 * @return String （“UpE”,或“UpN”）。
	 */
	public static String getUploadType() {
		String UploadType = "";
		try {

			UploadType = PROP.getProperty("UploadType").toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return UploadType;
		}
	}

	public static Vector unionAllVec(Vector vec1, Vector vec2) {
		boolean flag = false;
		int size = vec2.size();
		Vector ret = new Vector();
		ret = (Vector) vec1.clone();

		int itemp = 0;
		for (int i = 0; i < vec2.size(); i++) {
			itemp = 0;
			flag = false;
			while ((itemp < vec1.size()) && (!flag)) {
				if (vec1.get(itemp).toString().equals(vec2.get(i).toString())) {
					flag = true;
				} else {
					itemp = itemp + 1;
				}
			}
			if (!flag) {
				vec1.add(vec2.get(i));
			}
		}

		return vec1;
	}

	/**
	 * @see 格式化字符串
	 * @param OriStr
	 *            分割符号为“，”情况下，“,Str1,Str2,Str3” 或 “Str1,Str2,Str3，”或
	 *            “，Str1,Str2,Str3，”等
	 * @param dim
	 *            分割符号，如“，”
	 * @return Str1,Str2,Str3
	 */
	public static String right(String str, int i) {
		String sc = "";
		if (!str.equals("")) {
			int strlength = str.length();
			int priostrlength = (strlength - i);
			sc = str.substring(priostrlength, strlength);
		}
		return sc;
	}

	public static String left(String str, int i) {
		String sc = "";
		if (!str.equals("")) {
			int strlength = str.length();
			int priostrlength = (i);
			sc = str.substring(0, priostrlength);
		}
		return sc;
	}

	public static String getFormatStr(String OriStr, String dim) {
		// OriStr 不能为空；
		OriStr = null2String(OriStr, "");
		if (OriStr == "") {
			OriStr = "";
		} else {
			int dimLength = dim.length();
			if (left(OriStr, dimLength).equals(dim)) {
				OriStr = right(OriStr, (OriStr.length() - dimLength));
			}
			if (right(OriStr, dimLength).equals(dim)) {
				OriStr = left(OriStr, (OriStr.length() - dimLength));
			}
		}
		return OriStr;
	}

	public static String getLocalStringByHours(int hour) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, hour);

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(c.getTime());

		return date;
	}

	public static String getFaultTime(String beginTime, String endTime) {
		// 此方法用于计算历时输入参数为两个时间类型；
		String temp = null;
		if (endTime.equals(null) || endTime.equals("")) {
			return temp;
		} else if (endTime.equals(null) || endTime.equals("")) {
			return temp;
		} else {
			int beginYear = Integer.parseInt(beginTime.substring(0, 4));
			int beginMou = Integer.parseInt(beginTime.substring(5, 7));
			int beginDay = Integer.parseInt(beginTime.substring(8, 10));
			int beginHou = Integer.parseInt(beginTime.substring(11, 13));
			int beginFen = Integer.parseInt(beginTime.substring(14, 16));

			int endYear = Integer.parseInt(endTime.substring(0, 4));
			int endMou = Integer.parseInt(endTime.substring(5, 7));
			int endDay = Integer.parseInt(endTime.substring(8, 10));
			int endHou = Integer.parseInt(endTime.substring(11, 13));
			int endFen = Integer.parseInt(endTime.substring(14, 16));

			if (endYear > beginYear) {
				for (int i = beginMou + 1; i <= 12; i++) {
					if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8
							|| i == 10 || i == 12) {
						endDay = endDay + 31;
					} else if (i == 2) {
						if (beginYear == 2008) {
							endDay = endDay + 29;
						} else {
							endDay = endDay + 28;
						}
					} else {
						endDay = endDay + 30;
					}
				}
				for (int i = 1; i <= endMou - 1; i++) {
					if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8
							|| i == 10 || i == 12) {
						endDay = endDay + 31;
					} else if (i == 2) {
						if (beginYear == 2008) {
							endDay = endDay + 29;
						} else {
							endDay = endDay + 28;
						}
					} else {
						endDay = endDay + 30;
					}
				}
				int t = 0;
				if (beginMou == 1 || beginMou == 3 || beginMou == 5
						|| beginMou == 7 || beginMou == 8 || beginMou == 10
						|| beginMou == 12) {
					t = 31 - beginDay;
				} else if (beginMou == 2) {
					if (beginYear == 2008 || beginYear == 2004) {
						t = 29 - beginDay;
					} else {
						t = 28 - beginDay;
					}
				} else {
					t = 30 - beginDay;
				}
				endDay = endDay + t;
				endDay = endDay + beginDay;
			} else {
				if (endMou > beginMou) {
					for (int i = beginMou + 1; i <= endMou - 1; i++) {
						if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8
								|| i == 10 || i == 12) {
							endDay = endDay + 31;
						} else if (i == 2) {
							if (beginYear == 2008) {
								endDay = endDay + 29;
							} else {
								endDay = endDay + 28;
							}
						} else {
							endDay = endDay + 30;
						}
					}
					int t = 0;
					if (beginMou == 1 || beginMou == 3 || beginMou == 5
							|| beginMou == 7 || beginMou == 8 || beginMou == 10
							|| beginMou == 12) {
						t = 31 - beginDay;
					} else if (beginMou == 2) {
						if (beginYear == 2008 || beginYear == 2004) {
							t = 29 - beginDay;
						} else {
							t = 28 - beginDay;
						}
					} else {
						t = 30 - beginDay;
					}
					endDay = endDay + t;
					endDay = endDay + beginDay;
				} else {
					endDay = endDay;
				}
			}
			int day = endDay - beginDay;
			int hour = endHou - beginHou + day * 24;
			int fen = endFen - beginFen;
			int t = hour * 60 + fen;
			if (fen < 0) {
				hour = hour - 1;
				fen = fen + 60;
				t = hour * 60 + fen;
			}
			// temp=hour+" 小时 "+fen+"分";
			temp = String.valueOf(t);
		}
		if (temp.equals(null)) {
			temp = "";
		}
		return temp;
	}

	/**
	 * @see 得到某一天开始的时间字符串
	 * @see 例如：StaticMethod.getDateString(-1),可以返回昨天的时间字符串
	 * @param disday
	 *            int 和当前距离的天数
	 * @return String para的标准时间格式的串,例如：返回'2003-8-10 00:00:00'
	 */

	public static String getTimeBeginString(int disday) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		int _yy = c.get(Calendar.YEAR);
		int _mm = c.get(Calendar.MONTH) + 1;
		int _dd = c.get(Calendar.DATE);
		ls_display = _yy + "-" + _mm + "-" + _dd + " 00:00:00";
		return ls_display;
	}

	/**
	 * @see 得到某一天结束的时间字符串
	 * @see 例如：StaticMethod.getDateString(-1),可以返回昨天的时间字符串
	 * @param disday
	 *            int 和当前距离的天数
	 * @return String para的标准时间格式的串,例如：返回'2003-8-10 00:00:00'
	 */

	public static String getTimeEndString(int disday) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		int _yy = c.get(Calendar.YEAR);
		int _mm = c.get(Calendar.MONTH) + 1;
		int _dd = c.get(Calendar.DATE);
		ls_display = _yy + "-" + _mm + "-" + _dd + " 23:59:59";
		return ls_display;
	}

	public static long nullObject2Long(Object s) {
		long i = 0;

		String str = "";
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = 0;
		}

		return i;
	}

	public static long nullObject2Long(Object s, long temp) {
		long i = temp;

		String str = "";
		try {
			str = s.toString();
			i = Long.parseLong(str);
		} catch (Exception e) {
			i = temp;
		}

		return i;
	}

	public static String[] subTimeString(String time) {

		String[] timeString = time.split(" ");
		String[] time1 = timeString[0].split("-");
		String[] time2 = timeString[1].split(":");
		int length = time1.length + time2.length;
		String[] Time = new String[length];

		for (int i = 0; i < time1.length; i++) {
			Time[i] = time1[i];
		}
		for (int j = 0; j < time2.length; j++) {
			Time[time1.length + j] = time2[j];
		}
		return Time;
	}

	public static String convert(long millonSecond) {
		int totalsecond = (int) millonSecond / 1000;
		int hour, min, day, sec;
		String minStr = "", hourStr = "", dayStr = "";
		/*
		 * day=totalsecond /(60*60*24); dayStr=String.valueOf(day);
		 */
		hour = (totalsecond) / (60 * 60);
		hourStr = String.valueOf(hour);
		min = (totalsecond - hour * 60 * 60) / 60;
		minStr = String.valueOf(min);
		sec = totalsecond - min * 60 - hour * 60 * 60;
		if (min == 0)
			minStr = "00";
		if (hour == 0)
			hourStr = "00";
		return hourStr + ":" + minStr + ":" + sec;
	}

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
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
			}

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			System.out.println("删除文件夹操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * zip压缩功能 basePath----d:\\temp\\zipout目录下 zipFile ----d:\\temp\\out.zip.
	 * 
	 * @throws Exception
	 */
	public static void CreateZip(String baseDir, String zipFile)
			throws Exception {

		// 压缩baseDir下所有文件，包括子目录
		List fileList = getSubFiles(new File(baseDir));

		// 压缩文件名
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		int readLen = 0;
		for (int i = 0; i < fileList.size(); i++) {
			File f = (File) fileList.get(i);
			// System.out.print("Adding: " + f.getPath() + f.getName());

			// 创建一个ZipEntry，并设置Name和其它的一些属性
			ze = new ZipEntry(getAbsFileName(baseDir, f));
			ze.setSize(f.length());
			ze.setTime(f.lastModified());

			// 将ZipEntry加到zos中，再写入实际的文件内容
			zos.putNextEntry(ze);
			InputStream is = new BufferedInputStream(new FileInputStream(f));
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				zos.write(buf, 0, readLen);
			}
			is.close();
			// System.out.println(" done...");
		}
		zos.close();
	}

	/**
	 * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
	 * 
	 * @param baseDir
	 *            java.lang.String 根目录
	 * @param realFileName
	 *            java.io.File 实际的文件名
	 * @return 相对文件名
	 */
	private static String getAbsFileName(String baseDir, File realFileName) {

		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else {
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	private static List getSubFiles(File baseDir) {

		List ret = new ArrayList();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				ret.add(tmp[i]);
			}
			if (tmp[i].isDirectory()) {
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}

	public static String getAddZero(String str) {
		String time = "";

		try {
			if (StaticVariable.DB.equals("oracle")) {
				String[] strArray = str.split(" ");
				String dataStr = strArray[0];

				String[] time_ = dataStr.split("-");

				int month = Integer.parseInt(time_[1]);
				String month_ = "";
				if (month < 10)
					month_ = "0" + month;
				else
					month_ = month + "";

				int day = Integer.parseInt(time_[2]);
				String day_ = "";
				if (day < 10)
					day_ = "0" + day;
				else
					day_ = day + "";

				if (str.indexOf(" ") > 0)
					time = time_[0] + "-" + month_ + "-" + day_ + " "
							+ strArray[1];
				else
					time = time_[0] + "-" + month_ + "-" + day_;
			} else {
				time = str;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 根据时间字符串（yyyy-mm-dd hh:mm:ss） 加上一定的分钟数，返回一个加和后的时间字符串 add by gongyfueng for
	 * duty
	 * 
	 * @param str
	 *            时间字符串， int minute
	 * @return str
	 */
	public static String getDateForMinute(String str, int minute) {
		String time = "";
		try {
			GregorianCalendar cal = String2Cal(str);
			cal.add(cal.MINUTE, minute);
			time = String.valueOf(cal.get(cal.YEAR));
			time = time + "-" + String.valueOf(cal.get(cal.MONTH) + 1);
			time = time + "-" + String.valueOf(cal.get(cal.DATE));
			time = time + " " + String.valueOf(cal.get(cal.HOUR_OF_DAY));
			time = time + ":" + String.valueOf(cal.get(cal.MINUTE));
			time = time + ":" + String.valueOf(cal.get(cal.SECOND));
			System.out.println(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 
	 * add by gongyfueng for duty
	 * 
	 * @param str
	 *            时间字符串， int minute
	 * @return str
	 */

	public static String getHourbyMinute(String str, int hour) {
		String time = "";
		int hours = 0;
		try {
			String[] strArray = str.split(":");
			String str_1 = strArray[0];
			if (str_1.substring(0, 1).equals("0")) {
				str = str_1.substring(1, 2);
			} else {
				str = str_1;
			}
			hours = Integer.parseInt(str) + hour;
			if (hours > 24)
				hours = hours - 24;
			if (hours == 24)
				hours = 0;
			if (hours < 0)
				hours = 24 + hours;
			if (hours < 10)
				time = "0" + hours;
			if (hours >= 10)
				time = hours + "";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return time+":00";
	}
}
