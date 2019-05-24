package com.boco.eoms.crm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.product.service.bo.SpecialLineBO;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.crm.util.SheetMapingSchema;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.webapp.action.NewIBaseSheet;
import com.boco.eoms.sheet.groupcomplaint.zhzw.GroupComplaintZHZWService;
import com.boco.eoms.sheet.interfaceInfo.util.IODate;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.XmlDom4jSX;
//import com.boco.eoms.widencomplaintSheet.Eoms2IomService;
//import com.boco.eoms.widencomplaintSheet.EomsServiceLocator;
import com.boco.eoms.widencomplaintSheet.Eoms2IomService;
import com.boco.eoms.widencomplaintSheet.EomsServiceLocator;

public class EOMSProcessSheet {
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
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	public String newWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("crm新建工单");
		
		
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);

		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opTime", opTime);
		
		
		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		String sheetId = "";
		String result = "0";
		try{
			//保存报文至本地文件夹
			if ((31 == sheetType.intValue()&&4 == serviceType.intValue()) || (32 == sheetType.intValue()&&4 == serviceType.intValue())) {
				IODate.WriteDate(sheetType.toString(), serviceType.toString(), serialNo, opDetail);
			}
//			保存报文至本地文件夹
			 InterfaceUtil interfaceUtil = new InterfaceUtil();
			 XmlDom4jSX d = new XmlDom4jSX();
				List mapList = d.getList(opDetail);
				sheetMap.putAll((HashMap)mapList.get(0));
	        List attach = interfaceUtil.getAttachRefFromXml(attachRef);		
	        
	        try{
				SpecialLineBO bo = new SpecialLineBO();
				String id = bo.save(sheetType.toString() ,serviceType.toString(), mapList);

				if(id!=null)
					sheetMap.put("orderSheetId", id);
			}catch(Exception err){
				err.printStackTrace();
			}
	
	        EomsLoader loader = new EomsLoader();
	        sheetId = loader.newWorkSheet(sheetMap, attach);
	        System.out.println("sheetId==lyg==1=="+sheetId);
	        monitorMap.put("sheetKey", sheetId);
	        if(sheetType.intValue()==56){
	        	result = "0";
	        }
	        String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
	        monitorMap.put("beanId", beanId);
			
		}catch(Exception err){
			err.printStackTrace();
			result =err.getMessage();
		}
		System.out.println("newWorkSheet " + serialNo + ":" + result);
		System.out.println("lyg=newWorkSheet=sheetId="+sheetId+"===serialNo="+serialNo);
		System.out.println("sheetId==="+sheetId+"====result=="+result);
		if(!"".equals(sheetId) && "0".equals(result)){
//			58 家宽投诉
			System.out.println("sheetType.intValue==="+sheetType.intValue());
			if(sheetType.intValue()==58){
				//调用智慧装维派单接口
				System.out.println("11111===lyg=="+sheetId);
				EomsServiceLocator service = new EomsServiceLocator();
				System.out.println("22222===lyg=="+sheetId);
				try {
					System.out.println("start lyg=newWorkSheet=sheetId="+sheetId);
					Eoms2IomService bing = service.getEoms2IomServicePort();
					String str =bing.newWorkSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
					System.out.println("str=newWorkSheet==return=lyg=="+str+"====sheetId="+sheetId);
				} catch (Exception e) {
					e.printStackTrace();
					result ="调用智慧装维newWorkSheet报错"+e.getMessage();
				}
			}
		}
		
		return result;
	}
	/**
	 * 工单重派
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	public String renewWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("crm工单重派");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);
		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opDepart", opTime);
		
		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		String sheetId = "";
		
		String result = "0";
		try{
			InterfaceUtil interfaceUtil =new InterfaceUtil();
			XmlDom4jSX d = new XmlDom4jSX();
			List mapList = d.getList(opDetail);
			sheetMap.putAll((HashMap)mapList.get(0));	
			
			try{
				SpecialLineBO bo = new SpecialLineBO();
				bo.delete(serialNo,sheetType.toString() ,serviceType.toString(), mapList);
				String id = bo.save(sheetType.toString() ,serviceType.toString(), mapList);

				if(id!=null)
					sheetMap.put("orderSheetId", id);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			List attach = interfaceUtil.getAttachRefFromXml(attachRef);
			EomsLoader loader = new EomsLoader();
			String sheetKey = loader.renewWorkSheet(sheetMap, attach);
			monitorMap.put("sheetKey", sheetKey);
			if(sheetType.intValue()==56)
				//result = "0,"+sheetId;
				result = "0";
			
	        String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
	        monitorMap.put("beanId", beanId);
			
		}catch(Exception err){
			err.printStackTrace();
			result =err.getMessage();
		}
		System.out.println("renewWorkSheet " + serialNo + ":" + result);
		
		
		
		
		if("0".equals(result)){
//			58 家宽投诉
			if(sheetType.intValue()==58){
//				由于返回的是sheetKey用客服流水号得到sheetId
				sheetId = sheetNo2SheetId(serialNo);
				if(!"".equals(sheetId)){
//					调用智慧装维派单接口
					EomsServiceLocator service = new EomsServiceLocator();
					try {
						System.out.println("start lyg=renewWorkSheet=sheetId="+sheetId);
						Eoms2IomService bing = service.getEoms2IomServicePort();
						String str = bing.renewWorkSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
						System.out.println("str=renewWorkSheet==return=lyg=="+str+"====sheetId="+sheetId);
					} catch (Exception e) {
						e.printStackTrace();
						result ="调用智慧装维renewWorkSheet报错"+e.getMessage();
					}
				}
			}
		}
		
		return result;
	}
	/**
	 * 阶段通知
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	public String suggestWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("crm阶段通知");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);
		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opDepart", opTime);

		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		
		String result = "0";
		String sheetId = "";
		try{
			InterfaceUtil interfaceUtil =new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");		
			
			List attach = interfaceUtil.getAttachRefFromXml(attachRef);
			EomsLoader loader = new EomsLoader();
			sheetId = loader.suggestWorkSheet(sheetMap, attach);
			monitorMap.put("sheetKey", sheetId);
			
			String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
		    monitorMap.put("beanId", beanId);
			
		}catch(Exception err){
			err.printStackTrace();
			result =err.getMessage();
		}
		System.out.println("suggestWorkSheet " + serialNo + ":" + result);
		
		
		if(!"".equals(sheetId) && "0".equals(result)){
//			58 家宽投诉
			if(sheetType.intValue()==58){
				//调用智慧装维派单接口
				EomsServiceLocator service = new EomsServiceLocator();
				try {
					System.out.println("start lyg=remindWorkSheet=sheetId="+sheetId);
					Eoms2IomService bing = service.getEoms2IomServicePort();
					String str = bing.remindWorkSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
					System.out.println("str=remindWorkSheet==return=lyg=="+str+"====sheetId="+sheetId);
				} catch (Exception e) {
					e.printStackTrace();
					result ="调用智慧装维remindWorkSheet报错"+e.getMessage();
				}
			}
			
			
			else if(sheetType.intValue()==57){
				GroupComplaintZHZWService groupComplaintZHZWService=new GroupComplaintZHZWService();
				groupComplaintZHZWService.informSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			}
		}
		
		return result;
	}
	/**
	 * 工单退回
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	public String untreadWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("crm工单退回");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);
		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opDepart", opTime);

		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		
		String result = "0";
		String sheetId = "";
		try{
			InterfaceUtil interfaceUtil =new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");		
			
			List attach = interfaceUtil.getAttachRefFromXml(attachRef);
			EomsLoader loader = new EomsLoader();
			String sheetKey = loader.untreadWorkSheet(sheetMap, attach);
			monitorMap.put("sheetKey", sheetKey);
			
			 String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
		     monitorMap.put("beanId", beanId);
			
		}catch(Exception err){
			err.printStackTrace();
			result =err.getMessage();
		}
		System.out.println("untreadWorkSheet " + serialNo + ":" + result);
		
		if("0".equals(result)){
//			58 家宽投诉
			if(sheetType.intValue()==58){
				
				sheetId = sheetNo2SheetId(serialNo);
				if(!"".equals(sheetId)){
//					调用智慧装维派单接口
					EomsServiceLocator service = new EomsServiceLocator();
					try {
						System.out.println("start lyg=remindWorkSheet=sheetId="+sheetId);
						Eoms2IomService bing = service.getEoms2IomServicePort();
						String str = bing.untreadWorkSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
						System.out.println("str=untreadWorkSheet==return=lyg=="+str+"====sheetId="+sheetId);
					} catch (Exception e) {
						e.printStackTrace();
						result ="调用智慧装维untreadWorkSheet报错"+e.getMessage();
					}
				}
				
				
			}
		}
		
		return result;
	}
	/**
	 * 归档
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	public String checkinWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("crm工单归档");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);
		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opDepart", opTime);
		
		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		String result = "0";
		String sheetId = "";
		try{
			InterfaceUtil interfaceUtil =new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");		
			
			List attach = interfaceUtil.getAttachRefFromXml(attachRef);
			EomsLoader loader = new EomsLoader();
			String sheetKey = loader.checkinWorkSheet(sheetMap, attach);
			monitorMap.put("sheetKey", sheetKey);
			
			 String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
		     monitorMap.put("beanId", beanId);
			
			
		}catch(Exception err){
			err.printStackTrace(); 
			result =err.getMessage();
		}
		System.out.println("checkinWorkSheet " + serialNo + ":" + result);
		
		if("0".equals(result)){
//			58 家宽投诉
			if(sheetType.intValue()==58){
				sheetId = sheetNo2SheetId(serialNo);
				if(!"".equals(sheetId)){
//					调用智慧装维派单接口
					EomsServiceLocator service = new EomsServiceLocator();
					try {
						System.out.println("start lyg=checkinWorkSheet=sheetId="+sheetId);
						Eoms2IomService bing = service.getEoms2IomServicePort();
						String str = bing.checkinWorkSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
						System.out.println("str=checkinWorkSheet==return=lyg=="+str+"====sheetId="+sheetId);
					} catch (Exception e) {
						e.printStackTrace();
						result ="调用智慧装维checkinWorkSheet报错"+e.getMessage();
					}
				}
				
			}
			
			else if(sheetType.intValue()==57){
				GroupComplaintZHZWService groupComplaintZHZWService=new GroupComplaintZHZWService();
				sheetId = sheetNo2SheetId1(serialNo);
				System.out.println("lk,EOMSProcessSheet="+sheetId);
				groupComplaintZHZWService.endSheet(sheetType+"", serviceType+"", serialNo, sheetId, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			}
			
			
		}
		
		return result;
	}
	/**
	 * 撤销
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	/*public String cancelWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("crm cancelWorkSheet");
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);
		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opDepart", opTime);
		
		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		
		String result = "0";
		try{
			InterfaceUtil interfaceUtil =new InterfaceUtil();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");		
			
			List attach = interfaceUtil.getAttachRefFromXml(attachRef);
			EomsLoader loader = new EomsLoader();
			String sheetKey = loader.cancelWorkSheet(sheetMap, attach);
			monitorMap.put("sheetKey", sheetKey);
			
			
			 String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
		     monitorMap.put("beanId", beanId);
			
			
		}catch(Exception err){
			err.printStackTrace(); 
			result =err.getMessage();
		}
		System.out.println("cancelWorkSheet " + serialNo + ":" + result);
		
//		InterfaceDataMonitor monitor = new InterfaceDataMonitor();
//		monitor.saveMonitor(monitorMap, result, "crm", "cancelWorkSheet");
		
		return result;
	}*/
	/**
	 * 资源信息推送接口(福建)
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail
	 * @return
	 */
	public String putNbrmsInfo(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		return null;
	}
	/**
	 * 订单信息推送请求接口(福建)
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail
	 * @return
	 */
	public String requestEomsInfo(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		return null;
	}
	public String putImpiExeResultInfo(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		return null;
	}
	
	
	/**
	 * 工单新增
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail
	 * @return
	 */
	public String bandsvrWorkSheet(String sheetType,String serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String opCorp,String opDepart,String opContact,String opTime,String opDetail,String attachRef){
		BocoLog.info(this, "start bandsvrWorkSheet");
		BocoLog.info(this, "sheetType:"+sheetType);
		BocoLog.info(this, "serviceType:"+serviceType);
		BocoLog.info(this, "serialNo:"+serialNo);
		BocoLog.info(this, "serSupplier:"+serSupplier);
		BocoLog.info(this, "serCaller:"+serCaller);
		BocoLog.info(this, "callerPwd:"+callerPwd);
		BocoLog.info(this, "callTime:"+callTime);
		BocoLog.info(this, "opCorp:"+opCorp);
		BocoLog.info(this, "opDepart:"+opDepart);
		BocoLog.info(this, "opContact:"+opContact);
		BocoLog.info(this, "opTime:"+opTime);
		BocoLog.info(this, "opDetail:"+opDetail);
		BocoLog.info(this, "attachRef:"+attachRef);
		
		String result = "0";
		
		Map monitorMap = new HashMap();
		
		try{
			HashMap sheetMap = new HashMap();
			sheetMap.put("sheetType", sheetType);
			sheetMap.put("serviceType", serviceType);
			sheetMap.put("serialNo", serialNo);
			
			sheetMap.put("serSupplier", serSupplier);
			
			sheetMap.put("serCaller", serCaller);
			sheetMap.put("callerPwd", callerPwd);
			sheetMap.put("callTime", callTime);
			sheetMap.put("opDetail", opDetail);
			sheetMap.put("attachRef", attachRef);
			
			sheetMap.put("opPerson", "");
			sheetMap.put("opCorp", opCorp);
			sheetMap.put("opDepart", opDepart);
			sheetMap.put("opContact", opContact);
			sheetMap.put("opDepart", opTime);
			
			monitorMap.putAll(sheetMap);
			
			InterfaceUtil interfaceUtil = new InterfaceUtil();
			sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			
			List attach = interfaceUtil.getAttachRefFromXml(attachRef);
			//List attach = null;
			//String beanId = this.getDictId("dict.sheetType", "sheetType", "beanId", sheetType);
			String busiType = (String)sheetMap.get("busiType");
			String bandType = (String)sheetMap.get("bandType");
			
			String beanId = SheetMapingSchema.getInstance().getBeanId(bandType, busiType);
			monitorMap.put("beanId", beanId);
			BocoLog.info(this, "bandsvrWorkSheet======beanId=="+beanId);
			if(beanId==null || beanId.equals(""))
				throw new Exception("broadband参数错误：bandType or busiType err");
			NewIBaseSheet newIBaseSheet = (NewIBaseSheet)ApplicationContextHolder.getInstance().getBean(beanId);
			String sheetKey = newIBaseSheet.newWorkSheet(sheetMap, attach);
			monitorMap.put("sheetKey", sheetKey);
			
		}catch(Exception e){
			e.printStackTrace();
			result = e.getMessage();
		}
		BocoLog.info(this, "bandsvrWorkSheet return:"+result);
		
//		InterfaceDataMonitor monitor = new InterfaceDataMonitor();
//		monitor.saveMonitor(monitorMap, result, "crm", "cancelWorkSheet");
		
		return result;
	}
	
	public String sheetNo2SheetId(String sheetNo){
		String sheetId = "";
		
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
		List list = new ArrayList();
		try {
			list = iMainService.getMainListByParentSheetId(sheetNo);
		} catch (SheetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (list != null && list.size() > 0)
		{
			BaseMain main = (BaseMain)list.get(0);
			sheetId = StaticMethod.nullObject2String(main.getSheetId());
		} 
		System.out.println("lygsheet==="+sheetId);
		return sheetId;
	}
	public String sheetNo2SheetId1(String sheetNo){
		String sheetId = "";
		
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
		List list = new ArrayList();
		try {
			list = iMainService.getMainListByParentSheetId(sheetNo);
		} catch (SheetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (list != null && list.size() > 0)
		{
			BaseMain main = (BaseMain)list.get(0);
			sheetId = StaticMethod.nullObject2String(main.getSheetId());
		} 
		System.out.println("lk,sheetId==="+sheetId);
		return sheetId;
	}
	
//	public String getDictId(String nodeName,String fromAttributeName,String toAttributeName,String value) throws Exception{
//		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/broadSheetservice/config/broad-interface-config.xml");
//		InterfaceUtilProperties up = new InterfaceUtilProperties();
//		String dict = up.getXmlValue(filePath, nodeName, fromAttributeName, toAttributeName, value);
//		if(dict==null || dict.equals(""))
//			throw new Exception("读取字典错误："+fromAttributeName+" err"+value);
//		return dict;
//	}	
	
	public static void main(String[] args) {
		String xml = "<opDetail>" +
		 "<recordInfo>" +
			 "<fieldInfo>"
			 + "<fieldChName>1</fieldChName>"
			 + "<fieldEnName>busiType</fieldEnName>"
			 + "<fieldContent>001</fieldContent>" +
			 "</fieldInfo>"+
			 "<fieldInfo>"
			 + "<fieldChName>2</fieldChName>"
			 + "<fieldEnName>bandType</fieldEnName>"
			 + "<fieldContent>001</fieldContent>" +
			 "</fieldInfo>"
		 + "</recordInfo>" +
		 "</opDetail>";
		
		EOMSProcessSheet ps = new EOMSProcessSheet();
		String result = ps.bandsvrWorkSheet("11", "22", "33", "", "", "", "", "", "", "", "", xml,"");
		System.out.println("==="+result);
	}
}
