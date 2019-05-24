package com.boco.eoms.sheet.tool.access.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.access.webapp.form.TawSheetAccessForm;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;
import com.boco.eoms.sheet.tool.limit.service.ISheetLimitManager;

import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * Action class to handle CRUD on a TawSheetAccess object
 * 
 * @struts.action name="tawSheetAccessForm" path="/tawSheetAccesss"
 *                scope="request" validate="false" parameter="method"
 *                input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main"
 *                        path="/WEB-INF/pages/tawSheetAccess/tawSheetAccessTree.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawSheetAccess/tawSheetAccessForm.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawSheetAccess/tawSheetAccessList.jsp"
 *                        contextRelative="true"
 */
public final class TawSheetAccessAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return mapping.findForward("search");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("main");
	}

	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

	/**
	 * ajax保存
	 */
	public void xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");

		TawSheetAccess tawSheetAccess = (TawSheetAccess) convert(tawSheetAccessForm);

		boolean isNew = ("".equals(tawSheetAccessForm.getId()) || tawSheetAccessForm
				.getId() == null);
		String paraccessid = tawSheetAccess.getParAccessid();
		if (paraccessid == null
				|| paraccessid.equals(TawSystemAreaUtil.AREA_DEFAULT_STRVAL)
				|| paraccessid.equals(TawSystemAreaUtil.AREA_DEFAULT_PARENTVAL)) {

			tawSheetAccess
					.setParAccessid(TawSystemAreaUtil.AREA_DEFAULT_PARENTVAL);
			tawSheetAccess.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
			tawSheetAccess
					.setOrdercode(TawSystemAreaUtil.AREA_DEFAULT_ORDERCODEVAL);
			tawSheetAccess.setAccessid(mgr
					.getNewAccessid(TawSystemAreaUtil.AREA_DEFAULT_STRONE));
		} else {
			String newaccessid = mgr.getNewAccessid(paraccessid);
			TawSheetAccess parAccess = new TawSheetAccess();
			parAccess = mgr.getAccessByAccessId(paraccessid);
			if (tawSheetAccess.getAccessid() == null
					|| tawSheetAccess.getAccessid().equals("")) {
				tawSheetAccess.setAccessid(newaccessid);
			}
			tawSheetAccess.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
			tawSheetAccess.setOrdercode(new Integer(parAccess.getOrdercode()
					.intValue()
					+ TawSystemAreaUtil.AREA_DEFAULT_ORDERCODEVAL.intValue()));
			TawSheetAccess upparAccess = new TawSheetAccess();
			upparAccess = (TawSheetAccess) mgr.getAccessByAccessId(paraccessid);

			upparAccess.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFZERO);
			mgr.saveTawSheetAccess(upparAccess);

		}
		if (isNew) {

			request.setAttribute("lastNewId", paraccessid);
		} else {
			request.setAttribute("lastEditId", tawSheetAccess.getAccessid());
		}
		mgr.saveTawSheetAccess(tawSheetAccess);
	}

	/**
	 * 根据模块或功能的编码，删除该对象
	 */
	public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");

		String accessid = request.getParameter("id");
		TawSheetAccess access = mgr.getAccessByAccessId(accessid);
		List sameLevelaccess = mgr.getSameLevelAccess(access.getParAccessid(),
				access.getOrdercode());

		if (sameLevelaccess != null && sameLevelaccess.size() <= 0) {
			TawSheetAccess parAccess = (TawSheetAccess) mgr
					.getAccessByAccessId(access.getParAccessid());
			parAccess.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
			mgr.saveTawSheetAccess(parAccess);
		} else if (sameLevelaccess != null && sameLevelaccess.size() > 0) {
			List sonAccessList = (ArrayList) mgr
					.getAllSonAccessByAreaid(accessid);
			for (Iterator prowIt = sonAccessList.iterator(); prowIt.hasNext();) {
				TawSheetAccess sysaccess = (TawSheetAccess) prowIt.next();
				mgr.removeTawSheetAccess(String.valueOf(sysaccess.getId()));
			}
		} else {
			mgr.removeTawSheetAccess(String.valueOf(access.getId()));
		}

		mgr.removeTawSheetAccess(tawSheetAccessForm.getId());

	}

	/**
	 * ajax请求修改某节点的详细信息
	 */
	public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;

		if (tawSheetAccessForm.getId() != null) {
			ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");
			TawSheetAccess tawSheetAccess = (TawSheetAccess) convert(tawSheetAccessForm);

			mgr.saveTawSheetAccess(tawSheetAccess);
		}

		return null;
	}

	/**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String accessid = request.getParameter("id");
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");
		TawSheetAccess tawSheetAccess = mgr.getTawSheetAccess(accessid);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawSheetAccess);

		JSONUtil.print(response, jsonRoot.toString());
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");

		/*
		 * String accessid = request.getParameter("id"); TawSheetAccess access =
		 * mgr.getAccessByAccessId(accessid); List sameLevelaccess =
		 * mgr.getSameLevelAccess(access.getParAccessid(),
		 * access.getOrdercode());
		 * 
		 * if (sameLevelaccess != null && sameLevelaccess.size() <= 0) {
		 * TawSheetAccess parAccess = (TawSheetAccess) mgr
		 * .getAccessByAccessId(access.getParAccessid());
		 * parAccess.setLeaf(TawSystemAreaUtil.AREA_DEFAULT_LEAFONE);
		 * mgr.saveTawSheetAccess(parAccess); } else if (sameLevelaccess != null &&
		 * sameLevelaccess.size() > 0) { List sonAccessList = (ArrayList) mgr
		 * .getAllSonAccessByAreaid(accessid); for (Iterator prowIt =
		 * sonAccessList.iterator(); prowIt.hasNext();) { TawSheetAccess
		 * sysaccess = (TawSheetAccess) prowIt.next();
		 * mgr.removeTawSheetAccess(String.valueOf(sysaccess.getId())); } } else {
		 * mgr.removeTawSheetAccess(String.valueOf(access.getId())); }
		 */
		mgr.removeTawSheetAccess(tawSheetAccessForm.getId());
		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;
		ITawSystemWorkflowManager workflowmgr = (ITawSystemWorkflowManager) getBean("ITawSystemWorkflowManager");

		List workflowlist = workflowmgr.getTawSystemWorkflows();
		request.setAttribute("workflowlist", workflowlist);

		if (tawSheetAccessForm.getId() != null) {
			ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");

			TawSheetAccess tawSheetAccess = mgr
					.getTawSheetAccess(tawSheetAccessForm.getId());
			tawSheetAccessForm = (TawSheetAccessForm) convert(tawSheetAccess);
			request.setAttribute("tawSheetAccessForm", tawSheetAccessForm);
			request.setAttribute("accesss", tawSheetAccessForm.getAccesss());
			updateFormBean(mapping, request, tawSheetAccessForm);
		}
		request.setAttribute("tawSheetAccessForm", tawSheetAccessForm);
		return mapping.findForward("edit");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;

		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");

		TawSheetAccess tawSheetAccess = (TawSheetAccess) convert(tawSheetAccessForm);

		boolean isNew = ("".equals(tawSheetAccessForm.getId()) || tawSheetAccessForm
				.getId() == null);
		TawSheetAccess access = mgr.getAccessByPronameAndTaskname(
				tawSheetAccess.getProcessname(), tawSheetAccess.getTaskname());
		if (access != null) {
			tawSheetAccess.setId(null);
			mgr.removeTawSheetAccess(String.valueOf(access.getId()));
		}
		
		mgr.saveTawSheetAccess(tawSheetAccess);
		request.setAttribute("tawSheetAccessForm", tawSheetAccessForm);
		return mapping.findForward("search");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		TawSheetAccessForm tawSheetAccessForm = (TawSheetAccessForm) form;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawDemoMytableList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = new Integer(25);
		final Integer pageIndex = new Integer(
				GenericValidator.isBlankOrNull(request
						.getParameter(pageIndexName)) ? 0 : (new Integer(
						request.getParameter(pageIndexName)).intValue() - 1));
		ITawSheetAccessManager mgr = (ITawSheetAccessManager) getBean("ItawSheetAccessManager");
		Map map = (Map) mgr.getTawSheetAccesss(pageIndex, pageSize);
		List list = (List) map.get("result");
		List rlist = new ArrayList();
		if( list != null && list.size()>0){
			ITawSystemWorkflowManager workflowmgr = (ITawSystemWorkflowManager) getBean("ITawSystemWorkflowManager");
			for( int i=0;i<list.size();i++){
				TawSheetAccess sheetacs = (TawSheetAccess)list.get(i);
				TawSystemWorkflow workflow = workflowmgr.getTawSystemWorkflowByName(sheetacs.getProcessname());
				
				String mainbeanid = workflow.getMainServiceBeanId();
				String taskname="";
				if( sheetacs.getTaskname()==null || sheetacs.getTaskname().equals("")){
					taskname = "新增工单";
				}else{
				 taskname = getPhaseidArray(  mainbeanid, sheetacs.getProcessname(), sheetacs.getTaskname());
				}
				 sheetacs.setProcessname(workflow.getRemark());
				sheetacs.setTaskname(taskname);
				rlist.add(sheetacs); 
			}
		}else{
			rlist = list;
		}
		request.setAttribute("resultSize", map.get("total")); 
		request.setAttribute("tawSheetAccessList", rlist);
		request.setAttribute("tawSheetAccessForm", tawSheetAccessForm);
		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}

	/**
	 * 根据专业，查询时限
	 * 
	 * @author wangjianhua
	 * @date 2008-08-02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getProcessHumanTask(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String processName = StaticMethod.null2String(request
				.getParameter("processName"));
		String taskName = request.getParameter("taskName");
		ITawSystemWorkflowManager workflowmgr = (ITawSystemWorkflowManager) getBean("ITawSystemWorkflowManager");
		
		JSONArray jsonRoot = new JSONArray();
		
		if(taskName==null || taskName.toUpperCase().equals("NULL")){
		    JSONObject j = new JSONObject();
		    j.put("text", "请选择");
		    j.put("value", "");
		    jsonRoot.put(j);
		}
		try {
			TawSystemWorkflow workFlow = workflowmgr
					.getTawSystemWorkflowByName(processName);
			String mainServiceId = workFlow.getMainServiceBeanId();
			IMainService mainService = (IMainService) getBean(mainServiceId);
			FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
					processName, mainService.getRoleConfigPath());
			FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
			if (flowDefine == null)
				return;
			PhaseId phaseIds[] = flowDefine.getPhaseId();

			for (int i = 0; phaseIds != null && i < phaseIds.length; i++) {
				PhaseId phase = phaseIds[i];
				TawCommonsUIListItem item = new TawCommonsUIListItem();

				JSONObject j = new JSONObject();
				if (phase.getId().equals("receive")) {
				    j.put("text", "新增工单");
				    j.put("value", "");
				} else {
				    j.put("text", phase.getName());
				    j.put("value", phase.getId());
				}
				jsonRoot.put(j);
			}
		} catch (Exception e) {

		}
		JSONUtil.print(response, jsonRoot.toString());

	}
	
	
	//查询phasId
	public String getPhaseidArray( String mainserviceid,String processName,String taskname){
		IMainService mainService = (IMainService) getBean(mainserviceid);
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				processName, mainService.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine == null)
			return "";
		PhaseId phaseIds[] = flowDefine.getPhaseId();
		String rtaskname = "";
		for( int i=0;i<phaseIds.length;i++){
			PhaseId phaseid = phaseIds[i];
			if( phaseid.getId().equals(taskname)){
				rtaskname = phaseid.getName();
			}
		}
		return rtaskname; 
	}
}
