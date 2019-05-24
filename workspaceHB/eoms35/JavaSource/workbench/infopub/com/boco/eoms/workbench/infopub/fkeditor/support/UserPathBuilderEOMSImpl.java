package com.boco.eoms.workbench.infopub.fkeditor.support;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import net.fckeditor.requestcycle.UserPathBuilder;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jul 22, 2008 2:52:16 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserPathBuilderEOMSImpl implements UserPathBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fckeditor.requestcycle.UserPathBuilder#getUserFilesPath(javax.servlet.http.HttpServletRequest)
	 */
	public String getUserFilesPath(HttpServletRequest request) {
		TawSystemSessionForm form = (TawSystemSessionForm) request.getSession()
				.getAttribute("sessionform");
		return "/userfiles/" + form.getUserid();
	}

}
