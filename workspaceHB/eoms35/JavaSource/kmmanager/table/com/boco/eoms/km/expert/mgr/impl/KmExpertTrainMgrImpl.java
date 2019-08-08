package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertTrain;
import com.boco.eoms.km.expert.mgr.KmExpertTrainMgr;
import com.boco.eoms.km.expert.dao.KmExpertTrainDao;

/**
 * <p>
 * Title:培训经历
 * </p>
 * <p>
 * Description:培训经历
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmExpertTrainMgrImpl implements KmExpertTrainMgr {

    private KmExpertTrainDao kmExpertTrainDao;

    public KmExpertTrainDao getKmExpertTrainDao() {
        return this.kmExpertTrainDao;
    }

    public void setKmExpertTrainDao(KmExpertTrainDao kmExpertTrainDao) {
        this.kmExpertTrainDao = kmExpertTrainDao;
    }

    public List getKmExpertTrains() {
        return kmExpertTrainDao.getKmExpertTrains();
    }

    public KmExpertTrain getKmExpertTrain(final String id) {
        return kmExpertTrainDao.getKmExpertTrain(id);
    }

    public void saveKmExpertTrain(KmExpertTrain kmExpertTrain) {
        kmExpertTrainDao.saveKmExpertTrain(kmExpertTrain);
    }

    public void removeKmExpertTrain(final String id) {
        kmExpertTrainDao.removeKmExpertTrain(id);
    }

    public void removeKmExpertTrains(final String[] ids) {
        kmExpertTrainDao.removeKmExpertTrains(ids);
    }

    public Map getKmExpertTrains(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return kmExpertTrainDao.getKmExpertTrains(curPage, pageSize, whereStr);
    }

    public Map getKmExpertTrainsByUserId(final Integer curPage, final Integer pageSize,
                                         final String userId) {
        return kmExpertTrainDao.getKmExpertTrainsByUserId(curPage, pageSize, userId);
    }

}