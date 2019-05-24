package com.boco.eoms.sheet.branchindexreduction.job;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionMain;
import com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionTask;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionFlowManager;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionLinkManager;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionMainManager;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionTaskManager;
import com.boco.eoms.sheet.branchindexreduction.util.DateUtil;
/**
 * 首次初审环节建单人超2个工作日内未完成归档操作则系统自动归档
 * 二次初审环节建单人超1个工作日内未完成归档操作则系统自动归档
 * @author wmm
 *
 */
public class AutoTrailAndAgainToSchedule implements Job{

	private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");/* 周中是放假的时间 */
	private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");/*周末上上班时间的*/
	
	IBranchIndexReductionTaskManager taskMgr=(IBranchIndexReductionTaskManager)ApplicationContextHolder.getInstance().getBean("iBranchIndexReductionTaskManager");
	IBranchIndexReductionMainManager mainMgr=(IBranchIndexReductionMainManager)ApplicationContextHolder.getInstance().getBean("iBranchIndexReductionMainManager");
	IBranchIndexReductionLinkManager linkMgr=(IBranchIndexReductionLinkManager)ApplicationContextHolder.getInstance().getBean("iBranchIndexReductionLinkManager");
	IBranchIndexReductionFlowManager flowMgr = (IBranchIndexReductionFlowManager)ApplicationContextHolder.getInstance().getBean("iBranchIndexReductionFlowManager");
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		String nowtime =(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		BocoLog.info(AutoTrailAndAgainToSchedule.class,"AutoFinishSchedule Job is running start;"+nowtime);
		
		
		long date = addDateByHour(2);/*首次 当前时间往前推2个工作日  返回毫秒*/
		long date2 = addDateByHour(1);/*第二次 当前时间往前推1个工作日*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar currcal = Calendar.getInstance();
		Date d;
		int jj_n=0;
		try {
			 d =sdf1.parse(sdf.format(new Date())); // 当月1号
			 currcal.setTime(d);
			 if(DateIfEqualShow.checkHoliday(currcal))
			 {
					jj_n=1;
			 }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		long date5 = DateIfEqualShow.addDate01ByHour(new Date(), 4+jj_n);
		long date7 = DateIfEqualShow.addDate01ByHour(new Date(), 6+jj_n);
		long datenow = Calendar.getInstance().getTimeInMillis();
	
		String sheetId = "";
		System.out.println("当前时间往前推n个工作日"+date);
		
		//String condition = " taskName ='TrialTask' AND taskStatus != '5' ";
		// 判断操作环节
		List taskList = null;
		try {
			taskList = taskMgr.getTasksByCondition(" taskName ='TrialTask' and taskStatus != '5' ");
		} catch (Exception e) {
			BocoLog.info(AutoTrailAndAgainToSchedule.class,"the sql is error");
			e.printStackTrace();
		}
		
			
		if(taskList!=null && taskList.size()>0){
			BranchIndexReductionTask  taskObj = null;
			BranchIndexReductionMain  mainObj = null;
			String taskId = "";
			String sheetKey = "";
			HashMap sessionMap = new HashMap();
	        sessionMap.put("password", "123");
			String taskStatus = "";
			String mainReserveC ="";
			Calendar cal = Calendar.getInstance();
			HashMap sheetMap = new HashMap();
			String operateRoleId = "";
			for(int i=0;i<taskList.size();i++){
				try {
					taskObj = (BranchIndexReductionTask) taskList.get(i);
					taskId = StaticMethod.nullObject2String(taskObj.getId());
					sheetKey = StaticMethod.nullObject2String(taskObj.getSheetKey());
					taskStatus = StaticMethod.nullObject2String(taskObj.getTaskStatus());
					operateRoleId  = StaticMethod.nullObject2String(taskObj.getOperateRoleId());
					mainObj = (BranchIndexReductionMain) mainMgr.getSingleMainPO(sheetKey);
					mainReserveC = StaticMethod.nullObject2String(mainObj.getMainReserveC());
					
					
					sheetId = StaticMethod.nullObject2String(mainObj.getSheetId());
					TawSystemUser user = getrandomroleid(operateRoleId);
					//mainObj.setEndResult("满意");//请写入归档意见
					//mainObj.setHoldStatisfied(Integer.valueOf(1030301));//请写入归档满意度  
					//mainObj.setStatus(Constants.SHEET_HOLD);
					//mainMgr.saveOrUpdateMain(mainObj);
					
					BaseLink holdLink = (BaseLink) linkMgr.getLinkObject().getClass().newInstance();
					HashMap linkMap = new HashMap();
					linkMap.put("id", UUIDHexGenerator.getInstance().getID());
					linkMap.put("mainId", StaticMethod.nullObject2String(taskObj.getSheetKey()));
					linkMap.put("operateTime", new Date());
					linkMap.put("operateType", Integer.valueOf(18));
					linkMap.put("operateUserId", StaticMethod.nullObject2String(user.getUserid()));
					linkMap.put("operateDeptId", StaticMethod.nullObject2String(user.getDeptid()));
					linkMap.put("operateRoleId", StaticMethod.nullObject2String(operateRoleId));
					linkMap.put("operaterContact", StaticMethod.nullObject2String(user.getMobile()));
					linkMap.put("activeTemplateId", "TrialTask");
					linkMap.put("aiid", taskId); //task表待归档环节的task记录的id
					linkMap.put("piid", StaticMethod.nullObject2String(mainObj.getPiid()));
					linkMap.put("correlationKey", UUIDHexGenerator.getInstance().getID());
					linkMap.put("preLinkId", StaticMethod.nullObject2String(taskObj.getPreLinkId()));
					SheetBeanUtils.populate(holdLink, linkMap);
//					linkMgr.addLink(holdLink);
								
					Map mainMap = SheetBeanUtils.bean2Map(mainObj);
					Map operateMap = new HashMap();
					operateMap.put("phaseId", "HoldTask"); // 归档
					
					operateMap.put("dealPerformer",StaticMethod.nullObject2String(mainObj.getSendUserId())) ;
					operateMap.put("dealPerformerLeader",StaticMethod.nullObject2String(mainObj.getSendUserId())) ;
					operateMap.put("dealPerformerType","user") ; // 派往用户
					
					operateMap.put("beanId", "IBranchIndexReductionMainManager");
					operateMap.put("linkBeanId", "IBranchIndexReductionLinkManager");
					operateMap.put("linkClassName", linkMgr.getLinkObject()
							.getClass().getName());
					operateMap.put("mainClassName", mainMgr.getMainObject()
							.getClass().getName());
					operateMap.put("extendKey1", UUIDHexGenerator.getInstance().getID());
					
					sheetMap.put("main", mainMap);
					sheetMap.put("link", linkMap);
					sheetMap.put("operate", operateMap);
					
					taskObj.setTaskStatus("5");            
					
					sessionMap.put("userId", mainObj.getSendUserId());
					if(!"1".equals(mainReserveC)){/*第一次初审*/
						cal.setTime(taskObj.getCreateTime());
//						if(cal.getTimeInMillis()<date){/*满足条件 如果当前时间是第5个工作日（或者大于第5个工作日），那么就将所有工单归档*/
						if(date5<= datenow){/* 如果当前时间是第5个工作日（或者大于第5个工作日），那么就将所有工单归档*/
							if("2".equals(taskStatus)){/* 构造接单记录  然后提交流程引擎*/
								addLink(taskObj,mainObj,user);
							}
							taskMgr.addTask(taskObj);
							flowMgr.completeHumanTask(taskId, sheetMap,sessionMap);/*调流程引擎*/   // ????
						}else{
							BocoLog.info(AutoTrailAndAgainToSchedule.class,"不符合自动流转条件");
							continue;
						}
						
					}else{/*第二次*/
						cal.setTime(taskObj.getCreateTime());
//						if(cal.getTimeInMillis()<date2){/*满足条件*/
						if(date7<= datenow){/* 如果当前时间是第7个工作日（或者大于第7个工作日），那么就将所有工单归档*/
							taskMgr.addTask(taskObj);
							if("2".equals(taskStatus)){/* 构造接单记录  然后提交流程殷勤*/
								addLink(taskObj,mainObj,user);
							}
							flowMgr.completeHumanTask(taskId, sheetMap,sessionMap);/*调流程引擎*/
						}else{
							BocoLog.info(AutoTrailAndAgainToSchedule.class,"不符合自动流转条件");
							continue;
						}
						
					}
				} catch (Exception e) {
					BocoLog.info(AutoTrailAndAgainToSchedule.class,"工单自动流转失败。。。。from AutoFinishSchedule");
			        e.printStackTrace();
			        
			        BocoLog.error(AutoTrailAndAgainToSchedule.class, "sheetId ：" +sheetId+ "工单自动流转失败 error！"+e.getMessage());
			        continue;
				} 
			}
		}else{
			BocoLog.info(AutoTrailAndAgainToSchedule.class,"当前环节TrialTask没有符合条件的数据");
		}
		
		
	}
	
	 private TawSystemUser getrandomroleid(String roleId) throws Exception {
		 List userRoleList =  linkMgr.getLinksBycondition(" subroleid='"+roleId+"'  ", "TawSystemUserRefRole");
		 Random rand = new Random();
		 String userId = "";
		 if(userRoleList!=null && userRoleList.size()>0){
			TawSystemUserRefRole userRole = (TawSystemUserRefRole) userRoleList.get(rand.nextInt(userRoleList.size()));
			userId = StaticMethod.nullObject2String(userRole.getUserid());
		 }
		 TawSystemUser user = null;
		 if(!"".equals(userId)){
			 List userList =  linkMgr.getLinksBycondition(" userId='"+userId+"' and deleted='0' ", "TawSystemUser");
			 if(userList!=null && userList.size()>0){
				 user = (TawSystemUser) userList.get(0);
			 }
		 }
		 return user;
	    }

		
    	
  
	
	/**
	 * 构造接单link
	 * @param taskObj
	 * @param mainObj
	 */
	private void addLink(BranchIndexReductionTask taskObj, BranchIndexReductionMain mainObj,TawSystemUser user) {
		
		try {
			BaseLink holdLink = (BaseLink) linkMgr.getLinkObject().getClass().newInstance();
			HashMap linkMap = new HashMap();
			linkMap.put("id", UUIDHexGenerator.getInstance().getID());
			linkMap.put("mainId", StaticMethod.nullObject2String(taskObj.getSheetKey()));
			linkMap.put("operateTime", new Date());
			linkMap.put("operateType", Integer.valueOf(61));
			linkMap.put("operateUserId", StaticMethod.nullObject2String(user.getUserid()));
			linkMap.put("operateDeptId", StaticMethod.nullObject2String(user.getDeptid()));
			linkMap.put("operateRoleId", StaticMethod.nullObject2String(taskObj.getOperateRoleId()));
			linkMap.put("operaterContact", StaticMethod.nullObject2String(user.getMobile()));
			linkMap.put("activeTemplateId", "TrialTask");
			linkMap.put("aiid", taskObj.getId()); //task表待归档环节的task记录的id
			linkMap.put("piid", StaticMethod.nullObject2String(mainObj.getPiid()));
			linkMap.put("correlationKey", UUIDHexGenerator.getInstance().getID());
			linkMap.put("preLinkId", StaticMethod.nullObject2String(taskObj.getPreLinkId()));
			SheetBeanUtils.populate(holdLink, linkMap);
			linkMgr.addLink(holdLink);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 获取前n个工作日日期
	 * @return
	 */
	public static long addDateByHour(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.getWorkDate( new Date()));
		boolean holidayFlag = false;
	    for (int i = 0; i < days; i++){  
	        //把原时间加1小时
	    	cal.add(Calendar.DAY_OF_YEAR, -1); /*当前时间减一天*/
	        holidayFlag =checkHoliday(cal);
	        if(holidayFlag){/* 如果日期不合法*/
	        	i--;
            }
	    }
	    return cal.getTimeInMillis();

	 }

	/**
	 * 校验是否符合
	 * @param src
	 * @return
	* @throws ParseException 
	 */
	public static boolean checkHoliday(Calendar cal) {
		 //.getTime();
		 String nowtime =(new SimpleDateFormat("yyyy-MM-dd")).format( cal.getTime());
		 boolean result = false;
		 int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
	     if(isWeekend.contains(nowtime)){ /* 周中是放假的时间 */
	    	 System.out.println("当前日期："+nowtime +",不上班!");
	    	 return true;
	     }
		 if((week_index==0||week_index==6)&& !isWeekday.contains(nowtime)){ /*是周六或周末 并不在周末上班日期中 不合法*/
	    	 result = true;
	     }
	     return result;
	
	}
}
