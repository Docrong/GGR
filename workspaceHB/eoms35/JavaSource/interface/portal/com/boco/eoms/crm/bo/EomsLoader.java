// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   EomsLoader.java

package com.boco.eoms.crm.bo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.crm.util.SheetMapingSchema;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;

import java.util.HashMap;
import java.util.List;

public class EomsLoader {

    public EomsLoader() {
    }

    public IInterfaceServiceManage getCrmService(String sheetType, String serviceType)
            throws Exception {
        String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
        System.out.println("beanId=" + beanId);
        IInterfaceServiceManage mgr = (IInterfaceServiceManage) ApplicationContextHolder.getInstance().getBean(beanId);
        return mgr;
    }

    public String newWorkSheet(HashMap map, List attach)
            throws Exception {
        String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
        String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
        System.out.println("sheetType=" + sheetType + "serviceType=" + serviceType);
        IInterfaceServiceManage mgr = getCrmService(sheetType, serviceType);
        if (mgr == null)
            throw new Exception("未找到相应工单");
        else
            return mgr.newWorkSheet(map, attach);
    }

    public String renewWorkSheet(HashMap map, List attach)
            throws Exception {
        String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
        String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
        IInterfaceServiceManage mgr = getCrmService(sheetType, serviceType);
        if (mgr == null)
            throw new Exception("未找到相应工单");
        else
            return mgr.renewWorkSheet(map, attach);
    }

    public String suggestWorkSheet(HashMap map, List attach)
            throws Exception {
        String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
        String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
        IInterfaceServiceManage mgr = getCrmService(sheetType, serviceType);
        if (mgr == null)
            throw new Exception("未找到相应工单");
        else
            return mgr.suggestWorkSheet(map, attach);
    }

    public String untreadWorkSheet(HashMap map, List attach)
            throws Exception {
        String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
        String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
        IInterfaceServiceManage mgr = getCrmService(sheetType, serviceType);
        if (mgr == null)
            throw new Exception("未找到相应工单");
        else
            return mgr.untreadWorkSheet(map, attach);
    }

    public String checkinWorkSheet(HashMap map, List attach)
            throws Exception {
        String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
        String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
        IInterfaceServiceManage mgr = getCrmService(sheetType, serviceType);
        if (mgr == null)
            throw new Exception("未找到相应工单");
        else
            return mgr.checkinWorkSheet(map, attach);
    }
}
