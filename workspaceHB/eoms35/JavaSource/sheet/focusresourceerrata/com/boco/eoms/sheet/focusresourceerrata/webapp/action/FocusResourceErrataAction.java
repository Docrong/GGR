package com.boco.eoms.sheet.focusresourceerrata.webapp.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.SheetAction;
import com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataLink;
import com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataMain;
import com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataTask;
import com.boco.eoms.sheet.focusresourceerrata.service.IFocusResourceErrataLinkManager;
import com.boco.eoms.sheet.focusresourceerrata.service.IFocusResourceErrataMainManager;
import com.boco.eoms.sheet.focusresourceerrata.service.IFocusResourceErrataTaskManager;

/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class FocusResourceErrataAction extends SheetAction  {
 	
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
	
	
//	public ActionForward showNewSheetPage(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		System.out.println("调用集中故障接口 start get jzgz port by lyg");
//		String endpoint = "http://10.30.172.40:58999/CentralizedFaultHandlingService?wsdl";
//
//		Service service = new Service();
//		Call call = (Call) service.createCall();
//		call.setTargetEndpointAddress(endpoint);
//		// WSDL里面描述的接口名称(要调用的方法)
//		call.setOperationName(new javax.xml.namespace.QName("http://services.boco.com/", "run"));
//		// 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
//		call.addParameter("standardAlarmFpId", XMLType.XSD_STRING, ParameterMode.IN);
//		
//		// 设置被调用方法的返回值类型
//		call.setReturnType(XMLType.XSD_STRING);
//		
//		call.setTimeout(new Integer(500));
//		//设置方法中参数的值
//		Object[] paramValues = new Object[] {"56789"};
//		System.out.println("wwww");
//		// 给方法传递参数，并且调用方法
//		try {
//			 call.invoke(paramValues);	
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//  	  	System.out.println("调用集中故障接口 end get jzgz port by lyg=");
//		return super.showNewSheetPage(mapping, form, request, response);
//	}
	
	
	public Map createT1Data(Map sheetMap) throws Exception {
		
		String userIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.userId"));
		String deptIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.deptId"));
		String subRoleIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.subRoleId"));
		String contactT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.contact"));
		
		String timeLine = StaticMethod.nullObject2String(sheetMap.get("timeLine"));
		
		String dealPerformer = StaticMethod.nullObject2String(sheetMap.get("dealPerformer"));
		String subRoleIdT2 = "";
		if(!"".equals(dealPerformer)){
			subRoleIdT2 = dealPerformer;
		}else{
			subRoleIdT2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.subRoleId"));
		}
		
		Calendar calendar1 = Calendar.getInstance();
		if("crm".equals(timeLine)){
			calendar1.add(13, 2);//当前时间加1s
		}else{
			calendar1.add(13, -6);
		}
		
		
		String taskId = "_TKI:" + UUIDHexGenerator.getInstance().getID();
	    
	    //link
	    IFocusResourceErrataLinkManager linkservice = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
	    FocusResourceErrataLink T1link61 = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	    T1link61.setId(UUIDHexGenerator.getInstance().getID());
	    T1link61.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T1link61.setOperateType(new Integer("61"));
	    
	    T1link61.setOperateTime(calendar1.getTime());
	    T1link61.setOperateDay(calendar1.get(5));
	    T1link61.setOperateMonth(calendar1.get(2) + 1);
	    T1link61.setOperateYear(calendar1.get(1));
	    
	    T1link61.setOperateUserId(userIdT1);//T1 接单人
	    T1link61.setOperaterContact(contactT1);//T1 接单人电话
	    T1link61.setToOrgType(new Integer(0));
	    T1link61.setAcceptFlag(new Integer(0));
	    T1link61.setCompleteFlag(new Integer(0));
	    
	    T1link61.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T1link61.setActiveTemplateId("MonitoringDepart");
	    T1link61.setPiid("");
	    T1link61.setAiid(taskId);//对应Task表的id
	    
	    T1link61.setToOrgRoleId("");//确认受理不需要填此项
	    
	    T1link61.setOperateRoleId(subRoleIdT1);//T1处理人子角色id
	    T1link61.setOperateDeptId(deptIdT1);//T1处理人的部门
	    T1link61.setTemplateFlag(0);
	    
	    linkservice.addLink(T1link61);
	    
	    Calendar calendar2 = Calendar.getInstance();
	    if("crm".equals(timeLine)){
	    	calendar2.add(13, 3);//当前时间加2s
	    }else{
	    	calendar2.add(13, -4);
	    }
	    
	    FocusResourceErrataLink T1link = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	    String linkId = UUIDHexGenerator.getInstance().getID();
	   
	    T1link.setId(UUIDHexGenerator.getInstance().getID());
	    T1link.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T1link.setOperateType(new Integer(StaticMethod.nullObject2String(sheetMap.get("operateType"))));
	    
	    T1link.setOperateTime(calendar2.getTime());
	    T1link.setOperateDay(calendar2.get(5));
	    T1link.setOperateMonth(calendar2.get(2) + 1);
	    T1link.setOperateYear(calendar2.get(1));
	    
	    T1link.setOperateUserId(userIdT1);//T1 接单人
	    T1link.setOperaterContact(contactT1);//T1 接单人电话
	    T1link.setToOrgType(new Integer(0));
	    T1link.setAcceptFlag(new Integer(0));
	    T1link.setCompleteFlag(new Integer(0));
	    
	    T1link.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T1link.setActiveTemplateId("MonitoringDepart");
	    T1link.setPiid("");
	    T1link.setAiid(taskId);//对应Task表的id
	    
	    T1link.setToOrgRoleId(subRoleIdT2);//派往下一步的角色id
	    
	    T1link.setOperateRoleId(subRoleIdT1);//T1处理人子角色id
	    T1link.setOperateDeptId(deptIdT1);//T1处理人的部门
	    T1link.setTemplateFlag(0);
	    
	    T1link.setLinkCheckState(StaticMethod.nullObject2String(sheetMap.get("mainCheckState")));
	    
	    
	    String linkApprovalOpinion = StaticMethod.nullObject2String(sheetMap.get("linkApprovalOpinion"));
	    String linkIsMysel = StaticMethod.nullObject2String(sheetMap.get("linkIsMysel"));
	    String linkRemark = StaticMethod.nullObject2String(sheetMap.get("linkRemark"));
	    String linkReserved1 = StaticMethod.nullObject2String(sheetMap.get("linkReserved1"));
	    String linkResult = StaticMethod.nullObject2String(sheetMap.get("linkResult"));
	    T1link.setLinkApprovalOpinion(linkApprovalOpinion);
	    T1link.setLinkIsMysel(linkIsMysel);
	    T1link.setLinkRemark(linkRemark);
	    T1link.setLinkReserved1(linkReserved1);
	    T1link.setLinkResult(linkResult);
	    
	    linkservice.addLink(T1link);
	    
	    
	    //task
		IFocusResourceErrataTaskManager taskservice = (IFocusResourceErrataTaskManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataTaskManager");
		FocusResourceErrataTask T1Task = new FocusResourceErrataTask();
	    T1Task.setId(taskId);
	    T1Task.setTaskName("MonitoringDepart");
	    T1Task.setTaskDisplayName("监控部自动流转");
	    T1Task.setFlowName("FocusResourceErrata");
	    T1Task.setSendTime(calendar2.getTime());
	    T1Task.setSheetKey(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T1Task.setTaskStatus("5");
	    T1Task.setSheetId(StaticMethod.nullObject2String(sheetMap.get("sheetId")));
	    T1Task.setTitle(StaticMethod.nullObject2String(sheetMap.get("title")));
	    T1Task.setOperateType("subrole");
	    T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
	    T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
	    T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
	    T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
	    T1Task.setOperateRoleId(subRoleIdT1);//T1 的处理角色
	    T1Task.setTaskOwner(userIdT1);//T1 的处理人
	    T1Task.setIfWaitForSubTask("false");
	    T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
	    T1Task.setPreLinkId(linkId);
	    taskservice.addTask(T1Task);
	    Map T1Map = new HashMap();
	    T1Map.put("T1Link", T1link);
	    T1Map.put("T1Task", T1Task);
	    
		return T1Map;
	}
	
	
	public Map createT2Data(Map sheetMap) throws Exception {
		
		String userIdT2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.userId"));
		String deptIdT2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.deptId"));
		String subRoleIdT2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.subRoleId"));
		String contactT2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.contact"));
		
		String timeLine = StaticMethod.nullObject2String(sheetMap.get("timeLine"));
		
		String dealPerformer = StaticMethod.nullObject2String(sheetMap.get("dealPerformer"));
		String subRoleIdT3 = "";
		if(!"".equals(dealPerformer)){
			subRoleIdT3 = dealPerformer;
		}else{
			subRoleIdT3 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T3.subRoleId"));
		}
		
		Calendar calendar1 = Calendar.getInstance();
		if("crm".equals(timeLine)){
			calendar1.add(13, 4);//当前时间加4s
		}else{
			calendar1.add(13, -2);
		}
		
		
		String taskId = "_TKI:" + UUIDHexGenerator.getInstance().getID();
	    
	    //link
	    IFocusResourceErrataLinkManager linkservice = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
	    FocusResourceErrataLink T2link61 = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	    T2link61.setId(UUIDHexGenerator.getInstance().getID());
	    T2link61.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T2link61.setOperateType(new Integer("61"));
	    
	    T2link61.setOperateTime(calendar1.getTime());
	    T2link61.setOperateDay(calendar1.get(5));
	    T2link61.setOperateMonth(calendar1.get(2) + 1);
	    T2link61.setOperateYear(calendar1.get(1));
	    
	    T2link61.setOperateUserId(userIdT2);//T2 接单人
	    T2link61.setOperaterContact(contactT2);//T2 接单人电话
	    T2link61.setToOrgType(new Integer(0));
	    T2link61.setAcceptFlag(new Integer(0));
	    T2link61.setCompleteFlag(new Integer(0));
	    
	    T2link61.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T2link61.setActiveTemplateId("TransmissionNet");
	    T2link61.setPiid("");
	    T2link61.setAiid(taskId);//对应Task表的id
	    
	    T2link61.setToOrgRoleId("");//确认受理不需要填此项
	    
	    T2link61.setOperateRoleId(subRoleIdT2);//T2处理人子角色id
	    T2link61.setOperateDeptId(deptIdT2);//T2处理人的部门
	    T2link61.setTemplateFlag(0);
	    
	    linkservice.addLink(T2link61);
	    
	    Calendar calendar2 = Calendar.getInstance();
	    if("crm".equals(timeLine)){
	    	calendar2.add(13, 6);//当前时间加6s
	    }else{
	    	calendar2.add(13, -1);
	    }
	    
	    FocusResourceErrataLink T2link = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	    String linkId = UUIDHexGenerator.getInstance().getID();
	   
	    T2link.setId(UUIDHexGenerator.getInstance().getID());
	    T2link.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T2link.setOperateType(new Integer(StaticMethod.nullObject2String(sheetMap.get("operateType"))));
	    
	    T2link.setOperateTime(calendar2.getTime());
	    T2link.setOperateDay(calendar2.get(5));
	    T2link.setOperateMonth(calendar2.get(2) + 1);
	    T2link.setOperateYear(calendar2.get(1));
	    
	    T2link.setOperateUserId(userIdT2);//T1 接单人
	    T2link.setOperaterContact(contactT2);//T1 接单人电话
	    T2link.setToOrgType(new Integer(0));
	    T2link.setAcceptFlag(new Integer(0));
	    T2link.setCompleteFlag(new Integer(0));
	    
	    T2link.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T2link.setActiveTemplateId("TransmissionNet");
	    T2link.setPiid("");
	    T2link.setAiid(taskId);//对应Task表的id
	    
	    T2link.setToOrgRoleId(subRoleIdT3);//派往下一步的角色id
	    
	    T2link.setOperateRoleId(subRoleIdT2);//T1处理人子角色id
	    T2link.setOperateDeptId(deptIdT2);//T1处理人的部门
	    T2link.setTemplateFlag(0);
	    
	    String linkApprovalOpinion = StaticMethod.nullObject2String(sheetMap.get("linkApprovalOpinion"));
	    String linkIsMysel = StaticMethod.nullObject2String(sheetMap.get("linkIsMysel"));
	    String linkRemark = StaticMethod.nullObject2String(sheetMap.get("linkRemark"));
	    String linkReserved1 = StaticMethod.nullObject2String(sheetMap.get("linkReserved1"));
	    String linkResult = StaticMethod.nullObject2String(sheetMap.get("linkResult"));
	    T2link.setLinkApprovalOpinion(linkApprovalOpinion);
	    T2link.setLinkIsMysel(linkIsMysel);
	    T2link.setLinkRemark(linkRemark);
	    T2link.setLinkReserved1(linkReserved1);
	    T2link.setLinkResult(linkResult);
	    
	    linkservice.addLink(T2link);
	    
	    
	    //task
		IFocusResourceErrataTaskManager taskservice = (IFocusResourceErrataTaskManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataTaskManager");
		FocusResourceErrataTask T2Task = new FocusResourceErrataTask();
		T2Task.setId(taskId);
		T2Task.setTaskName("TransmissionNet");
		T2Task.setTaskDisplayName("传输网部审核");
		T2Task.setFlowName("FocusResourceErrata");
		T2Task.setSendTime(calendar2.getTime());
		T2Task.setSheetKey(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
		T2Task.setTaskStatus("5");
		T2Task.setSheetId(StaticMethod.nullObject2String(sheetMap.get("sheetId")));
		T2Task.setTitle(StaticMethod.nullObject2String(sheetMap.get("title")));
		T2Task.setOperateType("subrole");
		T2Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
		T2Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		T2Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		T2Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		T2Task.setOperateRoleId(subRoleIdT2);//T2 的处理角色
		T2Task.setTaskOwner(userIdT2);//T2 的处理人
		T2Task.setIfWaitForSubTask("false");
		T2Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
		T2Task.setPreLinkId(linkId);
	    taskservice.addTask(T2Task);
	    Map T2Map = new HashMap();
	    T2Map.put("T2Link", T2link);
	    T2Map.put("T2Task", T2Task);
	    
		return T2Map;
	}
	
	public Map createHoldTaskData(Map sheetMap) throws Exception {
		
		String userIdHold = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("base.InterfaceUser"));
		String deptIdHold = "";
		String subRoleIdHold = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("base.InterfaceRole"));
		String contactHold = "";
		
		ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(userIdHold);
		if (user != null)
		{
			deptIdHold = user.getDeptid();
			contactHold = user.getMobile();
		}
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(13, 7);//当前时间加7s
		
		String taskId = "_TKI:" + UUIDHexGenerator.getInstance().getID();
	    
	    //link
	    IFocusResourceErrataLinkManager linkservice = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
//	    FocusResourceErrataLink T2link61 = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
//	    T2link61.setId(UUIDHexGenerator.getInstance().getID());
//	    T2link61.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
//	    T2link61.setOperateType(new Integer("61"));
//	    
//	    T2link61.setOperateTime(calendar1.getTime());
//	    T2link61.setOperateDay(calendar1.get(5));
//	    T2link61.setOperateMonth(calendar1.get(2) + 1);
//	    T2link61.setOperateYear(calendar1.get(1));
//	    
//	    T2link61.setOperateUserId(userIdHold);
//	    T2link61.setOperaterContact(contactHold);
//	    T2link61.setToOrgType(new Integer(0));
//	    T2link61.setAcceptFlag(new Integer(0));
//	    T2link61.setCompleteFlag(new Integer(0));
//	    
//	    T2link61.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
//	    T2link61.setActiveTemplateId("TransmissionNet");
//	    T2link61.setPiid("");
//	    T2link61.setAiid(taskId);//对应Task表的id
//	    
//	    T2link61.setToOrgRoleId("");//确认受理不需要填此项
//	    
//	    T2link61.setOperateRoleId(subRoleIdHold);
//	    T2link61.setOperateDeptId(deptIdHold);
//	    T2link61.setTemplateFlag(0);
//	    
//	    linkservice.addLink(T2link61);
	    
	    Calendar calendar2 = Calendar.getInstance();
	    calendar2.add(13, 10);//当前时间加10s
	    FocusResourceErrataLink Holdlink = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	    String linkId = UUIDHexGenerator.getInstance().getID();
	   
	    Holdlink.setId(UUIDHexGenerator.getInstance().getID());
	    Holdlink.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    Holdlink.setOperateType(new Integer("18"));
	    
	    Holdlink.setOperateTime(calendar2.getTime());
	    Holdlink.setOperateDay(calendar2.get(5));
	    Holdlink.setOperateMonth(calendar2.get(2) + 1);
	    Holdlink.setOperateYear(calendar2.get(1));
	    
	    Holdlink.setOperateUserId(userIdHold);
	    Holdlink.setOperaterContact(contactHold);
	    Holdlink.setToOrgType(new Integer(0));
	    Holdlink.setAcceptFlag(new Integer(0));
	    Holdlink.setCompleteFlag(new Integer(0));
	    
	    Holdlink.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    Holdlink.setActiveTemplateId("TransmissionNet");
	    Holdlink.setPiid("");
	    Holdlink.setAiid(taskId);//对应Task表的id
	    
	    
	    Holdlink.setOperateRoleId(subRoleIdHold);
	    Holdlink.setOperateDeptId(deptIdHold);
	    Holdlink.setTemplateFlag(0);
	    
	    String linkApprovalOpinion = StaticMethod.nullObject2String(sheetMap.get("linkApprovalOpinion"));
	    String linkIsMysel = StaticMethod.nullObject2String(sheetMap.get("linkIsMysel"));
	    String linkRemark = StaticMethod.nullObject2String(sheetMap.get("linkRemark"));
	    String linkReserved1 = StaticMethod.nullObject2String(sheetMap.get("linkReserved1"));
	    String linkResult = StaticMethod.nullObject2String(sheetMap.get("linkResult"));
	    Holdlink.setLinkApprovalOpinion(linkApprovalOpinion);
	    Holdlink.setLinkIsMysel(linkIsMysel);
	    Holdlink.setLinkRemark(linkRemark);
	    Holdlink.setLinkReserved1(linkReserved1);
	    Holdlink.setLinkResult(linkResult);
	    
	    linkservice.addLink(Holdlink);
	    
	    
	    //task
		IFocusResourceErrataTaskManager taskservice = (IFocusResourceErrataTaskManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataTaskManager");
		FocusResourceErrataTask HoldTask = new FocusResourceErrataTask();
		HoldTask.setId(taskId);
		HoldTask.setTaskName("HoldTask");
		HoldTask.setTaskDisplayName("归档");
		HoldTask.setFlowName("FocusResourceErrata");
		HoldTask.setSendTime(calendar2.getTime());
		HoldTask.setSheetKey(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
		HoldTask.setTaskStatus("5");
		HoldTask.setSheetId(StaticMethod.nullObject2String(sheetMap.get("sheetId")));
		HoldTask.setTitle(StaticMethod.nullObject2String(sheetMap.get("title")));
		HoldTask.setOperateType("subrole");
		HoldTask.setCreateTime(new Date(System.currentTimeMillis() - 500L));
		HoldTask.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		HoldTask.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		HoldTask.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		HoldTask.setOperateRoleId(subRoleIdHold);
		HoldTask.setTaskOwner(userIdHold);
		HoldTask.setIfWaitForSubTask("false");
		HoldTask.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
		HoldTask.setPreLinkId(linkId);
	    taskservice.addTask(HoldTask);
	    Map HoldTaskMap = new HashMap();
	    HoldTaskMap.put("Holdlink", Holdlink);
	    HoldTaskMap.put("HoldTask", HoldTask);
	    
		return HoldTaskMap;
	}
	
	public FocusResourceErrataMain createMainData(Map sheetMap) throws Exception {
		
		String sendUserId = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("base.InterfaceUser"));
		String sendDeptId = "";
		String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("base.InterfaceRole"));
		String sendContact = "";
		
		String deptIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.deptId"));
		String subRoleIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.subRoleId"));
		
		
		ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(sendUserId);
		if (user != null)
		{
			sendDeptId= user.getDeptid();
			sendContact = user.getMobile();
		}
		
		IFocusResourceErrataLinkManager linkservice = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
	   
	    
	    Calendar calendar2 = Calendar.getInstance();
	    FocusResourceErrataLink T0link = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	   
	    T0link.setId(UUIDHexGenerator.getInstance().getID());
	    T0link.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T0link.setOperateType(new Integer("0"));
	    
	    T0link.setOperateTime(calendar2.getTime());
	    T0link.setOperateDay(calendar2.get(5));
	    T0link.setOperateMonth(calendar2.get(2) + 1);
	    T0link.setOperateYear(calendar2.get(1));
	    
	    T0link.setOperateUserId(sendUserId);
	    T0link.setOperaterContact(sendContact);
	    T0link.setToOrgType(new Integer(0));
	    T0link.setAcceptFlag(new Integer(0));
	    T0link.setCompleteFlag(new Integer(0));
	    
	    T0link.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T0link.setActiveTemplateId("");
	    T0link.setPiid("");
	    T0link.setAiid("");//对应Task表的id
	    
	    T0link.setToOrgRoleId(subRoleIdT1);//派往下一步的角色id
	    
	    T0link.setOperateRoleId(subRoleIdT1);//T1处理人子角色id
	    T0link.setOperateDeptId(deptIdT1);//T1处理人的部门
	    T0link.setTemplateFlag(0);
	    linkservice.addLink(T0link);
		
		
		
		
		IFocusResourceErrataMainManager mainservice = (IFocusResourceErrataMainManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataMainManager");
		FocusResourceErrataMain main = new FocusResourceErrataMain();
		
		
		String acceptLimit = StaticMethod.getLocalString(6);
		String completeLimit = StaticMethod.getLocalString(7);
		
		main.setSheetAcceptLimit(SheetUtils.stringToDate(acceptLimit));
		main.setSheetCompleteLimit(SheetUtils.stringToDate(completeLimit));
		
		main.setId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
		main.setSheetId(StaticMethod.nullObject2String(sheetMap.get("sheetId")));
		main.setTitle(StaticMethod.nullObject2String(sheetMap.get("title")));
		main.setSendTime(new Date());
		main.setSendOrgType("subrole");
		main.setSendUserId(sendUserId);
		main.setSendContact(sendContact);
		main.setStatus(new Integer(1));
		main.setDeleted(new Integer(0));
		main.setHoldStatisfied(new Integer(0));
		main.setSendDeptId(sendDeptId);
		main.setSendRoleId(sendRoleId);
		main.setTemplateFlag(0);
		main.setSendYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		main.setSendMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		main.setSendDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		
		main.setMainBusinessType(StaticMethod.nullObject2String(sheetMap.get("mainBusinessType")));
		main.setMainCaller(StaticMethod.nullObject2String(sheetMap.get("mainCaller")));
		main.setMainCheckState(StaticMethod.nullObject2String(sheetMap.get("mainCheckState")));
		main.setMainCircuitCode(StaticMethod.nullObject2String(sheetMap.get("mainCircuitCode")));
		main.setMainCustomerName(StaticMethod.nullObject2String(sheetMap.get("mainCustomerName")));
		main.setMainFailCity(StaticMethod.nullObject2String(sheetMap.get("mainFailCity")));
		main.setMainLineArea(StaticMethod.nullObject2String(sheetMap.get("mainLineArea")));
		main.setMainProductInstance(StaticMethod.nullObject2String(sheetMap.get("mainProductInstance")));
		main.setMainReserved1(StaticMethod.nullObject2String(sheetMap.get("mainReserved1")));
		main.setMainReserved2(StaticMethod.nullObject2String(sheetMap.get("mainReserved2")));
		main.setMainReserved3(StaticMethod.nullObject2String(sheetMap.get("mainReserved3")));
		main.setMainReserved4(StaticMethod.nullObject2String(sheetMap.get("mainReserved4")));
		main.setMainReserved5(StaticMethod.nullObject2String(sheetMap.get("mainReserved5")));
		main.setMainSheetNun(StaticMethod.nullObject2String(sheetMap.get("mainSheetNun")));
		
		mainservice.saveOrUpdateMain(main);
		
		return main;
	}
	
	public void createT2linkData(Map sheetMap,HttpServletRequest request) throws Exception {
		IFocusResourceErrataMainManager mainservice = (IFocusResourceErrataMainManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataMainManager");
		TawSystemSessionForm tawSystemSessionForm = (TawSystemSessionForm) request
	      .getSession().getAttribute("sessionform");
		String subRoleIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.subRoleId"));
		String sheetId = StaticMethod.nullObject2String(sheetMap.get("sheetId"));
		FocusResourceErrataMain main = (FocusResourceErrataMain)mainservice.getMainBySheetId(sheetId);
		main.setStatus(new Integer(1));
		mainservice.saveOrUpdateMain(main);
		
		IFocusResourceErrataLinkManager linkservice = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
		FocusResourceErrataLink T2link = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	   
	    T2link.setId(UUIDHexGenerator.getInstance().getID());
	    T2link.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
	    T2link.setOperateType(new Integer(StaticMethod.nullObject2String(sheetMap.get("operateType"))));
	    
	    Calendar calendar2 = Calendar.getInstance();
	    T2link.setOperateTime(calendar2.getTime());
	    T2link.setOperateDay(calendar2.get(5));
	    T2link.setOperateMonth(calendar2.get(2) + 1);
	    T2link.setOperateYear(calendar2.get(1));
	    
	    T2link.setOperateUserId(tawSystemSessionForm.getUserid());//T1 接单人
	    T2link.setOperaterContact(tawSystemSessionForm.getContactMobile());//T1 接单人电话
	    T2link.setToOrgType(new Integer(0));
	    T2link.setAcceptFlag(new Integer(0));
	    T2link.setCompleteFlag(new Integer(0));
	    
	    T2link.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T2link.setActiveTemplateId("TransmissionNet");
	    T2link.setPiid("");
	    T2link.setAiid("");//对应Task表的id
	    
	    T2link.setToOrgRoleId(subRoleIdT1);//派往下一步的角色id
	    
	    T2link.setOperateRoleId("");//T1处理人子角色id
	    T2link.setOperateDeptId(tawSystemSessionForm.getDeptid());//T1处理人的部门
	    T2link.setTemplateFlag(0);
	    
	    String linkApprovalOpinion = StaticMethod.nullObject2String(sheetMap.get("linkApprovalOpinion"));
	    String linkIsMysel = StaticMethod.nullObject2String(sheetMap.get("linkIsMysel"));
	    String linkRemark = StaticMethod.nullObject2String(sheetMap.get("linkRemark"));
	    String linkReserved1 = StaticMethod.nullObject2String(sheetMap.get("linkReserved1"));
	    String linkResult = StaticMethod.nullObject2String(sheetMap.get("linkResult"));
	    T2link.setLinkApprovalOpinion(linkApprovalOpinion);
	    T2link.setLinkIsMysel(linkIsMysel);
	    T2link.setLinkRemark(linkRemark);
	    T2link.setLinkReserved1(linkReserved1);
	    T2link.setLinkResult(linkResult);
	    
	    linkservice.addLink(T2link);
	}

	public void createT3linkData(Map sheetMap,HttpServletRequest request) throws Exception {
		IFocusResourceErrataMainManager mainservice = (IFocusResourceErrataMainManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataMainManager");
		TawSystemSessionForm tawSystemSessionForm = (TawSystemSessionForm) request
	      .getSession().getAttribute("sessionform");
		String subRoleIdT1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T1.subRoleId"));
		String sheetId = StaticMethod.nullObject2String(sheetMap.get("sheetId"));
		FocusResourceErrataMain main = (FocusResourceErrataMain)mainservice.getMainBySheetId(sheetId);
		main.setStatus(new Integer(1));
		mainservice.saveOrUpdateMain(main);
		
		IFocusResourceErrataLinkManager linkservice = (IFocusResourceErrataLinkManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataLinkManager");
		FocusResourceErrataLink T3link = (FocusResourceErrataLink)linkservice.getLinkObject().getClass().newInstance();
	   
		T3link.setId(UUIDHexGenerator.getInstance().getID());
		T3link.setMainId(StaticMethod.nullObject2String(sheetMap.get("sheetKey")));
		T3link.setOperateType(new Integer(StaticMethod.nullObject2String(sheetMap.get("operateType"))));
	    
	    Calendar calendar2 = Calendar.getInstance();
	    T3link.setOperateTime(calendar2.getTime());
	    T3link.setOperateDay(calendar2.get(5));
	    T3link.setOperateMonth(calendar2.get(2) + 1);
	    T3link.setOperateYear(calendar2.get(1));
	    
	    T3link.setOperateUserId(tawSystemSessionForm.getUserid());//T1 接单人
	    T3link.setOperaterContact(tawSystemSessionForm.getContactMobile());//T1 接单人电话
	    T3link.setToOrgType(new Integer(0));
	    T3link.setAcceptFlag(new Integer(0));
	    T3link.setCompleteFlag(new Integer(0));
	    
	    T3link.setPreLinkId("");//上一个link记录的id，由于这里没法得到就没有填写
	    T3link.setActiveTemplateId("CitieErrata");
	    T3link.setPiid("");
	    T3link.setAiid("");//对应Task表的id
	    
	    T3link.setToOrgRoleId(subRoleIdT1);//派往下一步的角色id
	    
	    T3link.setOperateRoleId("");//T1处理人子角色id
	    T3link.setOperateDeptId(tawSystemSessionForm.getDeptid());//T1处理人的部门
	    T3link.setTemplateFlag(0);
	    
	    String linkApprovalOpinion = StaticMethod.nullObject2String(sheetMap.get("linkApprovalOpinion"));
	    String linkIsMysel = StaticMethod.nullObject2String(sheetMap.get("linkIsMysel"));
	    String linkRemark = StaticMethod.nullObject2String(sheetMap.get("linkRemark"));
	    String linkReserved1 = StaticMethod.nullObject2String(sheetMap.get("linkReserved1"));
	    String linkResult = StaticMethod.nullObject2String(sheetMap.get("linkResult"));
	    T3link.setLinkApprovalOpinion(linkApprovalOpinion);
	    T3link.setLinkIsMysel(linkIsMysel);
	    T3link.setLinkRemark(linkRemark);
	    T3link.setLinkReserved1(linkReserved1);
	    T3link.setLinkResult(linkResult);
	    
	    linkservice.addLink(T3link);
	}
	
 }
 



