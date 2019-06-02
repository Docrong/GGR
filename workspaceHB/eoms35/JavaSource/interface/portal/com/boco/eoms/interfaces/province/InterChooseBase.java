package com.boco.eoms.interfaces.province;

import java.io.IOException;
import org.jdom.JDOMException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;


public class InterChooseBase {
	private static String configPath = "/com/boco/eoms/interfaces/province/config/province-util.xml";
	
	public String isAlive() {
		System.out.println("收到省部接口握手信号");
		String isAliveResult = "";

		return isAliveResult;
	}

	/**
	 * 生成xml文件上传
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String upLoadBaseSN(String startTime, String endTime) {
		String result= "";
		InterFaceUtil interFaceUtil =new InterFaceUtil();
		String provinceID=XmlManage.getFile(configPath).getProperty("Interface.ProvinceID");
	
		ICommonFaultMainManager commonFaultService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
	    java.util.List mainLi = null;
	    try {
	    	mainLi = commonFaultService.getHoldedSheetListByTime(startTime,endTime);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		 try {
			interFaceUtil.BuildXMLDoc(provinceID,"001", startTime, endTime,mainLi);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		IComplaintMainManager complaintMainService = (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
		try {			
			mainLi = complaintMainService.getHoldedSheetListByTime(startTime,endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			interFaceUtil.BuildXMLDoc(provinceID,"002", startTime, endTime,mainLi);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		return result;
		
	}
	/**
	 * 生成htmlwenj
	 * @param BaseSN
	 * @param BaseType
	 * @return
	 */
	public String upLoadBaseInfo(String BaseSN, String BaseType) {
		String result = "";
		String url="";
		
		String htmlPath = XmlManage.getFile(configPath).getProperty("Interface.Ftp.HtmlPath");
		String provinceID = XmlManage.getFile(configPath).getProperty("Interface.ProvinceID");
		String fileName = provinceID+"_"+BaseType+"_"+BaseSN;
		if(BaseType.equals("001")){
			url=XmlManage.getFile(configPath).getProperty("Interface.Url");
			ICommonFaultMainManager commonFaultService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
			CommonFaultMain main = (CommonFaultMain)commonFaultService.getMainBySheetId(BaseSN);	
			main.setReportFlag(2);
    		commonFaultService.saveOrUpdateMain(main);
		}
		else{
			url=XmlManage.getFile(configPath).getProperty("Interface.AppUrl");
			IComplaintMainManager complaintMainService = (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");
			ComplaintMain main = (ComplaintMain)complaintMainService.getMainBySheetId(BaseSN);	
			main.setReportFlag(2);
    		complaintMainService.saveOrUpdateMain(main);
		}
		SheetStaticMethod.UrlToHtml(url+BaseSN,htmlPath,fileName+".html");
		
		InterFaceUtil interFaceUtil =new InterFaceUtil();
		interFaceUtil.getUPloadHtml(htmlPath,fileName+".html");
		return result;
	}
	public static void main(String agrs[]) {
		InterChooseBase main = new InterChooseBase();
		main.upLoadBaseSN("2009-1-25 17:59:43","2009-2-29 17:59:43");
	}
}
