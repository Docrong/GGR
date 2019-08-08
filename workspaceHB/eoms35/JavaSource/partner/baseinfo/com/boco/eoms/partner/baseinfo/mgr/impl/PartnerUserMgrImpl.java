package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.PartnerUser;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserMgr;
import com.boco.eoms.partner.baseinfo.dao.PartnerUserDao;

/**
 * <p>
 * Title:��f��Ϣ
 * </p>
 * <p>
 * Description:��f��Ϣ
 * </p>
 * <p>
 * Tue Feb 10 17:33:14 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public class PartnerUserMgrImpl implements PartnerUserMgr {

    private PartnerUserDao partnerUserDao;

    public PartnerUserDao getPartnerUserDao() {
        return this.partnerUserDao;
    }

    public void setPartnerUserDao(PartnerUserDao partnerUserDao) {
        this.partnerUserDao = partnerUserDao;
    }

    public List getPartnerUsers() {
        return partnerUserDao.getPartnerUsers();
    }

    public PartnerUser getPartnerUser(final String id) {
        return partnerUserDao.getPartnerUser(id);
    }

    public void savePartnerUser(PartnerUser partnerUser) {
        partnerUserDao.savePartnerUser(partnerUser);
    }

    public void removePartnerUser(final String id) {
        partnerUserDao.removePartnerUser(id);
    }

    public Map getPartnerUsers(final Integer curPage, final Integer pageSize,
                               final String whereStr) {
        return partnerUserDao.getPartnerUsers(curPage, pageSize, whereStr);
    }

    //判断人力信息userId 是否唯一
    public Boolean isunique(final String userId) {
        return partnerUserDao.isunique(userId);
    }

    //批量删除某地市某厂商下所有的人力信息，参数是treeNodeId
    public void removePartnerUserByTreeNodeId(final String treeNodeId) {
        partnerUserDao.removePartnerUserByTreeNodeId(treeNodeId);
    }

}