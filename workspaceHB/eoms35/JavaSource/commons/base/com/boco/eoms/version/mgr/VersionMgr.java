package com.boco.eoms.version.mgr;

import java.util.List;

/**
 * 版本mgr
 * 
 * @author leo
 * 
 */
public interface VersionMgr {

	/**
	 * 增加版本日志，若之前已存在此版本日志则不输出，否则写入版本
	 * 
	 * @param moudleVersion
	 *            版本
	 */
	public void addVerion(List versions);

	/**
	 * 主版本
	 */
	public final static String EOMS_VERSION = "EOMS";

	/**
	 * 模块版本
	 */
	public final static String MODULE_VERSION = "MODULE";
}
