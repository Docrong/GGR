package com.boco.eoms.duty.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventPub;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.service.ITawRmDutyEventPubManager;
import com.boco.eoms.duty.service.ITawRmExchangePerManager;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventForm;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventPubForm;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventSubForm;

/*
 * add by 龚玉峰
 * 
 * at 2008-11-18
 */
public class TawRmDutyEventAction extends BaseAction {

	private static int PAGE_LENGTH = 15;

	private ITawRmDutyEventPubManager tawRmDutyEnventPubManager;

	// 默认
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		System.out.println("-----------------------------");
		System.out.println("login add method");
		return mapping.findForward("add");
	}

	/*
	 * edit
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmDutyEventForm dutyform = (TawRmDutyEventForm) form;
		TawRmDutyEventPub tawRmDutyEventPub=new TawRmDutyEventPub();
		String isSubmit = request.getParameter("isSubmit");
		
		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) convert(dutyform);
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		ITawRmDutyEventPubManager tawRmDutyEnventPubManager;
		Object rdep = getBean("tawRmDutyEnventPubManager");
		tawRmDutyEnventPubManager = (ITawRmDutyEventPubManager)rdep;
		SimpleDateFormat  sdf = new  SimpleDateFormat( "MM/dd/yyyy HH:mm ");
		String dateTime=sdf.format(new Date());
		
		String id = tawRmDutyEvent.getId();
		tawRmDutyEvent.setIsSubmit(isSubmit);
		String systemdate = StaticMethod.getLocalString();
		tawRmDutyEvent.setInputdate(systemdate);
		tawRmDutyEvent.setInputuser(saveSessionBeanForm.getUserid());
		tawRmDutyEvent.setDeptid(saveSessionBeanForm.getDeptid());
		String s=dutyform.getPubStatus();
		if(s!=null){
			tawRmDutyEvent.setPubstatus(dutyform.getPubStatus());
		}else{
			tawRmDutyEvent.setPubstatus("2");
		}
		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
		updateFormBean(mapping, request, dutyform);
		
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventSubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		String whereStr = " where eventid = '" + id
				+ "'  ";
		Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex,
				new Integer(length), whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		List lt=null;
		String tid=null;
		String pubStatus=null;
		int pflay;
		pflay=Integer.parseInt(tawRmDutyEvent.getFlag());
		request.setAttribute("TawRmDutyEventSubList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));
		lt=tawRmDutyEnventPubManager.getEventId(id);
		if(lt.size()!=0){
			pubStatus=dutyform.getPubstatus();
			if(pubStatus!=null){
				for(int i = 0; i<lt.size();i++){
					tawRmDutyEventPub = (TawRmDutyEventPub)lt.get(i);
					tid= tawRmDutyEventPub.getId();
				}
			
			
				if(tid!=null){
					tawRmDutyEventPub.setId(tid);
				}
				tawRmDutyEventPub.setFlag(dutyform.getPflag());
				tawRmDutyEventPub.setEventid(id);
				tawRmDutyEventPub.setTitle(dutyform.getTitle());
				tawRmDutyEventPub.setDatatime(dateTime);
				tawRmDutyEventPub.setStarttime(dutyform.getStartTime());
				tawRmDutyEventPub.setEndtime(dutyform.getEndTime());
				tawRmDutyEventPub.setOid((int)dutyform.getOid());
				tawRmDutyEventPub.setMan(saveSessionBeanForm.getUserid());
				tawRmDutyEventPub.setPubstatus(dutyform.getPubStatus());
				tawRmDutyEnventPubManager.saveTawRmDutyEventPub(tawRmDutyEventPub);
				
			}
		}
		if(pflay>2){
			pubStatus=dutyform.getPubStatus();
			if(pubStatus!=null){
				for(int i = 0; i<lt.size();i++){
					tawRmDutyEventPub = (TawRmDutyEventPub)lt.get(i);
					tid= tawRmDutyEventPub.getId();
				}
			
			if(tid!=null){
				tawRmDutyEventPub.setId(tid);
			}
			tawRmDutyEventPub.setFlag(dutyform.getPflag());
			tawRmDutyEventPub.setEventid(id);
			tawRmDutyEventPub.setTitle(dutyform.getTitle());
			tawRmDutyEventPub.setDatatime(dateTime);
			tawRmDutyEventPub.setStarttime(dutyform.getStartTime());
			tawRmDutyEventPub.setEndtime(dutyform.getEndTime());
			tawRmDutyEventPub.setOid((int)dutyform.getOid());
			tawRmDutyEventPub.setMan(saveSessionBeanForm.getUserid());
			tawRmDutyEventPub.setPubstatus(dutyform.getPubStatus());
			
			tawRmDutyEnventPubManager.saveTawRmDutyEventPub(tawRmDutyEventPub);
				
			
		}
		}
		return mapping.findForward("addsuccess");
	}

	/*
	 * deleted
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
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		ITawRmDutyEventPubManager tawRmDutyEnventPubManager;
		Object rdep = getBean("tawRmDutyEnventPubManager");
		tawRmDutyEnventPubManager = (ITawRmDutyEventPubManager)rdep;
		List l=tawRmDutyEnventPubManager.getEventId(id);
		if(l.isEmpty()){
			mgr.removeTawRmDutyEvent(id);
		}else{
			
			tawRmDutyEnventPubManager.removeTawRmDutyEventPub(id);
			System.out.println(id);
			mgr.removeTawRmDutyEvent(id);
		}		
		return mapping.findForward("success");
	}

	/*
	 * search
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptId = saveSessionBeanForm.getDeptid();
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String whereStr = " where deptid like '" + deptId
				+ "%' ";
		Map map = (Map) mgr.getTawRmDutyEvents(pageIndex, new Integer(length),
				whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("search");
	}

	/*
	 * serachList
	 */
	public ActionForward serachList(ActionMapping mapping, ActionForm form,
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
	 * searchListShow
	 */
	public ActionForward searchListShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmDutyEventForm dutyform = (TawRmDutyEventForm) form;

		String deptId = saveSessionBeanForm.getDeptid();
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		StringBuffer whereStr = new StringBuffer();
		whereStr.append(" where deptid like '" + deptId + "%' ");
		if (!StaticMethod.null2String(dutyform.getEventtitle()).equals("")) {
			whereStr.append(" and eventtitle like '%"
					+ dutyform.getEventtitle() + "%'");
		}
		if (!StaticMethod.null2String(dutyform.getFaultType()).equals("")) {
			whereStr.append(" and faulttype = " + dutyform.getFaultType());
		}
		if (!StaticMethod.null2String(dutyform.getFlag()).equals("")) {
			whereStr.append(" and Flag = " + dutyform.getFlag());
		}
		if (!StaticMethod.null2String(dutyform.getComplateFlag()).equals("")) {
			whereStr
					.append(" and ComplateFlag = " + dutyform.getComplateFlag());
		}
		if (!StaticMethod.null2String(dutyform.getBeginTime()).equals("")) {
			whereStr.append(" and BeginTime >= '" + dutyform.getBeginTime()
					+ "'");
		}
		if (!StaticMethod.null2String(dutyform.getEndtime()).equals("")) {
			whereStr.append(" and Endtime <= '" + dutyform.getEndtime() + "'");
		}if (!StaticMethod.null2String(dutyform.getPubstatus()).equals("")) {
			whereStr.append(" and pubstatus = '" + dutyform.getPubstatus() + "'");
		}

		Map map = (Map) mgr.getTawRmDutyEvents(pageIndex, new Integer(length),
				whereStr.toString());
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("searchList");
	}

	/*
	 * 
	 */
	public ActionForward editList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmDutyEventForm dutyform = (TawRmDutyEventForm) form;
		String deptId = saveSessionBeanForm.getDeptid();
		String userid = saveSessionBeanForm.getUserid();
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String whereStr = " where deptid like '" + deptId
				+ "%'  and inputuser ='" + userid
				+ "'  and ComplateFlag = 2 ";
		Map map = (Map) mgr.getTawRmDutyEvents(pageIndex, new Integer(length),
				whereStr);
						
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("editList");
	}

	/*
	 * save
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		TawRmDutyEventForm dutyform = (TawRmDutyEventForm) form;
		String isSubmit = request.getParameter("isSubmit");
		SimpleDateFormat  sdf = new  SimpleDateFormat( "MM/dd/yyyy HH:mm ");
		String dateTime=sdf.format(new Date());

		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) convert(dutyform);
		TawRmDutyEventPub tawRmDutyEventPub = new TawRmDutyEventPub();
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		
		String flag = StaticMethod.null2String(tawRmDutyEvent.getFlag());
		System.out.println(flag);
		int s=Integer.parseInt(flag);
		
		tawRmDutyEvent.setIsSubmit(isSubmit);
		String systemdate = StaticMethod.getLocalString();
		tawRmDutyEvent.setInputdate(systemdate);
		tawRmDutyEvent.setInputuser(saveSessionBeanForm.getUserid());
		tawRmDutyEvent.setDeptid(saveSessionBeanForm.getDeptid());
		if(s>2){
			tawRmDutyEvent.setPubstatus(dutyform.getPubStatus());
		}else{
			tawRmDutyEvent.setPubstatus("2");
		}
		
		String id = mgr.saveTawRmDutyEvent(tawRmDutyEvent);
		
		if(s>2){
			ITawRmDutyEventPubManager tawRmDutyEnventPubManager;
			Object rdep = getBean("tawRmDutyEnventPubManager");
			tawRmDutyEnventPubManager = (ITawRmDutyEventPubManager)rdep;
			tawRmDutyEventPub.setFlag(dutyform.getPflag());
			tawRmDutyEventPub.setEventid(id);
			tawRmDutyEventPub.setTitle(dutyform.getTitle());
			tawRmDutyEventPub.setDatatime(dateTime);
			tawRmDutyEventPub.setStarttime(dutyform.getStartTime());
			tawRmDutyEventPub.setEndtime(dutyform.getEndTime());
			tawRmDutyEventPub.setOid((int)dutyform.getOid());
			tawRmDutyEventPub.setMan(saveSessionBeanForm.getUserid());
			tawRmDutyEventPub.setPubstatus(dutyform.getPubStatus());
			tawRmDutyEnventPubManager.saveTawRmDutyEventPub(tawRmDutyEventPub);
		}
		
		

		dutyform.setId(id);
		updateFormBean(mapping, request, dutyform);
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventSubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		String whereStr = " where eventid = '" + id
				+ "' ";
		Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex,
				new Integer(length), whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventSubList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));
		return mapping.findForward("addsuccess");
	}

	/*
	 * getEventForEdit
	 */
	public ActionForward getEventForEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		int length = 15;
		TawRmDutyEvent tawRmDutyEvent = null;
		TawRmDutyEventForm tawRmDutyEventForm = null;
		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventSubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String id = StaticMethod.null2String(request.getParameter("id"));
		TawRmDutyEventPub tawRmDutyEventPub=new TawRmDutyEventPub();
		try {
			ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
			String whereStr = " where eventid = '" + id
					+ "'  ";
			Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");// 取所有这个主记录的子记录 为了在页面显示
			request.setAttribute("TawRmDutyEventSubList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));
			tawRmDutyEvent = mgr.getTawRmDutyEvent(id);
			tawRmDutyEventForm= (TawRmDutyEventForm) convert(tawRmDutyEvent);
			
			String s=tawRmDutyEventForm.getPubstatus();
			if(s != null &&!("").equals(s)){
			if(!s.equals("2")){		
			ITawRmDutyEventPubManager tawRmDutyEnventPubManager;
			Object rdep = getBean("tawRmDutyEnventPubManager");
			tawRmDutyEnventPubManager = (ITawRmDutyEventPubManager)rdep;
			List ls=tawRmDutyEnventPubManager.getEventId(id);
			if(ls.size()!=0){
				for(int i = 0; i<ls.size();i++){
					tawRmDutyEventPub = (TawRmDutyEventPub)ls.get(i);
			}
				tawRmDutyEventForm.setPubStatus(tawRmDutyEventPub.getPubstatus());
				tawRmDutyEventForm.setDataTime(tawRmDutyEventPub.getDatatime());
				tawRmDutyEventForm.setEndTime(tawRmDutyEventPub.getEndtime());
				tawRmDutyEventForm.setPflag(tawRmDutyEventPub.getFlag());
				tawRmDutyEventForm.setEventId(tawRmDutyEventPub.getEventid());
				tawRmDutyEventForm.setOid(tawRmDutyEventPub.getOid());
				tawRmDutyEventForm.setStartTime(tawRmDutyEventPub.getStarttime());
				tawRmDutyEventForm.setTitle(tawRmDutyEventPub.getTitle());
			}
			}
			}
//			request.setAttribute("pubStatus",tawRmDutyEventPub.getPubstatus());
//			request.setAttribute("title", tawRmDutyEventPub.getTitle());
//			request.setAttribute("oid",new Integer(tawRmDutyEventPub.getOid()));
//			request.setAttribute("endTime", tawRmDutyEventPub.getEndtime());
//			request.setAttribute("startTime", tawRmDutyEventPub.getStarttime());
//			request.setAttribute("pflag",tawRmDutyEventPub.getFlag());
//			
			String flagName = "";
			String flag = StaticMethod.null2String(tawRmDutyEvent.getFlag());
			if (flag.equals("1"))
				flagName = "★";
			if (flag.equals("2"))
				flagName = "★★";
			if (flag.equals("3"))
				flagName = "★★★";
			if (flag.equals("4"))
				flagName = "★★★★";
			if (flag.equals("5"))
				flagName = "★★★★★";
			tawRmDutyEventForm.setFlagName(flagName);

			String faulttypeName = "";

			String faulttype = StaticMethod.null2String(tawRmDutyEvent
					.getFaultType());
			if (faulttype.equals("1"))
				faulttypeName = "故障(传输)";
			if (faulttype.equals("2"))
				faulttypeName = "故障(移动)";
			if (faulttype.equals("3"))
				faulttypeName = "故障(数固)";
			if (faulttype.equals("4"))
				faulttypeName = "故障(网络)";
			if (faulttype.equals("5"))
				faulttypeName = "重要社会";
			if (faulttype.equals("6"))
				faulttypeName = "灾害";
			tawRmDutyEventForm.setFaultTypeName(faulttypeName);

			String complateFlag = tawRmDutyEvent.getComplateFlag();
			String complateFlagName = "";
			if (complateFlag.equals("1"))
				complateFlagName = "完成";
			if (complateFlag.equals("2"))
				complateFlagName = "未完成";
			tawRmDutyEventForm.setComplateFlagName(complateFlagName);

			request.setAttribute("COMPLATEFLAG", tawRmDutyEvent
					.getComplateFlag());
			updateFormBean(mapping, request, tawRmDutyEventForm);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("addsuccess");
	}

	/*
	 * saveSub
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
				"TawRmDutyEventSubList")
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
		TawRmDutyEventSub sub = new TawRmDutyEventSub();
		TawRmDutyEventPub tawRmDutyEventPub=new TawRmDutyEventPub();

		try {
			String systemdate = StaticMethod.getLocalString();
			sub.setContent(content);
			sub.setEventid(id);
			sub.setHappentime(happentime);
			sub.setWorksheetid(worksheetid);
			sub.setRecordtime(systemdate);
			sub.setRecordman(saveSessionBeanForm.getUserid());
			ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
			
			ITawRmDutyEventPubManager tawRmDutyEnventPubManager;
			Object rdep = getBean("tawRmDutyEnventPubManager");
			tawRmDutyEnventPubManager = (ITawRmDutyEventPubManager)rdep;
			
			mgr.saveTawRmDutyEventSub(sub); // save
			String whereStr = " where eventid = '" + id
					+ "'  ";
			Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");// 取所有这个主记录的子记录 为了在页面显示
			request.setAttribute("TawRmDutyEventSubList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));

			TawRmDutyEvent tawRmDutyEvent = mgr.getTawRmDutyEvent(id);
			TawRmDutyEventForm tawRmDutyEventForm = (TawRmDutyEventForm) convert(tawRmDutyEvent);
			
			List ls=tawRmDutyEnventPubManager.getEventId(id);
			if(ls.size()!=0){
				for(int i = 0; i<ls.size();i++){
					tawRmDutyEventPub = (TawRmDutyEventPub)ls.get(i);
			}
				tawRmDutyEventForm.setPubStatus(tawRmDutyEventPub.getPubstatus());
				tawRmDutyEventForm.setDataTime(tawRmDutyEventPub.getDatatime());
				tawRmDutyEventForm.setEndTime(tawRmDutyEventPub.getEndtime());
				tawRmDutyEventForm.setPflag(tawRmDutyEventPub.getFlag());
				tawRmDutyEventForm.setEventId(tawRmDutyEventPub.getEventid());
				tawRmDutyEventForm.setOid(tawRmDutyEventPub.getOid());
				tawRmDutyEventForm.setStartTime(tawRmDutyEventPub.getStarttime());
				tawRmDutyEventForm.setTitle(tawRmDutyEventPub.getTitle());
			}
			
				
			
			
			request.setAttribute("editflag", "1");
			String flagName = "";
			String flag = StaticMethod.null2String(tawRmDutyEvent.getFlag());
			if (flag.equals("1"))
				flagName = "★";
			if (flag.equals("2"))
				flagName = "★★";
			if (flag.equals("3"))
				flagName = "★★★";
			if (flag.equals("4"))
				flagName = "★★★★";
			if (flag.equals("5"))
				flagName = "★★★★★";
			tawRmDutyEventForm.setFlagName(flagName);

			String faulttypeName = "";

			String faulttype = StaticMethod.null2String(tawRmDutyEvent
					.getFaultType());
			if (faulttype.equals("1"))
				faulttypeName = "故障(传输)";
			if (faulttype.equals("2"))
				faulttypeName = "故障(移动)";
			if (faulttype.equals("3"))
				faulttypeName = "故障(数固)";
			if (faulttype.equals("4"))
				faulttypeName = "故障(网络)";
			if (faulttype.equals("5"))
				faulttypeName = "重要社会";
			if (faulttype.equals("6"))
				faulttypeName = "灾害";
			tawRmDutyEventForm.setFaultTypeName(faulttypeName);

			String complateFlag = tawRmDutyEvent.getComplateFlag();
			String complateFlagName = "";
			if (complateFlag.equals("1"))
				complateFlagName = "完成";
			if (complateFlag.equals("2"))
				complateFlagName = "未完成";
			tawRmDutyEventForm.setComplateFlagName(complateFlagName);

			String pubStatus=tawRmDutyEvent.getPubstatus();
			String pubStatusName="";
			if(pubStatus!=null&&!("").equals(pubStatus)){
			if(pubStatus.equals("0"))
				pubStatusName="未发布";
			if(pubStatus.equals("1"))
				pubStatusName="发布";
			if(pubStatus.equals("2"))
				pubStatusName="不可发布";
			}else{
				pubStatusName="不可发布";
			}
			
			tawRmDutyEventForm.setPubStatusName(pubStatusName);
			updateFormBean(mapping, request, tawRmDutyEventForm);
			if (struts.equals("1")) {
				tawRmDutyEvent.setComplateFlag(struts);
				mgr.saveTawRmDutyEvent(tawRmDutyEvent);
				return mapping.findForward("otherSaveSuccess");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("addsuccess");
	}

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
				"TawRmDutyEventSubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String id = StaticMethod.null2String(request.getParameter("id"));
		try {
			ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
			
			String whereStr = " where eventid = '" + id
					+ "' ";
			Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");// 取所有这个主记录的子记录 为了在页面显示
			request.setAttribute("TawRmDutyEventSubList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));

			TawRmDutyEvent tawRmDutyEvent = mgr.getTawRmDutyEvent(id);
			TawRmDutyEventForm tawRmDutyEventForm = (TawRmDutyEventForm) convert(tawRmDutyEvent);
			
			String flagName = "";
			String flag = StaticMethod.null2String(tawRmDutyEvent.getFlag());
			if (flag.equals("1"))
				flagName = "★";
			if (flag.equals("2"))
				flagName = "★★";
			if (flag.equals("3"))
				flagName = "★★★";
			if (flag.equals("4"))
				flagName = "★★★★";
			if (flag.equals("5"))
				flagName = "★★★★★";
			tawRmDutyEventForm.setFlagName(flagName);

			String faulttypeName = "";

			String faulttype = StaticMethod.null2String(tawRmDutyEvent
					.getFaultType());
			if (faulttype.equals("1"))
				faulttypeName = "故障(传输)";
			if (faulttype.equals("2"))
				faulttypeName = "故障(移动)";
			if (faulttype.equals("3"))
				faulttypeName = "故障(数固)";
			tawRmDutyEventForm.setFaultTypeName(faulttypeName);

			String complateFlag = tawRmDutyEvent.getComplateFlag();
			String complateFlagName = "";
			if (complateFlag.equals("1"))
				complateFlagName = "完成";
			if (complateFlag.equals("2"))
				complateFlagName = "未完成";
			tawRmDutyEventForm.setComplateFlagName(complateFlagName);
			
			
			String pubStatus=tawRmDutyEvent.getPubstatus();
			
			String pubStatusName="";
			if(pubStatus!=null&&!("").equals(pubStatus)){
			if(pubStatus.equals("0"))
				pubStatusName="未发布";
			if(pubStatus.equals("1"))
				pubStatusName="发布";
			if(pubStatus.equals("2"))
				pubStatusName="不可发布";
			}else{
				pubStatusName="不可发布";
			}
			tawRmDutyEventForm.setPubStatusName(pubStatusName);
				
			
			
			request.setAttribute("COMPLATEFLAG", tawRmDutyEvent
					.getComplateFlag());
			updateFormBean(mapping, request, tawRmDutyEventForm);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("eventSuccess");
	}

	/*
	 * saveSub
	 */
	public ActionForward saveSubOther(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventSubList")
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
		TawRmDutyEventSub sub = new TawRmDutyEventSub();

		try {
			String systemdate = StaticMethod.getLocalString();
			sub.setContent(content);
			sub.setEventid(id);
			sub.setHappentime(happentime);
			sub.setWorksheetid(worksheetid);
			sub.setRecordtime(systemdate);
			sub.setRecordman(saveSessionBeanForm.getUserid());
			ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
			mgr.saveTawRmDutyEventSub(sub); // save
			String whereStr = " where eventid = '" + id
					+ "'  ";
			Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex, new Integer(
					length), whereStr);
			List list = (List) map.get("result");// 取所有这个主记录的子记录 为了在页面显示
			request.setAttribute("TawRmDutyEventSubList", list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", new Integer(length));

			TawRmDutyEvent tawRmDutyEvent = mgr.getTawRmDutyEvent(id);
			if (struts.equals("1")) {
				tawRmDutyEvent.setComplateFlag(struts);
				mgr.saveTawRmDutyEvent(tawRmDutyEvent);
			}
			TawRmDutyEventForm tawRmDutyEventForm = (TawRmDutyEventForm) convert(tawRmDutyEvent);
			String flagName = "";
			String flag = StaticMethod.null2String(tawRmDutyEvent.getFlag());
			if (flag.equals("1"))
				flagName = "★";
			if (flag.equals("2"))
				flagName = "★★";
			if (flag.equals("3"))
				flagName = "★★★";
			if (flag.equals("4"))
				flagName = "★★★★";
			if (flag.equals("5"))
				flagName = "★★★★★";
			tawRmDutyEventForm.setFlagName(flagName);

			String faulttypeName = "";

			String faulttype = StaticMethod.null2String(tawRmDutyEvent
					.getFaultType());
			if (faulttype.equals("1"))
				faulttypeName = "故障(传输)";
			if (faulttype.equals("2"))
				faulttypeName = "故障(移动)";
			if (faulttype.equals("3"))
				faulttypeName = "故障(数固)";
			tawRmDutyEventForm.setFaultTypeName(faulttypeName);

			String complateFlag = StaticMethod.null2String(tawRmDutyEvent
					.getComplateFlag());
			String complateFlagName = "";
			if (complateFlag.equals("1"))
				complateFlagName = "完成";
			if (complateFlag.equals("2"))
				complateFlagName = "未完成";
			tawRmDutyEventForm.setComplateFlagName(complateFlagName);

			request.setAttribute("COMPLATEFLAG", tawRmDutyEvent
					.getComplateFlag());
			updateFormBean(mapping, request, tawRmDutyEventForm);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("false");
		}

		return mapping.findForward("otherSaveSuccess");
	}

	/*
	 * 大值班 add by fengshaohong
	 */
	public ActionForward provinceNotes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawRmDutyEventManager Eventmgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		String areaId = "";
		List listlist = new ArrayList();
		List list = mgr.getSamelevelDeptbyDeptids("1", "1", "0");
		for (int i = 0; i < list.size(); i++) {
			List eventList = new ArrayList();
			TawSystemDept tawSystemDept = (TawSystemDept) list.get(i);
			areaId = tawSystemDept.getDeptId();
			eventList = Eventmgr.getTawRmDutyEventByDeptAndFlag(areaId, "1",
					"5");
			listlist.add(eventList);
			if (!eventList.isEmpty()) {
				int k = Integer.parseInt(((TawRmDutyEvent) eventList.get(0))
						.getFlag());
				if (k < 2) {
					tawSystemDept.setRemark("0");
				} else if (k == 2) {
					tawSystemDept.setRemark("1");
				} else {
					tawSystemDept.setRemark("2");
				}
			} else {
				tawSystemDept.setRemark("0");
			}

		}
		request.setAttribute("noteList", list);
		request.setAttribute("listlist", listlist);
		return mapping.findForward("noteList");

	}

	/*
	 * 大值班 add by fengshaohong
	 */
	public ActionForward noteDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptId = (String) request.getParameter("deptId");
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String whereStr = " where deptid like '" + deptId
				+ "%'  ";
		Map map = (Map) mgr.getTawRmDutyEvents(pageIndex, new Integer(length),
				whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("search");
	}

	/*
	 * 大值班 add by fengshaohong
	 */
	public ActionForward bigWorkTitle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String isOnDuty = saveSessionBeanForm.getWorkSerial();
		if ("".equals(isOnDuty) || isOnDuty == null || isOnDuty.equals("0")) {

			return search(mapping, form, request, response);
		} else {
			return add(mapping, form, request, response);
		}
	}

}
