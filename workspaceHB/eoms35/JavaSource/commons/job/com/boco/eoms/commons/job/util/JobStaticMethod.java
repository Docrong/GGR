package com.boco.eoms.commons.job.util;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.service.ITawCommonsJobsortManager;
/**
 * 
 * <p>Title:任务组件静态方法类
 * </p>
 * <p>Description:
 * </p>
 * <p>Apr 20, 2007 9:01:42 PM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class JobStaticMethod {
  /**
   * 获取任务类型，拼接成任务类型下拉框
   * @return
   * @author 秦敏
   */
  public static List getJobSort(){
	List sortInfoList=new ArrayList();
	ITawCommonsJobsortManager mgr = (ITawCommonsJobsortManager) ApplicationContextHolder
	                                 .getInstance().getBean("ItawCommonsJobsortManager");
    List sortList=mgr.getTawCommonsJobsorts(new TawCommonsJobsort());
	for(int i=0;i<sortList.size();i++){
      TawCommonsJobsort sort=(TawCommonsJobsort)sortList.get(i);
	  sortInfoList.add(new org.apache.struts.util.LabelValueBean(
			           sort.getJobSortName(), sort.getId().toString()));
	    }
	  return sortInfoList;	
	}
	
}
