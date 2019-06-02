/*
 * Created on 2007-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.xml;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;

import org.exolab.castor.xml.Unmarshaller;

import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FlowDefineSchema {

	private static FlowDefineSchema flowDefineSchema = null;

	public static FlowDefineSchema getInstance() throws Exception {
		if (flowDefineSchema == null) {
			flowDefineSchema = new FlowDefineSchema();
		}
		return flowDefineSchema;
	}

//	public WorkFlowRule loadXml(String path) throws Exception {
//		try {
//			if(path==null || path.equals("")){
//				throw new ParseXMLException();
//			}
//			WorkFlowRule rule = (WorkFlowRule) ParseXmlService
//					.create()
//					.xml2object(
//							WorkFlowRule.class,
//							StaticMethod.getFilePathForUrl(path));
//			return rule;
//		} catch (ParseXMLException e) {
//			throw new Exception("读取配置文件'"+path+"'出错");
//		}
//	}
	
	
	public WorkFlowRule loadXml(String path) throws Exception {
		try {
//			 用于读取xml文件
			FileReader in = null;
			// 返回obj,调用者需强制转型
			Object obj = null;
			try {
				// 缓存
				// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();
				Unmarshaller un = new Unmarshaller(WorkFlowRule.class);
				// 设置不验证xml以加快速度
				un.setValidation(false);
				// Unmarshaller un = new Unmarshaller(cls);
				// un.setResolver(cdr);

				// 读取xml文件
				// in = new FileReader(finalPath);

				InputStreamReader isr = new InputStreamReader(StaticMethod
						.getFileInputStream(path),"utf-8");
				System.out.println("encoding======"+isr.getEncoding());
				obj = un.unmarshal(isr);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ParseXMLException(e);
			} finally {
				if (in != null) {
					try {
						// 关闭
						in.close();
					} catch (IOException e) {
						throw new ParseXMLException(e);
					}
				}
			}
			WorkFlowRule rule = (WorkFlowRule)obj;
//			WorkFlowRule rule = (WorkFlowRule) ParseXmlService
//					.create()
//					.xml2object(
//							WorkFlowRule.class,
//							StaticMethod.getFilePathForUrl("classpath:config/commonfault-config.xml"));
			return rule;
		} catch (ParseXMLException e) {
			e.printStackTrace();
			throw new Exception("读取配置文件'config/roleConfig.xml'出错");
		}
	}	
	
   
	public WorkFlowRule loadXml() throws Exception {
		try {
//			 用于读取xml文件
			FileReader in = null;
			// 返回obj,调用者需强制转型
			Object obj = null;
			try {
				// 缓存
				// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();
				Unmarshaller un = new Unmarshaller(WorkFlowRule.class);
				// 设置不验证xml以加快速度
				un.setValidation(false);
				// Unmarshaller un = new Unmarshaller(cls);
				// un.setResolver(cdr);

				// 读取xml文件
				// in = new FileReader(finalPath);
				InputStream input = (InputStream)StaticMethod
				.getFileInputStream("classpath:config/commonfault-config.xml");
				

				InputStreamReader isr = new InputStreamReader(StaticMethod
						.getFileInputStream("classpath:config/commonfault-config.xml"),"utf-8");
				System.out.println("encoding======"+isr.getEncoding());
				obj = un.unmarshal(isr);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ParseXMLException(e);
			} finally {
				if (in != null) {
					try {
						// 关闭
						in.close();
					} catch (IOException e) {
						throw new ParseXMLException(e);
					}
				}
			}
			WorkFlowRule rule = (WorkFlowRule)obj;
//			WorkFlowRule rule = (WorkFlowRule) ParseXmlService
//					.create()
//					.xml2object(
//							WorkFlowRule.class,
//							StaticMethod.getFilePathForUrl("classpath:config/commonfault-config.xml"));
			return rule;
		} catch (ParseXMLException e) {
			e.printStackTrace();
			throw new Exception("读取配置文件'config/roleConfig.xml'出错");
		}
	}	
	
	
	
}