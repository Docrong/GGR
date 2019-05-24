package com.boco.eoms.sheet.commonfault.service.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.handheldoperation.HandheldOperationUtil;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;


public class SendMessageClient {
	public void sendMessage(String userid,String sheetId,String title,Date completelimit){
		List mapList=new ArrayList();
		//查询子角色下的人员
		ITawSystemUserRefRoleManager mgr=((ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager"));
		List list=mgr.getUserbyroleid(userid);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				TawSystemUser user =(TawSystemUser)list.get(i);
				if(user!=null){
					Map para=new HashMap();
					para.put("userid", user.getUserid());
					para.put("sheetId", sheetId);
					para.put("title", title);
					para.put("completelimit", completelimit);
					
					mapList.add(para);
				}
			}
		}
		HandheldOperationUtil hoUtil =new HandheldOperationUtil();
		
		
		try { 
			String opDetail =hoUtil.getXmlFromMap(mapList, StaticMethod.getFilePathForUrl("classpath:config/sendmessage-util.xml"), "message","");
		
			System.out.println("消息推送接口opDetail="+opDetail);
		
		
	    	String NameSP = StaticMethod.nullObject2String(
					XmlManage.getFile("/config/sendmessage-util.xml").getProperty("SendMessageBO.NameSP"));
			String method = StaticMethod.nullObject2String(
					XmlManage.getFile("/config/sendmessage-util.xml").getProperty("SendMessageBO.method"));
			String SOAPAction = StaticMethod.nullObject2String(
					XmlManage.getFile("/config/sendmessage-util.xml").getProperty("SendMessageBO.SOAPAction"));
			
			String serSupplier=StaticMethod.nullObject2String(
					XmlManage.getFile("/config/sendmessage-util.xml").getProperty("SendMessageBO.serSupplier"));
			
			String url=StaticMethod.nullObject2String(
					XmlManage.getFile("/config/sendmessage-util.xml").getProperty("SendMessageBO.ServerUrl"));
			System.out.println("url:"+url);
			System.out.println("method:"+method);
			System.out.println("NameSP:"+NameSP);
			
			  Service service = new Service(); 
		      String xmlns = NameSP ;
		      Call call = (Call)service.createCall(); 
		      call.setOperationName(new QName(xmlns,method)); 
		      call.setSOAPActionURI(SOAPAction);
		     
//		      call.addParameter(new QName(xmlns,"serSupplier"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
//		      call.addParameter(new QName(xmlns,"serCaller"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
//		      call.addParameter(new QName(xmlns,"callerPwd"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
//		      call.addParameter(new QName(xmlns,"callTime"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
//		      call.addParameter(new QName(xmlns,"opDetail"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		      call.addParameter(new javax.xml.namespace.QName(xmlns, "opDetail"), 
		    		  			new javax.xml.namespace.QName(xmlns, "string"), 
		    		  			java.lang.String.class, javax.xml.rpc.ParameterMode.IN);
		        
		      System.out.println("开始调用消息推送接口");
		      call.setTargetEndpointAddress(new java.net.URL(url));
		      call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
		      call.setTimeout(Integer.valueOf(50000));
		      
//		     String outxml = (String)call.invoke(new Object[]{serSupplier,"","","",opDetail}); 
		      String outxml = (String)call.invoke(new Object[]{opDetail}); 
			 System.out.println("outxml==="+outxml);
	    
		}catch (Exception e) {
	    	System.out.println(e.toString());
	    }
	}
	
}
