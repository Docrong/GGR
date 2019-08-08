package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsHistory;
import com.boco.eoms.km.knowledge.mgr.KmContentsHistoryMgr;
import com.boco.eoms.km.knowledge.dao.KmContentsHistoryDao;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:33 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmContentsHistoryMgrImpl implements KmContentsHistoryMgr {

    private KmContentsHistoryDao kmContentsHistoryDao;

    public KmContentsHistoryDao getKmContentsHistoryDao() {
        return this.kmContentsHistoryDao;
    }

    public void setKmContentsHistoryDao(KmContentsHistoryDao kmContentsHistoryDao) {
        this.kmContentsHistoryDao = kmContentsHistoryDao;
    }

    public List getKmContentsHistorys() {
        return kmContentsHistoryDao.getKmContentsHistorys();
    }

    public KmContentsHistory getKmContentsHistory(final String id) {
        return kmContentsHistoryDao.getKmContentsHistory(id);
    }

    public void saveKmContentsHistory(KmContentsHistory kmContentsHistory) {
        kmContentsHistoryDao.saveKmContentsHistory(kmContentsHistory);
    }

    public void removeKmContentsHistory(final String id) {
        kmContentsHistoryDao.removeKmContentsHistory(id);
    }

    public Map getKmContentsHistorys(final Integer curPage, final Integer pageSize,
                                     final String whereStr) {
        return kmContentsHistoryDao.getKmContentsHistorys(curPage, pageSize, whereStr);
    }

}