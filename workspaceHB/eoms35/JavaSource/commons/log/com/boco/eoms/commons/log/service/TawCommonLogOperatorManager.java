package com.boco.eoms.commons.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;

public interface TawCommonLogOperatorManager extends Manager {
	/**
	 * Retrieves all of the tawCommonLogOperators
	 */
	public List getTawCommonLogOperators(
			TawCommonLogOperator tawCommonLogOperator);

	/**
	 * Gets tawCommonLogOperator's information based on id.
	 * 
	 * @param id
	 *            the tawCommonLogOperator's id
	 * @return tawCommonLogOperator populated tawCommonLogOperator object
	 */
	public TawCommonLogOperator getTawCommonLogOperator(final String id);

	/**
	 * Saves a tawCommonLogOperator's information
	 * 
	 * @param tawCommonLogOperator
	 *            the object to be saved
	 */
	public void saveTawCommonLogOperator(
			TawCommonLogOperator tawCommonLogOperator);

	/**
	 * Removes a tawCommonLogOperator from the database by id
	 * 
	 * @param id
	 *            the tawCommonLogOperator's id
	 */
	public void removeTawCommonLogOperator(final String id);

	/**
	 * 通过用户ID 模块ID 操作ID 时间段进行日志查询
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllBymodelopertiems(HttpServletRequest request,final Integer curPage, 
			final Integer pageSize,final String userid,final String modelid,
			final String operid,final String starttime,final String endtime,final String issucess);

	/**
	 * 查询某个用户的所有操作日志
	 * 
	 * @param userid
	 * @return
	 */
	public List getAllByUserIDs(String userid, String issucess);

	/**
	 * 查询某个模块的日志信息
	 * 
	 * @param modelid
	 * @return
	 */
	public List getAllBymodelids(String modelid, String issucess);

	/**
	 * 查询某个用户 对某个模块的操作情况
	 * 
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getAllByuseridandModelids(String userid, String modelid,
			String issucess);

	/**
	 * 查询某个用户对某个模块某个业务的操作情况
	 * 
	 * @param userid
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getAllbyUseridModelidOperids(final Integer curPage, 
			final Integer pageSize,String userid, String modelid,
			String operid, String issucess);

	/**
	 * 查询某个模块 的某个业务的操作情况
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getAllbyModelidAndOperids(String modelid, String operid,
			String issucess);

	/**
	 * 根据userid查询某个用户某个时间段的日志信息
	 * 
	 * @param userid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllByUseridAndTimes(String userid, String starttime,
			String endtime, String issucess);

	/**
	 * 更加模块ID 查询某个模块在某个时间段的日志信息
	 * 
	 * @param modelid
	 * @param starttiem
	 * @param endtime
	 * @return
	 */
	public List getAllByModelidAndTimes(String modelid, String starttiem,
			String endtime, String issucess);

	/**
	 * 根据用户ID 模块ID 查询某个时间段某个用户对某个模块的日志信息
	 * 
	 * @param userid
	 * @param modelid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List getAllByUMIDandTimes(String userid, String modelid,
			String starttime, String endtime, String issucess);

	/**
	 * 根据模块ID 操作ID 查询某个时间段某个模块的某个操作的日志信息
	 * 
	 * @param modelid
	 * @param operid
	 * @param startime
	 * @param endtime
	 * @return
	 */
	public List getAllByMidAndOperidAndTimes(String modelid, String operid,
			String startime, String endtime, String issucess);

}
