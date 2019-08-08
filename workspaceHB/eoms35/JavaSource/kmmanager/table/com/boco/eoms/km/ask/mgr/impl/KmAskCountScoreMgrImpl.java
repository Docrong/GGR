package com.boco.eoms.km.ask.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskCountScore;
import com.boco.eoms.km.ask.model.KmAskScore;
import com.boco.eoms.km.ask.mgr.KmAskCountScoreMgr;
import com.boco.eoms.km.ask.mgr.KmAskScoreMgr;
import com.boco.eoms.km.ask.dao.KmAskCountScoreDao;
import com.boco.eoms.km.ask.dao.KmAskScoreDao;

/**
 * <p>
 * Title:问答总积分
 * </p>
 * <p>
 * Description:问答总积分
 * </p>
 * <p>
 * Wed Aug 19 10:09:58 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskCountScoreMgrImpl implements KmAskCountScoreMgr {

    private KmAskCountScoreDao kmAskCountScoreDao;

    private KmAskScoreDao kmAskScoreDao;

    public KmAskCountScoreDao getKmAskCountScoreDao() {
        return this.kmAskCountScoreDao;
    }

    public void setKmAskCountScoreDao(KmAskCountScoreDao kmAskCountScoreDao) {
        this.kmAskCountScoreDao = kmAskCountScoreDao;
    }

    public List getKmAskCountScores() {
        return kmAskCountScoreDao.getKmAskCountScores();
    }

    /**
     * 查询某人的积分情况
     *
     * @param id
     * @return
     */
    public KmAskCountScore getKmAskCountScoreByUser(final String userName) {
        return kmAskCountScoreDao.getKmAskCountScoreByUser(userName);
    }

    public KmAskCountScore getKmAskCountScore(final String id) {
        return kmAskCountScoreDao.getKmAskCountScore(id);
    }

    public void saveKmAskCountScore(KmAskCountScore kmAskCountScore) {
        kmAskCountScoreDao.saveKmAskCountScore(kmAskCountScore);
    }


    public void saveKmAskCountScore(final String user, final String userDept, final Integer operateId) {
        KmAskScore kmAskScore = kmAskScoreDao.getKmAskScoreByOperateId(operateId);
        Integer countScore = kmAskScore.getScore();
        if (countScore == null) {
            countScore = new Integer(0);
        }
        KmAskCountScore kmAskCountScore = kmAskCountScoreDao.getKmAskCountScoreByUser(user);
        if ((kmAskCountScore.getId() == null) || (kmAskCountScore.getId().equals(""))) {
            kmAskCountScore.setCountScore(countScore);
            kmAskCountScore.setUserDept(userDept);
            kmAskCountScore.setUserName(user);
        } else {
            int score = kmAskCountScore.getCountScore().intValue();
            score += countScore.intValue();
            kmAskCountScore.setCountScore(new Integer(score));
        }
        kmAskCountScoreDao.saveKmAskCountScore(kmAskCountScore);
    }

    public void removeKmAskCountScore(final String id) {
        kmAskCountScoreDao.removeKmAskCountScore(id);
    }

    public Map getKmAskCountScores(final Integer curPage, final Integer pageSize,
                                   final String whereStr) {
        return kmAskCountScoreDao.getKmAskCountScores(curPage, pageSize, whereStr);
    }

    public KmAskScoreDao getKmAskScoreDao() {
        return kmAskScoreDao;
    }

    public void setKmAskScoreDao(KmAskScoreDao kmAskScoreDao) {
        this.kmAskScoreDao = kmAskScoreDao;
    }


}