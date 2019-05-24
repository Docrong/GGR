package com.boco.eoms.commons.interfaceMonitoring.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.interfaceMonitoring.dao.hibernate.InterfaceMonitoringDaoHibernate;
import com.boco.eoms.commons.interfaceMonitoring.mgr.InterfaceMonitoringMgr;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;

public class InterfaceMonitoringUtil {

	/**
	 * @param args
	 */

	public String testLink(String strUrl)
	// 一个public方法，返回字符串，错误则返回"error open url"
	{
		String result = "";
		try {

			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String s = "";

			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			result = "Success";
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();

		}
		return result;
	}

	public String test(String strUrl) {
		String name = "serCaller,";
		String str = "<serCaller xmlns='http://util.interfaceMonitoring.commons.eoms.boco.com'>EOMS</serCaller><callTime xmlns='http://util.interfaceMonitoring.commons.eoms.boco.com'>"
				+ "2008-06-26 00:00:00</callTime>";
		if (str.indexOf("<serCaller") > -1) {
			int l = str.indexOf("<serCaller");
			int y = str.indexOf("</serCaller>");
			int z = str.indexOf(">");
			// str=str.replaceAll("<serCaller xmlns=","");
			str = str.substring(l, y);
			// int l=str.lastIndexOf(">");
			System.out.println(str.substring(z + 1, str.length()));
		}
		return null;
	}

	public void interfaceMonitoringLlog(String callingTime, String callingSide,
			String provider, String interFaceType, String interFaceMethod,
			String success, String exceptionLog, String keyword,
			String callDirection, String text) {
		InterfaceMonitoring interfaceMonitoring = new InterfaceMonitoring();
		interfaceMonitoring.setProvider(provider);
		interfaceMonitoring.setCallingSide(callingSide);
		interfaceMonitoring.setCallingTime(callingTime);
		interfaceMonitoring.setInterFaceMethod(interFaceMethod);
		interfaceMonitoring.setKeyword(keyword);
		interfaceMonitoring.setInterFaceType(interFaceType);
		interfaceMonitoring.setCallDirection("Horizontal");
		if (success.equals("Success")) {
			interfaceMonitoring.setSuccess("成功");
		} else {
			interfaceMonitoring.setSuccess("失败");
			interfaceMonitoring.setExceptionLog(exceptionLog);
		}
		interfaceMonitoring.setText(text);
		InterfaceMonitoringMgr attributes = (InterfaceMonitoringMgr) ApplicationContextHolder
				.getInstance().getBean("InterfaceMonitoringMgr");
		InterfaceMonitoringDaoHibernate InterfaceMonitoringDaoHibernate = new InterfaceMonitoringDaoHibernate();
		attributes.saveInterfaceMonitoring(interfaceMonitoring);
	}
	public void interfaceMonitoringLlogout(Map map) {
	    for(Iterator it = map.entrySet().iterator(); it.hasNext();){   
//	    	Tools
            System.out.println(it.next());

        } 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 InterfaceMonitoringUtil interfaceMonitoringUtil = new
		 InterfaceMonitoringUtil();
		// System.out.println(interfaceMonitoringUtil.testLink("http://localhost:8085/eoms/services/FilterRoles"));
		// interfaceMonitoringUtil.test("");
//		int q = -1;
//		int a = 1;
//		int s = -1 >>> a;
//		System.out.println(s + "");
		
		 Map test = new HashMap();
		 test.put("test1", "111");
		 test.put("test2", "222");
		 interfaceMonitoringUtil.interfaceMonitoringLlogout(test);
		 

	}

}
