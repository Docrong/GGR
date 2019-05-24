package com.boco.eoms.commons.fileconfig.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.IParseXmlManager;

/**
 * <p>
 * Title:对外发布的ParseXmlService，
 * </p>
 * <p>
 * Description: 使用ParseXmlService.create().xml2object()直接调用xml解析
 * </p>
 * <p>
 * Date:Apr 22, 2007 7:51:19 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ParseXmlService {
	/**
	 * 单例
	 */
	private static ParseXmlService instance;

	/**
	 * 解析xml的manager
	 */
	private IParseXmlManager mgr;

	/**
	 * 通过实现xml2sheets业务方法,解析xml
	 * 
	 * @param cls
	 *            类
	 * @param key
	 *            xml mapping配置文件的key值
	 * @param filePath
	 *            xml配置文件路径,包含文件名
	 * @return 解析的对象
	 * @see ParseXmlManagerImpl#xml2object(Class, String, String)
	 * @throws ParseXMLException
	 *             解析出错抛异常
	 */
	public Object xml2object(Class cls, String key, String filePath)
			throws ParseXMLException {
		return this.mgr.xml2object(cls, key, filePath);
	}

	/**
	 * 将obj保存为filePath(abc.xml)
	 * 
	 * @param obj
	 *            OR对象
	 * @param filePath
	 *            要保存的地址
	 * @param key
	 *            xml mapping配置文件的key值
	 * @throws ParseXMLException
	 *             保存出错抛出异常
	 */
	public void object2xml(Object obj, String key, String filePath)
			throws ParseXMLException {
		mgr.object2xml(obj, key, filePath);
	}
	
	public Object xml2object(Class cls, String key, String filePath,boolean isMapPath)
	throws ParseXMLException {
		return this.mgr.xml2object(cls, key, filePath,isMapPath);
	}
	
	public void object2xml(Object obj, String key, String filePath,boolean isMapPath)
	throws ParseXMLException {
		mgr.object2xml(obj, key, filePath,isMapPath);
	}
	
	public Object xml2object(Class cls, String filePath)
	throws ParseXMLException {
		return this.mgr.xml2object(cls, filePath);
	}
	
	public Object xml2objectWithKey(Class cls, String key)
	throws ParseXMLException {
		return this.mgr.xml2objectWithKey(cls, key);
	}

	/**
	 * 私有构造方法
	 * 
	 */
	private ParseXmlService() {
		mgr = (IParseXmlManager) ApplicationContextHolder.getInstance()
				.getBean("ParseXmlManagerImpl");
	}

	/**
	 * 创建ParseXmlManagerService
	 * 
	 * @return 创建本身，单例
	 */
	public static ParseXmlService create() {
		if (instance == null) {
			instance = new ParseXmlService();
		}
		return instance;
	}

}
