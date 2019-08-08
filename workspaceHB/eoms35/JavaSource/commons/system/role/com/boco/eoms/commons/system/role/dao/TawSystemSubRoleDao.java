package com.boco.eoms.commons.system.role.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

public interface TawSystemSubRoleDao extends Dao {

    /**
     * Retrieves all of the tawSystemSubRoles
     */
    public List getTawSystemSubRoles(final long roleId);

    /**
     * Retrieves all of the tawSystemSubRoles from where string
     */
    public List getTawSystemSubRoles(final String whereStr);

    /**
     * Gets tawSystemSubRole's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the tawSystemSubRole's id
     * @return tawSystemSubRole populated tawSystemSubRole object
     */
    public TawSystemSubRole getTawSystemSubRole(final String id);

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

    /**
     * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
     */
    public Map getTawSystemSubRoles(final Integer curPage,
                                    final Integer pageSize);

    public Map getTawSystemSubRoles(final Integer curPage,
                                    final Integer pageSize, final String whereStr);

    public Map mapTawSystemSubRoles(final Integer start,
                                    final Integer limit, final String whereStr);

    public void createSubRolesByDept(final TawSystemRole role,
                                     final TawSystemDept tawSystemDept);

    public List getSubRolesByDeptId(final String deptId);

    /**
     * 部门树，根据部门ID和流程ID获取子角色
     *
     * @param deptId   部门ID
     * @param systemId 流程ID
     * @return
     */
    public List getSubRolesByDeptId(final String deptId, final String systemId);

    public TawSystemSubRole getTawSystemSubRoleByLogo(final String logo);

    public List getSubRoles(String roleId, Hashtable filterHash)
            throws Exception;

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
    public List getSubRolesByDeptId(final String deptId, final String systemId,
                                    final String roleId);

    /**
     * 获取某角色下所有子角色
     *
     * @param roleId
     * @return
     */
    public List getSubRolesByRoleId(final long roleId);

    public List getSubRolesByIds(String subRoleIds);

    /**
     * 删除大角色下所有子角色
     *
     * @param roleId
     */
    public void removeTawSystemSubRole(final long roleId);

    /**
     * 通过版本删除
     *
     * @param version 版本
     */
    public void removeTawSystemSubRoleByVersion(String version);

    /**
     * 取roleId下的子角色（除子角色id(subroleId)的其他子角色)
     *
     * @param subroleId
     * @return
     */
    public List getOtherSubroles(long roleId, String subroleId);

    /**
     * 根据条件取某角色下的所有有子角色
     *
     * @param roleId    角色id
     * @param condition 条件
     * @return
     */
    public List getSubrolesByCon(String roleId, String condition);

    public List getSubRolesByAreaIdAndRoleId(String areaId, String roleId);

    /**
     * 取areaId,roleId，但type1为空的
     *
     * @param areaId 地域id
     * @param roleId 角色id
     * @return 取areaId, roleId
     */
    public List listSubRole(String areaId, int roleId);

    /**
     * 取areaId,roleId，但type1 not为空的
     *
     * @param areaId 地域id
     * @param roleId 角色id
     * @return 取areaId, roleId，但type1为空的subrole列表
     */
    public List listSubRoleWithType1NotNull(String areaId, String roleId);

    /**
     * 取areaId,roleId，但type1为空的
     *
     * @param areaId 地域id
     * @param roleId 角色id
     * @return 取areaId, roleId，但type1为空的subrole列表
     */
    public List listSubRoleWithType1Null(String areaId, String roleId);

    /**
     * 根据roleId和subRoleName查找subRole对象
     *
     * @param roleId
     * @param subRoleName
     * @return
     */
    public boolean getSubRoleByRole(String roleId, String subRoleId);

    public List getCountyByRoleId(String areaId, int roleId);

    public List listCountySubRole(String areaId, int roleId);
}
