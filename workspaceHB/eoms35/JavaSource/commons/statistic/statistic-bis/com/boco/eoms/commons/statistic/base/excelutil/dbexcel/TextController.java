package com.boco.eoms.commons.statistic.base.excelutil.dbexcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;

/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
public class TextController {
	private Flopper flopper = null;
	private Xml2Unc xml2Unc = null;
	private String xml_excel = null;
	private String xml_unc = null;

	private OutputStream os = null;
	private Connection conn = null;

	TextController() {
		xml2Unc = new Xml2Unc();
	}

	public void setFlopper(String cname) {
		try {
			flopper = (Flopper) Class.forName(cname).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	public Flopper getFlopper() {
		return flopper;
	}

	public void Convert2Unc(String[] args) {
		if (args.length < 3)
			return;
		xml_excel = args[0];
		xml_unc = args[1];
		setFlopper(args[2]);
		int len = args.length - 3;
		if (len > 0) {
			String[] para = new String[len];
			System.arraycopy(args, 3, para, 0, len);
			//			for (int i = 0; i < len; i++) {
			//				para[i] = args[i + 3];
			//			}
			flopper.setParameter(para);
		}
		xml2Unc.setFlopper(flopper);
		flopper.beginConvert();
		conn = flopper.getConnection();
		flopper.afterConnected(conn);
		xml2Unc.setConnection(conn);
		try {
			os = new FileOutputStream(xml_unc);
		} catch (FileNotFoundException e) {
		}
		PrintStream sysout = System.out;
		System.setOut(new PrintStream(os));
		xml2Unc.createUncXml(xml_excel);
		flopper.beforeClosed(conn);
		flopper.closeConnection(conn);
		System.setOut(sysout);
		flopper.endOfConvert();
	}

//	主程序入口：com.uncnet.dbexcel.TextController的main方法
//	调用参数：Excel Xml文件路径，输出UNC Xml文件路径，Flopper扩展类名字空间，其它扩展参数...
//	参数示例：
//	F:work49-23\expert.xml F:work49-23\_expert2.xml com.boco.eoms.commons.statistic.base.excelutil.dbexcel.flopper.YpzbExpertFp oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@10.0.2.108:1521:eoms eomsdev eomsdev F:work49-23\datadiploma.txt F:work49-23\datahospital.txt
	public static void main(String[] args) {
		System.out.println("Begin convert...");
		TextController controller = new TextController();
		controller.Convert2Unc(args);
		System.out.println("End of convert.");
	}
}
