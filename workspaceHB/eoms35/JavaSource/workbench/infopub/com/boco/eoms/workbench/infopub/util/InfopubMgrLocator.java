package com.boco.eoms.workbench.infopub.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager;

/**
 * <p>
 * Title:信息发布管理locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 29, 2008 5:09:53 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class InfopubMgrLocator {

	/**
	 * 返回信息管理类
	 * 
	 * @return 信息管理类
	 */
	public static IThreadHistoryManager getThreadManager() {
		return (IThreadHistoryManager) ApplicationContextHolder.getInstance()
				.getBean("IthreadManager");
	}

}
