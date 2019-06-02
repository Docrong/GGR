package com.boco.eoms.km.statics.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.statics.model.KmOperateScore;
import com.boco.eoms.km.statics.mgr.KmOperateScoreMgr;
import com.boco.eoms.km.statics.dao.KmOperateScoreDao;

/**
 * <p>
 * Title:操作积分定义表
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KmOperateScoreMgrImpl implements KmOperateScoreMgr {
 
	private KmOperateScoreDao  kmOperateScoreDao;
 	
	public KmOperateScoreDao getKmOperateScoreDao() {
		return this.kmOperateScoreDao;
	}
 	
	public void setKmOperateScoreDao(KmOperateScoreDao kmOperateScoreDao) {
		this.kmOperateScoreDao = kmOperateScoreDao;
	}
 	
    public List getKmOperateScores() {
    	return kmOperateScoreDao.getKmOperateScores();
    }
    
    public KmOperateScore getKmOperateScore(final String id) {
    	return kmOperateScoreDao.getKmOperateScore(id);
    }
    
    public KmOperateScore getKmOperateScore(final Integer operateId) {
    	return kmOperateScoreDao.getKmOperateScore(operateId);
    }
    
    public void saveKmOperateScore(KmOperateScore kmOperateScore) {
    	kmOperateScoreDao.saveKmOperateScore(kmOperateScore);
    }
    
    public void removeKmOperateScore(final String id) {
    	kmOperateScoreDao.removeKmOperateScore(id);
    }
    
    public Map getKmOperateScores(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmOperateScoreDao.getKmOperateScores(curPage, pageSize, whereStr);
	}
	
}