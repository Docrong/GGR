package com.boco.eoms.commons.statistic.base.anychart.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Template-XML操作方法
 * @author tomilee
 *
 */
public class ReportTool {
	
	/**
	 * 读取XML - 模板文件
	 * @param path
	 */
	public static Document readDocument(String path){		
		Document document = null;
		SAXReader saxBuilder = null;
		
		saxBuilder = new SAXReader();
		FileInputStream file = null;
		try{
			file = new FileInputStream(path);
			InputStreamReader reader = new InputStreamReader(file);
//			saxBuilder.setEncoding("UTF-8");
			document = saxBuilder.read(reader,"utf-8");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(file != null)
			{
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return document;
	}
	
	/**
	 * 输出XML
	 * @param document
	 * @param fileName
	 */
	public static void write(Document document, String filepath) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			//format.setEncoding("utf-8");
			File file = new File(filepath);
			FileOutputStream output = new FileOutputStream(file);
			OutputStreamWriter outputwriter = new OutputStreamWriter(output,"utf-8");
			XMLWriter writer = new XMLWriter(outputwriter, format);
			writer.write(document);
			writer.flush();
			writer.close();
			outputwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加节点属性
	 * @param element
	 * @param hmp
	 */
	public static void addAttribute(Element element, HashMap hmp){
		Set keys = hmp.keySet();
		Iterator iter = keys.iterator();
		while(iter.hasNext()){
			String keyname = (String)iter.next();
			if(element.attribute(keyname)==null){
				element.addAttribute(keyname, (String)hmp.get(keyname));
			}else{
				Attribute attribute = element.attribute(keyname);
				attribute.setValue((String)hmp.get(keyname));
			}
		}
	}
	
	/**
	 * 按规则配置线条颜色值16位
	 * @param i
	 * @return
	 */
	public static String getLineColor(int i){
		String color = "";
		switch (i) {
			case 12:
				color = "0x0066FF";
				break;
			case 11:
				color = "0xFF9933";
				break;
			case 10:
				color = "0x9966FF";
				break;
			case 9:
				color = "0xCC00FF";
				break;
			case 8:
				color = "0xFFEBCD";
				break;
			case 7:
				color = "0xFF00FF";
				break;
			case 6:
				color = "0xFF6600";
				break;
			case 5:
				color = "0x66FF00";
				break;
			case 4:
				color = "0xFFFF33";
				break;
			case 3:
				color = "0x808000";
				break;
			case 2:
				color = "0xFF0033";
				break;
			case 1:
				color = "0x9400D3";
				break;
			case 0:
				color = "0x000000";
				break;
		}
		return color;
	}
	
	/**
	 * 按规则配置柱条颜色值16位
	 * @param i
	 * @return
	 */
	public static String getColumnColor(int i){
		String color = "";
		switch (i) {
			case 12:
				color = "0xFF3366";
				break;
			case 11:
				color = "0x6495ed";				
				break;
			case 10:
				color = "0xFFB6C1";
				break;
			case 9:
				color = "0x00FFFF";
				break;
			case 8:
				color = "0x33CCCC";
				break;
			case 7:
				color = "0x0066FF";
				break;
			case 6:
				color = "0x9370db";
				break;
			case 5:
				color = "0x00fa9a";
				break;
			case 4:
				color = "0xff4500";
				break;
			case 3:
				color = "0xffd700";
				break;
			case 2:
				color = "0x00FF00";
				break;
			case 1:
				color = "0x0000CD";
				break;
			case 0:
				color = "0xC9A971";
				break;
		}
		return color;
	}
	/**
	 * 饼图16位色
	 * @param i
	 * @return
	 */
	public static String getPieColor(int i){
		String color = "";
		switch (i) {
			case 12:
				color = "0xFF3366";
				break;
			case 11:
				color = "0xCB9236";				
				break;
			case 10:
				color = "0xFFB6C1";
				break;
			case 9:
				color = "0xEFB14D";
				break;
			case 8:
				color = "0xDBAD60";
				break;
			case 7:
				color = "0xFAB442";
				break;
			case 6:
				color = "0xABA38D";
				break;
			case 5:
				color = "0xC9A971";
				break;
			case 4:
				color = "0x839AB2";
				break;
			case 3:
				color = "0x4D8FE5";
				break;
			case 2:
				color = "0x7397C1";
				break;
			case 1:
				color = "0x418CF0";
				break;
			case 0:
				color = "0x0000CD";
				break;
		}
		return color;
	}
	
}
