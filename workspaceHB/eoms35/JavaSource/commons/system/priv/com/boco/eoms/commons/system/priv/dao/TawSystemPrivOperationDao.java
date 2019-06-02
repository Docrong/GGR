package com.boco.eoms.commons.system.priv.dao;
  
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;

public interface TawSystemPrivOperationDao extends Dao {

	/**
	 * Retrieves all of the tawSystemPrivOperations
	 */
	public List getTawSystemPrivOperations(
			TawSystemPrivOperation tawSystemPrivOperation);

	/**
	 * Gets tawSystemPrivOperation's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemPrivOperation's id
	 * @return tawSystemPrivOperation populated tawSystemPrivOperation object
	 */
	public TawSystemPrivOperation getTawSystemPrivOperation(final String id);

	/**
	 * Saves a tawSystemPrivOperation's information
	 * 
	 * @param tawSystemPrivOperation
	 *            the object to be saved
	 */
	public void saveTawSystemPrivOperation(
			TawSystemPrivOperation tawSystemPrivOperation);

	/**
	 * Removes a tawSystemPrivOperation from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivOperation's id
	 */
	public void removeTawSystemPrivOperation(final String id);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getTawSystemPrivOperations(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivOperations(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 得到某一模块下的所有对象（包括子模块与功能项,包括该模块）
	 */
	public List getAllSubObjects(String code);

	/**
	 * 得到某一模块下的所有子模块 （包括子模块的子模块，,包括该模块）
	 */
	public List getAllSubModules(String code);

	/**
	 * 得到某一模块下的所有功能项（包括子模块的功能项）
	 */

	public List getAllSubOperations(String code);

	/**
	 * 得到某一模块下的对象（仅仅是有关该模块的）
	 */
	public List getObjects(String code);

	/**
	 * 得到某一模块下的功能项（仅仅是有关该模块的）
	 */
	public List getOperations(String code);

	/**
	 * 得到某一模块下的子模块(仅仅是有关该模块的)
	 */
	public List getModules(String code, String deleted);

	/**
	 * 得到某一模块下最大的CODE值
	 * 
	 * @param fatherId
	 * @return
	 */
	public String getMaxCodeValue(String fatherId);

	public String getMaxCodeValue();

	/**
	 * 得到某一模块的直接父模块
	 * 
	 * @param childObjectCode
	 * @return
	 */
	public TawSystemPrivOperation getFatherModule(String childObjectCode);

	/**
	 * 得到某一模块的所有父模块集合
	 */
	public List getAllFatherModules(String code);

	public TawSystemPrivOperation getTawSystemPrivOpt(final String code);

	public String getParentModuleName(String _strParentId);

	public List getDirectSubModules(String _strParentId);

	public void removeTawSystemPrivOperationByCode(String _strCode);
	
	//2009.03.24加，删除节点，顺带删除所有子节点
    public void removeAllNodeByCode(final String _strCode);

	/**
	 * 根据CODE取得对应的NAME
	 * 
	 * @param code
	 * @return
	 */
	public TawSystemPrivOperation getPrivOperationbyCode(String code);

	/**
	 * 根据parentCode取菜单
	 * 
	 * @param parentCode
	 *            父菜单id
	 * @return 菜单列表
	 */
	public List getTawSystemPrivOerationsByParentCode(String parentCode);

	/**
	 * 获取parentCode下的菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param type
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @param parentCode
	 *            取某节点下的所有菜单
	 * @return 获取合并后的最上层菜单
	 */
	public List listOperation(String userId, String deptId, List roleIds,
			String type, String parentCode);
	
	/**
	 * 获取parentCode下的菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param type
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @param parentCode
	 *            取某节点下的所有菜单
	 * @return 获取合并后的最上层菜单
	 */
	public List listOperationwap(String userId, String deptId, List roleIds,
			String type, String parentCode);
	
	/**
	 * 获取parentCode下的菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param type
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @param parentCode
	 *            取某节点下的所有菜单
	 * @return 获取合并后的最上层菜单
	 * 
	 * add by gongyufeng 为了wap登陆准备数据
	 */
	public List listOperationWap(String userId, String deptId, List roleIds,
			String type, String parentCode,String loginNature);
	
	/**
	 * 获取operationId的下层菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @prama operationId 父菜单id
	 * @return 获取合并后的最上层菜单
	 * @since 0.1
	 */
	public List listOpertion(String userId, String deptId, List roleIds,
			String type, String operationId);

	/**
	 * 取某用户操作权限
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色列表
	 * @param url
	 *            权限url
	 * @param total
	 *            取出的条数，若为空则取全部
	 * @return 取某用户的操作权限
	 */
	public List listOperation(final String userId, final String deptId,
			final List roleIds, final String url, final Integer total);

	/**
	 * 取某用户（包括其所属角色、部门）某菜单方案下所有可操作的菜单
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色列表
	 * @param menuId
	 *            菜单id *
	 * @param type
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @param parentCode
	 *            父节点
	 * @return 某用户（包括其所属角色、部门）某菜单方案下所有可操作的菜单列表
	 */
	public List listOperation(String userId, String deptId, List roleIds,
			String menuId, String type, String parentCode);
	
	/**
	 * 获取operationId的所有下层菜单。如：流程管理->任务工单->派发工单，取流程管理、任务工单、派发工单
	 * 
	 * @param userId
	 *            用户id
	 * @prama operationId 父菜单id
	 * @param
	 *        查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @return 所有菜单项
	 * @since 0.1
	 */
	public List listOperationAll(String userId, String deptId, List roleIds,
			String type, String operationId); 

	/**
	 * 得到某一模块下的所有未删除未隐藏的对象（包括子模块与功能项,包括该模块）
	 */
	public List getAllEnableSubObjects(String code);

}


