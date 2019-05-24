package com.boco.eoms.duty.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.writer.Writer;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.dao.TawUserRoomDAO;
import com.boco.eoms.duty.mgr.EventReadMgr;
import com.boco.eoms.duty.mgr.FaultCircuitMgr;
import com.boco.eoms.duty.mgr.FaultCommontMgr;
import com.boco.eoms.duty.mgr.FaultEquipmentMgr;
import com.boco.eoms.duty.model.EventRead;
import com.boco.eoms.duty.model.TawRmAssignwork;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventRegion;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.duty.model.TawRmRecord;
import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventForm;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventSubForm;

/*
 * add by 李江红
 * 
 * at 2008-11-18
 */
public class YnTawRmDutyEventAction extends BaseAction {

	private static int PAGE_LENGTH = 15;

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
		TawRmDutyEventForm tawrmDutyEventForm = (TawRmDutyEventForm)form;
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// 判断是否值班
		if (saveSessionBeanForm.getWorkSerial().equals("0")) {
			// DutySchedule dutySchedule =new DutySchedule();
			// dutySchedule.TawRmRecordEmail();
			return mapping.findForward("notonduty");
		}
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		// 如果根据工单号生成事件，已经存在了这个工单号对应的事件，就转到相关事件
		if(tawrmDutyEventForm.getSubWorksheetid()!=null&&!tawrmDutyEventForm.getSubWorksheetid().equals("")) {
			List list = mgr.getTawRmDutyEventBySheetId(tawrmDutyEventForm.getSubWorksheetid());
			if(list!=null&&list.size()>0) {
				tawrmDutyEventForm.setId(((TawRmDutyEvent)list.get(0)).getId());
				return edit(mapping, tawrmDutyEventForm, request, response);
			}
		}
		
		tawrmDutyEventForm.setComplateFlag("2"); // 状态默认为未完成。
		tawrmDutyEventForm.setInputdate(StaticMethod.getCurrentDateTime());
		tawrmDutyEventForm.setInputuser(saveSessionBeanForm.getUserid());
		tawrmDutyEventForm.setRoomid(saveSessionBeanForm.getRoomId());
		tawrmDutyEventForm.setWorkserial(saveSessionBeanForm.getWorkSerial());
		
		//获取本班次及其其它班次未完成的事件,事件要属于本机房新增或者是相关机房
		
		String condition = " AND (tawRmDutyEvent.workserial=" + saveSessionBeanForm.getWorkSerial()
						+ " OR tawRmDutyEvent.complateFlag=2 "
						+ " AND (tawRmDutyEvent.roomid=" + saveSessionBeanForm.getRoomId() + ")"
						+ " OR tawRmDutyEvent.regionlist like ('%#" + CommonTools.getItemId(saveSessionBeanForm.getRoomId()) +"#%')) ";
		
