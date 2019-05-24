package com.boco.eoms.extra.supplierkpi.schedule;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportStorage;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.service.ITawSuppKpiInstReportOrderManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;

/**
 * 
 * <p>
 * Title:任务调度--生成统计报表存储表 TawSuppkpiReportStorageSchedule类 *
 * </p>
 * <p>
 * Description: 用于供应商管理流程生成统计报表存储表 定制时的克隆表达式: 0 1 0 1 * ?
 * </p>
 * <p>
 * Dec 5, 2007 10:32:52 AM
 * </p>
 * 
 * @author
 * @version 1.0
 * 
 */
public class TawSuppkpiReportStorageSchedule implements Job {
	
    /**
	 * Description: 轮循执行方法
	 */
	public void execute(JobExecutionContext context)throws JobExecutionException {
		try {			
			createStorage();						  
			  System.out.println("===================================================>执行成功!");			
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
			System.out.println("====================================> 执行出错!");
		}	
	}
	
	public void createStorage(){
		  String currentDate = StaticMethod.getCurrentDateTime();
		  String yy = currentDate.substring(5,7);		
		  // 以下代码主要完成任务的实现
		  if(SuppStaticVariable.INSTANCE_JAN.equals(yy)){ 
			  executeMonth();        // 生成月度报表
			  executeQuarter();      // 生成季度报表
			  executeYear();         // 生成年度报表
		  }else if(SuppStaticVariable.INSTANCE_APR.equals(yy) || SuppStaticVariable.INSTANCE_JUL.equals(yy) || SuppStaticVariable.INSTANCE_OCT.equals(yy)){
			  executeMonth();        // 生成月度报表
			  executeQuarter();      // 生成季度报表
		  }else{ 
			  executeMonth();        // 生成月度报表
		  }
	}
	
	public int repair(){
		  int num = 0;
		  try {
			  String currentDate = StaticMethod.getCurrentDateTime();
			  String yy = currentDate.substring(5,7);		
			  // 以下代码主要完成任务的实现
			  if(SuppStaticVariable.INSTANCE_JAN.equals(yy)){ 
				  num += executeMonth();        // 生成月度报表
				  num += executeQuarter();      // 生成季度报表
				  num += executeYear();         // 生成年度报表
			  }else if(SuppStaticVariable.INSTANCE_APR.equals(yy) || SuppStaticVariable.INSTANCE_JUL.equals(yy) || SuppStaticVariable.INSTANCE_OCT.equals(yy)){
				  num += executeMonth();        // 生成月度报表
				  num += executeQuarter();      // 生成季度报表
			  }else{ 
				  num += executeMonth();        // 生成月度报表
			  }
			  System.out
				.println("===================================================>执行成功!");
		  }
		  catch (Exception e) {
			  num = -1;
			  BocoLog.error(this, e.toString());
			  System.out.println("====================================> 执行出错!");
		  }
		  return num;
	}
	
