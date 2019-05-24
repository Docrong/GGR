package com.boco.eoms.km.train.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainFeedback;
import com.boco.eoms.km.train.mgr.TrainFeedbackMgr;
import com.boco.eoms.km.train.dao.TrainFeedbackDao;

/**
 * <p>
 * Title:反馈信息
 * </p>
 * <p>
 * Description:反馈信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:47 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainFeedbackMgrImpl implements TrainFeedbackMgr {
 
	private TrainFeedbackDao  trainFeedbackDao;
 	
	public TrainFeedbackDao getTrainFeedbackDao() {
		return this.trainFeedbackDao;
	}
 	
	public void setTrainFeedbackDao(TrainFeedbackDao trainFeedbackDao) {
		this.trainFeedbackDao = trainFeedbackDao;
	}
 	
    public List getTrainFeedbacks() {
    	return trainFeedbackDao.getTrainFeedbacks();
    }
    
    /**
	 * 查询某培训计划下的所有反馈信息
	 * @param trainPlanId
	 * @return
	 */
	public List getTrainFeedbacksByPlanId(final String trainPlanId){
		return trainFeedbackDao.getTrainFeedbacksByPlanId(trainPlanId);
	}
    
    public TrainFeedback getTrainFeedback(final String id) {
    	return trainFeedbackDao.getTrainFeedback(id);
    }
    
    public void saveTrainFeedback(TrainFeedback trainFeedback) {
    	trainFeedbackDao.saveTrainFeedback(trainFeedback);
    }
    
    public void removeTrainFeedback(final String id) {
    	trainFeedbackDao.removeTrainFeedback(id);
    }
    
    public Map getTrainFeedbacks(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return trainFeedbackDao.getTrainFeedbacks(curPage, pageSize, whereStr);
	}
	
}