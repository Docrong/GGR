package com.boco.eoms.commons.system.user.service.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;

public class TawSystemUserBoImpl implements ITawSystemUserBo {

    ITawSystemUserManager usermanager;

    /**
     * 得到部门的所有USER
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptids(String deptid) {

        List list = new ArrayList();

        list = usermanager.getUserBydeptids(deptid);

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

        list = usermanager.getUserBydeptidsPartner(deptid);

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

        user = usermanager.getUserByuserid(userid);

        return user;

    }

    /**
     * 获取系统管理员的用户列表
     *
     * @param userdegree
     * @return
     */
    public List getUserbyUserdegrees(String userdegree) {

        List list = new ArrayList();

        list = usermanager.getUserbyUserdegrees(userdegree);

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

        list = usermanager.getUserbyCptids(cptid);

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

        list = usermanager.getUserbyOperuserids(operuserid);

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

        list = usermanager.getUserbysexs(sexid);

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

        list = usermanager.getUserbyDeptidandDegids(deptid, degreeid);

        return list;

    }

    /**
     * 取得某机房的所有管理员
     *
     * @param cptid
     * @param degid
     * @return
     */
    public List getUserBycptidAndDegids(String cptid, String degid) {

        List list = new ArrayList();

        list = usermanager.getUserBycptidAndDegid(cptid, degid);

        return list;

    }

    /**
     * 删除某部门的所有用户
     *
     * @param deptid
     * @throws TawSystemUserException
     */
    public void removeUserbydeptid(String deptid) throws TawSystemUserException {

        try {
            List userlist = new ArrayList();
            userlist = usermanager.getUserBydeptids(deptid);

            if (userlist != null && userlist.size() > 0) {
                TawSystemUser sysuser = new TawSystemUser();
                ITawSystemPrivAssignManager mgr = (ITawSystemPrivAssignManager) ApplicationContextHolder
                        .getInstance().getBean("ItawSystemPrivAssignManager");
                for (int i = 0; i < userlist.size(); i++) {

                    sysuser = (TawSystemUser) userlist.get(i);
                    if (mgr.hasAssign(sysuser.getUserid())) {

                        usermanager.removeTawSystemUser(sysuser.getId(),
                                sysuser.getUserid());
                    }

                }
                usermanager.removeUserbydeptid(deptid);
            }

        } catch (Exception ex) {

            throw new TawSystemUserException("删除部门用户失败 " + deptid
                    + " 没有此部门或者系统出错");
        }

    }

    /**
     * 删除某机房的所有用户
     *
     * @param cptid
     * @throws TawSystemUserException
     */
    public void removeUserbycptid(String cptid) throws TawSystemUserException {

        try {
            List userlist = new ArrayList();
            userlist = usermanager.getUserbyCptids(cptid);

            if (userlist != null && userlist.size() > 0) {
                TawSystemUser sysuser = new TawSystemUser();
                ITawSystemPrivAssignManager mgr = (ITawSystemPrivAssignManager) ApplicationContextHolder
                        .getInstance().getBean("ItawSystemPrivAssignManager");
                for (int i = 0; i < userlist.size(); i++) {

                    sysuser = (TawSystemUser) userlist.get(i);
                    if (mgr.hasAssign(sysuser.getUserid())) {

                        usermanager.removeTawSystemUser(sysuser.getId(),
                                sysuser.getUserid());
                    }

                }
                usermanager.removeUserbycptid(cptid);
            }

        } catch (Exception ex) {

            throw new TawSystemUserException("删除机房 " + cptid
                    + " 用户失败 没有此机房或者系统出错");
        }

    }

