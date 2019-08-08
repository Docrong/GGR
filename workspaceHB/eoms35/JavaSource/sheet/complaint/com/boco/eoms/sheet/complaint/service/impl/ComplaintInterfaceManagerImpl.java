// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintInterfaceManagerImpl.java

package com.boco.eoms.sheet.complaint.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.complaint.model.ComplaintLink;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintTaskManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

import java.util.HashMap;

public class ComplaintInterfaceManagerImpl extends
        WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager {

    public ComplaintInterfaceManagerImpl() {
    }

    public boolean sendFlowInterfaceData(BaseMain main, BaseLink link,
                                         String interfaceType, String methodType, String serviceType) {
        try {
            String result;
            String nodeName = "";
            result = "";
            String attachRef = CrmLoader.createAttachRefXml(link
                    .getNodeAccessories());
            String taskName = link.getActiveTemplateId();
            String operateType = link.getOperateType().toString();
            HashMap map = SheetBeanUtils.bean2Map(link);
            map.putAll(SheetBeanUtils.bean2Map(main));
            System.out.println("taskName=" + taskName);
            System.out.println("operateType=" + operateType);
            System.out.println("serviceType=" + serviceType);
            if (serviceType == null || serviceType.length() == 0)
                serviceType = "0";
            if (operateType.equals("4")) {
//				IComplaintTaskManager complaintTaskManager = (IComplaintTaskManager) ApplicationContextHolder
//						.getInstance().getBean("iComplaintTaskManager");
//				ITask task = complaintTaskManager.getTaskByPreLinkid(link
//						.getId());
//				if (task.getTaskOwner().equals(main.getSendRoleId())
//						|| task.getTaskOwner().equals(main.getSendUserId())
//						|| task.getTaskOwner().equals(main.getSendDeptId())) {
                nodeName = "withdrawWorkSheet";
                String opDetail = InterfaceUtilProperties
                        .getInstance()
                        .getXmlFromMap(
                                map,
                                StaticMethod
                                        .getFilePathForUrl("classpath:config/complaint-crm.xml"),
                                nodeName);
                result = CrmLoader.withdrawWorkSheet(56, (new Integer(
                        serviceType)).intValue(), main
                        .getParentCorrelation(), opDetail, attachRef);
//				}
            } else if (operateType.equals("9")) {
                nodeName = "notifyWorkSheet";
                String opDetail = InterfaceUtilProperties
                        .getInstance()
                        .getXmlFromMap(
                                map,
                                StaticMethod
                                        .getFilePathForUrl("classpath:config/complaint-crm.xml"),
                                nodeName);
                result = CrmLoader.notifyWorkSheet(56,
                        (new Integer(serviceType)).intValue(), main
                                .getParentCorrelation(), opDetail, attachRef);
            } else if (taskName.equalsIgnoreCase("DeferExamineHumTask")
                    && operateType.equalsIgnoreCase("66")) {
                System.out.println("MainDelayFlag="
                        + StaticMethod.nullObject2String(map
                        .get("mainDelayFlag")));
                if (StaticMethod.nullObject2String(map.get("mainDelayFlag"))
                        .equals("1")) {
                    nodeName = "deferReploy";
                    String opDetail = InterfaceUtilProperties
                            .getInstance()
                            .getXmlFromMap(
                                    map,
                                    StaticMethod
                                            .getFilePathForUrl("classpath:config/complaint-crm.xml"),
                                    nodeName);
                    result = CrmLoader.notifyWorkSheet(56, (new Integer(
                            serviceType)).intValue(), main
                            .getParentCorrelation(), opDetail, attachRef);
                }
            } else if (operateType.equals("46")) {
                nodeName = "replyWorkSheet";
                String opDetail = InterfaceUtilProperties
                        .getInstance()
                        .getXmlFromMap(
                                map,
                                StaticMethod
                                        .getFilePathForUrl("classpath:config/complaint-crm.xml"),
                                nodeName);
                result = CrmLoader.replyWorkSheet(56,
                        (new Integer(serviceType)).intValue(), main
                                .getParentCorrelation(), opDetail, attachRef);
            } else if (taskName.equalsIgnoreCase("FirstExcuteHumTask")
                    && operateType.equals("61")) {
                nodeName = "confirmWorkSheet";
                String opDetail = InterfaceUtilProperties
                        .getInstance()
                        .getXmlFromMap(
                                map,
                                StaticMethod
                                        .getFilePathForUrl("classpath:config/complaint-crm.xml"),
                                nodeName);
                result = CrmLoader.confirmWorkSheet(56, (new Integer(
                                serviceType)).intValue(), main.getParentCorrelation(),
                        opDetail, attachRef);
            }
            System.out.println("result==" + result);
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

    public boolean dealUnReadyData(BaseMain main, BaseLink link,
                                   String interfaceType, String methodType, String serviceType) {
        ComplaintMain complaintMain = (ComplaintMain) main;
        ComplaintLink complaintLink = (ComplaintLink) link;
        String ifCheck = complaintMain.getMainIfCheck();
        System.out.println("getMainIfCheck:" + ifCheck);
        System.out.println("getMainCheckResult:"
                + complaintMain.getMainCheckResult());
        if (ifCheck == null || ifCheck.equals("") || ifCheck.equals("0"))
            return false;
        if ("2".equals(ifCheck))
            return true;
        if ("1030101".equals(complaintMain.getMainCheckResult()) && complaintMain.getMainLastRepeatTime() != null && complaintLink.getOperateTime() != null && complaintMain.getMainLastRepeatTime().equals(complaintLink.getOperateTime()))
            return sendFlowInterfaceData(main, link, interfaceType, methodType, serviceType);
        return "1030102".equals(complaintMain.getMainCheckResult());
    }
}
