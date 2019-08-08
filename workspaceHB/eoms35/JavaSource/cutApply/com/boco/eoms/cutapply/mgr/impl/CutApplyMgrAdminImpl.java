package com.boco.eoms.cutapply.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.cutapply.model.CutApplyAdmin;
import com.boco.eoms.cutapply.mgr.CutApplyAdminMgr;
import com.boco.eoms.cutapply.dao.CutApplyAdminDao;

/**
 * <p>
 * Title:干线割接管理
 * </p>
 * <p>
 * Description:干线割接管理
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 *
 * @author wangsixuan
 * @version 3.5
 */
public class CutApplyMgrAdminImpl implements CutApplyAdminMgr {

    private CutApplyAdminDao cutApplyAdminDao;

    public CutApplyAdminDao getCutApplyAdminDao() {
        return this.cutApplyAdminDao;
    }

    public void setCutApplyAdminDao(CutApplyAdminDao cutApplyAdminDao) {
        this.cutApplyAdminDao = cutApplyAdminDao;
    }

    public List getCutApplyAdmins() {
        return cutApplyAdminDao.getCutApplyAdmins();
    }

    public CutApplyAdmin getCutApplyAdmin(final String id) {
        return cutApplyAdminDao.getCutApplyAdmin(id);
    }

    public void saveCutApplyAdmin(CutApplyAdmin cutApplyAdmin) {
        cutApplyAdminDao.saveCutApplyAdmin(cutApplyAdmin);
    }

    public void removeCutApplyAdmin(final String id) {
        cutApplyAdminDao.removeCutApplyAdmin(id);
    }

    public Map getCutApplyAdmins(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return cutApplyAdminDao.getCutApplyAdmins(curPage, pageSize, whereStr);
    }

    public List getCutApplyAdminsByCondition(final String hql) {
        return cutApplyAdminDao.getCutApplyAdminsByCondition(hql);
    }

}