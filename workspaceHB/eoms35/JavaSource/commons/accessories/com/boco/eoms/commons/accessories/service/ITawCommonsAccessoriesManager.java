package com.boco.eoms.commons.accessories.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 附件管理service类
 * </p>
 * <p>
 * Apr 10, 2007 11:02:32 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 * 
 */
public interface ITawCommonsAccessoriesManager extends Manager {
	/**
	 * Retrieves all of the tawCommonsAccessoriess
	 */
	public List getTawCommonsAccessoriess();

	/**
	 * Gets tawCommonsAccessories's information based on id.
	 * 
	 * @param id
	 *            the tawCommonsAccessories's id
	 * @return tawCommonsAccessories populated tawCommonsAccessories object
	 */
	public TawCommonsAccessories getTawCommonsAccessories(final String id);

	/**
	 * Saves a tawCommonsAccessories's information
	 * 
	 * @param tawCommonsAccessories
	 *            the object to be saved
	 */
	public void saveTawCommonsAccessories(
			TawCommonsAccessories tawCommonsAccessories);

	/**
	 * Removes a tawCommonsAccessories from the database by id
	 * 
	 * @param id
	 *            the tawCommonsAccessories's id
	 */
	public void removeTawCommonsAccessories(final String id);

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param appCode
	 * @return
	 * @throws FileUploadException
	 * @throws IOException
	 * @throws AccessoriesException
	 * @author 秦敏
	 */
	public List saveFile(HttpServletRequest request, String appCode,
			String filelist) throws AccessoriesException;

	/**
	 * 根据文件ID查询文件信息
	 * 
	 * @param fileIds
	 * @return
	 * @author 秦敏
	 */
	public List getAllFileById(String fileIds) throws AccessoriesException;

	/**
	 * 根据文件名称查询文件存放路径。
	 * 
	 * @param fileNames
	 *            文件名称，用","分割
	 * @return String[] 路径数组
	 */
	public String[] getFilePathByName(String fileNames)
			throws AccessoriesException;

	/**
	 * 取上传路径
	 * 
	 * @param appId
	 *            应用id
	 * @return
	 * @throws AccessoriesConfigException
	 */
	public String getFilePath(String appId) throws AccessoriesConfigException;

	/**
	 * 根据附件id取url
	 * 
	 * @param id
	 * @return
	 */
	public String getUrlById(String id);

	/**
	 * 外系统附件下载接口
	 *
	 * @param cnName
	 * 			  文件中文名
	 * @param url
	 *            外系统附件存放地址
	 * @param code
	 *            本系统对应的附件类型
	 * @return
	 */
	public String downFromOtherSystem(String cnName, String strRemoteAddr,
			String code);	
	/**
	 * 外系统附件下载接口
	 *
	 * @param id
	 * 			  附件文件名
	 * @return
	 */
	public TawCommonsAccessories getTawCommonsAccessoriesByName(String id);
	
	/**
	 * 附件上传接口
	 *
	 * @param id
	 * 			  附件文件名
	 * @param uploadType
	 *            上传方式
	 * @return
	 */
	public TawCommonsAccessories getSystemToOther(String id, String uploadType);
	
	public List getTawCommonsAccessoriesByMonth(String beginyear,String beginmonth);
}
