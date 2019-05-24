package com.boco.eoms.sheet.securityobjaudit.interfaces;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.boco.eoms.base.util.StringUtil;
import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.sheet.securityobjaudit.interfaces.XmlUtil;

public class EOMSSecurityObjAudit {
	/**
	 * 联通测试
	 * @param serSupplier
	 * @param callTime
	 * @return 
	 */
	public String isAlive(String serSupplier,String callTime) {
		System.out.println("收到crm握手信号");
		String isAliveResult = "0";

		return isAliveResult;
	}
	/**
	 * 工单派发
	 * @param sheetType
	 * @param serviceType
	 * @param opDetail xml
	 * @return
	 */
	public String SecObjectIssue(String opDetail,String token){
		System.out.println("crm工单派发");
		

		System.out.println("SecObjectIssue==liu=");
		HashMap sheetMap=new HashMap();
		sheetMap.put("sheetType", "71");
		sheetMap.put("serviceType", "1");

		String msgdsc="";
		String returnstate="0";
		String errorid="";
		String mainISMPSheetId ="";
		String sheetId="";
		
		
		try {
//			InterfaceUtil interfaceUtil=new InterfaceUtil();
//			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			
			XmlUtil xmlutil = new XmlUtil();
			System.out.println("opDetail=SecObjectIssue=liu==before="+opDetail);
			opDetail = StringUtil.decodeString(opDetail);
			System.out.println("opDetail=SecObjectIssue=liu==after="+opDetail);
			
			mainISMPSheetId=xmlutil.getSingleNodeValue2(opDetail, "ISMPordernum");//
			String title=xmlutil.getSingleNodeValue2(opDetail, "title");//
			String mainCheckTimeStr=xmlutil.getSingleNodeValue2(opDetail, "checkTime");//
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		
			java.util.Date mainCheckTime=sdf.parse(mainCheckTimeStr);  
			
			String mainWorkName=xmlutil.getSingleNodeValue2(opDetail, "taskName");//
			String mainWorkId=xmlutil.getSingleNodeValue2(opDetail, "taskNum");//
			
			String mainProDes=xmlutil.getSingleNodeValue2(opDetail, "issuedesc");//
			//String mainOperateName=xmlutil.getSingleNodeValue2(opDetail, "operationName");//
			String mainSecurityID=xmlutil.getSingleNodeValue2(opDetail, "objectID");//
			String mainSecurityName=xmlutil.getSingleNodeValue2(opDetail, "objectName");//
			String mainSecurityIP=xmlutil.getSingleNodeValue2(opDetail, "objectIP");//
			
			
			sheetMap.put("title", title);
			sheetMap.put("mainISMPSheetId", mainISMPSheetId);
			sheetMap.put("mainCheckTime", mainCheckTime);
			sheetMap.put("mainWorkName", mainWorkName);
			sheetMap.put("mainWorkId", mainWorkId);
			sheetMap.put("mainProDes", mainProDes);
			//sheetMap.put("mainOperateName", mainOperateName);
			sheetMap.put("mainSecurityID", mainSecurityID);
			sheetMap.put("mainSecurityName", mainSecurityName);
			sheetMap.put("mainSecurityIP", mainSecurityIP);
		} catch (Exception e) {
			e.printStackTrace();
			returnstate = "1";
			errorid = "1002";
			msgdsc=e.getMessage();
			System.out.println(" EOMSSecurityObjAudit newWorkSheet is " + msgdsc);
		}
		
		try {
			EomsLoader loader=new EomsLoader();
			sheetId = loader.newWorkSheet(sheetMap, null);
		} catch (Exception e) {
			e.printStackTrace();
			returnstate = "1";
			errorid = "1001";
			msgdsc=e.getMessage();
			System.out.println(" EOMSSecurityObjAudit newWorkSheet is " + msgdsc);
		}	
		String result = getResult(mainISMPSheetId,returnstate,errorid,msgdsc,sheetId);
		
		result = StringUtil.encodeString(result);
		
		return result;
	}

	public String orderarchive(String opDetail){
		System.out.println("crm工单派发");
		

		
		HashMap sheetMap=new HashMap();
		sheetMap.put("sheetType", "71");
		sheetMap.put("serviceType", "1");

		String msgdsc="";
		String returnstate="0";
		String errorid="";
		String mainISMPSheetId ="";
		try {
//			InterfaceUtil interfaceUtil=new InterfaceUtil();
//			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			
			opDetail = StringUtil.decodeString(opDetail);
			XmlUtil xmlutil = new XmlUtil();
			String userNmae=xmlutil.getSingleNodeValue2(opDetail, "userNmae");//
			String orderID=xmlutil.getSingleNodeValue2(opDetail, "orderID");//
			String ISMPordernum=xmlutil.getSingleNodeValue2(opDetail, "ISMPordernum");//
			String orderarchivetime=xmlutil.getSingleNodeValue2(opDetail, "orderarchivetime");//工单归档时间
			String endResult=xmlutil.getSingleNodeValue2(opDetail, "orderarchiveramark");//工单归档备注
			String orderstate=xmlutil.getSingleNodeValue2(opDetail, "orderstate");//工单归档状态。1、工单归档；2、工单从新发起
			String linkReject=xmlutil.getSingleNodeValue2(opDetail, "msgdsc");//工单驳回描述
			
			
			
			
			sheetMap.put("orderID", orderID);
			sheetMap.put("userNmae", userNmae);
			sheetMap.put("mainISMPSheetId", mainISMPSheetId);
			sheetMap.put("orderarchivetime", orderarchivetime);
			sheetMap.put("endResult", endResult);
			sheetMap.put("orderstate", orderstate);
			sheetMap.put("linkReject", linkReject);
			
		} catch (Exception e) {
			e.printStackTrace();
			returnstate = "1";
			errorid = "1002";
			msgdsc=e.getMessage();
			System.out.println(" EOMSSecurityObjAudit orderarchive is " + msgdsc);
		}
		
		try {
			EomsLoader loader = new EomsLoader();
			String orderstate = (String)sheetMap.get("orderstate");
			if ("1".equals(orderstate)) {
				loader.checkinWorkSheet(sheetMap, null);
			} else if ("2".equals(orderstate)) {
				loader.untreadWorkSheet(sheetMap, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnstate = "1";
			errorid = "1001";
			msgdsc=e.getMessage();
			System.out.println(" EOMSSecurityObjAudit orderarchive is " + msgdsc);
		}	
		
		String result = getResult(mainISMPSheetId,returnstate,errorid,msgdsc,"");
		
		result = StringUtil.encodeString(result);
		
		return result;
	}
	
	

	public String getResult(String mainISMPSheetId,String returnstate,String errorid,String msgdsc,String sheetId){
		
		String s="<?xml version=\"1.0\" encoding=\"UTF-8\"?><flowreturn>"+
			"<returnstate>"+returnstate+"</returnstate><orderID>"+sheetId+"</orderID><ISMPordernum>"+mainISMPSheetId+"</ISMPordernum>";
		if("1".equals(returnstate)){ 
			s = s+"<msg><errorid>"+errorid+"</errorid><msgdsc>"+msgdsc+"</msgdsc></msg>";
		}
		s = s+"</flowreturn>";
		
		System.out.println("result:liu="+s);
		
		return s;
	}
	
	
}
