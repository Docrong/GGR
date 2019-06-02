package com.boco.eoms.extra.supplierkpi.schedule;

import java.lang.reflect.Field;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder;
import com.boco.eoms.extra.supplierkpi.model.TawSuppkpiReportStorage;
import com.boco.eoms.extra.supplierkpi.service.ITawSuppKpiInstReportOrderManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSuppKpiInstReportOrderForm;
/**
 * <p>
 * Title:用于手动追报加报表指标，并生相应报表 
 * </p>
 * <p>
 * Description: 用于手动追报加报表指标，并生相应报表  
 * </p>
 * <p>
 * 2007-12-28,14:32:52 pM
 * </p> 
 * @author
 * @version 1.0 
 */
public class TawSuppkpiReportStorageRepair {
	
	TawSuppKpiInstReportOrderForm reportOrderForm = new TawSuppKpiInstReportOrderForm();
	
//	  String currentTime = StaticMethod.getCurrentDateTime();
//	  int date = Integer.parseInt(currentTime.substring(8,10));
//	  String py = SuppStaticVariable.getLocalString(date, 0);		  
//	  int fy = Integer.parseInt(py.substring(5,7));
//    int counter = 0;
//	  if(fy==3){
//		  counter=1;
//		}else if(fy==6){
//			counter=2;
//		}else if(fy==9){
//			counter=3;
//		}else if(fy==12){
//			counter=4;
//		}		
//	String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
//	String[] quarters = {"one","two","three","four"};
	
	/**
	 * 构造方法
	 * @param reportOrderForm
	 */
	public TawSuppkpiReportStorageRepair(TawSuppKpiInstReportOrderForm reportOrderForm){
		this.reportOrderForm = reportOrderForm;		
	}
	/**
	 * 追加指标后生成相应报表
	 */
	public void executeCreate(){	

//	    if(reportOrderForm.getLatitude().trim().equals("106010301")){
//			for(int i=0;i<fy;i++){
//				String month =  months[i].trim();
//				createReportStorage(month);
//			}	    	
//	    }else if(reportOrderForm.getLatitude().trim().equals("106010302")){
//			for(int i=0;i<counter;i++){
//			String quarter =  quarters[i].trim();
//			createReportStorage(quarter);
//		    }	    	
//	    }else{
//			createReportStorage("year");
//		}
		if(reportOrderForm.getMonth().trim()!=null && !reportOrderForm.getMonth().trim().equals("")){
			String monthTemp = reportOrderForm.getMonth().substring(0,reportOrderForm.getMonth().length()-2);
			String[] temp = StaticMethod.TokenizerString2(monthTemp, "@@");
			for(int i=0;i<temp.length;i++){
				String month =  temp[i].trim();
				createReportStorage(month);
			}
		}else if(reportOrderForm.getQuarter().trim()!=null && !reportOrderForm.getQuarter().trim().equals("")){
			String quarterTemp = reportOrderForm.getQuarter().substring(0,reportOrderForm.getQuarter().length()-2);
			String[] temp = StaticMethod.TokenizerString2(quarterTemp, "@@");
			for(int i=0;i<temp.length;i++){
				String quarter =  temp[i].trim();
				createReportStorage(quarter);
			}
		}else{
			createReportStorage("year");
		}
		System.out.println("=======》追加报表执行成功！");
	}
	
