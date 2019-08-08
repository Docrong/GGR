package com.boco.eoms.businessupport.interfaces.transfer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementMainManager;
import com.boco.eoms.util.InterfaceUtil;

/**
 * 传输电路接口服务
 *
 * @author IBM
 */
public class EomsForTransferService {
    /**
     * 推送施工信息 传输调用EOMS接口，将电路施工结果告知EOMS
     *
     * @param callerPwd
     * @param xmlSource
     * @return
     */
    public String pushConstructResult(String callerPwd, String xmlSource) {
        BocoLog.info(this, "start pushConstructResult");
        BocoLog.info(this, "xmlSource:" + xmlSource);
        String result = "";
        try {
            HashMap sheetMap = new HashMap();

            InterfaceUtil interfaceUtil = new InterfaceUtil();
            sheetMap = interfaceUtil.xmlElements(xmlSource, sheetMap, "FieldContent");

            String mainTransferNum = StaticMethod.nullObject2String(sheetMap.get("sheetId"));//电路调单号

            IBusinessImplementMainManager mgr = (IBusinessImplementMainManager) ApplicationContextHolder.getInstance().getBean("iBusinessImplementMainManager");

            List list = mgr.getMainsByCondition("mainCircuitSheetId='" + mainTransferNum + "'");
            if (list != null && list.size() > 0) {
                BusinessImplementMain main = (BusinessImplementMain) list.get(0);
                if (main == null) {
                    result = this.getResult("0", "没有找到对应的工单:" + mainTransferNum);
                } else {
                    String mainConstructionIfS = StaticMethod.nullObject2String(sheetMap.get("dealSuccess"));//接口调用是否成功     无需判断是否成功，直接保存
                    main.setMainIsCircuitComplete(mainConstructionIfS);
                    mgr.saveOrUpdateMain(main);

                    result = this.getResult("1", "");
                }
            } else {
                result = this.getResult("0", "没有找到对应的工单:" + mainTransferNum);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        BocoLog.info(this, "pushConstructResult result:" + result);
        return result;
    }

    private String getResult(String isSuccess, String errorInfo) {
        List chNameList = new ArrayList();
        List enNameList = new ArrayList();
        List contentList = new ArrayList();

        chNameList.add("接口调用是否成功");
        enNameList.add("isSuccess");
        contentList.add(isSuccess);

        chNameList.add("错误信息");
        enNameList.add("errorInfo");
        contentList.add(errorInfo);

        return CrmLoader.createOpDetailXml(chNameList, enNameList, contentList);
    }
}
