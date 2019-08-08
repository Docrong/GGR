package com.boco.eoms.businessupport.interfaces.transfer.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.transfer.client.TransferLoader;
import com.boco.eoms.businessupport.interfaces.transfer.service.ITransferInterfaceManager;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.businessdredge.model.BusinessDredgeMain;
import com.boco.eoms.sheet.businessdredge.service.IBusinessDredgeMainManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.XmlDom4j;

public class TransferInterfaceManagerImplNM implements ITransferInterfaceManager {
    /**
     * 根据传输调单号得到设计完成的电路信息，并更新到业务开通系统中
     *
     * @param condition       main字段对应的map
     * @param orderSheetId    订单号
     * @param mainSpecifyType 专业类别
     * @param userName        用户名
     * @param password        密码
     * @return 成功是否的标识
     * @throws SheetException
     */
    public String dealTraphInfosResult(Map map, String sheetkey, String mainSpecifyType, String userName, String password) throws SheetException {
        String str = "0";
        try {
            IBusinessDredgeMainManager mgr = (IBusinessDredgeMainManager) ApplicationContextHolder.getInstance().getBean("iBusinessDredgeMainManager");

            String result = "";
            String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, null, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "backupSheet");
            result = TransferLoader.irmsGetTraphInfos(userName, password, opDetail);
            System.out.println("irmsGetTraphInfos result:" + result);
            XmlDom4j dom = new XmlDom4j();
            List resultList = dom.getList(result);
            if (resultList != null) {
                for (int i = 0; i < resultList.size(); i++) {
                    Map tempResultMap = (Map) resultList.get(i);
                    String isSuccess = StaticMethod.nullObject2String(tempResultMap.get("isSuccess"));
                    if (!isSuccess.equals("0")) {
                        String traphName = StaticMethod.nullObject2String(tempResultMap.get("traphName"));
                        String traphCuid = StaticMethod.nullObject2String(tempResultMap.get("traphCuid"));

                        BusinessDredgeMain main = (BusinessDredgeMain) mgr.getSingleMainPO(sheetkey);
                        if (main != null) {
//							main.setMainCircuitCode(traphCuid);
                            mgr.saveOrUpdateMain(main);
                        } else {
                            System.out.println(" 没找到电路名称：" + traphName + " 对应的电路信息");
                        }
                    } else {
                        String errorInfo = StaticMethod.nullObject2String(tempResultMap.get("errorInfo"));
                        throw new Exception("调用传输接口失败：" + errorInfo);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = e.getMessage();
        }
        return str;
    }
}
