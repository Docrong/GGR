package com.boco.eoms.extra.supplierkpi.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;

/**
 * 
 * <p>
 * Title:任务调度--生成考核项实例填写表 JobSupplierkpiSchedule类
 * 
 * </p>    
 * <p>
 * Description: 用于供应商管理流程生成考核项实例填写表
 * 
 * 定制时的克隆表达式: 0 1 0 1 * ?
 * </p>
 * <p>
 * Oct 24, 2007 10:32:52 AM
 * </p>
 * 
 * @author
 * @version 1.0
 * 
 */
public class TawSupplierkpiSchedule implements Job {
	
	ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) ApplicationContextHolder
			.getInstance().getBean("ItawSupplierkpiInstanceManager");	

	  String currentDate = StaticMethod.getCurrentDateTime();
	  String yy = currentDate.substring(5,7);
	  
/**
 * 自动生成KPI指标实例填写程序
 */	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		try {
			  //以下代码主要完成任务的实现			  
			  if(SuppStaticVariable.INSTANCE_JAN.equals(yy)){ 
				  monthInstance();        //月度考核项实例填写表		  
			      quarterInstance();      //季度考核项实例填写表 
			      yearInstance();         //年度考核项实例填写表
			  }else if(SuppStaticVariable.INSTANCE_APR.equals(yy) || SuppStaticVariable.INSTANCE_JUL.equals(yy) || SuppStaticVariable.INSTANCE_OCT.equals(yy)){
			      monthInstance();        //月度考核项实例填写表
			      quarterInstance();      //季度考核项实例填写表
			  }else{ 
				  monthInstance();        //月度考核项实例填写表
			  }						  
			  System.out.println("===================================================>执行成功!");			
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
			System.out.println("====================================> 执行出错!");
		}
	}
	
/**
 * 手动生成KPI指标实例填写程序
 */	
	public int repair(){
		int num = 0;
		try {
			// 以下代码主要完成任务的实现
			if (SuppStaticVariable.INSTANCE_JAN.equals(yy)) {
				num += monthInstance(); // 月度考核项实例填写表
				num += quarterInstance(); // 季度考核项实例填写表
				num += yearInstance(); // 年度考核项实例填写表
			} else if (SuppStaticVariable.INSTANCE_APR.equals(yy)
					|| SuppStaticVariable.INSTANCE_JUL.equals(yy)
					|| SuppStaticVariable.INSTANCE_OCT.equals(yy)) {
				num += monthInstance(); // 月度考核项实例填写表
				num += quarterInstance(); // 季度考核项实例填写表
			} else {
				num += monthInstance(); // 月度考核项实例填写表
			}
			System.out
					.println("===================================================>执行成功!");
		} catch (Exception e) {
			num = -1;
			BocoLog.error(this, e.toString());
			System.out.println("====================================> 执行出错!");
		}
		return num;
	}	
