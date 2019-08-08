package com.boco.eoms.km.score.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsApply;
import com.boco.eoms.km.score.model.KmScoreSet;
import com.boco.eoms.km.score.model.KmScoreWeight;
import com.boco.eoms.km.score.mgr.KmScoreSetMgr;
import com.boco.eoms.km.score.dao.KmScoreSetDao;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:积分设定表
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 *
 * @author me
 * @version 1.0
 */
public class KmScoreSetMgrImpl implements KmScoreSetMgr {

    private KmScoreSetDao kmScoreSetDao;

    public KmScoreSetDao getKmScoreSetDao() {
        return this.kmScoreSetDao;
    }

    public void setKmScoreSetDao(KmScoreSetDao kmScoreSetDao) {
        this.kmScoreSetDao = kmScoreSetDao;
    }

    public List getKmScoreSets() {
        return kmScoreSetDao.getKmScoreSets();
    }

    public KmScoreSet getKmScoreSet(final String id) {
        return kmScoreSetDao.getKmScoreSet(id);
    }

    public void saveKmScoreSet(KmScoreSet kmScoreSet) {
        kmScoreSetDao.saveKmScoreSet(kmScoreSet);
    }

    public void removeKmScoreSet(final String id) {
        kmScoreSetDao.removeKmScoreSet(id);
    }

    public Map getKmScoreSets(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        Map map = kmScoreSetDao.getKmScoreSets(curPage, pageSize, whereStr);
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            KmScoreSet kmScoreSet = null;
            KmScoreWeight kmScoreWeight = null;
            if (objs[0] instanceof KmScoreSet) {
                kmScoreSet = (KmScoreSet) objs[0];
                kmScoreWeight = (KmScoreWeight) objs[1];
            } else {
                kmScoreWeight = (KmScoreWeight) objs[0];
                kmScoreSet = (KmScoreSet) objs[1];
            }
            kmScoreSet.setActionWeight(kmScoreWeight.getActionWeight());
            kmScoreSet.setPowerWeight(kmScoreWeight.getPowerWeight());
            list_result.add(kmScoreSet);
        }
        map.put("result", list_result);
        return map;
    }

    public void removeKmScoreSetByPowerName(String powerName) {
        kmScoreSetDao.removeKmScoreSetByPowerName(powerName);
    }

    public void removeKmScoreSetId(String id) {
        kmScoreSetDao.removeKmScoreSetId(id);
    }

}