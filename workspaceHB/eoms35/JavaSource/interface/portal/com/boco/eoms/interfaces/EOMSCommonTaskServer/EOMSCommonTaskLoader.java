package com.boco.eoms.interfaces.EOMSCommonTaskServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.commontask.service.impl.CommonTaskCrmServiceManageImpl;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;

public class EOMSCommonTaskLoader {
    public static final String WORKSHEET_REPLY = "replyWorkSheet";

    public String replyWorkSheet(HashMap interfaceMap, List attach) throws Exception {
        System.out.println("进入replyWorkSheet()");
        CommonTaskCrmServiceManageImpl ct = new CommonTaskCrmServiceManageImpl();
        Map map = ct.initMap(interfaceMap, attach, EOMSCommonTaskLoader.WORKSHEET_REPLY);
        if (map.get("phaseId") == null)
            throw new Exception("phaseId为空");
        IMainService iMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(ct.getMainBeanId());

        map = ct.setBaseMap(map);
        String sheetNo = StaticMethod.nullObject2String(map.get("sheetId"));
        System.out.println("serialNo=" + sheetNo);
        BaseMain main = iMainService.getMainBySheetId(sheetNo);
        if (main != null) {
            map.put("id", main.getId());
            map.put("operateUserId", main.getSendUserId());
            return ct.dealSheet(main, map, attach);
        } else {
            throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
        }
    }
}
