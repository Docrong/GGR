package com.boco.eoms.sheet.resourceaffirm.service.impl;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoaderHB;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.resourceaffirm.service.IResourceAffirmTaskManager;

public class ResourceAffirmInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager{

	public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
		try{
			String nodeName = "";
			String result = "";
			
			String attachRef = CrmLoaderHB.createAttachRefXml(link.getNodeAccessories());
			
			String taskName = link.getActiveTemplateId();
			String operateType = link.getOperateType().toString();
			
			HashMap map = SheetBeanUtils.bean2Map(link);
			map.putAll(SheetBeanUtils.bean2Map(main));
			
			serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType",  StaticMethod.nullObject2String(map.get("btype1")));
			System.out.println("serviceType="+serviceType);
			
			if (operateType.equals("4")) {// 驳回
//				IResourceAffirmTaskManager complaintTaskManager = (IResourceAffirmTaskManager)ApplicationContextHolder.getInstance().getBean("iresourceaffirmTaskManager");
//				ITask task = complaintTaskManager.getTaskByPreLinkid(link.getId());
//				if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
					nodeName = "withdrawWorkSheet";
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
					result = CrmLoaderHB.withdrawWorkSheet(InterfaceConstants.SERVIVETYPE_RESOURCEAFFIRM, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef);
//				}
			} else if (operateType.equals("9")) {// 阶段回复
				nodeName = "notifyWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
				result = CrmLoaderHB.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_RESOURCEAFFIRM, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
			}else if (operateType.equals("205")||operateType.equals("208")) {// 回复
				nodeName = "replyWorkSheet";	
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
				System.out.println("opDetail========="+opDetail);
				result = CrmLoaderHB.replyWorkSheet(InterfaceConstants.SERVIVETYPE_RESOURCEAFFIRM, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
			}else if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
				nodeName = "confirmWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceAffirm-crm.xml"), nodeName);
				result = CrmLoaderHB.confirmWorkSheet(InterfaceConstants.SERVIVETYPE_RESOURCEAFFIRM, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
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

}