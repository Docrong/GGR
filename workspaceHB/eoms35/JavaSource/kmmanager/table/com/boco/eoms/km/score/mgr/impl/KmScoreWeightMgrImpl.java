package com.boco.eoms.km.score.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.score.model.KmScoreWeight;
import com.boco.eoms.km.score.mgr.KmScoreWeightMgr;
import com.boco.eoms.km.score.dao.KmScoreWeightDao;

/**
 * <p>
 * Title:积分权重
 * </p>
 * <p>
 * Description:积分权重表
 * </p>
 * <p>
 * Fri Aug 21 09:06:28 CST 2009
 * </p>
 *
 * @author me
 * @version 1.0
 */
public class KmScoreWeightMgrImpl implements KmScoreWeightMgr {

    private KmScoreWeightDao kmScoreWeightDao;

    public KmScoreWeightDao getKmScoreWeightDao() {
        return this.kmScoreWeightDao;
    }

    public void setKmScoreWeightDao(KmScoreWeightDao kmScoreWeightDao) {
        this.kmScoreWeightDao = kmScoreWeightDao;
    }

    public List getKmScoreWeights() {
        return kmScoreWeightDao.getKmScoreWeights();
    }

    public KmScoreWeight getKmScoreWeight(final String id) {
        return kmScoreWeightDao.getKmScoreWeight(id);
    }

    public void saveKmScoreWeight(KmScoreWeight kmScoreWeight) {
        kmScoreWeightDao.saveKmScoreWeight(kmScoreWeight);
    }

    public void removeKmScoreWeight(final String id) {
        kmScoreWeightDao.removeKmScoreWeight(id);
    }

    public Map getKmScoreWeights(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return kmScoreWeightDao.getKmScoreWeights(curPage, pageSize, whereStr);
    }

    public List getNextLevelKmScoreWeights(final String nodeId, final Integer nodeIdLength) {
        return kmScoreWeightDao.getNextLevelKmScoreWeights(nodeId, nodeIdLength);
    }

    public KmScoreWeight getKmScoreWeightByNodeId(String nodeId) {
        // TODO Auto-generated method stub
        return kmScoreWeightDao.getKmScoreWeightByNodeId(nodeId);
    }

    public void removeKmScoreWeightByNodeId(String nodeId) {
        kmScoreWeightDao.removeKmScoreWeightByNodeId(nodeId);
    }

}