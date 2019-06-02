package com.boco.eoms.workbench.commission.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionInstance;

public interface ITawWorkbenchCommissionInstanceDao {

	/**
	 * 保存代理实例信息
	 * 
	 * @param commissionInstance
	 *            代理实例信息
	 * @return
	 */
	public void saveCommissionInstance(
			TawWorkbenchCommissionInstance commissionInstance);

	/**
	 * 删除代理实例信息
	 * 
	 * @param commissionInstance
	 *            代理实例信息
	 * @return
	 */
	public void delCommissionInstance(
			TawWorkbenchCommissionInstance commissionInstance);

	/**
	 * 根据ID查询代理实例信息
	 * 
	 * @param id
	 *            代理实例ID
	 * @return TawWorkbenchCommissionInstance 代理实例
	 */
	public TawWorkbenchCommissionInstance getCommissionInstance(String id);

	/**
	 * 根据被代理人ID和模块ID查询代理实例信息
	 * 
	 * @param userId
	 *            被代理人ID
	 * @param moduleId
	 *            模块ID
	 * @return
	 */
	public List listCommissionInstances(String userId, String moduleId);

	/**
	 * 根据被代理人ID查询代理实例信息
	 * 
	 * @param userId
	 *            被代理人ID
	 * @return
	 */
	public List listCommissionInstances(String userId);

	/**
	 * 根据时间段查询代理实例信息
	 * 
	 * @param startTime
	 *            代理开始时间
	 * @param endTime
	 *            代理结束时间
	 * @return
	 */
	public List listCommissionInstancesByPeriod(String startTime, String endTime);

	/**
	 * 分页查询代理实例信息
	 * 
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            总页数
	 * @param whereStr
	 *            查询条件
	 * @return Map 查询结果集
	 */
	public Map listCommissionInstances(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 根据代理人ID和模块ID查询代理实例信息
	 * 
	 * @param trustorId
	 *            代理人ID
	 * @param moduleId
	 *            模块ID
	 * @return
	 */
	public List listCommissionInstancesByTrustorId(String trustorId,
			String moduleId);

	/**
	 * 根代理人ID查询代理实例信息
	 * 
	 * @param trustorId
	 *            代理人ID
	 * @return
	 */
	public List listCommissionInstancesByTrustorId(String trustorId);
	/**
	 *  2009-01-16加
	 * 根据被代理人ID查询代理实例信息
	 * 
	 * @param userId
	 *            被代理人ID
	 * @param moduleId
	 *            模块ID
	 * @return
	 */
	public List listCommissionInstancesByUserId(String userId, String moduleId) ;
	/**
	 *  2009-01-16
	 * 根据被代理人ID查询代理实例信息
	 * 
	 * @param userId
	 *            被代理人ID
	 * @return
	 */
	public List listCommissionInstancesByUserId(String userId);
	
	
	
}
