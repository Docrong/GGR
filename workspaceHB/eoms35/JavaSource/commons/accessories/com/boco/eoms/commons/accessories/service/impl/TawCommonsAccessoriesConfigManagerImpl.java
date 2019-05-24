package com.boco.eoms.commons.accessories.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesConfigDao;
import com.boco.eoms.commons.accessories.dao.jdbc.TawCommonsAccessoriesDaoJdbc;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.model.TawCommonsApplication;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:附件配置管理service实现类
 * </p>
 * <p>
 * Apr 10, 2007 11:00:28 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 * 
 */
public class TawCommonsAccessoriesConfigManagerImpl extends BaseManager
		implements ITawCommonsAccessoriesConfigManager {
	private TawCommonsAccessoriesDaoJdbc daoJdbc;

	private TawCommonsAccessoriesConfigDao configDAO;

	public TawCommonsAccessoriesDaoJdbc getDaoJdbc() {
		return daoJdbc;
	}

	public void setDaoJdbc(TawCommonsAccessoriesDaoJdbc daoJdbc) {
		this.daoJdbc = daoJdbc;
	}

	/**
	 * @return Returns the configDAO.
	 */
	public TawCommonsAccessoriesConfigDao getConfigDAO() {
		return configDAO;
	}

	/**
	 * @param configDAO
	 *            The configDAO to set.
	 */
	public void setConfigDAO(TawCommonsAccessoriesConfigDao configDAO) {
		this.configDAO = configDAO;
	}

	/**
	 * 获取配置信息
	 * 
	 * @author 秦敏
	 * @return
	 * @throws Exception
	 */
	public List getTawCommonsAccessoriesConfigs()
			throws AccessoriesConfigException {
		List configInfoList = configDAO.getTawCommonsAccessoriesConfigs();
		return configInfoList;
	}

	/**
	 * 保存配置信息（xml文件）
	 * 
	 * @author 秦敏
	 * @param configObject
	 *            配置信息
	 */
	public void saveTawCommonsAccessoriesConfig(
			TawCommonsAccessoriesConfig config)
			throws AccessoriesConfigException {
		String appName = getAppNameByAppcode(config.getAppId());
		config.setAppName(appName);
		configDAO.saveTawCommonsAccessoriesConfig(config);
	}

	/**
	 * 查询配置信息
	 * 
	 * @author
	 * @param appCode
	 *            应用模板ID
	 * 
	 */
	public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(
			Integer appId) throws AccessoriesConfigException {
		TawCommonsAccessoriesConfig config = configDAO
				.getTawCommonsAccessoriesConfig(appId);
		return config;
	}

	/**
	 * 查询配置信息
	 * 
	 * @author
	 * @param appCode
	 *            应用模板编码
	 * 
	 */
	public TawCommonsAccessoriesConfig getAccessoriesConfigByAppcode(
			String appCode) throws AccessoriesConfigException {
		TawCommonsAccessoriesConfig config = configDAO
				.getAccessoriesConfigByAppcode(appCode);
		return config;
	}

	/**
	 * 查询配置信息
	 * 
	 * @author
	 * @param appCode
	 *            应用模板ID
	 * 
	 */
	public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(String id)
			throws AccessoriesConfigException {
		TawCommonsAccessoriesConfig config = configDAO
				.getTawCommonsAccessoriesConfig(id);
		return config;
	}

	/**
	 * 删除配置信息
	 * 
	 * @author 秦敏
	 * @param appCode
	 *            应用模板ID
	 * 
	 */
	public void removeTawCommonsAccessoriesConfig(Integer appId)
			throws AccessoriesConfigException {
		configDAO.removeTawCommonsAccessoriesConfig(appId);
	}

	/**
	 * 根据应用模块ID号查询模块名称
	 * 
	 * @param appCode
	 * @return
	 * @author 秦敏
	 */
	private String getAppNameByAppcode(Integer appCode) {
		String appName = "";
		appName = daoJdbc.getApplicatioNameById(appCode.intValue());
		return appName;
	}

	/**
	 * 获取应用模块信息
	 * 
	 * @return
	 * @author 秦敏
	 */
	public List getApplicationInfo() {
		List applicationTag = new ArrayList();
		List applicationInfo = daoJdbc.getTawSystemApplications();
		for (int i = 0; i < applicationInfo.size(); i++) {
			TawCommonsApplication application = (TawCommonsApplication) applicationInfo
					.get(i);
			applicationTag.add(new org.apache.struts.util.LabelValueBean(
					application.getAppName(), String.valueOf(application
							.getAppId())));
		}
		return applicationTag;
	}


}
