package com.boco.eoms.commons.job.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务类型DAO
 * </p>
 * <p>Apr 10, 2007 10:33:39 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public interface TawCommonsJobsortDao extends Dao {

	/**
	 * Retrieves all of the tawCommonsJobsorts
	 */
	public List getTawCommonsJobsorts(TawCommonsJobsort tawCommonsJobsort);

	/**
	 * Gets tawCommonsJobsort's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawCommonsJobsort's id
	 * @return tawCommonsJobsort populated tawCommonsJobsort object
	 */
	public TawCommonsJobsort getTawCommonsJobsort(final Integer id);

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
	public void removeTawCommonsJobsort(final Integer id);
	
	//2009-5-21 根据任务类型名称查询数据
	public List getTawCommonsJobsortByJobSortName(String jobSortName);
}
