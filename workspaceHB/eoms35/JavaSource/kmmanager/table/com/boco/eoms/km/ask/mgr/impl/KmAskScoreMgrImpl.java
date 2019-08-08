package com.boco.eoms.km.ask.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskScore;
import com.boco.eoms.km.ask.mgr.KmAskScoreMgr;
import com.boco.eoms.km.ask.dao.KmAskScoreDao;

/**
 * <p>
 * Title:问答积分配置
 * </p>
 * <p>
 * Description:问答积分配置
 * </p>
 * <p>
 * Wed Aug 19 16:41:06 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskScoreMgrImpl implements KmAskScoreMgr {

    private KmAskScoreDao kmAskScoreDao;

    public KmAskScoreDao getKmAskScoreDao() {
        return this.kmAskScoreDao;
    }

    public void setKmAskScoreDao(KmAskScoreDao kmAskScoreDao) {
        this.kmAskScoreDao = kmAskScoreDao;
    }

    public List getKmAskScores() {
        return kmAskScoreDao.getKmAskScores();
    }

    /**
     * 根据操作id 查询积分配置
     *
     * @param operateId
     * @return
     */
    public KmAskScore getKmAskScoreByOperateId(final Integer operateId) {
        return kmAskScoreDao.getKmAskScoreByOperateId(operateId);
    }

    public KmAskScore getKmAskScore(final String id) {
        return kmAskScoreDao.getKmAskScore(id);
    }

    public void saveKmAskScore(KmAskScore kmAskScore) {
        kmAskScoreDao.saveKmAskScore(kmAskScore);
    }

    public void removeKmAskScore(final String id) {
        kmAskScoreDao.removeKmAskScore(id);
    }

    public Map getKmAskScores(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        return kmAskScoreDao.getKmAskScores(curPage, pageSize, whereStr);
    }

}