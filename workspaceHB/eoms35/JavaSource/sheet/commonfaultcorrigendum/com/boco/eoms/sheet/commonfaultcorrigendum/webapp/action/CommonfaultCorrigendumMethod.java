package com.boco.eoms.sheet.commonfaultcorrigendum.webapp.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationImpl;
import com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationImplServiceLocator;
import com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumLink;
import com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumMain;
import com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumTask;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumLinkManager;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumMainManager;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumTaskManager;

/**
 * <p>
 * Title:故障工单勘误流程
 * </p>
 * <p>
 * Description:故障工单勘误流程
 * </p>
 * <p>
 * Mon Sep 29 11:24:17 CST 2014
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class CommonfaultCorrigendumMethod extends BaseSheet  {
     
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
		//String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
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
		
		
		if (operateType.equals("102") && taskName.equals("NetCorrigendum")) {
//			String mainnewTeamRoleId = StaticMethod.nullObject2String(request.getParameter("mainnewTeamRoleId"));
//			String mainnewccObject = StaticMethod.nullObject2String(request.getParameter("mainnewccObject"));
//			String mainCommonfaultNetName = StaticMethod.nullObject2String(main.get("mainCommonfaultNetName"));
//			ICommonfaultCorrigendumMainManager service = (ICommonfaultCorrigendumMainManager)ApplicationContextHolder.getInstance().getBean("iCommonfaultCorrigendumMainManager");
//			service.updateNetTeam(mainnewTeamRoleId, mainnewccObject, mainCommonfaultNetName);
//			System.out.println("sheetId="+sheetId+"==OverHold");
			ICommonfaultCorrigendumLinkManager linkservice = (ICommonfaultCorrigendumLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonfaultCorrigendumLinkManager");
			ICommonfaultCorrigendumTaskManager taskservice = (ICommonfaultCorrigendumTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonfaultCorrigendumTaskManager");
			ICommonfaultCorrigendumMainManager mainservice = (ICommonfaultCorrigendumMainManager)ApplicationContextHolder.getInstance().getBean("iCommonfaultCorrigendumMainManager");
			operate.put("hasNextTaskFlag", "true");
			operate.put("phaseId", "Over");
			Calendar calendar = Calendar.getInstance();
			calendar.add(13, 10);
			CommonfaultCorrigendumLink linkbean = (CommonfaultCorrigendumLink)linkservice.getLinkObject().getClass().newInstance();
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
			Object commonfaultCorrigendumMainObj = mainservice.getSingleMainPO(StaticMethod.nullObject2String(main.get("id")));
			if (commonfaultCorrigendumMainObj != null)
			{
				CommonfaultCorrigendumMain resourcesErrataMain = (CommonfaultCorrigendumMain)commonfaultCorrigendumMainObj;
//				resourcesErrataMain.setEndResult(obj);
				resourcesErrataMain.setStatus(new Integer(1));
				resourcesErrataMain.setHoldStatisfied(Integer.valueOf(0xfb89d));
				mainservice.addMain(resourcesErrataMain);
			}
//			main.put("status", "1");
			CommonfaultCorrigendumTask task = new CommonfaultCorrigendumTask();
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
			task.setFlowName("CommonfaultCorrigendumMainFlowProcess");
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
			ICommonfaultCorrigendumLinkManager service = (ICommonfaultCorrigendumLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonfaultCorrigendumLinkManager");
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
 
	public String getReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String reply = "false";
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		ICommonFaultMainManager mainservice = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonfaultCorrigendumMainManager service = (ICommonfaultCorrigendumMainManager)ApplicationContextHolder.getInstance().getBean("iCommonfaultCorrigendumMainManager");
		if (!"".equals(sheetKey)) {
			CommonFaultMain commonFaultMain = (CommonFaultMain)mainservice.getCommonFaultMainById(sheetKey);
			String mainNetName = StaticMethod.nullObject2String(commonFaultMain.getMainNetName());
			if (!"".equals(mainNetName)) {
				List queryList = service.getNetTeam(mainNetName);
				if (queryList.size() > 0) {
					reply = "true";
					String[] elementKey = new String[]{"","mainNetType","mainCity","mainCounty","mainSaveTime","mainCreateUserId","mainCreateDeptId","mainifAutotran","mainTeamRoleId","mainccObject"};
					Map main = new HashMap();
					Object[] tmpObjArr = (Object[])queryList.get(0);
					for (int j=1;j<tmpObjArr.length;j++) {
						main.put(elementKey[j], tmpObjArr[j]);
					}
					main.put("title", StaticMethod.nullObject2String(commonFaultMain.getSheetId()) + "此工单网元对应班组错误,需进行勘误.");
					main.put("mainCommonfaultSheetId", StaticMethod.nullObject2String(commonFaultMain.getSheetId()));
					main.put("mainCommonfaultNetName", mainNetName);
					main.put("mainCorrigendumTypeOne", StaticMethod.nullObject2String(commonFaultMain.getMainNetSortOne()));
					main.put("mainCorrigendumTypeTwo", StaticMethod.nullObject2String(commonFaultMain.getMainNetSortTwo()));
					main.put("mainCorrigendumTypeThree", StaticMethod.nullObject2String(commonFaultMain.getMainNetSortThree()));
					
					main.put("sendMonth", "0");
					main.put("sheetId", service.getSheetId());
					main.put("templateFlag", "0");
					main.put("holdStatisfied", "0");
					main.put("correlationKey", UUIDHexGenerator.getInstance().getID());
					main.put("sendUserId", "kw_admin");
					main.put("id", UUIDHexGenerator.getInstance().getID());
					main.put("sendDeptId", "12201");
					main.put("sendDay", "0");
					main.put("deleted", "0");
					main.put("sendOrgType", "subrole");
					main.put("sendYear", "0");
					main.put("sendTime", new Date());
					main.put("status", "0");
					main.put("sendRoleId", "8aa0813b1c6f2386011c6f39c8350027");
					
					Map link = new HashMap();
					link.put("operateYear", "0");
					link.put("templateFlag", "0");
					link.put("correlationKey", UUIDHexGenerator.getInstance().getID());
					link.put("completeFlag", "1");
					link.put("operateDay", "0");
					link.put("operateType", "0");
					link.put("acceptFlag", "1");
					link.put("operateUserId", "kw_admin");
					link.put("operateRoleId", "8aa0813b1c6f2386011c6f39c8350027");
					link.put("operateMonth", "0");
					link.put("acceptFlag", "1");
					link.put("operateDeptId", "12201");
					link.put("id", UUIDHexGenerator.getInstance().getID());
					link.put("toOrgType", "0");
					link.put("operateTime", new Date());
					
					Map operate = new HashMap();
					operate.put("dealPerformerType", "subrole");
					operate.put("gatherPhaseId", "HoldTask");
					operate.put("phaseId", "NetCorrigendum");
					operate.put("dealPerformer", tmpObjArr[0]);
					operate.put("beanId", "iCommonfaultCorrigendumMainManager");
					operate.put("extendKey1", UUIDHexGenerator.getInstance().getID());
					operate.put("linkBeanId", "iCommonfaultCorrigendumLinkManager");
					operate.put("linkClassName", "com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumLink");
					operate.put("reInvokeCount", "0");
					operate.put("mainClassName", "com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumMain");
					operate.put("dealPerformerLeader", tmpObjArr[0]);
					
					HashMap map = new HashMap();
					map.put("main", main);
					map.put("link", link);
					map.put("operate", operate);
					HashMap sessionMap = new HashMap();
					TawSystemSessionForm sessionform = (TawSystemSessionForm) request
							.getSession().getAttribute("sessionform");
					sessionMap.put("userId", sessionform.getUserid());
					sessionMap.put("password", sessionform.getPassword());
					
					IBusinessFlowService businessFlowService=
						 (IBusinessFlowService)ApplicationContextHolder.getInstance().getBean("businessFlowService");
					businessFlowService.initProcess("CommonfaultCorrigendum", "newWorkSheet",
							map, sessionMap);
				}
			}
		}
		return reply;
	}
	
	public void performPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		if (taskName.equals("NetCorrigendum") && operateType.equals("102"))
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			CommonfaultCorrigendumMain main = (CommonfaultCorrigendumMain)getMainService().getSingleMainPO(sheetKey);
			String sheetId = StaticMethod.nullObject2String(main.getSheetId());
			String mainCommonfaultNetName = StaticMethod.nullObject2String(main.getMainCommonfaultNetName());
			//根据网元名称查询
			String sqlquery = "SELECT netid,netname,teamroleid FROM commonfault_net_team_wx WHERE netname = '"+mainCommonfaultNetName+"'";
			IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		    List list = services.getSheetAccessoriesList(sqlquery);
		    
		    String result = "0";//1成功 0失败
		    String status ="2";
		    String text = "填写的此网元对应班组信息与综资系统信息不一致，不能回单;";
		    
		    if(list != null && list.size()>0){
		    	String netid = StaticMethod.nullObject2String((String)((Map) list.get(0)).get("netid"));
		    	String netname = StaticMethod.nullObject2String((String)((Map) list.get(0)).get("netname"));
		    	String teamroleid = StaticMethod.nullObject2String((String)((Map) list.get(0)).get("teamroleid"));
		    	String inparam = "<ProvFORM><DeviceId>" + netid + "</DeviceId><DeviceName>" + netname + "</DeviceName><GridId>" + teamroleid + "</GridId></ProvFORM>";
		    	
		    	try {
					JudgeDeviceGridRelationImplServiceLocator service = new JudgeDeviceGridRelationImplServiceLocator();
					JudgeDeviceGridRelationImpl binding = (JudgeDeviceGridRelationImpl)service.getJudgeDeviceGridRelationService();
					//.getOSSProcessSheet();
					result = binding.process(inparam);
					System.out.println("sheetId="+sheetId+"==CommonfaultCorrigendumMain--JudgeDeviceGridRelationImplServiceLocator--result=" + result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result = "2";//调用接口失败
					status = "2";
					text = "综资接口调用失败，请联系管理员";
				}
				
				//填写的此网元对应班组信息与综资系统信息不一致，不能回单
				if("0".equals(result)){//失败
					status = "2";
					text = "填写的此网元对应班组信息与综资系统信息不一致，不能回单";
				}
				
				if("1".equals(result)){//成功
					status = "0";
					text = "";
				}
				
				
		    }
		    JSONArray data = new JSONArray();
			JSONObject o = new JSONObject();
			o.put("text", text);
			data.put(o);
			JSONObject jsonRoot = new JSONObject();
			jsonRoot.put("data", data);
			jsonRoot.put("status", status);
			JSONUtil.print(response, jsonRoot.toString());
		}else{
			super.performPreCommit(mapping, form, request, response);
		}
	}
	
 }