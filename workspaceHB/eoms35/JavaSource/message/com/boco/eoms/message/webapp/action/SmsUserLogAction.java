package com.boco.eoms.message.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.message.mgr.ISmsMobilesTemplateManager;
import com.boco.eoms.message.webapp.form.SmsUserLogForm;
import com.boco.eoms.parter.baseinfo.contract.util.TawContractConstants;

public class SmsUserLogAction extends BaseAction {

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmsUserLogForm smsUserLogForm = (SmsUserLogForm) form;
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawContractConstants.TAWCONTRACT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map map=(Map)mgr.searchUser(pageIndex,pageSize,smsUserLogForm);
		List list = (List) map.get("result");
		request.setAttribute("tawContractList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("logList");
	}
	
	public ActionForward searchList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		// 取某版块对象
		List list = mgr.getNodes4Team();
		request.setAttribute("teamList", list);
		return mapping.findForward("searchForm");
		
	}
}
