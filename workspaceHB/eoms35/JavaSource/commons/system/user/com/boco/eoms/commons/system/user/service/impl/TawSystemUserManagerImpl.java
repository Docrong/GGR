package com.boco.eoms.commons.system.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.dao.jdbc.TawSystemUserJdbc;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.sox.mgr.IUserPasswdHistoryMgr;
import com.boco.eoms.commons.system.user.sox.model.UserPasswdHistory;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;

public class TawSystemUserManagerImpl extends BaseManager implements
        ITawSystemUserManager {
    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#importUser(java.lang.String)
     */
    public boolean importUser(String excelpath) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#saveTawSystemUser(com.boco.eoms.commons.system.user.model.TawSystemUser)
     */
    public void saveTawSystemUser(TawSystemUser tawSystemUser) {
        dao.saveTawSystemUser(tawSystemUser);
    }

    private TawSystemUserDao dao;

    /**
     * 部门manager
     */
    private ITawSystemDeptManager tawSystemDeptManager;

    /**
     * 用户密码修改记录
     */

    private IUserPasswdHistoryMgr userPasswdHistoryMgr;

    private TawSystemPrivAssignDao tawSystemPrivAssignDao;

    private TawSystemPrivMenuCommonDao tawSystemPrivUserAssignJdbc;

    public TawSystemPrivAssignDao getTawSystemPrivAssignDao() {
        return tawSystemPrivAssignDao;
    }

    public void setTawSystemPrivAssignDao(
            TawSystemPrivAssignDao tawSystemPrivAssignDao) {
        this.tawSystemPrivAssignDao = tawSystemPrivAssignDao;
    }

    public TawSystemPrivMenuCommonDao getTawSystemPrivUserAssignJdbc() {
        return tawSystemPrivUserAssignJdbc;
    }

    public void setTawSystemPrivUserAssignJdbc(
            TawSystemPrivMenuCommonDao tawSystemPrivUserAssignJdbc) {
        this.tawSystemPrivUserAssignJdbc = tawSystemPrivUserAssignJdbc;
    }

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSystemUserDao(TawSystemUserDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#getTawSystemUsers(com.boco.eoms.commons.system.user.model.TawSystemUser)
     */
    public List getTawSystemUsers(final TawSystemUser tawSystemUser) {
        return dao.getTawSystemUsers(tawSystemUser);
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#getTawSystemUser(String
     * id)
     */
    public TawSystemUser getTawSystemUser(final String id) {
        return dao.getTawSystemUser(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#saveTawSystemUser(TawSystemUser
     * tawSystemUser)
     */
    public void saveTawSystemUser(TawSystemUser tawSystemUser, String olduserid) {
        // 若修改为帐号未锁定（帐号解锁）则将失败次数置为0
        if (!tawSystemUser.isAccountLocked()) {
            tawSystemUser.setFailCount(new Integer(0));
        }
        dao.saveTawSystemUser(tawSystemUser);
        // 加入修改历史密码记录
        userPasswdHistoryMgr
                .addUserPasswdHistory(new UserPasswdHistory(tawSystemUser
                        .getId(), tawSystemUser.getPassword(), new Date()));
        /*
         * PortalService locator = new PortalServiceLocator();
         * PortalServicePortType portype = new PortalServicePortTypeProxy();
         *
         * try { portype = locator.getPortalServiceHttpPort(); PortalUser
         * portaluser = new PortalUser();
         * portaluser.setDeptid(tawSystemUser.getDeptid());
         * portaluser.setDeptname(tawSystemUser.getDeptname());
         * portaluser.setEmail(tawSystemUser.getEmail());
         * portaluser.setPassword(tawSystemUser.getPassword());
         * portaluser.setRole(tawSystemUser.getPortalrolename());
         * portaluser.setUserId(tawSystemUser.getUserid());
         * portaluser.setUsername(tawSystemUser.getUsername());
         * portype.saveOrUpdatePortalUser(portaluser, olduserid); } catch
         * (Exception e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */
    }

    /**
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#removeTawSystemUser(String
     * id)
     */
    public void removeTawSystemUser(final String id) {
        // TawSystemUser sysuser = dao.getTawSystemUser(id);
        dao.removeTawSystemUser(id);
        /*
         * PortalService locator = new PortalServiceLocator();
         * PortalServicePortType portype = new PortalServicePortTypeProxy();
         *
         * try { portype = locator.getPortalServiceHttpPort();
         * portype.delPortalUser(sysuser.getUserid()); } catch (Exception e) { //
         * TODO Auto-generated catch block e.printStackTrace(); }
         */
    }

    /**
     * 如果被删除用户恢复
     *
     * @param id
     */
    public void resumeTawSystemUser(final String id) {
        dao.resumeTawSystemUser(id);
    }


    /**
     * 如果被删除用户已经有权限则删除用户的同时需要删除用户的权限
     *
     * @param id
     * @param userid
     */
    public void removeTawSystemUser(final String id, final String userid) {

        //tawSystemPrivUserAssignJdbc.removeUserAssignByUserid(userid);    taw_system_priv_userassign表已经作废
        TawSystemPrivAssign privassign = null;
        ArrayList list = (ArrayList) tawSystemPrivAssignDao
                .getObjectPriv(userid);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                privassign = (TawSystemPrivAssign) list.get(i);
                if (privassign != null) {
                    tawSystemPrivAssignDao.removeTawSystemPrivAssign(privassign
                            .getId());
                    BocoLog.info(this, "删除用户:" + userid + " 权限成功");
                }
            }
        }
        dao.removeTawSystemUser(id);
    }

    /**
     * 得到部门的所有USER
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptids(String deptid) {
        List list = new ArrayList();

        list = dao.getUserBydeptids(deptid);

        return list;
    }

    /**
     * 2008-12-2 wangsixuan
     * 得到部门的所有USER,只取是代维的USER
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptidsPartner(String deptid) {
        List list = new ArrayList();

        list = dao.getUserBydeptidsPartner(deptid);

        return list;
    }

    /**
     * 根据userid得到用户的信息
     *
     * @param userid
     * @return
     */
    public TawSystemUser getUserByuserid(String userid) {

        TawSystemUser user = new TawSystemUser();

        user = dao.getUserByuserid(userid);

        return user;
    }

    /**
     * 根据userid得到用户的信息（包括已删除，查询不到返回为空）
     *
     * @param userid
     * @return
     */
    public TawSystemUser getTawSystemUserByuserid(String userid) {

        TawSystemUser user = new TawSystemUser();

        user = dao.getTawSystemUserByuserid(userid);

        return user;
    }

    /**
     * 根据名称得到用户列表
     *
     * @param userName
     * @return List
     */
    public List getUsersByName(String userName) {
        List list = new ArrayList();
        list = dao.getUsersByName(userName);
        return list;
    }

    /**
     * 根据名称，部门得到用户列表
     *
     * @param userName
     * @return List
     */
    public List getUsersByName(String userName, String deptName) {
        List list = new ArrayList();
        list = dao.getUsersByName(userName, deptName);
        return list;
    }

    public List getUsersDelByName(String userName) {
        List list = new ArrayList();
        list = dao.getUsersDelByName(userName);
        return list;
    }

    /**
     * 用户删除回收站
     *
     * @return List
     */
    public List getUsersByDeleted() {
        List list = new ArrayList();
        list = dao.getUsersByDeleted();
        return list;
    }

    /**
     * 根据名称得到用户列表
     * add：wangsixuan 09-1-5
     *
     * @param 用户名，地市、部门、电话、邮箱
     * @return List
     */
    public List getUsersByInfo(String userName, String cptroomname, String deptname, String mobile, String email) {
        List list = new ArrayList();
        list = dao.getUsersByInfo(userName, cptroomname, deptname, mobile, email);
        return list;
    }

    /**
     * 获取系统管理员的用户列表
     *
     * @param userdegree
     * @return
     */
    public List getUserbyUserdegrees(String userdegree) {

        List list = new ArrayList();

        list = dao.getUserbyUserdegrees(userdegree);

        return list;

    }

    /**
     * 得到某机房的所有用户列表
     *
     * @param cptid
     * @return
     */
    public List getUserbyCptids(String cptid) {

        List list = new ArrayList();

        list = dao.getUserbyCptids(cptid);

        return list;

    }

    /**
     * 查询某用户添加的所有用户
     *
     * @param operuserid
     * @return
     */
    public List getUserbyOperuserids(String operuserid) {

        List list = new ArrayList();

        list = dao.getUserbyOperuserids(operuserid);

        return list;

    }

    /**
     * 根据性别查询用户
     *
     * @param sexid
     * @return
     */
    public List getUserbysexs(String sexid) {

        List list = new ArrayList();

        list = dao.getUserbysexs(sexid);

        return list;

    }

    /**
     * 取得某部门下的所有管理员的userid
     *
     * @param deptid
     * @param degreeid
     * @return
     */
    public List getUserbyDeptidandDegids(String deptid, String degreeid) {

        List list = new ArrayList();

        list = dao.getUserbyDeptidandDegids(deptid, degreeid);

        return list;

    }

    /**
     * 取得某机房的所有管理员
     *
     * @param cptid
     * @param degid
     * @return
     */
    public List getUserBycptidAndDegid(String cptid, String degid) {

        List list = new ArrayList();

        list = dao.getUserBycptidAndDegid(cptid, degid);

        return list;

    }

    /**
     * 删除某部门的所有用户
     *
     * @param deptid
     */
    public void removeUserbydeptid(String deptid) {

        dao.removeUserbydeptid(deptid);

    }

    /**
     * 删除某机房的所有用户
     *
     * @param cptid
     */
    public void removeUserbycptid(String cptid) {
        dao.removeUserbycptid(cptid);
    }

    /**
     * 根据部门id 和子部门id 查询用户
     *
     * @param deptid
     * @param sondeptid
     * @return
     */
    public List getUserbydeptidanson(String deptids) {
        List list = new ArrayList();
        list = dao.getUserbydeptidanson(deptids);

        return list;
    }

    /**
     * 查询某部门所有的EMAIL
     *
     * @return
     */
    public List getAllEmalibyDeptid(String deptid) {

        List list = new ArrayList();
        list = dao.getAllEmalibyDeptid(deptid);

        return list;
    }

    /**
     * 查询某部门所有的手机号
     *
     * @return
     */
    public List getAllMobileBydeptid(String deptid) {

        List list = new ArrayList();
        list = dao.getAllMobileBydeptid(deptid);

        return list;

    }

    /**
     * 查询某机房的所有EMAIL
     *
     * @return
     */
    public List getAllEmailbyCptid(String cptid) {

        List list = new ArrayList();
        list = dao.getAllEmailbyCptid(cptid);

        return list;

    }

    /**
     * 查询某机房的所有手机号
     *
     * @return
     */
    public List getAllMobilebyCptid(String cptid) {

        List list = new ArrayList();
        list = dao.getAllMobilebyCptid(cptid);

        return list;
    }

    /**
     * 得到某些用户的EMAIL in查询
     *
     * @param userids
     * @return
     */
    public List getAllEmailbyuserids(String userids) {

        List list = new ArrayList();
        list = dao.getAllEmailbyuserids(userids);

        return list;
    }

    /**
     * 得到某些用户的手机号 in查询
     *
     * @param userids
     * @return
     */
    public List getAllMobileByuerids(String userids) {

        List list = new ArrayList();
        list = dao.getAllMobileByuerids(userids);

        return list;
    }

    /**
     * 查询未被删除的用户
     *
     * @return
     */
    public List getNoDelUser() {

        List list = new ArrayList();
        list = dao.getNoDelUser();

        return list;
    }

    /**
     * 得到正在休假的用户
     *
     * @param userid
     * @return
     */
    public List getRestByUserid(String userid) {
        return dao.getRestByUserid(userid);
    }

    /**
     * 得到未休假的用户
     *
     * @param userid
     * @return
     */
    public List getNoRestByUserid(String userid) {
        return dao.getNoRestByUserid(userid);
    }

    /**
     * 判断某用户是否正在休假
     *
     * @param userid
     * @return
     */
    public boolean isRestbyUserid(String userid) {
        return dao.isRestbyUserid(userid);
    }

    /**
     * 判断某用户是否是全职
     *
     * @param userid
     * @return
     */
    public boolean isFullemploybyUserid(String userid) {
        return dao.isFullemploybyUserid(userid);
    }

    /**
     * 查询所有全职用户
     *
     * @param userid
     * @return
     */
    public List getFullemploybyUserid(String userid) {
        return dao.getFullemploybyUserid(userid);
    }

    /**
     * 查询所有兼职用户
     *
     * @param userid
     * @return
     */
    public List getPartemployBuUserid(String userid) {
        return dao.getPartemployBuUserid(userid);
    }

    /**
     * 查询某状态的所有用户
     *
     * @param userstatus
     * @return List user
     * @author liuxy
     */
    public List getUserByUserstatus(String userstatus) {
        return dao.getUserByUserstatus(userstatus);
    }

    /**
     * 设置用户状态
     *
     * @param userid
     * @param userstatus
     * @return void
     * @author liuxy
     */
    public void setUserstatus(TawSystemUser tawSystemUser, String userstatus) {
        tawSystemUser.setUserstatus(userstatus);
        dao.saveTawSystemUser(tawSystemUser);
    }

    /*
     * id2name，即用户id转为用户名称 added by leo
     *
     * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(final String id) throws DictDAOException {
        return dao.id2Name(id);
    }

    /**
     * 删除用户的时候需要刷新缓存
     *
     * @param sysuser
     * @return
     */
    public void removeTawSystemUserandcatch(final String id,
                                            TawSystemUser sysuser) {
        dao.removeTawSystemUser(id);
    }

    /**
     * 通过一些用户 查询这些用户中属于某部门的用户
     *
     * @param userids
     * @param deptid
     * @return
     */
    public List getUserBydeptidAndUserids(String userids, String deptid) {
        return TawSystemUserJdbc.getInstance().getUserBydeptidAndUserids(
                userids, deptid);
    }

    /**
     * 通过用户ID获得用户手机号码
     *
     * @param userId 用户ID
     * @return 用户手机号码
     */
    public String getMobilesByUserId(String userId) {
        return dao.getMobilesByUserId(userId);
    }

    /**
     * 验证用户密码是否为数字+字母组合并不少于6位
     *
     * @param passwd 用户密码
     * @return 验证通过否
     */
    public boolean checkPasswd(String passwd) {
        // 密码必须要于所设位数
        if (passwd == null
                || passwd.length() < UserMgrLocator.getUserAttributes()
                .getPasswdLength().intValue()) {
            return false;
        }
        // 密码中必须包括0-9
        if (!Pattern.compile(".*[0-9]{1,}.*").matcher(passwd).matches()) {
            return false;
        }
        // 密码中必须包括小写字母
        if (!Pattern.compile(".*[a-z]{1,}.*").matcher(passwd).matches()) {
            return false;
        }
        // 密码中必须包括大写字母
        if (!Pattern.compile(".*[A-Z]{1,}.*").matcher(passwd).matches()) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#checkUserId(java.lang.String)
     */
    public boolean checkUserId(String userId) {
        if (userId == null && "".equals(userId)) {
            return false;
        }
        // 字母开头，允许5-16字节，允许字母数字下划线
        if (Pattern.compile("^[a-zA-Z][a-zA-Z0-9_@.]{3,15}$").matcher(userId)
                .matches()) {
            return true;
        }

        return false;
    }

    /**
     * @param userPasswdHistoryMgr the userPasswdHistoryMgr to set
     */
    public void setUserPasswdHistoryMgr(
            IUserPasswdHistoryMgr userPasswdHistoryMgr) {
        this.userPasswdHistoryMgr = userPasswdHistoryMgr;
    }

    public List getUserIdsBydeptid(String deptid) {
        return dao.getUserIdsBydeptid(deptid);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#isUserExist(java.lang.String)
     */
    public boolean isUserExist(String userId) {
        TawSystemUser user = this.getUserByuserid(userId);
        if (user != null && user.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#listUsersByDeptId(java.lang.String)
     */
    public List listUsersByDeptId(String deptId) {
        // 递归取所有部门
        List depts = tawSystemDeptManager.getSubDepts(deptId);
        List users = null;
        if (null != depts) {
            String deptIds = this.depts2deptIds(depts);
            // 通过多个deptid取所有deptid下的用户
            users = this.getUserbydeptidanson(deptIds);
        }
        if (users == null) {
            return new ArrayList();
        }
        return users;
    }

    private String depts2deptIds(List depts) {
        StringBuffer deptIds = new StringBuffer();
        if (depts != null) {
            for (Iterator it = depts.iterator(); it.hasNext(); ) {
                TawSystemDept dept = (TawSystemDept) it.next();
                deptIds.append("'" + dept.getDeptId() + "',");
            }
        }
        String result = deptIds.toString();
        // 若不为空去掉最后逗号
        if (!"".equals(result)) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * @param tawSystemDeptManager the tawSystemDeptManager to set
     */
    public void setTawSystemDeptManager(
            ITawSystemDeptManager tawSystemDeptManager) {
        this.tawSystemDeptManager = tawSystemDeptManager;
    }

    public String updatePassword(TawSystemUser tawSystemUser, String password) {
        String flag = "false";
        tawSystemUser.setPassword(new Md5PasswordEncoder().encodePassword(
                password, new String()));
        dao.saveTawSystemUser(tawSystemUser);
        // MsgServiceImpl msgService = new MsgServiceImpl();
        // String creattime = StaticMethod.getLocalString();
        // String serverId = "1";
        // 根据现场要求，密码修改成功后，不再短信提醒
        // String content = "您的密码已经修改为"+password;
        // flag = msgService.sendMsg4Mobiles(serverId, content,
        // "-1", tawSystemUser.getMobile(), creattime);
        return flag;
    }

    public boolean getUser(String userId, String password) {
        // TODO Auto-generated method stub
        TawSystemUser tawSystemUser = null;
        boolean result = false;
        if (!"".equals(userId)) {
            tawSystemUser = dao.getUser(userId, password);
            if (!"".equals(tawSystemUser.getUsername())
                    && tawSystemUser.getUsername() != null) {
                result = true;
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#getUsersBySubRoleid(java.lang.String)
     */
    public List getUsersBySubRoleid(String subRoleId) {
        return dao.getUsersBySubRoleid(subRoleId);
    }


    public List getUserBydeptidAndSubs(String _deptId) {
        List list = new ArrayList();

        list = dao.getUserBydeptidAndSubs(_deptId);

        return list;
    }

    public List getUserLike(String deptid) {
        List list = new ArrayList();
        list = dao.getUserBydeptidAndSubs(deptid);
        return list;
    }

    public List getUserLikePartner(String deptid) {
        List list = new ArrayList();
        list = dao.getUserBydeptidAndSubsPartner(deptid);
        return list;
    }

    public List getUserBydeptidsNoSelf(String deptid, String userid) {
        List list = new ArrayList();

        list = dao.getUserBydeptidsNoSelf(deptid, userid);

        return list;
    }

    public String getUserByUserIdOrMobile(String userParam, String password) {
        String result = "";
        final String md5Passwd = new Md5PasswordEncoder().encodePassword(
                password, new String());
        List list = dao.getTawSystemUsers("(userid='" + userParam + "' and password='" + md5Passwd + "')" + " or (mobile='" + userParam + "' and password='" + md5Passwd + "')");
        if (list != null && list.size() > 0) {
            result = ((TawSystemUser) list.get(0)).getUserid();
        }
        return result;
    }

    public List getUserByMobile(String mobile) {
        List list = dao.getTawSystemUsers("mobile='" + mobile + "'");
        return list;
    }

    public List getUserByIdMobile(String name) {
        List list = dao.getUserByIdMobile(name);
        return list;
    }
}