	/**
	 * 生成统计报表存储表
	 */
	public void createReportStorage(String timeLatitude){
		
		String year = reportOrderForm.getYear();
		String special = reportOrderForm.getSpecialType();
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");
		
		String hql = "select instance.id,instance.manufacturerId,matching.modelId,matching.reportcol,instance.statictsCycle,instance.serviceType,instance.specialType,instance.timeLatitude,instance.year,instance.examineContent" +
				     " from TawSuppKpiInstReportOrder reportOrder,TawSuppkpiReportmodelMatching matching,TawSupplierkpiInstance instance"+
                     " where reportOrder.id=matching.modelId and instance.kpiItemId=matching.kpiItemId and reportOrder.serviceType=instance.serviceType"+
                     " and reportOrder.specialType=instance.specialType and reportOrder.latitude=instance.statictsCycle"+
                     " and instance.timeLatitude='"+timeLatitude+"' and reportOrder.specialType = '"+special+"'"+
                     " and instance.year='"+year+"'" + 
                     " and reportOrder.state='1' and reportOrder.appendState='1' and  instance.fillFlag!=0 group by instance.manufacturerId,matching.modelId,instance.statictsCycle," +
                     "instance.serviceType,instance.specialType,instance.timeLatitude,instance.year,instance.examineContent,matching.reportcol,instance.id"+
					 " order by instance.manufacturerId,instance.id";  //instance.id 在最后有多个专业数据时如果发现数据分解错误时,可换成instance.serviceType试一下
		//System.out.println("=======>"+hql);
		List list = mgr.getTawSuppkpiReportStorages(hql);
		TawSuppkpiReportStorage tawSuppkpiReportStorage = new TawSuppkpiReportStorage();

		boolean flag = true;
		String manufacturerId = "";
		String specialType = "";
		for (int i = 0; i < list.size(); i++) {
			Object[] temp = (Object[]) list.get(i);
			// 列转行完成,保存上一条信息
			if ((!"".equals(manufacturerId) && !manufacturerId.equals(String
					.valueOf(temp[1]).trim()))
					|| (!"".equals(specialType) && !specialType.equals(String
							.valueOf(temp[6]).trim()))) {
				mgr.saveTawSuppkpiReportStorage(tawSuppkpiReportStorage);
				flag = true;
			}
			if(flag){
				//tawSuppkpiReportStorage 对象中的内容由多列数据组成,所以不能在每次循环时重新new一个新对象,
				//所以在组装一条新数据前须手动将tawSuppkpiReportStorage对象中的原有数据用clearBean()方法清除.
				tawSuppkpiReportStorage = clearBean(tawSuppkpiReportStorage);
				//开始组装新的一条数据				
				tawSuppkpiReportStorage.setModelId(String.valueOf(temp[2]));              //模型ID
				tawSuppkpiReportStorage.setTimeGranularity(String.valueOf(temp[4]));      //时间粒度 即统计周期
				tawSuppkpiReportStorage.setManufacturerId(String.valueOf(temp[1]));       //厂商ID
				tawSuppkpiReportStorage.setServiceType(String.valueOf(temp[5]));          //服务类型
				tawSuppkpiReportStorage.setSpecialType(String.valueOf(temp[6]));          //专业类型
				tawSuppkpiReportStorage.setReportTime(String.valueOf(temp[7]));           //报表时间
				tawSuppkpiReportStorage.setReportBelongTime(String.valueOf(temp[8]));     //报表时间
				tawSuppkpiReportStorage.setCreateTime(StaticMethod.getTimestamp(StaticMethod.getCurrentDateTime()));       //生成时间
				tawSuppkpiReportStorage = (TawSuppkpiReportStorage)conversion(tawSuppkpiReportStorage,temp);
				manufacturerId = String.valueOf(temp[1]).trim();
				specialType = String.valueOf(temp[6]).trim();
				flag=false;
			}else{
				tawSuppkpiReportStorage = (TawSuppkpiReportStorage)conversion(tawSuppkpiReportStorage,temp);
				manufacturerId = String.valueOf(temp[1]).trim();
				specialType = String.valueOf(temp[6]).trim();
			}

		}			
        //列转行完成,保存最后一条信息
		if(tawSuppkpiReportStorage.getModelId()!=null && !tawSuppkpiReportStorage.getModelId().equals("")){
			mgr.saveTawSuppkpiReportStorage(tawSuppkpiReportStorage);
		}		
	}
	/**
	 * Description:用于列数据转换成行数据
	 */
	public TawSuppkpiReportStorage conversion(TawSuppkpiReportStorage storage,
			Object[] temp) {
		try {
			Field[] fields = storage.getClass().getDeclaredFields();
			String temp_exco = String.valueOf(temp[9]);
			String field = String.valueOf(temp[3]);
			for (int i = 1; i < fields.length; i++) {
				if (fields[i].getName().trim().equals(field.trim())) {
					fields[i].setAccessible(true);
					fields[i].set(storage, temp_exco);
					fields[i].setAccessible(false);
					break;
				}
			}
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
		}
		return storage;
	}

