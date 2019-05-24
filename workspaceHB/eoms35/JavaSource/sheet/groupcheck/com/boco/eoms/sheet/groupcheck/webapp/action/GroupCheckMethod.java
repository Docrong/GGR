package com.boco.eoms.sheet.groupcheck.webapp.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumLink;
import com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumMain;
import com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumTask;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumLinkManager;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumMainManager;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumTaskManager;
import com.boco.eoms.sheet.groupcheck.model.GroupCheckLink;
import com.boco.eoms.sheet.groupcheck.model.GroupCheckMain;
import com.boco.eoms.sheet.groupcheck.model.GroupCheckTask;
import com.boco.eoms.sheet.groupcheck.service.IGroupCheckLinkManager;
import com.boco.eoms.sheet.groupcheck.service.IGroupCheckMainManager;
import com.boco.eoms.sheet.groupcheck.service.IGroupCheckTaskManager;

/**
 * <p>
 * Title:集客投诉核查工单
 * </p>
 * <p>
 * Description:集客投诉核查工单
 * </p>
 * <p>
 * Wed Nov 08 15:11:38 GMT+08:00 2017
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class GroupCheckMethod extends BaseSheet  {
     
     public String getPageColumnName() {
		
		return super.getPageColumnName()+"gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
		+ "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";
		
	}
     
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception   {
        
        HashMap hashMap = new HashMap();

    	HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
		BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		if(!sheetKey.equals("")){
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			
		} 
		if(!sheetKey.equals("")){
			main = this.getMainService().getSingleMainPO(sheetKey);
		} 
		sheetMap.put("main", main);	
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet",sheetMap);
    	
    	return hashMap;
    }
    /**
     * 提交流程引擎前作最后一次参数处理
     */
     public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	super.dealFlowEngineMap(mapping, form, request, response);
    	
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		HashMap sheetMap = this.getFlowEngineMap();
		
		Map main = (HashMap) sheetMap.get("main");
		Map operate = (HashMap)sheetMap.get("operate");
		Map link = (HashMap) sheetMap.get("link");
		
		String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

		if(taskName.equals("reply") || taskName.equals("advice"))
		{   			
			link.put("id", "");			
		}
		
		if(dealperformers.length>=1){
			
			String corrKey = "";
			String tmp = "";
			for(int i=0;i<dealperformers.length;i++){
				tmp =  UUIDHexGenerator.getInstance().getID();
				if(dealperformers.length == 1){
					corrKey = tmp;
				}else{
					if(corrKey.equals("")){
						corrKey = tmp;
					}else{
						corrKey = corrKey + "," + tmp;	
					}
					
				}
			}
			System.out.println("corrKey"+corrKey);
			operate.put("extendKey1", corrKey);
			
		}
		
		
		
		if (operateType.equals("101") && taskName.equals("CityCheck") && phaseId.equals("HoldTask")) {
			IGroupCheckLinkManager linkservice = (IGroupCheckLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupCheckLinkManager");
			IGroupCheckTaskManager taskservice = (IGroupCheckTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupCheckTaskManager");
			IGroupCheckMainManager mainservice = (IGroupCheckMainManager)ApplicationContextHolder.getInstance().getBean("iGroupCheckMainManager");
			operate.put("hasNextTaskFlag", "true");
			operate.put("phaseId", "Over");
			
			operate.put("dealPerformerType", "user");
			operate.put("dealPerformer", StaticMethod.nullObject2String(main.get("sendUserId")));
			operate.put("dealPerformerLeader", StaticMethod.nullObject2String(main.get("sendUserId")));
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(13, 10);
			GroupCheckLink linkbean = (GroupCheckLink)linkservice.getLinkObject().getClass().newInstance();
			linkbean.setId(UUIDHexGenerator.getInstance().getID());
			linkbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
			linkbean.setOperateTime(calendar.getTime());
			linkbean.setOperateType(new Integer(18));
			linkbean.setOperateDay(calendar.get(5));
			linkbean.setOperateMonth(calendar.get(2) + 1);
			linkbean.setOperateYear(calendar.get(1));
			linkbean.setOperateUserId(StaticMethod.nullObject2String(main.get("sendUserId")));
			linkbean.setOperateDeptId(StaticMethod.nullObject2String(main.get("sendDeptId")));
			linkbean.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
			linkbean.setOperaterContact(StaticMethod.nullObject2String(main.get("sendContact")));
			linkbean.setToOrgRoleId("");
			linkbean.setToOrgType(new Integer(0));
			linkbean.setAcceptFlag(new Integer(2));
			linkbean.setCompleteFlag(new Integer(2));
			linkbean.setActiveTemplateId("HoldTask");
			linkservice.addLink(linkbean);
			Object groupCheckMainObj = mainservice.getSingleMainPO(StaticMethod.nullObject2String(main.get("id")));
			if (groupCheckMainObj != null)
			{
				GroupCheckMain resourcesErrataMain = (GroupCheckMain)groupCheckMainObj;
				resourcesErrataMain.setStatus(new Integer(1));
				resourcesErrataMain.setHoldStatisfied(Integer.valueOf(0xfb89d));
				mainservice.addMain(resourcesErrataMain);
			}
			GroupCheckTask task = new GroupCheckTask();
			try
			{
				task.setId(UUIDHexGenerator.getInstance().getID());
			}
			catch (Exception e3)
			{
				e3.printStackTrace();
			}
			task.setTaskName("HoldTask");
			task.setTaskDisplayName("归档");
			task.setFlowName("GroupCheckMainFlowProcess");
			task.setSendTime(new Date());
			task.setSheetKey(StaticMethod.nullObject2String(main.get("id")));
			task.setTaskStatus("5");
			task.setSheetId(StaticMethod.nullObject2String(main.get("sheetId")));
			task.setTitle(StaticMethod.nullObject2String(main.get("title")));
			task.setOperateType("subrole");
			task.setCreateTime(new Date());
			task.setCreateYear(calendar.get(1));
			task.setCreateMonth(calendar.get(2) + 1);
			task.setCreateDay(calendar.get(5));
			task.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
			task.setTaskOwner(StaticMethod.nullObject2String(main.get("sendUserId")));
			task.setOperateType("subrole");
			task.setIfWaitForSubTask("false");
			task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
			task.setPreLinkId(linkbean.getId());
			taskservice.addTask(task);
		}
		sheetMap.put("main", main);
		sheetMap.put("link", link);
		sheetMap.put("operate", operate);
		
		this.setFlowEngineMap(sheetMap);
    }
    
    
    /**
     * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
     */
    public Map getProcessOjbectAttribute() {
     	Map  attributeMap = new HashMap();
     	attributeMap.put("dealPerformer","dealPerformer");
        attributeMap.put("copyPerformer","copyPerformer");
     	attributeMap.put("auditPerformer","auditPerformer");
     	attributeMap.put("subAuditPerformer","subAuditPerformer");
     	attributeMap.put("objectName", "operate");
  		return attributeMap;	
 	}
 	
	public Map getParameterMap() {
		Map  attributeMap = new HashMap();
		return attributeMap;
	} 
    
    /**
     * 设置main和link保存附件字段属性
     */
	 public Map getAttachmentAttributeOfOjbect() {
		Map objectMap = new HashMap();
		
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");

		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
			
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}
	 /**
     * 进入处理环节
     */
	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.showInputDealPage(mapping, form, request, response);
   		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        //驳回上一级（不是移交的任务），需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
			BaseLink  prelink = this.getLinkService().getSingleLinkPO(preLinkId);
			if (prelink != null) {
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
		//如果是移交后的任务被驳回 
		if (operateType.equals("4")) {
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			IGroupCheckLinkManager service = (IGroupCheckLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupCheckLinkManager");
			String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));
			String condition = " mainId='" + sheetKey + "' and operateType=8 and aiid='" + taskId + "' order by operateTime desc";
			List linkList = service.getLinksBycondition(condition);
			if (linkList != null && linkList.size() > 0) {
				BaseLink prelink = (BaseLink)linkList.get(0);
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
		
    }
	
	public void performDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String taskId = StaticMethod.null2String(request.getParameter("TKID"));
		
		String copyPerformer = StaticMethod.null2String(request.getParameter("copyPerformer"));
		
		String operateType = StaticMethod.null2String(request.getParameter("operateType"));
		
		String taskName = StaticMethod.null2String(request.getParameter("taskName"));
		
		super.performDeal(mapping, form, request, response);
		
		if(!"".equals(copyPerformer) || Integer.parseInt(operateType) == Constants.ACTION_MAKECOPYFOR
									 || Integer.parseInt(operateType) == Constants.ACTION_PHASE_BACKTOUP
									 || Integer.parseInt(operateType) == Constants.ACTION_DRIVERFORWARD){
			
			try {
				
				newSaveNonFlowData(taskId, this.getFlowEngineMap());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	public String showNewSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map map = new HashMap();
		map.put("title", "自动转单liu");
		map.put("mainGroupSheetId", "HB-058-171107-53008");
		map.put("mainProductIns", "");
		map.put("mainCircuitCode", "asd");
		map.put("mainUserAffilia", "");
		map.put("mainComplaintTime", "2014-10-12 23:24:25");
		map.put("operateroleid", "8a9982f222885c36012298ed35bf38de");
//		getNewFlow(map);
		return super.showNewSheetPage(mapping, form, request, response);
	}
	
	public String getNewFlow(Map map) throws Exception{
		
		IGroupCheckMainManager service = (IGroupCheckMainManager)ApplicationContextHolder.getInstance().getBean("iGroupCheckMainManager");
		Map main = new HashMap();
		main.put("title", StaticMethod.nullObject2String(map.get("title")));
		main.put("mainGroupSheetId", StaticMethod.nullObject2String(map.get("mainGroupSheetId")));//集团投诉工单
		main.put("mainProductIns", StaticMethod.nullObject2String(map.get("mainProductIns")));//产品实例标识
		main.put("mainCircuitCode", StaticMethod.nullObject2String(map.get("mainCircuitCode")));//电路代号
		main.put("mainUserAffilia", StaticMethod.nullObject2String(map.get("mainUserAffilia")));//用户归属地
		
		String mainComplaintTime = StaticMethod.nullObject2String(map.get("mainComplaintTime"));
		if(mainComplaintTime != null && !"".equals(mainComplaintTime)){
			main.put("mainComplaintTime", getDateByString(mainComplaintTime));//投诉时间
		}
		Calendar c = Calendar.getInstance();
		Date sheetAcceptLimit = c.getTime();
		c.add(Calendar.DATE, 2);
		Date sheetCompleteLimit = c.getTime();
		main.put("sheetAcceptLimit",  sheetAcceptLimit);
		main.put("sheetCompleteLimit",  sheetCompleteLimit);
		
		String operateroleid = StaticMethod.nullObject2String(map.get("operateroleid"));
		
		System.out.println("核查工单派往对象operateroleid="+operateroleid);
		
	    System.out.println("title="+StaticMethod.nullObject2String(map.get("title")));
	    System.out.println("mainGroupSheetId="+StaticMethod.nullObject2String(map.get("mainGroupSheetId")));
	    System.out.println("mainProductIns="+StaticMethod.nullObject2String(map.get("mainProductIns")));
	    System.out.println("mainCircuitCode="+StaticMethod.nullObject2String(map.get("mainCircuitCode")));
	    System.out.println("mainUserAffilia="+StaticMethod.nullObject2String(map.get("mainUserAffilia")));
	    System.out.println("mainComplaintTime="+StaticMethod.nullObject2String(map.get("mainComplaintTime")));
	    System.out.println("operateroleid="+StaticMethod.nullObject2String(map.get("operateroleid")));
		//读取建单人的信息
		String sendUserId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupCheck-crm.xml").getProperty("base.SendUserId"));
		String sendDeptId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupCheck-crm.xml").getProperty("base.SendDeptId"));
		String sendContact = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupCheck-crm.xml").getProperty("base.SendContact"));
		String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupCheck-crm.xml").getProperty("base.SendRoleId"));
		main.put("sendMonth", "0");
		main.put("sheetId", service.getSheetId());
		main.put("templateFlag", "0");
		main.put("holdStatisfied", "0");
		main.put("correlationKey", UUIDHexGenerator.getInstance().getID());
		main.put("sendUserId", sendUserId);//派段人
		main.put("id", UUIDHexGenerator.getInstance().getID());
		main.put("sendDeptId", sendDeptId);
		main.put("sendContact",sendContact); 
		main.put("sendDay", "0");
		main.put("deleted", "0");
		main.put("sendOrgType", "subrole");
		main.put("sendYear", "0");
		main.put("sendTime", new Date());
		main.put("status", "0");
		main.put("sendRoleId", sendRoleId);//派单人角色
		
		Map link = new HashMap();
		link.put("operateYear", "0");
		link.put("templateFlag", "0");
		link.put("correlationKey", UUIDHexGenerator.getInstance().getID());
		link.put("completeFlag", "1");
		link.put("operateDay", "0");
		link.put("operateType", "0");
		link.put("acceptFlag", "1");
		link.put("operateUserId", sendUserId);
		link.put("operateRoleId", sendRoleId);
		link.put("operateMonth", "0");
		link.put("acceptFlag", "1");
		link.put("operateDeptId", sendDeptId);
		link.put("id", UUIDHexGenerator.getInstance().getID());
		link.put("toOrgType", "0");
		link.put("operateTime", new Date());
		
		Map operate = new HashMap();
		operate.put("dealPerformerType", "subrole");
		operate.put("gatherPhaseId", "HoldTask");
		operate.put("phaseId", "CityCheck");
		operate.put("dealPerformer", operateroleid);//派往下一步的人
		operate.put("beanId", "iGroupCheckMainManager");
		operate.put("extendKey1", UUIDHexGenerator.getInstance().getID());
		operate.put("linkBeanId", "iGroupCheckLinkManager");
		operate.put("linkClassName", "com.boco.eoms.sheet.groupcheck.model.GroupCheckLink");
		operate.put("reInvokeCount", "0");
		operate.put("mainClassName", "com.boco.eoms.sheet.groupcheck.model.GroupCheckMain");
		operate.put("dealPerformerLeader", operateroleid);//派往下一步的人
		
		
		HashMap map1 = new HashMap();
		map1.put("main", main);
		map1.put("link", link);
		map1.put("operate", operate);
		HashMap sessionMap = new HashMap();
		
		sessionMap.put("userId", "admin");
		sessionMap.put("password", "123");
		
		IBusinessFlowService businessFlowService=
			 (IBusinessFlowService)ApplicationContextHolder.getInstance().getBean("businessFlowService");
		businessFlowService.initProcess("GroupCheck", "newWorkSheet",
				map1, sessionMap);
		return null;
	}
	
	public Date getDateByString(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			System.out.println("时间由string转换成date错误");
			e.printStackTrace();
		}
		return date;
	}
 
 }