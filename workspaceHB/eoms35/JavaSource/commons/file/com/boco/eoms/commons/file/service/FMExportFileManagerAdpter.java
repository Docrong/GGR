package com.boco.eoms.commons.file.service;

import com.boco.eoms.commons.fileconfig.service.IParseXmlManager;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 9:17:08 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public abstract class FMExportFileManagerAdpter implements IFMExportFileManager {

	/**
	 * 解析xml
	 */
	protected IParseXmlManager fmParseXmlManager;

	public IParseXmlManager getFmParseXmlManager() {
		return fmParseXmlManager;
	}

	public void setFmParseXmlManager(IParseXmlManager fmParseXmlManager) {
		this.fmParseXmlManager = fmParseXmlManager;
	}

}
