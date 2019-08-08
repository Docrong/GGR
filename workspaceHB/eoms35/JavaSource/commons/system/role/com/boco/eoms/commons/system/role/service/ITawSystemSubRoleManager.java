package com.boco.eoms.commons.system.role.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

public interface ITawSystemSubRoleManager extends Manager {
    /**
     * Retrieves all of the tawSystemSubRoles
     */
    public List getTawSystemSubRoles(final long roleId);

    /**
     * Retrieves all of the tawSystemSubRoles from where string
     */
    public List getTawSystemSubRoles(final String whereStr);

    /**
     * Gets tawSystemSubRole's information based on id.
     *
     * @param id the tawSystemSubRole's id
     * @return tawSystemSubRole populated tawSystemSubRole object
     */

    public TawSystemSubRole getTawSystemSubRole(final String id);

    /**
     * Saves a tawSystemSubRole's information
     *
     * @param tawSystemSubRole the object to be saved
     * @param userMap
     */
    public void saveTawSystemSubRole(TawSystemSubRole tawSystemSubRole, Map userMap);

    /**
     * Saves a tawSystemSubRole's information
     *
     * @param tawSystemSubRole the object to be saved
     */
    public void saveTawSystemSubRole(TawSystemSubRole tawSystemSubRole);

    /**
     * Removes a tawSystemSubRole from the database by id
     *
     * @param id the tawSystemSubRole's id
     */
    public void removeTawSystemSubRole(final String id);

    public Map getTawSystemSubRoles(final Integer curPage, final Integer pageSize);

    public Map getTawSystemSubRoles(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据开始查询的记录序数start、每页最多记录数limit和查询条件whereStr查询子角色（分页）。
     * 返回Map对象：
     * {List} result 查询子角色结果集的当前分页的list
     * {Integer} total 子角色结果集的总数
     *
     * @param start    开始查询序数
     * @param limit    每页记录数
     * @param whereStr 条件sql字符串
     * @return Map
     */
    public Map mapTawSystemSubRoles(final Integer start, final Integer limit, final String whereStr);

    public void createSubRolesByDept(final long roleId, final String deptId);

    /**
     * 删除角色大类的所有子角色
     *
     * @param roleId
     */
    public void deleteSubRolesByRoleId(final long roleId);

    /**
     * 获取部门下的子角色
     *
     * @param deptId
     * @return
     */
    public List getSubRolesByDeptId(final String deptId);

    /**
     * 批量保存子角色
     *
     * @param subRoleList<TawSystemSubRole>
     * @param isLimit                       是否限制增加相同区分度的子角色，true不允许增加相同区分度的子角色，false允许
     * @throws Exception
     */
    public void saveTawSystemSubRoles(List subRoleList, boolean isLimit) throws Exception;

    /**
     * 删除多个子角色，ID用逗号分隔
     *
     * @param subRoleIDs
     */
    public void deleteSubRoles(String subRoleIDs);

    /**
     * @param roleId     角色类别ID
     * @param filterHash key为TawSystemSubRole表中区分度对应的业务名称
     * @return List<TawSystemSubRole>
     * @throws Exception
     */
    public List getSubRoles(String roleId, Hashtable filterHash) throws Exception;

    /**
     * 根据角色ID和USERID获取子角色列表
     *
     * @param userId
     * @param roleId
     * @return <TawSystemSubRole>
     */
    public List getSubRoles(String userId, String roleId);

    /**
     * 部门树，根据部门ID和流程ID获取子角色
     *
     * @param deptId   部门ID
     * @param systemId 流程ID
     * @param roleId   大角色ID
     * @return
     */
    public List getSubRolesByDeptId(final String deptId, final String systemId, String roleId);

    /**
     * 设置虚拟组（或者角色）的组长
     *
     * @param groupId
     * @param userId
     */
    public void saveLeader(String groupId, String userId) throws Exception;

    /**
     * 根据roleId和子角色名称，查找对应的子角色
     *
     * @param roleId      角色Id
     * @param subRoleName 子角色名称
     * @return
     * @throws Exception
     */
    public boolean getSubRoleByRole(String roleId, String subRoleId) throws Exception;

    /**
     * 批量保存子角色（维护班组roleId的保存方法，会存入county字段的值）
     * add by weichao 20130625
     *
     * @param tawSystemSubRole
     * @param isLimit          是否限制增加相同区分度的子角色，true不允许增加相同区分度的子角色，false允许
     */
    public void saveTawSystemSubRoles(List subRoleList, boolean b, String areaid) throws Exception;

    public List getCountyByRoleId(String areaId, int roleId);

    public List listCountySubRole(String areaId, int roleId);
}

