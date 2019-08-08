package com.boco.eoms.km.interfaces.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jMethod {

    /**
     * 根据 XML 格式的字符串创建一个 Document 对象
     *
     * @param xmlString
     * @return Document
     */
    public static Document buildDocument(String xmlString) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 根据文件路径读取 XML 文件，并创建一个 Document 对象
     * @param file
     * @return Document
     */
//	public static Document readDocument(String filePath) {
//		Document doc = null;
//		try {
//			File newFile = new File(filePath);
//			System.out.println("readDocument = " + newFile.getAbsolutePath());
//			SAXReader reader = new SAXReader();
//			reader.setEncoding("GB2312");
//			doc = reader.read(newFile);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		return doc;
//	}

    /**
     * 根据文件路径读取 XML 文件，并创建一个 Document 对象
     *
     * @param file
     * @return Document
     */
    public static Document readDocument(String filePath) {
        String xmlString = "";
        try {
            xmlString = KmInterMethod.readFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = Dom4jMethod.buildDocument(xmlString);
        return doc;
    }

    /**
     * 根据 XML 文件创建一个 Document 对象
     *
     * @param file
     * @return Document
     */
    public static Document readDocument(File file) {
        Document doc = null;
        try {
            SAXReader reader = new SAXReader();
            doc = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static int out2File(Document doc, String filePath) {
        try {
            File newFile = new File(filePath);
            System.out.println("out2File = " + newFile.getAbsolutePath());
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GB2312");
            XMLWriter writer = new XMLWriter(new FileWriter(newFile), format);
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

}
