package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;

public class CommonFaultInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager {

    public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
        boolean result = false;
        if (interfaceType.equalsIgnoreCase("InterSwitchAlarm")) {//告警接口
            String status = "";
            String taskName = link.getActiveTemplateId();
            if (link == null || (taskName.equals("") && link.getOperateRoleId().equals(link.getToOrgRoleId()))) {
                status = "草稿";
            } else {
                Integer operateType = link.getOperateType();
                if (taskName.equals("") && !link.getOperateRoleId().equals(link.getToOrgRoleId()))
                    status = "待受理";
                else if (taskName.equalsIgnoreCase("FirstExcuteHumTask") && operateType == new Integer(1))
                    status = "处理中";
                else if (operateType == new Integer(46))
                    status = "处理完成";
                else if (operateType == new Integer(18))
                    status = "已归档";
            }
            try {
                String limit = "";
                if (main.getSheetCompleteLimit() != null)
                    limit = StaticMethod.date2String(main.getSheetCompleteLimit());
                CommonFaultBO.sendAlarmInfo(((CommonFaultMain) main).getMainAlarmNum(), main.getSheetId(), status, StaticMethod.nullObject2String(((CommonFaultMain) main).getMainIfAlarmSend()), limit);
                result = true;
            } catch (Exception err) {
                result = false;
            }
        }
        return result;
    }

}
