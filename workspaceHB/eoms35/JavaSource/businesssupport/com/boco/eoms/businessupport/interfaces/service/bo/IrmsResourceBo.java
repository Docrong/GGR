package com.boco.eoms.businessupport.interfaces.service.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.businessupport.interfaces.client.ResManagerLoader;
import com.boco.eoms.businessupport.interfaces.service.IResourceXml;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;

public class IrmsResourceBo {
	private static IResourceXml getBean(){
		String xmlBeanId = XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.xmlBeanId");
		return (IResourceXml)ApplicationContextHolder.getInstance().getBean(xmlBeanId);
	}
	/**
	 * 资源服务初始化接口：调用预占页面之前调用此接口
	 * @param opdetail
	 * @return
	 */
	public static String createProService(Map orderMap,List productList) throws Exception{
		BocoLog.info(IrmsResourceBo.class, "start createProService...");
		IResourceXml rx = IrmsResourceBo.getBean();
		String xml = "";
		if(productList!=null){
			List list = new ArrayList();
			for(int i=0;i<productList.size();i++){
				Map map = null;
				Object o = productList.get(i);
				if(o instanceof Map)
					map = (Map)o;
				else{
					map = SheetBeanUtils.bean2Map(o);
				}
				
				map.put("customerId", orderMap.get("id").toString());
				list.add(map);
			}
			xml = rx.getXmlFromMapInit(orderMap, list);
		}else
			xml = rx.getXmlFromMapInit(orderMap);
		
		return ResManagerLoader.createProService(xml);
	}
	/**
	 * 资源实占接口，归档时调用。返回实占的资源信息。
	 * @param opdetail
	 * @return
	 */
	public static String occupyServiceRes(Map orderMap,List productList) throws Exception{
		BocoLog.info(IrmsResourceBo.class, "start occupyServiceRes...");
		IResourceXml rx = IrmsResourceBo.getBean();
		String xml = "";
		if(productList!=null){
			List list = new ArrayList();
			for(int i=0;i<productList.size();i++){
				Object o = productList.get(i);
				if(o instanceof Map)
					list.add(o);
				else{
					Map map = SheetBeanUtils.bean2Map(o);
					list.add(map);
				}
			}
			xml = rx.getXmlFromMap(orderMap,list);
		}else
			xml = rx.getXmlFromMap(orderMap);
		
		return ResManagerLoader.occupyServiceRes(xml);
	}
	
	/**
	 * 资源取消
	 * @param opdetail
	 * @return
	 */
	public static String undoServiceRes(Map orderMap,List productList) throws Exception{
		BocoLog.info(IrmsResourceBo.class, "start undoServiceRes...");
		IResourceXml rx = IrmsResourceBo.getBean();
		String xml = "";
		if(productList!=null){
			List list = new ArrayList();
			for(int i=0;i<productList.size();i++){
				Object o = productList.get(i);
				if(o instanceof Map)
					list.add(o);
				else{
					Map map = SheetBeanUtils.bean2Map(o);
					list.add(map);
				}
			}
			xml = rx.getXmlFromMap(orderMap,list);
		}else
			xml = rx.getXmlFromMap(orderMap);
		
		return ResManagerLoader.undoServiceRes(xml);
	}
	
	/**
	 * 资源回滚
	 * @param opdetail
	 * @return
	 */
	public static String undoTaskRes(Map orderMap,List productList) throws Exception{
		BocoLog.info(IrmsResourceBo.class, "start undoTaskRes...");
		IResourceXml rx = IrmsResourceBo.getBean();
		String xml = "";
		if(productList!=null){
			List list = new ArrayList();
			for(int i=0;i<productList.size();i++){
				Object o = productList.get(i);
				if(o instanceof Map)
					list.add(o);
				else{
					Map map = SheetBeanUtils.bean2Map(o);
					list.add(map);
				}
			}
			xml = rx.getXmlFromMap(orderMap,list);
		}else
			xml = rx.getXmlFromMap(orderMap);
		
		return ResManagerLoader.undoTaskRes(xml);
	}
	/**
	 * 预占校验
	 * @param orderMap
	 * @param productList
	 * @return
	 * @throws Exception
	 */
	public static String preOccupyResFinish(Map orderMap,List productList) throws Exception{
		BocoLog.info(IrmsResourceBo.class, "start preOccupyResFinish...");
		IResourceXml rx = IrmsResourceBo.getBean();
		String xml = "";
		if(productList!=null){
			List list = new ArrayList();
			for(int i=0;i<productList.size();i++){
				Object o = productList.get(i);
				if(o instanceof Map)
					list.add(o);
				else{
					Map map = SheetBeanUtils.bean2Map(o);
					list.add(map);
				}
			}
			xml = rx.getXmlFromMap(orderMap,list);
		}else
			xml = rx.getXmlFromMap(orderMap);
		
		return ResManagerLoader.preOccupyResFinish(xml);
	}
}
