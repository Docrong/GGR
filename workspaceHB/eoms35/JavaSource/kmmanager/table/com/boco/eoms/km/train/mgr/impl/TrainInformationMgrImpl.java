package com.boco.eoms.km.train.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainInformation;
import com.boco.eoms.km.train.mgr.TrainInformationMgr;
import com.boco.eoms.km.train.dao.TrainInformationDao;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:10:34 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainInformationMgrImpl implements TrainInformationMgr {

    private TrainInformationDao trainInformationDao;

    public TrainInformationDao getTrainInformationDao() {
        return this.trainInformationDao;
    }

    public void setTrainInformationDao(TrainInformationDao trainInformationDao) {
        this.trainInformationDao = trainInformationDao;
    }

    public List getTrainInformations() {
        return trainInformationDao.getTrainInformations();
    }

    public TrainInformation getTrainInformation(final String id) {
        return trainInformationDao.getTrainInformation(id);
    }

    public void saveTrainInformation(TrainInformation trainInformation) {
        trainInformationDao.saveTrainInformation(trainInformation);
    }

    public void removeTrainInformation(final String id) {
        trainInformationDao.removeTrainInformation(id);
    }

    public Map getTrainInformations(final Integer curPage, final Integer pageSize,
                                    final String whereStr) {
        return trainInformationDao.getTrainInformations(curPage, pageSize, whereStr);
    }

}