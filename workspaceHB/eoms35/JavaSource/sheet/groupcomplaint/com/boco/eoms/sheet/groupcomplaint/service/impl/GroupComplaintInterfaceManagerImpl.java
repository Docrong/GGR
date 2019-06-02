package com.boco.eoms.sheet.groupcomplaint.service.impl;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintTaskManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class GroupComplaintInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager{

	public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
		try{
			String nodeName = "";
			String result = "";
			String attachRef = CrmLoader.createAttachRefXml(link.getNodeAccessories());
			
			String taskName = link.getActiveTemplateId();
			String operateType = link.getOperateType().toString();
			System.out.println("taskName="+taskName);
			System.out.println("operateType="+operateType);
			
			HashMap map = SheetBeanUtils.bean2Map(link);
			map.putAll(SheetBeanUtils.bean2Map(main));
			
			System.out.println("serviceType="+serviceType);
			if(serviceType==null || serviceType.length()==0){
				serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", StaticMethod.nullObject2String(map.get("bservType")));
			}
			if(serviceType==null || serviceType.length()==0){
				serviceType = "0";
			}
			System.out.println("serviceType="+serviceType);
			
			if (operateType.equals("4")) {// 驳回
//				IGroupComplaintTaskManager complaintTaskManager = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
//				ITask task = complaintTaskManager.getTaskByPreLinkid(link.getId());
//				if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
					nodeName = "withdrawWorkSheet";
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/groupComplaint-crm.xml"), nodeName);
					result = CrmLoader.withdrawWorkSheet(InterfaceConstants.SERVIVETYPE_GROUPCOMPLAINT, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef, link.getOperateUserId());
//				}
			} else if (operateType.equals("9")) {// 阶段回复
				nodeName = "notifyWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/groupComplaint-crm.xml"), nodeName);
				result = CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_GROUPCOMPLAINT, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef, link.getOperateUserId());
			}else if(taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66")){//同意延期{
				if(StaticMethod.nullObject2String(map.get("mainDelayFlag")).equals("1")){
					nodeName = "deferReploy";
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/groupComplaint-crm.xml"), nodeName);
					result = CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_GROUPCOMPLAINT, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef, link.getOperateUserId());
				}
			}else if (operateType.equals("46")) {// 回复
	//			String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
	//			ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgeTaskManager");
	//			ITask task = iTaskService.getSinglePO(taskId);
	//			if(task!=null&&task.getSubTaskFlag()!=null){//是子任务
	//				info.setReturnType("1");
	//			}else{
//					info.setReturnType("0");							
	//			}	
				nodeName = "replyWorkSheet";	
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/groupComplaint-crm.xml"), nodeName);
				result = CrmLoader.replyWorkSheet(InterfaceConstants.SERVIVETYPE_GROUPCOMPLAINT, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef, link.getOperateUserId());
			}else if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61")) {// 受理
				nodeName = "confirmWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/groupComplaint-crm.xml"), nodeName);
				result = CrmLoader.confirmWorkSheet(InterfaceConstants.SERVIVETYPE_GROUPCOMPLAINT, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef, link.getOperateUserId());
			}
			if(result.endsWith("0"))
				return true;
			else
				return false;

		}catch(Exception err){
			err.printStackTrace();
			BocoLog.error(this, "调用接口失败：" + err.toString());
			return false;
		}
	}

	public boolean dealUnReadyData(BaseMain main, BaseLink link,String interfaceType,String methodType,String serviceType){
		GroupComplaintMain complaintMain = (GroupComplaintMain)main;
        String ifCheck = complaintMain.getMainIfCheck();
        System.out.println("getMainIfCheck:" + ifCheck);
        System.out.println("getMainCheckResult:" + complaintMain.getMainCheckResult());
        if(ifCheck == null || ifCheck.equals("") || ifCheck.equals("0"))
            return false;
        if("2".equals(ifCheck))
            return true;
        if("1030101".equals(complaintMain.getMainCheckResult()))
            return sendFlowInterfaceData(main, link, interfaceType, methodType, serviceType);
        return !"1030102".equals(complaintMain.getMainCheckResult()) ? false : false;
	}
}
