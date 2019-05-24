package com.boco.eoms.sheet.autodistribute.webapp.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;
import com.boco.eoms.sheet.autodistribute.service.IAutoDistributeManager;
import com.boco.eoms.sheet.autodistribute.webapp.form.AutoDistributeForm;
import com.boco.eoms.sheet.base.util.SheetAttributes;

public class AutoDistributeAction extends BaseAction{
	
	/**
	 * 撤销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
	/**
	 * 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		AutoDistributeForm autoDistributeForm = (AutoDistributeForm) form;

		IAutoDistributeManager mgr = (IAutoDistributeManager) getBean("iAutoDistributeManager");
		
		mgr.removeAutoDistribute(autoDistributeForm.getId());
		
		return search(mapping, form, request, response);
	}
	
	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		//保存动态分配任务配置
		String id = StaticMethod.null2String(request.getParameter("autoDistributeId"));
		String flowName = StaticMethod.null2String(request.getParameter("flowName"));
		String autoType = StaticMethod.null2String(request.getParameter("autoType"));
		String roleId = StaticMethod.null2String(request.getParameter("roleId"));
		String threshold = StaticMethod.null2String(request.getParameter("threshold"));
		
		AutoDistribute autoDistribute = new AutoDistribute();
		autoDistribute.setAutoType(autoType);
		autoDistribute.setFlowName(flowName);
		autoDistribute.setId(id);
		autoDistribute.setRoleId(roleId);
		autoDistribute.setThreshold(threshold);
		IAutoDistributeManager mgr = (IAutoDistributeManager) getBean("iAutoDistributeManager");
		mgr.saveAutoDistribute(autoDistribute);
		
		return search(mapping, form, request, response);
	}
	
	 /**
     * 查询超时提醒列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author jialei
     */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		//获取所有记录
		IAutoDistributeManager mgr = (IAutoDistributeManager) getBean("iAutoDistributeManager");
		HashMap autoMap = mgr.getAllAutoDistribute(pageIndex,pageSize);
		
		request.setAttribute("autoList", autoMap.get("autoList"));
		request.setAttribute("autoTotal", autoMap.get("autoTotal"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	/**
     * 默认执行方法
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author jialei
     */
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("unspecified");
		return search(mapping, form, request, response);
	}

	/**
	 * 显示保存和修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward showInputPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'showInputPage' method");
		}
		String type = StaticMethod.nullObject2String(request.getParameter("type"));
		if(type.equals("add")){
			request.setAttribute("type", "add");
		}else{
			String id = StaticMethod.nullObject2String(request.getParameter("id"));
			IAutoDistributeManager mgr = (IAutoDistributeManager) getBean("iAutoDistributeManager");
			AutoDistribute ad = mgr.getAutoDistribute(id);
			if(ad.getThreshold()==null||ad.getThreshold().equals("")){
				ad.setThreshold("0");
			}
			request.setAttribute("AutoDistribute", ad);
			ITawSystemRoleManager roleManager = (ITawSystemRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemRoleManager");
	        request.setAttribute("roleName", roleManager.getRoleNameById(Long.parseLong(ad.getRoleId())));
			
		}
		return mapping.findForward("input");
	}
	
}
