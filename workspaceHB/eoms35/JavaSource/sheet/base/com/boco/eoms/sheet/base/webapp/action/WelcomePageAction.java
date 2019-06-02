/*
 * Created on 2008-2-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WelcomePageAction extends BaseAction{
	public ActionForward showWelcomeList(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List beanList = new ArrayList();
		beanList.add("SeResourceAssess");
		beanList.add("SeSpecification");
		beanList.add("Nop");
		beanList.add("performance");
		beanList.add("BigAct");
		beanList.add("AbrEvent");
		beanList.add("Design");
		beanList.add("Dril");
		
		beanList.add("BusinessDesign");
		beanList.add("BusinessPilot");
		beanList.add("BusinessPromotion");
		beanList.add("PvmProcess");
		
		for(int i=0;i<beanList.size();i++){
			String beanName = (String)beanList.get(i);
			IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
			Map map = baseSheet.getUndoList(request);
			
			request.setAttribute(beanName+"List", map.get("taskList"));
			request.setAttribute(beanName+"Total", map.get("total"));
		}
		
		return mapping.findForward("success");
	}
}
