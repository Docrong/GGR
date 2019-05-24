package com.boco.eoms.duty.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.mgr.TawRmThingMgr;
import com.boco.eoms.duty.model.TawRmThing;
import com.boco.eoms.duty.model.TawRmThingNote;
import com.boco.eoms.duty.webapp.form.TawRmThingForm;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * 2009-4-29
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 * 
 */
public class TawRmThingAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return add(mapping, form, request, response);
	}

	/**
	 * forward
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("add");
	}

	/**
	 * save tawRmThing
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String action = request.getParameter("action");
		TawRmThingForm tawRmThingForm = (TawRmThingForm) form;
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");
		TawRmThing tawRmThing = new TawRmThing();
		if ("update".equals(action)) {
			tawRmThing.setId(tawRmThingForm.getId());
			System.out.println("id=" + tawRmThingForm.getId());
		}
		tawRmThing.setIsForUse(tawRmThingForm.getIsForUse());
		tawRmThing.setEstate(tawRmThingForm.getEstate());
		tawRmThing.setRoomId(saveSessionBeanForm.getRoomId());
		tawRmThing.setThingName(tawRmThingForm.getThingName());
		tawRmThing.setThingComment(tawRmThingForm.getThingComment());
		tawRmThing.setInputUserId(saveSessionBeanForm.getUserid());
		tawRmThing.setInputTime(StaticMethod
				.getCurrentDateTime("yyyy-MM-dd HH:ss:mm"));
		tawRmThingMgr.save(tawRmThing);
		return mapping.findForward("mainList");
	}

	/*
	 * list thing
	 */
	public ActionForward listThing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");

		String room_id = saveSessionBeanForm.getRoomId();
		request.setAttribute("Thinglist", tawRmThingMgr.getThingList(room_id));
		return mapping.findForward("listThing");
	}

	/*
	 * list thing
	 */
	public ActionForward listThingNote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String thingId = StaticMethod.null2String(request
				.getParameter("thingId"));
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");
		request.setAttribute("ThingNoteList", tawRmThingMgr
				.getThingNoteList(thingId));
		request.setAttribute("thingId", thingId);
		return mapping.findForward("listThingNote");
	}

	public ActionForward addThingAndNote(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");

		String thingId = StaticMethod.null2String(request
				.getParameter("thingId"));
		TawRmThing tawRmThing = tawRmThingMgr.getTawRmThingById(thingId);
		request.setAttribute("TawRmThing", tawRmThing);
		request.setAttribute("TawRmThingNoteList", tawRmThingMgr
				.getThingNoteList(thingId));
		return mapping.findForward("addThingAndNote");
	}

	public ActionForward deleteThing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");

		String thingId = StaticMethod.null2String(request
				.getParameter("thingId"));
		tawRmThingMgr.deleteTawRmThingById(thingId);
		return mapping.findForward("mainList");
	}

	public ActionForward deleteThingNote(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");

		String id =  StaticMethod.null2String(request.getParameter("thingNoteId"));
		tawRmThingMgr.deleteTawTmThingNoteById(id);
		return mapping.findForward("mainList");
	}

	public ActionForward addThingNote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");
		String thingId =StaticMethod.null2String(request.getParameter("thingId"));
		String thingNoteId =request.getParameter("thingNoteId");
		TawRmThingNote tawRmThingNote = new TawRmThingNote();
		if(null ==thingNoteId || "".equals(thingNoteId) ){//add
			tawRmThingNote.setEstate(1);
		}else{//update
			tawRmThingNote.setId(StaticMethod.null2String(thingNoteId));
			tawRmThingNote.setEstate(StaticMethod.getIntValue(request.getParameter("estate")));
		}
		tawRmThingNote.setThingId(thingId);
		tawRmThingNote.setBeginTime(request.getParameter("beginTime"));
		tawRmThingNote.setEndTime(request.getParameter("endTime"));
		tawRmThingNote.setToUser(request.getParameter("toUser"));
		
		tawRmThingNote.setNoteComment(request.getParameter("noteComment"));
		tawRmThingNote.setInputUserId(saveSessionBeanForm.getUserid());
		tawRmThingNote.setInputUserName(saveSessionBeanForm.getUsername());
		tawRmThingNote.setInputTime(StaticMethod.getCurrentDateTime());
		tawRmThingMgr.save(tawRmThingNote);
		return mapping.findForward("mainList");
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");
		List list =tawRmThingMgr.getThingList(saveSessionBeanForm.getRoomId());
		request.setAttribute("TawRmThingList",list);
		return  mapping.findForward("goQuery");
	}
	
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String thingId=request.getParameter("id");
		String beginTime=request.getParameter("beginTime");
		String endTime=request.getParameter("endTime");
		String pageIndexName = new org.displaytag.util.ParamEncoder("tawRmThingNoteList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawRmThingMgr tawRmThingMgr = (TawRmThingMgr) getBean("tawRmThingMgr");
		final String whereStr =tawRmThingMgr.builderSql(thingId, beginTime, endTime);
		Map map =tawRmThingMgr.getQueryList(pageIndex, pageSize, whereStr);
		List result=(List)map.get("result");
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("tawRmThingNoteList",result);
		return mapping.findForward("doQuery");
	}
}
