package com.boco.eoms.km.train.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.train.model.TrainRequire;
import com.boco.eoms.km.train.mgr.TrainRequireMgr;
import com.boco.eoms.km.train.dao.TrainRequireDao;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 15:34:49 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainRequireMgrImpl implements TrainRequireMgr {
 
	private TrainRequireDao  trainRequireDao;
 	
	public TrainRequireDao getTrainRequireDao() {
		return this.trainRequireDao;
	}
 	
	public void setTrainRequireDao(TrainRequireDao trainRequireDao) {
		this.trainRequireDao = trainRequireDao;
	}
 	
    public List getTrainRequires() {
    	return trainRequireDao.getTrainRequires();
    }
    
    public TrainRequire getTrainRequire(final String id) {
    	return trainRequireDao.getTrainRequire(id);
    }
    
    public void saveTrainRequire(TrainRequire trainRequire) {
    	trainRequireDao.saveTrainRequire(trainRequire);
    }
    
    public void removeTrainRequire(final String id) {
    	trainRequireDao.removeTrainRequire(id);
    }
    
    public Map getTrainRequires(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return trainRequireDao.getTrainRequires(curPage, pageSize, whereStr);
	}
	
}