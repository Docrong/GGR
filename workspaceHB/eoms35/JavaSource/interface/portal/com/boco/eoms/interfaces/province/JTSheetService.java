package com.boco.eoms.interfaces.province;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.interfaces.province.service.IJTSheetInterfaceService;
import com.boco.eoms.interfaces.province.util.JTSheetInterfaceUtils;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.StaxParser;


/**
 * 集团EOMS系统和省EOMS系统互联接口类
 * @author qinmin
 *
 */
public class JTSheetService {
	IJTSheetInterfaceService jtSheetInterfaceService = 
		   (IJTSheetInterfaceService)ApplicationContextHolder.getInstance().getBean("IJTSheetInterfaceService");
	/**
	 * 连通测试
	 * @param isAlive
	 * @param 
	 * @return isAliveResult(string类型。为空表示相应服务可用，非空表示该服务当前出现问题，错误返回信息用相应明文进行说明)
	 * @throws 执行报错 RuleToolXMLException
	 */
	public String isAlive() {
		return "";
	}
	/**
	 * 新建工单接口
	 * @param serSupplier String(30) 服务提供方
	 * @param serCaller String(30) 服务调用方
	 * @param callerPwd String(30) 服务密码
	 * @param callTime String(30) 服务调用时间
	 * @param sheetType String(30) 工单类别
	 * @param sheetSN  String(30) 工单流水号
	 * @param userID String(30) 用户集团账号
	 * @param opDetail 详细信息
	 * @param attachment 附件信息
	 * @return result 输出为一个字符串
	 */
	public String newSheet(String serSupplier, String serCaller,String callerPwd, String callTime, String sheetType,String sheetSN,
			String userID,String opDetail,String attachment){
		System.out.println("JTSheetService newSheet start");
		System.out.println((new StringBuilder("sheetType=")).append(sheetType).toString());
		System.out.println((new StringBuilder("sheetSN＝")).append(sheetSN).toString());
		System.out.println((new StringBuilder("userID＝")).append(userID).toString());
		System.out.println((new StringBuilder("opDetail＝")).append(opDetail).toString());
		System.out.println((new StringBuilder("attachment＝")).append(attachment).toString());
		System.out.println((new StringBuilder("callerPwd＝")).append(callerPwd).toString());

		
		String NameSP = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/JISheetService-util.xml").getProperty("webServicesParam.NameSP");
		String SOAPAction = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/JISheetService-util.xml").getProperty("webServicesParam.SOAPAction");
		//根据不同的工单类型来获取不同调用地址
		String  sheetName="";
		if(sheetType!=null&&"051".equals(sheetType)){//故障工单
			sheetName="commonfault";
		}else if(sheetType!=null&&"001".equals(sheetType)){//通用任务工单
			sheetName="commontask";
		}
		String url = StaticMethod.nullObject2String(XmlManage.getFile("/com/boco/eoms/interfaces/province/config/JISheetService-util.xml").getProperty((new StringBuilder("webServicesParam.serviceUrl.")).append(sheetName).toString()));

		String strRetrun = "";
		try {
			Service service = new Service();
			String xmlns = NameSP;
			Call call = (Call)service.createCall();
			call.setOperationName(new QName(xmlns, "newEOMSSheet"));
			call.setSOAPActionURI(SOAPAction);
			call.addParameter(new QName(xmlns, "serSupplier"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "serCaller"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "callerPwd"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "callTime"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "sheetType"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "sheetSN"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "userID"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "opDetail"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(xmlns, "attachment"), XMLType.XSD_STRING, ParameterMode.IN);
			call.setTargetEndpointAddress(new URL(url));
			call.setReturnType(XMLType.XSD_STRING);
			strRetrun = (String)call.invoke(new Object[] {serSupplier, serCaller, callerPwd, callTime,sheetType,sheetSN,userID, opDetail,attachment});
			//strRetrun="clearTime =2013-10-18 18:19:23;errList=";
			BocoLog.info(JTSheetService.class,"部省接口调用成功，返回信息是："+strRetrun);
		 } catch(Exception e) {            
			 BocoLog.info(JTSheetService.class,"部省接口失败，失败信息是："+e.getMessage());  
			 e.printStackTrace();
		 }
		 return strRetrun;
	}
	/**
	 * 新建任务或者故障工单接口
	 * @param serSupplier String(30) 服务提供方
	 * @param serCaller String(30) 服务调用方
	 * @param callerPwd String(30) 服务密码
	 * @param callTime String(30) 服务调用时间
	 * @param sheetType String(30) 工单类别
	 * @param sheetSN  String(30) 工单流水号
	 * @param userID String(30) 用户集团账号
	 * @param opDetail 详细信息
	 * @param attachment 附件信息
	 * @return result 输出为一个字符串
	 */
	public String newEOMSSheet(String serSupplier, String serCaller,String callerPwd, String callTime, String sheetType,String sheetSN,
		String userID,String opDetail,String attachment){
	   System.out.println("JTSheetService newSheet start");
		System.out.println((new StringBuilder("sheetType=")).append(sheetType).toString());
		System.out.println((new StringBuilder("sheetSN＝")).append(sheetSN).toString());
		System.out.println((new StringBuilder("userID＝")).append(userID).toString());
		System.out.println((new StringBuilder("opDetail＝")).append(opDetail).toString());
		System.out.println((new StringBuilder("attachment＝")).append(attachment).toString());
		System.out.println((new StringBuilder("callerPwd＝")).append(callerPwd).toString());

	   Map returnResultMap=new HashMap();//存放接口返回字段对应的值
	   Map returnCnNameMap=new HashMap();//存放接口返回字段对应的中文名称
	   String result="";//处理结果
	   String errorList="";//接口处理错误信息
	   String reSheetSN="";//省派生工单流水号
	   String openUrl="";//省端工单查看url
	   try{
		  HashMap interfaceInfoMap = new HashMap();
		  interfaceInfoMap.put("serSupplier", serSupplier);
		  interfaceInfoMap.put("serCaller", serCaller);
		  interfaceInfoMap.put("callerPwd", callerPwd);			
		  if(callTime==null||"".equals(callTime)){
			Date curTime = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			callTime = df.format(curTime); 
		   }
		  interfaceInfoMap.put("callTime", callTime);
		  interfaceInfoMap.put("sheetType", sheetType);
		  interfaceInfoMap.put("sheetSN", sheetSN);
		  interfaceInfoMap.put("userID", userID);
		  //解析接口传递过来的opdetail信息
		  List opDetailList= StaxParser.getInstance().getOpdetailList(opDetail,interfaceInfoMap);
		  if(opDetailList!=null&&opDetailList.size()>0){			
			  //判断传递过来的字段信息是否满足长度要求
			  String checkLengthResult=JTSheetInterfaceUtils.checkLength(opDetailList);
			  //判断传递过来的字段信息里面是否满足编码映射关系(调试的时候再说)			  
			  if("".equals(checkLengthResult)){
				  //验证建单用户的合法性
				  String sendUserId=JTSheetInterfaceUtils.checkUserInfo(userID);
				  //验证该工单是否已经派发过
				  String provinceSheetId=jtSheetInterfaceService.getSheetIdByGroupSheetId(sheetSN, sheetType);
					System.out.println((new StringBuilder("sendUserId==")).append(sendUserId).append("||provinceSheetId==").append(provinceSheetId).toString());
				  if(!"".equals(sendUserId)&&"".equals(provinceSheetId)){
					  HashMap opDetailMap=(HashMap)opDetailList.get(0); 
					  opDetailMap.put("sender", sendUserId);
					//根据工单类型判断是故障工单还是任务工单
					if(sheetType!=null&&"051".equals(sheetType)){//故障工单
						String sheetInfo=jtSheetInterfaceService.newCentralCommonfaultSheet(opDetailMap); 
						if(sheetInfo!=null&&!"".equals(sheetInfo)){
						  String[] sheetInfos=sheetInfo.split(";");	
						  reSheetSN=sheetInfos[0];
						  String centralCommonfaultSheetUrl = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/JISheetService-util.xml").getProperty("centralCommonfaultSheetUrl");
							openUrl = (new StringBuilder(String.valueOf(centralCommonfaultSheetUrl))).append("&sheetKey=").append(sheetInfos[1]).toString();

						  result="0";
						  jtSheetInterfaceService.saveSheetIdByGroupSheetId(sheetSN, reSheetSN, sheetType);
						}					
					}else if(sheetType!=null&&"001".equals(sheetType)){//通用任务工单
						//解析接口传递过来的attachment信息
						System.out.println("get attachment start");
						List attachList = JTSheetInterfaceUtils.getAttachRefFromXml(attachment);
						System.out.println("get attachment end");
						String sheetInfo=jtSheetInterfaceService.newCommonTaskSheet(opDetailMap,attachList);
						if(sheetInfo!=null&&!"".equals(sheetInfo)){
							String[] sheetInfos=sheetInfo.split(";");	
							reSheetSN=sheetInfos[0];
							String centralCommonfaultSheetUrl = XmlManage.getFile("/com/boco/eoms/interfaces/province/config/JISheetService-util.xml").getProperty("commonTaskSheetUrl");
							openUrl = (new StringBuilder(String.valueOf(centralCommonfaultSheetUrl))).append("&sheetKey=").append(sheetInfos[1]).toString();
							result="0";
							jtSheetInterfaceService.saveSheetIdByGroupSheetId(sheetSN, reSheetSN, sheetType);
					    }
					}else {
					   result="2";
					   errorList="sheetNo=;errList=工单类别编码不满足接口规范";  
					} 
				  }else{
					  
					  if(sendUserId==null||"".equals(sendUserId)){
						  result="2";
						  errorList="sheetNo=;errList=用户验证失败"; 
					  }
					  if(!"".equals(provinceSheetId)){
						  result="1";
						  errorList="sheetNo=;errList=该张工单已经转单"; 
					  }
				  }				  
			  }else if(!"".equals(checkLengthResult)){//接口字段中存在不满足长度要求
				  result="2";
					errorList = (new StringBuilder("sheetNo=;errList=")).append(checkLengthResult).toString();

			  }
		  }else{//接口字段不满足接口规范字段
			  result="2";
			  errorList="sheetNo=;errList=接口参数opDetail字段值不满足接口规范";		  
		  }		  		  
	   }catch(Exception e){
		   e.printStackTrace();
		   result="2";
			errorList = (new StringBuilder("sheetNo=;errList=工单新增失败，失败消息为")).append(e.getMessage()).toString();

	   }
	   //拼接接口返回字符串
	   returnResultMap.put("sheetSN", sheetSN);
	   returnCnNameMap.put("sheetSN", "集团工单流水号");
	   returnResultMap.put("result", result);
	   returnCnNameMap.put("result", "处理结果");
	   returnResultMap.put("reSheetSN", reSheetSN);
	   returnCnNameMap.put("reSheetSN", "省派生工单流水号");
	   returnResultMap.put("openUrl", openUrl);
	   returnCnNameMap.put("openUrl", "工单url");
	   returnResultMap.put("errorList", errorList);
	   returnCnNameMap.put("errorList", "错误信息描述");
	   String returnResult=JTSheetInterfaceUtils.createOpDetailXml(returnResultMap,returnCnNameMap);
	   System.out.println(returnResult);
	   return returnResult;
	}
}
