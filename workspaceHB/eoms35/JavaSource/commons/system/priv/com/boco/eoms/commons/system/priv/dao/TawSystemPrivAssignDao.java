package com.boco.eoms.commons.system.priv.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;

public interface TawSystemPrivAssignDao extends Dao {

	/**
	 * Retrieves all of the tawSystemPrivAssigns
	 */
	public List getTawSystemPrivAssigns(TawSystemPrivAssign tawSystemPrivAssign);

	/**
	 * Gets tawSystemPrivAssign's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemPrivAssign's id
	 * @return tawSystemPrivAssign populated tawSystemPrivAssign object
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(final String id);

	/**
	 * Saves a tawSystemPrivAssign's information
	 * 
	 * @param tawSystemPrivAssign
	 *            the object to be saved
	 */
	public void saveTawSystemPrivAssign(TawSystemPrivAssign tawSystemPrivAssign);

	/**
	 * Removes a tawSystemPrivAssign from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivAssign's id
	 */
	public void removeTawSystemPrivAssign(final String id);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 查询某用户分配的权限信息
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserCreatePrivs(final String operuserid);

	/**
	 * 查询OBJECT 对应的权限
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectPriv(final String objectid);

	/**
	 * 查询某权限方案被分配的情况
	 * 
	 * @return
	 */
	public List getPrivassigninfos(final String privid);

	/**
	 * 判断某GROUP是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean hasAssign(String objectid);

	/**
	 * 判断某菜单方案是否被分配过
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isMenuHasAssign(String menuid);

	/**
	 * 获取权限菜单方案赋权对象列表
	 * 
	 * @param url
	 *            唯一标识
	 * 
	 * @return 拥有url的权限列表
	 */
	public List getAssignsByUrl(String url);

	/**
	 * 根据组织id(用户，部门，角色）、菜单方案取赋权信息
	 * 
	 * @param orgId
	 *            组织id
	 * @param type
	 *            组织类型
	 * @param menuId
	 *            菜单方案id
	 * @return 根据组织id(用户，部门，角色）、菜单方案返回赋权信息
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(String orgId,
			String type, String menuId);

	/**
	 * 获取合并后的菜单方案
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @return 获取合并后的菜单单方案
	 */
	public List listMenu(String userId, String deptId, List roleIds);

	/**
	 * 通过url取拥有赋权
	 * 
	 * @param url
	 *            url权限
	 * @return 取拥有赋权信息
	 */
	public List listAssign(String url);

}
