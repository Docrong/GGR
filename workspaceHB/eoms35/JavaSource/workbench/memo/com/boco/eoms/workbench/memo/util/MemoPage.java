package com.boco.eoms.workbench.memo.util;

import java.util.Random;
import java.util.ResourceBundle;

public class MemoPage {
	private static int MAX_PAGE_INDEX = 200;

	private static String HEADER = "Result page";

	static ResourceBundle prop = null;
	static {
		prop = ResourceBundle.getBundle("resources.application_zh_CN");
		try {
			HEADER = prop.getString("pager.header.title");
		} catch (Exception e) {
		}
		try {
			MAX_PAGE_INDEX = Integer.parseInt(prop
					.getString("pager.max.page.index"));
		} catch (Exception e) {
		}
	}

	public static String generate(int offset, int length, int size, String url,
			String param) {
		String ranName = getRandom();
		String pref1 = "&";
		String pref;
		if (url.indexOf("?") > -1) {
			pref = "&";
		} else {
			pref = "?";
		}

		int pageNum = 0;
		int pageNo = offset / size + 1;

		if (length % size == 0) {
			pageNum = length / size;
		} else {
			pageNum = length / size + 1;
		}

		StringBuffer header = new StringBuffer();
		header.append(HEADER + ": ");

		header.append(prop.getString("taw.total") + "&nbsp;");
		header.append(String.valueOf(length) + "&nbsp;");
		header.append(prop.getString("taw.piece") + "&nbsp;");
		header.append(String.valueOf(pageNum) + "&nbsp;");
		header.append(prop.getString("taw.pageDescription") + "&nbsp;");
		header.append("<select name=\"page" + ranName + "\" onchange=\"goPage"
				+ ranName + "();\">");

		for (int i = 1; i <= pageNum; i++) {
			header.append("<option value=\'" + (i - 1) * size + "\'");
			if (pageNo == i)
				header.append(" selected");
			header.append(">" + i + "</option>");
		}

		header
				.append("</select>" + prop.getString("taw.page")
						+ "&nbsp;&nbsp;");

		if (offset > 0) {
			header.append("&nbsp;<a href=\"" + url + pref + param + pref1
					+ "pager.size=" + length + "&pager.offset="
					+ (offset - size) + "\">" + prop.getString("label.prePage")
					+ "</a>\n");
		}
		int start;
		int radius = MAX_PAGE_INDEX / 2 * size;
		if (offset < radius) {
			start = 0;
		} else if (offset < length - radius) {
			start = offset - radius;
		} else {
			start = (length / size - MAX_PAGE_INDEX) * size;
		}

		if (offset < length - size) {
			header.append("&nbsp;<a href=\"" + url + pref + param + pref1
					+ "pager.size=" + length + "&pager.offset="
					+ ((int) offset + (int) size) + "\">"
					+ prop.getString("label.nextPage") + "</a>\n");
		}

		header.append("\n");

		header.append("<script language=\"javascript\">\n");
		header.append("<!--\n");
		header.append("function goPage" + ranName + "()\n{window.location=\""
				+ url + pref + param + pref1 + "pager.size=" + length
				+ "&pager.offset=\"" + " + document.all.page" + ranName
				+ ".value;}\n");
		header.append("-->\n");
		header.append("</script>");
		return header.toString();
	}
	  /**避免首页定制goPage方面名称重复.随机字符串通过两个int的随机数组合而成.
	   * getRandom
	   *
	   * @throws Exception
	   * @return String
	   */
	  public static String getRandom() {
	    String ranStr = "";
	    try {
	      Random random = new Random();
	      ranStr = "" + Math.abs(random.nextInt()) + Math.abs(random.nextInt());
	    }
	    catch (Exception e) {
	      System.out.println(e);
	      ranStr = "";
	    }
	    return ranStr;
	  }
}
