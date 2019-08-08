package com.boco.eoms.interfaces.EOMSService;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.interfaces.EOMSService.bo.InterfaceConcoller;
import com.boco.eoms.interfaces.EOMSService.util.IcrmUtil;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.circuitdispatch.service.ICircuitDispatchMainManager;
import com.boco.eoms.util.InterfaceUtil;

public class EOMSService {
    /**
     * 保存网元选择结果
     *
     * @param serSupplier
     * @param serCaller
     * @param callerPwd
     * @param callTime
     * @param opDetail
     * @return
     */
    public String setCellInfo(String serSupplier, String serCaller, String callerPwd, String callTime, String opDetail) {
        System.out.println("保存网元选择结果 setCellInfo");
        System.out.println("serCaller=" + serCaller);
        System.out.println("opDetail=" + opDetail);

        HashMap sheetMap = new HashMap();
        sheetMap.put("serSupplier", serSupplier);
        sheetMap.put("serCaller", serCaller);
        sheetMap.put("callerPwd", callerPwd);
        sheetMap.put("callTime", callTime);


        String result = "1";
        String sheetId = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
            sheetId = StaticMethod.nullObject2String(sheetMap.get("sheetId"));
            String cellInfo = StaticMethod.nullObject2String(sheetMap.get("cellInfo"));

            IcrmUtil icrmUtil = new IcrmUtil();
            icrmUtil.xmlElements(cellInfo, "//cellDetail/cellInfo", "cellId", "cellName");

//			ICircuitDispatchMainManager iCircuitDispatchMainManager = (ICircuitDispatchMainManager)ApplicationContextHolder.getInstance().getBean("iCircuitDispatchMainManager");
//			iCircuitDispatchMainManager.saveCellInfo(sheetId, cellInfo);
            result = "0";

        } catch (Exception err) {
            err.printStackTrace();
            result = err.getMessage();
        }
        System.out.println("setCellInfo " + sheetId + ":" + result);
        return result;
    }

    /**
     * 保存方案号
     *
     * @param serSupplier
     * @param serCaller
     * @param callerPwd
     * @param callTime
     * @param opDetail
     * @return
     */
    public String saveDesignId(String serSupplier, String serCaller, String callerPwd, String callTime, String opDetail) {
        System.out.println("保存方案号 saveDesignId");
        System.out.println("serCaller=" + serCaller);
        System.out.println("opDetail=" + opDetail);

        HashMap sheetMap = new HashMap();
        sheetMap.put("serSupplier", serSupplier);
        sheetMap.put("serCaller", serCaller);
        sheetMap.put("callerPwd", callerPwd);
        sheetMap.put("callTime", callTime);


        String result = "1";
        String sheetId = "";
        try {
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
            sheetId = StaticMethod.nullObject2String(sheetMap.get("sheetId"));
            String designId = StaticMethod.nullObject2String(sheetMap.get("designId"));

            ICircuitDispatchMainManager iCircuitDispatchMainManager = (ICircuitDispatchMainManager) ApplicationContextHolder.getInstance().getBean("iCircuitDispatchMainManager");
            iCircuitDispatchMainManager.saveDesignId(sheetId, designId);
            result = "0";

        } catch (Exception err) {
            err.printStackTrace();
            result = err.getMessage();
        }
        System.out.println("saveDesignId " + sheetId + ":" + result);
        return result;
    }

    /**
     * 驳回工单(施工驳回设计)
     *
     * @param serSupplier
     * @param serCaller
     * @param callerPwd
     * @param callTime
     * @param opDetail
     * @return
     */
    public String rejectSheet(String serSupplier, String serCaller, String callerPwd, String callTime, String opDetail) {
        BocoLog.info(this, "EOMSService rejectSheet");
        BocoLog.info(this, "serSupplier" + serSupplier);
        BocoLog.info(this, "serCaller" + serCaller);
        BocoLog.info(this, "callerPwd" + callerPwd);
        BocoLog.info(this, "callTime" + callTime);
        BocoLog.info(this, "opDetail" + opDetail);

        String result = "";
        try {
            HashMap sheetMap = new HashMap();
            sheetMap.put("serSupplier", serSupplier);
            sheetMap.put("serCaller", serCaller);
            sheetMap.put("callerPwd", callerPwd);
            sheetMap.put("callTime", callTime);
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

            sheetMap.put("sheetType", "42");
            sheetMap.put("operateType", "4");

            InterfaceConcoller concoller = new InterfaceConcoller();
            concoller.createNewSheetInstance(sheetMap, null);

            result = "0";

        } catch (Exception err) {
            err.printStackTrace();
            result = err.getMessage();
        }
        BocoLog.info(this, "EOMSNewSheetService newWorkSheet result=" + result);
        return result;
    }

    /**
     * 提交工单（设计重新派发）
     *
     * @param serSupplier
     * @param serCaller
     * @param callerPwd
     * @param callTime
     * @param opDetail
     * @return
     */
    public String submitSheet(String serSupplier, String serCaller, String callerPwd, String callTime, String opDetail) {
        BocoLog.info(this, "EOMSService rejectSheet");
        BocoLog.info(this, "serSupplier" + serSupplier);
        BocoLog.info(this, "serCaller" + serCaller);
        BocoLog.info(this, "callerPwd" + callerPwd);
        BocoLog.info(this, "callTime" + callTime);
        BocoLog.info(this, "opDetail" + opDetail);

        String result = "";
        try {
            HashMap sheetMap = new HashMap();
            sheetMap.put("serSupplier", serSupplier);
            sheetMap.put("serCaller", serCaller);
            sheetMap.put("callerPwd", callerPwd);
            sheetMap.put("callTime", callTime);
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

            sheetMap.put("sheetType", "42");
            sheetMap.put("operateType", "110");

            InterfaceConcoller concoller = new InterfaceConcoller();
            concoller.createNewSheetInstance(sheetMap, null);

            result = "0";

        } catch (Exception err) {
            err.printStackTrace();
            result = err.getMessage();
        }
        BocoLog.info(this, "EOMSNewSheetService newWorkSheet result=" + result);
        return result;
    }

    public static void main(String[] avg) {
        EOMSService s = new EOMSService();
        String xml = "<opDetail><recordInfo><fieldInfo><fieldChName>sheetId</fieldChName><fieldEnName>sheetId</fieldEnName><fieldContent>SC-042-090429-10001</fieldContent></fieldInfo>" +
                "<fieldInfo><fieldChName>taskId</fieldChName><fieldEnName>taskId</fieldEnName><fieldContent>_TKI:a01b0120.ef95fb27.76fddff5.c66f03a3</fieldContent></fieldInfo>" +
                "</recordInfo></opDetail>";
        s.submitSheet("", "", "", "", xml);
    }

}
