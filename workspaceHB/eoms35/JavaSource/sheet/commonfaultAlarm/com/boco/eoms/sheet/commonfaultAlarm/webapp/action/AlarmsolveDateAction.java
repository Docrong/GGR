package com.boco.eoms.sheet.commonfaultAlarm.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfaultAlarm.model.AlarmsolvedateConfig;
import com.boco.eoms.sheet.commonfaultAlarm.service.IAlarmsolveDateManager;

public class AlarmsolveDateAction extends BaseAction {
	/***
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getAlarmsolveDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		int status = 2;
		ICommonFaultMainManager mainmgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
		IAlarmsolveDateManager alarrMgr = (IAlarmsolveDateManager) getBean("iAlarmsolveDateManagerImpl");
		String sheetKeys  = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		System.out.println("yangxd  sheetkeys="+sheetKeys) ;
		String[] sheetKey = sheetKeys.split(",");
		for (int i = 0; i < sheetKey.length; i++) {
			CommonFaultMain main = (CommonFaultMain) mainmgr.getSingleMainPO(sheetKey[i]);
			String alarmId = main.getMainAlarmNum();
			System.out.println("yangxd  "+i+"= alarmId ="+alarmId ) ;
			Date alarmDate = alarrMgr.alarmsolveDate(alarmId);//获取告警平台清除时间
			if(alarmDate!=null){
				status=0;
				//更新EOMS告警清除时间
				main.setMainAlarmSolveDate(alarmDate);
				mainmgr.saveOrUpdateMain(main);
			}else{
				status=1;
			}
			o.put("date", StaticMethod.date2String(alarmDate));
		}
		data.put(o);
		jsonRoot.put("status", status);
		jsonRoot.put("data", data);
		JSONUtil.print(response, jsonRoot.toString());
	}
	
	public void updateEomsAlarmsolveDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		int status = 2;
		
		ICommonFaultMainManager mainmgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
		IAlarmsolveDateManager alarrMgr = (IAlarmsolveDateManager) getBean("iAlarmsolveDateManagerImpl");
		
		String linkFaultAvoidTime  = StaticMethod.nullObject2String(request.getParameter("linkFaultAvoidTime"));
		String sheetKeys  = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String[] sheetKey = sheetKeys.split(",");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String owneruser =sessionform.getUserid();
		String ownerdept = sessionform.getDeptid();
		for (int i = 0; i < sheetKey.length; i++) {
			CommonFaultMain main = (CommonFaultMain) mainmgr.getSingleMainPO(sheetKey[i]);
			String alarmId = main.getMainAlarmNum();
			AlarmsolvedateConfig acf = new AlarmsolvedateConfig();
			acf.setId(UUIDHexGenerator.getInstance().getID());
			acf.setAlarmnum(alarmId);
			acf.setSheetkey(sheetKey[i]);
			acf.setAcceptlimit(new Date());
			acf.setOwneruser(owneruser);
			acf.setOwnerdept(ownerdept);
			acf.setMainalarmsolvedate(StaticMethod.getTimestamp(linkFaultAvoidTime));
			acf.setStatus("0");
			acf.setIssended("0");
			main.setMainAlarmSolveDate(StaticMethod.getTimestamp(linkFaultAvoidTime));
			alarrMgr.updateEomsAlarmsolveDate(acf,main);
//			mainmgr.saveOrUpdateMain(main);
			status=0;
		}
		if(status==0){
			o.put("text", "同步告警时间成功！");
		}else{
			o.put("text", "同步告警时间失败！");
		}
		data.put(o);
		jsonRoot.put("status", status);
		jsonRoot.put("data", data);
		JSONUtil.print(response, jsonRoot.toString());
	}
	
	
	public void updateAlarmsolveDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonRoot = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		int status = 2;
		
		ICommonFaultMainManager mainmgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
		IAlarmsolveDateManager alarrMgr = (IAlarmsolveDateManager) getBean("iAlarmsolveDateManagerImpl");
		
		String linkFaultAvoidTime  = StaticMethod.nullObject2String(request.getParameter("linkFaultAvoidTime"));
		String sheetKeys  = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String owneruser =sessionform.getUserid();
		String ownerdept = sessionform.getDeptid();
		String[] sheetKey = sheetKeys.split(",");
		for (int i = 0; i < sheetKey.length; i++) {
			CommonFaultMain main = (CommonFaultMain) mainmgr.getSingleMainPO(sheetKey[i]);
			String alarmId = main.getMainAlarmNum();
			main.setMainAlarmSolveDate(StaticMethod.getTimestamp(linkFaultAvoidTime));
			AlarmsolvedateConfig acf = new AlarmsolvedateConfig();
			acf.setId(UUIDHexGenerator.getInstance().getID());
			acf.setAlarmnum(alarmId);
			acf.setSheetkey(sheetKey[i]);
			acf.setAcceptlimit(new Date());
			acf.setOwneruser(owneruser);
			acf.setOwnerdept(ownerdept);
			acf.setMainalarmsolvedate(StaticMethod.getTimestamp(linkFaultAvoidTime));
			acf.setStatus("0");
			acf.setIssended("1");
			main.setMainAlarmSolveDate(StaticMethod.getTimestamp(linkFaultAvoidTime));
			alarrMgr.updateAlarmsolveDate(acf,main);
//			mainmgr.saveOrUpdateMain(main);
			status=0;
		}
		if(status==0){
			o.put("text", "同步告警时间成功！");
		}else{
			o.put("text", "同步告警时间失败！");
		}
		data.put(o);
		jsonRoot.put("status", status);
		jsonRoot.put("data", data);
		JSONUtil.print(response, jsonRoot.toString());
	}
}
