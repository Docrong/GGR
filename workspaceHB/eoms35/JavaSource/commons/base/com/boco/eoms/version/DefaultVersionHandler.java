package com.boco.eoms.version;

import java.util.List;

import com.boco.eoms.version.mgr.VersionMgr;

/**
 * 默认版本实现类
 * 
 * @author leo
 * 
 */
public class DefaultVersionHandler implements VersionHandler {

	/**
	 * 版本控制mgr
	 */
	private VersionMgr versionMgr;

	/**
	 * 版本
	 */
	private List verions;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.version.VersionHandler#setVersions(java.util.List)
	 */
	public void setVersions(List versions) {
		this.verions = versions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.version.VersionHandler#execute(com.boco.eoms.version.Version)
	 */
	public Object handle(Version version) {
		versionMgr.addVerion(verions);

		return null;
	}

	/**
	 * @param versionMgr
	 *            the versionMgr to set
	 */
	public void setVersionMgr(VersionMgr versionMgr) {
		this.versionMgr = versionMgr;
	}

}
