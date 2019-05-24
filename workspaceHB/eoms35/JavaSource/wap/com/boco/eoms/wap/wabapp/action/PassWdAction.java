package com.boco.eoms.wap.wabapp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.wap.util.WapUtil;
import com.boco.eoms.wap.wabapp.form.CommonForm;

public class PassWdAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, IOException, Exception {
		ActionForward myforward = null;

		// 获取请求的action属性
		String myaction = mapping.getParameter();

		// 根据用户请求页面请求，进行页面跳转
		if (isCancelled(request)) {
			return mapping.findForward("cancel"); // 无效的请求，转向错误页面
		} else if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure"); // 条件为空，转向空页
		} else if ("CHECKUSER".equalsIgnoreCase(myaction)) {  
			myforward = performCheckUser(mapping, form, request, response);
		}else if ("INDEX".equalsIgnoreCase(myaction)) {  
			myforward = performpdIndex(mapping, form, request, response);
		}else if ("UPDATE".equalsIgnoreCase(myaction)) { 
			myforward = performUpdate(mapping, form, request, response);
		}
		return myforward;
	}

	/**
	 * 忘记密码首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performpdIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("pdIndex");
	}

	/**
	 * 检验用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performCheckUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommonForm commonform = (CommonForm) form;
		String name = commonform.getName();
		String tel = commonform.getTel();
		TawSystemUser user = null;
		String mobile = "";
		ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");

		if (name == null || "".equals(name)) {
			loginErrorReturn(request, response, "NameIsNull");
			return mapping.findForward("rewrite");

		} else {
			if (WapUtil.isMobileNumber(name)) {
				List list = userManager.getUserByIdMobile(name);
				if (list.size()==0) {
					loginErrorReturn(request, response, "UserNotFound");
					return mapping.findForward("rewrite");
				} else {
					user = (TawSystemUser) list.get(0);
					mobile = user.getMobile();
				}
			} else {
				user = userManager.getUserByuserid(name);
				if (user.getUserid() == null) {
					loginErrorReturn(request, response, "UserNotFound");
					return mapping.findForward("rewrite");
				} else {
					mobile = user.getMobile();
				}

			}
			if (!StaticMethod.null2String(mobile).equals(tel)) {
				loginErrorReturn(request, response, "NotUpdatePassword");
				return mapping.findForward("rewrite");
			}

			request.setAttribute("userid", user.getUserid());
		}
		return mapping.findForward("update");
	}

	/**
	 * 返回在頁面時顯示的錯誤信息
	 * 
	 * @param request
	 * @param response
	 * @param strError
	 */
	private void loginErrorReturn(HttpServletRequest request,
			HttpServletResponse response, String strError) {
		request.setAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY, strError);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward performUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommonForm commonform = (CommonForm) form;
		TawSystemUser user = null;
		ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("ItawSystemUserSaveManagerFlush");
		String userid = commonform.getName();
		String password = StaticMethod.null2String(commonform.getPassword());
		if(!"".equals(password)){
			boolean bool  = userManager.checkPasswd(password);
			if(bool==false){
				loginErrorReturn(request, response, "PasswordError");
				return mapping.findForward("false");
			}else{
				userManager.updatePassword(userManager.getUserByuserid(userid),password);
				
			}
		}
		
		return mapping.findForward("success");
	}
}
