package com.boco.eoms.version;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.version.mgr.VersionMgr;

/**
 * 版本日志控制类
 * 
 * @author leo
 * 
 */
public class DoVersionHandler {

	/**
	 * 版本日志mgr
	 */
	private VersionMgr versionMgr;
	/**
	 * 子模块的对应的版本执行类
	 */
	private List handlers;

	/**
	 * 主版本
	 */
	private Version version;

	/**
	 * @param handlers
	 *            the handlers to set
	 */
	public void setHandlers(List handlers) {
		this.handlers = handlers;
	}

	/**
	 * 执行所有handle
	 */
	public void doHandle() {

		VersionLog.log("--------------版本日志记录开始--------------");
		MoudleVersion mv = new MoudleVersion();
		mv.setName(version.getName());
		mv.setPatch(version.getVersion());
		mv.setOnDate(version.getOnDate());
		mv.setPatchDesc(version.getDesc());
		mv.setType(VersionMgr.EOMS_VERSION);
		List versions = new ArrayList();
		versions.add(mv);
		versionMgr.addVerion(versions);
		VersionLog.log("##########################################");
		if (handlers != null) {
			for (Iterator it = handlers.iterator(); it.hasNext();) {
				// 执行版本的handler
				String beanName = (String) it.next();
				VersionHandler vh = (VersionHandler) ApplicationContextHolder
						.getInstance().getBean(beanName);
				vh.handle(version);

			}
		}
		VersionLog.log("--------------版本日志记录结束--------------");
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Version version) {
		this.version = version;
	}

	/**
	 * @param versionMgr
	 *            the versionMgr to set
	 */
	public void setVersionMgr(VersionMgr versionMgr) {
		this.versionMgr = versionMgr;
	}
}
