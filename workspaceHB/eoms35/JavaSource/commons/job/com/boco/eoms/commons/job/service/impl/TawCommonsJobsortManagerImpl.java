package com.boco.eoms.commons.job.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;
import com.boco.eoms.commons.job.service.ITawCommonsJobsortManager;
import com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务类型service实现类
 * </p>
 * <p>Apr 10, 2007 10:49:11 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsJobsortManagerImpl extends BaseManager implements
		ITawCommonsJobsortManager {
	private TawCommonsJobsortDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonsJobsortDao(TawCommonsJobsortDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsortManager#getTawCommonsJobsorts(com.boco.eoms.commons.job.model.TawCommonsJobsort)
	 */
	public List getTawCommonsJobsorts(final TawCommonsJobsort tawCommonsJobsort) {
		return dao.getTawCommonsJobsorts(tawCommonsJobsort);
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsortManager#getTawCommonsJobsort(String
	 *      id)
	 */
	public TawCommonsJobsort getTawCommonsJobsort(final String id) {
		return dao.getTawCommonsJobsort(new Integer(id));
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsortManager#saveTawCommonsJobsort(TawCommonsJobsort
	 *      tawCommonsJobsort)
	 */
	public void saveTawCommonsJobsort(TawCommonsJobsort tawCommonsJobsort) {
		dao.saveTawCommonsJobsort(tawCommonsJobsort);
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobsortManager#removeTawCommonsJobsort(String
	 *      id)
	 */
	public void removeTawCommonsJobsort(final String id) {
	  dao.removeTawCommonsJobsort(new Integer(id));
	  
	  try{
        //删除任务订阅表中该任务类型涉及的任务类型。  	 
	   ITawCommonsJobsubscibeManager mgr = (ITawCommonsJobsubscibeManager) ApplicationContextHolder
	  
		                                   .getInstance().getBean("ItawCommonsJobsubscibeManager");
	   List jobList=mgr.getSubscibeListBySortId(new Integer(id));
	   if(jobList!=null){
		  for(int i=0;i<jobList.size();i++){
		   TawCommonsJobsubscibe job=(TawCommonsJobsubscibe)jobList.get(i); 	 
		   mgr.removeTawCommonsJobsubscibe(job.getId());
		 }
	  }
	}
	 catch(Exception e){
		 
	 }
  }	  
	public String sortId2name(Integer sortId) {
		String sortName = "";
		TawCommonsJobsort jobSort = dao.getTawCommonsJobsort(sortId);
		if (null == jobSort) {
			sortName = "此任务类型已不存在，原ID为：" + String.valueOf(sortId);
		} else {
			sortName = jobSort.getJobSortName();
		}
		return sortName;
	}
	
	//2009-5-21 根据任务类型名称查询数据
	public List getTawCommonsJobsortByJobSortName(String jobSortName){
		return dao.getTawCommonsJobsortByJobSortName(jobSortName);
	}
}
