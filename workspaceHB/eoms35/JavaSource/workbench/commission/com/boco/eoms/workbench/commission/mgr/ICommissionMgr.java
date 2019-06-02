package com.boco.eoms.workbench.commission.mgr;

import java.util.List;

/**
 * <p>
 * Title:代理管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jan 13, 2009 5:29:57 PM
 * </p>
 * 
 * @author wangbeiying
 * @version 0.1
 * 
 */
public interface ICommissionMgr {
	
	/**
	  * 根据当前用户和模块号取被代理user对象列表
	  * 
	  * @param trustorId
	  *            当前用户Id
	  * @param moduleId
	  *            代理模块Id
	  * @return List<TawSystemUser>
	  */
	 public List listUsersByModuleId(String trustorId, String moduleId);

	 /**
	  * 根据当前用户及属于的子角色，获取被代理人员列表
	  * 
	  * @param trustorId
	  *            当前用户Id
	  * @param subRoleId
	  *            子角色Id
	  * @return List<TawSystemUser>
	  */
	 public List listUsersBySubRoleId(String trustorId, String subRoleId);
	/**
	  * 根据当前用户和模块号取代理user对象列表
	  * 
	  * @param userId
	  *            当前用户Id
	  * @param moduleId
	  *            代理模块Id
	  * @return List<TawSystemUser>
	  */
	 public List listTrustorsByModuleId(String userId, String moduleId) ;
	 /**
	  * 根据当前用户及属于的子角色，获取代理人员列表
	  * 
	  * @param userId
	  *            当前用户Id
	  * @param subRoleId
	  *            子角色Id
	  * @return List<TawSystemUser>
	  */
	 public List listTrustorsBySubRoleId(String userId, String subRoleId);
}
