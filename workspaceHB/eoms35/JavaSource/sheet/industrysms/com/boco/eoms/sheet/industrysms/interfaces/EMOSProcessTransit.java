package com.boco.eoms.sheet.industrysms.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.industrysms.util.DesHexEncrypt;
//import com.boco.eoms.sheet.interfaceBase.util.InterfaceDataMonitor;
import com.boco.eoms.util.InterfaceUtil;

public class EMOSProcessTransit {

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
	public String replyWorkSheet(String serialNo,String serCaller,String callerPwd,String opDetail){
		System.out.println("crm工单派发");
		System.out.println("serialNo="+serialNo);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("opDetail="+opDetail);
		DesHexEncrypt dhe = new DesHexEncrypt();
		dhe.getKey("DNMS_EMOS_ITMS");
		callerPwd = dhe.getDesString(callerPwd);
		String callTime = StaticMethod.date2String(new Date());
		
		HashMap sheetMap=new HashMap();
		sheetMap.put("serialNo", serialNo);
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		
		Map monitorMap = new HashMap();
		monitorMap.put("sheetType", "");
		monitorMap.put("serviceType", "");
		monitorMap.put("serSupplier", "CRM");
		monitorMap.put("callTime", callTime);
		monitorMap.put("attachRef", "");
		monitorMap.put("opPerson", "");
		monitorMap.put("opCorp", "");
		monitorMap.put("opDepart", "");
		monitorMap.put("opContact", "");
		monitorMap.put("opDepart", "");
		monitorMap.put("opDetail", opDetail);
		monitorMap.put("sheetKey", serialNo);
		monitorMap.putAll(sheetMap);
		String result="0";
		try {
			InterfaceUtil interfaceUtil=new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			EOMSIndustrySmsLoader loader=new EOMSIndustrySmsLoader();
			loader.replyWorkSheet(sheetMap, null);
			//InterfaceDataMonitor monitor = new InterfaceDataMonitor();
			//monitor.saveMonitor(monitorMap, result,  "crm", "dataHumTask");
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
			//InterfaceDataMonitor monitor = new InterfaceDataMonitor();
			//monitor.saveMonitor(monitorMap, result,  "crm", "dataHumTask");
			System.out.println("IndustrySms replyWorkSheet:" + result);
		}
		return result;
	}
}