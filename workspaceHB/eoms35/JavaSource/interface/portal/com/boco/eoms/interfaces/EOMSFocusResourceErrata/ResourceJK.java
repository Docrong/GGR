package com.boco.eoms.interfaces.EOMSFocusResourceErrata;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.util.InterfaceUtil;

public class ResourceJK {

    /**
     * 工单派发
     *
     * @param sheetType
     * @param serviceType
     * @param serSupplier
     * @param serCaller
     * @param callerPwd
     * @param callTime
     * @param opDetail    xml
     * @return
     */
    public String newCorrectSheet(String serSupplier, String serCaller, String callerPwd, String callTime, String opDetail) {
        System.out.println("crm工单派发");
//		System.out.println("sheetType="+sheetType);
//		System.out.println("serviceType="+serviceType);
        String result = "";
        String sheetType = "";
        String serviceType = "";

        sheetType = "101";
        serviceType = "1";
        System.out.println("serSupplier=" + serSupplier);
        System.out.println("serCaller=" + serCaller);
        System.out.println("callerPwd=" + callerPwd);
        System.out.println("callTime=" + callTime);
        System.out.println("opDetail=" + opDetail);

        HashMap sheetMap = new HashMap();
        sheetMap.put("sheetType", sheetType);
        sheetMap.put("serviceType", serviceType);
        sheetMap.put("serSupplier", serSupplier);
        sheetMap.put("serCaller", serCaller);
        sheetMap.put("callerPwd", callerPwd);
        sheetMap.put("callTime", callTime);

        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
            EomsLoader loader = new EomsLoader();
            String sheetId = loader.newWorkSheet(sheetMap, null);
            result = "sheetNo=" + sheetId + ";errList=";
        } catch (Exception e) {
            e.printStackTrace();
            result = "sheetNo=;errList=新增勘误工单报错" + e.getMessage();
            System.out.println("newCorrectSheet is " + result);
        }

        return result;
    }
}