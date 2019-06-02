package com.boco.eoms.commons.accessories.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;
import com.boco.eoms.commons.accessories.util.AccessoriesUtil;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:附件管理service实现类
 * </p>
 * <p>
 * Apr 10, 2007 11:02:48 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 * 
 */
public class TawCommonsAccessoriesManagerFileuploadImpl extends BaseManager
		implements ITawCommonsAccessoriesManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#getFilePath(java.lang.String)
	 */
	public String getFilePath(String appId) throws AccessoriesConfigException {
		// TODO Auto-generated method stub
		return null;
	}

	private TawCommonsAccessoriesDao dao;

	private ITawCommonsAccessoriesConfigManager configManager;

	private String filePath;

	private int tempFileSize;

	private String fileName;
 
	
	private TawCommonsAccessories accessories;
 

	public int getTempFileSize() {
		return tempFileSize;
	}

	public void setTempFileSize(int tempFileSize) {
		this.tempFileSize = tempFileSize;
	}

	public ITawCommonsAccessoriesConfigManager getConfigManager() {
		return configManager;
	}

	public void setConfigManager(
			ITawCommonsAccessoriesConfigManager configManager) {
		this.configManager = configManager;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public TawCommonsAccessoriesDao getDao() {
		return dao;
	}

	public void setDao(TawCommonsAccessoriesDao dao) {
		this.dao = dao;
	}
	
	public String downFromOtherSystem(String cnName,String strRemoteAddr, String code) {
		return fileName;
	}

	public TawCommonsAccessories getSystemToOther(String id, String contentPath) {
		return accessories;
	}
 
	/**
	 * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#getTawCommonsAccessoriess(com.boco.eoms.commons.accessories.model.TawCommonsAccessories)
	 */
	public List getTawCommonsAccessoriess() {
		return dao.getTawCommonsAccessoriess();
	}

	/**
	 * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#getTawCommonsAccessories(String
	 *      id)
	 */
	public TawCommonsAccessories getTawCommonsAccessories(final String id) {
		return dao.getTawCommonsAccessories(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#saveTawCommonsAccessories(TawCommonsAccessories
	 *      tawCommonsAccessories)
	 */
	public void saveTawCommonsAccessories(
			TawCommonsAccessories tawCommonsAccessories) {
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
	}

	/**
	 * @see com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager#removeTawCommonsAccessories(String
	 *      id)
	 */
	public void removeTawCommonsAccessories(final String id) {
		dao.removeTawCommonsAccessories(new String(id));
	}

	public TawCommonsAccessories getTawCommonsAccessoriesByName(String id){
		return accessories;
	}
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
			String filelist) throws AccessoriesException {
		List fileList = new ArrayList();
		RequestContext requestContext = new ServletRequestContext(request);
		String accesspriesFileNames = filelist;
		try {
			// 确定这个请求确实来自于文件上传
			TawCommonsAccessoriesConfig config = this.configManager
					.getAccessoriesConfigByAppcode(appCode);
			if (FileUpload.isMultipartContent(requestContext) && config != null) {
				Integer maxSize = config.getMaxSize();
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
				String tempPath = StaticMethod.getFilePath(this.filePath);
				String fileHead = tempPath.substring(0, tempPath
						.lastIndexOf(request.getContextPath())
						+ request.getContextPath().length());
				String fileEnd = tempPath.substring(tempPath
						.lastIndexOf("classes") + 7);
				String rootFilePath = fileHead + fileEnd;
				AccessoriesUtil.createFile(rootFilePath, "/");
				File tempFile = new File(rootFilePath + "/tempfile");
				if (!tempFile.exists()) {
					tempFile.mkdir();
				}
				factory.setRepository(tempFile);
				// 设置最多只允许在内存中存储的数据,单位:字节
				factory.setSizeThreshold(tempFileSize);
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 设置允许用户上传文件大小,单位:字节
				upload.setSizeMax(maxSize.intValue() * 1024 * 1024);
				List items = new ArrayList();
				items = upload.parseRequest(request); // 文件上传

				Iterator it = items.iterator(); // 文件队列
				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (!fileItem.isFormField()) {
						// 上传的文件
						if (fileItem.getName() != null
								&& !fileItem.getName().equals("")
								&& fileItem.getSize() != 0) {
							// 分析文件名
							boolean isAllowFileTpye = false;
							StringTokenizer realname = new StringTokenizer(
									fileItem.getName(), "\\");
							String filename = null;
							while (realname.hasMoreTokens()) {
								filename = realname.nextToken();
							}
							if (config.getAllowFileType() != null
									&& !config.getAllowFileType().equals("")) {
								String[] allowType = config.getAllowFileType()
										.split(",");
								for (int i = 0; i < allowType.length; i++) {
									if (filename.endsWith(allowType[i])) {
										isAllowFileTpye = true;
										break;
									}
								}
							} else {
								isAllowFileTpye = true;
							}
							if (isAllowFileTpye) {
								String name = StaticMethod
										.getCurrentDateTime("yyyyMMddHHmmss")
										+ filename.substring(filename
												.indexOf("."));
								// 分离相对路径信息
								String[] paths = config.getPath().split("/");
								String path = rootFilePath;
								for (int i = 1; i < paths.length; i++) {
									path = path + "/" + paths[i];
									File saveDir = new File(path);
									if (!saveDir.exists()) {
										saveDir.mkdir();
									}
								}
								File newFile = new File(path + "/" + name);

								fileItem.write(newFile);
								// 附件信息入库
								TawCommonsAccessories accessories = new TawCommonsAccessories();
								accessories.setAccessoriesCnName(filename);
								accessories.setAccessoriesName(name);
								accessories.setAccessoriesPath(fileEnd
										+ config.getPath());
								accessories.setAccessoriesSize(fileItem
										.getSize());
								accessories
										.setAccessoriesUploadTime(StaticMethod
												.getLocalTime());
								accessories.setAppCode(config.getAppCode());
								this.dao.saveTawCommonsAccessories(accessories);
								accesspriesFileNames = accesspriesFileNames
										+ ",'" + name + "'";
							} else {
								BocoLog.error(this, "文件类型不符合规定");
							}
						} else {
							BocoLog.error(this, "文件没有选择 或 文件内容为空");
						}
					}// else
				}// while

			}// if
			if (!accesspriesFileNames.equals("")) {
				if (accesspriesFileNames.indexOf(",") == 0) {
					accesspriesFileNames = accesspriesFileNames.substring(1);
				}
				fileList = dao.getAllFileByName(accesspriesFileNames);
			}
		}// try
		catch (Exception e) {
			BocoLog.error(this, "文件上传错误");
			throw new AccessoriesException("文件上传错误");
		}
		return fileList;
	}

	/**
	 * 根据文件ID查询文件信息
	 * 
	 * @param fileIds
	 * @return
	 * @author 秦敏
	 */
	public List getAllFileById(String fileIds) {
		List list = dao.getAllFileByName(fileIds);
		return list;
	}

	/**
	 * 根据文件名称查询文件存放路径。
	 * 
	 * @param fileNames
	 *            文件名称，用","分割
	 * @return String[] 路径数组
	 */
	public String[] getFilePathByName(String fileNames)
			throws AccessoriesException {
		List list = dao.getAllFileByName(fileNames);
		String[] filePaths = new String[list.size()];
		TawCommonsAccessories accessories = null;
		for (int i = 0; i < list.size(); i++) {
			accessories = (TawCommonsAccessories) list.get(i);
			String filePath = accessories.getAccessoriesPath() + "\\"
					+ accessories.getAccessoriesName();
			filePaths[i] = filePath;
		}
		return filePaths;
	}
	
	public String getUrlById(String id) {
		return null;
	}

	public List getTawCommonsAccessoriesByMonth(String beginyear, String beginmonth) {
		// TODO Auto-generated method stub
		return null;
	}

}
