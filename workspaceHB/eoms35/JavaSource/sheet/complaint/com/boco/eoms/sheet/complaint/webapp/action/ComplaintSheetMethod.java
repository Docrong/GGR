// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintSheetMethod.java

package com.boco.eoms.sheet.complaint.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.exception.AccessoriesException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.*;
import com.boco.eoms.sheet.base.util.flowdefine.xml.*;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.complaint.model.ComplaintLink;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.*;
import com.boco.eoms.sheet.complaint.service.bo.EomsReplyNetClinet;
import com.boco.eoms.sheet.complaint.service.bo.EomsReplyTicketClient;
import com.boco.eoms.sheet.complaint.task.impl.ComplaintTask;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.interfaceBase.util.XmlUtil;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataMain;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintTaskManager;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import java.text.SimpleDateFormat;
import java.util.*;


import javax.servlet.http.*;
import javax.xml.rpc.ServiceException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.util.ParamEncoder;
import org.dom4j.*;

public class ComplaintSheetMethod extends BaseSheet
{

	public ComplaintSheetMethod()
	{
	}

	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		HashMap hashMap = new HashMap();
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
		System.out.println("operateName is -----------------------" + operatName);
		if (operatName.equals("forceHold"))
		{
			HashMap map = new HashMap();
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
			if (sheetKey == null || sheetKey.equals(""))
				sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			map.put("main", main);
			try
			{
				map.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			map.put("operate", getPageColumnName());
			hashMap.put("selfSheet", map);
		} else
		if (taskName.equals(""))
		{
			HashMap sheetMap = new HashMap();
			try
			{
				sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
		} else
		if (taskName.equals("DraftHumTask") || taskName.equals("ByRejectHumTask"))
		{
			HashMap sheetMap = new HashMap();
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
			if (sheetKey == null || sheetKey.equals(""))
				sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			sheetMap.put("main", main);
			try
			{
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException illegalaccessexception) { }
			catch (InstantiationException instantiationexception) { }
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
		} else
		if (taskName.equals("HoldHumTask"))
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
			HashMap sheetMap = new HashMap();
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			sheetMap.put("main", main);
			try
			{
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
		} else
		if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("CheckingHumTask"))
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
			System.out.println("==$$$$一级处理、二级处理、质检==sheetKey===" + sheetKey);
			HashMap sheetMap = new HashMap();
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			System.out.println("==$$$$一级处理、二级处理、质检==main===" + main);
			sheetMap.put("main", main);
			try
			{
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException illegalaccessexception1) { }
			catch (InstantiationException instantiationexception1) { }
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
		} else
		if (taskName.equals("advice") || taskName.equals("reply") || taskName.equals("cc") || taskName.equals("DeferExamineHumTask"))
		{
			HashMap sheetMap = new HashMap();
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
			if (sheetKey == null && sheetKey.equals(""))
				sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			System.out.println("非流程动作=====sheetKey is" + sheetKey);
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			sheetMap.put("main", main);
			try
			{
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException e1)
			{
				e1.printStackTrace();
			}
			catch (InstantiationException e1)
			{
				e1.printStackTrace();
			}
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
		} else
		{
			HashMap sheetMap = new HashMap();
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
			if (sheetKey == null && sheetKey.equals(""))
				sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			sheetMap.put("main", main);
			try
			{
				sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
			}
			catch (IllegalAccessException illegalaccessexception2) { }
			catch (InstantiationException instantiationexception2) { }
			sheetMap.put("operate", getPageColumnName());
			hashMap.put("selfSheet", sheetMap);
		}
		return hashMap;
	}

	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.dealFlowEngineMap(mapping, form, request, response);
		IComplaintMainManager mainservice = (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");						
		String roleId = StaticMethod.nullObject2String(request.getParameter("roleId"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		System.out.println("activeTemplateId=" + taskName);
		HashMap sheetMap = getFlowEngineMap();
		Map main = (HashMap)sheetMap.get("main");
		Map link = (HashMap)sheetMap.get("link");
		Map operateMap = (HashMap)sheetMap.get("operate");
		
		final ComplaintMain mainObject = new ComplaintMain();
		SheetBeanUtils.populate(mainObject, main);
		
		if (taskName.equals("reply") || taskName.equals("advice"))
			link.put("id", "");
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		System.out.println("operateType=====" + operateType);
		if (operateType.equals("46"))
		{
			Object operate = link.get("operateTime");
			if (operate != null && operate.getClass().isArray())
				operate = ((Object[])operate)[0];
			main.put("mainLastRepeatTime", operate);
		}
		String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendImmediately"));
		if (StaticMethod.nullObject2String(main.get("mainInterfaceSheetType")).equalsIgnoreCase("crm"))
		{
			if (!sendImmediately.equalsIgnoreCase("true"))
				if (operateType.equals("4"))
				{
					String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.InterfaceUser"));
					String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendRoleId"));
					String dealPerformer = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
					System.out.println("\346\212\225\350\257\211\351\251\263\345\233\236:dealPerformer=" + dealPerformer);
					if (dealPerformer.equals(userId) || dealPerformer.equals(sendRoleId))
					{
						operateMap.put("interfaceType", "withdrawWorkSheet");
						operateMap.put("methodType", "withdrawWorkSheet");
					}
				} else
				if (operateType.equals("9"))
				{
					operateMap.put("interfaceType", "notifyWorkSheet");
					operateMap.put("methodType", "notifyWorkSheet");
				} else
				if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61"))
				{
					String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
					ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
					ITask task = iTaskService.getSinglePO(taskId);
					if (task != null && !StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true"))
					{
						operateMap.put("interfaceType", "confirmWorkSheet");
						operateMap.put("methodType", "confirmWorkSheet");
					}
				} else
				if (taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66") && StaticMethod.nullObject2String(request.getParameter("mainDelayFlag")).equals("1"))
				{
					operateMap.put("interfaceType", "notifyWorkSheet");
					operateMap.put("methodType", "notifyWorkSheet");
				}
			if (operateType.equals("46"))
			{
				operateMap.put("interfaceType", "replyWorkSheet");
				operateMap.put("methodType", "replyWorkSheet");
				operateMap.put("sendType", new Integer("2"));
			}
			if (operateType.equals("56")&&taskName.equals("CheckingHumTask") )
			{
				String sheetKey =StaticMethod.nullObject2String(request.getParameter("mainId"));
				System.out.println("------质检环节------sheetkey-"+sheetKey);
				IWfInterfaceInfoManager iwfinterfaceinfoManager =(IWfInterfaceInfoManager)ApplicationContextHolder.getInstance().getBean("iWfInterfaceInfoManager");
				iwfinterfaceinfoManager.updateInfoForSend(sheetKey, "replyWorkSheet", "replyWorkSheet");
			}
		}
	 	System.out.println("---and by ph");
		System.out.println("lizhi:taskName=" + taskName + "operateType=" + operateType);
		if(operateType.equals("1") && taskName.equals("FirstExcuteHumTask")){
		String dealPerformer = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
		System.out.println("------eomsinfo ---dealPerformer -"+dealPerformer);
		StringBuffer where = new StringBuffer();
		where.append(" roleid = '215' and type5 = '5' ");
		where.append("and id ='"+dealPerformer+"'");
		System.out.println("----eomsinfo ---where +"+where.toString());
		ITawSystemSubRoleManager tawsubrole = (ITawSystemSubRoleManager)ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
		List subroles = tawsubrole.getTawSystemSubRoles(where.toString());			
//		final ComplaintMain mainObject = new ComplaintMain();
//		SheetBeanUtils.populate(mainObject, main);
		if(subroles.size()>0){
		System.out.println("------------start  -----SetEomsInfo---"+main.get("sheetId"));
		System.out.println("----eomsinfo---mainobject--"+mainObject.getSheetId()+"--"+mainObject.getBdeptContact());
		long m = System.currentTimeMillis();
		Runnable invokeAutoSplit = new Runnable(){
		public void run() {
			try{
			int result = EomsReplyTicketClient.setEomsInfo(mainObject);
			System.out.println("----setEomsTicket---return---"+result);
			}	
			catch (Exception e)
			{
				e.printStackTrace();
			}
				     }						
		};
		Thread t = new Thread(invokeAutoSplit);
		t.start();
		try{
			Thread.sleep(5000);
			}	
			catch (Exception e)
			{
				e.printStackTrace();
			}
		 System.out.println("eomsinfo --time :" + (System.currentTimeMillis() - m) / 1000+ "ms!");
				
	           }
		
		String subroleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/netOptComplainSheet.xml").getProperty("interfaceType.subroleId"));
		if(dealPerformer!=null && subroleid.equals(dealPerformer))	{
			try
			{
				String netResult = EomsReplyNetClinet.sendEomsInfo(mainObject);
				System.out.println("----setEomsNet---netResult---" + netResult);
				if("0".equals(netResult)){
					mainObject.setOtherUserDesc("netcomplaint");			
					mainservice.saveOrUpdateMain(mainObject);}
				}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		 }
		
		//移交调用同步接口
		if( (taskName.equals("SecondExcuteHumTask") && operateType.equals("8")) || operateType.equals("4")){
			String dealPerformer = StaticMethod.nullObject2String(operateMap.get("dealPerformer"));
			System.out.println("sheetId---"+mainObject.getSheetId()+"---dealPerformer -"+dealPerformer);
			String subroleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/netOptComplainSheet.xml").getProperty("interfaceType.subroleId"));
			if(dealPerformer!=null && subroleid.equals(dealPerformer))	{
				try
				{
					String netResult = EomsReplyNetClinet.sendEomsInfo(mainObject);
					System.out.println("----setEomsNet---netResult---" + netResult);
					if("0".equals(netResult)){
						mainObject.setOtherUserDesc("netcomplaint");			
						mainservice.saveOrUpdateMain(mainObject);
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
		
		
		if (taskName.equals("SecondExcuteHumTask") && operateType.equals("46"))
		{
			String customPhone = StaticMethod.nullObject2String(main.get("customPhone"));
			Date complaintTime = StaticMethod.nullObject2Timestamp(main.get("complaintTime"));
			System.out.println("lizhi:customPhone=" + customPhone + "complaintTime=" + complaintTime);
			int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(complaintTime);
			cal.add(5, time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beforedate = sdf.format(cal.getTime());
			String afterdate = sdf.format(complaintTime);
			System.out.println("lizhi:beforedate=" + beforedate + "afterdate=" + afterdate + "customPhone=" + customPhone);
			int repeatNum = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
			System.out.println("lizhi:repeatNum=" + repeatNum);
			
			System.out.println("test:repeatNum=" + repeatNum);
			List needAutoTranList = null;
			if (repeatNum == 0 || repeatNum == 1)
			{
				String complaintType1 = StaticMethod.nullObject2String(main.get("complaintType1"));
				String complaintType2 = StaticMethod.nullObject2String(main.get("complaintType2"));
				String complaintType = StaticMethod.nullObject2String(main.get("complaintType"));
				String complaintType4 = StaticMethod.nullObject2String(main.get("complaintType4"));
				String complaintType5 = StaticMethod.nullObject2String(main.get("complaintType5"));
				String complaintType6 = StaticMethod.nullObject2String(main.get("complaintType6"));
				String complaintType7 = StaticMethod.nullObject2String(main.get("complaintType7"));
				System.out.println("lizhi:complaintType1=" + complaintType1 + "complaintType2=" + complaintType2 + "complaintType=" + complaintType + "complaintType4=" + complaintType4 + "complaintType5=" + complaintType5 + "complaintType6=" + complaintType6 + "complaintType7=" + complaintType7);
				needAutoTranList = (List)((IComplaintMainManager)getMainService()).ifneedAutotran(complaintType1, complaintType2, complaintType, complaintType4, complaintType5, complaintType6, complaintType7);
				
				
				//System.out.println("lizhi:needAutoTranList.size=" + needAutoTranList.size());
				
				if (needAutoTranList!=null&&needAutoTranList.size() > 0)
				{
					Date opeTime = new Date();
					String prelinkId = StaticMethod.nullObject2String(link.get("id"));
					String subroleid = StaticMethod.nullObject2String(main.get("sendRoleId"));
					System.out.println("lizhi:opeTime=" + opeTime + "prelinkId=" + prelinkId + "subroleid=" + subroleid);
					operateMap.put("phaseId", "HoldHumTask");
					operateMap.put("dealPerformer", subroleid);
					operateMap.put("dealPerformerLeader", subroleid);
					operateMap.put("dealPerformerType", "subrole");
					if (StaticMethod.nullObject2String(main.get("mainInterfaceSheetType")).equalsIgnoreCase("crm")){
						if (!sendImmediately.equalsIgnoreCase("true")){
							operateMap.put("interfaceType", "replyWorkSheet");
							operateMap.put("methodType", "replyWorkSheet");
							operateMap.put("sendType", new Integer("0"));
						}
					}
					createCheckingLink(main, opeTime, prelinkId, subroleid);
					createCheckingTask(main, prelinkId);
				   String otheruser =StaticMethod.nullObject2String(main.get("otherUserDesc"));
					if(!"".equals(otheruser)){
						String sheetid =StaticMethod.nullObject2String(main.get("sheetId"));
						String  autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoUser"));
						String result = "1030101";
						String describe ="ok";
						System.out.println("--inspection ---result--"+result+"--describe---"+result);
						try {
							String inspectResult  = EomsReplyNetClinet.inspectionWorkSheet(sheetid, autoUser, result, describe);
							
							System.out.println("--sheetid--"+sheetid+"---inspectionResult----"+inspectResult);
						  } catch (ServiceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
					}
				}
			}
		}
		if (taskName.equals("CheckingHumTask") &&(operateType.equals("56")||operateType.equals("54")))
		{String otheruser =StaticMethod.nullObject2String(main.get("otherUserDesc"));
		if(!"".equals(otheruser)){	
		    String sheetid =StaticMethod.nullObject2String(main.get("sheetId"));
			String operUser =StaticMethod.nullObject2String(link.get("operateUserId"));
			String inspectionResult =StaticMethod.nullObject2String(link.get("linkCheckResult"));
			String inspectionDescribe =StaticMethod.nullObject2String(link.get("linkCheckIdea"));
			System.out.println("---inspection-----inspectionResult-"+inspectionResult+"-inspectionDescribe-"+inspectionDescribe);
			try {
				String inspectResult  = EomsReplyNetClinet.inspectionWorkSheet(sheetid, operUser, inspectionResult, inspectionDescribe);
				
				System.out.println("--sheetid--"+sheetid+"---inspectionResult----"+inspectResult);
				if("-1".equals(inspectResult)&& "1030102".equals(inspectionResult)){
			       System.out.println("netOpt调用接口失败！---+sheetid--- "+sheetid);		
					throw new Exception("netOpt调用接口失败！");
				}
			  } catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
		sheetMap.put("main", main);
		sheetMap.put("link", link);
		sheetMap.put("operate", operateMap);
		setFlowEngineMap(sheetMap);
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	super.showInputDealPage(mapping, form, request, response);
	String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
	TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
	ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
	List subRoleList = new ArrayList();
	String ccsubroleid = "";
	int listLength = subRoleList.size();
	long roleId = 0L;
	System.out.println("===operateRoleId====" + operateRoleId);
	TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);
	request.setAttribute("operateRoleId", operateRoleId);
	if (subrole != null)
		request.setAttribute("roleId", (new StringBuffer(String.valueOf(subrole.getRoleId()))).toString());
	request.setAttribute("operateDeptId", sessionform.getDeptid());
	String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
	String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
	if (taskName.equals(""))
		taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
	String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
	System.out.println("-----------operateType---------" + operateType);
	String zjSubroleid = "8a9982f2222d20300122311a9071016b";


	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
	if (operateType.equals("46"))
	{
		ComplaintMain complaintmain = (ComplaintMain)getMainService().getSingleMainPO(sheetKey);
		String customattribution = complaintmain.getCustomAttribution();
		String complaintType1 = complaintmain.getComplaintType1();
		String areaid = StaticMethod.nullObject2String(InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("customAttribution", StaticMethod.nullObject2String(customattribution)));
		System.out.println("--------质检 areaid-----------" + areaid);
		String roleid = String.valueOf(subrole.getRoleId());
		if (!areaid.equals(""))
		{
			ArrayList rolelist = (ArrayList)mgr.getSubRolesByDeptId(areaid, "52", "217");
			System.out.println("--质检-----deptid-------" + areaid);
			System.out.println("----质检----roleid-----------" + roleid);
			System.out.println("------------质检---------zjSubroleid----" + zjSubroleid);
			if (rolelist.size() > 0 && "101062502".equals(complaintType1))
			{
				TawSystemSubRole sysrole = (TawSystemSubRole)rolelist.get(0);
				zjSubroleid = sysrole.getId();
			}
		}
		System.out.println("------------质检---------zjSubroleid12----" + zjSubroleid);
		request.setAttribute("zjSubroleid", zjSubroleid);
	}
	if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") && !operateType.equals("4"))
		super.setParentTaskOperateWhenRejct(request);
	else
	if (taskName.equals("SecondExcuteHumTask") && operateType.equals("4"))
		setParentTaskOperateWhenRejct(request);
   if (taskName.equals("FirstExcuteHumTask") && operateType.equals("4")&& taskStatus.equals(Constants.TASK_STATUS_CLAIMED))
		setParentTaskOperateWhenRejctTocutsom(request);
	if (taskName.equals("SecondExcuteHumTask") && operateType.equals("4"))
	{
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
		System.out.println("------------ph***---------preLinkId----" + preLinkId);
		ccsubroleid = findFirstOperateId(sheetKey);
		request.setAttribute("ccsubroleid", ccsubroleid);
	}
}



	public void setParentTaskOperateWhenRejct(HttpServletRequest request)
		throws Exception
	{
		String prelinkid = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
		BaseLink preLink = getLinkService().getSingleLinkPO(prelinkid);
		String fOperateroleid = "";
		String ftaskOwner = "";
		String fOperateroleidType = "";
		String fPreTaskName = "";
		if (preLink != null)
		{
			String parentTaskId = StaticMethod.nullObject2String(preLink.getAiid());
			if (!parentTaskId.equals(""))
			{
				ITask task = getTaskService().getSinglePO(parentTaskId);
				fOperateroleid = task.getOperateRoleId();
				ftaskOwner = task.getTaskOwner();
				fOperateroleidType = task.getOperateType();
				fPreTaskName = task.getTaskName();
			} else
			{
				String firstOperateroleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("firstOperateroleid"));
				fOperateroleid = firstOperateroleid;
				ftaskOwner = firstOperateroleid;
				fOperateroleidType = "subrole";
			}
		}
		request.setAttribute("fOperateroleid", fOperateroleid);
		request.setAttribute("ftaskOwner", ftaskOwner);
		request.setAttribute("fOperateroleidType", fOperateroleidType);
		request.setAttribute("fPreTaskName", fPreTaskName);
	}

	public void setParentTaskOperateWhenRejctTocutsom(HttpServletRequest request)
	throws Exception
	{
		String prelinkid = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
		BaseLink preLink = getLinkService().getSingleLinkPO(prelinkid);
		String fOperateroleid = "";
		String ftaskOwner = "";
		String fOperateroleidType = "";
		String fPreTaskName = "";
		if (preLink != null)
		{	 String sheetKey = preLink.getMainId();
		     BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
		     fOperateroleid = main.getSendRoleId();
		     ftaskOwner = main.getSendUserId();
		     fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
		}
		request.setAttribute("fOperateroleid", fOperateroleid);
		request.setAttribute("ftaskOwner", ftaskOwner);
		request.setAttribute("fOperateroleidType", fOperateroleidType);
		request.setAttribute("fPreTaskName", fPreTaskName);
	}
	
	public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showDetailPage(mapping, form, request, response);
		String cusPhone = StaticMethod.nullObject2String(request.getParameter("cusPhone"), "");
		String cusPhones[] = cusPhone.split("\\(");
		String phoneNum = cusPhones[0];
		ComplaintMain mainObject = (ComplaintMain)request.getAttribute("sheetMain");
        Date complaintTime = mainObject.getComplaintTime();
        String customPhone = mainObject.getCustomPhone();
		int time2 = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime2"));
		Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(complaintTime);
		cal1.add(5, time2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String beforedate1 = sdf.format(cal1.getTime());
		String afterdate1 = sdf.format(complaintTime);
	//	int repeatNum = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
		int repeatNum1 = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime1(beforedate1, afterdate1, customPhone);
		
		String ifrepeat = "";
		String repeatNum = "";
		if ("1".equals(phoneNum))
		{
			ifrepeat = "否";
			repeatNum = "1";
		} else
		{
			ifrepeat = "是";
			repeatNum = phoneNum;
		}
		request.setAttribute("ifrepeat", ifrepeat);
		request.setAttribute("repeatNum", repeatNum);
		request.setAttribute("repeateNum1", repeatNum1+"");
//		add by weichao 20141230		
		String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
		if(!"".equals(taskStatus)&&"4".equals(taskStatus)){
			String t1dealer = request.getParameter("u");
			System.out.println("t1dealer's id is==="+t1dealer);
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			ITawSystemUserRefRoleManager manager = (ITawSystemUserRefRoleManager)ApplicationContextHolder.
							getInstance().getBean("itawSystemUserRefRoleManager");
			List userids = manager.getUserIdBySubRoleids(t1dealer);
			String ift1dealer = "false";
			if(null!=userids&&userids.size()>0){
				System.out.println("check it ok!!");
				ift1dealer = userids.contains(sessionform.getUserid())?"true":ift1dealer;			
			}
			request.setAttribute("ift1dealer", ift1dealer);						
		}
//add by weichao 20141230		
		
	}

	public Map getProcessOjbectAttribute()
	{
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
		attributeMap.put("subAuditPerformer", "subAuditPerformer");
		attributeMap.put("objectName", "operate");
		return attributeMap;
	}

	public Map getAttachmentAttributeOfOjbect()
	{
		Map objectMap = new HashMap();
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}
	public String findFirstOperateId(String sheetKey)
	throws Exception
{
    	String firstOperateId = "";
	    List complaintTasks = getTaskService().getTasksByCondition(" sheetKey='" + sheetKey + "' and taskName='FirstExcuteHumTask' ");
	    ComplaintTask t1Task = null;
	    if (complaintTasks != null && complaintTasks.size() > 0)
	     {
		   t1Task = (ComplaintTask)complaintTasks.get(complaintTasks.size() - 1);
		   firstOperateId = t1Task.getOperateRoleId();
	     }


		 System.out.println("-----***ph***--------firstOperateId:"+firstOperateId);		
	/*	
		 if (preLink != null){
				 String parentTaskId = StaticMethod.nullObject2String(preLink.getAiid());
	//			 if(!("").equals(parentTaskId)){
				 ITask task = getTaskService().getSinglePO(parentTaskId);
				  preTaskName = task.getTaskName();
				  preLinkId2 =task.getPreLinkId();
				  firstOperateId =task.getOperateRoleId();
				 }
		 else {
				
				 }*/
			
		return firstOperateId;
	}


	
	public String findPreOperateTask(String preLinkId, HttpServletRequest request)
		throws Exception
	{
		BaseLink preLink = getLinkService().getSingleLinkPO(preLinkId);
		String preTaskName = "";
		String fOperateroleid = "";
		String ftaskOwner = "";
		String fOperateroleidType = "";
		if (preLink != null)
		{
			String parentTaskId = StaticMethod.nullObject2String(preLink.getAiid());
			if (!parentTaskId.equals(""))
			{
				ITask task = getTaskService().getSinglePO(parentTaskId);
				fOperateroleid = task.getOperateRoleId();
				ftaskOwner = task.getTaskOwner();
				fOperateroleidType = task.getOperateType();
				preTaskName = task.getTaskName();
			} else
			{
				String sheetKey = preLink.getMainId();
				BaseMain main = getMainService().getSingleMainPO(sheetKey);
				fOperateroleid = main.getSendRoleId();
				ftaskOwner = main.getSendUserId();
				fOperateroleidType = "subrole";
			}
		}
		return preTaskName;
	}

	public void showAtomDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		showDetailPageAtom(mapping, form, request, response);
		ComplaintMain mainObject = (ComplaintMain)request.getAttribute("sheetMain");
		ComplaintTask task = (ComplaintTask)request.getAttribute("task");
		String isAccept = null;
		if (task.getTaskStatus().equals("2"))
			isAccept = "0";
		if (task.getTaskStatus().equals("8"))
			isAccept = "1";
		String asXML = showAtomDetail(mainObject, task, isAccept, request);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(asXML);
	}

	public String showAtomDetail(ComplaintMain mainObject, ITask task, String isAccept, HttpServletRequest request)
		throws Exception
	{
		Document document = DocumentFactory.getInstance().createDocument();
		Element root = document.addElement("process");
		Element attributes = root.addElement("attributes");
		Element attribute1 = attributes.addElement("attribute");
		Element title1 = attribute1.addElement("title");
		Element name1 = attribute1.addElement("name");
		title1.setText("工单流水号");
		name1.setText(StaticMethod.null2String(mainObject.getSheetId()));
		Element attribute2 = attributes.addElement("attribute");
		Element title2 = attribute2.addElement("title");
		Element name2 = attribute2.addElement("name");
		title2.setText("工单状态");
		IDictService serviceOne = (IDictService)ApplicationContextHolder.getInstance().getBean("DictService");
		String sheetStatus = (String)serviceOne.itemId2name("dict-sheet-common#sheetStatus", mainObject.getStatus());
		name2.setText(sheetStatus);
		Element attribute3 = attributes.addElement("attribute");
		Element title3 = attribute3.addElement("title");
		Element name3 = attribute3.addElement("name");
		title3.setText("工单主题");
		name3.setText(StaticMethod.null2String(mainObject.getTitle()));
		Element attribute4 = attributes.addElement("attribute");
		Element title4 = attribute4.addElement("title");
		Element name4 = attribute4.addElement("name");
		title4.setText("操作人");
		ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String operateName = service.id2Name(mainObject.getSendUserId(), "tawSystemUserDao");
		name4.setText(operateName);
		Element attribute5 = attributes.addElement("attribute");
		Element title5 = attribute5.addElement("title");
		Element name5 = attribute5.addElement("name");
		title5.setText("操作部门");
		String operateDept = service.id2Name(mainObject.getSendDeptId(), "tawSystemDeptDao");
		name5.setText(operateDept);
		Element attribute6 = attributes.addElement("attribute");
		Element title6 = attribute6.addElement("title");
		Element name6 = attribute6.addElement("name");
		title6.setText("操作人当前角色");
		String operateRoleID = service.id2Name(mainObject.getSendRoleId(), "tawSystemSubRoleDao");
		name6.setText(operateRoleID);
		Element attribute7 = attributes.addElement("attribute");
		Element title7 = attribute7.addElement("title");
		Element name7 = attribute7.addElement("name");
		title7.setText("操作人联系方式");
		name7.setText(StaticMethod.null2String(mainObject.getSendContact()));
		Element attribute8 = attributes.addElement("attribute");
		Element title8 = attribute8.addElement("title");
		Element name8 = attribute8.addElement("name");
		title8.setText("操作时间");
		String operatetime = StaticMethod.date2String(mainObject.getSendTime());
		name8.setText(operatetime);
		Element attribute9 = attributes.addElement("attribute");
		Element title9 = attribute9.addElement("title");
		Element name9 = attribute9.addElement("name");
		title9.setText("紧急程度");
		name9.setText(StaticMethod.null2String(mainObject.getUrgentDegree()));
		Element attribute10 = attributes.addElement("attribute");
		Element title10 = attribute10.addElement("title");
		Element name10 = attribute10.addElement("name");
		title10.setText("受理时限");
		name10.setText(StaticMethod.date2String(mainObject.getSheetAcceptLimit()));
		Element attribute11 = attributes.addElement("attribute");
		Element title11 = attribute11.addElement("title");
		Element name11 = attribute11.addElement("name");
		title11.setText("处理时限");
		name11.setText(StaticMethod.date2String(mainObject.getSheetCompleteLimit()));
		Element attribute12 = attributes.addElement("attribute");
		Element title12 = attribute12.addElement("title");
		Element name12 = attribute12.addElement("name");
		title12.setText("派发联系人");
		name12.setText(StaticMethod.null2String(mainObject.getBtype1()));
		Element attribute13 = attributes.addElement("attribute");
		Element title13 = attribute13.addElement("title");
		Element name13 = attribute13.addElement("name");
		title13.setText("派发联系人电话");
		name13.setText(StaticMethod.null2String(mainObject.getBdeptContact()));
		Element attribute14 = attributes.addElement("attribute");
		Element title14 = attribute14.addElement("title");
		Element name14 = attribute14.addElement("name");
		title14.setText("投诉时间");
		name14.setText(StaticMethod.date2String(mainObject.getComplaintTime()));
		Element attribute15 = attributes.addElement("attribute");
		Element title15 = attribute15.addElement("title");
		Element name15 = attribute15.addElement("name");
		title15.setText("故障时间");
		name15.setText(StaticMethod.date2String(mainObject.getFaultTime()));
		Element attribute16 = attributes.addElement("attribute");
		Element title16 = attribute16.addElement("title");
		Element name16 = attribute16.addElement("name");
		title16.setText("投诉位置");
		name16.setText(StaticMethod.null2String(mainObject.getComplaintAdd()));
		Element attribute17 = attributes.addElement("attribute");
		Element title17 = attribute17.addElement("title");
		Element name17 = attribute17.addElement("name");
		title17.setText("投诉内容");
		name17.setText(StaticMethod.null2String(mainObject.getComplaintDesc()));
		Element attribute19 = attributes.addElement("attribute");
		Element title19 = attribute19.addElement("title");
		Element name19 = attribute19.addElement("name");
		title19.setText("是否大面积投诉");
		name19.setText(service.id2Name(mainObject.getBdeptContactPhone(), "ItawSystemDictTypeDao"));
		Element attribute20 = attributes.addElement("attribute");
		Element title20 = attribute20.addElement("title");
		Element name20 = attribute20.addElement("name");
		title20.setText("重复投诉次数");
		name20.setText(StaticMethod.null2String(mainObject.getRepeatComplaintTimes()));
		Element attribute21 = attributes.addElement("attribute");
		Element title21 = attribute21.addElement("title");
		Element name21 = attribute21.addElement("name");
		title21.setText("用户类型");
		name21.setText(service.id2Name(mainObject.getCustomType(), "ItawSystemDictTypeDao"));
		Element attribute22 = attributes.addElement("attribute");
		Element title22 = attribute22.addElement("title");
		Element name22 = attribute22.addElement("name");
		title22.setText("客户品牌");
		name19.setText(service.id2Name(mainObject.getCustomBrand(), "ItawSystemDictTypeDao"));
		Element attribute23 = attributes.addElement("attribute");
		Element title23 = attribute23.addElement("title");
		Element name23 = attribute23.addElement("name");
		title23.setText("客户级别");
		name23.setText(StaticMethod.null2String(mainObject.getCustomLevel()));
		Element attribute24 = attributes.addElement("attribute");
		Element title24 = attribute24.addElement("title");
		Element name24 = attribute24.addElement("name");
		title24.setText("用户归属地");
		name24.setText(StaticMethod.null2String(mainObject.getCustomAttribution()));
		Element attribute25 = attributes.addElement("attribute");
		Element title25 = attribute25.addElement("title");
		Element name25 = attribute25.addElement("name");
		title25.setText("投诉分类一级类别");
		String complaintType1 = service.id2Name(mainObject.getComplaintType1(), "ItawSystemDictTypeDao");
		name25.setText(complaintType1);
		Element attribute26 = attributes.addElement("attribute");
		Element title26 = attribute26.addElement("title");
		Element name26 = attribute26.addElement("name");
		title26.setText("投诉分类二级类别");
		String complaintType2 = service.id2Name(mainObject.getComplaintType2(), "ItawSystemDictTypeDao");
		name26.setText(complaintType2);
		Element attribute27 = attributes.addElement("attribute");
		Element title27 = attribute27.addElement("title");
		Element name27 = attribute27.addElement("name");
		title27.setText("投诉分类三级类别");
		String complaintType = service.id2Name(mainObject.getComplaintType(), "ItawSystemDictTypeDao");
		name27.setText(complaintType);
		Element attribute28 = attributes.addElement("attribute");
		Element title28 = attribute28.addElement("title");
		Element name28 = attribute28.addElement("name");
		title28.setText("投诉分类四级类别");
		String complaintType4 = service.id2Name(mainObject.getComplaintType4(), "ItawSystemDictTypeDao");
		name28.setText(complaintType4);
		Element attribute29 = attributes.addElement("attribute");
		Element title29 = attribute29.addElement("title");
		Element name29 = attribute29.addElement("name");
		title29.setText("投诉分类五级类别");
		String complaintType5 = service.id2Name(mainObject.getComplaintType5(), "ItawSystemDictTypeDao");
		name29.setText(complaintType5);
		Element attribute30 = attributes.addElement("attribute");
		Element title30 = attribute30.addElement("title");
		Element name30 = attribute30.addElement("name");
		title30.setText("投诉分类六级类别");
		String complaintType6 = service.id2Name(mainObject.getComplaintType6(), "ItawSystemDictTypeDao");
		name30.setText(complaintType6);
		Element attribute31 = attributes.addElement("attribute");
		Element title31 = attribute31.addElement("title");
		Element name31 = attribute31.addElement("name");
		title31.setText("投诉分类七级类别");
		String complaintType7 = service.id2Name(mainObject.getComplaintType7(), "ItawSystemDictTypeDao");
		name31.setText(complaintType7);
		Element attribute32 = attributes.addElement("attribute");
		Element title32 = attribute32.addElement("title");
		Element name32 = attribute32.addElement("name");
		title32.setText("投诉受理省份");
		String startDealCity = StaticMethod.null2String(service.id2Name(mainObject.getStartDealCity(), "ItawSystemDictTypeDao"));
		name32.setText(startDealCity);
		Element attribute33 = attributes.addElement("attribute");
		Element title33 = attribute33.addElement("title");
		Element name33 = attribute33.addElement("name");
		title33.setText("故障行政区");
		name33.setText(StaticMethod.null2String(mainObject.getFaultArea()));
		Element attribute34 = attributes.addElement("attribute");
		Element title34 = attribute34.addElement("title");
		Element name34 = attribute34.addElement("name");
		title34.setText("故障路段名");
		name34.setText(StaticMethod.null2String(mainObject.getFaultRoad()));
		Element attribute35 = attributes.addElement("attribute");
		Element title35 = attribute35.addElement("title");
		Element name35 = attribute35.addElement("name");
		title35.setText("故障门牌号");
		name35.setText(StaticMethod.null2String(mainObject.getFaultNo()));
		Element attribute36 = attributes.addElement("attribute");
		Element title36 = attribute36.addElement("title");
		Element name36 = attribute36.addElement("name");
		title36.setText("故障交叉路段名1");
		name36.setText(StaticMethod.null2String(mainObject.getFaultRoad1()));
		Element attribute37 = attributes.addElement("attribute");
		Element title37 = attribute37.addElement("title");
		Element name37 = attribute37.addElement("name");
		title37.setText("故障交叉路段名2");
		name37.setText(StaticMethod.null2String(mainObject.getFaultRoad2()));
		Element attribute38 = attributes.addElement("attribute");
		Element title38 = attribute38.addElement("title");
		Element name38 = attribute38.addElement("name");
		title38.setText("楼宇名称");
		name38.setText(StaticMethod.null2String(mainObject.getFaultVill()));
		Element attribute39 = attributes.addElement("attribute");
		Element title39 = attribute39.addElement("title");
		Element name39 = attribute39.addElement("name");
		title39.setText("是否上门服务");
		String isVisit = service.id2Name(mainObject.getIsVisit(), "ItawSystemDictTypeDao");
		name39.setText(isVisit);
		Element attribute40 = attributes.addElement("attribute");
		Element title40 = attribute40.addElement("title");
		Element name40 = attribute40.addElement("name");
		title40.setText("故障现象");
		name40.setText(StaticMethod.null2String(mainObject.getFaultDesc()));
		Element attribute41 = attributes.addElement("attribute");
		Element title41 = attribute41.addElement("title");
		Element name41 = attribute41.addElement("name");
		title41.setText("附件");
		ITawCommonsAccessoriesManager mgrr = (ITawCommonsAccessoriesManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
		try
		{
			String str = mainObject.getSheetAccessories();
			List list = mgrr.getAllFileById(mainObject.getSheetAccessories());
			String url = "";
			for (int i = 0; i < list.size(); i++)
			{
				TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories)list.get(i);
				url = url + "<a href='http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/eoms35/accessories/tawCommonsAccessoriesConfigs.do?method=download&type=interface&userName=" + request.getParameter("userName") + "&id=" + tawCommonsAccessories.getId() + "'>" + tawCommonsAccessories.getAccessoriesCnName() + "</a><br>";
			}

			name41.setText(url);
		}
		catch (AccessoriesException e)
		{
			e.printStackTrace();
		}
		Element parameters = root.addElement("parameters");
		Element parameter1 = parameters.addElement("hidden");
		Element id1 = parameter1.addElement("id");
		Element value1 = parameter1.addElement("value");
		id1.setText("beanName");
		value1.setText(request.getParameter("beanName"));
		Element parameter2 = parameters.addElement("hidden");
		Element id2 = parameter2.addElement("id");
		Element value2 = parameter2.addElement("value");
		id2.setText("sheetKey");
		value2.setText(task.getSheetKey());
		Element parameter3 = parameters.addElement("hidden");
		Element id3 = parameter3.addElement("id");
		Element value3 = parameter3.addElement("value");
		id3.setText("taskId");
		value3.setText(task.getId());
		Element parameter4 = parameters.addElement("hidden");
		Element id4 = parameter4.addElement("id");
		Element value4 = parameter4.addElement("value");
		id4.setText("taskName");
		value4.setText(task.getTaskName());
		Element parameter5 = parameters.addElement("hidden");
		Element id5 = parameter5.addElement("id");
		Element value5 = parameter5.addElement("value");
		id5.setText("preLinkId");
		value5.setText(task.getPreLinkId());
		Element parameter6 = parameters.addElement("hidden");
		Element id6 = parameter6.addElement("id");
		Element value6 = parameter6.addElement("value");
		id6.setText("isAccept");
		value6.setText(isAccept);
		Element parameter7 = parameters.addElement("hidden");
		Element id7 = parameter7.addElement("id");
		Element value7 = parameter7.addElement("value");
		id7.setText("activeTemplateId");
		value7.setText(task.getTaskName());
		Element parameter8 = parameters.addElement("hidden");
		Element id8 = parameter8.addElement("id");
		Element value8 = parameter8.addElement("value");
		id8.setText("beanName");
		value8.setText(request.getParameter("beanName"));
		Element parameter9 = parameters.addElement("hidden");
		Element id9 = parameter9.addElement("id");
		Element value9 = parameter9.addElement("value");
		id9.setText("beanId");
		value9.setText("iCommonFaultMainManager");
		Element parameter10 = parameters.addElement("hidden");
		Element id10 = parameter10.addElement("id");
		Element value10 = parameter10.addElement("value");
		id10.setText("mainClassName");
		value10.setText("com.boco.eoms.sheet.complaint.model.ComplaintMain");
		Element parameter11 = parameters.addElement("hidden");
		Element id11 = parameter11.addElement("id");
		Element value11 = parameter11.addElement("value");
		id11.setText("linkClassName");
		value11.setText("com.boco.eoms.sheet.complaint.model.ComplaintLink");
		Element parameter12 = parameters.addElement("hidden");
		Element id12 = parameter12.addElement("id");
		Element value12 = parameter12.addElement("value");
		id12.setText("aiid");
		value12.setText(task.getId());
		Element parameter13 = parameters.addElement("hidden");
		Element id13 = parameter13.addElement("id");
		Element value13 = parameter13.addElement("value");
		id13.setText("piid");
		value13.setText(StaticMethod.null2String(task.getProcessId()));
		Element parameter14 = parameters.addElement("hidden");
		Element id14 = parameter14.addElement("id");
		Element value14 = parameter14.addElement("value");
		id14.setText("mainId");
		value14.setText(task.getSheetKey());
		Element parameter15 = parameters.addElement("hidden");
		Element id15 = parameter15.addElement("id");
		Element value15 = parameter15.addElement("value");
		id15.setText("TKID");
		value15.setText(task.getId());
		Element parameter01 = parameters.addElement("parameter");
		Element id01 = parameter01.addElement("id");
		Element value01 = parameter01.addElement("value");
		id01.setText("taskStatus");
		value01.setText(task.getTaskStatus());
		Element parameter02 = parameters.addElement("parameter");
		Element id02 = parameter02.addElement("id");
		Element value02 = parameter02.addElement("value");
		id02.setText("preTaskName");
		String preLinkId = task.getPreLinkId();
		String preTaskName = findPreOperateTask(preLinkId, request);
		value02.setText(preTaskName);
		System.out.println("preTaskName>>>>>>>" + preTaskName);
		System.out.println("------------------------------------" + document.asXML());
		return document.asXML();
	}

	public String getSheetAttachCode()
	{
		return null;
	}

	public Map initMap(Map map, List attach, String type)
		throws Exception
	{
		return null;
	}

	public void showListUndo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Integer pageSize = ((SheetAttributes)ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		String order = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("o")));
		String sort = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("s")));
		String orderCondition = "";
		if (!order.equals(""))
			if (order.equals("1"))
				order = " asc";
			else
				order = " desc";
		if (!sort.equals(""))
			orderCondition = " " + sort + order;
		String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
		if (!exportType.equals(""))
			pageSize = new Integer(-1);
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		BaseMain mainObject = (BaseMain)getMainService().getMainObject().getClass().newInstance();
		ITask taskObject = (ITask)getTaskService().getTaskModelObject().getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		String ifAgent = StaticMethod.null2String(request.getParameter("ifAgent"));
		condition.put("ifAgent", ifAgent);
		String flowName = getMainService().getFlowTemplateName();
		HashMap taskListOvertimeMap = getTaskService().getUndoTaskByOverTime(condition, userId, deptId, flowName, pageIndex, pageSize);
		int total = ((Integer)taskListOvertimeMap.get("taskTotal")).intValue();
		List taskOvertimeList = (List)taskListOvertimeMap.get("taskList");
		List taskMapList = new ArrayList();
		List taskList = new ArrayList();
		if (taskOvertimeList != null && taskOvertimeList.size() > 0)
		{
			IOvertimeTipManager service = (IOvertimeTipManager)ApplicationContextHolder.getInstance().getBean("iOvertimeTipManager");
			List timeList = service.getEffectOvertimeTip(getMainService().getFlowTemplateName(), userId);
			HashMap columnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
			HashMap columnMapOverTip = OvertimeTipUtil.getNotOverTimeColumnByMapping(flowName);
			int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
			for (int i = 0; i < taskOvertimeList.size(); i++)
			{
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0)
				{
					Object tmpObjArr[] = (Object[])taskOvertimeList.get(i);
					tmptask = (ITask)tmpObjArr[0];
					Iterator it = columnMap.keySet().iterator();
					int j = 0;
					String elementKey;
					Object tempcolumn;
					for (; it.hasNext(); tmptaskMap.put(columnMap.get(elementKey), tempcolumn))
					{
						j++;
						elementKey = (String)it.next();
						tempcolumn = tmpObjArr[j];
						conditionMap.put(elementKey, tempcolumn);
					}

				} else
				{
					tmptask = (ITask)taskOvertimeList.get(i);
				}
				if (exportType.equals(""))
				{
					String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(columnMapOverTip, tmptask.getCompleteTimeLimit(), conditionMap, timeList, flowName);
					taskMap.put("overtimeType", overtimeFlag);
				}
				String customPhone = StaticMethod.nullObject2String(tmptaskMap.get("customPhone"));
				Date complaintTime = StaticMethod.nullObject2Timestamp(tmptaskMap.get("complaintTime"));
				Calendar cal = Calendar.getInstance();
				cal.setTime(complaintTime);
				cal.add(5, time);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String beforedate = sdf.format(cal.getTime());
				String afterdate = sdf.format(complaintTime);
				System.out.println("lizhi:beforedate=" + beforedate + "afterdate=" + afterdate + "customPhone=" + customPhone);
				int repeatNum = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
				System.out.println("lizhi:repeatNum=" + repeatNum);
				if (repeatNum == 0)
					taskMap.put("customPhoneRepeatCount", "1(无历史投诉)");
				else
				if (repeatNum == 1)
					taskMap.put("customPhoneRepeatCount", "1(无历史投诉)");
				else
					taskMap.put("customPhoneRepeatCount", repeatNum + "(重复投诉)");
				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
				taskMap.putAll(tmptaskMap);
				taskList.add(tmptask);
				taskMapList.add(taskMap);
			}

		}
		Integer overTimeTaskCount = getTaskService().getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "list");
		request.setAttribute("module", mapping.getPath().substring(1));
		String workflowName = getMainService().getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(workflowName, getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null)
		{
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i++)
			{
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive"))
				{
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}

		}
		request.setAttribute("phaseIdMap", phaseIdMap);
		request.setAttribute("stepIdList", phaseIdList);
		String batch = StaticMethod.null2String(request.getParameter("batch"));
		if (!batch.equals("") && batch.equals("true"))
		{
			Map tempMap = new HashMap();
			String dictName = "dict-sheet-" + mapping.getPath().substring(1);
			List dictItems = DictMgrLocator.getDictService().getDictItems(Util.constituteDictId(dictName, "activeTemplateId"));
			for (Iterator it = dictItems.iterator(); it.hasNext();)
			{
				DictItemXML dictItemXml = (DictItemXML)it.next();
				String description = dictItemXml.getDescription();
				if (description.equals("batch:true"))
					tempMap.put(dictItemXml.getItemId(), dictItemXml.getItemName());
			}

			Map batchTaskMap = new HashMap();
			if (tempMap.size() > 0)
			{
				for (Iterator it = tempMap.keySet().iterator(); it.hasNext();)
				{
					String taskName = (String)it.next();
					for (Iterator tasks = taskList.iterator(); tasks.hasNext();)
					{
						ITask task = (ITask)tasks.next();
						if (taskName.equals(task.getTaskName()) && (task.getSubTaskFlag() == null || task.getSubTaskFlag().equals("false") || task.getSubTaskFlag().equals("")))
						{
							batchTaskMap.put(task.getTaskName(), task.getTaskDisplayName());
							break;
						}
					}

				}

			}
			request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
			request.setAttribute("batchTaskMap", batchTaskMap);
		}
	}

	public void newPerformNonFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
{
    BocoLog.info(this, "===\u4F18\u5316======\u975E\u6D41\u7A0B\u52A8\u4F5C===");
    HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
    Map map = newSetDealRequestMap(mapping, form, request, response);
    Object taskIdObject = map.get("TKID");
    if(taskIdObject != null && taskIdObject.getClass().isArray())
        taskIdObject = ((Object[])taskIdObject)[0];
    String taskId = StaticMethod.nullObject2String(taskIdObject);
    Map serializableMap = SheetUtils.serializableParemeterMap(map);
    Iterator it = serializableMap.keySet().iterator();
    HashMap WpsMap = new HashMap();
    HashMap tempWpsMap;
    for(; it.hasNext(); WpsMap.putAll(tempWpsMap))
    {
        String mapKey = (String)it.next();
        Map tempMap = (Map)serializableMap.get(mapKey);
        if(taskId.equals(""))
        {
            Object obj = tempMap.get("aiid");
            if(obj.getClass().isArray())
            {
                Object obja[] = (Object[])obj;
                obj = obja[0];
            }
            taskId = StaticMethod.nullObject2String(obj);
        }
        HashMap tempColumnMap = (HashMap)columnMap.get(mapKey);
        tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
    }

    setFlowEngineMap(WpsMap);
    dealFlowEngineMap(mapping, form, request, response);
    try
    {
        HashMap tmpMap = getFlowEngineMap();
        newSaveNonFlowData(taskId, tmpMap);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
	public void showMainDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showMainDetailPage(mapping, form, request, response);
		ComplaintMain mainObject = (ComplaintMain)request.getAttribute("sheetMain");
		if (mainObject != null)
		{
			Date complaintTime = mainObject.getComplaintTime();
			String customPhone = mainObject.getCustomPhone();
			int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
			int time2 = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime2"));
			Calendar cal = Calendar.getInstance();
			Calendar cal1 = Calendar.getInstance();
			cal.setTime(complaintTime);
			cal1.setTime(complaintTime);
			cal.add(5, time);
			cal1.add(5, time2);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beforedate = sdf.format(cal.getTime());
			String beforedate1 = sdf.format(cal1.getTime());
			String afterdate = sdf.format(complaintTime);
			String afterdate1 = sdf.format(complaintTime);
			int repeatNum = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
			int repeatNum1 = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime1(beforedate1, afterdate1, customPhone);
			
			String ifrepeat = "";
			if (repeatNum == 0)
			{
				request.setAttribute("repeatNum", "1(无历史投诉)");
				ifrepeat = "否";
			} else
			if (repeatNum == 1)
			{
				request.setAttribute("repeatNum", "1(无历史投诉)");
				ifrepeat = "否";
			} else
			{
				request.setAttribute("repeatNum", repeatNum + "(重复投诉)");
				ifrepeat = "是";
			}
			request.setAttribute("ifrepeat", ifrepeat);
			request.setAttribute("repeateNum1", repeatNum1+"");
		}
	}

	public static ComplaintLink createCheckingLink(Map mainrule, Date operateTime, String prelinkId, String subroleid)
		throws Exception
	{
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoUser"));
		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoSubRole"));
		System.out.println("lizhi:autoUser=" + autoUser + "autoSubRole=" + autoSubRole);
		IComplaintLinkManager linkservice = (IComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(operateTime);
		calendar.add(13, 30);
		ComplaintLink T1link61 = (ComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		T1link61.setId(UUIDHexGenerator.getInstance().getID());
		T1link61.setOperateType(new Integer("61"));
		T1link61.setActiveTemplateId("CheckingHumTask");
		T1link61.setOperateTime(calendar.getTime());
		T1link61.setOperateDay(calendar.get(5));
		T1link61.setOperateMonth(calendar.get(2) + 1);
		T1link61.setOperateYear(calendar.get(1));
		T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link61.setToOrgRoleId(subroleid);
		T1link61.setPreLinkId(prelinkId);
		T1link61.setNodeAccessories("");
		T1link61.setToOrgType(new Integer(0));
		T1link61.setCompleteFlag(new Integer(0));
		T1link61.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link61.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link61.setOperateDeptId("12201");
		T1link61.setTemplateFlag(0);
		linkservice.addLink(T1link61);
		calendar.add(13, 30);
		ComplaintLink T1link = (ComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		T1link.setId(UUIDHexGenerator.getInstance().getID());
		T1link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link.setOperateType(new Integer(56));
		T1link.setOperateTime(calendar.getTime());
		T1link.setOperateDay(calendar.get(5));
		T1link.setOperateMonth(calendar.get(2) + 1);
		T1link.setOperateYear(calendar.get(1));
		T1link.setAcceptFlag(new Integer(0));
		T1link.setPreLinkId(prelinkId);
		T1link.setActiveTemplateId("CheckingHumTask");
		T1link.setToOrgType(new Integer(0));
		T1link.setCompleteFlag(new Integer(0));
		T1link.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link.setOperateDeptId("12201");
		String correlationKey = UUIDHexGenerator.getInstance().getID();
		T1link.setCorrelationKey(correlationKey);
		T1link.setTemplateFlag(0);
		T1link.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
		T1link.setToOrgRoleId(subroleid);
		T1link.setNodeAcceptLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetAcceptLimit")));
		T1link.setNodeCompleteLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetCompleteLimit")));
		T1link.setLinkCheckResult("1030101");
		T1link.setLinkCheckIdea("ok");
		linkservice.addLink(T1link);
		return T1link;
	}

	public static void createCheckingTask(Map mainrule, String preLinkId)
		throws Exception
	{
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoUser"));
		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-util.xml").getProperty("AutoSubRole"));
		IComplaintTaskManager taskservice = (IComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
		ComplaintTask T1Task = (ComplaintTask)taskservice.getTaskModelObject().getClass().newInstance();
		try
		{
			T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
		}
		catch (Exception e3)
		{
			e3.printStackTrace();
		}
		T1Task.setTaskName("CheckingHumTask");
		T1Task.setTaskDisplayName("质检");
		T1Task.setFlowName("ComplaintProcess");
		T1Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.get("sendTime")));
		T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
		T1Task.setTaskStatus("5");
		T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
		T1Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
		T1Task.setOperateType("subrole");
		T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
		T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		T1Task.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1Task.setTaskOwner(StaticMethod.nullObject2String(autoUser));
		T1Task.setIfWaitForSubTask("false");
		T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
		T1Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
		T1Task.setAcceptTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetAcceptLimit")));
		T1Task.setCompleteTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetCompleteLimit")));
		taskservice.addTask(T1Task);
	}
	
	
	/**
	 * 工单提交预处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		IComplaintTaskManager taskservice = (IComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
		IComplaintLinkManager linkservice = (IComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		
		ComplaintMain main = (ComplaintMain)getMainService().getSingleMainPO(sheetKey);
		String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('8','2') and taskName in ('FirstExcuteHumTask','SecondExcuteHumTask','CheckingHumTask')  ORDER BY createtime DESC ";
        List taskList = taskservice.getTasksByCondition(condition);
        
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		
		ComplaintLink linkbean = new ComplaintLink();
		
		if(operateType.equals("56")&&taskName.equals("CheckingHumTask")){
			 List linkList = linkservice.getLinksBycondition("mainid = '"+sheetKey+"' AND operatetype = '46' ORDER BY operatetime DESC","ComplaintLink");
	            if(linkList != null && linkList.size()>0){
	            	linkbean = (ComplaintLink)linkList.get(0);
	            }
		}else{
			String remark = StaticMethod.nullObject2String(request.getParameter("remark"));
			String ndeptContact = StaticMethod.nullObject2String(request.getParameter("ndeptContact"));
			String ndeptContactPhone = StaticMethod.nullObject2String(request.getParameter("ndeptContactPhone"));
			String compProp = StaticMethod.nullObject2String(request.getParameter("compProp"));
			String isReplied = StaticMethod.nullObject2String(request.getParameter("isReplied"));
			String issueEliminatTimeStr = StaticMethod.nullObject2String(request.getParameter("issueEliminatTime"));
			String issueEliminatReason = StaticMethod.nullObject2String(request.getParameter("issueEliminatReason"));
			String linkDealDesc = StaticMethod.nullObject2String(request.getParameter("linkDealDesc"));
			String dealResult = StaticMethod.nullObject2String(request.getParameter("dealResult"));
			
			linkbean.setActiveTemplateId(taskName);
			linkbean.setOperateType(new Integer(operateType));
			linkbean.setOperateUserId(sessionform.getUserid());
			
			Date issus = SheetUtils.stringToDate(issueEliminatTimeStr);
			if(!"".equals(issueEliminatTimeStr)&& issus != null){
				linkbean.setIssueEliminatTime(issus);
			}
			linkbean.setRemark(remark);
			linkbean.setNdeptContact(ndeptContact);
			linkbean.setNdeptContactPhone(ndeptContactPhone);
			linkbean.setCompProp(compProp);
			linkbean.setIsReplied(isReplied);
			linkbean.setIssueEliminatReason(issueEliminatReason);
			linkbean.setLinkDealDesc(linkDealDesc);
			linkbean.setDealResult(dealResult);
		}
		
		
		ComplaintTask task = new ComplaintTask();
		if ((taskList != null) && (taskList.size() > 0)) {
			task = (ComplaintTask)taskList.get(0);
		}
		String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iComplaintMainManager");
		IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager)ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
		
		String status = "0";
		String text = "";
		
		System.out.println("complaintsheet==="+main.getSheetId());
		
		boolean returnType = true;
		WfInterfaceInfo info = new WfInterfaceInfo();
		//T1驳回给建单人 调用接口，返回成功就流转、不成功不流转
		String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendImmediately"));
		String linkId = StaticMethod.nullObject2String(task.getId());
		String isSended = "0";
		String mainBeanId = "iComplaintMainManager";
		String linkBeanId = "iComplaintTaskManager";
		String interfaceType = "";
		String methodType = "";
		if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm"))
		{
			if (!sendImmediately.equalsIgnoreCase("true")){
				if (operateType.equals("4") && taskName.equals("FirstExcuteHumTask")){ //驳回
//					String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.InterfaceUser"));
//					String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendRoleId"));
//					String dealPerformer = StaticMethod.nullObject2String(request.getParameter("dealPerformer"));
//					System.out.println("lyg:dealPerformer=" + dealPerformer);
//					if (dealPerformer.equals(userId) || dealPerformer.equals(sendRoleId))
//					{	
						interfaceType = "withdrawWorkSheet";
						methodType = "withdrawWorkSheet";
						
						info.setInterfaceType(interfaceType);
						info.setSheetKey(sheetKey);
						info.setLinkId(linkId);
						info.setIsSended(isSended);
						info.setMainBeanId(mainBeanId);
						info.setLinkBeanId(linkBeanId);
						info.setMethodType(methodType);
						
						System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=4=befoer=="+returnType);
						returnType = operateMgr.sendData(info,linkbean);
						System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=4=after=="+returnType);
						
						if(!returnType){//返回的结果是false
							text = "调用客服接口错误！";
							status = "2";
						}
//					}
				} else if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61")){//T1确认受理
					if (task != null && !StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true"))
					{   
						
						interfaceType = "confirmWorkSheet";
						methodType = "confirmWorkSheet";
						
						info.setInterfaceType(interfaceType);
						info.setSheetKey(sheetKey);
						info.setLinkId(linkId);
						info.setIsSended(isSended);
						info.setMainBeanId(mainBeanId);
						info.setLinkBeanId(linkBeanId);
						info.setMethodType(methodType);
						
						System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=61=befoer=="+returnType);
						returnType = operateMgr.sendData(info,linkbean);
						System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=61=after=="+returnType);
					}
				}
			}
			
		}
		
		if (operateType.equals("56")&&taskName.equals("CheckingHumTask") ){
			System.out.println("------质检环节------sheetkey-"+sheetKey);
			
			interfaceType = "replyWorkSheet";
			methodType = "replyWorkSheet";
			
			info.setInterfaceType(interfaceType);
			info.setSheetKey(sheetKey);
			info.setLinkId(linkId);
			info.setIsSended(isSended);
			info.setMainBeanId(mainBeanId);
			info.setLinkBeanId(linkBeanId);
			info.setMethodType(methodType);
			
			System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=56=befoer=="+returnType);
			returnType = operateMgr.sendData(info,linkbean);
			System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=56=after=="+returnType);
			
			if(!returnType){//返回的结果是false
				text = "调用客服接口错误！";
				status = "2";
			}
			
		}
		
		//T2回单时判断是否自动质检，是则调用接口，返回成功就流转、不成功不流转，否流程往下走
		//T2确认受理调用接口，此时不需要确认
		
		if (taskName.equals("SecondExcuteHumTask") && operateType.equals("46"))
		{
			String customPhone = StaticMethod.nullObject2String(main.getCustomPhone());
			Date complaintTime = StaticMethod.nullObject2Timestamp(main.getComplaintTime());
			System.out.println("lyg:customPhone=" + customPhone + "complaintTime=" + complaintTime);
			int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/complaint-util.xml").getProperty("RepeatComplaintTime"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(complaintTime);
			cal.add(5, time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beforedate = sdf.format(cal.getTime());
			String afterdate = sdf.format(complaintTime);
			System.out.println("lyg:beforedate=" + beforedate + "afterdate=" + afterdate + "customPhone=" + customPhone);
			int repeatNum = ((IComplaintMainManager)getMainService()).getCustomPhoneBySendTime(beforedate, afterdate, customPhone);
			System.out.println("lyg:repeatNum=" + repeatNum);
			
			List needAutoTranList = null;
			if (repeatNum == 0 || repeatNum == 1)
			{
				String complaintType1 = StaticMethod.nullObject2String(main.getComplaintType1());
				String complaintType2 = StaticMethod.nullObject2String(main.getComplaintType2());
				String complaintType = StaticMethod.nullObject2String(main.getComplaintType());
				String complaintType4 = StaticMethod.nullObject2String(main.getComplaintType4());
				String complaintType5 = StaticMethod.nullObject2String(main.getComplaintType5());
				String complaintType6 = StaticMethod.nullObject2String(main.getComplaintType6());
				String complaintType7 = StaticMethod.nullObject2String(main.getComplaintType7());
				System.out.println("lizhi:complaintType1=" + complaintType1 + "complaintType2=" + complaintType2 + "complaintType=" + complaintType + "complaintType4=" + complaintType4 + "complaintType5=" + complaintType5 + "complaintType6=" + complaintType6 + "complaintType7=" + complaintType7);
				needAutoTranList = (List)((IComplaintMainManager)getMainService()).ifneedAutotran(complaintType1, complaintType2, complaintType, complaintType4, complaintType5, complaintType6, complaintType7);
				
				if (needAutoTranList!=null&&needAutoTranList.size() > 0)
				{
					Date opeTime = new Date();
//					String prelinkId = StaticMethod.nullObject2String(link.get("id"));
					String subroleid = StaticMethod.nullObject2String(main.getSendRoleId());
					System.out.println("lyg:opeTime=" + opeTime +  "subroleid=" + subroleid);
					if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm")){
						if (!sendImmediately.equalsIgnoreCase("true")){
							//调用接口
							
							interfaceType = "replyWorkSheet";
							methodType = "replyWorkSheet";
							
							info.setInterfaceType(interfaceType);
							info.setSheetKey(sheetKey);
							info.setLinkId(linkId);
							info.setIsSended(isSended);
							info.setMainBeanId(mainBeanId);
							info.setLinkBeanId(linkBeanId);
							info.setMethodType(methodType);
							
							System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=46=befoer=="+returnType);
							returnType = operateMgr.sendData(info,linkbean);
							System.out.println("complaintsheet==="+main.getSheetId()+"====lyg:returnType=46=after=="+returnType);
							
							if(!returnType){//返回的结果是false
								text = "调用客服接口错误！";
								status = "2";
							}
						}
					}
				}
			}
		}
		
		JSONArray data = new JSONArray();
		JSONObject o = new JSONObject();
		o.put("text", text);
		data.put(o);
		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("data",data);
		jsonRoot.put("status", status);
		JSONUtil.print(response, jsonRoot.toString());
		
	}
}
