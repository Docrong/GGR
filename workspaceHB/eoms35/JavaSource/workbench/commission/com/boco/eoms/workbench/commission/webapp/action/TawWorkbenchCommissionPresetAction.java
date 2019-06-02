package com.boco.eoms.workbench.commission.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.workbench.commission.mgr.ITawWorkbenchCommissionPresetMgr;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionPreset;
import com.boco.eoms.workbench.commission.webapp.form.TawWorkbenchCommissionPresetForm;

public class TawWorkbenchCommissionPresetAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return listAllPresets(mapping, form, request, response);
	}

	public ActionForward listAllPresets(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String moduleId = StaticMethod.null2String(request
				.getParameter("moduleId"));
		String trustorIds = "[]";
		String trustorsStr = "";
		if (moduleId == null || "".equals(moduleId)) {
			moduleId = StaticMethod.nullObject2String(request
					.getAttribute("moduleId"));
		}
		if (moduleId != null && !"".equals(moduleId)) {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userId = sessionform.getUserid();
			trustorIds = findTrustorsJSON(userId, moduleId);
			trustorsStr = findTrustorsString(userId, moduleId);
		}
		request.setAttribute("moduleId", moduleId);
		request.setAttribute("trustorIds", trustorIds);
		request.setAttribute("trustorsStr", trustorsStr);
		return mapping.findForward("list");
	}

	public ActionForward newPreset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public ActionForward editPreset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public ActionForward savePreset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawWorkbenchCommissionPresetMgr commissionPresetMgr = (ITawWorkbenchCommissionPresetMgr) getBean("ItawWorkbenchCommissionPresetMgr");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		TawWorkbenchCommissionPresetForm commissionPresetForm = (TawWorkbenchCommissionPresetForm) form;
		String idStr = commissionPresetForm.getTrustorId();
		String[] trustorIds = idStr.split(",");
		commissionPresetMgr.delCommissionPresets(userId, commissionPresetForm
				.getModuleId());
		if (trustorIds != null) {
			for (int i = 0; i < trustorIds.length; i++) {
				String trustorId = trustorIds[i];
				if (trustorId != null && !"".equals(trustorId)
						&& !"[]".equals(trustorId)) {
					TawWorkbenchCommissionPreset commissionPreset = new TawWorkbenchCommissionPreset();
					commissionPreset.setModuleId(commissionPresetForm
							.getModuleId());
					commissionPreset.setTrustorId(trustorId);
					commissionPreset.setUserId(userId);
					commissionPreset.setTrustorName(DictMgrLocator
							.getId2NameService().id2Name(
									commissionPreset.getTrustorId(),
									"tawSystemUserDao"));
					commissionPreset.setUserName(DictMgrLocator
							.getId2NameService().id2Name(
									commissionPreset.getUserId(),
									"tawSystemUserDao"));
					commissionPresetMgr.saveCommissionPrese(commissionPreset);
				}
			}
		}
		request.setAttribute("moduleId", commissionPresetForm.getModuleId());
		return listAllPresets(mapping, form, request, response);
	}

	public String findTrustorsJSON(String userId, String moduleId) throws Exception {
		ITawWorkbenchCommissionPresetMgr commissionPresetMgr = (ITawWorkbenchCommissionPresetMgr) getBean("ItawWorkbenchCommissionPresetMgr");

		List list = (ArrayList) commissionPresetMgr.listCommissionPresets(
				userId, moduleId);
		JSONArray jsonRoot = new JSONArray();
		String jsonStr = "[]";
		if (list.size() > 0) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				TawWorkbenchCommissionPreset commissionPreset = (TawWorkbenchCommissionPreset) it
						.next();
				JSONObject jitem = new JSONObject();
				jitem.put("id", commissionPreset.getTrustorId());
				jitem.put("name", commissionPreset.getTrustorName());
				jsonRoot.put(jitem);
			}
			jsonStr = jsonRoot.toString();
		}
		return jsonStr;
	}
	
	public String findTrustorsString(String userId, String moduleId) throws Exception {
		ITawWorkbenchCommissionPresetMgr commissionPresetMgr = (ITawWorkbenchCommissionPresetMgr) getBean("ItawWorkbenchCommissionPresetMgr");

		List list = (ArrayList) commissionPresetMgr.listCommissionPresets(
				userId, moduleId);
		String trustorsStr = "";
		if (list.size() > 0) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				TawWorkbenchCommissionPreset commissionPreset = (TawWorkbenchCommissionPreset) it
						.next();
				trustorsStr = trustorsStr + commissionPreset.getTrustorId() + ",";
			}
			if (trustorsStr.endsWith(",")) {
				trustorsStr = trustorsStr.substring(0, trustorsStr.length() - 1);
			}
		}
		return trustorsStr;
	}

	public ActionForward delPreset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public void userFromPreset(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ITawWorkbenchCommissionPresetMgr presetMgr = (ITawWorkbenchCommissionPresetMgr) getBean("ItawWorkbenchCommissionPresetMgr");
		String moduleId = StaticMethod.null2String(request
				.getParameter("moduleId"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		JSONArray jsonRoot = presetMgr.listCommissionPresetsJSON(userId,
				moduleId);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
	}
}
