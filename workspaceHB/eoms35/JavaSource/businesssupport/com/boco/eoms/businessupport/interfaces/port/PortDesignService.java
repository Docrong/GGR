package com.boco.eoms.businessupport.interfaces.port;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.XmlDom4j;

public class PortDesignService {
	/**
	 * EOMS设计环节打开资管方案设计页面时，资管系统调用EOMS获取端点数据接口，获取AZ端接入点和客户端勘查信息
	 * @param sheetId
	 * @return
	 */
	public String getPortData(String orderSheetId) {

//		IResourceConfirmMainManager resourceMgr = (IResourceConfirmMainManager) ApplicationContextHolder
//				.getInstance().getBean("iResourceConfirmMainManager");
		TrancePointMgr trancePointMgr = (TrancePointMgr) ApplicationContextHolder
				.getInstance().getBean("businessupportMgr");
		
		
		IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
		.getInstance().getBean("IOrderSheetManager");
		
		OrderSheet os = mgr.getOrderSheet(orderSheetId);
		String specialType = os.getOrderBuisnessType();
		
		Object objectName = "";
		if(specialType.equals(Constants._GPRS_LINE)){//GPRS专线
			objectName = new GprsSpecialLine();
        }else if(specialType.equals(Constants._IP_LINE)){//IP专线
        	objectName = new IPSpecialLine();
        }else if(specialType.equals(Constants._TRANSFER_LINE)){//传输专线
        	objectName = new TransferSpecialLine();
        }
		
		List list = mgr.getSpecialLinesByType(orderSheetId,objectName);
		List listMap = new ArrayList();
		listMap.add(SheetBeanUtils.bean2Map(list.get(0)));
//		}
		HashMap map = SheetBeanUtils.bean2Map(os);
		
//		List businessList = trancePointMgr.getBusinessupportBySheetId(orderSheetId);
//		for(int i=0;i<businessList.size();i++){
//			TrancePoint tp = (TrancePoint)businessList.get(0);
//			map.putAll(SheetBeanUtils.bean2Map(tp));
//		}
		
		
		String opDetail = "";
		
		try {
			opDetail= InterfaceUtilProperties.getInstance().getXmlFromMap(map,listMap,StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/portDesign-interface-config.xml"), "trancePort");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("orderSheetId-->" + orderSheetId);

		return opDetail;
	}
	/**
	 * 方案设计完成后，资管系统将修改后的端点信息传给EOMS
	 * @param serSupplier服务提供方 
	 * @param serCaller服务调用方
	 * @param callerPwd口令
	 * @param callTime服务调用时间
	 * @param opDetail详细信息
	 * @return
	 */
	public String updatePortData(String serSupplier, String serCaller,
			String callerPwd, String callTime, String opDetail) {

		try {

			HashMap sheetMap = new HashMap();
			sheetMap.put("serSupplier", serSupplier);
			sheetMap.put("serCaller", serCaller);
			sheetMap.put("callerPwd", callerPwd);
			sheetMap.put("callTime", callTime);
			XmlDom4j xd = new XmlDom4j();
			
			List list = xd.getList(opDetail);

			TrancePointMgr trancePointMgr = (TrancePointMgr) ApplicationContextHolder
					.getInstance().getBean("businessupportMgr");
			IIPSpecialLineManager iIPSpecialLineManager = (IIPSpecialLineManager)  ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");

			for(int i=0;i<list.size();i++){
				
				sheetMap = (HashMap)list.get(i);
				
				IPSpecialLine ip = iIPSpecialLineManager.getIPSpecialLine(StaticMethod.nullObject2String(sheetMap.get("portId")));
				ip.setAccessSiteIdenA(StaticMethod.nullObject2String(sheetMap.get("accessSiteIden")));
				ip.setInterfaceSiteNameA(StaticMethod.nullObject2String(sheetMap.get("siteName")));
				ip.setInterfaceEquipCodeA(StaticMethod.nullObject2String(sheetMap.get("siteEquipCode")));
				ip.setLinkDesinTypeA(StaticMethod.nullObject2String(sheetMap.get("portInterfaceType")));
				ip.setFiberAcount(StaticMethod.nullObject2String(sheetMap.get("fiberNum")));
				ip.setFiberLengthA(StaticMethod.nullObject2String(sheetMap.get("fiberLength")));
				ip.setFiberOwnerA(StaticMethod.nullObject2String(sheetMap.get("fiberOwner")));
				
//				TrancePoint trance = trancePointMgr.getBusinessupport(StaticMethod.nullObject2String(sheetMap.get("portId")));
//
//				trance.setAccessSiteIden(StaticMethod.nullObject2String(sheetMap.get("accessSiteIden")));
//
//				trance.setSiteName(StaticMethod.nullObject2String(sheetMap.get("siteName")));
//				trance.setSiteEquipCode(StaticMethod.nullObject2String(sheetMap.get("siteEquipCode")));
//				trance.setPortACustAidPhone(StaticMethod.nullObject2String(sheetMap.get("portACustAidPhone")));
//				trance.setPortCustAidPerson(StaticMethod.nullObject2String(sheetMap.get("portCustAidPerson")));
//				trance.setPortInterfaceType(StaticMethod.nullObject2String(sheetMap.get("portInterfaceType")));

				iIPSpecialLineManager.saveOrUpdate(ip);
			}
			return "0";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errList=" + e.getMessage();
		}
	}

	public String getBusinessData(String orderSheetId) {
//		IResourceConfirmMainManager resourceMgr = (IResourceConfirmMainManager) ApplicationContextHolder
//		.getInstance().getBean("iResourceConfirmMainManager");
		
//		ResourceConfirmMain main = new ResourceConfirmMain();
//		TrancePointMgr trancePointMgr = (TrancePointMgr) ApplicationContextHolder
//		.getInstance().getBean("businessupportMgr");
//		String orderSheetId = sheetId;

		IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
		.getInstance().getBean("IOrderSheetManager");
		
		OrderSheet os = mgr.getOrderSheet(orderSheetId);
		String specialType = os.getOrderBuisnessType();
		
		Object objectName = null;
		if(specialType.equals(Constants._GPRS_LINE)){//GPRS专线
			objectName = new GprsSpecialLine();
        }else if(specialType.equals(Constants._IP_LINE)){//IP专线
        	objectName = new IPSpecialLine();
        }else if(specialType.equals(Constants._TRANSFER_LINE)){//传输专线
        	objectName = new TransferSpecialLine();
        }
		
		List list = mgr.getSpecialLinesByType(orderSheetId,objectName);
		List listMap = new ArrayList();
		//目前业务需求一条
//		for(int i=0;list!=null&&i<list.size();i++){
		listMap.add(SheetBeanUtils.bean2Map(list.get(0)));
//		}
		HashMap map = SheetBeanUtils.bean2Map(os);
		
		String nodeName = "portDesign";
		String opDetail = "";
		try {
			opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map,listMap, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/config/portDesign-interface-config.xml"), nodeName);
		
			System.out.println(opDetail.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return opDetail;
	}

	

	public String updateProductData(String serSupplier, String serCaller,
			String callerPwd, String callTime, String opDetail) {
	try {

		HashMap sheetMap = new HashMap();
		sheetMap.put("serSupplier", serSupplier);
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		XmlDom4j xd = new XmlDom4j();
		
		List list = xd.getList(opDetail);

		
		IGprsSpecialLineManager mgr = (IGprsSpecialLineManager)  ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
		IIPSpecialLineManager iIPSpecialLineManager = (IIPSpecialLineManager)  ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");

		for(int i=0;i<list.size();i++){
			
			
			sheetMap = (HashMap)list.get(i);
			
			String productType = StaticMethod.nullObject2String(sheetMap.get("productType"));
			
			if("0".equals(productType)){
				GprsSpecialLine gs = mgr.getGprsSpecialLine(StaticMethod.nullObject2String(sheetMap.get("portductId")));
				
				gs.setSiteNameZ(StaticMethod.nullObject2String(sheetMap.get("siteName")));
				gs.setPortZBDeviceName(StaticMethod.nullObject2String(sheetMap.get("equipName")));
				gs.setPortZBDevicePort(StaticMethod.nullObject2String(sheetMap.get("portName")));
				
				mgr.saveOrUpdate(gs);
			}
			if("7".equals(productType)){
				IPSpecialLine ip = iIPSpecialLineManager.getIPSpecialLine(StaticMethod.nullObject2String(sheetMap.get("portductId")));
				ip.setSiteNameZ(StaticMethod.nullObject2String(sheetMap.get("siteName")));
				ip.setPortZBDeviceName(StaticMethod.nullObject2String(sheetMap.get("equipName")));
				ip.setPortZBDevicePort(StaticMethod.nullObject2String(sheetMap.get("portName")));
				
				iIPSpecialLineManager.saveOrUpdate(ip);
			}
			
			

			
			
		}
		return "0";

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "errList=" + e.getMessage();
	}
}
//	public static void main(String[] args) {
//		PortDesignService ps = new PortDesignService();
//		ps.getBusinessData("SN-311-100516-30002");
//	}
}
