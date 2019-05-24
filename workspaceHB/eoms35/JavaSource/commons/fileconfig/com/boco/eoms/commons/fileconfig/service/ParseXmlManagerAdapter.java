package com.boco.eoms.commons.fileconfig.service;

import java.util.Properties;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 4:50:56 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public abstract class ParseXmlManagerAdapter implements IParseXmlManager {

	/**
	 * castor mapping 文件路径
	 */
	protected Properties mappingPath;

	public Properties getMappingPath() {
		return mappingPath;
	}

	public void setMappingPath(Properties mappingPath) {
		this.mappingPath = mappingPath;
	}

}
