package com.boco.eoms.commons.fileconfig.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.ParseXmlManagerAdapter;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 5:04:31 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ParseXmlManagerImpl extends ParseXmlManagerAdapter {

	/**
	 * 通过实现xml2sheets业务方法,解析xml
	 * 
	 * @param cls
	 *            类
	 * @param key
	 *            xml mapping配置文件的key值
	 * @param filePath
	 *            xml配置文件路径,包含文件名
	 * @return 返回解析的对象
	 * @throws ParseXMLException
	 */
	public Object xml2object(Class cls, String key, String filePath)
			throws ParseXMLException {
		return this.xml2object(cls,key,filePath,true);
	}

	public void object2xml(Object obj, String key, String filePath)
			throws ParseXMLException {

		this.object2xml(obj,key,filePath,true);

	}
	
	/**
	 * 通过实现xml2sheets业务方法,解析xml
	 * 
	 * @param cls
	 *            类
	 * @param key
	 *            xml mapping配置文件的key值
	 * @param filePath
	 *            xml配置文件路径,包含文件名
	 * @param isMapPath
	 *            标识key是mapping配置文件的key值还是mapping文件的路径名，true代表是配置文件key值
	 * @return 返回解析的对象
	 * @throws ParseXMLException
	 */
	public void object2xml(Object obj, String key, String filePath,boolean isMapPath)
	throws ParseXMLException {
		String mp = key;
		if(isMapPath)
			mp = (String) mappingPath.get(key);
		
		// castor 用于加载mapping文件
		Mapping mapping = new Mapping();

		FileWriter writer = null;
		File file = new File(StaticMethod.getFilePath(filePath));
		// Create a File to marshal to
		try {
			// 加载mapping文件
			mapping.loadMapping(StaticMethod.getFileUrl(mp));
			if (!file.exists()) {
				file.createNewFile();
			}
			// 使用filepath建立writer
			writer = new FileWriter(file);
			// 通过mapping文件写文件
			Marshaller marshaller = new Marshaller(writer);
			marshaller.setMapping(mapping);
			marshaller.marshal(obj);
		} catch (IOException e) {
			throw new ParseXMLException(e);
		} catch (MappingException e) {
			throw new ParseXMLException(e);
		} catch (MarshalException e) {
			throw new ParseXMLException(obj
					+ "不是有效castor mapping对象,error message:" + e.getMessage());
		} catch (ValidationException e) {
			throw new ParseXMLException(obj + "castor 验证出错error message:"
					+ e.getMessage());
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				throw new ParseXMLException(e);
			}
		}
	}
	
	/**
	 * 通过实现xml2sheets业务方法,解析xml
	 * 
	 * @param cls
	 *            类
	 * @param key
	 *            xml mapping配置文件的key值
	 * @param filePath
	 *            xml配置文件路径,包含文件名
	 * @param isMapPath
	 *            标识key是mapping配置文件的key值还是mapping文件的路径名，true代表是配置文件key值
	 * @return 返回解析的对象
	 * @throws ParseXMLException
	 */
	public Object xml2object(Class cls, String key, String filePath, boolean isMapPath)
			throws ParseXMLException {
//		 取路径
		String mp = key;
		if(isMapPath)
			mp = (String) mappingPath.get(key);

		// castor 用于加载mapping文件
		Mapping mapping = new Mapping();
		// 用于读取xml文件
		FileReader in = null;
		// 返回obj,调用者需强制转型
		Object obj = null;
		try {

			// 加载mapping文件
			mapping.loadMapping(StaticMethod.getFileUrl(mp));
			// 缓存
			// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();
			Unmarshaller un = new Unmarshaller(cls);
			// 设置不验证xml以加快速度
			un.setValidation(false);
			// Unmarshaller un = new Unmarshaller(cls);
			// un.setResolver(cdr);
			un.setMapping(mapping);
			// 读取xml文件
			// in = new FileReader(finalPath);

			InputStreamReader isr = new InputStreamReader(StaticMethod
					.getFileInputStream(filePath));
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
		return obj;
	}
	
	/**
	 * 通过实现xml2sheets业务方法,解析xml（不需mapping文件）
	 * 
	 * @param cls
	 *            类
	 * @param filePath
	 *            xml配置文件路径,包含文件名
	 * @return 返回解析的对象
	 * @throws ParseXMLException
	 */
	public Object xml2object(Class cls, String filePath)
			throws ParseXMLException {
		// 用于读取xml文件
		FileReader in = null;
		// 返回obj,调用者需强制转型
		Object obj = null;
		try {
			// 缓存
			// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();
			Unmarshaller un = new Unmarshaller(cls);
			// 设置不验证xml以加快速度
			un.setValidation(false);
			// Unmarshaller un = new Unmarshaller(cls);
			// un.setResolver(cdr);

			// 读取xml文件
			// in = new FileReader(finalPath);

			InputStreamReader isr = new InputStreamReader(StaticMethod
					.getFileInputStream(filePath));
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
		return obj;
	}
	
	/**
	 * 通过实现xml2sheets业务方法,解析xml（不需mapping文件）
	 * 
	 * @param cls
	 *            类
	 * @param key
	 *            applicationContext-fileconfig.xml的key
	 * @return 返回解析的对象
	 * @throws ParseXMLException
	 */
	public Object xml2objectWithKey(Class cls, String key)
			throws ParseXMLException {
		// 用于读取xml文件
		FileReader in = null;
		// 返回obj,调用者需强制转型
		Object obj = null;
		
		String filePath = (String) mappingPath.get(key);
		try {
			// 缓存
			// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();
			Unmarshaller un = new Unmarshaller(cls);
			// 设置不验证xml以加快速度
			un.setValidation(false);
			// Unmarshaller un = new Unmarshaller(cls);
			// un.setResolver(cdr);

			// 读取xml文件
			// in = new FileReader(finalPath);

			InputStreamReader isr = new InputStreamReader(StaticMethod
					.getFileInputStream(filePath));
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
		return obj;
	}
}
