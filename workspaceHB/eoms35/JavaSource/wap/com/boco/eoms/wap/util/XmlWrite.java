package com.boco.eoms.wap.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.dom4j.*;
import org.dom4j.io.*;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.system.user.model.TawSystemUser;


/**
 * @author 龔玉峰
 * @date 2009-02-18
 * @see 手动读数据并写到一个xml文件
 * 
 */
public class XmlWrite {

	/**
	 * @param className
	 * @param obj
	 * @return url
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @see 读入数据并写到一个xml文件。返回一个存在本地的xml文件路径
	 */
	public static String XmlWriteReturnUrl(String className,Object obj) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// 文件路径

		try {
		String xmlPath = AccessoriesMgrLocator.getAccessoriesAttributes()
				.getUploadPath();
		String xmlName = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
		Document document = DocumentFactory.getInstance().createDocument();
		// 添加根元素
		
		Element root = document.addElement("process");
		// 元素信息

		Element attributes = root.addElement("attributes");
		Element attribute1 = attributes.addElement("attribute");

		// 添加第一个人的信息<用的一种方法>
		Element title = attribute1.addElement("title");
		Element name = attribute1.addElement("name");

		title.setText("汉字");
		name.setText("123123123");

		Element parameters = root.addElement("parameters");
		Element parameter = parameters.addElement("parameter");
		Element id = parameter.addElement("id");
		Element values = parameter.addElement("value");

		id.setText("name");
		values.setText("123123123");

			// 如果不存在
			File file = new File(xmlPath);
			if (!file.exists()) {
				file.mkdir();
			}
			// 文件输出流
			FileWriter out = new FileWriter(xmlPath + xmlName + ".xml");
			// 声明格式化变量
			OutputFormat of = OutputFormat.createPrettyPrint();
			// 写入文件
			XMLWriter xout = new XMLWriter(out, of);
			xout.write(document);
			// 关闭流
			xout.close();
			out.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param  string s
	 * @return
	 * @see 计算一个字符串的长度。包括英文字母和数字
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

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// XmlWriteReturnUrl();
		System.out.println(substring("哈哈哈abc123", 0, 5));
		System.out.println(substring("哈哈哈哈哈b哈哈", 0, 5));
		TawSystemUser user = new TawSystemUser();
		Object obj = (Object)user;
		System.out.println(Class.forName(TawSystemUser.class.getName()).newInstance().getClass());

		// "haha"
		// "哈哈哈"
	}
}
