// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   KnowLedgeBO.java

package com.boco.eoms.sheet.commonfault.knowledage;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.knowledge.util.KnowledgeClient;
import com.boco.eoms.knowledge.util.KnowledgeMethod;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;

import java.text.SimpleDateFormat;
import java.util.*;

import krms.KnowledgeServiceSoapBindingStub;

public class KnowLedgeBO {

    public KnowLedgeBO() {
    }

    public static String showNewknowLedage(String sheetKey, String userId)
            throws Exception {
        ICommonFaultMainManager mgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        CommonFaultMain main = (CommonFaultMain) mgr.getSingleMainPO(sheetKey);
        Map mainMap = new HashMap();
        if (main != null) {
            mainMap.put("sheetNo", main.getSheetId());
            mainMap.put("title", main.getTitle());
            mainMap.put("mainFaultResponseLevel", service.id2Name(StaticMethod.nullObject2String(main.getMainFaultResponseLevel()), "ItawSystemDictTypeDao"));
            mainMap.put("mainAlarmNum", main.getMainAlarmNum());
            mainMap.put("mainAlarmSolveDate", main.getMainAlarmSolveDate());
            mainMap.put("mainAlarmSource", main.getMainAlarmSource());
            mainMap.put("mainAlarmLogicSort", service.id2Name(StaticMethod.nullObject2String(main.getMainAlarmLogicSort()), "ItawSystemDictTypeDao"));
            mainMap.put("mainAlarmLogicSortSub", service.id2Name(StaticMethod.nullObject2String(main.getMainAlarmLogicSortSub()), "ItawSystemDictTypeDao"));
            mainMap.put("mainFaultSpecialty", service.id2Name(StaticMethod.nullObject2String(main.getMainFaultSpecialty()), "ItawSystemDictTypeDao"));
            mainMap.put("mainEquipmentFactory", service.id2Name(StaticMethod.nullObject2String(main.getMainEquipmentFactory()), "tawSystemAreaDao"));
            mainMap.put("mainEquipmentType", main.getMainEquipmentType());
            mainMap.put("mainNetName", main.getMainNetName());
            mainMap.put("mainEquipmentModel", main.getMainEquipmentModel());
            mainMap.put("mainFaultGenerantTime", main.getMainFaultGenerantTime());
            mainMap.put("mainIfAffectOperation", service.id2Name(StaticMethod.nullObject2String(main.getMainIfAffectOperation()), "ItawSystemDictTypeDao"));
            mainMap.put("mainFaultDiscoverableMode", service.id2Name(StaticMethod.nullObject2String(main.getMainFaultDiscoverableMode()), "ItawSystemDictTypeDao"));
            mainMap.put("mainNetSortOne", service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortOne()), "ItawSystemDictTypeDao"));
            mainMap.put("mainNetSortTwo", service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortTwo()), "ItawSystemDictTypeDao"));
            mainMap.put("mainNetSortThree", service.id2Name(StaticMethod.nullObject2String(main.getMainNetSortThree()), "ItawSystemDictTypeDao"));
        }
        String xml = KnowledgeMethod.getKnowledgeXml(userId, "CommonFault", sheetKey, mainMap, null);
        return KnowledgeClient.loadAddKnowledgeService().saveXmlValue(xml);
    }

    public static String searchSheet(String sheetKey, String userId)
            throws Exception {
        ICommonFaultMainManager mgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        CommonFaultMain main = (CommonFaultMain) mgr.getSingleMainPO(sheetKey);
        Map mainMap = new HashMap();
        if (main != null)
            mainMap.put("mainNetSortThree", StaticMethod.nullObject2String(main.getMainNetSortThree()));
        List list = mgr.getMainList(mainMap);
        String sheetIds = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CommonFaultMain commonFaultMain = (CommonFaultMain) list.get(i);
                sheetIds = sheetIds + commonFaultMain.getId() + ",";
            }

        }
        if (sheetIds.length() > 0)
            sheetIds = sheetIds.substring(0, sheetIds.length() - 1);
        return "";//KnowledgeClient.loadAddKnowledgeService().getKnowledgeBySheetIds(sheetIds, "CommonFault", userId);
        //return KnowledgeClient.loadAddKnowledgeService().getKnowledgeBySheetIds(sheetIds, "CommonFault", userId);
    }

    public static String showNewLeaveKnowLedage(String sheetKey, String userId)
            throws Exception {
        ICommonFaultMainManager mgr = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
        ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
        CommonFaultMain main = (CommonFaultMain) mgr.getSingleMainPO(sheetKey);
        TawSystemUser user = userManager.getUserByuserid(userId);
        Map mainMap = new HashMap();
        if (main != null) {
            mainMap.put("sheetNo", main.getSheetId());
            mainMap.put("userName", user.getUsername());
            mainMap.put("deptName", user.getDeptname());
            mainMap.put("mobile", user.getMobile());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateFormat.format(new Date());
            mainMap.put("createTime", time);
        }
        String xml = KnowledgeMethod.getKnowledgeXml(userId, "CommonFaultLeave", sheetKey, mainMap, null);
        return KnowledgeClient.loadAddKnowledgeService().saveXmlValue(xml);
    }
}