/**
 * 月度考核项实例填写表生成方法,返回生成实例个数(主要用于手动补采) 
 */
	public int monthInstance() {
		
		int num = 0;  
		String currentTime = StaticMethod.getCurrentDateTime();
		  int date = Integer.parseInt(currentTime.substring(8,10));
		  String py = SuppStaticVariable.getLocalString(date, 0);
		  String year = py.substring(0,4);
		  String fy = py.substring(5,7);
		  
		ITawSystemSubRoleManager subRole = (ITawSystemSubRoleManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemSubRoleManager");
		
        String fillStratTime = StaticMethod.getLocalString();
        String fillEndTime = SuppStaticVariable.getMonthEnd();        
        
        
		String queryStr = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem items where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=items.id and items.statictsCycle='106010301' and items.assessStatus=3 and items.deleted=1 and items.assessMoment='106010603'";
        queryStr += " and (relation.kpiItemId not in(select kpiItemId from TawSupplierkpiInstance where manufacturerId=assessInstance.supplierId and kpiItemId=relation.kpiItemId and year='" + year + "' and timeLatitude='" + fy +"'))";
        queryStr += " and items.templateId  not  in(select id from TawSupplierkpiTemplate where specialType in(select distinct(specialType) from TawSupplierkpiInstanceAss where assessStatus=3 and year='" + year + "' and timeLatitude='" + fy + "'))";
		//String queryStr = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem item where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=item.id and item.statictsCycle='106010301' and item.assessMoment='106010603'";
		List list = (List) mgr.getTawSupplierkpiItems(queryStr);
		
		TawSupplierkpiAssessInstance assessInstance = new TawSupplierkpiAssessInstance();
		TawSupplierkpiRelation relation = new TawSupplierkpiRelation();
		TawSupplierkpiItem kpiItem = new TawSupplierkpiItem();
		//TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();	
		TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
		
		for (int i = 0; i < list.size(); i++) {
			TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
			List roleList = new ArrayList();
			String manufacturerName = null;
			assessInstance = (TawSupplierkpiAssessInstance)((Object[]) list.get(i))[0];
			relation = (TawSupplierkpiRelation)((Object[]) list.get(i))[1];
			kpiItem = (TawSupplierkpiItem)((Object[]) list.get(i))[2];
            //取出对应的厂商名称
			if(!assessInstance.getSupplierId().equals("")&&assessInstance.getSupplierId()!=null){
				manufacturerName = mgr.getTawSupplierNameById(assessInstance.getSupplierId());
			}			
			tawSupplierkpiInstance.setKpiItemId(StaticMethod.null2String(relation.getKpiItemId()));
			tawSupplierkpiInstance.setManufacturerId(StaticMethod.null2String(assessInstance.getSupplierId()));
			tawSupplierkpiInstance.setManufacturerName(StaticMethod.null2String(manufacturerName));
			tawSupplierkpiInstance.setServiceType(StaticMethod.null2String(assessInstance.getServiceType()));
			tawSupplierkpiInstance.setSpecialType(StaticMethod.null2String(assessInstance.getSpecialType()));
			tawSupplierkpiInstance.setKpiName(StaticMethod.null2String(kpiItem.getKpiName()));
			tawSupplierkpiInstance.setAssessId(StaticMethod.null2String(relation.getAssessInstanceId()));			
			tawSupplierkpiInstance.setItemType(StaticMethod.null2String(kpiItem.getItemType()));
			tawSupplierkpiInstance.setAssessContent(StaticMethod.null2String(kpiItem.getAssessContent()));
			tawSupplierkpiInstance.setAssessNote(StaticMethod.null2String(kpiItem.getAssessNote()));
			tawSupplierkpiInstance.setDataSource(StaticMethod.null2String(kpiItem.getDataSource()));
			tawSupplierkpiInstance.setDataType(StaticMethod.null2String(kpiItem.getDataType()));
			tawSupplierkpiInstance.setUnit(StaticMethod.null2String(kpiItem.getUnit()));
			tawSupplierkpiInstance.setFillStratTime(StaticMethod.getTimestamp(fillStratTime));
			tawSupplierkpiInstance.setFillEndTime(StaticMethod.getTimestamp(fillEndTime));
			tawSupplierkpiInstance.setStatictsCycle(StaticMethod.null2String(kpiItem.getStatictsCycle()));
			tawSupplierkpiInstance.setIsImpersonality(StaticMethod.null2String(kpiItem.getIsImpersonality()));
			tawSupplierkpiInstance.setAssessMoment(StaticMethod.null2String(kpiItem.getAssessMoment()));
			tawSupplierkpiInstance.setTimeLatitude(fy);
			tawSupplierkpiInstance.setYear(year);
			
            //如果预定执行者为大角色，则取出其下面的所有子所角，每个子角色分配一份
			if(kpiItem.getPreActor()!=null&&!kpiItem.getPreActor().equals("")&&kpiItem.getPreActor().length()<32){
				roleList = subRole.getTawSystemSubRoles(Long.parseLong(kpiItem.getPreActor()));
	            for(int l = 0; l < roleList.size(); l++){
	            	tawSystemSubRole = (TawSystemSubRole) roleList.get(l);
	            	if (assessInstance.getSpecialType().equals(tawSystemSubRole.getType2()) || assessInstance.getSpecialType().equals(tawSystemSubRole.getType3())) {
	            		tawSupplierkpiInstance.setFillRole(StaticMethod.null2String(tawSystemSubRole.getId()));
		            	mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
		            	num++;
	            	}
	            }				
			}else{//如果预定义执行者为小角色时，则直接给小角色分配指标
            	tawSupplierkpiInstance.setFillRole(StaticMethod.null2String(kpiItem.getPreActor()));
            	mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
            	num++;
			}			
		}
		return num;
	}
	
/**
 * 季度考核项实例填写表生成方法   需在此方法给相关指标赋初值
 */
	public int quarterInstance() {
		
		int num = 0;  
		String currentTime = StaticMethod.getCurrentDateTime();
		  int date = Integer.parseInt(currentTime.substring(8,10));
		  String py = SuppStaticVariable.getLocalString(date, 0);
		  String year = py.substring(0,4);
		  String fy = py.substring(5,7);
		  int y = Integer.parseInt(fy);
		  String timeLatitude = "";		  
		  if(y>=1&&y<4){
				timeLatitude="one";
			}else if(y>=4&&y < 7){
				timeLatitude="two";
			}else if(y>=7&&y<10){
				timeLatitude="three";
			}else{
				timeLatitude="four";
			}
		  
		ITawSystemSubRoleManager subRole = (ITawSystemSubRoleManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemSubRoleManager");
		
        String fillStratTime = StaticMethod.getLocalString();
        String fillEndTime = SuppStaticVariable.getMonthEnd();
        

		//String queryStr = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem item where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=item.id and item.statictsCycle='106010302' and item.assessMoment='106010603'";
		String queryStr = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem items where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=items.id and items.statictsCycle='106010302' and items.assessStatus=3 and items.deleted=1 and items.assessMoment='106010603'";
		queryStr += " and (relation.kpiItemId not in(select kpiItemId from TawSupplierkpiInstance where manufacturerId=assessInstance.supplierId and kpiItemId=relation.kpiItemId and year='" + year + "' and timeLatitude='" + timeLatitude +"'))";
		queryStr += " and items.templateId not in(select id from TawSupplierkpiTemplate where specialType in(select distinct(specialType) from TawSupplierkpiInstanceAss where assessStatus=3 and year='" + year + "' and timeLatitude='" + timeLatitude + "'))";
		List list = (List) mgr.getTawSupplierkpiItems(queryStr);
		
		TawSupplierkpiAssessInstance assessInstance = new TawSupplierkpiAssessInstance();
		TawSupplierkpiRelation relation = new TawSupplierkpiRelation();
		TawSupplierkpiItem kpiItem = new TawSupplierkpiItem();
		//TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();	
		TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
		
		for (int i = 0; i < list.size(); i++) {
			TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
			List roleList = new ArrayList();
			String manufacturerName = null;
			assessInstance = (TawSupplierkpiAssessInstance)((Object[]) list.get(i))[0];
			relation = (TawSupplierkpiRelation)((Object[]) list.get(i))[1];
			kpiItem = (TawSupplierkpiItem)((Object[]) list.get(i))[2];
            //取出对应的厂商名称
			if(!assessInstance.getSupplierId().equals("")&&assessInstance.getSupplierId()!=null){
				manufacturerName = mgr.getTawSupplierNameById(assessInstance.getSupplierId());
			}
		
			tawSupplierkpiInstance.setKpiItemId(StaticMethod.null2String(relation.getKpiItemId()));
			tawSupplierkpiInstance.setManufacturerId(StaticMethod.null2String(assessInstance.getSupplierId()));
			tawSupplierkpiInstance.setManufacturerName(StaticMethod.null2String(manufacturerName));
			tawSupplierkpiInstance.setServiceType(StaticMethod.null2String(assessInstance.getServiceType()));
			tawSupplierkpiInstance.setSpecialType(StaticMethod.null2String(assessInstance.getSpecialType()));
			tawSupplierkpiInstance.setKpiName(StaticMethod.null2String(kpiItem.getKpiName()));
			tawSupplierkpiInstance.setAssessId(StaticMethod.null2String(relation.getAssessInstanceId()));			
			tawSupplierkpiInstance.setItemType(StaticMethod.null2String(kpiItem.getItemType()));
			tawSupplierkpiInstance.setAssessContent(StaticMethod.null2String(kpiItem.getAssessContent()));
			tawSupplierkpiInstance.setAssessNote(StaticMethod.null2String(kpiItem.getAssessNote()));
			tawSupplierkpiInstance.setDataSource(StaticMethod.null2String(kpiItem.getDataSource()));
			tawSupplierkpiInstance.setDataType(StaticMethod.null2String(kpiItem.getDataType()));
			tawSupplierkpiInstance.setUnit(StaticMethod.null2String(kpiItem.getUnit()));
			tawSupplierkpiInstance.setFillStratTime(StaticMethod.getTimestamp(fillStratTime));
			tawSupplierkpiInstance.setFillEndTime(StaticMethod.getTimestamp(fillEndTime));
			tawSupplierkpiInstance.setStatictsCycle(StaticMethod.null2String(kpiItem.getStatictsCycle()));
			tawSupplierkpiInstance.setIsImpersonality(StaticMethod.null2String(kpiItem.getIsImpersonality()));
			tawSupplierkpiInstance.setAssessMoment(StaticMethod.null2String(kpiItem.getAssessMoment()));
			tawSupplierkpiInstance.setTimeLatitude(timeLatitude);
			tawSupplierkpiInstance.setYear(year);
			
            //如果预定执行者为大角色，则取出其下面的所有子所角，每个子角色分配一份			
			if(kpiItem.getPreActor()!=null&&!kpiItem.getPreActor().equals("")&&kpiItem.getPreActor().length()<32){
				roleList = subRole.getTawSystemSubRoles(Long.parseLong(kpiItem.getPreActor()));
	            for(int l = 0; l < roleList.size(); l++){
	            	tawSystemSubRole = (TawSystemSubRole) roleList.get(l);
	            	if (assessInstance.getSpecialType().equals(tawSystemSubRole.getType2()) || assessInstance.getSpecialType().equals(tawSystemSubRole.getType3())) {
	            		tawSupplierkpiInstance.setFillRole(StaticMethod.null2String(tawSystemSubRole.getId()));
		            	mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
		            	num++;
	            	}
	            }				
			}else{//如果预定义执行者为小角色时，则直接给小角色分配指标
            	tawSupplierkpiInstance.setFillRole(StaticMethod.null2String(kpiItem.getPreActor()));
            	mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
            	num++;
			}			
		}
		return num;
	}

	
/**
 * 年度考核项实例填写表生成方法
 */
	public int yearInstance() {
		
		int num = 0;
		ITawSystemSubRoleManager subRole = (ITawSystemSubRoleManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemSubRoleManager");
		
        String fillStratTime = StaticMethod.getLocalString();
        String fillEndTime = SuppStaticVariable.getMonthEnd();
        
	    String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8,10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0,4);        
		String timeLatitude = "year";
		//String queryStr = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem item where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=item.id and item.statictsCycle='106010303' and item.assessMoment='106010603'";
		String queryStr = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem item where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=item.id and item.statictsCycle='106010303' and item.assessStatus=3 and item.deleted=1 and item.assessMoment='106010603'";
		queryStr += " and (relation.kpiItemId not in(select kpiItemId from TawSupplierkpiInstance where manufacturerId=assessInstance.supplierId and kpiItemId=relation.kpiItemId and year='" + year + "' and timeLatitude='" + timeLatitude +"'))";
		queryStr += " and item.templateId not in(select id from TawSupplierkpiTemplate where specialType in(select distinct(specialType) from TawSupplierkpiInstanceAss where assessStatus=3 and year='" + year + "' and timeLatitude='" + timeLatitude + "'))";
		List list = (List) mgr.getTawSupplierkpiItems(queryStr);
		
		TawSupplierkpiAssessInstance assessInstance = new TawSupplierkpiAssessInstance();
		TawSupplierkpiRelation relation = new TawSupplierkpiRelation();
		TawSupplierkpiItem kpiItem = new TawSupplierkpiItem();
		//TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();	
		TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
		
		for (int i = 0; i < list.size(); i++) {
			TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
			List roleList = new ArrayList();
			String manufacturerName = null;
			assessInstance = (TawSupplierkpiAssessInstance)((Object[]) list.get(i))[0];
			relation = (TawSupplierkpiRelation)((Object[]) list.get(i))[1];
			kpiItem = (TawSupplierkpiItem)((Object[]) list.get(i))[2];
			//取出对应的厂商名称
			if(!assessInstance.getSupplierId().equals("")&&assessInstance.getSupplierId()!=null){
				manufacturerName = mgr.getTawSupplierNameById(assessInstance.getSupplierId());
			}

			tawSupplierkpiInstance.setKpiItemId(StaticMethod.null2String(relation.getKpiItemId()));
			tawSupplierkpiInstance.setManufacturerId(StaticMethod.null2String(assessInstance.getSupplierId()));
			tawSupplierkpiInstance.setManufacturerName(StaticMethod.null2String(manufacturerName));
			tawSupplierkpiInstance.setServiceType(StaticMethod.null2String(assessInstance.getServiceType()));
			tawSupplierkpiInstance.setSpecialType(StaticMethod.null2String(assessInstance.getSpecialType()));
			tawSupplierkpiInstance.setKpiName(StaticMethod.null2String(kpiItem.getKpiName()));
			tawSupplierkpiInstance.setAssessId(StaticMethod.null2String(relation.getAssessInstanceId()));			
			tawSupplierkpiInstance.setItemType(StaticMethod.null2String(kpiItem.getItemType()));
			tawSupplierkpiInstance.setAssessContent(StaticMethod.null2String(kpiItem.getAssessContent()));
			tawSupplierkpiInstance.setAssessNote(StaticMethod.null2String(kpiItem.getAssessNote()));
			tawSupplierkpiInstance.setDataSource(StaticMethod.null2String(kpiItem.getDataSource()));
			tawSupplierkpiInstance.setDataType(StaticMethod.null2String(kpiItem.getDataType()));
			tawSupplierkpiInstance.setUnit(StaticMethod.null2String(kpiItem.getUnit()));
			tawSupplierkpiInstance.setFillStratTime(StaticMethod.getTimestamp(fillStratTime));
			tawSupplierkpiInstance.setFillEndTime(StaticMethod.getTimestamp(fillEndTime));
			tawSupplierkpiInstance.setStatictsCycle(StaticMethod.null2String(kpiItem.getStatictsCycle()));
			tawSupplierkpiInstance.setIsImpersonality(StaticMethod.null2String(kpiItem.getIsImpersonality()));
			tawSupplierkpiInstance.setAssessMoment(StaticMethod.null2String(kpiItem.getAssessMoment()));
			tawSupplierkpiInstance.setTimeLatitude(timeLatitude);
			tawSupplierkpiInstance.setYear(year);
			
			//如果预定执行者为大角色，则取出其下面的所有子所角，每个子角色分配一份
			if(kpiItem.getPreActor()!=null&&!kpiItem.getPreActor().equals("")&&kpiItem.getPreActor().length()<32){
				roleList = subRole.getTawSystemSubRoles(Long.parseLong(kpiItem.getPreActor()));
	            for(int l = 0; l < roleList.size(); l++){
	            	tawSystemSubRole = (TawSystemSubRole) roleList.get(l);
	            	if (assessInstance.getSpecialType().equals(tawSystemSubRole.getType2()) || assessInstance.getSpecialType().equals(tawSystemSubRole.getType3())) {
	            		tawSupplierkpiInstance.setFillRole(StaticMethod.null2String(tawSystemSubRole.getId()));
		            	mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
		            	num ++;
	            	}
	            }				
			}else{//如果预定义执行者为小角色时，则直接给小角色分配指标
            	tawSupplierkpiInstance.setFillRole(StaticMethod.null2String(kpiItem.getPreActor()));
            	mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
            	num++;
			}			
		}
		return num;
	}
}
