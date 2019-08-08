package com.boco.eoms.sheet.resourceconfirm.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoaderHB;
import com.boco.eoms.crm.util.RecordUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmLinkManager;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmTaskManager;

public class ResourceConfirmInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager {

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

            map.put("sheetType", String.valueOf(InterfaceConstants.SERVIVETYPE_RESOURCEAFFIRM));
            map.put("serviceType", serviceType);

            int tempType = 31;

            if (operateType.equals("4")) {// 驳回
                IResourceConfirmTaskManager complaintTaskManager = (IResourceConfirmTaskManager) ApplicationContextHolder.getInstance().getBean("iResourceConfirmTaskManager");
                ITask task = complaintTaskManager.getTaskByPreLinkid(link.getId());
                if (task.getTaskOwner().equals(main.getSendRoleId()) || task.getTaskOwner().equals(main.getSendUserId()) || task.getTaskOwner().equals(main.getSendDeptId())) {
                    nodeName = "withdrawWorkSheet";
                    String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml"), nodeName);
//					result = CrmLoaderHB.withdrawWorkSheet(tempType, new Integer(serviceType).intValue(),main.getParentCorrelation(), opDetail,attachRef,link.getOperateUserId());
                    result = CrmLoaderHB.withdrawWorkSheet(tempType, new Integer(serviceType).intValue(), main.getParentCorrelation(), opDetail, attachRef);
                }
            } else if (operateType.equals("9")) {// 阶段回复
                nodeName = "notifyWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml"), nodeName);
//				result = CrmLoaderHB.notifyWorkSheet(tempType, new Integer(serviceType).intValue(),main.getParentCorrelation(), opDetail,attachRef,link.getOperateUserId());
                result = CrmLoaderHB.notifyWorkSheet(tempType, new Integer(serviceType).intValue(), main.getParentCorrelation(), opDetail, attachRef);
            } else if (operateType.equals("2091") || operateType.equals("2015")) {// 回复
                IResourceConfirmLinkManager linkmgr = (IResourceConfirmLinkManager) ApplicationContextHolder.getInstance().getBean("iResourceConfirmLinkManager");
                List linkList = linkmgr.getLinksByMainId(main.getId());
                if (linkList != null) {
                    for (int i = 0; i < linkList.size(); i++) {
                        BaseLink blink = (BaseLink) linkList.get(i);
                        if (!blink.getOperateType().toString().equals("61")) {
                            HashMap linkMap = SheetBeanUtils.bean2Map(blink);
                            map = this.joinMap(map, linkMap);
                        }
                    }
                }
                ResourceConfirmMain resourceconfirmmain = (ResourceConfirmMain) main;
                String orderid = resourceconfirmmain.getOrderSheetId();
                String specialtyType = resourceconfirmmain.getMainSpecifyType();
                List list = OrderSheetMethod.getSpecialLineList(orderid, specialtyType);
				
				/*if(link.getNodeAccessories()==null||link.getNodeAccessories().equals("")){
					IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
					OrderSheet os = mgr.getOrderSheet(orderid);
					attachRef = RecordUtil.createAttachRefXml(os.getAccessories());
				}*/

                nodeName = "replyWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, list, StaticMethod.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml"), nodeName);
//				result = CrmLoaderHB.replyWorkSheet(tempType, new Integer(serviceType).intValue(),main.getParentCorrelation(), opDetail,attachRef,link.getOperateUserId());
                result = CrmLoaderHB.replyWorkSheet(tempType, new Integer(serviceType).intValue(), main.getParentCorrelation(), opDetail, attachRef);
            } else if (taskName.equals("AcceptTask") && operateType.equals("61")) {// 受理
                nodeName = "confirmWorkSheet";
                String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml"), nodeName);
//				result = CrmLoaderHB.confirmWorkSheet(tempType, new Integer(serviceType).intValue(),main.getParentCorrelation(), opDetail,attachRef,link.getOperateUserId());
                result = CrmLoaderHB.confirmWorkSheet(tempType, new Integer(serviceType).intValue(), main.getParentCorrelation(), opDetail, attachRef);
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
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = StaticMethod.nullObject2String(it.next());
            Object obj = childMap.get(key);
            if (obj != null && !obj.toString().equals(""))
                map.put(key, obj);
        }
        return map;
    }

}
