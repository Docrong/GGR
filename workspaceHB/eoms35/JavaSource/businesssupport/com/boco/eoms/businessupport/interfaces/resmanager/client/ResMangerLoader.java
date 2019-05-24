package com.boco.eoms.businessupport.interfaces.resmanager.client;

import java.net.URL;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.irms.resconfig.ResManagerImplServiceLocator;
import com.boco.irms.resconfig.ResManagerImplServiceSoapBindingStub;
/**
 * 资源接入点接口
 * @author IBM
 *
 */
public class ResMangerLoader {
	private static  ResManagerImplServiceSoapBindingStub load() throws Exception{
		String getResMangerStr = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/resmanager/config/resmanager-config.xml").getProperty("ServiceUrl");		
		System.out.println("ResMangerService:"+getResMangerStr);
        URL resMangerUrl = new URL(getResMangerStr);
        ResManagerImplServiceSoapBindingStub binding = (ResManagerImplServiceSoapBindingStub)(new ResManagerImplServiceLocator().getResManagerImplPort(resMangerUrl));
        return binding;
	}
	
	/**
	 * 资源实占
	 * @param userName
	 * @param password
	 * @param fiberCode
	 * @return
	 */
	public static String occupy(String userName,String password,String fiberCode){
		String result = "";
		try{
			result = ResMangerLoader.load().occupy(userName, password, fiberCode);
		}catch(Exception err){
			err.printStackTrace();	
			result = "occupy err:"+err.getMessage();
		}
		System.out.println("occupy return:"+result);
		return result;
	}	
}
