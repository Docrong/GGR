package com.boco.eoms.commons.statistic.base.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;


public class GraphicsReportUtil {

	private static Logger logger = Logger.getLogger(GraphicsReportUtil.class);
	
	/**
	 * 通过castors解析xml，映射为javaObject
	 * @param cls  类
	 * @param str xml字符串标识形式
	 * @return
	 * @throws IOException 
	 */
	public static Object xml2object(Class cls, String str) throws IOException
	{
		Unmarshaller un = new Unmarshaller(cls);
		// 设置不验证xml以加快速度
		un.setValidation(false);
		
		Object object = null;
		ByteArrayInputStream bis = null;
		InputStreamReader isr = null;
		try {
			bis = new ByteArrayInputStream(str.getBytes("UTF-8"));
			isr= new InputStreamReader(bis,"UTF-8");
			object = un.unmarshal(isr);
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		finally
		{
			if(bis != null)
			{
				bis.close();
			}
			
			if(isr != null)
			{
				isr.close();
			}
			
		}
		
		return object;
	}
	
	/**
	 * 转换为中文名称 column\line\columnline\pie
	 * @param id
	 * @return
	 */
	public static String graphicsId2Name(String id)
	{
		String str = "";
		
		if("line".equalsIgnoreCase(id))
		{
			str = "线图";
		}
		else if("column".equalsIgnoreCase(id))
		{
			str = "柱图";
		}
		else if("columnline".equalsIgnoreCase(id))
		{
			str = "线柱结合图";
		}
		else if("pie".equalsIgnoreCase(id))
		{
			str = "饼图";
		}
		else 
		{
			str = "不是 线图，柱图，线柱结合图，饼图 中的一种";
		}
		return str;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
