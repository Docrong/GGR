package com.boco.eoms.commons.job.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务订阅service
 * </p>
 * <p>Apr 10, 2007 10:49:38 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public interface ITawCommonsJobsubscibeManager extends Manager {
	/**
	 * Retrieves all of the tawCommonsJobsubscibes
	 */
	public List getTawCommonsJobsubscibes( );

	/**
	 * Gets tawCommonsJobsubscibe's information based on id.
	 * 
	 * @param id
	 *            the tawCommonsJobsubscibe's id
	 * @return tawCommonsJobsubscibe populated tawCommonsJobsubscibe object
	 */
	public TawCommonsJobsubscibe getTawCommonsJobsubscibe(final String id);
	
	/**
	 * Gets tawCommonsJobsubscibe's information based on id.
	 * 
	 * @param id
	 *            the tawCommonsJobsubscibe's id
	 * @return tawCommonsJobsubscibe populated tawCommonsJobsubscibe object
	 */
	public TawCommonsJobsubscibe getTawCommonsJobsubscibeForSubID(final String subID);

	/**
	 * Saves a tawCommonsJobsubscibe's information
	 * 
	 * @param tawCommonsJobsubscibe
	 *            the object to be saved
	 */
	public void saveTawCommonsJobsubscibe(
			TawCommonsJobsubscibe tawCommonsJobsubscibe, boolean isNew)
			throws ScheduleRunException;

	/**
	 * Removes a tawCommonsJobsubscibe from the database by id
	 * 
	 * @param id
	 *            the tawCommonsJobsubscibe's id
	 */
	public void removeTawCommonsJobsubscibe(final String id) throws ScheduleRunException;

	/**
	 * 新生成任务订阅号
	 * @param jobSortId 任务类型ID
	 * @return
	 * @author 秦敏
	 */
	public String newSubId(String jobSortId);
	
	 /**
	  * 根据任务类型ID号查询任务订阅信息
	  * @param jobSortId 任务类型ID号
	  * @return
	  * @author 秦敏
	  */
	public List getSubscibeListBySortId(Integer jobSortId); 

}
