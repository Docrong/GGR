package com.boco.eoms.commons.job.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务监控DAO
 * </p>
 * <p>Apr 10, 2007 10:32:45 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public interface TawCommonsJobmonitorDao extends Dao {

	/**
	 * Retrieves all of the tawCommonsJobmonitors
	 */
	public List getTawCommonsJobmonitors(
			TawCommonsJobmonitor tawCommonsJobmonitor);

	/**
	 * Gets tawCommonsJobmonitor's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawCommonsJobmonitor's id
	 * @return tawCommonsJobmonitor populated tawCommonsJobmonitor object
	 */
	public TawCommonsJobmonitor getTawCommonsJobmonitor(final String id);

	/**
	 * Saves a tawCommonsJobmonitor's information
	 * 
	 * @param tawCommonsJobmonitor
	 *            the object to be saved
	 */
	public void saveTawCommonsJobmonitor(
			TawCommonsJobmonitor tawCommonsJobmonitor);

	/**
	 * Removes a tawCommonsJobmonitor from the database by id
	 * 
	 * @param id
	 *            the tawCommonsJobmonitor's id
	 */
	public void removeTawCommonsJobmonitor(final String id);

	/**
	 * 根据订阅号获取订阅信息
	 * @author  秦敏
	 * @param subId 订阅号
	 * @return
	 */
	public TawCommonsJobmonitor getJobMonitorBySubId(String subId);

	/**
	 * 获取当前所有的正在运行的任务
	 * @return
	 * @author 秦敏
	 */
	public List getRunningJob();

	/**
	 * 删除当前所有正在运行的任务
	 * 
	 * @author 秦敏
	 */
	public void removeRunningJob();
	/**
	 * 获取最近一次执行job的时间
	 * @param subId 订阅号
	 * @return
	 */
	public TawCommonsJobmonitor getLastJobmonitorBySubId(String subId);

}
