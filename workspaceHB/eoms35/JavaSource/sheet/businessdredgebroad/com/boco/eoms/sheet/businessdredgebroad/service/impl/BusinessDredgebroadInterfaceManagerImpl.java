package com.boco.eoms.sheet.businessdredgebroad.service.impl;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.businessdredgebroad.service.IBusinessDredgebroadTaskManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class BusinessDredgebroadInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager{

	public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
		try{
			String nodeName = "";
			String result = "";
			
			String attachRef = CrmLoader.createAttachRefXml(link.getNodeAccessories());
			
			String taskName = link.getActiveTemplateId();
			String operateType = link.getOperateType().toString();
			
			HashMap map = SheetBeanUtils.bean2Map(link);
			map.putAll(SheetBeanUtils.bean2Map(main));
            //alter by yangna
			String zxType =  StaticMethod.nullObject2String(map.get("zxType"));
			System.out.println("zxType="+zxType);
			if(!"".equals(zxType)){
				serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType2",  zxType);
			}else{
				serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType1",  StaticMethod.nullObject2String(map.get("businesstype1")));
			}
			System.out.println("serviceType="+serviceType);
            //end by yangna
			//serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", StaticMethod.nullObject2String(map.get("businesstype1")));
			//System.out.println("serviceType="+serviceType);
			
			if (operateType.equals("4")) {// 驳回
				IBusinessDredgebroadTaskManager complaintTaskManager = (IBusinessDredgebroadTaskManager)ApplicationContextHolder.getInstance().getBean("iBusinessDredgebroadTaskManager");
				ITask task = complaintTaskManager.getTaskByPreLinkid(link.getId());
				if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
					nodeName = "withdrawWorkSheet";
					String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredgebroad-crm.xml"), nodeName);
					result = CrmLoader.withdrawWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
							main.getParentCorrelation(), opDetail,attachRef);
				}
			} else if (operateType.equals("9")) {// 阶段回复
				nodeName = "notifyWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredgebroad-crm.xml"), nodeName);
				result = CrmLoader.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
			}else if (operateType.equals("205")) {// 回复
				nodeName = "replyWorkSheet";
				System.out.println("ParentCorrelation:"+main.getParentCorrelation());
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredgebroad-crm.xml"), nodeName);
				result = CrmLoader.replyWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
						main.getParentCorrelation(), opDetail,attachRef);
			}else if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
				nodeName = "confirmWorkSheet";
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredgebroad-crm.xml"), nodeName);
				result = CrmLoader.confirmWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
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