	public int executeMonth(){
		int monthNum = 0;
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");		
		  String currentTime = StaticMethod.getCurrentDateTime();
		  int date = Integer.parseInt(currentTime.substring(8,10));
		  String py = SuppStaticVariable.getLocalString(date, 0);
		  String year = py.substring(0, 4);
		  String timeLatitude = py.substring(5,7);		  
		// 周期为月度报表
		String seleReportOrder = "from TawSuppKpiInstReportOrder reportOrder where reportOrder.latitude = '106010301' and reportOrder.state = '1' and reportOrder.appendState != '1' and reportEndTime >=:reportTime and reportStartTime <=:reportTime and (reportOrder.id not in(select modelId from TawSuppkpiReportStorage where reportTime='" + timeLatitude + "' and reportBelongTime='" + year + "'))" ;		
		List list = mgr.getTawSuppKpiInstReOrderQuerys(seleReportOrder, py);
		for(int i = 0;i<list.size();i++){
			TawSuppKpiInstReportOrder reportOrder = (TawSuppKpiInstReportOrder)list.get(i);
			String specialType = reportOrder.getSpecialType();
			String latitude = reportOrder.getLatitude();
			String modelId = reportOrder.getId();
			int temp = createReportStorage(specialType,latitude,timeLatitude, modelId);
			if (temp > 0) {
				monthNum ++;
			}
		}
		return monthNum;
	}
	// 下面执行季度报表的生成，调用createReportStorage方法生成报表
	public int executeQuarter(){
		int quarterNum = 0;
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");		
		  String currentTime = StaticMethod.getCurrentDateTime();
		  int date = Integer.parseInt(currentTime.substring(8,10));
		  String py = SuppStaticVariable.getLocalString(date, 0);
		  String year = py.substring(0, 4);
		  int fy = Integer.parseInt(py.substring(5,7));
		  String timeLatitude = "";
		  if(fy==3){
				timeLatitude="one";
			}else if(fy==6){
				timeLatitude="two";
			}else if(fy==9){
				timeLatitude="three";
			}else if(fy==12){
				timeLatitude="four";
			}
		  
		// 周期为季度报表
		String seleReportOrder = "from TawSuppKpiInstReportOrder reportOrder where reportOrder.latitude = '106010302' and reportOrder.state = '1' and reportOrder.appendState != '1' and reportEndTime >=:reportTime and reportStartTime <=:reportTime and (reportOrder.id not in(select modelId from TawSuppkpiReportStorage where reportTime='" + timeLatitude + "' and reportBelongTime='" + year + "'))" ;
		List list = mgr.getTawSuppKpiInstReOrderQuerys(seleReportOrder, py);
		for(int i = 0;i<list.size();i++){
			TawSuppKpiInstReportOrder reportOrder = (TawSuppKpiInstReportOrder)list.get(i);
			String specialType = reportOrder.getSpecialType();
			String latitude = reportOrder.getLatitude();
			String modelId = reportOrder.getId(); 
			int temp = createReportStorage(specialType,latitude,timeLatitude, modelId);
			if (temp > 0) {
				quarterNum ++;
			}
		}
		return quarterNum;
	}
	
	public int executeYear(){
		int yearNum = 0;
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");		
		  String currentTime = StaticMethod.getCurrentDateTime();
		  int date = Integer.parseInt(currentTime.substring(8,10));
		  String py = SuppStaticVariable.getLocalString(date, 0);
		  String year = py.substring(0, 4);
		  String timeLatitude = "year";		  
		// 周期为年度报表
		String seleReportOrder = "from TawSuppKpiInstReportOrder reportOrder where reportOrder.latitude = '106010303' and reportOrder.state = '1' and reportOrder.appendState != '1'  and reportEndTime >=:reportTime and reportStartTime <=:reportTime and (reportOrder.id not in(select modelId from TawSuppkpiReportStorage where reportTime='" + timeLatitude + "' and reportBelongTime='" + year + "'))" ;
		List list = mgr.getTawSuppKpiInstReOrderQuerys(seleReportOrder, py);
		for(int i = 0;i<list.size();i++){
			TawSuppKpiInstReportOrder reportOrder = (TawSuppKpiInstReportOrder)list.get(i);
			String specialType = reportOrder.getSpecialType();
			String latitude = reportOrder.getLatitude();
			String modelId = reportOrder.getId();
			int temp = createReportStorage(specialType,latitude,timeLatitude, modelId);
			if (temp > 0) {
				yearNum ++;
			}
		}
		return yearNum;
	}	
	
