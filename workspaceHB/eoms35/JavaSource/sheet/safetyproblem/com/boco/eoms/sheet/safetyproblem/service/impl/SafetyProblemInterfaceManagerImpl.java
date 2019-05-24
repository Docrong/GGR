package com.boco.eoms.sheet.safetyproblem.service.impl;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.complaint.service.IComplaintTaskManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class SafetyProblemInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager{

	public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
		try{
			String nodeName = "";
			String result = "";

			String attachRef = CrmLoader.createAttachRefXml(link.getNodeAccessories());
			
			String taskName = link.getActiveTemplateId();
			String operateType = link.getOperateType().toString();
			
			HashMap map = SheetBeanUtils.bean2Map(link);
			map.putAll(SheetBeanUtils.bean2Map(main));
			
			System.out.println("taskName="+taskName);
			System.out.println("operateType="+operateType);
			if(serviceType == null || serviceType.length() == 0)
	            serviceType = "0";
			if (operateType.equals("4")) {// 驳回
				IComplaintTaskManager complaintTaskManager = (IComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
				ITask task = complaintTaskManager.getTaskByPreLinkid(link.getId());
				if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
					nodeName = "withdrawWorkSheet";
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/complaint-crm.xml"), nodeName);
					result = CrmLoader.withdrawWorkSheet(InterfaceConstants.SERVIVETYPE_COMPLAINT, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef);
				}
			} else if (operateType.equals("9")) {// 阶段回复
				nodeName = "notifyWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/complaint-crm.xml"), nodeName);
				result = CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_COMPLAINT, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
			}else if(taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66")){//同意延期{
				System.out.println("MainDelayFlag="+StaticMethod.nullObject2String(map.get("mainDelayFlag")));
				if(StaticMethod.nullObject2String(map.get("mainDelayFlag")).equals("1")){
					nodeName = "deferReploy";
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/complaint-crm.xml"), nodeName);
					result = CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_COMPLAINT, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef);
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
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/complaint-crm.xml"), nodeName);
					result = CrmLoader.replyWorkSheet(InterfaceConstants.SERVIVETYPE_COMPLAINT, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef);

			} else if (taskName.equalsIgnoreCase("FirstExcuteHumTask") && operateType.equals("61")) {// 受理
				nodeName = "confirmWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/complaint-crm.xml"), nodeName);
				result = CrmLoader.confirmWorkSheet(InterfaceConstants.SERVIVETYPE_COMPLAINT, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
			}
//			String result = CrmWaitInfoBo.getInstance().sendInfo(info);
			
			
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

}
