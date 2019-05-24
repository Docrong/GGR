package com.boco.eoms.workbench.commission.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionInstance;

/**
 * <p>
 * Title:代理实例管理接口类
 * </p>
 * <p>
 * Description:代理实例管理接口类
 * </p>
 * <p>
 * Date:2008-7-24 下午03:27:58
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface ITawWorkbenchCommissionInstanceMgr {

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
	 * 根据当前用户和模块号取得让我代理工作的user对象列表
	 * 
	 * @param trustorId
	 *            代理人Id
	 * @param moduleId
	 *            代理模块Id
	 * @return List<TawSystemUser>
	 */
	public List listUsersByModuleId(String trustorId, String moduleId);

	/**
	 * 根据当前用户取得属于某子角色的让我代理工作的人员列表
	 * 
	 * @param trustorId
	 *            代理人Id
	 * @param subRoleId
	 *            子角色Id
	 * @return List<TawSystemUser>
	 */
	public List listUsersBySubRoleId(String trustorId, String subRoleId);
	/**
	 *  2009-01-16
	  * 根据当前用户和模块号取我委托的代理user对象列表
	  * 
	  * @param userId
	  *            当前用户Id
	  * @param moduleId
	  *            代理模块Id
	  * @return List<TawSystemUser>
	  */
	 public List listTrustorsByModuleId(String userId, String moduleId) ;
	 /**
	  *  2009-01-16
	  * 根据当前用户及属于的子角色，获取我委托的代理人员列表
	  * 
	  * @param userId
	  *            当前用户Id
	  * @param subRoleId
	  *            子角色Id
	  * @return List<TawSystemUser>
	  */
	 public List listTrustorsBySubRoleId(String userId, String subRoleId);
	
}
