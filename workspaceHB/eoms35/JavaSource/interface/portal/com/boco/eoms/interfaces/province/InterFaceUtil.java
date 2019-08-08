package com.boco.eoms.interfaces.province;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;

public class InterFaceUtil {

    private static String configPath = "/com/boco/eoms/interfaces/province/config/province-util.xml";

    public void BuildXMLDoc(String provinceID, String baseSchema,
                            String startTime, String endTime, List list) throws IOException,
            JDOMException {
        String user_name = XmlManage.getFile(configPath).getProperty("Interface.Ftp.User");
        String Password = XmlManage.getFile(configPath).getProperty("Interface.Ftp.Password");
        String ftpPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.ftpXmlPath");
        String xmlPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.XmlPath");
//		String copyFileXmlPath =  XmlManage.getFile(configPath).getProperty("Interface.Ftp.CopyFileXmlPath");
        String ip = XmlManage.getFile(configPath).getProperty("Interface.Ftp.Ip");
        int port = StaticMethod.null2int(XmlManage.getFile(configPath).getProperty("Interface.Ftp.Port"));
        // 创建根节点 root;
        Element root = new Element("root");

//		allCopyFile(xmlPath,copyFileXmlPath);
//		delAll(xmlPath);

        String time = getLocalString("yyyyMMddHHmmss");
        String id = String.valueOf(findID(xmlPath, provinceID + "_" + baseSchema + "_" + getLocalString("yyyyMMdd")) + 1);
        String no = "";
        if (id.length() == 1) {
            no = "00" + id;
        } else if (id.length() == 2) {
            no = "0" + id;
        } else {
            no = id;
        }
        String xmlName = provinceID + "_" + baseSchema + "_" + time + "_" + no;
        // 根节点添加到文档中；
        Document Doc = new Document(root);
        root.addContent(new Element("ProvinceID").setText(provinceID));
        root.addContent(new Element("BaseSchema").setText(baseSchema));
        root.addContent(new Element("StartTime").setText(startTime));
        root.addContent(new Element("EndTime").setText(endTime));

        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n";
        String BaseItem = "";
        // 此处 for 循环可替换成 遍历 数据库表的结果集操作;
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                BaseMain wfWorksheetMain = (BaseMain) list.get(i);
                String BaseItems = "<BaseItem>\n<BaseSN>"
                        + wfWorksheetMain.getSheetId() + "</BaseSN>\n<BaseTitle>"
                        + wfWorksheetMain.getTitle()
                        + "</BaseTitle>\n</BaseItem>\n";
                BaseItem += BaseItems;

            }
        }
        String endStr = "</root>";
        String outputstr = str + BaseItem + endStr;
        System.out.println(str + BaseItem + endStr);
        try {
            root.addContent(new Element("SvcCont").addContent(new CDATA(outputstr)));
        } catch (Exception err) {
            err.printStackTrace();
        }


        // root.addContent(elements);
        XMLOutputter XMLOut = new XMLOutputter();

//		Format format = Format.getPrettyFormat();
//	    format.setEncoding("ISO-8859-1");
//	    XMLOut.setFormat(format);

        // 输出 user.xml 文件；
        XMLOut.output(Doc, new FileOutputStream(xmlPath + xmlName + ".xml"));
        Download fu = new Download();
        fu.connectServer(ip, port, user_name, Password, ftpPath);
        fu.upload(xmlPath + xmlName + ".xml", xmlName + ".xml");


    }

    public void getUPloadHtml(String path, String fileName) {
        String user_name = XmlManage.getFile(configPath).getProperty("Interface.Ftp.User");
        String Password = XmlManage.getFile(configPath).getProperty("Interface.Ftp.Password");
        String ftpPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.ftpHtmlPath");
        String xmlPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.XmlPath");
//		String copyFileXmlPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.CopyFileXmlPath");
        String ip = XmlManage.getFile(configPath).getProperty("Interface.Ftp.Ip");
        int port = StaticMethod.null2int(XmlManage.getFile(configPath).getProperty("Interface.Ftp.Port"));
        Download fu = new Download();
        fu.connectServer(ip, port, user_name, Password, ftpPath);
        fu.upload(path + fileName, fileName);
    }

    public static String getLocalString(String Format) {
        java.util.Date currentDate = new java.util.Date();
//		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
//				"yyyyMMddHHmmss");
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
                Format);
        String date = dateFormat.format(currentDate);

        return date;
    }


