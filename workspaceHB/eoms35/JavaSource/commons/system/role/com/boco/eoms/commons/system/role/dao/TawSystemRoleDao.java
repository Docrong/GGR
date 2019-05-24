package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;

public interface TawSystemRoleDao extends Dao {

	/**
	 * Retrieves all of the tawSystemRoles
	 */
	public List getTawSystemRoles();

	/**
	 * Gets tawSystemRole's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param roleId
	 *            the tawSystemRole's roleId
	 * @return tawSystemRole populated tawSystemRole object
	 */
	public TawSystemRole getTawSystemRole(final long roleId);

	public TawSystemRole getTawSystemRole(final long roleId, final int deleted);

	/**
	 * Saves a tawSystemRole's information
	 * 
	 * @param tawSystemRole
	 *            the object to be saved
	 */
	public void saveTawSystemRole(TawSystemRole tawSystemRole);

	/**
	 * Removes a tawSystemRole from the database by roleId
	 * 
	 * @param roleId
	 *            the tawSystemRole's roleId
	 */
	public void removeTawSystemRole(final long roleId)
			throws TawSystemUserException;

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getTawSystemRoles(final Integer curPage, final Integer pageSize);

	public Map getTawSystemRoles(final Integer curPage, final Integer pageSize,
			final String whereStr);

	public List getRolesByStructureFlag(String structureFlag, int deleted);

	public List getChildRoleByRoleIdPirv(final long roleId);

	public List getChildRoleByRoleId(final long roleId);

	public String getRoleNameById(final long roleId);

	public void setLeaf(final long roleId, final Integer leaf);

	public String getNewStructureFlag(long parentRoleId) throws Exception;

	public List getRolesByPostId(long postId);

	public List getRolesByWorkflowFlag(final int workflowFlag);

	/**
	 * 获取大角色对应的所有部门
	 * 
	 * @param roleId
	 * @return <TawSystemDept>
	 */
	public List getDeptByRoleId(final long roleId);

	/**
	 * 获取大角色对应的所有地域
	 * 
	 * @param roleId
	 * @return <TawSystemDept>
	 */
	public List getAreaByRoleId(final long roleId);

	/**
	 * 获取角色对象
	 * 
	 * @param workflowFlag
	 *            流程编号
	 * @param roleName
	 *            角色名称
	 * @return
	 */
	public TawSystemRole getTawSystemRole(Integer workflowFlag, String roleName);

	/**
	 * 根据roleId获取某个角色下的系统角色
	 * 
	 * @param roleId
	 * @return <List>
	 */
	public List getSysRolesByRoleId(final long roleId);

	/**
	 * 根据workflowFlag获取某个流程下的流程角色
	 * 
	 * @param workflowFlag
	 * @return <List>
	 * @author mios
	 */
	public List getFlwRolesByWorkflowFlag(final int workflowFlag);

	/**
	 * 
	 * @param areaId
	 * @return
	 */
	public List getRolesOfArea(String areaId);

	

}
