package com.boco.eoms.commons.job.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 任务类型service
 * </p>
 * <p>
 * Apr 10, 2007 10:48:48 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 * 
 */
public interface ITawCommonsJobsortManager extends Manager {
	/**
	 * Retrieves all of the tawCommonsJobsorts
	 */
	public List getTawCommonsJobsorts(TawCommonsJobsort tawCommonsJobsort);

	/**
	 * Gets tawCommonsJobsort's information based on id.
	 * 
	 * @param id
	 *            the tawCommonsJobsort's id
	 * @return tawCommonsJobsort populated tawCommonsJobsort object
	 */
	public TawCommonsJobsort getTawCommonsJobsort(final String id);

	/**
	 * Saves a tawCommonsJobsort's information
	 * 
	 * @param tawCommonsJobsort
	 *            the object to be saved
	 */
	public void saveTawCommonsJobsort(TawCommonsJobsort tawCommonsJobsort);

	/**
	 * Removes a tawCommonsJobsort from the database by id
	 * 
	 * @param id
	 *            the tawCommonsJobsort's id
	 */
	public void removeTawCommonsJobsort(final String id);

	/**
	 * 转换任务类别Id为名称
	 * 
	 * @param sortId
	 *            任务类别Id
	 * @return
	 */
	public String sortId2name(Integer sortId);
	
	//2009-5-21 根据任务类型名称查询数据
	public List getTawCommonsJobsortByJobSortName(String jobSortName);
}
