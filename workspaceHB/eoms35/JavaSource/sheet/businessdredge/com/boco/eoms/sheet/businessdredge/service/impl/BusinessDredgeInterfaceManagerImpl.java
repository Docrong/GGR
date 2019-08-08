package com.boco.eoms.sheet.businessdredge.service.impl;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.crm.bo.CrmLoaderHB;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.businessdredge.service.IBusinessDredgeTaskManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class BusinessDredgeInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager {

    public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
        try {
            String nodeName = "";
            String result = "";

            String attachRef = CrmLoaderHB.createAttachRefXml(link.getNodeAccessories());

            String taskName = link.getActiveTemplateId();
            String operateType = link.getOperateType().toString();

            HashMap map = SheetBeanUtils.bean2Map(link);
            map.putAll(SheetBeanUtils.bean2Map(main));

            serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", StaticMethod.nullObject2String(map.get("businesstype1")));
            System.out.println("serviceType=" + serviceType);

            if (taskName.equals("ExcuteHumTask") && operateType.equals("4")) {// 驳回

                nodeName = "withdrawWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
                result = CrmLoaderHB.withdrawWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
                        main.getParentCorrelation(), opDetail, attachRef);

            } else if (operateType.equals("9")) {// 阶段回复
                nodeName = "notifyWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
                result = CrmLoaderHB.notifyWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
                        main.getParentCorrelation(), opDetail, attachRef);
            } else if (operateType.equals("205")) {// 回复
                nodeName = "replyWorkSheet";
                System.out.println("ParentCorrelation" + main.getParentCorrelation());
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
                result = CrmLoaderHB.replyWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
                        main.getParentCorrelation(), opDetail, attachRef);
            } else if (taskName.equals("ExcuteHumTask") && operateType.equals("61")) {// 受理
                nodeName = "confirmWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessDredge-crm.xml"), nodeName);
                result = CrmLoaderHB.confirmWorkSheet(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE, new Integer(serviceType).intValue(),
                        main.getParentCorrelation(), opDetail, attachRef);
            }
            if (result.endsWith("0"))
                return true;
            else
                return false;
        } catch (Exception err) {
            err.printStackTrace();
            BocoLog.error(this, "调用接口失败：" + err.toString());
            return false;
        }
    }

}
