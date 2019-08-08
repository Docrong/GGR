package com.boco.eoms.commons.system.user.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.IDeptMgr;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.IUserMgr;

/**
 * <p>
 * Title:用户管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 11, 2008 4:59:46 PM
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class UserMgrImpl implements IUserMgr {

    /**
     * 角色mgr
     */
    private ITawSystemUserManager tawSystemUserManager;

    /**
     * 用户dao
     */
    private TawSystemUserDao tawSystemUserDao;

    /**
     * 部门mgr
     */
    private IDeptMgr deptMgr;

    /**
     * 用户角色关联关系dao
     */
    private TawSystemUserRefRoleDao tawSystemUserRefRoleDao;

    /**
     * @param deptMgr the deptMgr to set
     */
    public void setDeptMgr(IDeptMgr deptMgr) {
        this.deptMgr = deptMgr;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#getLeaderOfGroup(java.lang.String)
     */
    public TawSystemUser getLeaderOfGroup(String groupId) {
        List list = this.tawSystemUserDao.getLeaderOfGroup(groupId,
                RoleConstants.groupType_leader);
        if (list != null) {
            return (TawSystemUser) list.iterator().next();
        }
        return new TawSystemUser();
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#getUser(java.lang.String,
     * java.lang.String)
     */
    public TawSystemUser getUser(String userId, String passwd) {
        return this.tawSystemUserDao.getUser(userId, passwd);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#getUser(java.lang.String)
     */
    public TawSystemUser getUser(String userId) {
        return this.tawSystemUserManager.getUserByuserid(userId);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#getUsers(int,
     * int, java.lang.String, java.lang.String)
     */
    public Map mapUser(Integer curPage, Integer pageSize, String condition) {
        return this.tawSystemUserDao.getTawSystemUsers(curPage, pageSize,
                condition);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#isExistUser(java.lang.String)
     */
    public boolean isExistUser(String userId) {
        TawSystemUser user = this.getUser(userId);
        if (user == null || null == user.getId() || "".equals(user.getId())) {
            return false;
        }
        return true;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#isSubroleHasUser(java.lang.String,
     * java.lang.String)
     */
    public boolean isSubroleHasUser(String userId, String subRoleId) {
        return this.tawSystemUserRefRoleDao.isExist(subRoleId, userId);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#getLeaderOfDept(java.lang.String)
     */
    public TawSystemUser getLeaderOfDept(String deptId) {
        TawSystemDept dept = this.deptMgr.getDept(deptId);
        if (dept == null || dept.getDeptmanager() == null
                || "".equals(dept.getDeptmanager())) {
            return new TawSystemUser();
        }
        return this.getUser(dept.getDeptmanager());
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUser(java.lang.String)
     */
    public List listUser(String condition) {
        return this.tawSystemUserDao.getTawSystemUsers(condition);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfArea(java.lang.String)
     */
    public List listUserOfArea(String areaId) {
        return this.tawSystemUserDao.getTawSystemUsersOfArea(areaId);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfDept(java.lang.String[])
     */
    public List listUserOfDept(String[] deptIds) {
        return this.tawSystemUserDao.getTawSystemUsersOfDept(deptIds);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfRoom(java.lang.String[])
     */
    public List listUserOfRoom(String[] roomIds) {
        return this.tawSystemUserDao.getTawSystemUsersOfRoom(roomIds);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfsubrole(java.lang.String[])
     */
    public List listUserOfsubrole(String[] subRoleIds) {
        return this.tawSystemUserDao.getTawSystemUsersOfSubrole(subRoleIds);
    }

    /**
     * @param tawSystemUserDao the tawSystemUserDao to set
     */
    public void setTawSystemUserDao(TawSystemUserDao tawSystemUserDao) {
        this.tawSystemUserDao = tawSystemUserDao;
    }

    /**
     * @param tawSystemUserManager the tawSystemUserManager to set
     */
    public void setTawSystemUserManager(
            ITawSystemUserManager tawSystemUserManager) {
        this.tawSystemUserManager = tawSystemUserManager;
    }

    /**
     * @param tawSystemUserRefRoleDao the tawSystemUserRefRoleDao to set
     */
    public void setTawSystemUserRefRoleDao(
            TawSystemUserRefRoleDao tawSystemUserRefRoleDao) {
        this.tawSystemUserRefRoleDao = tawSystemUserRefRoleDao;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#isDutyMaster(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public boolean isDutyMaster(String userId, String roomId, String workSerilId) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#isRoomMgr(java.lang.String,
     * java.lang.String)
     */
    public boolean isRoomMgr(String roomId, String userId) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listApplyUser(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public List listApplyUser(String userId, String moduleId, String type) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#createUser(com.boco.eoms.commons.system.user.model.TawSystemUser)
     */
    public void createUser(TawSystemUser user) {
        // TODO Auto-generated method stub

    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#listProxyUser(java.lang.String,
     * java.lang.String)
     */
    public List listProxyUser(String userId, String moduleId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#modifyUser(com.boco.eoms.commons.system.user.model.TawSystemUser)
     */
    public void modifyUser(TawSystemUser user) {
        // TODO Auto-generated method stub

    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#removeUser(com.boco.eoms.commons.system.user.model.TawSystemUser)
     */
    public void removeUser(TawSystemUser user) {
        // TODO Auto-generated method stub

    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#removeUsers(java.util.List)
     */
    public void removeUsers(List users) {
        // TODO Auto-generated method stub

    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#removeUsers(java.lang.String[])
     */
    public void removeUsers(String[] userIds) {
        // TODO Auto-generated method stub

    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#removeUsersOfDept(java.lang.String[])
     */
    public void removeUsersOfDept(String[] deptIds) {
        // TODO Auto-generated method stub

    }

    /**
     * @see com.boco.eoms.commons.system.user.service.IUserMgr#removeUsersOfSubrole(java.lang.String[])
     */
    public void removeUsersOfSubrole(String[] subRoleIds) {
        // TODO Auto-generated method stub

    }

    public List listByNameQuery(String q) {
        return tawSystemUserDao.listByNameQuery(q);
    }
}
