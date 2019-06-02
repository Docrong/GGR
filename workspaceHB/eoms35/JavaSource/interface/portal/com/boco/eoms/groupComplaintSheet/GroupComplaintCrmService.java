/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.groupComplaintSheet;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.groupcheck.webapp.action.GroupCheckMethod;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;
import com.boco.eoms.sheet.groupcomplaint.service.impl.GroupComplaintCrmManagerImpl;
import com.boco.eoms.util.InterfaceUtil;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintCrmService {
	
	public String getCommonInfo(String opDetail)
	  {
	    String result = "";
	    System.out.println("opDetail==lyg=="+opDetail);
	    try {
	      InterfaceUtil interfaceUtil = new InterfaceUtil();
	      IGroupComplaintMainManager mainservice = (IGroupComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintMainManager");
	      GroupComplaintMain main = new GroupComplaintMain();
	      
	      HashMap sheetMap = new HashMap();

	      if ((opDetail != null) && (!"".equals(opDetail)))
	      {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        String sheetId = StaticMethod.nullObject2String(sheetMap.get("complaint_sheetId"));//投诉工单号
	        String if_Match = StaticMethod.nullObject2String(sheetMap.get("if_Match"));//是否匹配
	        String mainCommSheetId = StaticMethod.nullObject2String(sheetMap.get("comm_sheetId"));//故障工单号
	        String group_Type = StaticMethod.nullObject2String(sheetMap.get("group_Type"));//查询类型
	        System.out.println("sheetId="+sheetId+"====if_Match="+if_Match+"====mainCommSheetId="+mainCommSheetId+"===group_Type="+group_Type);
	        if ((sheetId != null) && (!"".equals(sheetId))) {
	          main = (GroupComplaintMain)mainservice.getMainBySheetId(sheetId);
	          if (main != null) {
	        	  if("1".equals(group_Type)){
	  	        	 if("0".equals(if_Match)){//成功
	  	        		 main.setMainIfSelf("是");
	  	        	 }else{
	  	        		main.setMainIfSelf("否");
//	  	        		触发集团核查派单
	  					GroupCheckMethod method = new GroupCheckMethod();
	  					String mainId = main.getId();
	  					sheetId = main.getSheetId();
	  					GroupComplaintCrmManagerImpl crm = new GroupComplaintCrmManagerImpl();
	  					Map returnMap = (Map)crm.getComplaintById(mainId);
	  					System.out.println("sheetId=lyg="+sheetId);
	  					String title = StaticMethod.nullObject2String(main.getTitle());
	  					System.out.println("title=lyg="+title);
//	  					电路代号
	  					String electricCode  = StaticMethod.nullObject2String(returnMap.get("electricCode"));
	  					System.out.println("electricCode=lyg="+electricCode);
	  					//产品实例标识
	  					String indexFlag  = StaticMethod.nullObject2String(returnMap.get("indexFlag"));
	  					System.out.println("indexFlag=lyg="+indexFlag);
	  					//投诉时间
	  					String complaintTime  = StaticMethod.nullObject2String(returnMap.get("complaintTime"));
	  					System.out.println("complaintTime=lyg="+complaintTime);
	  					
	  					String operateroleid  = StaticMethod.nullObject2String(returnMap.get("operateroleid"));
	  					System.out.println("operateroleid=lyg="+operateroleid);
	  					
	  					if(complaintTime.indexOf(".")>0){
	  						complaintTime = complaintTime.substring(0, complaintTime.indexOf("."));
	  					}
	  					//所属地市
	  				    String cityName = StaticMethod.nullObject2String(main.getCityName());
	  				    System.out.println("cityName=lyg="+cityName);
	  					HashMap flowMap = new HashMap();
	  					
	  					flowMap.put("title", title);
	  					flowMap.put("mainGroupSheetId", sheetId);//集团投诉工单
	  					flowMap.put("mainProductIns", indexFlag);//产品实例标识
	  					flowMap.put("mainCircuitCode", electricCode);//电路代号
	  					flowMap.put("mainUserAffilia", cityName);//用户归属地
	  					flowMap.put("mainComplaintTime", complaintTime);//用户归属地
	  					flowMap.put("operateroleid", operateroleid);
	  					try {
	  						method.getNewFlow(flowMap);
						} catch (Exception e) {
							System.out.println("集中故障调用接口返回 是否自动发现为否 调用集团核查工单 报错！！");
							e.printStackTrace();
						}
	  					
	  	        		 
	  	        	 }
	  	           }else if("2".equals(group_Type)){
	  	        	 if("0".equals(if_Match)){//成功
	  	        		 main.setMainIfMatch("是");
	  	        		 main.setMainCommSheetId(mainCommSheetId);
	  	        	 }else{
	  	        		main.setMainIfMatch("否");
	  	        	 } 
	  	           } 
	               
	        	  mainservice.saveOrUpdateMain(main);
	             

//	            opDetail = hoUtil.getXmlFromMap(mainMap, StaticMethod.getFilePathForUrl("classpath:config/handgroupcomplaint.xml"), nodeName);
	            result = "Status=0;Errlist=";
	            System.out.println("getSheetInfoService==lyg="+result);
	            return result;
	          }

	          result = "Status=-1;sheetDetail=;Errlist=工单详情接口没有找到工单流水号为 " + sheetId + " 的工单未找到或不存在,请查证！";
	          return result;
	        }

	        result = "Status=-1;sheetDetail=;Errlist=工单详情接口没有找到对应工单流水号Sheet_id的参数,请查证！";
	        return result;
	      }

	      result = "Status=-1;sheetDetail=;Errlist=工单详情接口没有传入参数,请查证！";

	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单详情接口出错！详细信息为" + e.getMessage();
	    }
	    System.out.println("getSheetInfoService="+result);
	    return result;
	  }
	
	
}
