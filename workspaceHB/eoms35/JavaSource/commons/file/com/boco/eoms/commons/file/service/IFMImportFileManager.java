package com.boco.eoms.commons.file.service;

import java.util.Map;

import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.exception.FMImportFileException;

/**
 * <p>
 * Title: 为所有导入文件(excel,word,pdf)的基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 8:25:10 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public interface IFMImportFileManager {
	/**
	 * 导入操作,由excel(pdf,word考虑后续开发)为源读入
	 * 
	 * @param xmlPath
	 *            导入所需的xml配置文件,如FMImportSample.xml文件
	 * @param filePath
	 *            excel(pdf,word)文件的路径
	 * @return map<string,list>,string为xml所配的sheet num做为key,list为className的列表
	 * @throws FMImportFileException
	 *             导入出错则抛异常
	 */
	public Map impt(String xmlPath, String filePath)
			throws FMException;
}
