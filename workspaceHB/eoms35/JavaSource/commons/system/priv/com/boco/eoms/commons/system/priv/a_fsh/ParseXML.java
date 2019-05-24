package com.boco.eoms.commons.system.priv.a_fsh;

import com.boco.eoms.commons.system.priv.a_fsh.ParseXMLException;

/**
 * 解析xml接口
 * 
 * @author leo
 * 
 */
public interface ParseXML {

	/**
	 * 将xml映射为对�?
	 * 
	 * @param clz
	 *            被映射的对象
	 * @param xmlPath
	 *            xml地址
	 * 
	 * @return 返回被映射的对象
	 * @throws ParseXMLException
	 *             解析时出错抛出异�?
	 */
	public Object xml2object(Class clz, String xmlPath)
			throws ParseXMLException;

}
