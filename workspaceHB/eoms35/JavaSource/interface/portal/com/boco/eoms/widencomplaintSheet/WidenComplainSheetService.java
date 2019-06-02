package com.boco.eoms.widencomplaintSheet;

import java.util.HashMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.handheldoperation.HandheldOperationUtil;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.interfaceBase.util.XmlUtil;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintLink;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintMain;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintTask;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintLinkManager;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintMainManager;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintTaskManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WidenComplainSheetService {
	 public String getTodoListService(String opDetail)
	  {
	    String result = "";
	    HashMap opDetailMap = new HashMap();
	    InterfaceUtil interfaceUtil = new InterfaceUtil();
	    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
	    //Todo_type	待办工单分类	全量待办：0；即将超时：1；已经超时：2；未接单 3
	    try {
	      String nodeName = "todoList";
	      System.out.println("WidenCompiaintSheetService.getTodoListService内对应nodeName======" + nodeName);
	      System.out.println("--------ph:opDateail-----------" + opDetail);
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
	        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
	        System.out.println("--------ph:user_id-----------" + user_id); 
	        String todo_type = StaticMethod.nullObject2String(opDetailMap.get("Todo_type"));
	        System.out.println("--------lyg:todo_type-----------" + todo_type); 
	        
	        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records")) - 1;
	        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
	        if (("".equals(user_id)) || (user_id == null)) {
	          result = "Status=-1;sheetDetail=;Errlist=待办工单列表查询接口出错！用户名在EOMS中不存在。";
	          return result; 
	        } 
	        String hql = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("sql.todoListSql");
	        hql = ExcelConverterUtil.replaceAll(hql, "@userId@", user_id);
	        
	        //判断待办工单的类型
	        if("1".equals(todo_type)){//即将超时 
	        	hql = hql + " and m.sendtime >sysdate-8 and task.taskstatus = '8'  ";
	        }else if("2".equals(todo_type)){//已经超时
	        	hql = hql +" and m.sendtime > sysdate-24 and m.sendtime<sysdate-8  and task.taskstatus = '8' ";
	        }else if("3".equals(todo_type)){//未接单
	        	hql = hql +" and task.taskstatus = '2' ";
	        }
	        
	        
	        String countSql = "select count(distinct m.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
	        hql = hql + " order by task.createtime desc ";
	        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ > " + start_records;
	        System.out.println("--------todolistsql-----------" + sql);
	        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	        List taskList = services.getSheetAccessoriesList(sql);
	        List countList = services.getSheetAccessoriesList(countSql);
	        Map countMap = (Map)countList.get(0);
	        String todo_totals = StaticMethod.nullObject2String(countMap.get("num"));
	        opDetail = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+ hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml"), nodeName, todo_totals);
	        System.out.println("---result--opDetail--"+opDetail);
	        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
	        return result;
	      }
	      result = "Status=-1;sheetDetail=;Errlist=待办工单列表查询接口没有传入opDetail参数,请查证!";
	      return result;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=待办工单列表查询接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }
	 
	 
	 public String getFinishSheetListService(String opDetail)
	  {
	    String result = "";
	    HashMap opDetailMap = new HashMap();
	    InterfaceUtil interfaceUtil = new InterfaceUtil();
	    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
	    try {
	      String nodeName = "finishList";
	      System.out.println("WidenCompiaintSheetService.getFinishSheetListService内对应nodeName======" + nodeName);
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
	        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
	        System.out.println("--------ph:user_id-----------" + user_id);
	        String time_start = StaticMethod.nullObject2String(opDetailMap.get("Start_time"));
	        String time_end = StaticMethod.nullObject2String(opDetailMap.get("End_time"));
	        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
	        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
	        String hql = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("sql.finishtodoList");
	        if ("".equals(time_start))
	        {
	          Calendar canlendar = Calendar.getInstance();
	          canlendar.add(5, -3);
	          time_start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(canlendar.getTime());
	        }
	        if ("".equals(time_end))
	        {
	          Date date = new Date();
	          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          time_end = df.format(date);
	        }
	        if (end_records == 0) {
	          end_records = 15;
	        }
	        hql = ExcelConverterUtil.replaceAll(hql, "@startSime@", " and main.sendtime >=to_date('" + time_start + "','yyyy-MM-dd HH24:mi:ss')");
	        hql = ExcelConverterUtil.replaceAll(hql, "@endSime@", "and main.sendtime <=to_date('" + time_end + "','yyyy-MM-dd HH24:mi:ss')");
	        hql = ExcelConverterUtil.replaceAll(hql, "@userId@", user_id);
	       
	        String sheet_totals = "";
	        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");

	        String countSql = "select count(distinct main.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
	        System.out.println("---------已办列表数量SQL-----------ph-----------------" + countSql);
	        List countList = services.getSheetAccessoriesList(countSql);
	        Map countMap = (Map)countList.get(0);
	        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
	        System.out.println("-----已办列表数量------------ph-----------------" + sheet_totals);

	        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
	        List taskList = services.getSheetAccessoriesList(sql);

	        for (int i = 0; i < taskList.size(); i++) {
	          Map map = (Map)taskList.get(i);
	          if ((map.get("status") != null) && ("0".equals(map.get("status").toString())))
	            map.put("status", "运行中");
	          else if ((map.get("status") != null) && ("1".equals(map.get("status").toString())))
	            map.put("status", "已归档");
	          else if ((map.get("status") != null) && (!"".equals(map.get("status")))) {
	            map.put("status", "其它");
	          }
	        }
	        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml"), nodeName, sheet_totals);
	        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
	        return result;
	      }
	      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口没有传入opDetail参数,请查证！";
	      return result;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=已办工单列表查询接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }


	  public String getSheetInfoService(String opDetail)
	  {
	    String result = "";
	    try {
	      InterfaceUtil interfaceUtil = new InterfaceUtil();
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
	      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
	      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
	      ITawSystemAreaManager areaservice = (ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
	      WidenComplaintMain main = new WidenComplaintMain();

	      String nodeName = "dataService";
	      System.out.println("WidenCompiaintSheetService.getSheetInfoService内对应nodeName======" + nodeName);
	      HashMap sheetMap = new HashMap();
	      HandheldOperationUtil hoUtil = new HandheldOperationUtil();
	      if ((opDetail != null) && (!"".equals(opDetail)))
	      {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
	        if ((sheet_id != null) && (!"".equals(sheet_id))) {
	          main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	          if (main != null) {
	            String toDeptId = main.getToDeptId();
	            System.out.println("---工单信息查询------toDeptIdï¼" + toDeptId);
	            if ((!"".equals(toDeptId)) && (toDeptId != null)) {
	              TawSystemArea area = areaservice.getAreaByAreaId(toDeptId);
	              String areaName = area.getAreaname();
	              main.setToDeptId(areaName);
	            }
	            Map mainMap = SheetBeanUtils.bean2Map(main);
	            if ("1".equals(StaticMethod.nullObject2String(main.getStatus()))) {
	              mainMap.put("taskStatus", "已归档");
	            } else {
	              String condition = " sheetId = '" + sheet_id + "' and taskstatus in ('2','8','5')";
	              List taskList = taskservice.getTasksByCondition(condition);
	              if ((taskList != null) && (taskList.size() > 0)) {
	                WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
	                System.out.print("查詢出數據");
	                String taskStatus = StaticMethod.nullObject2String(task.getTaskStatus());
	                if ("2".equals(taskStatus))
	                  mainMap.put("taskStatus", "待受理");
	                else if ("8".equals(taskStatus))
	                  mainMap.put("taskStatus", "处理中");
	              }
	              else {
	                result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到工单流水号为 " + sheet_id + " 的工单未找到或不存在,请查证！";
	                return result;
	              }

	            }
	            //条件查询link 
	            String conditionLink = " mainId ='"+main.getId()+"' and operateType='102' order by operatetime";
	            List linkList = linkservice.getLinksBycondition(conditionLink);
	            String linkSpeed = "";//'20160520140918.gif','20160520140939.gif'
	            String linkReceipt = "";
	            String accessoriesUrl = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("accessoriesurl");
	            if(linkList != null && linkList.size()>0){
	            	WidenComplaintLink link = (WidenComplaintLink)linkList.get(0);
	            	linkSpeed = StaticMethod.nullObject2String(link.getLinkSpeed());
	            	linkReceipt = StaticMethod.nullObject2String(link.getLinkReceipt());
	            	if(!"".equals(linkSpeed)){
	            		linkSpeed = photoStr(linkSpeed,accessoriesUrl);
	            	}
	            	if(!"".equals(linkReceipt)){
	            		linkReceipt = photoStr(linkReceipt,accessoriesUrl);
	            	}
	            }
//	            mainMap.put("accessoriesUrl", accessoriesUrl);
//	            mainMap.put("linkSpeed", linkSpeed);
//	            mainMap.put("linkReceipt", linkReceipt);
	            opDetail = hoUtil.getXmlFromMap(mainMap, StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml"), nodeName);
	            String tem ="<Speed_Photo>"+linkSpeed+"</Speed_Photo><Receipt_Photo>"+linkReceipt+"</Receipt_Photo></recordInfo></opDetail>";
	            System.out.println("linkSpeed==="+linkSpeed);
	            System.out.println("linkReceipt==="+linkReceipt);
	            System.out.println("=====lyg===="+tem);
	            opDetail = opDetail.replace("</recordInfo></opDetail>", tem);
	            System.out.println("opDetail==="+opDetail); 
	            result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
	            return result;
	          }

	          result ="Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到工单流水号为 " + sheet_id + " 的工单未找到或不存在,请查证！";
	          return result;
	        }

	        result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到对应工单流水号Sheet_id的参数,请查证！";
	        return result;
	      }

	      result = "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有传入参数,请查证！";

	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      result =  "Status=-1;sheetDetail=;Errlist=工单数据服务接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }
	  
	  
	  
	  /**
	   * 
	   * @param photo '20160520140918.gif','20160520140939.gif'
	   * @return
	   */
	  public String photoStr (String photo,String accessoriesUrl){
		  IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		  String accessoriesSql = "select * from taw_commons_accessories where accessoriesname in ("+photo+")";
		  String accessoresRet = "";
		  try {
			  if(photo != null && !"".equals(photo)){
				  List accessorList = services.getSheetAccessoriesList(accessoriesSql);
				  for(int i=0;accessorList != null&&i<accessorList.size();i++){
					  String accessoriesname = StaticMethod.nullObject2String(((Map) accessorList.get(i)).get("accessoriesname"));
					  String accessoriescnname = StaticMethod.nullObject2String(((Map) accessorList.get(i)).get("accessoriescnname"));
					  String id = StaticMethod.nullObject2String(((Map) accessorList.get(i)).get("id"));
					  //http://10.30.227.22:9080/accessories/tawCommonsAccessoriesConfigs.do?method=download&daiweiflag=interfacefordaiwei&userName=yinyong&passWord=1&id=8a9e63965ec7301a015ec77f87f7003e
					  String opt = 
					  "<attachInfo>"+
						"<attachName>"+accessoriescnname+"</attachName>"+
						"<attachURL><![CDATA["+accessoriesUrl+"?method=download&daiweiflag=interfacefordaiwei&userName=yinyong&passWord=1&id="+id+"]]></attachURL>"+
						"<attachLength>500</attachLength>"+
					  "</attachInfo>";
					  accessoresRet = accessoresRet +  opt;
				  }
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查询附件报错");
			e.printStackTrace();
		}
		if(!"".equals(accessoresRet)){
			accessoresRet = "<attachRef>"+accessoresRet+"</attachRef>";
		}
		  return accessoresRet;
	  }
	  
	  
	  public String acceptSheet(String opDetail)
	  {
	    String result = "";
	    try {
	      InterfaceUtil interfaceUtil = new InterfaceUtil();
	      HashMap sheetMap = new HashMap();
	      HashMap columnMap = new HashMap();
	      HashMap sheetMap1 = new HashMap();
	      Map valueMap = new HashMap();
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
	      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
	      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
	      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
	      String operateUserId = "";
	      InterfaceUtilProperties properties = new InterfaceUtilProperties();

	      String nodeName = "acceptService";
	      System.out.println("WidenCompiaintSheetService.acceptSheet内对应nodeName======" + nodeName);

	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");

	        valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
	        //将当前操作人设置为默认人，读取配置文件
	        String operate_userid = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("interfaceType.operateUserId");
//	        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
	        if ((sheet_id != null) && (!"".equals(sheet_id))) {
	        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	          int status = StaticMethod.nullObject2int(main.getStatus());
	          if (status != 0) {
	            result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
	            return result;
	          }
	          String sheetKey = StaticMethod.nullObject2String(main.getId());
	          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='ExcuteHumTask' ORDER BY createtime DESC";
	          List taskList = taskservice.getTasksByCondition(condition);

	          if ((taskList != null) && (taskList.size() > 0)) {
	        	  WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
	            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	            TawSystemUser user = userMgr.getUserByuserid(operate_userid);
	            if ((operate_userid != null) && (!"".equals(operate_userid))) {
	            	 operateUserId = operate_userid;
	             
	            } else {
	                result = "Status=-1;Errlist=工单受理状态更新请求接口读取配置文件处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	   	              return result;
	            }

	            if (user != null) {
	              valueMap.put("operateDeptId", user.getDeptid());
	              valueMap.put("operaterContact", user.getMobile());
	            }

	            valueMap.put("operateUserId", operateUserId);
	            valueMap.put("operateRoleId", task.getOperateRoleId());
	            valueMap.put("mainId", main.getId());
	            valueMap.put("aiid", task.getId());
	            valueMap.put("toOrgRoleId", task.getOperateRoleId());
	            try
	            {
	              sheetMap1.put("main", main);
	              sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
	              sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
	              columnMap.put("selfSheet", sheetMap1);
	            } catch (IllegalAccessException e) {
	              e.printStackTrace();
	              result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
	              return result;
	            } catch (InstantiationException e) {
	              e.printStackTrace();
	              result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
                  return result;
	            }
	            try {
	            	//调用客服接口start
	            	String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iWidenComplaintMainManager");
	        		IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager)ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
	            	WfInterfaceInfo info = new WfInterfaceInfo();
					
					info.setInterfaceType("confirmWorkSheet");
					info.setSheetKey(sheetKey);
					info.setLinkId(task.getId());
					info.setIsSended("0");
					info.setMainBeanId("iWidenComplaintMainManager");
					info.setLinkBeanId("iWidenComplaintTaskManager");//此处传的是task
					info.setMethodType("confirmWorkSheet");
					
					WidenComplaintLink link = new WidenComplaintLink();
					link.setActiveTemplateId("FirstExcuteHumTask");
					link.setOperateType(new Integer(61));
					link.setOperateUserId(operateUserId);
					System.out.println("diaoyongkefu==="+main.getSheetId());
					try {
						 boolean returnType = operateMgr.sendData(info,link);
						 if(!returnType){
							 result = "Status=-1;sheetDetail=;Errlist=工单接单时调用客户接口失败";
							 return result;
						 }
					} catch (Exception e) {
						result = "Status=-1;sheetDetail=;Errlist=工单接单时调用客户接口报错,详细内容为" + e.getMessage();
						return result;
					}
//					调用客服接口end
	            	
	              sm.claimTask(task.getId(), valueMap, columnMap, operateUserId);
	            } catch (Exception e) {
	              e.printStackTrace();
	              result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口报错,详细内容为" + e.getMessage();
	              return result;
	            }
	            result = "Status=0;Errlist=";
	          } else {
	              result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于待受理状态,无法进行受理状态更新操作！";
	          	 return result;
	          }
	        }
	        else {
	            result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
	             return result;
	        }
	      } else {
	          result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
            return result;
	      }
	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
	    }
	    System.out.println("acceptSheet="+result);
	    return result;
	  }

	  public String getInquirySheet(String opDetail)
	  {
	    String result = "";
	    HashMap opDetailMap = new HashMap();
	    InterfaceUtil interfaceUtil = new InterfaceUtil();
	    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
	    try
	    {
	      String nodeName = "inquirySheet";
	      System.out.println("WidenCompiaintSheetService.getInquirySheet内对应nodeName======" + nodeName);
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
	        String sTime = StaticMethod.nullObject2String(opDetailMap.get("Start_time"));
	        String eTime = StaticMethod.nullObject2String(opDetailMap.get("End_time"));
	        String order_Id = StaticMethod.nullObject2String(opDetailMap.get("Order_Id"));
	        String order_Title = StaticMethod.nullObject2String(opDetailMap.get("Order_Title"));
	        String order_type = StaticMethod.nullObject2String(opDetailMap.get("Order_type"));
	        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records"));
	        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
	        if (end_records == 0) {
	          end_records = 15;
	        }
	        String hql = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("sql.inquirySheetSql");

	        if ((!"".equals(order_Id)) && (!"-1".equals(order_Id)))
	          hql = ExcelConverterUtil.replaceAll(hql, "@orderId@", "and main.sheetId like'%" + order_Id + "%'");
	        else {
	          hql = ExcelConverterUtil.replaceAll(hql, "@orderId@", "");
	        }
	        if ((!"".equals(order_Title)) && (!"-1".equals(order_Title)))
	          hql = ExcelConverterUtil.replaceAll(hql, "@orderTitle@", "and main.title like'%" + order_Title + "%'");
	        else {
	          hql = ExcelConverterUtil.replaceAll(hql, "@orderTitle@", "");
	        }
	        
	        
	        hql = ExcelConverterUtil.replaceAll(hql, "@ordertype@", "and main.complainttype1 ='101062501'");
	        
	        if ("".equals(sTime))
	          hql = ExcelConverterUtil.replaceAll(hql, "@timeStart@", "");
	        else {
	          hql = ExcelConverterUtil.replaceAll(hql, "@timeStart@", "and main.sendtime >=to_timestamp('" + sTime + "','yyyy-MM-dd HH24:mi:ss')");
	        }
	        if ("".equals(eTime))
	          hql = ExcelConverterUtil.replaceAll(hql, "@timeEnd@", "");
	        else {
	          hql = ExcelConverterUtil.replaceAll(hql, "@timeEnd@", "and main.sendtime <=to_timestamp('" + eTime + "','yyyy-MM-dd HH24:mi:ss')");
	        }

	        System.out.println("--------hql-----------" + hql);
	        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	        String sheet_totals = "";

	        String countSql = "select count(distinct main.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
	        System.out.println(countSql);
	        List countList = services.getSheetAccessoriesList(countSql);
	        Map countMap = (Map)countList.get(0);
	        sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));

	        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ >= " + start_records;
	        List taskList = services.getSheetAccessoriesList(sql);
	        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml"), nodeName, sheet_totals);
	        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
	        return result;
	      }
	      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口没有传入opDetail参数,请查证！";
	 	      return result;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单综合查询接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }


	  public String transferSheet(String opDetail)
	  {
	    String result = "";
	    try {
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
		  IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		  IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
		  InterfaceUtil interfaceUtil = new InterfaceUtil();
	      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
	      HashMap sheetMap = new HashMap();
	      HashMap columnMap = new HashMap();
	      HashMap sheetMap1 = new HashMap();
	      InterfaceUtilProperties properties = new InterfaceUtilProperties();
	      String nodeName = "transferSheet";
	      System.out.println("WidenCompiaintSheetService.transferSheet内对应nodeName======" + nodeName);
	      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("sheet_id"));
	        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("user_id"));
	        String transferroleId = StaticMethod.nullObject2String(sheetMap.get("transfer_obj"));
	        String remark = StaticMethod.nullObject2String(sheetMap.get("remark"));
	 //       String roleId = StaticMethod.nullObject2String(sheetMap.get("Operate_roleId"));
	        if ((sheet_id != null) && (!"".equals(sheet_id))) {
	        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	          String mainId = StaticMethod.nullObject2String(main.getId());

	          String condition = " sheetKey = '" + mainId + "' and taskstatus ='8' and taskname='ExcuteHumTask' ORDER BY createtime DESC ";
	          List taskList = taskservice.getTasksByCondition(condition);
	          if ((taskList != null) && (taskList.size() > 0))
	          {
	            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
	            WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
	            String taskId = StaticMethod.nullObject2String(task.getId());
	            String updateSql = "update widencomplaint_task set taskowner='" + transferroleId + "' where id='" + taskId + "'";
	            IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	            services.updateTasks(updateSql);
	            valueMap.put("operateUserId", operateUserId);
	            valueMap.put("operateRoleId", task.getOperateRoleId());
	            valueMap.put("operateDeptId", user.getDeptid());
	            valueMap.put("operaterContact", user.getMobile());
	            valueMap.put("mainId", mainId);
	            valueMap.put("aiid", taskId);
	            valueMap.put("toOrgRoleId", transferroleId);
	            valueMap.put("phaseId", "ExcuteHumTask");
				valueMap.put("dealPerformer", transferroleId);
				valueMap.put("dealPerformerLeader", transferroleId);
				valueMap.put("dealPerformerType", "subrole");
				valueMap.put("transferreason", remark);
	            sheetMap1.put("main", main);
	            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
	            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
	            columnMap.put("selfSheet", sheetMap1);
	            sm.dealSheet(mainId, valueMap, columnMap, operateUserId, taskservice);    
	            result = "Status=0;Errlist=";
	          } else {
	              result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于T2已接单未处理环节,无法进行工单移交操作！";
	              return result;
	          }
	        } else {
	            result = "Status=-1;Errlist=工单移交接口传入参数不正确,没有传入工单编号,请查证！";
	          return result;
	        }
	      } else {
		        result = "Status=-1;sheetDetail=;Errlist=工单移交接口没有传入opDetail参数,请查证！";
	        return result;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }

	  public String dealSheet(String opDetail)
	  {
	    String result = "";
	    System.out.println("opDetail widenComplainSheet="+opDetail);
	    try {
	    	
	      String Speed_Photo = "";
	      String Receipt_Photo = "";
	      String Recorded_Calls = "";
	      if(opDetail != null && !"".equals(opDetail)){
	    	  //将两个附件分离出来
	    	  System.out.println("liu1");
	    	  System.out.println("lyg1="+opDetail.lastIndexOf("<Speed_Photo>")+13);
	    	  System.out.println("lyg2="+opDetail.lastIndexOf("</Speed_Photo>"));
	    	if(opDetail.lastIndexOf("<Speed_Photo>")+13 >0 && opDetail.lastIndexOf("</Speed_Photo>")>0){
	    		Speed_Photo = opDetail.substring(opDetail.lastIndexOf("<Speed_Photo>")+13, opDetail.lastIndexOf("</Speed_Photo>"));
	        }
	  		System.out.println("photo1="+Speed_Photo);
	  		
	  		
	  		System.out.println("liu2");
	    	System.out.println("lyg3="+opDetail.lastIndexOf("<Receipt_Photo>")+15);
	    	System.out.println("lyg4="+opDetail.lastIndexOf("</Receipt_Photo>"));
	    	if(opDetail.lastIndexOf("<Receipt_Photo>")+15 >0 && opDetail.lastIndexOf("</Receipt_Photo>")>0){
	    		Receipt_Photo = opDetail.substring(opDetail.lastIndexOf("<Receipt_Photo>")+15, opDetail.lastIndexOf("</Receipt_Photo>"));
	    	}
	  		System.out.println("photo2="+Receipt_Photo);
	  		
	  		
	  		 if(opDetail != null && !"".equals(opDetail)){
		    	  //将语音附件分离
		    	  System.out.println("liu===语音==");
		    	  System.out.println("lyg1====语音=="+opDetail.lastIndexOf("<Recorded_Calls>")+16);
		    	  System.out.println("lyg2====语音=="+opDetail.lastIndexOf("</Recorded_Calls>"));
		    	if(opDetail.lastIndexOf("<Recorded_Calls>")+16 >0 && opDetail.lastIndexOf("</Recorded_Calls>")>0){
		    		Recorded_Calls = opDetail.substring(opDetail.lastIndexOf("<Recorded_Calls>")+16, opDetail.lastIndexOf("</Recorded_Calls>"));
		      }
		  		System.out.println("Recorded_Calls="+Recorded_Calls);
	  		 }
	  		 
	  		System.out.println("liu3");
	    	System.out.println("lyg5="+opDetail.lastIndexOf("<Speed_Photo>"));
	    	if(opDetail.lastIndexOf("<Speed_Photo>") >0){
	    		opDetail = opDetail.substring(0, opDetail.lastIndexOf("<Speed_Photo>"));
	    		opDetail = opDetail+"</recordInfo></opDetail>";
	    	}
	      }	
	    	
	      	
	      InterfaceUtil interfaceUtil = new InterfaceUtil();
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
	      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
	      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
	      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
	      HashMap sheetMap = new HashMap();
	      HashMap columnMap = new HashMap();
	      HashMap sheetMap1 = new HashMap();
	      String operateUserId = "";
	      InterfaceUtilProperties properties = new InterfaceUtilProperties();
	      String nodeName = "dealSheet";
	      System.out.println("WidenCompiaintSheetService.dealSheet内对应nodeName======" + nodeName);
	      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);

	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
//	        String operate_userid = "";//配置文件中得到
//	        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
	        String operate_userid = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("interfaceType.operateUserId");
	        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Operate_time"));
	        String ifzj = StaticMethod.nullObject2String(sheetMap.get("Is_quality_check"));
	        String zj_roleid = StaticMethod.nullObject2String(sheetMap.get("toroleid"));
	        String firsttime = StaticMethod.nullObject2String(sheetMap.get("firsttime"));
	        
	        //附件转换 by lyg start
//	        String Speed_Photo = StaticMethod.nullObject2String(sheetMap.get("Speed_Photo"));
//	        String Receipt_Photo = StaticMethod.nullObject2String(sheetMap.get("Receipt_Photo"));
	        
	        String speedAttachId = "";
	        String receiptAttachId = "";
	        String teleRecordAttachId = "";
	        String nodeAccessories = "";
	        System.out.println("测速照片Speed_Photo="+Speed_Photo);
	        System.out.println("返回照片Receipt_Photo="+Receipt_Photo);
	        if ((Speed_Photo != null) && (!"".equals(Speed_Photo))) {
	        	System.out.println("--Speed_Photo---------"+Speed_Photo);
	        	 InterfaceUtil interfaceUtilattach = new InterfaceUtil();
	        	 List attach = interfaceUtilattach.getAttachRefFromXml(Speed_Photo);	
	        	 WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
	        	 speedAttachId =  wm.getAttach(attach, "widencomplaint");     	
	    		 System.out.println("attachId=Speed_Photo=" + speedAttachId);
	    			if (speedAttachId != null&&!"".equals(speedAttachId) && speedAttachId.length() > 0){
	    				valueMap.put("linkSpeed", speedAttachId);
	    				if(speedAttachId.length()>80){
	    					result = "Status=-1;Errlist=工单处理完成提交请求接口传入的现场测速照片的附近大于三个,请查证！";
	    					return result;
	    				}
	    			}
	        }
	        
	        if ((Receipt_Photo != null) && (!"".equals(Receipt_Photo))) {
	        	System.out.println("--Receipt_Photo---------"+Receipt_Photo);
	        	 InterfaceUtil interfaceUtilattach = new InterfaceUtil();
	        	 List attach = interfaceUtilattach.getAttachRefFromXml(Receipt_Photo);	
	        	 WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
	        	 receiptAttachId =  wm.getAttach(attach, "widencomplaint");     	
	    		 System.out.println("attachId=Receipt_Photo=" + receiptAttachId);
	    			if (receiptAttachId != null&&!"".equals(receiptAttachId) && receiptAttachId.length() > 0){
	    				valueMap.put("linkReceipt", receiptAttachId);
	    				if(receiptAttachId.length()>80){
	    					result = "Status=-1;Errlist=工单处理完成提交请求接口传入的现场服务回执单照片的附近大于三个,请查证！";
	    					return result;
	    				}
	    			}
	        }
	        
	        if ((Recorded_Calls != null) && (!"".equals(Recorded_Calls))) {
	        	System.out.println("--Recorded_Calls---------"+Recorded_Calls);
	        	 InterfaceUtil interfaceUtilattach = new InterfaceUtil();
	        	 List attach = interfaceUtilattach.getAttachRefFromXml(Recorded_Calls);	
	        	 WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
	        	 teleRecordAttachId =  wm.getAttach(attach, "widencomplaint");     	
	    		 System.out.println("attachId=Receipt_Photo=" + teleRecordAttachId);
	    			if (teleRecordAttachId != null&&!"".equals(teleRecordAttachId) && teleRecordAttachId.length() > 0){
	    				valueMap.put("linkTeleRecord", teleRecordAttachId);
	    				if(teleRecordAttachId.length()>266){
	    					result = "Status=-1;Errlist=工单处理完成提交请求接口传入的录音的附近大于十个,请查证！";
	    					return result;
	    				}
	    			}
	        }
	        
	        if(!"".equals(receiptAttachId) && !"".equals(speedAttachId)){
	        	nodeAccessories = speedAttachId +","+receiptAttachId;
	        }else if("".equals(receiptAttachId) && !"".equals(speedAttachId)){
	        	nodeAccessories = speedAttachId;
	        }else if(!"".equals(receiptAttachId) && "".equals(speedAttachId)){
	        	nodeAccessories = receiptAttachId;
	        }
	        
	        if(!"".equals(teleRecordAttachId)){
	        	if(!"".equals(nodeAccessories)){
	        		nodeAccessories = nodeAccessories +","+teleRecordAttachId;
	        	}else{
	        		nodeAccessories = teleRecordAttachId;
	        	}
	        }
	        
	        if(!"".equals(nodeAccessories)){
	        	valueMap.put("nodeAccessories", nodeAccessories);
	        }
	        
	        //附件转换 by lyg end
	        
	        if ((sheet_id != null) && (!"".equals(sheet_id))) {
	        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	          int status = StaticMethod.nullObject2int(main.getStatus());

	          if (status != 0) {
	              result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行受理状态更新操作！";
	              return result;
	          }
	          String sheetKey = StaticMethod.nullObject2String(main.getId());
	          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='ExcuteHumTask' ORDER BY createtime DESC ";
	          List taskList = taskservice.getTasksByCondition(condition);

	          if ((taskList != null) && (taskList.size() > 0)) {
	            WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
	            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	            TawSystemUser user = userMgr.getUserByuserid(operate_userid);
	           
	            if ((operate_userid != null) && (!"".equals(operate_userid))) {
	            	 operateUserId = operate_userid;
	            }
	            else
	            {
	                result = "Status=-1;Errlist=工单处理完成提交请求接口读取配置文件的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	  	              return result;
	            }
	            List widenComplaintLinks = linkservice.getLinksBycondition(" mainid = '" + sheetKey + "' and operatetype ='61' and activetemplateid ='ExcuteHumTask'");
	             Date acceptDate = new Date();
	    		if (widenComplaintLinks != null && widenComplaintLinks.size() > 0)
	    		{
	    			WidenComplaintLink t1link = (WidenComplaintLink)widenComplaintLinks.get(0);
	    			acceptDate = t1link.getOperateTime();
	    		}
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    			Date firstdate = sdf.parse(firsttime);
	    			
	    		if(firstdate.before(acceptDate)){
	   			 result = "Status=-1;Errlist=工单第一次联系用户时间早于第一次接单时间,工单流水号为" + sheet_id + "的工单无法进行处理,请查证！";
	             return result;
	    		}else{
	    	
	           
	            if (user != null) {
	              valueMap.put("operaterContact", user.getMobile());
	            }
	            valueMap.put("operateUserId", operateUserId);
	            valueMap.put("operateRoleId", task.getOperateRoleId());
	            valueMap.put("mainId", main.getId());
	            valueMap.put("operateTime", operate_time);
	            valueMap.put("aiid", task.getId());
	            Map sheetMainMap = SheetBeanUtils.bean2Map(main);
	            valueMap.putAll(sheetMainMap);
	        	BaseLink linkObject = (BaseLink)linkservice.getLinkObject().getClass().newInstance();
				String linkId= UUIDHexGenerator.getInstance().getID();
				linkObject.setId(linkId);
				sheetMap1.put("main", main);
				sheetMap1.put("link", linkObject);
	            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
	            columnMap.put("selfSheet", sheetMap1);
	            String zjSubroleid = "8a9982f2222d20300122311a9071016b";
	            String customattribution = main.getCustomAttribution();
	         // String complaintType1 = main.getComplaintType1();
	            String areaid = StaticMethod.nullObject2String(InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("customAttribution", StaticMethod.nullObject2String(customattribution)));
	            System.out.println("--------è´¨æ£ areaid-----------" + areaid);
	            String roleid = String.valueOf(task.getOperateRoleId());
	           if (!"".equals(zj_roleid)&& zj_roleid!=null && "1".equals(ifzj)) {
	              System.out.println("--è´¨æ£-----deptid-------" + areaid);
	              System.out.println("----è´¨æ£----roleid-----------" + roleid);
	              System.out.println("------------è´¨æ£---------zj_roleid----" + zj_roleid);
	            
	                zjSubroleid = zj_roleid;
	             
	            }
	            System.out.println("------------è´¨æ£---------zjSubroleid12----" + zjSubroleid);
	            valueMap.put("dealPerformer", zjSubroleid);

	            valueMap.put("dealPerformerType", "subrole");
	            valueMap.put("dealPerformerLeader", zjSubroleid);
	  
	            IWfInterfaceInfoManager wfInterfaceInfoManager = (IWfInterfaceInfoManager) ApplicationContextHolder
				.getInstance().getBean("iWfInterfaceInfoManager");
			try {
				wfInterfaceInfoManager.saveWfInterfaceInto("iWidenComplaintMainManager",
						"iWidenComplaintLinkManager", sheetKey, linkId, "replyWorkSheet",
						"replyWorkSheet", "2");
				BocoLog.info(this, "invokeWfInterface end!!!!!!!!");
			} catch (Exception e) {
				e.printStackTrace();
			}    
			
			
//			调用客服接口start
        	String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iWidenComplaintMainManager");
    		IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager)ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
        	WfInterfaceInfo info = new WfInterfaceInfo();
			
			info.setInterfaceType("replyWorkSheet");
			info.setSheetKey(sheetKey);
			info.setLinkId(task.getId());
			info.setIsSended("0");
			info.setMainBeanId("iWidenComplaintMainManager");
			info.setLinkBeanId("iWidenComplaintTaskManager");//此处传的是task
			info.setMethodType("replyWorkSheet");
			
			WidenComplaintLink link = new WidenComplaintLink();
			link.setActiveTemplateId("ExcuteHumTask");
			link.setOperateType(new Integer(102));
			link.setOperateUserId(operateUserId);
			link.setNdeptContact(StaticMethod.nullObject2String(valueMap.get("ndeptContact")));
			link.setNdeptContactPhone(StaticMethod.nullObject2String(valueMap.get("ndeptContactPhone")));
			link.setIsReplied(StaticMethod.nullObject2String(valueMap.get("isReplied")));
			String issueEliminatTimeStr = StaticMethod.nullObject2String(valueMap.get("issueEliminatTime"));
			Date issus = SheetUtils.stringToDate(issueEliminatTimeStr);
			if(!"".equals(issueEliminatTimeStr)&& issus != null){
				link.setIssueEliminatTime(issus);
			}
			link.setIssueEliminatReason(StaticMethod.nullObject2String(valueMap.get("issueEliminatReason")));
			link.setLinkDealDesc(StaticMethod.nullObject2String(valueMap.get("linkDealDesc")));
			link.setDealResult(StaticMethod.nullObject2String(valueMap.get("dealResult")));
			
			
			System.out.println("diaoyongkefu==deal="+main.getSheetId());
			try {
				 boolean returnType = operateMgr.sendData(info,link);
				 System.out.println("returnType=="+returnType+"==sheetId=="+main.getSheetId());
				 if(!returnType){
					 result = "Status=-1;sheetDetail=;Errlist=工单回单时调用客户接口失败";
					 System.out.println("result=="+result);
					 return result;
				 }
			} catch (Exception e) {
				result = "Status=-1;sheetDetail=;Errlist=工单回单时调用客户接口报错,详细内容为" + e.getMessage();
				System.out.println("result=="+result);
				return result;
			}
//			调用客服接口end
			
			
			
				if ("0".equals(ifzj)&& ifzj!=null)
					{
						System.out.println("---------èªå¨è´¨æ£å¼å§--------");
	    				Date opeTime = new Date();
						String prelinkId = StaticMethod.nullObject2String(linkId);
						String subroleid = StaticMethod.nullObject2String(main.getSendRoleId());
						System.out.println("lizhi:opeTime=" + opeTime + "prelinkId=" + prelinkId + "subroleid=" + subroleid);
						valueMap.put("phaseId", "HoldTask");
						valueMap.put("dealPerformer", subroleid);
						valueMap.put("dealPerformerLeader", subroleid);
						valueMap.put("dealPerformerType", "subrole");
						
						if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm")){
						String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.SendImmediately"));
							if (!sendImmediately.equalsIgnoreCase("true")){
								try {
									wfInterfaceInfoManager.updateInfoForSend(sheetKey, "replyWorkSheet", "replyWorkSheet");
									BocoLog.info(this, "zhijian  invokeWfInterface end!!!!!!!!");
								} catch (Exception e) {
									e.printStackTrace();
								}   		
							}
						}
					
						createCheckingLink(main, opeTime, prelinkId, subroleid);
						createCheckingTask(main, prelinkId);
					}
				
				sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);      
	            result = "Status=0;Errlist=";
	    		}
	          } else {
		            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单处理完成提交操作！";
		            return result;
	          }
	        }
	        else {
	            result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
	     	    return result;
	        }
	      } else {
		        result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
	        return result;
	      }
	      System.out.println("dealSheet="+result);
	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
	  	}
	    return result;
	  }
	  
	  
		public static WidenComplaintLink createCheckingLink(WidenComplaintMain mainrule, Date operateTime, String prelinkId, String subroleid)
		throws Exception
	{
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoUser"));
		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoSubRole"));
		System.out.println("lizhi:autoUser=" + autoUser + "autoSubRole=" + autoSubRole);
		IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(operateTime);
		calendar.add(13, 30);
		WidenComplaintLink T1link61 = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		T1link61.setId(UUIDHexGenerator.getInstance().getID());
		T1link61.setOperateType(new Integer("61"));
		T1link61.setActiveTemplateId("CheckingHumTask");
		T1link61.setOperateTime(calendar.getTime());
		T1link61.setOperateDay(calendar.get(5));
		T1link61.setOperateMonth(calendar.get(2) + 1);
		T1link61.setOperateYear(calendar.get(1));
		T1link61.setMainId(StaticMethod.nullObject2String(mainrule.getId()));
		T1link61.setToOrgRoleId(subroleid);
		T1link61.setPreLinkId(prelinkId);
		T1link61.setNodeAccessories("");
		T1link61.setToOrgType(new Integer(0));
		T1link61.setCompleteFlag(new Integer(0));
		T1link61.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link61.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link61.setOperateDeptId("12201");
		T1link61.setTemplateFlag(0);
		linkservice.addLink(T1link61);
		calendar.add(13, 30);
		WidenComplaintLink T1link = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		T1link.setId(UUIDHexGenerator.getInstance().getID());
		T1link.setMainId(StaticMethod.nullObject2String(mainrule.getId()));
		T1link.setOperateType(new Integer(56));
		T1link.setOperateTime(calendar.getTime());
		T1link.setOperateDay(calendar.get(5));
		T1link.setOperateMonth(calendar.get(2) + 1);
		T1link.setOperateYear(calendar.get(1));
		T1link.setAcceptFlag(new Integer(0));
		T1link.setPreLinkId(prelinkId);
		T1link.setActiveTemplateId("CheckingHumTask");
		T1link.setToOrgType(new Integer(0));
		T1link.setCompleteFlag(new Integer(0));
		T1link.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link.setOperateDeptId("12201");
		String correlationKey = UUIDHexGenerator.getInstance().getID();
		T1link.setCorrelationKey(correlationKey);
		T1link.setTemplateFlag(0);
		T1link.setPiid(StaticMethod.nullObject2String(mainrule.getPiid()));
		T1link.setToOrgRoleId(subroleid);
		T1link.setNodeAcceptLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetAcceptLimit()));
		T1link.setNodeCompleteLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetCompleteLimit()));
		T1link.setLinkCheckResult("1030101");
		T1link.setLinkCheckIdea("ok");
		linkservice.addLink(T1link);
		return T1link;
	}

	public static void createCheckingTask(WidenComplaintMain mainrule, String preLinkId)
		throws Exception
	{
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoUser"));
		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoSubRole"));
		IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		WidenComplaintTask T1Task = (WidenComplaintTask)taskservice.getTaskModelObject().getClass().newInstance();
		try
		{
			T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
		}
		catch (Exception e3)
		{
			e3.printStackTrace();
		}
		T1Task.setTaskName("CheckingHumTask");
		T1Task.setTaskDisplayName("质检");
		T1Task.setFlowName("WidenComplaintProcess");
		T1Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.getSendTime()));
		T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.getId()));
		T1Task.setTaskStatus("5");
		T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.getSheetId()));
		T1Task.setTitle(StaticMethod.nullObject2String(mainrule.getTitle()));
		T1Task.setOperateType("subrole");
		T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
		T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		T1Task.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1Task.setTaskOwner(StaticMethod.nullObject2String(autoUser));
		T1Task.setIfWaitForSubTask("false");
		T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
		T1Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
		T1Task.setAcceptTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetAcceptLimit()));
		T1Task.setCompleteTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.getSheetCompleteLimit()));
		taskservice.addTask(T1Task);
	}
	
	
	public String readSheet(String opDetail)
	  {
	    String result = "";
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
	      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
	      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
	    System.out.println("-----工单已阅接口服务调用开始----------");
	    try {
	      InterfaceUtil interfaceUtil = new InterfaceUtil();
	      HashMap sheetMap = new HashMap();
	      String nodeName = "readSheet";
	      System.out.println("WidenCompiaintSheetService.readSheet内对应的nodeName======" + nodeName);
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
	        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_id"));
	        String remark = StaticMethod.nullObject2String(sheetMap.get("Operate_desc"));
	        WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	        String mainId = StaticMethod.nullObject2String(main.getId());
	        String condition = " sheetKey = '" + mainId + "' and taskstatus ='2' and taskname='cc'  ORDER BY createtime DESC";
	        List taskList = taskservice.getTasksByCondition(condition);
	        if ((taskList != null) && (taskList.size() > 0)) {
	        	for (int i =0; i<taskList.size(); i++)
	        	{		
	          WidenComplaintTask task = (WidenComplaintTask)taskList.get(i);
	          String taskId = StaticMethod.nullObject2String(task.getId());
	          String operateType = StaticMethod.nullObject2String(task.getOperateType());
	     
	          WidenComplaintLink linkbean = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
	          ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	          TawSystemUser user = userMgr.getUserByuserid(operateUserId);
	          String flag ="false"; 
	          if ((operateUserId != null) && (!"".equals(operateUserId))) {
	              if ("user".equals(operateType)) {
	                operateUserId = operateUserId;
	                flag ="true";
	              } else if ("subrole".equals(operateType)) {
	                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
	                List userList = usermgr.getRoleidByuserid(operateUserId);
	                if (userList.contains(task.getTaskOwner()) || operateUserId.equals(task.getTaskOwner())) {
	                  operateUserId = operateUserId;
	                  flag ="true"; 
	                } else {
	                  result = "Status=-1;Errlist=工单已阅更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	                  continue;
	                }
	              } else if ("dept".equals(operateType)) {
	                if (task.getTaskOwner().equals(user.getDeptid())) {
	                  operateUserId = operateUserId;
	                  flag ="true"; 
	                } else {
	                  result = "Status=-1;Errlist=工单已阅更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	                  continue;
	                }
	              }
	            } else {
	              result = "Status=-1;Errlist=工单已阅请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	              continue;
	            }
	          if ("true".equals(flag)&& flag !=null ){
	          String updateSql = "update widencomplaint_task set taskstatus='5' where id='" + taskId + "'";
		      IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		      services.updateTasks(updateSql);
	          Calendar calendar = Calendar.getInstance();
	          linkbean.setId(UUIDHexGenerator.getInstance().getID());
	          linkbean.setMainId(mainId);
	          linkbean.setOperateTime(calendar.getTime());
	          linkbean.setOperateType(new Integer(-15));
	          linkbean.setOperateDay(calendar.get(5));
	          linkbean.setOperateMonth(calendar.get(2) + 1);
	          linkbean.setOperateYear(calendar.get(1));
	          linkbean.setOperateUserId(operateUserId);
	          linkbean.setOperateDeptId(user.getDeptid());
	          linkbean.setOperateRoleId(StaticMethod.nullObject2String(task.getOperateRoleId()));
	          linkbean.setOperaterContact(user.getMobile());
	          linkbean.setActiveTemplateId("cc");
	          linkbean.setRemark(remark);
	          linkservice.addLink(linkbean);
	          result = "Status=0;Errlist=";}
	          else {
	        	   result = "Status=-1;Errlist=工单流水号为" + sheet_id + "没有对应的抄送记录,请查证！";
	 	          return result;  
	          }
	        }
	        }else {
	          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "没有对应的抄送记录,请查证！";
	          return result;
	        }
	      } else {
	        result = "Status=-1;Errlist=工单已阅接口没有传入opDetail参数,请查证！";
	        return result;
	      }
	      System.out.println("-----工单已阅接口服务调用结束----------");
	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;Errlist=工单已阅接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }
	
	
	
	public String getCCSheetListService(String opDetail)
	  {
	    String result = "";
	    HashMap opDetailMap = new HashMap();
	    InterfaceUtil interfaceUtil = new InterfaceUtil();
	    ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
	    try {
	      String nodeName = "todoList";
	      System.out.println("WidenCompiaintSheetService.getSheetListService内对应的nodeName======" + nodeName);
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
	        String user_id = StaticMethod.nullObject2String(opDetailMap.get("User_id"));
	        int start_records = StaticMethod.nullObject2int(opDetailMap.get("Start_records")) - 1;
	        int end_records = StaticMethod.nullObject2int(opDetailMap.get("End_records"));
	        TawSystemUser user = userMgr.getUserByuserid(user_id);
	        System.out.println("--------ph:user_id-----------" + user_id);
	        String dept_id = StaticMethod.nullObject2String(user.getDeptid());
	        System.out.println("--------ph:deptid-----------" + dept_id);
	        if (("".equals(user_id)) || (user_id == null) || (dept_id == null) || ("".equals(dept_id))) {
	          result = "Status=-1;sheetDetail=;Errlist=工单列表查询接口出错！用户名在EOMS中不存在。";
	          return result;
	        }

	        String hql = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("sql.ccundoListSql");
	        hql = ExcelConverterUtil.replaceAll(hql, "@userId@", user_id);
	        hql = ExcelConverterUtil.replaceAll(hql, "@deptId@", user.getDeptid());
	        String countSql = "select count(distinct m.id) num  " + hql.substring(hql.indexOf("from"), hql.length());
	        String sql = "select * from (select a.*,rownum row_ from (" + hql + " ) a )where row_ <=" + end_records + " and row_ > " + start_records;
	        System.out.println("----cclist-sql--"+sql);
	        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	        List taskList = services.getSheetAccessoriesList(sql);
	        List countList = services.getSheetAccessoriesList(countSql);
	        Map countMap = (Map)countList.get(0);
	        String sheet_totals = StaticMethod.nullObject2String(countMap.get("num"));
	        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml"), nodeName, sheet_totals);
	        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
	        System.out.println("--result--"+result);
	        return result;
	      }
	      result = "Status=-1;sheetDetail=;Errlist=工单抄送列表查询接口没有传入opDetail参数,请查证！";
	      return result;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单抄送列表查询接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }


	  public String getSheetHistoryService(String opDetail)
	  {
	    String result = "";
	    HashMap opDetailMap = new HashMap();
	    InterfaceUtil interfaceUtil = new InterfaceUtil();
	    IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
	    HandheldOperationUtil hoUtil = new HandheldOperationUtil();
	    try {
	      String nodeName = "historyList";
	      System.out.println("WidenCompiainSheetService.getSheetListService内对应的nodeName======" + nodeName);
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        opDetailMap = interfaceUtil.xmlElements(opDetail, opDetailMap, "FieldContent");
	        String sheetId = StaticMethod.nullObject2String(opDetailMap.get("Sheet_id"));
	        WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheetId);
	        String mainId = StaticMethod.nullObject2String(main.getId());
	        String sql = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("sql.historySql");
	        sql = ExcelConverterUtil.replaceAll(sql, "@mainid@", mainId);
	        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	        List taskList = services.getSheetAccessoriesList(sql);
	        opDetail = hoUtil.getXmlFromMap(taskList, StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml"), nodeName, "");
	        result = "Status=0;sheetDetail=" + opDetail + ";Errlist=";
	        return result;
	      }
	      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口没有传入opDetail参数,请查证！";
	      return result;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单流转环节服务接口出错！详细信息为" + e.getMessage();
	    }return result;
	  }

	  
	  public String assignSheet(String opDetail)
	  {
	    String result = "";
	    try {
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
		  IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		  IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");		  
		  ITawSystemUserManager tawsystemusermanager = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		  InterfaceUtil interfaceUtil = new InterfaceUtil();
	    //  WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
	      HashMap sheetMap = new HashMap();
	      HashMap columnMap = new HashMap();
	      HashMap sheetMap1 = new HashMap();
	      InterfaceUtilProperties properties = new InterfaceUtilProperties();
	      String nodeName = "assignSheet";
	      System.out.println("WidenCompiaintSheetService.transferSheet内对应nodeName======" + nodeName);
	      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        HashMap valueMap = (HashMap)properties.getMapFromXml(sheetMap, filePath, nodeName);
	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
	        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("user_id"));
	
	        String remark = StaticMethod.nullObject2String(sheetMap.get("remark"));
	    	String assignroleId = StaticMethod.nullObject2String(sheetMap.get("assign_obj"));
	        if ((sheet_id != null) && (!"".equals(sheet_id))) {
	        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	          String mainId = StaticMethod.nullObject2String(main.getId());

	          String condition = " sheetKey = '" + mainId + "' and taskstatus ='8' and taskname='ExcuteHumTask'  ORDER BY createtime DESC";
	          List taskList = taskservice.getTasksByCondition(condition);
	          if ((taskList != null) && (taskList.size() > 0))
	          {
	            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
	            WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
	            String taskId = StaticMethod.nullObject2String(task.getId());
	            System.out.println("-----taskId--"+taskId);
	            Calendar calendar = Calendar.getInstance();
	      //      String updateSql = "update widencomplaint_task set ifwaitforsubtask  ='true' where id='" + taskId + "'";
	     //       IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	     //       services.updateTasks(updateSql);
	            valueMap.put("operateTime", calendar.getTime());
	            valueMap.put("operateUserId", operateUserId);
	            valueMap.put("operateRoleId", task.getOperateRoleId());
	            valueMap.put("operateDeptId", user.getDeptid());
	            valueMap.put("operaterContact", user.getMobile());
	            valueMap.put("mainId", mainId);
	            valueMap.put("aiid", taskId);
	            valueMap.put("toOrgRoleId", assignroleId);
	            valueMap.put("phaseId", "ExcuteHumTask");
				valueMap.put("dealPerformer", assignroleId);
				valueMap.put("dealPerformerLeader", assignroleId);
				valueMap.put("dealPerformerType", "subrole");
				valueMap.put("taskOwner", assignroleId);
				valueMap.put("remark", remark);		
				valueMap.put("taskName", "WidenComplaintSubTask");
				valueMap.put("taskNamespace", "http://WidenComplaint");	
				valueMap.put("parentTaskName", "ExcuteHumTask");
				valueMap.put("main", main);
				valueMap.put("link", linkservice.getLinkObject().getClass().newInstance());
				valueMap.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
		         
	          //  sheetMap1.put("main", main);
	         //   sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
	         //   sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
	         //   columnMap.put("selfSheet", sheetMap1);
	    		HashMap sessionMap = new HashMap();
	    		TawSystemUser tawsystemuser = tawsystemusermanager.getTawSystemUserByuserid(operateUserId);
	    		String password = StaticMethod.nullObject2String(tawsystemuser.getPassword());
	    		sessionMap.put("userId", operateUserId);
	    		sessionMap.put("password", password);
	    		BusinessFlowServiceImpl businessFlowService = (BusinessFlowServiceImpl)ApplicationContextHolder.getInstance().getBean("businessFlowService");
	     //       sm.dealSheet(mainId, valueMap, columnMap, operateUserId, taskservice);   
	    		System.out.println("----stratassgin--");
	            businessFlowService.createSubTask(task.getId(),valueMap,sessionMap);
	            result = "Status=0;Errlist=";
	          } else {
	              result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于T2已接单未处理环节,无法进行工单分派操作！";
	              return result;
	          }
	        } else {
	            result = "Status=-1;Errlist=工单分派接口传入参数不正确,没有传入工单编号,请查证！";
	          return result;
	        }
	      } else {
		        result = "Status=-1;sheetDetail=;Errlist=工单分派接口没有传入opDetail参数,请查证！";
	        return result;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }
	  
	  public String dealAssignSheet(String opDetail)
	  {
	    String result = "";
	    try {
	      InterfaceUtil interfaceUtil = new InterfaceUtil();
	      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
	      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
	      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
	      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
	      HashMap sheetMap = new HashMap();
	      HashMap columnMap = new HashMap();
	      HashMap sheetMap1 = new HashMap();
	      String operateUserId = "";
	      InterfaceUtilProperties properties = new InterfaceUtilProperties();
	      String nodeName = "dealAssignSheet";
	      System.out.println("WidenCompiaintSheetService.dealAssignSheet内对应nodeName======" + nodeName);
	      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
	      if ((opDetail != null) && (!"".equals(opDetail))) {
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
	        Calendar calendar = Calendar.getInstance();
	        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
	        String operate_userid = StaticMethod.nullObject2String(sheetMap.get("Operate_userid"));
	        
	        if ((sheet_id != null) && (!"".equals(sheet_id))) {
	        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
	          int status = StaticMethod.nullObject2int(main.getStatus());

	          if (status != 0) {
	              result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行回复状态更新操作！";
	              return result;
	          }
	          String sheetKey = StaticMethod.nullObject2String(main.getId());
	          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='ExcuteHumTask' and subtaskflag='true'  ORDER BY createtime DESC";
	          List taskList = taskservice.getTasksByCondition(condition);
	          if ((taskList != null) && (taskList.size() > 0)) {
	            WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
	            String operateType = StaticMethod.nullObject2String(task.getOperateType());
	            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	            TawSystemUser user = userMgr.getUserByuserid(operate_userid);

	            if ((operate_userid != null) && (!"".equals(operate_userid))) {
	              if (task.getTaskOwner().equals(operate_userid)) {
	                operateUserId = operate_userid;
	              }
	              else if ("user".equals(operateType)) {
	                operateUserId = operate_userid;
	              } else if ("subrole".equals(operateType)) {
	                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
	                List userList = usermgr.getRoleidByuserid(operate_userid);
	                if (userList.contains(task.getTaskOwner())) {
	                  operateUserId = operate_userid;
	                } else {
	                    result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	                    return result;
	                }
	              } else if ("dept".equals(operateType)) {
	                if (task.getTaskOwner().equals(user.getDeptid())) {
	                  operateUserId = operate_userid;
	                } else {
	                    result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	                    return result;
	                }
	              }
	            }
	            else
	            {
	                result = "Status=-1;Errlist=工单处理完成提交请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
	  	              return result;
	            }
	           
	            if (user != null) {
	              valueMap.put("operaterContact", user.getMobile());
	            }
	            valueMap.put("operateUserId", operateUserId);
	            valueMap.put("operateRoleId", task.getOperateRoleId());
	            valueMap.put("mainId", main.getId());
	            valueMap.put("operateTime", calendar.getTime());
	            valueMap.put("aiid", task.getId());
	            Map sheetMainMap = SheetBeanUtils.bean2Map(main);
	            valueMap.putAll(sheetMainMap);
	            valueMap.put("dealPerformer", "");
	            valueMap.put("dealPerformerType", "");
	            valueMap.put("dealPerformerLeader", ""); 
	            valueMap.put("phaseId", "CheckingHumTask");
	            valueMap.put("hasNextTaskFlag", "true"); 
	            valueMap.put("reInvokeCount", "0"); 
	        	BaseLink linkObject = (BaseLink)linkservice.getLinkObject().getClass().newInstance();
				String linkId= UUIDHexGenerator.getInstance().getID();
				linkObject.setId(linkId);
				sheetMap1.put("main", main);
				sheetMap1.put("link", linkObject);
	            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
	            columnMap.put("selfSheet", sheetMap1);
 
		       sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);           
	            result = "Status=0;Errlist=";
	    		
	          } else {
		            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于处理中状态,无法进行工单处理完成提交操作！";
		            return result;
	          }
	        }
	        else {
	            result = "Status=-1;Errlist=工单受理状态更新请求接口传入参数不正确,请查证！";
	     	             return result;
	        }
	      } else {
		        result = "Status=-1;Errlist=工单受理状态更新请求接口没有传入opDetail参数,请查证！";
	        return result;
	      }
	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      result = "Status=-1;sheetDetail=;Errlist=工单受理状态更新请求接口出错！详细信息为" + e.getMessage();
	  		    }return result;
	  }
	  
	  
		public String replyAssignSheet(String opDetail)
		  {
		    String result = "";
		      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
		      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
		    System.out.println("-----工单分派回复通过接口服务调用开始----------");
		    try {
		      InterfaceUtil interfaceUtil = new InterfaceUtil();
		      HashMap sheetMap = new HashMap();
		      String nodeName = "replyAssignSheet";
		      System.out.println("WidenCompiaintSheetService.replyAssignSheet内对应的nodeName======" + nodeName);
		      if ((opDetail != null) && (!"".equals(opDetail))) {
		        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
		        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
		        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_id"));
		        String remark = StaticMethod.nullObject2String(sheetMap.get("Operate_desc"));
		        WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
		        String mainId = StaticMethod.nullObject2String(main.getId());
		        String condition = " sheetKey = '" + mainId + "' and taskstatus ='5' and taskname='ExcuteHumTask'  and subtaskflag='true' and  (SUBTASKDEALFALG <> 'true' OR SUBTASKDEALFALG IS NULL) ORDER BY createtime DESC ";

		        List taskList = taskservice.getTasksByCondition(condition);
		        if ((taskList != null) && (taskList.size() > 0)) {
		          WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
		          String taskId = StaticMethod.nullObject2String(task.getId());
		          String operateType = StaticMethod.nullObject2String(task.getOperateType());
		          String taskOwner = StaticMethod.null2String(task.getTaskOwner());
		          String toTaskID = StaticMethod.null2String(task.getParentTaskId());
		          WidenComplaintTask selfTask =(WidenComplaintTask) taskservice.getSinglePO(toTaskID);	
		          WidenComplaintLink linkbean = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		          ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		          TawSystemUser user = userMgr.getUserByuserid(operateUserId);
		          if ((operateUserId != null) && (!"".equals(operateUserId))) {
		              if ("user".equals(operateType)) {
		                operateUserId = operateUserId;
		              } else if ("subrole".equals(operateType)) {
		                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
		                List userList = usermgr.getRoleidByuserid(operateUserId);
		                if (userList.contains(selfTask.getTaskOwner()) || operateUserId.equals(selfTask.getTaskOwner())) {
		                  operateUserId = operateUserId;
		                } else {
		                  result = "Status=-1;Errlist=工单分派回复通过请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
		                  return result;
		                }
		              } else if ("dept".equals(operateType)) {
		                if (task.getTaskOwner().equals(user.getDeptid())) {
		                  operateUserId = operateUserId;
		                } else {
		                  result = "Status=-1;Errlist=工单分派回复通过更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
		                  return result;
		                }
		              }
		            } else {
		              result = "Status=-1;Errlist=工单分派回复通过请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
		              return result;
		            }
		          Calendar calendar = Calendar.getInstance();
		          linkbean.setId(UUIDHexGenerator.getInstance().getID());
		          linkbean.setMainId(mainId);
		          linkbean.setOperateTime(calendar.getTime());
		          linkbean.setOperateType(new Integer(6));
		          linkbean.setOperateDay(calendar.get(5));
		          linkbean.setOperateMonth(calendar.get(2) + 1);
		          linkbean.setOperateYear(calendar.get(1));
		          linkbean.setOperateUserId(operateUserId);
		          linkbean.setOperateDeptId(user.getDeptid());
		          linkbean.setOperateRoleId(StaticMethod.nullObject2String(selfTask.getOperateRoleId()));
		          linkbean.setOperaterContact(user.getMobile());
		          linkbean.setCompleteFlag(new Integer(0));
		          linkbean.setToOrgRoleId(StaticMethod.nullObject2String(task.getOperateRoleId()));
		          linkbean.setToOrgUserId(taskOwner);
		          linkbean.setPiid(StaticMethod.nullObject2String(main.getPiid()));
		          linkbean.setActiveTemplateId("ExcuteHumTask");
		          linkbean.setRemark(remark);
		          linkservice.addLink(linkbean);
		         String updateSql = "update widencomplaint_task set subtaskdealfalg='true' where id='" + taskId + "'";
		          IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		          services.updateTasks(updateSql);

		       //    查询没有回复的任务
		      	List list = taskservice.getUndealTaskListByParentTaskId(toTaskID);
		  		List notReversions = taskservice.getNotReversionListByParentTaskId(toTaskID);
		  		if (list != null && notReversions.size() == 0) {
		  			
		  			selfTask.setIfWaitForSubTask("false");
		  			taskservice.addTask(selfTask);
		  		}
		          result = "Status=0;Errlist=";
		        } else {
		          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "没有对应的抄送记录,请查证！";
		          return result;
		        }
		      } else {
		        result = "Status=-1;Errlist=工单分派回复通过接口没有传入opDetail参数,请查证！";
		        return result;
		      }
		      System.out.println("-----工单分派回复通过接口服务调用结束----------");
		      return result;
		    } catch (Exception e) {
		      e.printStackTrace();
		      result = "Status=-1;Errlist=工单已阅接口出错！详细信息为" + e.getMessage();
		    }return result;
		  }
		
		  
		  public String rejectAssignSheet(String opDetail)
		  {
		    String result = "";
		    try {
		      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
			  IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
			  IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
			  InterfaceUtil interfaceUtil = new InterfaceUtil();
		   //   WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		      HashMap sheetMap = new HashMap();
		    //  HashMap columnMap = new HashMap();
		    //  HashMap sheetMap1 = new HashMap();
		      InterfaceUtilProperties properties = new InterfaceUtilProperties();
		      String nodeName = "rejectAssignSheet";
		      System.out.println("WidenCompiaintSheetService.rejectAssignSheet内对应nodeName======" + nodeName);
		      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
		      if ((opDetail != null) && (!"".equals(opDetail))) {
		        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
		        HashMap valueMap =(HashMap) properties.getMapFromXml(sheetMap, filePath, nodeName);
		        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
		        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("Operate_id"));
		        String remark = StaticMethod.nullObject2String(sheetMap.get("Operate_desc"));
		      //  String operate_time = StaticMethod.nullObject2String(sheetMap.get("Operate_time"));
		        Calendar calendar = Calendar.getInstance();
		        if ((sheet_id != null) && (!"".equals(sheet_id))) {
		        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
		          String mainId = StaticMethod.nullObject2String(main.getId());

		          String condition = " sheetKey = '" + mainId + "' and taskstatus ='5' and taskname='ExcuteHumTask' and subtaskflag='true' AND (SUBTASKDEALFALG <> 'true' OR SUBTASKDEALFALG IS NULL)  ORDER BY createtime DESC ";
		          List taskList = taskservice.getTasksByCondition(condition);
		          if ((taskList != null) && (taskList.size() > 0))
		          {
		            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
		            String password = StaticMethod.nullObject2String(user.getPassword());
		            WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
		            String toTaskId = StaticMethod.nullObject2String(task.getId());
		            String taskId =StaticMethod.nullObject2String(task.getParentTaskId());
		            WidenComplaintTask selfTask = (WidenComplaintTask)taskservice.getSinglePO(taskId);
		            System.out.println("----taskOwner--"+task.getTaskOwner());
		            valueMap.put("operateUserId", operateUserId);     
		            valueMap.put("operateDeptId", user.getDeptid());
		            valueMap.put("operaterContact", user.getMobile());
		            valueMap.put("mainId", mainId);
		            valueMap.put("aiid", taskId);
		            valueMap.put("piid", task.getProcessId());
		            valueMap.put("operateTime", calendar.getTime());
		            valueMap.put("taskName", "WidenComplaintSubTask");
					valueMap.put("taskNamespace", "http://WidenComplaint");	
					valueMap.put("parentTaskName", "ExcuteHumTask");
		            valueMap.put("phaseId", "ExcuteHumTask");
		            valueMap.put("toOrgRoleId", task.getTaskOwner());    
		            valueMap.put("operateRoleId",selfTask.getOperateRoleId());
					valueMap.put("dealPerformer",  task.getOperateRoleId());
					valueMap.put("dealPerformerLeader",  task.getTaskOwner());
					valueMap.put("dealPerformerType", "subrole");
					valueMap.put("remark", remark);
					valueMap.put("main", main);
					valueMap.put("link", linkservice.getLinkObject().getClass().newInstance());
					valueMap.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
		              
		               HashMap sessionMap = new HashMap();
			    		sessionMap.put("userId", operateUserId);
			    		sessionMap.put("password", password);
		            BusinessFlowServiceImpl businessFlowService = (BusinessFlowServiceImpl)ApplicationContextHolder.getInstance().getBean("businessFlowService");		   	 
		   	         businessFlowService.createSubTask(taskId, valueMap, sessionMap);
			//        WidenComplaintTask selfTask = (WidenComplaintTask)taskservice.getSinglePO(toTaskId);
		   	        task.setSubTaskDealFalg("true");
		   	        taskservice.addTask(task);
		            result = "Status=0;Errlist=";
		          } else {
		              result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于T2已接单未处理环节,无法进行工单分派不通过操作！";
		              return result;
		          }
		        } else {
		            result = "Status=-1;Errlist=工单分派接口传入参数不正确,没有传入工单编号,请查证！";
		          return result;
		        }
		      } else {
			        result = "Status=-1;sheetDetail=;Errlist=工单分派接口没有传入opDetail参数,请查证！";
		        return result;
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return result;
		  }  
		  
		  public String sleepSheet(String opDetail)
		  { 
			  String result ="";		  
			  IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
			  IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
			  try {
				      InterfaceUtil interfaceUtil = new InterfaceUtil();
				      HashMap sheetMap = new HashMap();
				      String nodeName = "sleepSheet";
				      System.out.println("WidenCompiaintSheetService.sleepSheet内对应的nodeName======" + nodeName);
				      System.out.println("-----工单休眠接口服务调用开始--sleep--------");
				      if ((opDetail != null) && (!"".equals(opDetail))) {
				        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
				        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("sheet_id"));
				        String operateUserId = StaticMethod.nullObject2String(sheetMap.get("user_id"));
				        String sleepTime = StaticMethod.nullObject2String(sheetMap.get("resting_time"));
				        String sleepReasion = StaticMethod.nullObject2String(sheetMap.get("remark"));
				        if ((sheet_id != null) && (!"".equals(sheet_id))) {
				        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
				          int status = StaticMethod.nullObject2int(main.getStatus());

				          if (status != 0) {
				              result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行休眠状态更新操作！";
				              return result;
				          }
				        String sheetKey =StaticMethod.nullObject2String(main.getId());
				          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='ExcuteHumTask' ORDER BY createtime DESC ";
	     		        List taskList = taskservice.getTasksByCondition(condition);
				        if ((taskList != null) && (taskList.size() > 0)) {
				          WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
				          String customAttribution = StaticMethod.nullObject2String(main.getCustomAttribution());
				        	String customAttributionValue = "";
				      	if (!"".equals(customAttribution)) {
				      		customAttribution = customAttribution.substring(3);
				      		customAttributionValue = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty(customAttribution));
				      	}
				      	    String t1dealer = customAttributionValue;
				          String taskId = StaticMethod.nullObject2String(task.getId());
				          String operateType = StaticMethod.nullObject2String(task.getOperateType());
				          ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
				          TawSystemUser user = userMgr.getUserByuserid(operateUserId);
				          if ((operateUserId != null) && (!"".equals(operateUserId))) {
				              if ("user".equals(operateType)) {
				                operateUserId = operateUserId;
				              } else if ("subrole".equals(operateType)) {
				                ITawSystemUserRefRoleManager usermgr = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
				                List userList = usermgr.getRoleidByuserid(operateUserId);
				                if (userList.contains(task.getTaskOwner())|| operateUserId.equals(task.getTaskOwner())) {
				                  operateUserId = operateUserId;
				                } else {
				                  result = "Status=-1;Errlist=工单休眠1更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
				                  return result;
				                }
				              } else if ("dept".equals(operateType)) {
				                if (task.getTaskOwner().equals(user.getDeptid())) {
				                  operateUserId = operateUserId;
				                } else {
				                  result = "Status=-1;Errlist=工单休眠更新请求接口传入的处理人账号没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
				                  return result;
				                }
				              }
				            } else {
				              result = "Status=-1;Errlist=工单休眠请求接口传入的处理人账号为空,没有权限对工单流水号为" + sheet_id + "的工单进行处理,请查证！";
				              return result;
				            }
				          System.out.println(">>>>>sleepSheet>>>>>t1dealer" + t1dealer+"--sheet_id--"+sheet_id);
				      	if (t1dealer != null && !"".equals(t1dealer))
				      	{
				      		main.setMainT1Dealer(t1dealer);
				      		main.setMainSleepStatus(Integer.valueOf(1));
				      		main.setMainSleepTkid(taskId);
				      		main.setMainSleepTime(sleepTime);
				      		main.setMainSleepReason(sleepReasion);
				      		main.setMainSleepUser(operateUserId);
				      		main.setMainT2ApplyTime(new Date());
				      		mainservice.saveOrUpdateMain(main);
				      		task.setTaskStatus("4");
				      		taskservice.addTask(task);
				      	    result = "Status=0;Errlist=";
				      	}else{
				      		 result = "Status=-1;Errlist=工单流水号为"+ sheet_id + "没有对应的T1dealer,请查证！";	
				      		  return result;
				      	}
				      	} else {
				          result = "Status=-1;Errlist=工单流水号为" + sheet_id + "没有对应的T2处理记录,请查证！";
				          return result;
				        }
				      } else {
				        result = "Status=-1;Errlist=工单已阅接口没有传入opDetail参数中sheetid,请查证！";
				        return result;
				      }
				    } else {
					        result = "Status=-1;Errlist=工单已阅接口没有传入opDetail参数,请查证！";
					        return result;
					        }
				      System.out.println("-----工单休眠接口服务调用结束--sleep--------");
				      return result;
			     }catch (Exception e) {
				      e.printStackTrace();
				      result = "Status=-1;Errlist=工单休眠接口出错！详细信息为" + e.getMessage();
				    }return result;
		  }
		  
		  
		  /**
		   * 工单驳回接口
		   * @param opDetail
		   * @return
		   */
		  public String rejectSheet(String opDetail)
		  {
		    String result = "";
		    try {
		      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
		      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
		      InterfaceUtil interfaceUtil = new InterfaceUtil();
		      WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		      HashMap sheetMap = new HashMap();
		      HashMap columnMap = new HashMap();
		      HashMap sheetMap1 = new HashMap();
		      InterfaceUtilProperties properties = new InterfaceUtilProperties();
		      String nodeName = "rejectSheet";
		      System.out.println("HandWidenComplaint2EomsService.dealSheet内对应的nodeName======" + nodeName);
		      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
		      if ((opDetail != null) && (!"".equals(opDetail))) {
		        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
		        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
		        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
		        System.out.println("sheet_id====lyg="+sheet_id);
		        String operateUserId = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("interfaceType.operateUserId");
		        String operate_time = StaticMethod.nullObject2String(sheetMap.get("Reject_time"));
		        if ((sheet_id != null) && (!"".equals(sheet_id))) {
		        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
		        	int status = StaticMethod.nullObject2int(main.getStatus());

		          if (status != 0) {
		            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行驳回操作！";
		            return result;
		          }
		          String sheetKey = StaticMethod.nullObject2String(main.getId());
		          String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='ExcuteHumTask'";
		          List taskList = taskservice.getTasksByCondition(condition);
		          if ((taskList != null) && (taskList.size() > 0)) {
		        	 WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
		            ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		            TawSystemUser user = userMgr.getUserByuserid(operateUserId);
		            if (user != null) {
		              valueMap.put("operateDeptId", user.getDeptid());
		              valueMap.put("operaterContact", user.getMobile());
		            }
		            valueMap.put("operateUserId", operateUserId);
		            valueMap.put("operateRoleId", task.getOperateRoleId());
		            valueMap.put("mainId", main.getId());
		            valueMap.put("aiid", task.getId());
		            valueMap.put("piid", task.getProcessId());
		            valueMap.put("operateTime", operate_time);
		            valueMap.put("operateType", "4");
		            valueMap.put("hasNextTaskFlag", "true");

		            if ("ExcuteHumTask".equals(task.getTaskName())) {
		              valueMap.put("phaseId", "RejectTask");
		              valueMap.put("dealPerformer", main.getSendRoleId());
		              valueMap.put("dealPerformerLeader", main.getSendRoleId());
		              valueMap.put("dealPerformerType", "subrole");
		              valueMap.put("toOrgRoleId", main.getSendRoleId());
		            }
		            
//					调用客服接口start
	            	String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iWidenComplaintMainManager");
	        		IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager)ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
	            	WfInterfaceInfo info = new WfInterfaceInfo();
					
					info.setInterfaceType("withdrawWorkSheet");
					info.setSheetKey(sheetKey);
					info.setLinkId(task.getId());
					info.setIsSended("0");
					info.setMainBeanId("iWidenComplaintMainManager");
					info.setLinkBeanId("iWidenComplaintTaskManager");//此处传的是task
					info.setMethodType("withdrawWorkSheet");
					
					WidenComplaintLink link = new WidenComplaintLink();
					link.setActiveTemplateId("ExcuteHumTask");
					link.setOperateType(new Integer(4));
					link.setOperateUserId(operateUserId);
					
					link.setRemark(StaticMethod.nullObject2String(valueMap.get("remark")));
					
					System.out.println("diaoyongkefu==deal="+main.getSheetId());
					try {
						 boolean returnType = operateMgr.sendData(info,link);
						 if(!returnType){
							 result = "Status=-1;sheetDetail=;Errlist=工单驳回时调用客户接口失败";
							 return result;
						 }
					} catch (Exception e) {
						result = "Status=-1;sheetDetail=;Errlist=工单驳回时调用客户接口报错,详细内容为" + e.getMessage();
						return result;
					}
//					调用客服接口end
		            
		            
		            sheetMap1.put("main", main);
		            sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
		            sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
		            columnMap.put("selfSheet", sheetMap1);
		            valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
		            sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
		            result = "Status=0;Errlist=";
					
		          }
		          else {
		            result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于接单状态,无法进行工单驳回操作！";
		            return result;
		          }
		        }
		        else {
		          result = "Status=-1;Errlist=工单驳回接口传入参数不正确,请查证！";
		          return result;
		        }
		      } else {
		        result = "Status=-1;Errlist=工单驳回接口没有传入opDetail参数,请查证！";
		        return result;
		      }
		      return result;
		    } catch (Exception e) {
		      e.printStackTrace();
		      result = "Status=-1;Errlist=工单驳回接口出错！详细信息为" + e.getMessage();
		    }
		    System.out.println("rejectSheet="+result);
		    return result;
		  }
		  
		  /**
		   * 阶段回复
		   * @param opDetail
		   * @return
		   */
	public String replySheet(String opDetail){
		
		   String result = "";
		   try {
		      IWidenComplaintMainManager mainservice = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
		      IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		      IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
		      
		      WidenComplaintLink linkbean = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		      
		      InterfaceUtil interfaceUtil = new InterfaceUtil();
		      HashMap sheetMap = new HashMap();
		      InterfaceUtilProperties properties = new InterfaceUtilProperties();
		      String nodeName = "replySheet";
		      System.out.println("HandWidenComplaint2EomsService.dealSheet内对应的nodeName======" + nodeName);
		      String filePath = StaticMethod.getFilePathForUrl("classpath:config/widencomplaintSheet.xml");
		      if ((opDetail != null) && (!"".equals(opDetail))) {
		        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
		        Map valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
		        String sheet_id = StaticMethod.nullObject2String(sheetMap.get("Sheet_id"));
		        System.out.println("sheet_id====lyg=replySheet="+sheet_id);
//		        String operateUserId = ;
		        String operateUserId = XmlManage.getFile("/config/widencomplaintSheet.xml").getProperty("interfaceType.operateUserId");
		        if ((sheet_id != null) && (!"".equals(sheet_id))) {
		        	WidenComplaintMain main = (WidenComplaintMain)mainservice.getMainBySheetId(sheet_id);
		        	int status = StaticMethod.nullObject2int(main.getStatus());
		        	if (status != 0) {
		        		result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于运行状态,无法进行阶段回复操作！";
		        		return result;
		        	}
		            String sheetKey = StaticMethod.nullObject2String(main.getId());
		            String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='8' and taskName ='ExcuteHumTask'";
		            List taskList = taskservice.getTasksByCondition(condition);
		            if ((taskList != null) && (taskList.size() > 0)) {
		            	 WidenComplaintTask task = (WidenComplaintTask)taskList.get(0);
		            	ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		            	TawSystemUser user = userMgr.getUserByuserid(operateUserId);
		            	if (user != null) {
		            		valueMap.put("operateDeptId", user.getDeptid());
		            		valueMap.put("operaterContact", user.getMobile());
		            		
		            	}
		            	
		            	
//						调用客服接口start
		            	String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iWidenComplaintMainManager");
		        		IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager)ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
		            	WfInterfaceInfo info = new WfInterfaceInfo();
						
						info.setInterfaceType("notifyWorkSheet");
						info.setSheetKey(sheetKey);
						info.setLinkId(task.getId());
						info.setIsSended("0");
						info.setMainBeanId("iWidenComplaintMainManager");
						info.setLinkBeanId("iWidenComplaintTaskManager");//此处传的是task
						info.setMethodType("notifyWorkSheet");
						
						WidenComplaintLink link = new WidenComplaintLink();
						link.setActiveTemplateId("ExcuteHumTask");
						link.setOperateType(new Integer(9));
						link.setOperateUserId(operateUserId);
						System.out.println("diaoyongkefu==replySheet="+main.getSheetId());
						try {
							 boolean returnType = operateMgr.sendData(info,link);
							 if(!returnType){
								 result = "Status=-1;sheetDetail=;Errlist=工单阶段回复时调用客户接口失败";
								 return result;
							 }
						} catch (Exception e) {
							result = "Status=-1;sheetDetail=;Errlist=工单阶段回复时调用客户接口报错,详细内容为" + e.getMessage();
							return result;
						}
//						调用客服接口end
		            	
		            	//开始
		            	
		            	Calendar calendar = Calendar.getInstance();
		            	SheetBeanUtils.populateEngineMap2Bean(linkbean, valueMap);
//		            	String Aiid = StaticMethod.nullObject2String(linkbean.getAiid(), "");
//		            	ITask tmptask = taskservice.getSinglePO(Aiid);
		            	
		            	if (user != null) {
		            		linkbean.setOperateDeptId(user.getDeptid());
		            		linkbean.setOperaterContact(user.getMobile());
		            		linkbean.setOperateUserId(user.getUserid());
		            	}
		            	
		            	linkbean.setActiveTemplateId("reply");
		            	linkbean.setId(UUIDHexGenerator.getInstance().getID());
		            	linkbean.setMainId(main.getId());
		            	linkbean.setOperateType(new Integer(9));
		            	linkbean.setOperateTime(new Date());
		            	linkbean.setToOrgRoleId(main.getSendRoleId());
		            	linkbean.setOperateDay(calendar.get(Calendar.DATE));
		            	linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
		            	linkbean.setOperateYear(calendar.get(Calendar.YEAR));
		            	// 保存task数据
		            	WidenComplaintTask task1 = (WidenComplaintTask)taskservice.getTaskModelObject().getClass().newInstance();
		            	task1.setId(UUIDHexGenerator.getInstance().getID());
		            	task1.setTaskName("reply");
		            	task1.setTaskDisplayName("阶段回复");
		            	task1.setOperateRoleId(main.getSendRoleId());
		            	task1.setTaskOwner(main.getSendRoleId());
		            	task1.setFlowName("WidenComplaint");
		            	task1.setOperateType("subrole");
		            	newSaveTask(main, linkbean, task1);
		            	linkbean.setAiid(task1.getId());
		            	linkservice.addLink(linkbean);
		            	//结束
		            	
		            	
		            	
		            	result = "Status=0;Errlist=";
					
		            }else {
		            	result = "Status=-1;Errlist=工单流水号为" + sheet_id + "的工单未处于回单状态,无法进行工单阶段回复操作！";
		            	return result;
		            }
		        }else {
		          result = "Status=-1;Errlist=工单阶段回复接口传入参数不正确,请查证！";
		          return result;
		        }
		      } else {
		        result = "Status=-1;Errlist=工单阶段回复接口没有传入opDetail参数,请查证！";
		        return result;
		      }
		      return result;
		    } catch (Exception e) {
		      e.printStackTrace();
		      result = "Status=-1;Errlist=工单阶段回复接口出错！详细信息为" + e.getMessage();
		    }
		    System.out.println("rejectSheet="+result);
		    return result;
		  }	  
		
	/**
	 * 整合用 保存task数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void newSaveTask(BaseMain main, BaseLink link, ITask task)
			throws Exception {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);

		if (task.getId() == null)
			task.setId(UUIDHexGenerator.getInstance().getID());
		if (task.getCreateTime() == null)
			task.setCreateTime(nowDate);
		if (task.getTaskStatus() == null)
			task.setTaskStatus(Constants.TASK_STATUS_READY);
		if (task.getProcessId() == null && main.getPiid() != null)
			task.setProcessId(main.getPiid());
		if (task.getSheetKey() == null)
			task.setSheetKey(main.getId());
		if (task.getSheetId() == null)
			task.setSheetId(main.getSheetId());
		if (task.getTitle() == null)
			task.setTitle(main.getTitle());
		if (task.getAcceptTimeLimit() == null)
			task.setAcceptTimeLimit(link.getNodeAcceptLimit());
		if (task.getCompleteTimeLimit() == null)
			task.setCompleteTimeLimit(link.getNodeCompleteLimit());
		if (task.getPreLinkId() == null)
			task.setPreLinkId(link.getId());
		if (task.getIfWaitForSubTask() == null)
			task.setIfWaitForSubTask("false");

		task.setCreateDay(calendar.get(Calendar.DATE));
		task.setCreateMonth(calendar.get(Calendar.MONTH) + 1);
		task.setCreateYear(calendar.get(Calendar.YEAR));
		IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		taskservice.addTask(task);
	}
}
