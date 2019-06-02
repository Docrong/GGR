/**
 * 
 */
package com.boco.eoms.commons.log.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.commons.log.dao.ILogConfigDAO;
import com.boco.eoms.commons.log.exceptions.DocumentCreateException;
import com.boco.eoms.commons.log.exceptions.LogConfigDuplicateException;
import com.boco.eoms.commons.log.exceptions.LogConfigNotFoundException;
import com.boco.eoms.commons.log.exceptions.LogConfigReflashException;
import com.boco.eoms.commons.log.model.ILogConfig;
import com.boco.eoms.commons.log.model.LogConfig;
import com.boco.eoms.commons.log.service.impl.LogConfigDom4jFactoryBean;
import com.boco.eoms.commons.log.util.LogConfigXmlConstant;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 2:09:13 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigXmlDom4jDAO implements ILogConfigDAO {

	/**
	 * logConfigDom4jFactoryBean
	 */
	private LogConfigDom4jFactoryBean logConfigDom4jFactoryBean = null;

	/**
	 * @return the logConfigDom4jFactoryBean
	 */
	public LogConfigDom4jFactoryBean getLogConfigDom4jFactoryBean() {
		return logConfigDom4jFactoryBean;
	}

	/**
	 * @param logConfigDom4jFactoryBean
	 *            the logConfigDom4jFactoryBean to set
	 */
	public void setLogConfigDom4jFactoryBean(
			LogConfigDom4jFactoryBean logConfigDom4jFactoryBean) {
		this.logConfigDom4jFactoryBean = logConfigDom4jFactoryBean;
	}

	/**
	 * 
	 */
	public LogConfigXmlDom4jDAO() {
		super();
	}

	/**
	 * @param logConfigDom4jFactoryBean
	 */
	public LogConfigXmlDom4jDAO(
			LogConfigDom4jFactoryBean logConfigDom4jFactoryBean) {
		super();
		this.logConfigDom4jFactoryBean = logConfigDom4jFactoryBean;
	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#deleteLogConfig(java.lang.Object,
	 *      com.boco.eoms.commons.log.model.ILogConfig)
	 */
	public void deleteLogConfig(Object modelId, ILogConfig logConfig)
			throws LogConfigNotFoundException, LogConfigDuplicateException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#findLogConfig(java.lang.Object,
	 *      java.lang.Object)
	 */
	//public ILogConfig findLogConfig(Object modelId, Object operId)
	public ILogConfig findLogConfig(Object operId)
			throws LogConfigNotFoundException, LogConfigDuplicateException {
		ILogConfig logConfig = null;
		StringBuffer xpathSB = new StringBuffer("//");
		Document doc = null;
		try {
			doc = logConfigDom4jFactoryBean.getDocument("aa1");
		} catch (DocumentCreateException e) {
			throw new LogConfigNotFoundException(e.getMessage());
		}
		// 合并xpath,//element[@attr=operId]
		xpathSB.append(LogConfigXmlConstant.XML_ELEMENT);
		xpathSB.append("[@");
		xpathSB.append(LogConfigXmlConstant.XML_OPERID);
		xpathSB.append("=");
		xpathSB.append((String) operId);
		xpathSB.append("]");

		List logConfigList = doc.selectNodes(xpathSB.toString());
		int size = logConfigList.size();
		if (size == 0) {
			throw new LogConfigNotFoundException("operId=" + operId + "的节点不存在！");
		}
		//if (size > 1) {
		//	throw new LogConfigDuplicateException("modelId=" + modelId
		//			+ ",operId=" + operId + "的节点数为" + size);
		//}
		Element element = (Element) logConfigList.get(0);
		//Element root = (Element) doc.selectSingleNode("/"
				//+ LogConfigXmlConstant.XML_ROOT);
		logConfig = new LogConfig();
		logConfig.setModelId(element.attributeValue("modelId"));
		logConfig.setModelName(element.attributeValue("modelName"));
		logConfig.setOperId(element.attributeValue("operId"));
		logConfig.setOperName(element.attributeValue("operName"));
		logConfig.setOperDesc(element.attributeValue("operDesc"));

		return logConfig;
	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#listChildLogConfig(java.lang.Object,
	 *      java.lang.Object)
	 */
	public List listChildLogConfig(Object modelId, Object operId) throws LogConfigNotFoundException {
		List returnList = new ArrayList();
		ILogConfig logConfig = null;
		StringBuffer xpathSB = new StringBuffer("//");
		Document doc = null;
		Element element = null;
		try {
			doc = logConfigDom4jFactoryBean.getDocument((String) modelId);
		} catch (DocumentCreateException e) {
			throw new LogConfigNotFoundException(e.getMessage());
		}
		//合并xpath,//element[@attr=operId]/*
		xpathSB.append(LogConfigXmlConstant.XML_ELEMENT);
		xpathSB.append("[@");
		xpathSB.append(LogConfigXmlConstant.XML_OPERID);
		xpathSB.append("=");
		xpathSB.append((String) operId);
		xpathSB.append("]/*");
		Element root = (Element) doc.selectSingleNode("/"
				+ LogConfigXmlConstant.XML_ROOT);
		String modelName = root.attributeValue("modelName");
		List logConfigList = doc.selectNodes(xpathSB.toString());
		//迭代，生成子结点列表
		for(Iterator it=logConfigList.iterator();it.hasNext();) {
			logConfig = new LogConfig();
			element = (Element)it.next();
			logConfig.setModelId((String) modelId);
			logConfig.setModelName(modelName);
			logConfig.setOperId(element.attributeValue("operId"));
			logConfig.setOperName(element.attributeValue("operName"));
			logConfig.setOperDesc(element.attributeValue("operDesc"));
			logConfig.setLeaf(element.attributeValue("leaf"));
			returnList.add(logConfig);
		}
		return returnList;
	}

	public List findAllLogConfig(Object modelId) throws LogConfigNotFoundException,LogConfigDuplicateException
	{
		List returnList = new ArrayList();
		ILogConfig logConfig = null;
		StringBuffer xpathSB = new StringBuffer("//");
		Document doc = null;
		Element element = null;
		try {
			doc = logConfigDom4jFactoryBean.getDocument((String) modelId);
		} catch (DocumentCreateException e) {
			throw new LogConfigNotFoundException(e.getMessage());
		}
		//合并xpath,//element[@attr=operId]/*
		xpathSB.append(LogConfigXmlConstant.XML_ELEMENT);
		//xpathSB.append("[@");
		//xpathSB.append(LogConfigXmlConstant.XML_OPERID);
		//xpathSB.append("=");
		//xpathSB.append((String) operId);
		//xpathSB.append("]/*");
		Element root = (Element) doc.selectSingleNode("/"
				+ LogConfigXmlConstant.XML_ROOT);
		String modelName = root.attributeValue("modelName");
		List logConfigList = doc.selectNodes(xpathSB.toString());
		//迭代，生成子结点列表
		for(Iterator it=logConfigList.iterator();it.hasNext();) {
			logConfig = new LogConfig();
			element = (Element)it.next();
			logConfig.setModelId((String) modelId);
			logConfig.setModelName(modelName);
			logConfig.setOperId(element.attributeValue("operId"));
			logConfig.setOperName(element.attributeValue("operName"));
			logConfig.setOperDesc(element.attributeValue("operDesc"));
			returnList.add(logConfig);
		}
		return returnList;
	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#modifyLogConfig(java.lang.Object,
	 *      com.boco.eoms.commons.log.model.ILogConfig)
	 */
	public void modifyLogConfig(Object modelId, ILogConfig logConfig)
			throws LogConfigNotFoundException, LogConfigDuplicateException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#reflashLogConfigByModelId(java.lang.Object)
	 */
	public void reflashLogConfigByModelId(Object modelId)
			throws LogConfigReflashException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#reflashLogConfigByOperId(java.lang.Object)
	 */
	public void reflashLogConfigByOperId(Object operId)
			throws LogConfigReflashException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#reflashLogConfigs()
	 */
	public void reflashLogConfigs() throws LogConfigReflashException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.log.dao.ILogConfigDAO#saveLogConfig(java.lang.Object,
	 *      com.boco.eoms.commons.log.model.ILogConfig)
	 */
	public ILogConfig saveLogConfig(Object modelId, ILogConfig logConfig)
			throws LogConfigNotFoundException, LogConfigDuplicateException {
		// TODO Auto-generated method stub
		return null;
	}

}
