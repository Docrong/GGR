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

public class ResourceXmlImplFJ implements IResourceXml{
	/**
	 * 将数据转换成接口xml
	 * @param orderMap 定单信息map
	 * @param productList<map> 产品实例列表
	 * @return
	 * @throws Exception
	 */
	public String getXmlFromMapInit(Map orderMap,List productList) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/resourceInterface_util.xml");
		Map customerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customer", false);
		Map customerManagerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customerManager", false);
		
		List pList = new ArrayList();
		List demandInfoList = new ArrayList();
		List demandInfoInnerList = new ArrayList();
		String productType = "";
		for(int i=0;i<productList.size();i++){
			Map productMap = (Map)productList.get(i);
			Map map = new HashMap();
			map.putAll(orderMap);
			map.putAll(productMap);
			map.put("c_id", orderMap.get("id"));
			map.put("customerManagerCode", orderMap.get("mainGroupNumber"));
			Map pMap = InterfaceUtilProperties.getInstance().getMapFromXml(map, filePath, "product", false);
			InterfaceUtilProperties interfaceutilproperties = new InterfaceUtilProperties();
			productType = interfaceutilproperties.getInterfaceCodeByDictId("productType", (String)pMap.get("productType"));
			pMap.put("productType", productType);
//			pMap.put("mainProductsType", "7");
			pList.add(pMap);
			Map dMap = InterfaceUtilProperties.getInstance().getMapFromXml(map, filePath, "demandInfo", false);
			demandInfoList.add(dMap);
			Map diMap = InterfaceUtilProperties.getInstance().getMapFromXml(map, filePath, "demandInfoInnerList", false);
			demandInfoInnerList.add(diMap);
		}

		Map map = new HashMap();
		map.put("customer", customerMap);
		map.put("customerManager", customerManagerMap);
		map.put("product", pList);
		map.put("demandInfo", demandInfoList);
		map.put("demandInfoInnerList", demandInfoInnerList);
		
		String xml = DataSourceUtil.getWellFormatXml(map);
		BocoLog.info(ResourceXmlImplFJ.class, "irms xml:"+xml);
		return xml;
	}
	public String getXmlFromMapInit(Map orderMap) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/resourceInterface_util.xml");
		Map customerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customer", false);
		Map customerManagerMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "customerManager", false);
		Map product = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "product", false);
		Map demandInfo = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "demandInfo", false);
		Map demandInfoInner = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "demandInfoInnerList", false);
		
		Map map = new HashMap();
		map.put("customer", customerMap);
		map.put("customerManager", customerManagerMap);
		map.put("product", product);
		map.put("demandInfo", demandInfo);
		map.put("demandInfoInnerList", demandInfoInner);
		
		String xml = DataSourceUtil.getWellFormatXml(map);
		BocoLog.info(ResourceXmlImplFJ.class, "irms xml:"+xml);
		return xml;
	}
	public String getXmlFromMap(Map orderMap,List productList) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/resourceInterface_util.xml");
		List list = new ArrayList();
		for(int i=0;i<productList.size();i++){
			Map tempMap = new HashMap();
			tempMap.putAll(orderMap);

			Map productMap = (Map)productList.get(i);
			tempMap.putAll(productMap);
			
			System.out.println(tempMap.get("serviceType"));

			Map commInfoMap = InterfaceUtilProperties.getInstance().getMapFromXml(tempMap, filePath, "commInfo", false);
			list.add(commInfoMap);
		}
		Map map = new HashMap();
		map.put("commInfo", list);
		String xml = DataSourceUtil.getWellFormatXml(map);
		BocoLog.info(ResourceXmlImplFJ.class, "irms xml:"+xml);
		return xml;
	}
	public String getXmlFromMap(Map orderMap) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/resourceInterface_util.xml");
		Map commInfoMap = InterfaceUtilProperties.getInstance().getMapFromXml(orderMap, filePath, "commInfo", false);
		
		Map map = new HashMap();
		map.put("commInfo", commInfoMap);
		String xml = DataSourceUtil.getWellFormatXml(map);
		BocoLog.info(ResourceXmlImplFJ.class, "irms xml:"+xml);
		return xml;
	}
	
	public static void main(String[] avg){
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
		
		try{
//			IrmsXmlStaitc.getXmlFromMap(orderMap, list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