//	public static void CopyFile(String from_name, String to_name) {
//		try {
//			File from_file = new File(from_name);
//			File to_file = new File(to_name);
//			String parent = to_file.getParent();
//			File dir = new File(parent);
//			FileInputStream from = null;
//			FileOutputStream to = null;
//			try {
//				from = new FileInputStream(from_file);
//				to = new FileOutputStream(to_file);
//				byte[] buffer = new byte[4096];
//				int bytes_read;
//
//				while ((bytes_read = from.read(buffer)) != -1)
//					to.write(buffer, 0, bytes_read);
//			} finally {
//				if (from != null)
//					try {
//						from.close();
//					} catch (IOException e) {
//						;
//					}
//
//				if (to != null)
//					try {
//						to.close();
//					} catch (IOException e) {
//						;
//					}
//			}
//		} catch (java.io.IOException e) {
//
//		}

    //	}
//	public static void allCopyFile(String XmlPath, String CopyFileXmlPath) {
//		File file = new File(XmlPath);
////		File f2 = new File("D:\\test3\\");
//		for(int i=0;i<file.listFiles().length;i++){
//			String file1 =file.listFiles()[i].toString();
//			int l=file1.length();
//			int j=file1.lastIndexOf("\\");
//			String name=file1.substring(j,l);
//			CopyFile(file1,CopyFileXmlPath+name);
//		}
//
//
//	}
//	public static void delAll(String  xmlPath) throws IOException {
//		File f = new File(xmlPath);
//		if(f.listFiles().length>0){
//		for(int i=0;i<f.listFiles().length;i++){
//			File file = f.listFiles()[i];
//			file.delete();
//		}
//		}
//		
//		
//	}
    public static int findID(String xmlPath, String xmlName) throws IOException {
        int id = 0;
        File f = new File(xmlPath);
        if (f == null)
            throw new IOException("not find " + xmlPath);
        for (int i = 0; i < f.listFiles().length; i++) {
            File file = f.listFiles()[i];
            if (file.toString().indexOf(xmlName) > -1) {
                id++;
            }
//			file.
        }
        return id;

    }

    public static void main(String[] args) {
        try {
            // InterFaceUtil j2x = new InterFaceUtil();
            // System.out.println("生成 mxl 文件...");
            // ArrayList list = new ArrayList();
            // j2x.BuildXMLDoc("311", "001", "2008-04-05 12:01:00",
            // "2008-04-05 13:01:00", list);
            // java.util.Date currentDate = new java.util.Date();
            // java.text.SimpleDateFormat dateFormat = new
            // java.text.SimpleDateFormat(
            // "yyyyMMddHHmmss");
            // String date = dateFormat.format(currentDate);
            // System.out.println("date:"+date);
//			File f1 = new File("D:\\test2\\");
//			File f2 = new File("D:\\test3\\");
//			for(int i=0;i<f1.listFiles().length;i++){
//				String f3 =f1.listFiles()[i].toString();
//				int l=f3.length();
//				int j=f3.lastIndexOf("\\");
//				String name=f3.substring(j,l);
//				CopyFile(f3, "D:\\test3\\"+name);
//			}
//	
//			File f = new File("D:\\test2\\");
//			f.delete();
////			delAll("D:\\test2\\");
//			String xmlPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.XmlPath");
//			String copyFileXmlPath=XmlManage.getFile(configPath).getProperty("Interface.Ftp.CopyFileXmlPath");
////			allCopyFile(xmlPath,copyFileXmlPath);
            int id = findID("D:\\test2\\", "d") + 1;
            System.out.println(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