		int length = 1000;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map map = (Map) mgr.getEventByCondition(pageIndex,new Integer(length), condition);		
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));
		
		// 地区数据字典
    	IDictService dictService = (IDictService) this.getBean("DictService");
        List regionlist = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + tawrmDutyEventForm.REGIONSDICT);
		request.setAttribute("REGIONLIST", regionlist);
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

		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
		FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");	
		TawRmDutyEvent tawRmDutyEvent = mgr.getTawRmDutyEvent(dutyform.getId());
		tawRmDutyEvent.setRegionname(CommonTools.getTypeNameFromXML(CommonTools.parseTypeList(tawRmDutyEvent.getRegionlist()),TawRmDutyEventForm.REGIONSDICT));
		tawRmDutyEvent.setFaultCommontCount(StaticMethod.nullObject2int(faultCommontMgr.getNum(" AND faultCommont.recordPerId='" + tawRmDutyEvent.getId() + "' ")));
		tawRmDutyEvent.setFaultCircuitCount(StaticMethod.nullObject2int(faultCircuitMgr.getNum(" AND faultCircuit.recordPerId='" + tawRmDutyEvent.getId() + "' ")));
		tawRmDutyEvent.setFaultEquipmentCount(StaticMethod.nullObject2int(faultEquipmentMgr.getNum(" AND faultEquipment.recordPerId='" + tawRmDutyEvent.getId() + "' ")));
		if(tawRmDutyEvent.getFaultCommontId()!=null&&!tawRmDutyEvent.getFaultCommontId().equals("")) {
			tawRmDutyEvent.setFaultReportSheet(faultCommontMgr.getFaultCommont(tawRmDutyEvent.getFaultCommontId()).getSerialNos());
		} else {
			tawRmDutyEvent.setFaultReportSheet(null);
		}
		
		dutyform = (TawRmDutyEventForm) convert(tawRmDutyEvent);
		updateFormBean(mapping, request, dutyform);
		
		// 子过程列表
		ITawRmDutyEventManager subMgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		String condition = " where eventid= '" + tawRmDutyEvent.getId() + "' ";
		int length = 100;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventSubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map map = (Map) subMgr.getTawRmDutyEventSubs(pageIndex,new Integer(length), condition);		
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventSubList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		// 初始化地区数据字典
    	IDictService dictService = (IDictService) this.getBean("DictService");
        List regionlist = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + TawRmDutyEventForm.REGIONSDICT);
		request.setAttribute("REGIONLIST", regionlist);
		return mapping.findForward("edit");
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
		TawRmDutyEventForm dutyForm = (TawRmDutyEventForm)form;
		
		String id = StaticMethod.null2String(request.getParameter("id"));
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		mgr.removeTawRmDutyEvent(id);
		return add(mapping, dutyForm, request, response);
	}
	
	/**
	 * 删除子过程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletedSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");

		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String id = StaticMethod.null2String(request.getParameter("id"));
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		TawRmDutyEventSub tawRmDutyEventSub = mgr.getTawRmDutyEventSub(id);
		
		TawRmDutyEventSubForm dutyform = new TawRmDutyEventSubForm();
		dutyform.setId(tawRmDutyEventSub.getId());
		dutyform.setEventid(tawRmDutyEventSub.getEventid());
	
		mgr.removeTawRmDutyEventSub(id);
		updateFormBean(mapping, request, dutyform);
		return mapping.findForward("edit");
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
		}

		Map map = (Map) mgr.getTawRmDutyEvents(pageIndex, new Integer(length),
				whereStr.toString());
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("search");
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
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		TawRmDutyEventForm dutyform = (TawRmDutyEventForm) form;
		String isSubmit = request.getParameter("isSubmit");
		boolean isNew = (null == dutyform.getId() || "".equals(dutyform.getId()));
		String id = dutyform.getId();

		if (isNew) {// 新增加
			TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) convert(dutyform);
			tawRmDutyEvent.setIsSubmit(isSubmit);
			tawRmDutyEvent.setInputdate(StaticMethod.getCurrentDateTime());
			tawRmDutyEvent.setInputuser(saveSessionBeanForm.getUserid());
			tawRmDutyEvent.setDeptid(saveSessionBeanForm.getDeptid());
			tawRmDutyEvent.setSheetid(dutyform.getSubWorksheetid());
			id = mgr.saveTawRmDutyEvent(tawRmDutyEvent);
			
			// 保存子过程信息
			TawRmDutyEventSub tawRmDutyEventSub = new TawRmDutyEventSub();
			tawRmDutyEventSub.setDeptid(saveSessionBeanForm.getDeptid());
			tawRmDutyEventSub.setRoomid(saveSessionBeanForm.getRoomId());
			tawRmDutyEventSub.setEventid(id);
			tawRmDutyEventSub.setRecordman(saveSessionBeanForm.getUserid());
			tawRmDutyEventSub.setWorksheetid(dutyform.getSubWorksheetid());
			tawRmDutyEventSub.setHappentime(dutyform.getSubHappentime());
			tawRmDutyEventSub.setContent(dutyform.getSubContent());
			tawRmDutyEventSub.setRecordtime(StaticMethod.getCurrentDateTime());
			mgr.saveTawRmDutyEventSub(tawRmDutyEventSub);
			
			// 第一次新增时，增加是否已阅读
			EventReadMgr eventReadMgr = (EventReadMgr) getBean("eventReadMgr");
			EventRead eventRead = new EventRead();
			eventRead.setDeptid(saveSessionBeanForm.getDeptid());
			eventRead.setEventid(id);
			eventRead.setReadaffirm("0");
			eventRead.setReadtime(StaticMethod.getCurrentDateTime());
			eventRead.setUserid(saveSessionBeanForm.getUserid());
			eventReadMgr.saveEventRead(eventRead);
		} else {
			TawRmDutyEvent tawRmDutyEvent = mgr.getTawRmDutyEvent(dutyform.getId());
			
			tawRmDutyEvent.setFaultType(dutyform.getFaultType());
			tawRmDutyEvent.setFlag(dutyform.getFlag());
			tawRmDutyEvent.setBeginTime(dutyform.getBeginTime());
			tawRmDutyEvent.setEndtime(dutyform.getEndtime());
			tawRmDutyEvent.setEventtitle(dutyform.getEventtitle());
			tawRmDutyEvent.setRegionlist(dutyform.getRegionlist());
			tawRmDutyEvent.setComplateFlag(dutyform.getComplateFlag());

			mgr.saveTawRmDutyEvent(tawRmDutyEvent);
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

		String whereStr = " where eventid = '" + id + "'";
		Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex,new Integer(length), whereStr);
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventSubList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));
		
		return add(mapping, dutyform, request, response);
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
					+ "'  ";
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
	 * 保存子过程
	 */
	public ActionForward saveSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		TawRmDutyEventSubForm tawRmDutyEventSubForm = (TawRmDutyEventSubForm) form;
		boolean isNew = (null == tawRmDutyEventSubForm.getId() || "".equals(tawRmDutyEventSubForm.getId()));
		
		TawRmDutyEventSub tawRmDutyEventSub = null;

		if (isNew) {// 新增加
			tawRmDutyEventSub = (TawRmDutyEventSub) convert(tawRmDutyEventSubForm);
			tawRmDutyEventSub.setDeptid(saveSessionBeanForm.getDeptid());
			tawRmDutyEventSub.setRoomid(saveSessionBeanForm.getRoomId());
			tawRmDutyEventSub.setRecordman(saveSessionBeanForm.getUserid());
			tawRmDutyEventSub.setRecordtime(StaticMethod.getCurrentDateTime());
			mgr.saveTawRmDutyEventSub(tawRmDutyEventSub);
		} else {
			tawRmDutyEventSub = mgr.getTawRmDutyEventSub(tawRmDutyEventSubForm.getId());
			tawRmDutyEventSub.setHappentime(tawRmDutyEventSubForm.getHappentime());
			tawRmDutyEventSub.setWorksheetid(tawRmDutyEventSubForm.getWorksheetid());
			tawRmDutyEventSub.setContent(tawRmDutyEventSubForm.getContent());
			mgr.saveTawRmDutyEventSub(tawRmDutyEventSub);
		}
		
		tawRmDutyEventSubForm.setEventid(tawRmDutyEventSub.getEventid());
		return mapping.findForward("edit");
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
					+ "'  ";
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
	 * 大值班
	 * add by fengshaohong
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
		String areaId="";
		List listlist = new ArrayList();
		List list = mgr.getSamelevelDeptbyDeptids("1", "1", "0");
		for(int i=0;i<list.size();i++){
			List eventList = new ArrayList();
			TawSystemDept tawSystemDept =(TawSystemDept)list.get(i);
			areaId = tawSystemDept.getDeptId();
			eventList =Eventmgr.getTawRmDutyEventByDeptAndFlag(areaId, "1", "5");
			listlist.add(eventList);
			if(!eventList.isEmpty())
			{
				int k = Integer.parseInt(((TawRmDutyEvent)eventList.get(0)).getFlag());
				if(k<2){
					tawSystemDept.setRemark("0");
				}else if(k==2){
					tawSystemDept.setRemark("1");
				}else{
					tawSystemDept.setRemark("2");
				}
			}else{
				tawSystemDept.setRemark("0");
			}
			
		}
		request.setAttribute("noteList", list);
		request.setAttribute("listlist", listlist);
		return mapping.findForward("noteList");
		
	}
	/*
	 * 大值班
	 * add by fengshaohong
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
	
	/**
	 * 未阅读事件列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward readList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		//获取本班次及其其它班次未完成的事件,事件要属于本机房新增或者是相关机房
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		String condition = " AND tawRmDutyEvent.flag>2 "
						 + " AND (tawRmDutyEvent.roomid=" + saveSessionBeanForm.getRoomId() 
						 + " OR tawRmDutyEvent.regionlist like ('%#" + CommonTools.getItemId(saveSessionBeanForm.getRoomId()) +"#%')) "
						 + " AND (tawRmDutyEvent.id not in "
						 + " (SELECT eventRead.eventid from EventRead eventRead where eventRead.userid ='" + saveSessionBeanForm.getUserid() + "')) ";
		
		int length = 1000;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map map = (Map) mgr.getEventByCondition(pageIndex,new Integer(length), condition);		
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("readList");
	}
	
	/**
	 * 批量阅读事件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward batchRead(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		
		String[] checkValue = (String[]) request.getParameterMap().get("my_check"); // 获取选中的重要时间

		//获取本班次及其其它班次未完成的事件,事件要属于本机房新增或者是相关机房
		EventReadMgr eventReadMgr = (EventReadMgr) getBean("eventReadMgr");
		for(int i=0;i<checkValue.length;i++) {
			if(eventReadMgr.getEventReads(checkValue[i],saveSessionBeanForm.getUserid()).size()==0) {
				// 增加已阅读
				EventRead eventRead = new EventRead();
				eventRead.setDeptid(saveSessionBeanForm.getDeptid());
				eventRead.setEventid(checkValue[i]);
				eventRead.setReadaffirm("0");
				eventRead.setReadtime(StaticMethod.getCurrentDateTime());
				eventRead.setUserid(saveSessionBeanForm.getUserid());
				eventReadMgr.saveEventRead(eventRead);
			}
		}
		
		return readList(mapping,form,request,response);
	}
	
	/**
	 * 进行大值班状态转换
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mainRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String isOnDuty = saveSessionBeanForm.getWorkSerial();
		if("".equals(isOnDuty)||isOnDuty==null||isOnDuty.equals("0")){
			return allEventList(mapping,form,request,response);
		}else{
			return	mainEvent(mapping,form,request,response);
		}
	}
	
	/**
	 * 获取子过程列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sublist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String eventid = request.getParameter("eventid").toString();
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		String condition = " where eventid= '" + eventid + "' ";
		
		int length = 100;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"TawRmDutyEventSubList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map map = (Map) mgr.getTawRmDutyEventSubs(pageIndex,new Integer(length), condition);		
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventSubList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));
		
		EventReadMgr eventReadMgr = (EventReadMgr) getBean("eventReadMgr");
		if(eventReadMgr.getEventReads(eventid,saveSessionBeanForm.getUserid()).size()==0) {
			// 增加已阅读
			EventRead eventRead = new EventRead();
			eventRead.setDeptid(saveSessionBeanForm.getDeptid());
			eventRead.setEventid(eventid);
			eventRead.setReadaffirm("0");
			eventRead.setReadtime(StaticMethod.getCurrentDateTime());
			eventRead.setUserid(saveSessionBeanForm.getUserid());
			eventReadMgr.saveEventRead(eventRead);
		}
		
		return mapping.findForward("sublist");
	}
	
	/**
	 * 故障事件和监控纪要填写
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mainEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");

		// 判断是否值班
		if (saveSessionBeanForm.getWorkSerial()==null||
				saveSessionBeanForm.getWorkSerial().equals("")
				||saveSessionBeanForm.getWorkSerial().equals("0")) {
			// DutySchedule dutySchedule =new DutySchedule();
			// dutySchedule.TawRmRecordEmail();
			//return mapping.findForward("notonduty");
			return allEventList(mapping,form,request,response);
		}
		return mapping.findForward("mainEvent");
	}
	
	/**
	 * 全省监控事件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward allEventList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		String roomid = "167"; // 省公司机房

		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		
		List roomList = CommonTools.getTawRmDutyEventList();
		List regionList = new ArrayList();
		String jksCurrentWork = Integer.toString(mgr.getDutyAssignwork(roomid,StaticMethod.getCurrentDateTime()).getId()); // 监控室当前班次
		for(int i=0;i<roomList.size();i++) {
			DictItemXML dictItemXML = (DictItemXML)roomList.get(i);
			TawRmDutyEventRegion tawRmDutyEventRegion = null;
			// 取到机房当前班次的排班数据
			String currentWork = Integer.toString(mgr.getDutyAssignwork(dictItemXML.getItemDescription().toString(),StaticMethod.getCurrentDateTime()).getId());
			tawRmDutyEventRegion = mgr.getEventRegionData(jksCurrentWork,currentWork,dictItemXML.getItemId().toString(),dictItemXML.getItemDescription().toString());
			tawRmDutyEventRegion.setId(dictItemXML.getItemId().toString());
			tawRmDutyEventRegion.setRoomId(dictItemXML.getItemDescription().toString());
			tawRmDutyEventRegion.setRegionName(dictItemXML.getItemName().toString());
			tawRmDutyEventRegion.setWorkserial(currentWork);
			regionList.add(tawRmDutyEventRegion);
		}
		
		request.setAttribute("roomid",roomid); // 省公司机房
		request.setAttribute("region","1"); // 省公司机房对应的数据字典
		request.setAttribute("REGIONLIST", regionList);
		return mapping.findForward("allEventList");
	}
	
	/**
	 * 全省监控事件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward eventList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String region = StaticMethod.nullObject2String(request.getParameter("region"));
		String roomid = StaticMethod.nullObject2String(request.getParameter("roomid"));
		String jksroomid = "167"; // 省公司监控室机房
		
		// 获取监控室和执行部门当前班次的排班数据
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		
		// 获取监控室的排班数据
		TawRmAssignwork jksAssignwork = new TawRmAssignwork();
		TawRmAssignwork lastJksAssignwork = new TawRmAssignwork();
		jksAssignwork = mgr.getDutyAssignwork(jksroomid,StaticMethod.getCurrentDateTime()); // 监控室当前班次
		if(jksAssignwork!=null&&jksAssignwork.getStarttimeDefined()!=null) {
			String jksDutyBeginTime = CommonTools.getTimeString(jksAssignwork.getStarttimeDefined(), -1);
			lastJksAssignwork = mgr.getDutyAssignwork(jksroomid,jksDutyBeginTime); // 监控室上班次
		}
		
		// 执行部门的排班数据
		TawRmAssignwork assignwork = new TawRmAssignwork();
		TawRmAssignwork lastAssignwork = new TawRmAssignwork();
		assignwork = mgr.getDutyAssignwork(roomid,StaticMethod.getCurrentDateTime()); // 执行部门当前班次
		if(assignwork!=null&&assignwork.getStarttimeDefined()!=null) {
			String dutyBeginTime = CommonTools.getTimeString(assignwork.getStarttimeDefined(), -1);
			lastAssignwork = mgr.getDutyAssignwork(roomid,dutyBeginTime); // 执行部门上班次
		}
		
		// 当前班次事件
		List currentEventList = new ArrayList();
		TawRmDutyEventRegion currentEventInfo = new TawRmDutyEventRegion();
		if(assignwork!=null&&assignwork.getStarttimeDefined()!=null&&!assignwork.getStarttimeDefined().equals("")) {
			currentEventList = mgr.getEventList(Integer.toString(jksAssignwork.getId()),Integer.toString(assignwork.getId()),region,jksroomid);
			currentEventInfo = mgr.parseEventList(currentEventList);
			currentEventInfo.setDutyBeginTime(assignwork.getStarttimeDefined());
			currentEventInfo.setDutyEndTime(assignwork.getEndtimeDefined());
			currentEventInfo = mgr.getEventNumByFaultType(currentEventList,currentEventInfo);
			currentEventInfo = mgr.getEventNumByFlag(currentEventList,currentEventInfo);
		}
		
		// 上班次事件
		List lastEventList = new ArrayList();
		TawRmDutyEventRegion lastEventInfo = new TawRmDutyEventRegion();
		TawRmRecord lastTawRmRecord = new TawRmRecord();
		if(lastAssignwork!=null&&lastAssignwork.getStarttimeDefined()!=null&&!lastAssignwork.getStarttimeDefined().equals("")) {
			lastEventList = mgr.getEventList(Integer.toString(lastJksAssignwork.getId()),Integer.toString(lastAssignwork.getId()),region,jksroomid);
			lastEventInfo = mgr.parseEventList(lastEventList);
			lastEventInfo.setDutyBeginTime(lastAssignwork.getStarttimeDefined());
			lastEventInfo.setDutyEndTime(lastAssignwork.getEndtimeDefined());
			lastEventInfo = mgr.getEventNumByFaultType(lastEventList,lastEventInfo);
			lastEventInfo = mgr.getEventNumByFlag(lastEventList,lastEventInfo);
			
			// 呈现前一班次的监控纪要
			TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(com.boco.eoms.db.util.ConnectionPool.getInstance());
			lastTawRmRecord = tawRmRecordDAO.retrieve(lastAssignwork.getId());
		}
		
		request.setAttribute("LASTTAWRMRECORD",lastTawRmRecord==null?new TawRmRecord():lastTawRmRecord);		
		request.setAttribute("CURRENTEVENTINFO",currentEventInfo);		
		request.setAttribute("LASTEVENTINFO",lastEventInfo);
		request.setAttribute("CURRENTEVENTTLIST",currentEventList);		
		request.setAttribute("LASTEVENTLIST",lastEventList);
		request.setAttribute("resultSize", new Integer(10000));
		return mapping.findForward("eventList");
	}
	
	/**
	 * @boco.title 
	 * @boco.desc 重要故障和灾害事件提取,采用ajax<br>
	 * ActionForward return null
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward faultExtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// 判断超时 
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
			// 计算当前班次
			ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
			List eventsList = null;
			String major_fault = ""; // 三星级以及以上的事件记录主题
			String disaster_events = ""; // 灾害事件
			
			//获取本班次及其其它班次未完成的事件,事件要属于本机房新增或者是相关机房
			String condition = " AND (tawRmDutyEvent.workserial=" + saveSessionBeanForm.getWorkSerial()
			+ " OR tawRmDutyEvent.complateFlag=2 "
			+ " AND (tawRmDutyEvent.roomid=" + saveSessionBeanForm.getRoomId() + ")"
			+ " OR tawRmDutyEvent.regionlist like ('%#" + CommonTools.getItemId(saveSessionBeanForm.getRoomId()) +"#%')) ";
			Map map = (Map) mgr.getEventByCondition(new Integer(0),new Integer(1000), condition);		
			eventsList = (List) map.get("result");
			
			// 将重要故障和灾害事件的主题组合
			for (int j = 0; j < eventsList.size(); j++) {
				TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent)eventsList.get(j);
				if (StaticMethod.nullObject2int(tawRmDutyEvent.getFlag()) >= 3
						&& !tawRmDutyEvent.getFaultType().equals("9")) { // 组合三星级以及以上的事件记录主题
					major_fault += tawRmDutyEvent.getEventtitle() + "\r\n";
				}
				if (tawRmDutyEvent.getFaultType().equals("9")) { // 灾害事件
					disaster_events += tawRmDutyEvent.getEventtitle();
				}
			}
			// Set content type of the response to text/xml
			response.setContentType("text/xml");
			response.setCharacterEncoding("gb2312");
			// Create the response text
			String responseText = major_fault + "</fault>" + disaster_events;
			// Write the response back to the browser
			PrintWriter out = null;
			out = response.getWriter();
			out.println(responseText);
			// Close the writer
			out.close();
		} catch (Exception e) {
            e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 故障事件查询界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmDutyEventForm tawrmDutyEventForm = (TawRmDutyEventForm)form;
				
		// 地区数据字典
    	IDictService dictService = (IDictService) this.getBean("DictService");
        List regionlist = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + tawrmDutyEventForm.REGIONSDICT);
		request.setAttribute("REGIONLIST", regionlist);
		
		// 故障类别数据字典
        List faulttypelist = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + tawrmDutyEventForm.FAULTTYPEDICT);
		request.setAttribute("FAULTTYPELIST", faulttypelist);
		
		// 星级数据字典
        List flaglist = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + tawrmDutyEventForm.FAULTFLAGDICT);
		request.setAttribute("FLAGLIST", flaglist);
		
		// 是否完成数据字典
        List complatelist = dictService.getDictItems("dict-duty" + Constants.DICT_ID_SPLIT_CHAR + tawrmDutyEventForm.COMPLATEDICT);
		request.setAttribute("COMPLATELIST", complatelist);
		
		// 得表机房列表
		ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
		List cptroomlist = mgr.getTawSystemCptroomList();
		request.setAttribute("CPTROOMLIST", cptroomlist);
		
		return mapping.findForward("query");
	}
	
	/**
	 * 事件查询列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryDone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmDutyEventForm tawrmDutyEventForm = (TawRmDutyEventForm)form;
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		//获取本班次及其其它班次未完成的事件,事件要属于本机房新增或者是相关机房
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		String condition = mgr.getSearchCondition(tawrmDutyEventForm);
		
		int length = 15;
		String pageIndexName = new org.displaytag.util.ParamEncoder("TawRmDutyEventList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map map = (Map) mgr.getQueryEventList(pageIndex,new Integer(length), condition);		
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		request.setAttribute("TawRmDutyEventList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", new Integer(length));

		return mapping.findForward("readList");
	}
	
	/**
	 * 事件查询列表,门户接口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAtomLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final Integer pageIndex = new Integer(request.getParameter("pageIndex"));
		final Integer pageSize = new Integer(request.getParameter("pageSize"));
		
		TawRmDutyEventForm tawrmDutyEventForm = new TawRmDutyEventForm();
		try { // 星级参数，可以不传
			tawrmDutyEventForm.setFlag(StaticMethod.nullObject2String(request.getParameter("flag")));
		} catch(Exception e) {}
		try { // 最近几天参数，可以不传
			tawrmDutyEventForm.setDays(StaticMethod.nullObject2int(request.getParameter("days")));
		} catch(Exception e) {}
		try { // 发生时间，起始，可以不传
			tawrmDutyEventForm.setFromBeginTime(StaticMethod.nullObject2String(request.getParameter("fromBeginTime")));
		} catch(Exception e) {}
		try { // 发生时间，终止，可以不传
			tawrmDutyEventForm.setToBeginTime(StaticMethod.nullObject2String(request.getParameter("toBeginTime")));
		} catch(Exception e) {}
		
		if(tawrmDutyEventForm.getFlag()==null&&tawrmDutyEventForm.getFlag().equals(""))
		tawrmDutyEventForm.setFlag("2,3,4"); // 门户只要二星级事件
		
		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
		String condition = mgr.getSearchCondition(tawrmDutyEventForm);
		Map map = (Map) mgr.getQueryEventList(pageIndex,pageSize, condition);
//		String contentPath = request.getScheme() + "://"
//		+ request.getLocalAddr() + ":" + request.getLocalPort()
//		+ request.getContextPath();
		String contentPath = UtilMgrLocator.getEOMSAttributes().getEomsUrl();
		
		List list = (List) map.get("result"); // 取所有这个主记录的子记录 为了在页面显示
		try {
			// 创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			TawRmDutyEvent tawRmDutyEvent = null;
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				tawRmDutyEvent = (TawRmDutyEvent) list.get(i);
				Entry entry = feed.insertEntry();
				entry.setTitle(tawRmDutyEvent.getEventtitle());	
				entry.setContent(contentPath + "/duty/yndutyevent.do?method=edit&id="+tawRmDutyEvent.getId());
				entry.setSummary(tawRmDutyEvent.getFlag());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");   
				entry.setPublished(sdf.parse(tawRmDutyEvent.getBeginTime()));
			}
			// 每页显示条数
			feed.setText(map.get("total").toString());
			feed.setTitle(contentPath + "/duty/yndutyevent.do?method=queryDone&pageIndex=1&pageSize=25&flag=2&days=31&userName=admin");
	
			OutputStream os = response.getOutputStream();
			PrintStream ps = new PrintStream(os);
			feed.getDocument().writeTo(ps);
			
			// 下面为换行显示
			//Abdera abdera = new Abdera();
			//Writer writer = abdera.getWriterFactory().getWriter("prettyxml");
			//writer.writeTo(feed, ps);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
