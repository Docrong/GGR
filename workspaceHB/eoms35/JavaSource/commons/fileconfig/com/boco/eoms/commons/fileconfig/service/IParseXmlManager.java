package com.boco.eoms.commons.fileconfig.service;

import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;

/**
 * <p>
 * Title: 解析xml业务接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 2:09:59 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public interface IParseXmlManager {

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
			throws ParseXMLException;

	/**
	 * 将obj保存为filePath(abc.xml)
	 * 
	 * @param obj
	 *            OR对象
	 * @param filePath
	 *            要保存的地址
	 * @param key
	 *            xml mapping配置文件的key值
	 * 
	 * @throws ParseXMLException
	 *             保存出错抛出异常
	 */
	public void object2xml(Object obj, String key, String filePath)
			throws ParseXMLException;
	
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
	throws ParseXMLException;
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
	public void object2xml(Object obj, String key, String filePath, boolean isMapPath)
	throws ParseXMLException;
	
	/**
	 * 通过实现xml2sheets业务方法,解析xml
	 * 
	 * @param cls
	 *            类
	 * @param filePath
	 *            xml配置文件路径,包含文件名
	 * @return 返回解析的对象
	 * @throws ParseXMLException
	 */
	public Object xml2object(Class cls, String filePath)
	throws ParseXMLException;
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
			throws ParseXMLException;

}
