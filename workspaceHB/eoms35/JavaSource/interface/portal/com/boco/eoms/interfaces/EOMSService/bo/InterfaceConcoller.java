package com.boco.eoms.interfaces.EOMSService.bo;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.crm.util.SheetMapingSchema;
import com.boco.eoms.sheet.base.webapp.action.NewIBaseSheet;

public class InterfaceConcoller {

    public NewIBaseSheet getService(String sheetType) throws Exception {
        String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, "");
//		beanId = "BusinessDredge";
        NewIBaseSheet mgr = (NewIBaseSheet) ApplicationContextHolder.getInstance().getBean(beanId);
        return mgr;
    }

    public void createNewSheetInstance(HashMap map, List attach) throws Exception {
        String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));

        NewIBaseSheet mgr = this.getService(sheetType);
        if (mgr == null)
            throw new Exception("未找到相应工单");
        mgr.createNewSheetInstance(map, attach);
    }
}
