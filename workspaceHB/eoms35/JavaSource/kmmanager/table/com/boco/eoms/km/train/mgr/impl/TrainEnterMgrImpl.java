package com.boco.eoms.km.train.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainEnter;
import com.boco.eoms.km.train.mgr.TrainEnterMgr;
import com.boco.eoms.km.train.dao.TrainEnterDao;

/**
 * <p>
 * Title:报名信息
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainEnterMgrImpl implements TrainEnterMgr {

    private TrainEnterDao trainEnterDao;

    public TrainEnterDao getTrainEnterDao() {
        return this.trainEnterDao;
    }

    public void setTrainEnterDao(TrainEnterDao trainEnterDao) {
        this.trainEnterDao = trainEnterDao;
    }

    public List getTrainEnters() {
        return trainEnterDao.getTrainEnters();
    }

    /**
     * 培训计划的报名信息
     *
     * @return
     */
    public List getTrainEntersByPlanId(final String trainPlanId) {
        return trainEnterDao.getTrainEntersByPlanId(trainPlanId);
    }

    public TrainEnter getTrainEnter(final String id) {
        return trainEnterDao.getTrainEnter(id);
    }

    public void saveTrainEnter(TrainEnter trainEnter) {
        trainEnterDao.saveTrainEnter(trainEnter);
    }

    public void removeTrainEnter(final String id) {
        trainEnterDao.removeTrainEnter(id);
    }

    public Map getTrainEnters(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        return trainEnterDao.getTrainEnters(curPage, pageSize, whereStr);
    }

}