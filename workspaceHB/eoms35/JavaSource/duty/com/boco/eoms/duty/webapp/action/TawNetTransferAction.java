
package com.boco.eoms.duty.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.model.TawNetTransfer;
import com.boco.eoms.duty.model.TawNetTransferSub;
import com.boco.eoms.duty.service.ITawNetTransferManager;
import com.boco.eoms.duty.service.ITawNetTransferSubManager;
import com.boco.eoms.duty.webapp.form.TawNetTransferForm;
import com.boco.eoms.duty.webapp.form.TawNetTransferSubForm;


public final class TawNetTransferAction extends BaseAction {
	
	public ActionForward toform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		request.setAttribute("tawNetTransferForm", tawNetTransferForm);
		return mapping.findForward("edit");
	}
	
	public ActionForward toquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		request.setAttribute("tawNetTransferForm", tawNetTransferForm);
		return mapping.findForward("query");
	}
	
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		Date date = new Date();
		TawSystemSessionForm sessionForm = this.getUser(request);
		String originater = sessionForm.getUserid();
		String dept = sessionForm.getDeptid();
		tawNetTransferForm.setOriginater(originater);
		tawNetTransferForm.setOriginateDept(dept);
		TawNetTransfer tawNetTransfer = (TawNetTransfer) convert(tawNetTransferForm);
		tawNetTransfer.setDate(date);
		tawNetTransfer.setHasub("0");
		ITawNetTransferManager mgr = (ITawNetTransferManager)getBean("ITawNetTransferManager");
		mgr.saveTawNetTransfer(tawNetTransfer);
		return mapping.findForward("success");
	}
	
	public ActionForward xquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		ITawNetTransferManager mgr = (ITawNetTransferManager)getBean("ITawNetTransferManager");
		StringBuffer sql = new StringBuffer();
		String speciality = tawNetTransferForm.getSpeciality();
		String equipmentDept = tawNetTransferForm.getEquipmentDept();
		String state = tawNetTransferForm.getState();
		String start = tawNetTransferForm.getStartTime();
		String end = tawNetTransferForm.getEndTime();
		if(!"".equals(speciality)&&speciality!=null)
		{
			sql.append(" and speciality ='"+speciality+"'");
		}
		if(!"".equals(equipmentDept)&&equipmentDept!=null)
		{
			sql.append(" and equipmentDept ='"+equipmentDept+"'");
		}if(!"".equals(state)&&state!=null)
		{
			sql.append(" and state ='"+state+"'");
		}if(!"".equals(start)&&start!=null)
		{
			sql.append(" and to_char(date,'YYYY-MM-DD HH24:MI:SS') >='"+start+"'");
		}if(!"".equals(end)&&end!=null)
		{
			sql.append(" and to_char(date,'YYYY-MM-DD HH24:MI:SS') <='"+end+"'");
		}
		sql.append(" order by date desc");
		List list = mgr.getTawNetTransferByCondition(sql.toString());
		request.setAttribute("tawNetTransferlist", list);
		return mapping.findForward("query");
	}

	public ActionForward xspread(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		String id = tawNetTransferForm.getId();
		ITawNetTransferSubManager mgrSub = (ITawNetTransferSubManager)getBean("ITawNetTransferSubManager");
		List list = mgrSub.getTawNetTransferSubByMainId(id);
		List subList = new ArrayList();
		for(int i=0;i<list.size();i++){
			TawNetTransferSubForm temp = new TawNetTransferSubForm();
			temp = (TawNetTransferSubForm)convert((TawNetTransferSub)list.get(i));
			subList.add(temp);
		}
		
		ITawNetTransferManager mgr = (ITawNetTransferManager)getBean("ITawNetTransferManager");
		TawNetTransfer tawNetTransfer = mgr.getTawNetTransfer(id);
		String state = tawNetTransfer.getState();
		String title = tawNetTransfer.getTitle();
		tawNetTransferForm = (TawNetTransferForm) convert(tawNetTransfer);
		request.setAttribute("tawNetTransferForm", tawNetTransferForm);
		request.setAttribute("subList", subList);
		request.setAttribute("id", id);
		request.setAttribute("mainTitle", title);
		request.setAttribute("state", state);
		return mapping.findForward("subEdit");
	}
	public ActionForward xsaveSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferSubForm tawNetTransferSubForm = (TawNetTransferSubForm) form;
		Date date = new Date();
		TawSystemSessionForm sessionForm = this.getUser(request);
		String addMan = sessionForm.getUserid();
		tawNetTransferSubForm.setAddMan(addMan);
		TawNetTransferSub tawNetTransferSub = (TawNetTransferSub) convert(tawNetTransferSubForm);
		tawNetTransferSub.setAddTime(date);
		String state = tawNetTransferSubForm.getState();
		String mainId = tawNetTransferSubForm.getMainId();
		ITawNetTransferManager mgrMain = (ITawNetTransferManager)getBean("ITawNetTransferManager");
		TawNetTransfer tawNetTransfer = mgrMain.getTawNetTransfer(mainId);
		String hasub = tawNetTransfer.getHasub();
		String mainState = tawNetTransfer.getState();
		if(!mainState.equals("1")){
			return mapping.findForward("fail");
		}
		int flag = 0;
		if(hasub.equals("0")){
			tawNetTransfer.setHasub("1");
			flag=1;
		}
		if(state.equals("1")){
			tawNetTransfer.setState("2");
			flag=1;
		}
		if(flag==1){
		mgrMain.saveTawNetTransfer(tawNetTransfer);
		}
		ITawNetTransferSubManager mgr = (ITawNetTransferSubManager)getBean("ITawNetTransferSubManager");
		mgr.saveTawNetTransferSub(tawNetTransferSub);
		
		return mapping.findForward("success");
	}
	
	public ActionForward xdeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		String id = tawNetTransferForm.getId();
		ITawNetTransferManager mgr = (ITawNetTransferManager)getBean("ITawNetTransferManager");
		TawNetTransfer tawNetTransfer = mgr.getTawNetTransfer(id);
		tawNetTransferForm = (TawNetTransferForm) convert(tawNetTransfer);
		request.setAttribute("tawNetTransferForm", tawNetTransferForm);
		return mapping.findForward("change");
	}
	
	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawNetTransferForm tawNetTransferForm = (TawNetTransferForm) form;
		String id = tawNetTransferForm.getId();
		ITawNetTransferManager mgr = (ITawNetTransferManager)getBean("ITawNetTransferManager");
		mgr.removeTawNetTransfer(id);
		request.setAttribute("congratuation", "删除成功！");
		return mapping.findForward("success");
	}
}
