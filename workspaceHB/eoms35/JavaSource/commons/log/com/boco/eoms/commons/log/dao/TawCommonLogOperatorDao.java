package com.boco.eoms.commons.log.dao;

import java.util.List;
import java.util.Map;
import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;

public interface TawCommonLogOperatorDao extends Dao {

	/**
	 * Retrieves all of the tawCommonLogOperators
	 */
	public List getTawCommonLogOperators(
			TawCommonLogOperator tawCommonLogOperator);

	/**
	 * Gets tawCommonLogOperator's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
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
	public Map getAllbyUseridModelidOperids(final Integer curPage, 
			final Integer pageSize,final String userid,final String modelid,
			final String operid,final String issucess,final String startTime,final String endTime);

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
	 * 查询某个模块 的某个业务的操作情况
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
    public List getAllbyisSucess(String issucess);
	
	public String getAllwelcomebyone(String dept);
	
	public String getAlldeptcount(String dept,String begintime,String endtime);
	
	public String getAllusercount(String dept,String begintime,String endtime);

	public String getAllcount(String dept,String begintime,String endtime);
	
	public List getListdeptStat(String dept,String begintime,String endtime);
	
	public List getListuserStat(String dept,String begintime,String endtime);
	
	public Map getAllStat(final Integer curPage, final Integer pageSize,
			final String depts,final String startTime,final String endTime);
}
