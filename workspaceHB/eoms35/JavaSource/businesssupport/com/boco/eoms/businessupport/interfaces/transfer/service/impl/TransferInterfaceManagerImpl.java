package com.boco.eoms.businessupport.interfaces.transfer.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.transfer.client.TransferLoader;
import com.boco.eoms.businessupport.interfaces.transfer.service.ITransferInterfaceManager;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.XmlDom4j;

public class TransferInterfaceManagerImpl implements ITransferInterfaceManager {
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
    public String dealTraphInfosResult(Map map, String orderSheetId, String mainSpecifyType, String userName, String password) throws SheetException {
        String str = "0";
        try {
            IOrderSheetManager orderSheetManager = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
            ITransferSpecialLineManager transferSpecialLineManagerImpl = (ITransferSpecialLineManager) ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
            orderSheetManager.removeSpecialLinesByOrderId(orderSheetId, mainSpecifyType);
            String result = "";
            String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, null, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "backupSheet");
            result = TransferLoader.irmsGetTraphInfos(userName, password, opDetail);
            System.out.println("irmsGetTraphInfos result:" + result);
            InterfaceUtilProperties properties = new InterfaceUtilProperties();
            XmlDom4j dom = new XmlDom4j();
            List resultList = dom.getList(result);
            if (resultList != null) {
                for (int i = 0; i < resultList.size(); i++) {
                    Map tempResultMap = (Map) resultList.get(i);
                    String filePath = StaticMethod
                            .getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml");
                    Map transferSpecialLinemap = properties.getMapFromXml(tempResultMap, filePath, "irmsGetTraphInfos");

                    TransferSpecialLine transferSpecialLine = new TransferSpecialLine();

                    SheetBeanUtils.populateMap2Bean(transferSpecialLine, transferSpecialLinemap);
                    transferSpecialLine.setOrderSheet_Id(orderSheetId);
                    transferSpecialLineManagerImpl.saveTransferSpecialLine(transferSpecialLine);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = e.getMessage();
        }
        return str;
    }
}
