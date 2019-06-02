package com.boco.eoms.crm.bo;

import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.client.CRMProcessSheetServiceLocator;
import com.boco.eoms.crm.client.CRMProcessSheetSoapBindingStub;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class CrmLoader {
	private static CRMProcessSheetSoapBindingStub loadService(String sheetType) throws Exception{
		String getCrmStr = XmlManage.getFile("/com/boco/eoms/crm/config/crm-config.xml").getProperty("ServiceUrl");
		String serviceStr = InterfaceUtilProperties.getInstance().getServiceUrlBySheetType(sheetType);
		if(serviceStr.length()>0)
			getCrmStr = serviceStr;
		System.out.println("crmService:"+getCrmStr);
        URL crmUrl = new URL(getCrmStr);
        CRMProcessSheetSoapBindingStub binding = (CRMProcessSheetSoapBindingStub)(new CRMProcessSheetServiceLocator().getCRMProcessSheet(crmUrl));
        return binding;
	}
	
	public void isAlive(){
		
	}
	public static String confirmWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).confirmWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	public static String notifyWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).notifyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	
	public static String replyWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).replyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
			System.out.println("ParentCorrelation==="+serialNo+"==result="+result);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	public static String withdrawWorkSheet(int sheetType,int serviceType,String serialNo,String opDetail,String attachRef){
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).withdrawWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
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
			CrmLoader.addContentXML(recordInfo,StaticMethod.nullObject2String(chNameList.get(i)),StaticMethod.nullObject2String(enNameList.get(i)),contentList.get(i));
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).confirmWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).notifyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).replyWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
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
			result = CrmLoader.loadService(String.valueOf(sheetType)).withdrawWorkSheet(Integer.valueOf(sheetType), Integer.valueOf(serviceType), serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail);
		}catch(Exception err){
			System.out.println("");
			err.printStackTrace();			
		}
		return result;
	}
	
}
