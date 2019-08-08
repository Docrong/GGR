package com.boco.eoms.km.train.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainPlan;
import com.boco.eoms.km.train.mgr.TrainPlanMgr;
import com.boco.eoms.km.train.dao.TrainPlanDao;

/**
 * <p>
 * Title:培训计划
 * </p>
 * <p>
 * Description:培训及哈
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainPlanMgrImpl implements TrainPlanMgr {

    private TrainPlanDao trainPlanDao;

    public TrainPlanDao getTrainPlanDao() {
        return this.trainPlanDao;
    }

    public void setTrainPlanDao(TrainPlanDao trainPlanDao) {
        this.trainPlanDao = trainPlanDao;
    }

    public List getTrainPlans() {
        return trainPlanDao.getTrainPlans();
    }

    public TrainPlan getTrainPlan(final String id) {
        return trainPlanDao.getTrainPlan(id);
    }

    public void saveTrainPlan(TrainPlan trainPlan) {
        trainPlanDao.saveTrainPlan(trainPlan);
    }

    public void removeTrainPlan(final String id) {
        trainPlanDao.removeTrainPlan(id);
    }

    public Map getTrainPlans(final Integer curPage, final Integer pageSize,
                             final String whereStr) {
        return trainPlanDao.getTrainPlans(curPage, pageSize, whereStr);
    }

}