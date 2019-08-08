package com.boco.eoms.businessupport.interfaces.service.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.util.DataSourceUtil;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class IrmsXmlStaitc {
    /**
     * 将数据转换成接口xml
     *
     * @param orderMap         定单信息map
     * @param productList<map> 产品实例列表
     * @return
     * @throws Exception
     */
    public static String getXmlFromMap(Map orderMap, List productList) throws Exception {
        String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/EomsForIrmsService/config/irms-interface-config.xml");
        Map customerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customer", false);
        Map customerManagerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customerManager", false);
        Map product = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "product", false);

        List list = new ArrayList();
        for (int i = 0; i < productList.size(); i++) {
            Map productMap = (Map) productList.get(i);
            Map demandInfoMap = InterfaceUtilProperties.getInstance().getMapFromXml(productMap, filePath, "demandInfo", false);
            list.add(demandInfoMap);
        }

        Map map = new HashMap();
        map.put("customer", customerMap);
        map.put("customerManager", customerManagerMap);
        map.put("product", product);
        map.put("demandInfo", list);

        String xml = DataSourceUtil.getWellFormatXml(map);
        BocoLog.info(IrmsXmlStaitc.class, "irms xml:" + xml);
        return xml;
    }

    public static String getXmlFromMap(Map orderMap) throws Exception {
        String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/EomsForIrmsService/config/irms-interface-config.xml");
        Map customerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customer", false);
        Map customerManagerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customerManager", false);
        Map product = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "product", false);
        Map demandInfo = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "demandInfo", false);

        Map map = new HashMap();
        map.put("customer", customerMap);
        map.put("customerManager", customerManagerMap);
        map.put("product", product);
        map.put("demandInfo", demandInfo);

        String xml = DataSourceUtil.getWellFormatXml(map);
        BocoLog.info(IrmsXmlStaitc.class, "irms xml:" + xml);
        return xml;
    }

    public static void main(String[] avg) {
        Map orderMap = new HashMap();
        orderMap.put("ndeptContact", "1");
        orderMap.put("ndeptContactPhone", "2");
        orderMap.put("customerManagerName", "3");
        orderMap.put("applyId", "44");

        Map map = new HashMap();
        map.put("gatewayType", "55");
        map.put("stationName", "66");

        List list = new ArrayList();
        list.add(map);

        try {
            IrmsXmlStaitc.getXmlFromMap(orderMap, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
