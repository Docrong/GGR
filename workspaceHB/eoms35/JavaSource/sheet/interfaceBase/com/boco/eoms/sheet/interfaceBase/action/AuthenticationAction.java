package com.boco.eoms.sheet.interfaceBase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.interfaceBase.service.IAuthentication;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class AuthenticationAction extends SheetAction{
	
	public ActionForward getAuthenticationPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("errAuthentication");
	}

	/**	
	 * 鉴权
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward authentication(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String authenticationBeanId = StaticMethod.nullObject2String(request.getParameter("authenticationBeanId"));
		System.out.println("authenticationBeanId="+authenticationBeanId);
		if("".equals(authenticationBeanId))
			throw new Exception("authenticationBeanId不能为空");
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		String eomsUserId = "";
		if(sessionform!=null)
			eomsUserId = sessionform.getUserid();
		
		String userId = StaticMethod.nullObject2String(request.getParameter("userId"));
		String password = StaticMethod.nullObject2String(request.getParameter("password"));
		String forwardUrl = StaticMethod.nullObject2String(request.getParameter("forwardUrl"));
		System.out.println("forwardUrl="+forwardUrl);
		
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String filePath = com.boco.eoms.base.util.StaticMethod.getFilePathForUrl("classpath:config/circuitdispatch-util.xml");
		
		forwardUrl = InterfaceUtilProperties.getInstance().getXmlValue(filePath, "dict", "operateType", "url", operateType);
		
		IAuthentication authenticationMgr = (IAuthentication)ApplicationContextHolder.getInstance().getBean(authenticationBeanId);
		String result = authenticationMgr.getAuthenticationResult(userId, password);
		if(result!=null && result.length()>0){
			request.getSession().setAttribute(result+"_"+eomsUserId, userId);//标识为已鉴权,将对端的userId保存到session						
			ActionForward actionForward = new ActionForward();
			actionForward.setPath(forwardUrl);
			actionForward.setRedirect(true);
			return actionForward;
		}else{
			request.setAttribute("authenticationBeanId", authenticationBeanId);
			request.setAttribute("forwardUrl", forwardUrl);
			ActionForward forward = mapping.findForward("errAuthentication");
			String path = forward.getPath() + "?type=error&authenticationBeanId="+authenticationBeanId;
			return forward;
		}
	}
	public ActionForward authenticationCommonPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("authenticationCommon");
	}
	/**	
	 * 鉴权
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward authenticationCommon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String authenticationBeanId = StaticMethod.nullObject2String(request.getParameter("authenticationBeanId"));
		System.out.println("authenticationBeanId="+authenticationBeanId);
		if("".equals(authenticationBeanId))
			throw new Exception("authenticationBeanId不能为空");		
		
		IAuthentication authenticationMgr = (IAuthentication)ApplicationContextHolder.getInstance().getBean(authenticationBeanId);
		return authenticationMgr.authenticationCommon(mapping,form,request,response);
	}
	
}
