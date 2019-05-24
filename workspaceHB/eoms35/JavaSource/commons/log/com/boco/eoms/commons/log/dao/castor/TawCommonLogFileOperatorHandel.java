package com.boco.eoms.commons.log.dao.castor;

import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import org.exolab.castor.mapping.Mapping;

import org.exolab.castor.xml.Unmarshaller;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.log.model.castor.TawCommonLogFileOperatorMappiing;
import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonLogFileOperatorHandel {

	private String xmlfile;

	private Mapping mapping;

	public TawCommonLogFileOperatorHandel(String mappingfile, String xmlfiles) {

		this.xmlfile = xmlfiles;
		String logmappingfile = "";
		if (mappingfile.equals("")) {

			mappingfile = StaticMethod
					.getFilePath("classpath:com/boco/eoms/commons/log/dao/castor/bocologcontmapping.xml");

			logmappingfile = mappingfile.replaceAll("/", "\\\\");
		}
		try {
			mapping = new Mapping();
			mapping.loadMapping(logmappingfile);
		} catch (Exception e) {
			BocoLog.info(this, "查找的日志文件:" + xmlfiles + " 不存在或者被删除!");
		}
	}

	public TawCommonLogFileOperatorMappiing getElementc() {
		TawCommonLogFileOperatorMappiing contentcollection = null;

		Unmarshaller un = new Unmarshaller(
				TawCommonLogFileOperatorMappiing.class);
		try {

			un.setMapping(mapping);
			FileReader in;
			in = new FileReader(xmlfile);
			contentcollection = (TawCommonLogFileOperatorMappiing) un
					.unmarshal(in);
			in.close();
		} catch (Exception e) {

			BocoLog.info(this, "查找的日志文件:" + xmlfile + " 不存在或者被删除!");
		}

		return contentcollection;
	}

	public void saveDoc(String classname, String userID, String modelID,
			String modelname, String operID, String opernames, String savetime,
			String saveyear, String savemonth, String day, String loglevel,
			String remotecomputerip, String filepathandname, String message,
			String remarks) {
		try {
			// 实际目录

			// 从文件构造一个Document
			Document _doc = new Document();

			/*
			 * Map map = new HashMap(); map.put("type","text/xsl");
			 * map.put("href","products.xsl"); ProcessingInstruction pi = new
			 * ProcessingInstruction("xml-stylesheet",map); _doc.addContent(pi);
			 */

			Element root = new Element("bocologcontents");
			_doc.setRootElement(root);
			Attribute bute = new Attribute("siteName", "EOMS3.5");
			root.setAttribute(bute);
			Element product = new Element("contents");
			root.addContent(product);
			Attribute pbute = new Attribute("id", String.valueOf(operID));
			product.setAttribute(pbute);

			product.addContent(new Element("userid").setText(String
					.valueOf(userID)));
			product.addContent(new Element("modeid").setText(String
					.valueOf(modelID)));
			product.addContent(new Element("loglevel").setText(String
					.valueOf(loglevel)));
			product.addContent(new Element("operid").setText(String
					.valueOf(operID)));
			product.addContent(new Element("remark").setText(String
					.valueOf(remarks)));

			product.addContent(new Element("savetime").setText(String
					.valueOf(savetime)));
			product.addContent(new Element("ramoteip").setText(String
					.valueOf(remotecomputerip)));
			product.addContent(new Element("saveyear").setText(String
					.valueOf(saveyear)));
			product.addContent(new Element("savemonth").setText(String
					.valueOf(savemonth)));
			product.addContent(new Element("saveday").setText(String
					.valueOf(day)));
			product.addContent(new Element("message").setText(String
					.valueOf(message)));
			product.addContent(new Element("modelname").setText(String
					.valueOf(modelname)));
			product.addContent(new Element("filepathandname").setText(String
					.valueOf(filepathandname)));

			product.addContent(new Element("classname").setText(String
					.valueOf(classname)));

			product.addContent(new Element("opername").setText(String
					.valueOf(opernames)));

			XMLOutputter outp = new XMLOutputter();
			Format format = Format.getPrettyFormat();
			format.setEncoding("UTF-8");
			FileOutputStream fo;

			File file = new File(filepathandname);
			if (!file.exists()) {
				file.createNewFile();
			}
			fo = new FileOutputStream(filepathandname);

			outp.output(_doc, fo);

		} catch (Exception e) {
			// 捕获异常
			BocoLog.error(this, "Document 构造出现异常");
		}

	}
}
