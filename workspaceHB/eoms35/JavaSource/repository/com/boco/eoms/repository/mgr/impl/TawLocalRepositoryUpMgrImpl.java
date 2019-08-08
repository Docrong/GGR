package com.boco.eoms.repository.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.repository.dao.TawLocalRepositoryUpDao;
import com.boco.eoms.repository.mgr.TawLocalRepositoryUpMgr;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepositoryUp
 * </p>
 * <p>
 * Description:tawLocalRepositoryUp
 * </p>
 * <p>
 * Fri Oct 30 16:52:13 CST 2009
 * </p>
 *
 * @author 李锋
 * @version 1.0
 */
public class TawLocalRepositoryUpMgrImpl implements TawLocalRepositoryUpMgr {

    private TawLocalRepositoryUpDao tawLocalRepositoryUpDao;

    public TawLocalRepositoryUpDao getTawLocalRepositoryUpDao() {
        return this.tawLocalRepositoryUpDao;
    }

    public void setTawLocalRepositoryUpDao(TawLocalRepositoryUpDao tawLocalRepositoryUpDao) {
        this.tawLocalRepositoryUpDao = tawLocalRepositoryUpDao;
    }

    public List getTawLocalRepositoryUps() {
        return tawLocalRepositoryUpDao.getTawLocalRepositoryUps();
    }

    public TawLocalRepositoryUp getTawLocalRepositoryUp(final String id) {
        return tawLocalRepositoryUpDao.getTawLocalRepositoryUp(id);
    }

    public void saveTawLocalRepositoryUp(TawLocalRepositoryUp tawLocalRepositoryUp) {
        tawLocalRepositoryUpDao.saveTawLocalRepositoryUp(tawLocalRepositoryUp);
    }

    public void removeTawLocalRepositoryUp(final String id) {
        tawLocalRepositoryUpDao.removeTawLocalRepositoryUp(id);
    }

    public Map getTawLocalRepositoryUps(final Integer curPage, final Integer pageSize,
                                        final String whereStr) {
        return tawLocalRepositoryUpDao.getTawLocalRepositoryUps(curPage, pageSize, whereStr);
    }

}