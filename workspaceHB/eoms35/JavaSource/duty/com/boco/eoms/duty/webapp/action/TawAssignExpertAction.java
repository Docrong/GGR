
package com.boco.eoms.duty.webapp.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.dao.TawExpertRoomDAO;
import com.boco.eoms.duty.dao.TawRmAssignExpertDAO;
import com.boco.eoms.duty.model.TawExpertInfo;
import com.boco.eoms.duty.model.TawExpertSub;
import com.boco.eoms.duty.model.TawNetTransfer;
import com.boco.eoms.duty.model.TawNetTransferSub;
import com.boco.eoms.duty.service.ITawExpertInfoManager;
import com.boco.eoms.duty.service.ITawExpertSubManager;
import com.boco.eoms.duty.service.ITawNetTransferManager;
import com.boco.eoms.duty.service.ITawNetTransferSubManager;
import com.boco.eoms.duty.webapp.form.TawExpertInfoForm;
import com.boco.eoms.duty.webapp.form.TawExpertSubForm;
import com.boco.eoms.duty.webapp.form.TawNetTransferSubForm;
import com.boco.eoms.duty.webapp.form.TawRmAssignExpertForm;


public final class TawAssignExpertAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
	.getInstance();
	public ActionForward toform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = (TawExpertInfoForm) form;
		request.setAttribute("tawExpertInfoForm", tawExpertInfoForm);
		return mapping.findForward("edit");
	}
	
	public ActionForward toquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmAssignExpertForm tawRmAssignExpertForm = (TawRmAssignExpertForm) form;
		request.setAttribute("tawRmAssignExpertForm", tawRmAssignExpertForm);
		return mapping.findForward("query");
	}
	
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = (TawExpertInfoForm) form;
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String expertId = tawExpertInfoForm.getExpertId();
		String condition = " and expertId = '%"+expertId+"%'";
		List list = new ArrayList();
		list = mgr.getTawExpertInfosByCondition(condition);
		if(!list.isEmpty()){
			request.setAttribute("failReason", "专家id已经存在!");
			return mapping.findForward("fail");
		}
		Date addTime = new Date();
		TawSystemSessionForm sessionForm = this.getUser(request);
		String addMan = sessionForm.getUserid();
		TawExpertInfo tawExpertInfo = (TawExpertInfo) convert(tawExpertInfoForm);
		tawExpertInfo.setAddTime(addTime);
		tawExpertInfo.setCreater(addMan);
		mgr.saveTawExpertInfo(tawExpertInfo);
		return mapping.findForward("success");
	}
	
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawRmAssignExpertForm tawRmAssignExpertForm = (TawRmAssignExpertForm) form;
		TawRmAssignExpertDAO dao = new TawRmAssignExpertDAO(ds);
		String room = tawRmAssignExpertForm.getRoom();
		String expert = tawRmAssignExpertForm.getExpert();
		String start = tawRmAssignExpertForm.getStartTime();
		String end = tawRmAssignExpertForm.getEndTime();
	//	ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
	//	String condition = " and expertId = ";
		List list = dao.getExpertbyCondition(room, expert, start, end);
	//	for(int i=0;i<list.size();i++){
		  TawExpertRoomDAO tawRmAssignExpertDAO = new TawExpertRoomDAO(ds);
		  Vector expertList =tawRmAssignExpertDAO.getRoomExpertWithNull(Integer.parseInt(room));
		  request.setAttribute("expertList", expertList);
		request.setAttribute("tawRmAssignExpertFormlist", list);
		return mapping.findForward("query");
	}        
	
	public ActionForward toinfoquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = (TawExpertInfoForm) form;
		request.setAttribute("tawExpertInfoForm", tawExpertInfoForm);
		return mapping.findForward("query");
	}
	
	public ActionForward xinfoquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = (TawExpertInfoForm) form;
		String name = tawExpertInfoForm.getExpertName();
		String address = tawExpertInfoForm.getAdress();
		String goodat = tawExpertInfoForm.getBeGoodAt();
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String condition = "";
		if(!name.equals("")){
			condition += " and expertName like '%"+name+"%'";
		}if(!address.equals("")){
			condition += " and adress like '"+address+"%'";
		}if(!goodat.equals("")){
			condition += " and beGoodAt like '"+goodat+"%'";
		}
		List list = new ArrayList();
		list = mgr.getTawExpertInfosByCondition(condition);
		
		  request.setAttribute("tawExpertInfoFormList", list);
		
		return mapping.findForward("query");
	}                   
	public ActionForward toSelectroom(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}

		return mapping.findForward("selectRoom");
	}
	public ActionForward selectTime(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawRmAssignExpertForm tawRmAssignExpertForm = (TawRmAssignExpertForm) actionForm;
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
        TawExpertRoomDAO tawRmAssignExpertDAO = new TawExpertRoomDAO(ds);
		request.setAttribute("roomId", request.getParameter("roomId"));
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		tawRmAssignExpertForm.setRoom(roomId+"");
		try {
			Vector expertList =tawRmAssignExpertDAO.getRoomExpertWithNull(roomId);
			request.setAttribute("expertList", expertList);
			request.setAttribute("tawRmAssignExpertForm",tawRmAssignExpertForm);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return mapping.findForward("query");
		}
	public ActionForward xspread(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = new TawExpertInfoForm();
		String expertId = request.getParameter("notes");
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String sql = " and expertId ='"+expertId+"'";
		List infoList = mgr.getTawExpertInfosByCondition(sql);
		
		TawExpertInfo tawExpertInfo = (TawExpertInfo)infoList.get(0);
		String mainId = tawExpertInfo.getId();
		ITawExpertSubManager mgrSub = (ITawExpertSubManager)getBean("ITawExpertSubManager");
		List list = mgrSub.getTawExpertSubByMainId(mainId);
		List subList = new ArrayList();
		for(int i=0;i<list.size();i++){
			TawExpertSubForm temp = new TawExpertSubForm();
			temp = (TawExpertSubForm)convert((TawExpertSub)list.get(i));
			subList.add(temp);
		}
		tawExpertInfoForm = (TawExpertInfoForm) convert(tawExpertInfo);
		request.setAttribute("tawExpertInfoForm", tawExpertInfoForm);
		request.setAttribute("subList", subList);
		request.setAttribute("mainId", mainId);
		return mapping.findForward("subEdit");
		
	}
	public ActionForward xsaveSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertSubForm tawExpertSubForm = (TawExpertSubForm) form;
		Date date = new Date();
		TawSystemSessionForm sessionForm = this.getUser(request);
		String addMan = sessionForm.getUserid();
		tawExpertSubForm.setAddMan(addMan);
		TawExpertSub tawExpertSub = (TawExpertSub) convert(tawExpertSubForm);
		tawExpertSub.setAddTime(date);
		String mainId = (String) request.getParameter("mainId");
		tawExpertSub.setMainId(mainId);
		ITawExpertSubManager mgr = (ITawExpertSubManager)getBean("ITawExpertSubManager");
		mgr.saveTawExpertSub(tawExpertSub);
		
		return mapping.findForward("success");
	}
	
	
	
	
	public ActionForward toExpertInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = new TawExpertInfoForm();
		String expertId = request.getParameter("expertId");
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String sql = " and expertId ='"+expertId+"'";
		List list = new ArrayList();
		list = mgr.getTawExpertInfosByCondition(sql);
		
			tawExpertInfoForm = (TawExpertInfoForm) convert((TawExpertInfo)list.get(0));
		request.setAttribute("tawExpertInfoForm", tawExpertInfoForm);
		return mapping.findForward("edit");
	}
	
	public ActionForward searchfromzhiban(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = new TawExpertInfoForm();
		String expertId = request.getParameter("notes");
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String sql = " and expertId ='"+expertId+"'";
		List list = new ArrayList();
		list = mgr.getTawExpertInfosByCondition(sql);
		
			tawExpertInfoForm = (TawExpertInfoForm) convert((TawExpertInfo)list.get(0));
		request.setAttribute("tawExpertInfoForm", tawExpertInfoForm);
		return mapping.findForward("search");
	}
	public ActionForward searchfrominfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = new TawExpertInfoForm();
		String expertId = request.getParameter("expertId");
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String sql = " and expertId ='"+expertId+"'";
		List list = new ArrayList();
		list = mgr.getTawExpertInfosByCondition(sql);
		
			tawExpertInfoForm = (TawExpertInfoForm) convert((TawExpertInfo)list.get(0));
		request.setAttribute("tawExpertInfoForm", tawExpertInfoForm);
		return mapping.findForward("search");
	}
	public ActionForward oneDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawExpertInfoForm tawExpertInfoForm = new TawExpertInfoForm();
		String expertId = request.getParameter("expertId");
		ITawExpertInfoManager mgr = (ITawExpertInfoManager)getBean("ITawExpertInfoManager");
		String sql = " and expertId ='"+expertId+"'";
		List list = new ArrayList();
		list = mgr.getTawExpertInfosByCondition(sql);
		String id = ((TawExpertInfo)list.get(0)).getId();
		mgr.removeTawExpertInfo(id);
		return mapping.findForward("success");
	}
	
	
	
}
