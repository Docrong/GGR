package com.boco.eoms.safetyproblemgm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.product.service.bo.SpecialLineBO;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.crm.util.SheetMapingSchema;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.webapp.action.NewIBaseSheet;
import com.boco.eoms.sheet.groupcomplaint.service.impl.GroupComplaintCrmManagerImpl;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.interfaceInfo.util.IODate;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.XmlDom4jSX;

public class EOMSSafeProService {
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
	 * serSupplier	string(16)	服务提供方
serCaller	string(16)	服务调用方
callerPwd	string(32)	口令
callTime	string(20)	服务调用时间
opDetail	String	详细信息
attachRef	String	附件信息

	 */
	public String newWorkSheet(String serSupplier,String serCaller,String callerPwd,String callTime,String opDetail,String attachRef){
		System.out.println("crm新建工单");
		
		//生成一个时间戳，作为文件名
		//sheettype,serviceType,serialNo,opDetail存到一个单独的文件中。
		
		
		//调用FTP客户端程序，将该文件上传到对方的服务器
		//记录我们的日志到一个单独的文件
		
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);

		HashMap sheetMap = new HashMap();
		
		sheetMap.put("sheetType", "85");
		sheetMap.put("serviceType", "1");
		
		sheetMap.put("serSupplier", serSupplier);
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		String sheetId = "";
		String result = "";
		//
		try{
//			保存报文至本地文件夹
			InterfaceUtil interfaceUtil = new InterfaceUtil();
	        List attach = interfaceUtil.getAttachRefFromXml(attachRef);		
	        sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        EomsLoader loader = new EomsLoader();
	        sheetId = loader.newWorkSheet(sheetMap, attach);
	        System.out.println("sheetId==lyg==1=="+sheetId);
	        result = "sheetNo="+sheetId+";errList=";
		}catch(Exception err){
			err.printStackTrace();
			result = "sheetNo=;errList="+err.getMessage();
		}
		
		return result;
	}

}
