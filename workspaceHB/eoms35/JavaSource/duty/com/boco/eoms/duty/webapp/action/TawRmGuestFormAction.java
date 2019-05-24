package com.boco.eoms.duty.webapp.action;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.mgr.TawRmGuestFormMgr;
import com.boco.eoms.duty.model.TawRmGuestform;
import com.boco.eoms.duty.webapp.form.TawRmGuestFormForm;
import com.boco.eoms.duty.util.TawRmGuestFormConstants;
/**
 * <p>
 * Title:进出机房管理
 * </p>
 * <p>
 * Description:进出机房管理
 * </p>
 * <p>
 * 2009-04-25
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 * 
 */
public final class TawRmGuestFormAction extends BaseAction {

	/**
	 * 
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return add(mapping, form, request, response);
	}
	
	/**
	 * 出入机房登记
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
		TawRmGuestFormForm TawRmGuestform = (TawRmGuestFormForm) form;
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		String condition = " AND obj.flag=0 AND obj.roomId=" + saveSessionBeanForm.getRoomId();
		List list = mgr.list(condition);
		TawRmGuestform.setInputdate(StaticMethod.getCurrentDateTime());
		TawRmGuestform.setStrutsAction(1);
		TawRmGuestform.setCruser(saveSessionBeanForm.getUserid());
		TawRmGuestform.setDeptId(saveSessionBeanForm.getDeptid());
		TawRmGuestform.setDeptName(saveSessionBeanForm.getDeptname());
		request.setAttribute("UNCHECKGUESTFORMLIST", list);
		return mapping.findForward("add");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	  	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
		TawRmGuestFormForm tawRmGuestForm = (TawRmGuestFormForm) form;
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		TawRmGuestform tawRmGuestform = new TawRmGuestform();
		tawRmGuestform.setAffection(tawRmGuestForm.getAffection());
		tawRmGuestform.setCompany(tawRmGuestForm.getCompany());
		tawRmGuestform.setDutyman(tawRmGuestForm.getDutyman());
		tawRmGuestform.setEndtime(tawRmGuestForm.getEndtime());
		tawRmGuestform.setStarttime(tawRmGuestForm.getStarttime());
		tawRmGuestform.setInputdate(tawRmGuestForm.getInputdate());
		tawRmGuestform.setSender(tawRmGuestForm.getSender());
		tawRmGuestform.setGuestname(tawRmGuestForm.getGuestname());
		tawRmGuestform.setRoomId(Integer.parseInt(saveSessionBeanForm.getRoomId()));
		tawRmGuestform.setNotes(tawRmGuestForm.getNotes());
		tawRmGuestform.setConcerned(tawRmGuestForm.getConcerned());
		tawRmGuestform.setDepartment(tawRmGuestForm.getDepartment());
		tawRmGuestform.setPurpose(tawRmGuestForm.getPurpose());
		tawRmGuestform.setCruser(tawRmGuestForm.getCruser());
		tawRmGuestform.setDeptId(tawRmGuestForm.getDeptId());
		tawRmGuestform.setDeptName(tawRmGuestForm.getDeptName());
		if("ADD".equals(request.getParameter("action"))){
			tawRmGuestform.setFlag(0);
		}
		if("UPDATE".equals(request.getParameter("action"))){
			tawRmGuestform.setId(tawRmGuestForm.getId());
			tawRmGuestform.setFlag(1);
		}
		mgr.save(tawRmGuestform);
		return mapping.findForward("save");
	}

	/**
	 * 机房出入确认
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		String id =StaticMethod.null2String(request.getParameter("id"));
		request.setAttribute("tawRmGuestForm",mgr.getTawRmGuestform(id));
		return mapping.findForward("confirm");
	}	
	
	/**
	 * 机房出入查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		String id =StaticMethod.null2String(request.getParameter("id"));
		request.setAttribute("tawRmGuestForm",mgr.getTawRmGuestform(id));
		return mapping.findForward("view");
	}
	
	/**
	 * 机房出入查询初始化
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
		TawRmGuestFormForm TawRmGuestform = (TawRmGuestFormForm) form;
		TawRmGuestform.setFlag(-1);
		// 得表机房列表
		ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) getBean("ItawSystemCptroomManager");
		List cptroomlist = mgr.getTawSystemCptroomList();
		request.setAttribute("CPTROOMLIST", cptroomlist);
		return mapping.findForward("search");
	}
	
	/**
	 * 机房出入查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawRmGuestFormConstants.TAWRMGUESTFORM_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes().getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		String whereStr =mgr.getSearchCondition((TawRmGuestFormForm)form);
		Map map =mgr.getTawRmGuestForm(pageIndex, pageSize, whereStr);
		List result=(List)map.get("result");
		request.setAttribute("resultSize", map.get("total"));
		System.out.println("resultSize="+ map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute(TawRmGuestFormConstants.TAWRMGUESTFORM_LIST,result);
		return mapping.findForward("doSearch");
	}
	
	/**
	 * 机房出入删除初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		List list = mgr.getUnChecklist(0);
		request.setAttribute("UNCheckList", list);
		return mapping.findForward("delete");
	}
	
	/**
	 * 机房出入删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doDelete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TawRmGuestFormMgr mgr = (TawRmGuestFormMgr) getBean("tawRmGuestFormMgr");
		String id =request.getParameter("id");
		if(null != id && !"".equals(id)){
			mgr.deleteById(id);
		}
		return mapping.findForward("doDelete");
	}
}
