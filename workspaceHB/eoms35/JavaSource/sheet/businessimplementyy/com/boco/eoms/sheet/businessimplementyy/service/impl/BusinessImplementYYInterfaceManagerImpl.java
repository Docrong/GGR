// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-4-6 21:04:36
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BusinessImplementInterfaceManagerImpl.java

package com.boco.eoms.sheet.businessimplementyy.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.crm.util.RecordUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain;
import com.boco.eoms.sheet.businessimplementyy.service.IBusinessImplementYYLinkManager;
import com.boco.eoms.sheet.businessimplementyy.service.IBusinessImplementYYTaskManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

import java.util.*;

public class BusinessImplementYYInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract
        implements IWfInterfaceOperateManager {

    public BusinessImplementYYInterfaceManagerImpl() {
    }

    public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
        try {
            String nodeName = "";
            String result = "";
            String attachRef = RecordUtil.createAttachRefXml(link.getNodeAccessories());
            String taskName = link.getActiveTemplateId();
            String operateType = link.getOperateType().toString();
            HashMap map = SheetBeanUtils.bean2Map(link);
            map.putAll(SheetBeanUtils.bean2Map(main));
            serviceType = InterfaceUtilProperties.getInstance().getInterfaceCodeByDictId("serviceType", StaticMethod.nullObject2String(map.get("mainSpecifyType")));
            System.out.println("serviceType=" + serviceType);
            if (serviceType.equalsIgnoreCase(""))
                serviceType = "4";

            map.put("sheetType", String.valueOf(InterfaceConstants.SERVIVETYPE_BUSINESSDREDGE));
            map.put("serviceType", serviceType);

            int tempType = 320;

            if (operateType.equals("4")) {
                IBusinessImplementYYTaskManager complaintTaskManager = (IBusinessImplementYYTaskManager) ApplicationContextHolder.getInstance().getBean("iBusinessImplementYYTaskManager");
                ITask task = complaintTaskManager.getTaskByPreLinkid(link.getId());
                if (task.getTaskOwner().equals(main.getSendRoleId()) || task.getTaskOwner().equals(main.getSendUserId()) || task.getTaskOwner().equals(main.getSendDeptId())) {
                    nodeName = "withdrawWorkSheet";
                    String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessimplementyy-crm.xml"), nodeName);
//	                result = CrmLoader.withdrawWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef,link.getOperateUserId());
//	                result = CrmLoader.withdrawWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef );
                }
            }
            if (operateType.equals("9")) {
                nodeName = "notifyWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessimplementyy-crm.xml"), nodeName);
//	            result = CrmLoader.notifyWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef,link.getOperateUserId());
                result = CrmLoader.notifyWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef);
            } else if (operateType.equals("77")) {
                IBusinessImplementYYLinkManager linkmgr = (IBusinessImplementYYLinkManager) ApplicationContextHolder.getInstance().getBean("iBusinessImplementYYLinkManager");
                List linkList = linkmgr.getLinksByMainId(main.getId());
                if (linkList != null) {
                    for (int i = 0; i < linkList.size(); i++) {
                        BaseLink blink = (BaseLink) linkList.get(i);
                        if (!blink.getOperateType().toString().equals("61")) {
                            HashMap linkMap = SheetBeanUtils.bean2Map(blink);
                            map = joinMap(map, linkMap);
                        }
                    }

                }
                BusinessImplementYYMain businessimplementmain = (BusinessImplementYYMain) main;
                String orderid = businessimplementmain.getOrderSheetId();
                List list = OrderSheetMethod.getSpecialLineList(orderid, Constants._LANGUAGE_LINE);
                nodeName = "replyWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, list, StaticMethod.getFilePathForUrl("classpath:config/businessimplementyy-crm.xml"), nodeName);
//	            result = CrmLoader.replyWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef,link.getOperateUserId());
                result = CrmLoader.replyWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef);
            }
            if (operateType.equals("61")) {
                nodeName = "confirmWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/businessimplementyy-crm.xml"), nodeName);
//	            result = CrmLoader.confirmWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef,link.getOperateUserId());
                result = CrmLoader.confirmWorkSheet(tempType, (new Integer(serviceType)).intValue(), main.getParentCorrelation(), opDetail, attachRef);
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

    private HashMap joinMap(HashMap map, HashMap childMap) {
        Set set = childMap.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = StaticMethod.nullObject2String(it.next());
            Object obj = childMap.get(key);
            if (obj != null && !obj.toString().equals(""))
                map.put(key, obj);
        }

        return map;
    }
}