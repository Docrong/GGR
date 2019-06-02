package com.boco.eoms.commons.system.priv.a_fsh;

import com.boco.eoms.commons.system.priv.a_fsh.NotFoundParseXMLException;

/**
 * 解析xml factory
 * 
 * @author leo
 * 
 */
public class ParseXMLFactory {

	/**
	 * 取某个xml parse的实例
	 * 
	 * @param clz
	 *            解析xml的实例包+类名
	 * @return iparsexml的一个实例
	 * @throws NotFoundParseXMLException
	 *             没找到xml实类时抛出异常
	 */
	public static ParseXML getParseXML(String clz)
			throws NotFoundParseXMLException {
		if (clz == null || "".equals(clz.trim())) {
			return new CastorParseXML();
		}
		try {
			return (ParseXML) Class.forName(clz).newInstance();
		} catch (Exception e) {
			throw new NotFoundParseXMLException(e);
		}
	}

	/**
	 * 取默认的castor xml解析类
	 * 
	 * @return castor xml解析类实例
	 */
	public static ParseXML getParseXML() {
		try {
			return getParseXML(null);
		} catch (NotFoundParseXMLException e) {
			return new CastorParseXML();
		}

	}
}
