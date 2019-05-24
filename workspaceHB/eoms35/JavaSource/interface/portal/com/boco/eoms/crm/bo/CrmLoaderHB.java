package com.boco.eoms.crm.bo;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator;
import com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub;

public class CrmLoaderHB {
	private static CRMProcessSheetSoap11BindingStub loadService(String sheetType) throws Exception{
		String getCrmStr = XmlManage.getFile("/com/boco/eoms/crm/config/crm-config.xml").getProperty("ServiceUrl");
		String serviceStr = InterfaceUtilProperties.getInstance().getServiceUrlBySheetType(sheetType);
		if(serviceStr.length()>0)
			getCrmStr = serviceStr;
		System.out.println("crmService:"+getCrmStr);
        URL crmUrl = new URL(getCrmStr);//getCRMProcessSheetHttpSoap11Endpoint
        CRMProcessSheetSoap11BindingStub binding = (CRMProcessSheetSoap11BindingStub)(new CRMProcessSheetLocator().getCRMProcessSheetHttpSoap11Endpoint(crmUrl));
        System.out.println("crmService==1");
        return binding;
	}
	
	public void isAlive(){
		
	}
	public static String confirmWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
		String result = "";
		try{
			String serSupplier = "HB_EOMS";
			String serCaller = "EOMS";
			String callerPwd = "11111111";
			String callTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String opPerson = "";
			String opCorp = "bobo";
			String opDepart = "HB";
			String opContact = "";
			String opTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).confirmWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			System.out.println("confirmWorkSheet result:"+result);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	public static String notifyWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
		String result = "";
		
		String serSupplier = "HB_EOMS";
		String serCaller = "EOMS";
		String callerPwd = "11111111";
		String callTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String opPerson = "";
		String opCorp = "bobo";
		String opDepart = "HB";
		String opContact = "";
		String opTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
		try{
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).notifyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			System.out.println("notifyWorkSheet result:"+result);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	
	public static String replyWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
		String result = "";
//		(new Integer(32), new Integer(4),
//                "22702010082036194480", "HB_EOMS", "EOMS", "11111111",
//                "20091110 16:04:06", "", "bobo", "HB", "HB",
//                "13856478997", "2009-06-06", optDetail);
		String serSupplier = "HB_EOMS";
		String serCaller = "EOMS";
		String callerPwd = "11111111";
		String callTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String opPerson = "";
		String opCorp = "bobo";
		String opDepart = "HB";
		String opContact = "";
		String opTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
		try{
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).replyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			System.out.println("replyWorkSheet result:"+result);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	public static String withdrawWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
		String result = "";
		
