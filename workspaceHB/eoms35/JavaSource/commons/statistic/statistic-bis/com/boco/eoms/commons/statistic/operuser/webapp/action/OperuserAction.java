package com.boco.eoms.commons.statistic.operuser.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.commons.statistic.base.webapp.action.IStatMethod;
import com.boco.eoms.commons.statistic.base.webapp.action.StatAction;

public class OperuserAction extends StatAction {

	public ActionForward worklevelPerformStatistic(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		//request.setAttribute("findForward", "oper_statistic");
		//request.setAttribute("findListForward", "workleveloper_statisticsheetlist");
		
		statMethod.showStatisticPage(mapping, form, request, response);
		
		statMethod.performStatistic(mapping, form, request, response);
		
		
	 return  mapping.findForward("worklevel_statisticresult");
		
	}
	
	public ActionForward subareaPerformStatistic(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		//request.setAttribute("findListForward", "subarea_statisticsheetlist");
		statMethod.showStatisticPage(mapping, form, request, response);
		
		statMethod.performStatistic(mapping, form, request, response);
		
		
	 return  mapping.findForward("statisticresult");
		
	}
	
	public ActionForward prizelevelPerformStatistic(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IStatMethod statMethod = (IStatMethod) getBean(mapping.getAttribute());
		statMethod.showStatisticPage(mapping, form, request, response);
		
		statMethod.performStatistic(mapping, form, request, response);
		
		return  mapping.findForward("statisticresult");
		
	}
	
}
