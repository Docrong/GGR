package com.boco.eoms.commons.job.service;

import java.util.List;

import org.quartz.Scheduler;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description: 任务监控service
 * </p>
 * <p>Apr 10, 2007 10:41:52 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public interface ITawCommonsJobmonitorManager extends Manager {
	/**
	 * Retrieves all of the tawCommonsJobmonitors
	 */
	public List getTawCommonsJobmonitors(
			TawCommonsJobmonitor tawCommonsJobmonitor);

	/**
	 * Gets tawCommonsJobmonitor's information based on id.
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
	 * 获取当前所有的正在运行的任务
	 * @return
	 * @author 秦敏
	 */
	public List getRunningJob() throws Exception;
	/**
	 * 删除当前所有正在运行的任务
	 * 
	 * @author 秦敏
	 */
	public void removeRunningJob() throws Exception;
	/**
	 *任务调度器初始化
	 * 
	 * @author 秦敏
	 */
	public void instance() throws ScheduleRunException;
	/**
	 * 系统启动时装载任务
	 * 
	 * @author 秦敏
	 */
	public void run() throws ScheduleRunException;

    /**
     * 系统运行中添加任务
     * @param tawCommonsJobsubscibe 任务订阅信息
     * @throws ScheduleRunException 
     * @author 秦敏
     */
	public void addJob(TawCommonsJobsubscibe tawCommonsJobsubscibe)
			throws ScheduleRunException;
    /**
     * 系统运行中删除任务
     * @param jobName 任务名称
     * @throws ScheduleRunException
     * @author 秦敏
     */
	public void deleteJob(String jobName) throws ScheduleRunException;
    /**
     *  系统运行中修改任务
     * @param tawCommonsJobsubscibe 任务订阅信息
     * @throws ScheduleRunException
     * @author 秦敏
     */
	public void modifyJob(TawCommonsJobsubscibe tawCommonsJobsubscibe)
			throws ScheduleRunException;

	/**
	 * 获取Scheduler
	 * @return
	 * @author 秦敏
	 */
	public Scheduler getSched();
	/**
	 * 获取最近一次执行job的时间
	 * @param subId 订阅号
	 * @return
	 */
	public TawCommonsJobmonitor getLastJobmonitorBySubId(String subId);
}
