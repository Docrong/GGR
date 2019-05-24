package com.boco.eoms.workbench.infopub.fkeditor.support;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserAction;

/**
 * <p>
 * Title:fckeditor验证用户是否可上传或浏览
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 26, 2008 2:08:14 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserActionEOMS implements UserAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fckeditor.requestcycle.UserAction#isEnabledForFileBrowsing(javax.servlet.http.HttpServletRequest)
	 */
	public boolean isEnabledForFileBrowsing(HttpServletRequest reqeust) {
		return null == reqeust.getSession().getAttribute("sessionform") ? false
				: true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fckeditor.requestcycle.UserAction#isEnabledForFileUpload(javax.servlet.http.HttpServletRequest)
	 */
	public boolean isEnabledForFileUpload(HttpServletRequest reqeust) {
		return null == reqeust.getSession().getAttribute("sessionform") ? false
				: true;
	}

}
