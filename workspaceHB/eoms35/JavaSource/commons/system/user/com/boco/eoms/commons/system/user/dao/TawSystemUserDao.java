package com.boco.eoms.commons.system.user.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

public interface TawSystemUserDao extends Dao {

    /**
     * Retrieves all of the tawSystemUsers
     */
    public List getTawSystemUsers(TawSystemUser tawSystemUser);

    /**
     * Gets tawSystemUser's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the tawSystemUser's id
     * @return tawSystemUser populated tawSystemUser object
     */
    public TawSystemUser getTawSystemUser(final String id);

    /**
     * Saves a tawSystemUser's information
     *
     * @param tawSystemUser the object to be saved
     */
    public void saveTawSystemUser(TawSystemUser tawSystemUser);

    /**
     * Removes a tawSystemUser from the database by id
     *
     * @param id the tawSystemUser's id
     */
    public void removeTawSystemUser(final String id);

    /**
     * 回收站用户恢复
     *
     * @param id
     */
    public void resumeTawSystemUser(final String id);

    /**
     * 通过用户ID获得用户手机号码
     *
     * @param userId 用户ID
     * @return 用户手机号码
     */
    public String getMobilesByUserId(String userId);

    /**
     * 得到部门的所有USER
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptids(String deptid);


    /**
     * 得到部门的所有USER,不包括用户自己
     *
     * @param deptid
     * @param userid
     * @return
     */
    public List getUserBydeptidsNoSelf(String deptid, String userid);


    /**
     * 2008-12-2 wangsixuan
     * 得到部门的所有USER,只取是代维的USER
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptidsPartner(String deptid);

    /**
     * 根据userid得到用户的信息
     *
     * @param userid
     * @return
     */
    public TawSystemUser getUserByuserid(String userid);

    /**
     * 根据userid得到所有用户的信息（包括已删除，查询不到返回为空）
     *
     * @param userid
     * @return
     */
    public TawSystemUser getTawSystemUserByuserid(String userid);


    /**
     * 根据名称得到用户列表
     *
     * @param userName
     * @return List
     */
    public List getUsersByName(String userName);

    /**
     * 根据名称，部门得到用户列表
     *
     * @param userName
     * @return List
     */
    public List getUsersByName(String userName, String deptName);

    public List getUsersDelByName(String userName);

    /**
     * 用户删除回收站
     *
     * @return List
     */
    public List getUsersByDeleted();

    /**
     * 根据名称得到用户列表
     *
     * @param 用户名，地市、部门、电话、邮箱
     * @return List
     */
    public List getUsersByInfo(String userName, String cptroomname, String deptname, String mobile, String email);

    /**
     * 获取系统管理员的用户列表
     *
     * @param userdegree
     * @return
     */
    public List getUserbyUserdegrees(String userdegree);

    /**
     * 得到某机房的所有用户列表
     *
     * @param cptid
     * @return
     */
    public List getUserbyCptids(String cptid);

    /**
     * 查询某用户添加的所有用户
     *
     * @param operuserid
     * @return
     */
    public List getUserbyOperuserids(String operuserid);

    /**
     * 根据性别查询用户
     *
     * @param sexid
     * @return
     */
    public List getUserbysexs(String sexid);

    /**
     * 取得某部门下的所有管理员的userid
     *
     * @param deptid
     * @param degreeid
     * @return
     */
    public List getUserbyDeptidandDegids(String deptid, String degreeid);

    /**
     * 取得某机房的所有管理员
     *
     * @param cptid
     * @param degid
     * @return
     */
    public List getUserBycptidAndDegid(String cptid, String degid);

    /**
     * 删除某部门的所有用户
     *
     * @param deptid
     */
    public void removeUserbydeptid(String deptid);

    /**
     * 删除某机房的所有用户
     *
     * @param cptid
     */
    public void removeUserbycptid(String cptid);

    /**
     * 根据部门id 和子部门id 查询用户
     *
     * @param deptid
     * @param sondeptid
     * @return
     */
    public List getUserbydeptidanson(String deptids);

    /**
     * 查询某部门所有的EMAIL
     *
     * @return
     */
    public List getAllEmalibyDeptid(String deptid);

