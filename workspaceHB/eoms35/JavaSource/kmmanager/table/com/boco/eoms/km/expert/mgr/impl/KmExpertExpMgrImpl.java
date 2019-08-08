package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertExp;
import com.boco.eoms.km.expert.mgr.KmExpertExpMgr;
import com.boco.eoms.km.expert.dao.KmExpertExpDao;

/**
 * <p>
 * Title:工作经验
 * </p>
 * <p>
 * Description:工作经验
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertExpMgrImpl implements KmExpertExpMgr {

    private KmExpertExpDao kmExpertExpDao;

    public KmExpertExpDao getKmExpertExpDao() {
        return this.kmExpertExpDao;
    }

    public void setKmExpertExpDao(KmExpertExpDao kmExpertExpDao) {
        this.kmExpertExpDao = kmExpertExpDao;
    }

    public List getKmExpertExps() {
        return kmExpertExpDao.getKmExpertExps();
    }

    public KmExpertExp getKmExpertExp(final String id) {
        return kmExpertExpDao.getKmExpertExp(id);
    }

    public void saveKmExpertExp(KmExpertExp kmExpertExp) {
        kmExpertExpDao.saveKmExpertExp(kmExpertExp);
    }

    public void removeKmExpertExp(final String id) {
        kmExpertExpDao.removeKmExpertExp(id);
    }

    /**
     * 根据id批量删除工作经验
     *
     * @param id 主键
     */
    public void removeKmExpertExps(final String ids[]) {
        kmExpertExpDao.removeKmExpertExps(ids);
    }

    public Map getKmExpertExps(final Integer curPage, final Integer pageSize,
                               final String whereStr) {
        return kmExpertExpDao.getKmExpertExps(curPage, pageSize, whereStr);
    }

    public Map getKmExpertExpsByUserId(final Integer curPage, final Integer pageSize,
                                       final String userId) {
        return kmExpertExpDao.getKmExpertExpsByUserId(curPage, pageSize, userId);
    }

}