package com.boco.eoms.duty.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.model.TawRmEmergency;
 
import com.boco.eoms.duty.service.ITawRmEmergencyManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmEmergencyForm;
 

/*
 * add by 龚玉峰
 * 
 * at 2008-11-18
 */
public class TawRmEmergencyAction extends BaseAction {

	private static int PAGE_LENGTH = 15;

	// 默认
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// return mapping.findForward("search");
		return null;
	}

	// 主调
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		return mapping.findForward("main");
	}

	/*
	 * add
	 * 
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		return mapping.findForward("add");
	}

	/*
	 * edit
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmEmergencyForm dutyform = (TawRmEmergencyForm) form;
		String isSubmit = request.getParameter("isSubmit");

		TawRmEmergency TawRmEmergency = (TawRmEmergency) convert(dutyform);
		ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmDutyEnventManager");
		String id = TawRmEmergency.getId();
		 
		String systemdate = StaticMethod.getLocalString();
		 
		TawRmEmergency.setDeptid(saveSessionBeanForm.getDeptid());
		mgr.saveTawRmEmergency(TawRmEmergency);
		updateFormBean(mapping, request, dutyform);
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmEmergencySubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		String whereStr = " where eventid = '" + id
				+ "' ";
		Map map = (Map) mgr.getTawRmEmergencySubs(pageIndex,
				new Integer(length), whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmEmergencySubList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("addsuccess");
	}

	/*
	 * deleted
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleted(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String id = StaticMethod.null2String(request.getParameter("id"));
		ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmEmergencyManager");
		mgr.removeTawRmEmergency(id);
		return mapping.findForward("success");
	}

	/*
	 * search
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		
		return mapping.findForward("searchList");
	}

	/*
	 * serachList
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward serachList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmEmergencyForm dutyform = (TawRmEmergencyForm) form;
		TawRmEmergency tawRmEmergency = (TawRmEmergency) convert(dutyform);
		ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmEmergencyManager");
		List emergencyList = new ArrayList();
		dutyform.setDeptName("");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawRmEmergencyList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		
		String whereStr = " where 1=1";
		
		if(!"".equals(tawRmEmergency.getSpecialty())&&tawRmEmergency.getSpecialty()!=null){
			whereStr = whereStr + " and specialty like '%"+tawRmEmergency.getSpecialty()+"%'";
		}
		if(!"".equals(tawRmEmergency.getDeptid())&&tawRmEmergency.getDeptid()!=null){
			whereStr = whereStr + " and deptid = '"+tawRmEmergency.getDeptid()+"'";
		}
		if(!"".equals(tawRmEmergency.getImmobility())&&tawRmEmergency.getImmobility()!=null){
			whereStr = whereStr + " and immobility like '%"+tawRmEmergency.getImmobility()+"%'";
		}
		if(!"".equals(tawRmEmergency.getOther())&&tawRmEmergency.getOther()!=null){
			whereStr = whereStr + " and other like '%"+tawRmEmergency.getOther()+"%'";
		}			
		Map map = (Map) mgr.getTawRmEmergencys(pageIndex, new Integer(length),
				whereStr);
		ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder
		.getInstance().getBean("itawSystemUserManager");
        ITawSystemDeptManager dept = (ITawSystemDeptManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDeptManager");
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		
		for (Iterator it = list.iterator();it.hasNext();){
			TawRmEmergency emergency = (TawRmEmergency)it.next();
			emergency.setUserName(user.id2Name(emergency.getCruser()));
			emergency.setDeptName(dept.id2Name(emergency.getDeptid()));
			emergencyList.add(emergency);
		}
		request.setAttribute("tawRmEmergencyList", emergencyList);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));
	 
		return mapping.findForward("search");
	}

	/*
	 * searchListShow
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchListShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmEmergencyForm dutyform = (TawRmEmergencyForm) form;

		String deptId = saveSessionBeanForm.getDeptid();
		ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmDutyEnventManager");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmEmergencyList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		StringBuffer whereStr = new StringBuffer();
		whereStr.append(" where deptid like '" + deptId + "%' ");
		 

		Map map = (Map) mgr.getTawRmEmergencys(pageIndex, new Integer(length),
				whereStr.toString());
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmEmergencyList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("searchList");
	}
 
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String userid = saveSessionBeanForm.getUserid();  
		ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmEmergencyManager");
		ITawSystemUserManager user = (ITawSystemUserManager) ApplicationContextHolder
		.getInstance().getBean("itawSystemUserManager");

        ITawSystemDeptManager dept = (ITawSystemDeptManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemDeptManager");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawRmEmergencyList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String whereStr = " where cruser ='" + userid
				+ "' ";
		Map map = (Map) mgr.getTawRmEmergencys(pageIndex, new Integer(length),
				whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		List emergencyList = new ArrayList();
		for (Iterator it = list.iterator();it.hasNext();){
			TawRmEmergency tawRmEmergency = (TawRmEmergency)it.next();
			tawRmEmergency.setUserName(user.id2Name(tawRmEmergency.getCruser()));
			tawRmEmergency.setDeptName(dept.id2Name(tawRmEmergency.getDeptid()));
			emergencyList.add(tawRmEmergency);
		}
		request.setAttribute("tawRmEmergencyList", emergencyList);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("editList");
	}

	/*
	 * save
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		try{
		TawRmEmergencyForm dutyform = (TawRmEmergencyForm) form;
		TawRmEmergency tawRmEmergency = (TawRmEmergency) convert(dutyform);
		ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmEmergencyManager");
		String systemdate = StaticMethod.getLocalString();			
		tawRmEmergency.setCrtime(systemdate); 					   // 当前时间
		tawRmEmergency.setCruser(saveSessionBeanForm.getUserid()); // 当前用户
		String id = mgr.saveTawRmEmergency(tawRmEmergency);
		request.setAttribute("id",id);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("false");
		}
		return mapping.findForward("success");
	}

 
	/**
	 * 取出修改的类为页面显示以便修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getForEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
	 
		String id = StaticMethod.null2String(request.getParameter("id"));
		try {
			ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmEmergencyManager");
			TawRmEmergency tawRmEmergency = mgr.getTawRmEmergency(id);
			ITawSystemDeptManager dept = (ITawSystemDeptManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemDeptManager");
			tawRmEmergency.setDeptName(dept.id2Name(tawRmEmergency.getDeptid()));
			TawRmEmergencyForm TawRmEmergencyForm = (TawRmEmergencyForm) convert(tawRmEmergency);
		    updateFormBean(mapping, request, TawRmEmergencyForm);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("addsuccess");
	}

	/*
	 * saveSub
	 */
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmEmergencySubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String id = StaticMethod.null2String(request.getParameter("id"));
		String happentime = StaticMethod.null2String(request
				.getParameter("happentime"));
		String worksheetid = StaticMethod.null2String(request
				.getParameter("worksheetid"));
		String content = StaticMethod.null2String(request
				.getParameter("content"));
		String struts = StaticMethod
				.null2String(request.getParameter("struts"));
		 
		try {
			String systemdate = StaticMethod.getLocalString();
			 
			ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmDutyEnventManager");
			//mgr.saveTawRmEmergencySub(sub); // save
			String whereStr = " where eventid = '" + id
					+ "'  ";
			Map map = (Map) mgr.getTawRmEmergencySubs(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");// 取所有这个主记录的子记录 为了在页面显示
			request.setAttribute("TawRmEmergencySubList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));

			TawRmEmergency TawRmEmergency = mgr.getTawRmEmergency(id);
			TawRmEmergencyForm TawRmEmergencyForm = (TawRmEmergencyForm) convert(TawRmEmergency);
			request.setAttribute("editflag", "1");
			String flagName = "";
			 
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("addsuccess");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmEmergencySubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String id = StaticMethod.null2String(request.getParameter("id"));
		try {
			ITawRmEmergencyManager mgr = (ITawRmEmergencyManager) getBean("ITawRmDutyEnventManager");
			String whereStr = " where eventid = '" + id
					+ "'  ";
			Map map = (Map) mgr.getTawRmEmergencySubs(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");// 取所有这个主记录的子记录 为了在页面显示
			request.setAttribute("TawRmEmergencySubList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));

			TawRmEmergency TawRmEmergency = mgr.getTawRmEmergency(id);

			TawRmEmergencyForm TawRmEmergencyForm = (TawRmEmergencyForm) convert(TawRmEmergency);

			String flagName = "";
			 
			updateFormBean(mapping, request, TawRmEmergencyForm);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("eventSuccess");
	}

  
}