    /**
     * 修改某用户信息
     *
     * @param userid
     * @throws TawSystemUserException
     */
    public void saveOrUpdateuser(String userid, TawSystemUser systemuser)
            throws TawSystemUserException {

        try {
            TawSystemUser newsystemuser = usermanager.getUserByuserid(userid);
            newsystemuser.setCptroomid(systemuser.getCptroomid());
            newsystemuser.setCptroomname(systemuser.getCptroomname());
            newsystemuser.setDeptid(systemuser.getDeptid());
            newsystemuser.setDeptname(systemuser.getDeptname());
            newsystemuser.setEmail(systemuser.getEmail());
            newsystemuser.setFamilyaddress(systemuser.getFamilyaddress());
            newsystemuser.setFax(systemuser.getFax());
            newsystemuser.setMobile(systemuser.getMobile());
            newsystemuser.setOperremotip(systemuser.getOperremotip());
            newsystemuser.setOperuserid(systemuser.getOperuserid());
            newsystemuser.setPhone(systemuser.getPhone());
            newsystemuser.setPassword(systemuser.getPassword());
            newsystemuser.setRemark(systemuser.getRemark());
            newsystemuser.setSavetime(systemuser.getSavetime());
            newsystemuser.setSex(systemuser.getSex());
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-mm-dd hh:mm:ss");
            newsystemuser.setUpdatetime(format.format(new Date()));
            newsystemuser.setUserdegree(systemuser.getUserdegree());
            newsystemuser.setUserid(systemuser.getUserid());
            newsystemuser.setUsername(systemuser.getUsername());

            usermanager.saveTawSystemUser(newsystemuser, "");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new TawSystemUserException("更新用户 " + userid + " 出错!");
        }
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

        list = usermanager.getUserbydeptidanson(deptids);

        return list;
    }

    /**
     * 保存用户信息
     *
     * @param systemuser
     */
    public void saveUser(TawSystemUser systemuser) {

        usermanager.saveTawSystemUser(systemuser, "");

    }

    /**
     * 删除某用户信息
     *
     * @param userid
     */
    public void removeUserbyuserid(String userid) {

        TawSystemUser systemuser = new TawSystemUser();

        systemuser = usermanager.getUserByuserid(userid);
        usermanager.removeTawSystemUser(systemuser.getId());

    }

    /**
     * 查询某部门所有的EMAIL
     *
     * @return
     */
    public List getAllEmalibyDeptid(String deptid) {

        List list = new ArrayList();

        list = usermanager.getAllEmalibyDeptid(deptid);

        return list;

    }

    /**
     * 查询某部门所有的手机号
     *
     * @return
     */
    public List getAllMobileBydeptid(String deptid) {

        List list = new ArrayList();

        list = usermanager.getAllMobileBydeptid(deptid);

        return list;

    }

    /**
     * 查询某机房的所有EMAIL
     *
     * @return
     */
    public List getAllEmailbyCptid(String cptid) {

        List list = new ArrayList();

        list = usermanager.getAllEmailbyCptid(cptid);

        return list;
    }

    /**
     * 查询某机房的所有手机号
     *
     * @return
     */
    public List getAllMobilebyCptid(String cptid) {

        List list = new ArrayList();

        list = usermanager.getAllMobilebyCptid(cptid);

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

        list = usermanager.getAllEmailbyuserids(userids);

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

        list = usermanager.getAllMobileByuerids(userids);

        return list;
    }

    /**
     * 得到正在休假的用户
     *
     * @param userid
     * @return
     */
    public List getRestByUserid(String userid) {
        return usermanager.getRestByUserid(userid);
    }

    /**
     * 得到未休假的用户
     *
     * @param userid
     * @return
     */
    public List getNoRestByUserid(String userid) {
        return usermanager.getNoRestByUserid(userid);
    }

    /**
     * 判断某用户是否正在休假
     *
     * @param userid
     * @return
     */
    public boolean isRestbyUserid(String userid) {
        return usermanager.isRestbyUserid(userid);
    }

    /**
     * 判断某用户是否是全职
     *
     * @param userid
     * @return
     */
    public boolean isFullemploybyUserid(String userid) {
        return usermanager.isFullemploybyUserid(userid);
    }

    /**
     * 查询所有全职用户
     *
     * @param userid
     * @return
     */
    public List getFullemploybyUserid(String userid) {
        return usermanager.getFullemploybyUserid(userid);
    }

    /**
     * 查询所有兼职用户
     *
     * @param userid
     * @return
     */
    public List getPartemployBuUserid(String userid) {
        return usermanager.getPartemployBuUserid(userid);
    }

    /**
     * 通过一些用户 查询这些用户中属于某部门的用户
     *
     * @param userids
     * @param deptid
     * @return
     */
    public List getUserBydeptidAndUserids(String userids, String deptid) {
        return usermanager.getUserBydeptidAndUserids(userids, deptid);
    }

    public ITawSystemUserManager getUsermanager() {
        return usermanager;
    }

    public void setUsermanager(ITawSystemUserManager usermanager) {
        this.usermanager = usermanager;
    }

    public List getUserBydeptidsNoSelf(String deptid, String userid) {
        List list = new ArrayList();

        list = usermanager.getUserBydeptidsNoSelf(deptid, userid);

        return list;
    }

}
