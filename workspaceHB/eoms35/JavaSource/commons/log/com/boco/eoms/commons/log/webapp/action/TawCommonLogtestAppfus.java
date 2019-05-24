package com.boco.eoms.commons.log.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.log.webapp.bo.ITawCommonLogSearchBo;
import com.boco.eoms.commons.log.webapp.bo.impl.TawCommonLogSearchBoImpl;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;

public class TawCommonLogtestAppfus extends BaseAction {

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		return mapping.findForward("list");
	}
	
public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITawCommonLogSearchBo treebo = (ITawCommonLogSearchBo) ApplicationContextHolder
		.getInstance().getBean("iTawCommonLogSearchBo");
		JSONArray jsonRoot = treebo.getSmsServiceTreeXml(nodeId, "aa1");

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}
	
}
