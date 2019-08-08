package com.boco.eoms.businessupport.interfaces.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.IResourceXml;
import com.boco.eoms.businessupport.interfaces.util.DataSourceUtil;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class ResourceXmlImplJl implements IResourceXml {

    private String _configPath = "classpath:com/boco/eoms/businessupport/config/resourceInterface_util.xml";

    private String getBusinessCode(String businessType) throws Exception {
        if (businessType == null)
            return "";

        String filePath = StaticMethod
                .getFilePathForUrl(_configPath);
        String nodeName = InterfaceUtilProperties.getInstance().getXmlValue(filePath, "dict.nodeName", "businessType", "nodeName", businessType);
        if (nodeName.length() > 0)
            nodeName += ".";
        return nodeName;
    }

    /**
     * 将数据转换成接口xml
     *
     * @param orderMap         定单信息map
     * @param productList<map> 产品实例列表
     * @return
     * @throws Exception
     */
    public String getXmlFromMapInit(Map orderMap, List productList) throws Exception {
        String filePath = StaticMethod.getFilePathForUrl(_configPath);
        String type = StaticMethod.nullObject2String(orderMap.get("btype1"));
        if (type.length() == 0)
            type = StaticMethod.nullObject2String(orderMap.get("businesstype1"));
        String nodeName = this.getBusinessCode(type);

        Map customerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "customer", false);
        Map customerManagerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customerManager", false);

        List pList = new ArrayList();
        List demandInfoList = new ArrayList();
        List demandInfoInnerList = new ArrayList();
        for (int i = 0; i < productList.size(); i++) {
            Map productMap = (Map) productList.get(i);
            Map map = new HashMap();
            map.putAll(orderMap);
            map.putAll(productMap);
            map.put("c_id", orderMap.get("id"));
            Map pMap = InterfaceUtilProperties.getInstance().getMapFromXml(map, filePath, nodeName + "product", false);
//			pMap.put("mainProductsType", "7");
            pList.add(pMap);
            Map dMap = InterfaceUtilProperties.getInstance().getMapFromXml(map, filePath, nodeName + "demandInfo", false);
            demandInfoList.add(dMap);
            Map diMap = InterfaceUtilProperties.getInstance().getMapFromXml(map, filePath, nodeName + "demandInfoInnerList", false);
            demandInfoInnerList.add(diMap);
        }

        Map map = new HashMap();
        map.put("customer", customerMap);
        map.put("customerManager", customerManagerMap);
        map.put("product", pList);
        map.put("demandInfo", demandInfoList);
        map.put("demandInfoInnerList", demandInfoInnerList);

        String xml = DataSourceUtil.getWellFormatXml(map);
        BocoLog.info(ResourceXmlImplFJ.class, "irms xml:" + xml);
        return xml;
    }

    public String getXmlFromMapInit(Map orderMap) throws Exception {
        String filePath = StaticMethod.getFilePathForUrl(_configPath);

        String type = StaticMethod.nullObject2String(orderMap.get("btype1"));
        if (type.length() == 0)
            type = StaticMethod.nullObject2String(orderMap.get("businesstype1"));
        String nodeName = this.getBusinessCode(type);

        Map customerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "customer", false);
        Map customerManagerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "customerManager", false);
        Map product = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "product", false);
        Map demandInfo = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "demandInfo", false);
        Map demandInfoInner = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "demandInfoInnerList", false);

        Map map = new HashMap();
        map.put("customer", customerMap);
        map.put("customerManager", customerManagerMap);
        map.put("product", product);
        map.put("demandInfo", demandInfo);
        map.put("demandInfoInnerList", demandInfoInner);

        String xml = DataSourceUtil.getWellFormatXml(map);
        BocoLog.info(ResourceXmlImplFJ.class, "irms xml:" + xml);
        return xml;
    }

    public String getXmlFromMap(Map orderMap, List productList) throws Exception {
        return this.getXmlFromMap(orderMap);
    }

    public String getXmlFromMap(Map orderMap) throws Exception {
        String filePath = StaticMethod.getFilePathForUrl(_configPath);

        String type = StaticMethod.nullObject2String(orderMap.get("btype1"));
        if (type.length() == 0)
            type = StaticMethod.nullObject2String(orderMap.get("businesstype1"));
        String nodeName = this.getBusinessCode(type);

        Map commInfoMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, nodeName + "commInfo", false);

        Map map = new HashMap();
        map.put("commInfo", commInfoMap);
        String xml = DataSourceUtil.getWellFormatXml(map);
        BocoLog.info(ResourceXmlImplFJ.class, "irms xml:" + xml);
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
//			IrmsXmlStaitc.getXmlFromMap(orderMap, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
