package com.boco.eoms.commons.system.dept.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.system.dept.model.TawSystemDept;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 31, 2008 2:47:30 PM
 * </p>
 * 
 * @author 曲静波
 * @version 0.1
 * 
 */
public interface IDeptMgr {
	/**
	 * 创建部门
	 * 
	 * @param dept
	 *            部门
	 * @deprecated 不可用
	 */
	public void createDept(TawSystemDept dept);

	/**
	 * 删除部门
	 * 
	 * @param dept
	 *            部门
	 * @deprecated 不可用
	 */
	public void removeDept(TawSystemDept dept);

	/**
	 * 删除（某个或多个）部门
	 * 
	 * @param deptIds
	 *            部门id数组
	 * @deprecated 不可用
	 */
	public void removeDepts(String[] deptIds);

	/**
	 * 删除（某个或多个）部门
	 * 
	 * @param depts
	 *            部门list
	 * @deprecated 不可用
	 */
	public void removeDepts(List depts);

	/**
	 * 将某个（多个）用户赋予某部门下
	 * 
	 * @param userIds
	 *            用户id数组
	 * @param deptId
	 *            部门id
	 * @deprecated 不可用
	 */
	public void users2Dept(String[] userIds, String deptId);

	/**
	 * 移动部门（将源部门移到目的部门下）
	 * 
	 * @param originDeptId
	 *            源部门
	 * @param targetDeptId
	 *            目的部门
	 * @deprecated 不可用
	 */
	public void moveDept(String originDeptId, String targetDeptId);

	/**
	 * 修改部门
	 * 
	 * @param dept
	 *            部门
	 * @deprecated 不可用
	 */
	public void modifyDept(TawSystemDept dept);

	/**
	 * 查询所有父部门
	 * 
	 * @param deptId
	 *            部门id
	 * 
	 * @return deptId的父部门列表
	 * @since 0.1
	 */
	public List listParentDept(String deptId);

	/**
	 * 查询某部门下的部门
	 * 
	 * @param deptId
	 *            部门id
	 * @return 某部门下的所有部门
	 * @since 0.1
	 */
	public List listSubDept(String deptId);

	/**
	 * 取某部门下的所有部门
	 * 
	 * @param deptId
	 *            部门id
	 * @return 某部门下的所有部门
	 * @since 0.1
	 */
	public List listAllSubDept(String deptId);

	/**
	 * 查询根部门(讨论）
	 * 
	 * @return 根部门列表
	 * @since 0.1
	 */
	public List getRootDept();

	/**
	 * 分页取部门列表
	 * 
	 * @param curPage
	 *            要取的页码
	 * @param pageSize
	 *            每页条数
	 * @param condition
	 *            查询条件
	 * 
	 * @return total=总条数,result=当前页的用户列表(map)
	 * @since 0.1
	 */
	public Map mapDept(final Integer curPage, final Integer pageSize,
			final String condition);

	/**
	 * 查询某地域下的所有部门
	 * 
	 * @param areaId
	 *            地域id
	 * @return 某地域下的所有部门
	 * @since 0.1
	 */
	public List listDeptOfArea(String areaId);

	/**
	 * 取某部门
	 * 
	 * @param deptId
	 *            部门id
	 * @return 某（deptId）部门
	 * @since 0.1
	 */
	public TawSystemDept getDept(String deptId);

	/**
	 * 取某部门的父部门
	 * 
	 * @param deptId
	 *            部门id
	 * @return 某部门的父部门
	 * @since 0.1
	 */
	public TawSystemDept getParentDept(String deptId);

}
