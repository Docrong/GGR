/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.urgentfault.service.IUrgentFaultMainManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UrgentFaultAction extends SheetAction {

    /**
	 * 显示草图
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
	 * 显示流程VISO图
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
	 * 显示KPI
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
	 * 调用关系列表
	 * @author wangjianhua
	 * @date 2008-08-02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showInvokeRelationShipList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String mainId = StaticMethod.null2String(request.getParameter("id"));
		String beanName = mapping.getAttribute();
		IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
		IUrgentFaultMainManager urgentFaultMainManager = (IUrgentFaultMainManager)baseSheet.getMainService();
		List showInvokeRelationShipList = urgentFaultMainManager.showInvokeRelationShipList(mainId);
		String commonFaultId = (showInvokeRelationShipList.size() > 0 ? (((BaseMain)showInvokeRelationShipList.iterator().next()).getId()) : "");
		//得到处理环节的工单
		BaseLink baseLink = urgentFaultMainManager.getHasInvokeBaseLink(commonFaultId);
		String activeTemplateId = (baseLink == null ? "" : baseLink.getActiveTemplateId());
		
		request.setAttribute("proccessName", "故障处理工单");
		request.setAttribute("invokedproccessName", "紧急故障工单");
		request.setAttribute("showInvokeRelationShipList", showInvokeRelationShipList);
		request.setAttribute("activeTemplateId", activeTemplateId);
		return mapping.findForward("showInvokeRelationShipList");
	}
	
	
}
