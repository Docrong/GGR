package com.boco.eoms.commons.file.service;

import java.util.Map;

import com.boco.eoms.commons.file.exception.FMException;

/**
 * <p>
 * Title:所有文件导出的业务接口(pdf,excel,word)
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 8:54:35 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IFMExportFileManager {

	/**
	 * 导入操作,由excel(pdf,word考虑后续开发)为源读入
	 * 
	 * @param map
	 *            map
	 *            <integer,list>,integer为页码,list为该页要导入的内容,list中的object为xml配置,如FMExportSample.xml中的className
	 * 
	 * @param xmlPath
	 *            导入所需的xml配置文件,如FMImportSample.xml文件
	 * @param filePath
	 *            excel(pdf,word)文件的路径
	 * @throws FMException
	 *             出错则抛导出异常
	 */
	public void export(Map map, String xmlPath, String filePath)
			throws FMException;
}