		String serSupplier = "HB_EOMS";
		String serCaller = "EOMS";
		String callerPwd = "11111111";
		String callTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String opPerson = "";
		String opCorp = "bobo";
		String opDepart = "HB";
		String opContact = "";
		String opTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
		try{
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).withdrawWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			System.out.println("withdrawWorkSheet result:"+result);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	
	/**
	 * 生成opdetail
	 * @param chNameList
	 * @param enNameList
	 * @param contentList
	 * @return
	 */
	public static String createOpDetailXml(List chNameList,List enNameList,List contentList){
		Document dom4jDoc = DocumentHelper.createDocument();		
		Element opDetailElement = dom4jDoc.addElement("opDetail");
		Element recordInfo = opDetailElement.addElement("recordInfo");
		for(int i=0;i<chNameList.size();i++){
			CrmLoaderHB.addContentXML(recordInfo,StaticMethod.nullObject2String(chNameList.get(i)),StaticMethod.nullObject2String(enNameList.get(i)),contentList.get(i));
		}
		String xml = dom4jDoc.asXML();
		String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		int index = xml.indexOf(opt);
		if(index>=0){
			xml = xml.substring(index+opt.length());
		}
		return xml;
	}
	private static  void addContentXML(Element recordInfo, String cnname, String name,
			Object object) {
		Element filedInfo =recordInfo.addElement("fieldInfo");
		Element fieldCnName = filedInfo.addElement("fieldChName");
		fieldCnName.setText(cnname);
		Element fieldEnName = filedInfo.addElement("fieldEnName");
		fieldEnName.setText(name);
		Element fieldContent = filedInfo.addElement("fieldContent");
		if(object==null)
			fieldContent.setText("");
		else
			fieldContent.setText(object.toString());
	}
	public static String createAttachRefXml(String attachIds){
		if(attachIds==null||attachIds.equals(""))
			return "";
		attachIds = attachIds.replaceAll("'", "");
		Document dom4jDoc = DocumentHelper.createDocument();		
		Element opDetailElement = dom4jDoc.addElement("attachRef");		
		
		ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");

		String uploadType = XmlManage.getFile("/com/boco/eoms/crm/config/crm-config.xml").getProperty("uploadType");
		if(uploadType==null || uploadType.endsWith(""))
			uploadType = "http";
		
		String[] attacArray = attachIds.split(",");
		for(int i=0;i<attacArray.length;i++){			
			String id = attacArray[i];
			TawCommonsAccessories tawCommonsAccessories = mgr.getSystemToOther(id, uploadType);
			if(tawCommonsAccessories!=null&&tawCommonsAccessories.getAccessoriesPath()!=null){
				Element recordInfo = opDetailElement.addElement("attachInfo");
				
				Element attachName = recordInfo.addElement("attachName");
				attachName.setText(tawCommonsAccessories.getAccessoriesCnName());
				Element attachURL = recordInfo.addElement("attachURL");
				attachURL.setText(tawCommonsAccessories.getAccessoriesPath());
				Element attachLength = recordInfo.addElement("attachLength");
				attachLength.setText(""+tawCommonsAccessories.getAccessoriesSize());
			}
		}
		String xml = dom4jDoc.asXML();
		String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		int index = xml.indexOf(opt);
		if(index>=0){
			xml = xml.substring(index+opt.length());
		}
		return xml;
	}
	
	
	public static String confirmWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef,String userid){
		String result = "";
		try{
			String serSupplier = "";
			String serCaller = "";
			String callerPwd = "";
			String callTime = "";
			String opPerson = "";
			String opCorp = "";
			String opDepart = "";
			String opContact = "";
			String opTime = "";
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).confirmWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	public static String notifyWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef,String userid){
		String result = "";
		
		String serSupplier = "";
		String serCaller = "";
		String callerPwd = "";
		String callTime = "";
		String opPerson = "";
		String opCorp = "";
		String opDepart = "";
		String opContact = "";
		String opTime = "";
		try{
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).notifyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	
	public static String replyWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef,String userid){
		String result = "";
		
		String serSupplier = "";
		String serCaller = "";
		String callerPwd = "";
		String callTime = "";
		String opPerson = "";
		String opCorp = "";
		String opDepart = "";
		String opContact = "";
		String opTime = "";
		try{
			System.out.println(""+Integer.valueOf(sheetType)+"--------------"+ Integer.valueOf(serviceType)+"--------------"+ serialNo+"--------------"+ serSupplier+"-------------"+serCaller+"--------------"+ callerPwd+"--------------"+ callTime+"--------------"+ attachRef+"--------------"+ opPerson+"--------------"+ opCorp+"--------------"+ opDepart+"--------------"+ opContact+"--------------"+ opTime+"--------------"+ opDetail);
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).replyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	public static String withdrawWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef,String userid){
		String result = "";
		
		String serSupplier = "";
		String serCaller = "";
		String callerPwd = "";
		String callTime = "";
		String opPerson = "";
		String opCorp = "";
		String opDepart = "";
		String opContact = "";
		String opTime = "";
		try{
			result = CrmLoaderHB.loadService(String.valueOf(sheetType)).withdrawWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	
}