    /**
     * 查询某部门所有的手机号
     *
     * @return
     */
    public List getAllMobileBydeptid(String deptid);

    /**
     * 查询某机房的所有EMAIL
     *
     * @return
     */
    public List getAllEmailbyCptid(String cptid);

    /**
     * 查询某机房的所有手机号
     *
     * @return
     */
    public List getAllMobilebyCptid(String cptid);

    /**
     * 得到某些用户的EMAIL in查询
     *
     * @param userids
     * @return
     */
    public List getAllEmailbyuserids(String userids);

    /**
     * 得到某些用户的手机号 in查询
     *
     * @param userids
     * @return
     */
    public List getAllMobileByuerids(String userids);

    /**
     * 查询未被删除的用户
     *
     * @return
     */
    public List getNoDelUser();

    /**
     * 得到正在休假的用户
     *
     * @param userid
     * @return
     */
    public List getRestByUserid(String userid);

    /**
     * 得到未休假的用户
     *
     * @param userid
     * @return
     */
    public List getNoRestByUserid(String userid);

    /**
     * 判断某用户是否正在休假
     *
     * @param userid
     * @return
     */
    public boolean isRestbyUserid(String userid);

    /**
     * 判断某用户是否是全职
     *
     * @param userid
     * @return
     */
    public boolean isFullemploybyUserid(String userid);

    /**
     * 查询所有全职用户
     *
     * @param userid
     * @return
     */
    public List getFullemploybyUserid(String userid);

    /**
     * 查询所有兼职用户
     *
     * @param userid
     * @return
     */
    public List getPartemployBuUserid(String userid);

    /**
     * 查询某状态的所有用户
     *
     * @param userstatus
     * @return
     */
    public List getUserByUserstatus(String userstatus);

    /*
     * id2name，即用户id转为用户名称 added by leo
     *
     * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException;

    /**
     * 查询部门下所有用户的userid
     *
     * @param deptid
     * @return
     */
    public List getUserIdsBydeptid(String deptid);

    /**
     * 通过用户id和密码查询用户
     *
     * @param userId 用户id
     * @param passwd 密码
     * @return 用户
     */
    public TawSystemUser getUser(String userId, String passwd);

    /**
     * 通过子角色id取用户
     *
     * @param subRoleId 子角色
     * @return 用户列表
     */
    public List getUsersBySubRoleid(String subRoleId);

    /**
     * 取某虚拟组的组长
     *
     * @param groupId   虚拟组id
     * @param groupType RoleConstants.groupType_common=普通人员，RoleConstants.groupType_leader=组长
     * @return 返回虚拟组成员或组长
     */
    public List getLeaderOfGroup(String groupId, String groupType);

    /**
     * 分页取用户
     *
     * @param curPage  要访问的页
     * @param pageSize 每页显示条数
     * @param whereStr 条件
     * @return total=总条数,result=记录
     */
    public Map getTawSystemUsers(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * 取所有用户
     *
     * @param whereStr 查询条件及排序
     * @return 返回用户列表
     */
    public List getTawSystemUsers(String whereStr);

    /**
     * 取某一区域下的用户
     *
     * @param areaId 区域id
     * @return 返回某区域下的人员
     */
    public List getTawSystemUsersOfArea(String areaId);

    /**
     * 取所有部门id里的用户
     *
     * @param deptIds 部门ID数组
     * @return 所有部门ID中的用户
     */
    public List getTawSystemUsersOfDept(String deptIds[]);

    /**
     * 取所有机房id里的用户
     *
     * @param roomIds 机房ID数组
     * @return 所有机房中的用户
     */
    public List getTawSystemUsersOfRoom(String roomIds[]);

    /**
     * 取所给子角色列表的所有用户
     *
     * @param subroleIds 子角色id数组
     * @return 所有子角色中的用户
     */
    public List getTawSystemUsersOfSubrole(String subroleIds[]);

    public List getUserBydeptidAndSubs(String _deptId);

    public List getUserBydeptidAndSubsPartner(String _deptId);

    public List getUserLike(String deptid);

    /**
     * 根据手机号和userid获取user对象
     *
     * @param mobile
     * @param userid
     * @return
     */
    public List getUserByIdMobile(String userid);

    public abstract List listByNameQuery(String s);
}
