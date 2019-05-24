package com.boco.eoms.version.dao;

import com.boco.eoms.version.MoudleVersion;

/**
 * 版本管理dao
 * 
 * @author leo
 * 
 */
public interface VersionDao {

	/**
	 * 根据patch(补丁或版本号）获取版本对象
	 * 
	 * @param patch
	 *            补丁或版本号
	 * @param
	 *            VersionMgr.EOMS_VERSION,VersionMgr.MODULE_VERSION
	 * @return 版本
	 */
	public MoudleVersion getVersion(String patch, String type);

	/**
	 * 新增版本
	 * 
	 * @param moudleVersion
	 *            版本对象
	 */
	public void addVersion(MoudleVersion moudleVersion);

}