	/**
	 * 生成统计报表存储表 注意季度报表和年度报表数据是求和或求平均等生成的。
	 */
	public int createReportStorage(String special, String latitude, String timeLatitude, String modelId){
		  String currentTime = StaticMethod.getCurrentDateTime();
		  int date = Integer.parseInt(currentTime.substring(8,10));
		  int num=0;
		  String py = SuppStaticVariable.getLocalString(date, 0);
		  String year = py.substring(0,4);
// int fy = Integer.parseInt(py.substring(5,7));
// String timeLatitude = "";
// String timeLatitude1="";
// if(fy==3){
// timeLatitude="one";
// }else if(fy==6){
// timeLatitude="two";
// }else if(fy==9){
// timeLatitude="three";
// }else if(fy==12){
// timeLatitude="four";
// timeLatitude1="year";
// }
		
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");
		
		ITawSupplierkpiInstanceManager mgr1 = (ITawSupplierkpiInstanceManager) ApplicationContextHolder.getInstance().getBean("ItawSupplierkpiInstanceManager");
		TawSuppkpiReportStorage tawSuppkpiReportStorage = new TawSuppkpiReportStorage();
		boolean flag = true;
		String manufactureId = "";
		String specialType = "";
		String fillRole = "";
		// ---------若是季度或年度报表，需要对数据特殊处理-----------------
		if(timeLatitude.equals("one")||timeLatitude.equals("two")||timeLatitude.equals("three")||timeLatitude.equals("four")||timeLatitude.equals("year")){
			String[] timeLatitudes = new String[3];
			if(timeLatitude.equals("one")){
				timeLatitudes[0]="01";timeLatitudes[1]="02";timeLatitudes[2]="03";
			}
			else if (timeLatitude.equals("two")){
				timeLatitudes[0]="04";timeLatitudes[1]="05";timeLatitudes[2]="06";
			}
			else if (timeLatitude.equals("three")){
				timeLatitudes[0]="07";timeLatitudes[1]="08";timeLatitudes[2]="09";
			}
			else if (timeLatitude.equals("four")){
				timeLatitudes[0]="10";timeLatitudes[1]="11";timeLatitudes[2]="12";
			}
			String hqlm = "";
			if(!timeLatitude.equals("year")){
				hqlm = "select max(instance.id) as id,instance.manufacturerId as manufacturerId,max(matching.modelId) as modelId,matching.reportcol as reportcol,max(instance.statictsCycle) as statictsCycle,max(instance.serviceType)as serviceType,max(instance.specialType)as specialType,"+
	            " max(instance.timeLatitude) as timeLatitude,max(instance.year) as year,sum(instance.examineContent+0)as examineContent,max(instance.fillRole)as fillRole,max(instance.unit)as unit,max(instance.kpiItemId) as kpiItemId" +
			    " from TawSuppKpiInstReportOrder reportOrder,TawSuppkpiReportmodelMatching matching,TawSupplierkpiInstance instance"+
	            " where reportOrder.id=matching.modelId and instance.kpiItemId=matching.kpiItemId    "+
	            " and reportOrder.specialType='"+special+"'and reportOrder.specialType=instance.specialType and reportOrder.latitude = '"+latitude+"' "+
	            " and (instance.timeLatitude='"+timeLatitudes[0]+"'" +" or instance.timeLatitude='"+timeLatitudes[1]+"'" +" or instance.timeLatitude='"+timeLatitudes[2]+"'" +
	            " ) and instance.year='"+year+"' and reportEndTime >=:reportTime and reportStartTime <=:reportTime" + 
	            " and reportOrder.state='1' and reportOrder.appendState != '1' and  instance.fillFlag!=0 and matching.modelId='"+ modelId +"' group by instance.manufacturerId,matching.reportcol order by instance.manufacturerId,matching.reportcol" ;  // instance.id
			}
			// 在最后有多个专业数据时如果发现数据分解错误时,可换成instance.serviceType试一下
            
			if(timeLatitude.equals("year")){
				String[] tl = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"}; 
				hqlm = "select max(instance.id) as id,instance.manufacturerId as manufacturerId,max(matching.modelId) as modelId,matching.reportcol as reportcol,max(instance.statictsCycle) as statictsCycle,max(instance.serviceType)as serviceType,max(instance.specialType)as specialType,"+
	            " max(instance.timeLatitude) as timeLatitude,max(instance.year) as year,sum(instance.examineContent+0)as examineContent,max(instance.fillRole)as fillRole,max(instance.unit)as unit,max(instance.kpiItemId) as kpiItemId" +
			    " from TawSuppKpiInstReportOrder reportOrder,TawSuppkpiReportmodelMatching matching,TawSupplierkpiInstance instance"+
	            " where reportOrder.id=matching.modelId and instance.kpiItemId=matching.kpiItemId    "+
	            " and reportOrder.specialType='"+special+"'and reportOrder.specialType=instance.specialType and reportOrder.latitude = '"+latitude+"' "+
	            " and (instance.timeLatitude='"+tl[0]+"'" +" or instance.timeLatitude='"+tl[1]+"'" +" or instance.timeLatitude='"+tl[2]+"'" +" or instance.timeLatitude='"+tl[3]+"'"+" or instance.timeLatitude='"+tl[4]+"'"+" or instance.timeLatitude='"+tl[5]+"'"+" or instance.timeLatitude='"+tl[6]+"'"+
	            " or instance.timeLatitude='"+tl[7]+"'"+" or instance.timeLatitude='"+tl[8]+"'"+" or instance.timeLatitude='"+tl[9]+"'"+" or instance.timeLatitude='"+tl[10]+"'"+" or instance.timeLatitude='"+tl[11]+"'"+
	            " ) and instance.year='"+year+"' and reportEndTime >=:reportTime and reportStartTime <= :reportTime" + 
	            " and reportOrder.state='1' and reportOrder.appendState != '1' and  instance.fillFlag!=0 and matching.modelId='"+ modelId +"' group by instance.manufacturerId,matching.reportcol order by instance.manufacturerId,matching.reportcol" ; 
			}
			List instances = mgr1.getTawSupplierkpiInstances(hqlm, py);

			for (int i = 0; i < instances.size(); i++) {
				Object[] cc = (Object[]) instances.get(i);
				// 列转行完成,保存上一条信息
				if ((!"".equals(manufactureId) && !manufactureId.equals(String.valueOf(cc[1]).trim()))
						|| (!"".equals(specialType) && !specialType.equals(String.valueOf(cc[6]).trim()))
							|| (!"".equals(fillRole) && !fillRole.equals(String.valueOf(cc[10]).trim()))) {
					mgr.saveTawSuppkpiReportStorage(tawSuppkpiReportStorage);
					flag = true;
				}
				if(flag){
				tawSuppkpiReportStorage = clearBean(tawSuppkpiReportStorage);
				
				// 开始组装新的一条数据
				tawSuppkpiReportStorage.setModelId(String.valueOf(cc[2]));              // 模型ID
				tawSuppkpiReportStorage.setTimeGranularity(String.valueOf(cc[4]));      // 时间粒度
																						// 即统计周期
				tawSuppkpiReportStorage.setManufacturerId(String.valueOf(cc[1]));       // 厂商ID
				tawSuppkpiReportStorage.setServiceType(String.valueOf(cc[5]));          // 服务类型
				tawSuppkpiReportStorage.setSpecialType(String.valueOf(cc[6]));          // 专业类型
				tawSuppkpiReportStorage.setReportTime(timeLatitude);           // 报表时间
				tawSuppkpiReportStorage.setReportBelongTime(String.valueOf(cc[8]));     // 报表时间
				tawSuppkpiReportStorage.setCreateTime(StaticMethod.getTimestamp(StaticMethod.getCurrentDateTime()));       // 生成时间
				tawSuppkpiReportStorage.setFillRole(String.valueOf(cc[10]));
				//当评估数据单位是百分比的时候，报表数据是平均值。
				String examineContent = String.valueOf(cc[9]);
				if(String.valueOf(cc[11]).equals("106010502")&&instances.size()>0){
					float fexamineContent = Float.parseFloat(String.valueOf(cc[9]));
					if(timeLatitude.equals("year"))fexamineContent /= 12;
					else fexamineContent /= 3;
					examineContent = Double.toString(Math.round(fexamineContent*100)*0.01);
				}
				tawSuppkpiReportStorage = (TawSuppkpiReportStorage)conversion(tawSuppkpiReportStorage,cc,examineContent);
				
				manufactureId = String.valueOf(cc[1]).trim();
				specialType = String.valueOf(cc[6]).trim();
				fillRole = String.valueOf(cc[10]);
				flag=false;
				}else{
					tawSuppkpiReportStorage = (TawSuppkpiReportStorage)conversion(tawSuppkpiReportStorage,cc);
					manufactureId = String.valueOf(cc[1]).trim();
					specialType = String.valueOf(cc[6]).trim();
				}
			}
			num+=instances.size();
		}
		// -------------------
		// and reportOrder.serviceType=instance.serviceType 去掉了
		String hql = "select instance.id,instance.manufacturerId,matching.modelId,matching.reportcol,instance.statictsCycle,instance.serviceType,instance.specialType,instance.timeLatitude,instance.year,instance.examineContent,instance.fillRole,instance.unit,instance.kpiItemId" +
				     " from TawSuppKpiInstReportOrder reportOrder,TawSuppkpiReportmodelMatching matching,TawSupplierkpiInstance instance"+
                     " where reportOrder.id=matching.modelId and instance.kpiItemId=matching.kpiItemId    "+
                     " and reportOrder.specialType='"+special+"'and reportOrder.specialType=instance.specialType and reportOrder.latitude = '"+latitude+"' and reportOrder.latitude=instance.statictsCycle"+
                     " and instance.timeLatitude='"+timeLatitude+"'"+
                     " and instance.year='"+year+"' and reportEndTime >=:reportTime and reportStartTime <=:reportTime" + 
                     " and reportOrder.state='1' and reportOrder.appendState != '1' and  instance.fillFlag!=0 and matching.modelId='"+ modelId +"' group by instance.manufacturerId,matching.modelId,instance.statictsCycle," +
                     "instance.serviceType,instance.specialType,instance.timeLatitude,instance.year,instance.examineContent,matching.reportcol,instance.id,instance.fillRole,instance.unit,instance.kpiItemId"+
					 " order by instance.manufacturerId,matching.modelId,instance.fillRole,matching.reportcol";  // instance.id
																													// 在最后有多个专业数据时如果发现数据分解错误时,可换成instance.serviceType试一下
		// System.out.println("=======>"+hql);
		List list = mgr.getTawSuppkpiReportStorages(hql, py);
		

		for (int i = 0; i < list.size(); i++) {
			Object[] temp = (Object[]) list.get(i);
			// 列转行完成,保存上一条信息
			if ((!"".equals(manufactureId) && !manufactureId.equals(String.valueOf(temp[1]).trim()))
					|| (!"".equals(specialType) && !specialType.equals(String.valueOf(temp[6]).trim()))
						|| (!"".equals(fillRole) && !fillRole.equals(String.valueOf(temp[10]).trim()))) {
				mgr.saveTawSuppkpiReportStorage(tawSuppkpiReportStorage);
				flag = true;
			}
			if(flag){
				// tawSuppkpiReportStorage 对象中的内容由多列数据组成,所以不能在每次循环时重新new一个新对象,
				// 所以在组装一条新数据前须手动将tawSuppkpiReportStorage对象中的原有数据用clearBean()方法清除.
				tawSuppkpiReportStorage = clearBean(tawSuppkpiReportStorage);
				
				// 开始组装新的一条数据
				tawSuppkpiReportStorage.setModelId(String.valueOf(temp[2]));              // 模型ID
				tawSuppkpiReportStorage.setTimeGranularity(String.valueOf(temp[4]));      // 时间粒度
																							// 即统计周期
				tawSuppkpiReportStorage.setManufacturerId(String.valueOf(temp[1]));       // 厂商ID
				tawSuppkpiReportStorage.setServiceType(String.valueOf(temp[5]));          // 服务类型
				tawSuppkpiReportStorage.setSpecialType(String.valueOf(temp[6]));          // 专业类型
				tawSuppkpiReportStorage.setReportTime(String.valueOf(temp[7]));           // 报表时间
				tawSuppkpiReportStorage.setReportBelongTime(String.valueOf(temp[8]));     // 报表时间
				tawSuppkpiReportStorage.setCreateTime(StaticMethod.getTimestamp(StaticMethod.getCurrentDateTime()));       // 生成时间
				tawSuppkpiReportStorage.setFillRole(String.valueOf(temp[10]));
				tawSuppkpiReportStorage = (TawSuppkpiReportStorage)conversion(tawSuppkpiReportStorage,temp);
				
				manufactureId = String.valueOf(temp[1]).trim();
				specialType = String.valueOf(temp[6]).trim();
				fillRole = String.valueOf(temp[10]);
				flag=false;
			}else{
				tawSuppkpiReportStorage = (TawSuppkpiReportStorage)conversion(tawSuppkpiReportStorage,temp);
				manufactureId = String.valueOf(temp[1]).trim();
				specialType = String.valueOf(temp[6]).trim();
			}
			num+=list.size();

		}			
        // 列转行完成,保存最后一条信息
		if(tawSuppkpiReportStorage.getModelId()!=null && !tawSuppkpiReportStorage.getModelId().equals("")){
			mgr.saveTawSuppkpiReportStorage(tawSuppkpiReportStorage);
		}
		return num;
	}
	/**
	 * Description:用于列数据转换成行数据
	 */
	public TawSuppkpiReportStorage conversion(TawSuppkpiReportStorage storage,Object[] temp) {
		try {
			Field[] fields=storage.getClass().getDeclaredFields();
			String temp_exco = String.valueOf(temp[9]);
			String field = String.valueOf(temp[3]);
			for(int i=1;i<fields.length;i++){					
				if(fields[i].getName().trim().equals(field.trim())){
					fields[i].setAccessible(true) ;
					fields[i].set(storage,temp_exco);
					fields[i].setAccessible(false) ;
					break;
				}
			}			
		}
		catch (Exception e) {
			BocoLog.error(this, e.toString());
		}
		return storage ;
	}
	/**
	 * Description:用于列数据转换成行数据 增加此方法用于在生成季度报表时给相关指标项赋值
	 */
	public TawSuppkpiReportStorage conversion(TawSuppkpiReportStorage storage,Object[] temp,String examineContent) {
		try {
			Field[] fields=storage.getClass().getDeclaredFields();
			String field = String.valueOf(temp[3]);// col_1
			for(int i=1;i<fields.length;i++){					
				if(fields[i].getName().trim().equals(field.trim())){
					fields[i].setAccessible(true) ;
					fields[i].set(storage,examineContent);
					fields[i].setAccessible(false) ;
					break;
				}
			}			
		}
		catch (Exception e) {
			BocoLog.error(this, e.toString());
		}
		return storage ;
	}
	/**
	 * Description:用于清除指定Bean中的原有数据
	 */	
	public TawSuppkpiReportStorage clearBean(TawSuppkpiReportStorage storage) {
		try {
			Field[] fields=storage.getClass().getDeclaredFields();			
			int k=1;			
			for(int j=1;j<fields.length;j++){
				String temp_clear = "col_"+k; 
				if(fields[j].getName().trim().equals(temp_clear.trim())){
					fields[j].setAccessible(true) ;
					fields[j].set(storage,"");
					fields[j].setAccessible(false) ;
					k++;
				}
			}			
		}
		catch (Exception e) {
			BocoLog.error(this, e.toString());
		}
		return storage ;
	}	
}
