package com.boco.eoms.sheet.supervisetask.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
//import com.boco.eoms.sheet.listedregulation.model.ListedRegulationTask;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRecord;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRule;

public interface SuperviseTaskManager {

	
	public Map supervisetaskRuleList( Integer curPage,  Integer pageSize, Map maptj);
	public Map supervisetaskRuleList2( Integer curPage,  Integer pageSize, Map maptj);
	public Map supervisetaskRecordList( Integer curPage,  Integer pageSize, Map maptj);
	
	public void savesupervisetaskRule(SuperviseTaskRule t);
	public void savesupervisetaskRecord(SuperviseTaskRecord t);
	
	public SuperviseTaskRecord getSuperviseTaskRecordById(String id);
	public SuperviseTaskRule getSuperviseTaskRuleById(String id);
	
	public SuperviseTaskRecord getSuperviseTaskRecordBySheetId(String id);
	
	public ListedRegulationMain getListedRegulationMainById( String id);
	
	public String exportRecordExcel( HttpServletRequest request,
			HttpServletResponse response,List result)throws Exception;
	
	public Map  getUsers(List list);
	public Map getUsersNamePhone(String[]usersarr) throws Exception;
//	public void sendSMSIVR(String[]usersarr,String[]usersPhonearr,ListedRegulationMain main,SuperviseTaskRule rule,Date dealdate,String content2) throws Exception;
	
	public void supervisetaskRecordAddSaveAccept(Map map)throws Exception;
	public void supervisetaskRecordAddSaveDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	public void supervisetaskRecordDoneAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void supervisetaskRecordDone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	public CommonTaskMain getCommonTaskById(String id);
	
	public void newBulletin1(String str);
	public String querySheetStatus(String sheetid);
}