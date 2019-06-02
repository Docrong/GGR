package com.boco.eoms.version;

import java.util.List;

/**
 * 版本执行中输出日志信息
 * 
 * @author leo
 * 
 */
public interface VersionHandler {

	/**
	 * 
	 * @param version
	 */
	public void setVersions(List versions);

	/**
	 * 执行方法，被执行的方法
	 * 
	 * @param v
	 *            主版本配置类(即大版本）
	 * @return
	 */
	public Object handle(Version version);
}
