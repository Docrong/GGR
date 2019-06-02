package com.boco.eoms.interfaces.EOMSCommonTaskServer;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.util.InterfaceUtil;

public class EOMSCommonTaskSheet {

	/**
	 * 工单派发
	 * @param sheetType
	 * @param serviceType
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param opDetail xml
	 * @return
	 */
	public String newWorkSheet(Integer sheetType,Integer serviceType,String serSupplier,String serCaller,String callerPwd,String callTime,String opDetail){
		System.out.println("crm工单派发");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		
		HashMap sheetMap=new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serSupplier", serSupplier);
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("serCaller", serCaller);
		String result="0";
		try {
			InterfaceUtil interfaceUtil=new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			EomsLoader loader=new EomsLoader();
			loader.newWorkSheet(sheetMap, null);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
			System.out.println(" CommonTask newWorkSheet is " + result);
		}
		return result;
	}
	/**
	 * 回复工单
	 * @param sheetType
	 * @param serviceType
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param opDetail xml
	 * @return
	 */
	public String replyWorkSheet(Integer sheetType,Integer serviceType,String serSupplier,String serCaller,String callerPwd,String callTime,String opDetail){
		System.out.println("crm工单派发");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		
		HashMap sheetMap=new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serSupplier", serSupplier);
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		String result="0";
		try {
			InterfaceUtil interfaceUtil=new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			EOMSCommonTaskLoader loader=new EOMSCommonTaskLoader();
			loader.replyWorkSheet(sheetMap, null);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
			System.out.println("CommonTak replyWorkSheet:" + result);
		}
		return result;
	}
}