	/**
	 * Description:用于清除指定Bean中的原有数据
	 */
	public TawSuppkpiReportStorage clearBean(TawSuppkpiReportStorage storage) {
		try {
			Field[] fields = storage.getClass().getDeclaredFields();
			int k = 1;
			for (int j = 1; j < fields.length; j++) {
				String temp_clear = "col_" + k;
				if (fields[j].getName().trim().equals(temp_clear.trim())) {
					fields[j].setAccessible(true);
					fields[j].set(storage, "");
					fields[j].setAccessible(false);
					k++;
				}
			}
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
		}
		return storage;
	}
	/**
	 * 删除统计报表中无用的数据
	 */		
	public void cleanHistoryReportStorage(){
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");
		
		String year = reportOrderForm.getYear();                            //年度
		String month = "";                                                  //月度
		String quarter = "";                                                //季度

//	    if(reportOrderForm.getLatitude().trim().equals("106010301")){
//			for(int i=0;i<fy;i++){
//				month +=  months[i].trim()+"','";
//			}
//			month = month.substring(0,month.length()-3);	    	
//	    }else if(reportOrderForm.getLatitude().trim().equals("106010302")){
//			for(int i=0;i<counter;i++){
//				quarter +=  quarters[i].trim()+"','";
//			}
//			quarter = quarter.substring(0,quarter.length()-3);	    	
//	    }		

		if(reportOrderForm.getMonth().trim()!=null && !reportOrderForm.getMonth().trim().equals("")){
			String monthTemp = reportOrderForm.getMonth().substring(0,reportOrderForm.getMonth().length()-2);
			String[] temp = StaticMethod.TokenizerString2(monthTemp, "@@");
			for(int i=0;i<temp.length;i++){
				month +=  temp[i].trim()+"','";
			}
			month = month.substring(0,month.length()-3);
		}		
		if(reportOrderForm.getQuarter().trim()!=null && !reportOrderForm.getQuarter().trim().equals("")){
			String quarterTemp = reportOrderForm.getQuarter().substring(0,reportOrderForm.getQuarter().length()-2);
			String[] temp = StaticMethod.TokenizerString2(quarterTemp, "@@");
			for(int i=0;i<temp.length;i++){
				quarter +=  temp[i].trim()+"','";
			}
			quarter = quarter.substring(0,quarter.length()-3);
		}
		
	    String delReportStorage ="";
	    if(month!=null && !"".equals(month)){
	    	delReportStorage = "delete from TawSuppkpiReportStorage storage where storage.specialType = "+
            reportOrderForm.getSpecialType()+" and storage.reportTime in ('"+month+"')"+
            " and storage.reportBelongTime = '"+year+"'";	    	
	    }else if(quarter!=null && !"".equals(quarter)){
	    	delReportStorage = "delete from TawSuppkpiReportStorage storage where storage.specialType = "+
            reportOrderForm.getSpecialType()+" and storage.reportTime in ('"+quarter+"')"+
            " and storage.reportBelongTime = '"+year+"'";	    	
	    }else{
	    	delReportStorage = "delete from TawSuppkpiReportStorage storage where storage.specialType = "+
            reportOrderForm.getSpecialType()+" and storage.reportTime = 'year'"+
            " and storage.reportBelongTime = '"+year+"'";	    	
	    }
	    mgr.cleanHistoryReport(delReportStorage);	
	    System.out.println("=======》删除报表执行成功！");
	}

	/**
	 * 修改报表订制状态成功
	 */	
	public void modifyReportOrderState(){
		ITawSuppKpiInstReportOrderManager mgr = (ITawSuppKpiInstReportOrderManager) ApplicationContextHolder
		.getInstance().getBean("ItawSuppKpiInstReportOrderManager");
		
		//String delReportOrder = "delete from TawSuppKpiInstReportOrder reportOrder where reportOrder.state = '2'";
		String selReportOrder = "from TawSuppKpiInstReportOrder reportOrder where  reportOrder.state = '1' and reportOrder.appendState = '1'";	
		//String modelId = "";
		List list = mgr.getTawSuppKpiInstReOrderQuerys(selReportOrder);
		if(list!=null && list.size()>0){
			for(int i = 0;i<list.size();i++){
				TawSuppKpiInstReportOrder reportOrder = (TawSuppKpiInstReportOrder)list.get(i);
				reportOrder.setAppendState("0");
				mgr.saveTawSuppKpiInstReportOrder(reportOrder);
				//String tempId = reportOrder.getId();
				//modelId +=  tempId.trim()+"','";				
			}	
			//modelId = modelId.substring(0,modelId.length()-3);
			//String delModelMatching = "delete from TawSuppkpiReportmodelMatching modelMaching where modelMaching.modelId in ('"+modelId+"')";
			//删除报表模型匹配表中的无用数据
			//mgr.cleanHistoryReport(delModelMatching);
			//删除报表订表中的无用数据
			//mgr.cleanHistoryReport(delReportOrder);
			System.out.println("=======》修改报表订制状态成功！");
		}		
	}

}
