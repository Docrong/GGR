package com.boco.eoms.commons.log.dao.castor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

public class TawCommonLogAppendXML {

	/**
	 * 添加信息到xml
	 * 
	 * @param classname
	 * @param userID
	 * @param modelID
	 * @param modelname
	 * @param operID
	 * @param opername
	 * @param savetime
	 * @param saveyear
	 * @param savemonth
	 * @param day
	 * @param loglevel
	 * @param remotecomputerip
	 * @param filepath
	 * @param message
	 * @param remark
	 */
	public void saveToxml(String classname, String userID, String modelID,
			String modelname, String operID, String opername, String savetime,
			String saveyear, String savemonth, String day, String loglevel,
			String remotecomputerip, String filepath, String message,
			String remark) {
		Document doc;
		Element element;
		try {

			doc = getSAXBDoc(filepath);
			element = new Element("contents");
			element.setAttribute("id", String.valueOf(operID));

			Element savetimeElement = new Element("savetime");

			savetimeElement.setText(String.valueOf(savetime));
			element.addContent(savetimeElement);

			Element modeidElement = new Element("modeid");
			modeidElement.setText(String.valueOf(modelID));
			element.addContent(modeidElement);

			Element operidElement = new Element("operid");
			operidElement.setText(String.valueOf(operID));
			element.addContent(operidElement);

			Element useridElement = new Element("userid");
			useridElement.setText(String.valueOf(userID));
			element.addContent(useridElement);

			Element remarkElement = new Element("remark");
			remarkElement.setText(String.valueOf(remark));
			element.addContent(remarkElement);

			Element loglevelElement = new Element("loglevel");
			loglevelElement.setText(String.valueOf(loglevel));
			element.addContent(loglevelElement);

			Element remoteIpElement = new Element("ramoteip");
			remoteIpElement.setText(String.valueOf(remotecomputerip));
			element.addContent(remoteIpElement);

			Element yearElement = new Element("saveyear");
			yearElement.setText(String.valueOf(saveyear));
			element.addContent(yearElement);

			Element monthElement = new Element("savemonth");
			monthElement.setText(String.valueOf(savemonth));
			element.addContent(monthElement);

			Element dayElement = new Element("saveday");
			dayElement.setText(String.valueOf(day));
			element.addContent(dayElement);

			Element msgElement = new Element("message");
			msgElement.setText(String.valueOf(message));
			element.addContent(msgElement);

			Element clsnameElement = new Element("classname");
			clsnameElement.setText(String.valueOf(classname));
			element.addContent(clsnameElement);

			Element opernameElement = new Element("opername");
			opernameElement.setText(String.valueOf(opername));
			element.addContent(opernameElement);

			Element modenameElement = new Element("modelname");
			modenameElement.setText(String.valueOf(modelname));
			element.addContent(modenameElement);

			doc.getRootElement().addContent(element);

			XMLOutputter outp = new XMLOutputter();
			Format format = Format.getPrettyFormat();
			format.setEncoding("UTF-8");

			FileOutputStream fos = new FileOutputStream(filepath);
			outp.output(doc, fos);
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			BocoLog.error(this, e.getMessage());
		}

	}

	/**
	 * 添加保存信息的关联路径到xml
	 * 
	 * @param savetime
	 * @param modeid
	 * @param operid
	 * @param userid
	 * @param xmlfilepath
	 * @param infofilepath
	 * @param loglevel
	 */
	public void saveinfoToxml(String savetime, String modeid, String operid,
			String userid, String xmlfilepath, String infofilepath,
			String loglevel) {
		Document doc;
		Element element;
		try {
			xmlfilepath = StaticMethod
					.getFilePath("classpath:com/boco/eoms/commons/log/dao/castor/tawcommonlogfileinfo.xml");
			String infologxml = "";
			infologxml = xmlfilepath.replaceAll("/", "\\\\");

			doc = getSAXBDoc(infologxml);
			Element savetimeElement = null;

			element = new Element("fileinfo");
			element.setAttribute("id", operid);

			savetimeElement = new Element("savetime");
			savetimeElement.setText(savetime);
			element.addContent(savetimeElement);

			Element modeidElement = new Element("modeid");
			modeidElement.setText(modeid);
			element.addContent(modeidElement);

			Element operidElement = new Element("operid");
			operidElement.setText(operid);
			element.addContent(operidElement);

			Element useridElement = new Element("userid");
			useridElement.setText(userid);
			element.addContent(useridElement);

			Element filepathElement = new Element("filepath");
			filepathElement.setText(infofilepath);
			element.addContent(filepathElement);

			Element loglevelElement = new Element("loglevel");
			loglevelElement.setText(loglevel);
			element.addContent(loglevelElement);

			doc.getRootElement().addContent(element);

			XMLOutputter outp = new XMLOutputter();
			Format format = Format.getPrettyFormat();
			format.setEncoding("UTF-8");
			FileOutputStream fos = new FileOutputStream(infologxml);
			outp.output(doc, fos);
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			BocoLog.error(this, e.getMessage());
		}
	}

	/**
	 * 构造XML
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	private static Document getSAXBDoc(String filepath)
			throws FileNotFoundException, Exception {

		Document _doc = null;
		try {

			SAXBuilder sb = new SAXBuilder();

			_doc = sb.build(new FileInputStream(filepath));
			if (_doc != null) {
				return _doc;
			} else {
				BocoLog.info(TawCommonLogAppendXML.class, "制定路径的XML文件 "
						+ filepath + " 不存在!");
			}
		} catch (Exception e) {
			BocoLog.error(TawCommonLogAppendXML.class, "Document构造出现异常! "
					+ e.getMessage());
		}
		return _doc;
	}
}
