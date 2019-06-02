package com.boco.eoms.version.mgr.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.version.MoudleVersion;
import com.boco.eoms.version.VersionLog;
import com.boco.eoms.version.dao.VersionDao;
import com.boco.eoms.version.mgr.VersionMgr;

/**
 * 版本mgr实现
 * 
 * @author leo
 * 
 */
public class VersionMgrImpl implements VersionMgr {

	/**
	 * 版本dao
	 */
	private VersionDao versionDao;

	/**
	 * @param versionDao
	 *            the versionDao to set
	 */
	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.version.mgr.VersionMgr#addVerion(com.boco.eoms.version.MoudleVersion)
	 */
	public void addVerion(List versions) {
		for (Iterator it = versions.iterator(); it.hasNext();) {
			MoudleVersion moudleVersion = (MoudleVersion) it.next();
			MoudleVersion mv = versionDao.getVersion(moudleVersion.getPatch(),
					moudleVersion.getType());
			// 未找到version，即之前未更新过此版本
			if (mv == null || mv.getId() == null || "".equals(mv.getId())) {
				// 设置补丁时间
				moudleVersion.setPatchFirstDate(new Date());
				versionDao.addVersion(moudleVersion);
				VersionLog.log("模块名称:" + moudleVersion.getName());
				VersionLog.log("模块负责人:" + moudleVersion.getPrincipal());
				VersionLog.log("模块补丁号:" + moudleVersion.getPatch());
				VersionLog.log("问题编号:" + moudleVersion.getProblem());
				VersionLog.log("补丁描述:" + moudleVersion.getPatchDesc());
				VersionLog.log("补丁时间:" + moudleVersion.getOnDate());
				VersionLog.log("补丁负责人:" + moudleVersion.getPatchPrincipal());
				VersionLog.log("svn" + moudleVersion.getSvn());
				VersionLog
						.log("***********************************************");
			}
		}

	}

}
