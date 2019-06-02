
package com.boco.eoms.sheet.businessimplementyy.webapp.action;

import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.businessimplementyy.service.IBusinessImplementYYMainManager;

public  class BusinessImplementYYAction extends SheetAction {
   /**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDrawing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("draw");
	}
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("pic");
	}
	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showKPI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("kpi");
	}
	
	/**
	 * LINK的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newPerformDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		baseSheet.newPerformDeal(mapping, form, request, response);
		String forwardStr = StaticMethod.nullObject2String(request.getAttribute("forwardStr"));
		if(forwardStr.equals("detail")){
			return mapping.findForward(forwardStr);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward showHistoryPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
	    String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetNo"), "");
	    String userid = StaticMethod.nullObject2String(request.getParameter("userName"), "");
	    String beanName = mapping.getAttribute();
	    IBaseSheet baseSheet = (IBaseSheet)getBean(beanName);
	    IBusinessImplementYYMainManager mgr = (IBusinessImplementYYMainManager)baseSheet.getMainService();
	    List list = mgr.getMainListByParentSheetId(sheetId);
	    if(list!=null&& list.size()>0){
			BaseMain main = (BaseMain)list.get(0);
			String sheetKey = main.getId();
	
			if (!sheetKey.equals("")) {
				if("".equals(userid))
					userid = "admin";
				try{
					IWorkflowSecutiryService safeService=
						 (IWorkflowSecutiryService)ApplicationContextHolder.getInstance().getBean("WorkflowSecutiryService");
						 Subject subject = safeService.logIn(userid, "");
						 request.getSession().setAttribute("wpsSubject",subject);
				}catch(Exception e){ 
					BocoLog.error(this,"保存流程登陆信息报错："+e.getMessage()); 
				}
				
				ActionForward forward=mapping.findForward("showInterfaceDraftPage");
				String path = forward.getPath() + "&sheetKey="+sheetKey;
				System.out.println("path="+path);
				return new ActionForward(path, false);
			}	
			else
				throw new Exception("sheetNo不能为空");
		}else
			throw new Exception("没找到对应工单，请确认编号正确");
	}	
	
}
