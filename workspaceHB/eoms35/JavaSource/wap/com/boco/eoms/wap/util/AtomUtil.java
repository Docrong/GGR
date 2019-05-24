package com.boco.eoms.wap.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;

import java.util.Collections;
import java.util.Comparator;

import org.apache.abdera.Abdera;

import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.Parser;
import org.apache.abdera.parser.ParserOptions;

import com.boco.eoms.base.util.StaticMethod;

/**
 * 根据abdera提供的API创建的读取atom网站内容的工具
 * 
 * @author xugengxian
 * 
 */
public class AtomUtil {
	/**
	 * 基于displaytag，根据url和过滤器读取源数据
	 * 
	 * @return 返回一个MAP, 说明：list = 存储数据对象的list, total = 分页的总数据量
	 */
	public static List getFeedByURLandFilter4Pager(String url, String userid,String realPath,String flag) {

		if (url == null || url.equalsIgnoreCase("")) {
			return null;
		}
		List atomList = new ArrayList();
		// 如果为atom合并的形式。需要下面的表达式
		String urlArray[] = url.split(",");
		for (int i = 1; i < urlArray.length; i++) {

			InputStream input = null;
			Parser parser = Abdera.getNewParser();

			try {
				// input = new URL(url).openStream();
				// 利用POST方式提交请求参数
				HttpURLConnection conn = (HttpURLConnection) new URL(
						urlArray[i]).openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				osw.write("type=interface&userName=" + userid);
				osw.flush();
				osw.close();
				input = conn.getInputStream();

			} catch (Exception e) {
				e.printStackTrace();
			}
			ParserOptions opts = parser.getDefaultParserOptions();

			Document doc = null;

			if (input == null) {
				return null;
			}
			try {
				doc = parser.parse(input, "", opts);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 得到数据
			Feed feed = (Feed) doc.getRoot();
			// 根据数据转换。
			for (int j = 0; j < feed.getEntries().size(); j++) {
				Entry entry = (Entry) feed.getEntries().get(j);
				// 显示页面的时候。如果太长则截位
				if("".equals(StaticMethod.null2String(entry.getRights())) || "1".equals(entry.getRights())){// //0:无需截位
																											// 1:需要截位
				if (length(entry.getTitle()) >= 16){
					entry.setTitle(substring(entry.getTitle(), 0, 8) + "...");
				}
				}
				// url的替换 因为在action取url的时候有&的取不到。
				String tempurl = entry.getContent().replace("&", "*");
				String type = StaticMethod.null2String(entry.getLanguage());

				if ("".equals(type) || type == null) {
					entry
							.setContent(realPath+"/wap/action.do?method=detail&userId="
									+ userid
									+ "&type="
									+ type
									+ "&url=" + tempurl);
				}
				atomList.add(entry);

				// 比较原则。
				Comparator comparator = new ComparatorDate();
				Collections.sort(atomList, comparator);
			}

		}
		return atomList;
	}

	/**
	 * 重写是为了url的变换
	 * 
	 * @param url
	 * @param userid
	 * @param flag
	 * @return
	 */
	public static List getFeedByURLandFilter4Pager(String url, String userid,
			String flag) {

		if (url == null || url.equalsIgnoreCase("")) {
			return null;
		}
		List atomList = new ArrayList();
		// 如果为atom合并的形式。需要下面的表达式
		String urlArray[] = url.split(",");
		for (int i = 1; i < urlArray.length; i++) {

			InputStream input = null;
			Parser parser = Abdera.getNewParser();

			try {
				// input = new URL(url).openStream();
				// 利用POST方式提交请求参数
				HttpURLConnection conn = (HttpURLConnection) new URL(
						urlArray[i]).openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				OutputStreamWriter osw = new OutputStreamWriter(conn
						.getOutputStream());
				osw.write("type=interface&userName=" + userid);
				osw.flush();
				osw.close();
				input = conn.getInputStream();

			} catch (Exception e) {
				e.printStackTrace();
			}
			ParserOptions opts = parser.getDefaultParserOptions();

			Document doc = null;

			if (input == null) {
				return null;
			}
			try {
				doc = parser.parse(input, "", opts);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 得到数据
			Feed feed = (Feed) doc.getRoot();
			// 根据数据转换。
			for (int j = 0; j < feed.getEntries().size(); j++) {
				Entry entry = (Entry) feed.getEntries().get(j);
				// 显示页面的时候。如果太长则截位
				if (length(entry.getTitle()) > 16){
					entry.setTitle(substring(entry.getTitle(), 0, 8) + "...");
				}
				// url的替换
				String tempurl = entry.getContent().replace("&", "*");
				String type = StaticMethod.null2String(entry.getLanguage());

				if ("".equals(type) || type == null) {
					entry
							.setContent("/wap/action.do?method=detail&userId="
									+ userid
									+ "&type="
									+ type
									+ "&url=" + tempurl);
				}
				atomList.add(entry);

				// 比较原则。
				Comparator comparator = new ComparatorDate();
				Collections.sort(atomList, comparator);
			}
		}
		return atomList;
	}

	/**
	 * @author User @ see 比较原则
	 */
	static class ComparatorDate implements Comparator {

		public int compare(Object arg0, Object arg1) {
			int flag = 0;
			Entry entry0 = (Entry) arg0;
			Entry entry1 = (Entry) arg1;
			if (entry0.getPublished() != null && entry1.getPublished() != null) {
				flag = entry1.getPublished().compareTo(entry0.getPublished());
			}
			return flag;
		}
	}

	/**
	 * 二进制长度
	 * 
	 * @param s
	 * @return
	 */
	public static int length(String s) {
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则多取一个字符位
	 * 
	 * @param String
	 *            origin, 原始字符串
	 * @param int
	 *            begin, 开始位置
	 * @param int
	 *            len, 截取长度(一个汉字长度按2算的)
	 * @return String, 返回的字符串
	 */
	public static String substring(String origin, int begin, int len) {
		if (origin == null) {
			return origin;
		}
		int sBegin = (begin < 0) ? 0 : begin;
		// 越出范围处理
		if (len < 1 || sBegin > origin.length()) {
			return "";
		}

		if (len + sBegin > origin.length()) {
			return origin.substring(sBegin);
		}
		char[] c = origin.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = sBegin, j = sBegin; i < (sBegin + 2 * len); i++, j++) {
			if (j >= c.length)
				break;

			sb.append(c[j]);
			if (!isLetter(c[j])) {
				i++;
			}
		}
		return sb.toString();
	}

	/**
	 * 根据atom源地址和url参数，读取atom feeds
	 * 
	 * @param url
	 *            atom地址
	 * @param params
	 *            需要post的参数
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @author xugengxian
	 */
	public static Feed getFeeds(String url, String params) throws MalformedURLException, IOException{
		Feed feed;
		// 如果地址为空，则返回null
		if (url == null || url.equalsIgnoreCase("")) {
			return null;
		}
		InputStream input = null;
		Parser parser = Abdera.getNewParser();
		// 利用POST方式提交请求参数
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		OutputStreamWriter osw = new OutputStreamWriter(conn
				.getOutputStream());
		osw.write(params);
		osw.flush();
		osw.close();
		// 连接并取得返回的数据流
		input = conn.getInputStream();
		if (input == null) {
			return null;
		}
		// 得到数据
		feed = (Feed) parser.parse(input).getRoot();
		return feed;
	}
}
