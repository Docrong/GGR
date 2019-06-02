package com.boco.eoms.sheet.interfaceBase.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 鉴权接口
 * 
 * @author IBM
 * 
 */
public interface IAuthentication {
	/**
	 * 返回鉴权结果 true通过，false不通过
	 * 
	 * @param userId
	 *            用户id
	 * @param password
	 *            密码
	 * @return
	 */
	public String getAuthenticationResult(String userId, String password);

	public ActionForward authenticationCommon(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
