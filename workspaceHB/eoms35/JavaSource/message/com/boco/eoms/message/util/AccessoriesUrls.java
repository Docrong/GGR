package com.boco.eoms.message.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesConfigManager;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;

public class AccessoriesUrls {

	/**
	 * @param args
	 */
	

	public String[] saveAccessories(String accessoriesUrls) {
		String appCode = MsgHelp
		.getSingleProperty("//message/email/accessoriesAppCode");
		// 所有附件路径和真实名字的字符传list
		List fileList = new ArrayList();
		fileList = MsgHelp.String2List(accessoriesUrls);
		int len = fileList.size();
		// 附件的路径数组
		String urlArray[] = new String[accessoriesUrls.split(",").length];
		// 真实名字的list
		List realNameList = new ArrayList();
		String url = null;
		String realName = null;
		ITawCommonsAccessoriesConfigManager configManager = (ITawCommonsAccessoriesConfigManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsAccessoriesConfigManager");
		ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsAccessoriesManager");
		// 附件管理配置号码

		// 时间戳
		String date = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");

		String path = null;
		// 邮件附件的绝对路径
		String eMailUrl;
		for (int i = 0; i < len; i++) {
			String urls = (String) fileList.get(i);
			url = urls.substring(0, urls.indexOf("#"));
			urlArray[i] = url;
			realName = urls.substring(urls.indexOf("#") + 1);
			realNameList.add(realName);
			System.out.println(urlArray[i]);

			// 文件别名
			String onlyName = date + realName.substring(realName.indexOf("."));
			File file = new File(url);
			// 文件大小
			long size = file.length();
			// 文件路径
			String filePath = "classpath:accessories/uploadfile";
			// 文件真实名称
			// String realName=file.getName();

			TawCommonsAccessoriesConfig config;
			try {
				config = configManager.getAccessoriesConfigByAppcode(appCode);
				path = filePath.substring(filePath.indexOf(":") + 1, filePath
						.length())
						+ config.getPath();
				// 文件根路径
				String rootFilePath = AccessoriesMgrLocator
						.getTawCommonsAccessoriesManagerCOS().getFilePath(
								appCode);
				// 文件绝对路径
				eMailUrl = rootFilePath + onlyName;
				mgr.downFromOtherSystem(onlyName, url, appCode);
			} catch (AccessoriesConfigException e) {
				e.printStackTrace();
			}

		}

		return urlArray;

	}

	public String getaccessoriesRealUrls(String accessoriesUrls) {
		String appCode = MsgHelp
		.getSingleProperty("//message/email/accessoriesAppCode");
		String localUrls = "";
		// 所有附件路径和真实名字的字符传list
		List fileList = new ArrayList();
		fileList = MsgHelp.String2List(accessoriesUrls);
		int len = fileList.size();
		// 真实名字的list
		List realNameList = new ArrayList();
		String realName = "";
		// 时间戳
		String date = StaticMethod.getCurrentDateTime("yyyyMMddHHmmss");
		String temp = "";
		// 邮件附件的绝对路径
		String eMailUrl = "";
		for (int i = 0; i < len; i++) {
			String urls = (String) fileList.get(i);
			realName = urls.substring(urls.indexOf("#") + 1);
			realNameList.add(realName);
			// 文件别名
			String onlyName = date + realName.substring(realName.indexOf("."));
			try {
				// 文件根路径
				String rootFilePath = AccessoriesMgrLocator
						.getTawCommonsAccessoriesManagerCOS().getFilePath(
								appCode);
				// 文件绝对路径
				eMailUrl = rootFilePath + realName;
				temp += eMailUrl + ",";
			} catch (AccessoriesConfigException e) {
				e.printStackTrace();
			}
		}
		localUrls = temp.substring(0, temp.length() - 1);
		
//		List urlList=MsgHelp.String2List(accessoriesUrls);
//		int len=urlList.size();
//		String localUrls="";
//		String str="";
//		for(int i=0;i<len;i++){
//			String temp=(String)urlList.get(i);
//			str=temp.substring(0,temp.indexOf("#"));
//			localUrls+=str+",";
//		}
//		
//		System.out.println(localUrls);
		return localUrls;
	}
	
	/**
	 * 
	 * @param localAccessoryUrl 
	 * 			附件地址
	 * @return
	 */
	
	
	public String getAccessoryUrl(String localAccessoryUrl){
		String appCode1 = MsgHelp
		.getSingleProperty("//message/mms/accessoriesAppCode");
		//文件在服务器上的地址
		String remoteAccessoryUrl = "" ;
//		//文件真实名字
		int len = localAccessoryUrl.lastIndexOf("/") ;
		String realName = localAccessoryUrl.substring(len+1,localAccessoryUrl.length()) ;
		//文件类型
//		String type = realName.substring(realName.indexOf("."),realName.length()) ;
		//时间戳
		String date = StaticMethod.getCurrentDateTime("yyyyMMddHHmmssSSS") ;
		//文件别名
		String onlyName = date + realName.substring(realName.indexOf(".")) ;
		ITawCommonsAccessoriesConfigManager configManager = (ITawCommonsAccessoriesConfigManager) ApplicationContextHolder
															.getInstance().getBean("ItawCommonsAccessoriesConfigManager");
		ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager) ApplicationContextHolder
											.getInstance().getBean("ItawCommonsAccessoriesManager");
		//文件路径
		String path = null ;
		String url = localAccessoryUrl.substring(0,localAccessoryUrl.indexOf(realName))+onlyName;
//		String filePath = "classpath:accessories/uploadfile" ;
		TawCommonsAccessoriesConfig config;
		try {
			// 文件根路径
			String rootFilePath = AccessoriesMgrLocator
									.getTawCommonsAccessoriesManagerCOS().getFilePath(appCode1);
			//文件绝对路径
			config = configManager.getAccessoriesConfigByAppcode(appCode1) ;
//			path = filePath.substring(filePath.indexOf(":") + 1, filePath.length()) + config.getPath();
			path = rootFilePath + realName ;
			remoteAccessoryUrl = rootFilePath + onlyName ;
			mgr.downFromOtherSystem(onlyName, localAccessoryUrl, appCode1);
		} catch (AccessoriesConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更改文件名使其唯一
//		File file = new File(path) ;
//		boolean temp = false ;
//		while(!temp){
//			temp = file.renameTo(new File(remoteAccessoryUrl));
//		} 
		System.out.println("*********************************") ;
		System.out.println("***  path : " + path);
		System.out.println("***  onlyName: "+onlyName) ;
		System.out.println("***  realName: "+realName) ;
		System.out.println("***  remoteAccessoryUrl: "+remoteAccessoryUrl) ;
		System.out.println("***  url: "+url) ;
		System.out.println("*********************************") ;
		return remoteAccessoryUrl ;
	}
	
	public static void main(String[] args) {
//		String files = "http://10.32.1.23:8087/eoms/呵呵.txt#呵呵.txt,http://10.32.1.23:8087/eoms/duty/model/new/21/20080617145328.xls#fgsfd.xls";
//		AccessoriesUrls aa = new AccessoriesUrls();
//		aa.saveAccessories(files) ;
//		aa.getaccessoriesRealUrls(files);
		
//		String path = "http://10.32.1.128:8081/EOMS_J2EE/呵呵哈哈.txt";
		String path = "http://10.32.1.24:8089/eoms/accessories/uploadfile/test.doc";
//		AccessoriesUrls aa = new AccessoriesUrls() ;
		try {
//			System.out.println(aa.getAccessoryUrl(path) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
