package com.boco.eoms.sheet.industrysms.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.industrysms.model.AutomaticSwitch;
import com.boco.eoms.sheet.industrysms.model.IndustrySmsMain;
import com.boco.eoms.sheet.industrysms.service.AutomaticSwitchManager;
import com.boco.eoms.sheet.industrysms.service.IIndustrySmsMainManager;

/**
 * <p>
 * Title:行业短信开通删除工单
 * </p>
 * <p>
 * Description:行业短信开通删除工单
 * </p>
 * <p>
 * Mon Mar 04 17:27:01 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class IndustrySmsAction extends SheetAction  {
 	
 	 /**
	 * showDrawing
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
	 * showPic
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
	 * showKPI
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
	
	public ActionForward showSwitch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AutomaticSwitchManager automaticswitchmanager = (AutomaticSwitchManager)ApplicationContextHolder.getInstance().getBean("automaticswitchManager");
		AutomaticSwitch automaticswitch = new AutomaticSwitch();
		String name = "";
		List automaticswitchlist = (List)automaticswitchmanager.getAutomaticSwitchs(automaticswitch);
		if (automaticswitchlist.size()>0) {
			automaticswitch = (AutomaticSwitch) automaticswitchlist.get(0);
			name = StaticMethod.nullObject2String(automaticswitch.getName());
		}
		request.setAttribute("name", name);
		return mapping.findForward("showswitch");
	}
	
	public ActionForward changeSwitch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AutomaticSwitchManager automaticswitchmanager = (AutomaticSwitchManager)ApplicationContextHolder.getInstance().getBean("automaticswitchManager");
		IIndustrySmsMainManager service = (IIndustrySmsMainManager)ApplicationContextHolder.getInstance().getBean("iIndustrySmsMainManager");
		String condition = "status = '0' and spareTwo = '1'";
		AutomaticSwitch automaticswitch = new AutomaticSwitch();
		String name = "";
		List automaticswitchlist = (List)automaticswitchmanager.getAutomaticSwitchs(automaticswitch);
		if (automaticswitchlist.size()>0) {
			automaticswitch = (AutomaticSwitch) automaticswitchlist.get(0);
			name = StaticMethod.nullObject2String(automaticswitch.getName());
			if ("1".equals(name)) {
				name = "0";
				List mains = service.getMainsByCondition(condition);
				for (int i = 0; i < mains.size(); i++) {
					IndustrySmsMain main = (IndustrySmsMain)mains.get(i);
					main.setSpareTwo("0");
					service.saveOrUpdateMain(main);
				}
			} else {
				name = "1";
			}
			automaticswitch.setName(name);
			automaticswitchmanager.saveAutomaticSwitch(automaticswitch);
		} else {
			name = "0";
			automaticswitch.setName(name);
			automaticswitch.setDescription("自动处理开关");
			automaticswitchmanager.saveAutomaticSwitch(automaticswitch);
		}
		JSONArray json = new JSONArray();
		JSONObject jitem = new JSONObject();
		jitem.put("name", name);
		json.put(jitem);
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(json.toString());
		return null;
	}
 
 }
 



