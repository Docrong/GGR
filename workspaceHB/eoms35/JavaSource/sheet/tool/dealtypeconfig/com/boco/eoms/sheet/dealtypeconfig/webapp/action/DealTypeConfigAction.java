package com.boco.eoms.sheet.dealtypeconfig.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;
import com.boco.eoms.sheet.dealtypeconfig.service.IDealTypeConfigManager;
import com.boco.eoms.sheet.dealtypeconfig.util.DealTypeConfigUtil;
import com.boco.eoms.sheet.dealtypeconfig.webapp.form.DealTypeConfigForm;

public class DealTypeConfigAction extends BaseAction{
	
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
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 删除超时提醒
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

		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		IDealTypeConfigManager mgr = (IDealTypeConfigManager) getBean("iDealTypeConfigManager");
		
		mgr.removeDealTypeConfig(id);
		
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
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
		DealTypeConfigForm overtimeTipForm = (DealTypeConfigForm) form;
		DealTypeConfig config = (DealTypeConfig) convert(overtimeTipForm);
		IDealTypeConfigManager mgr = (IDealTypeConfigManager) getBean("iDealTypeConfigManager");
		mgr.saveDealTypeConfig(config);
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
		//得到用户名
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		if(flowName.equals("")){
			flowName = StaticMethod.nullObject2String(request.getAttribute("flowName"));
		}
		IDealTypeConfigManager mgr = (IDealTypeConfigManager) getBean("iDealTypeConfigManager");
		DealTypeConfig config = mgr.getDealTypeConfigByUserId(flowName, userId);
		ArrayList list = new ArrayList();
		if(config!=null){
			list.add(config);
		}
		request.setAttribute("configList", list); 
		request.setAttribute("configTotal", ""+list.size());
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
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
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
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		IDealTypeConfigManager mgr = (IDealTypeConfigManager) getBean("iDealTypeConfigManager");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		DealTypeConfig config = new DealTypeConfig();
		if(id.equals("")){
			config.setId(UUIDHexGenerator.getInstance().getID());
			config.setFlowName(flowName);
			config.setUserId(userId);
			Map phaseIdMap = DealTypeConfigUtil.getPhaseIdMap(flowName);
			Iterator it = phaseIdMap.keySet().iterator();
			while(it.hasNext()){
				String phaseId = (String)it.next();
				String tmpstr = phaseId.toLowerCase();
				if(tmpstr.indexOf("hold")!=-1){
					config.setTaskName(phaseId);
					config.setTaskDisplayName((String)phaseIdMap.get(phaseId));
				}
			}
		}else{
			config = mgr.getDealTypeConfig(id);
		}

		//request.setAttribute("phaseIdMap", phaseIdMap);
		//request.setAttribute("stepIdList", phaseIdList);
		request.setAttribute("dealtypeconfig", config);
		request.setAttribute("flowName", flowName);
		return mapping.findForward("input");
	}
	
}
