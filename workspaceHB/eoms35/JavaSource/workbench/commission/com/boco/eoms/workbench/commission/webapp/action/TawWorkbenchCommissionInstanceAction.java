package com.boco.eoms.workbench.commission.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.workbench.commission.mgr.ITawWorkbenchCommissionInstanceMgr;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionInstance;
import com.boco.eoms.workbench.commission.webapp.form.TawWorkbenchCommissionInstanceForm;

public class TawWorkbenchCommissionInstanceAction extends BaseAction {

	private static int PAGE_LENGTH = 5;

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return main(mapping, form, request, response);
	}

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("main");
	}

	public ActionForward listInstances(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String moduleId = StaticMethod.null2String(request
				.getParameter("moduleId"));
		if (moduleId == null || "".equals(moduleId)) {
			moduleId = StaticMethod.nullObject2String(request
					.getAttribute("moduleId"));
			if (moduleId == null || "".equals(moduleId)) {
				return null;
			}
		}
		ITawWorkbenchCommissionInstanceMgr instanceMgr = (ITawWorkbenchCommissionInstanceMgr) getBean("ItawWorkbenchCommissionInstanceMgr");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();

		// 分页
		int offset;
		int length = PAGE_LENGTH;
		String pageOffset = StaticMethod.null2String(request
				.getParameter("pager.offset"));
		if (pageOffset == null || "".equals(pageOffset)) {
			offset = 0;
		} else {
			offset = Integer.parseInt(pageOffset);
		}
		//String whereStr = " where userId = '" + userId + "' and moduleId = '"
		//		+ moduleId + "'";
		//Map instanceMap = instanceMgr.listCommissionInstances(new Integer(
		//		offset), new Integer(length), whereStr);
		//List list = (ArrayList) instanceMap.get("result");
		List list = instanceMgr.listCommissionInstances(userId, moduleId);
		List instanceList = new ArrayList();
		for (int i = offset; i < (length + offset) && i < list.size(); i++) {
			TawWorkbenchCommissionInstance commissionInstance = (TawWorkbenchCommissionInstance) list
					.get(i);
			instanceList.add(commissionInstance);
		}
		String url = request.getContextPath() + "/workbench/commission"
				+ mapping.getPath() + ".do";
		//int size = ((Integer) instanceMap.get("total")).intValue();
		int size = list.size();
		String pagerHeader = Pager.generate(offset, size, length, url,
				"method=listInstances&moduleId=" + moduleId);
		request.setAttribute("pagerHeader", pagerHeader);

		Iterator instanceIt = instanceList.iterator();
		request.setAttribute("moduleId", moduleId);
		request.setAttribute("instanceIt", instanceIt);
		//request.setAttribute("resultSize", instanceMap.get("total"));
		request.setAttribute("resultSize", String.valueOf(list.size()));
		return mapping.findForward("list");
	}

	public ActionForward listAllInstances(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public ActionForward newInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String moduleId = StaticMethod.null2String(request
				.getParameter("moduleId"));
		request.setAttribute("moduleId", moduleId);
		return mapping.findForward("edit");
	}

	public ActionForward editInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public ActionForward saveInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkbenchCommissionInstanceForm instanceForm = (TawWorkbenchCommissionInstanceForm) form;
		ITawWorkbenchCommissionInstanceMgr instanceMgr = (ITawWorkbenchCommissionInstanceMgr) getBean("ItawWorkbenchCommissionInstanceMgr");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		String userName = DictMgrLocator.getId2NameService().id2Name(userId,
				"tawSystemUserDao");
		TawWorkbenchCommissionInstance instance = (TawWorkbenchCommissionInstance) convert(instanceForm);
		instance.setUserId(userId);
		instance.setUserName(userName);
		instance.setTrustorName(DictMgrLocator.getId2NameService().id2Name(
				instance.getTrustorId(), "tawSystemUserDao"));
		instanceMgr.saveCommissionInstance(instance);
		request.setAttribute("moduleId", instance.getModuleId());
		return listInstances(mapping, form, request, response);
	}

	public ActionForward delInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = StaticMethod.null2String(request.getParameter("id"));
		ITawWorkbenchCommissionInstanceMgr instanceMgr = (ITawWorkbenchCommissionInstanceMgr) getBean("ItawWorkbenchCommissionInstanceMgr");
		TawWorkbenchCommissionInstance instance = instanceMgr
				.getCommissionInstance(id);
		if (instance.getId() == null || "".equals(instance.getId())) {
			return null;
		}
		request.setAttribute("moduleId", instance.getModuleId());
		instanceMgr.delCommissionInstance(instance);
		return listInstances(mapping, form, request, response);
	}
}
