package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamPublic;
import com.boco.eoms.km.exam.mgr.KmExamPublicMgr;
import com.boco.eoms.km.exam.dao.KmExamPublicDao;

/**
 * <p>
 * Title:考试发布
 * </p>
 * <p>
 * Description:考试发布
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmExamPublicMgrImpl implements KmExamPublicMgr {
 
	private KmExamPublicDao  kmExamPublicDao;
 	
	public KmExamPublicDao getKmExamPublicDao() {
		return this.kmExamPublicDao;
	}
 	
	public void setKmExamPublicDao(KmExamPublicDao kmExamPublicDao) {
		this.kmExamPublicDao = kmExamPublicDao;
	}
 	
    public List getKmExamPublics() {
    	return kmExamPublicDao.getKmExamPublics();
    }
    
    public KmExamPublic getKmExamPublic(final String id) {
    	return kmExamPublicDao.getKmExamPublic(id);
    }
    
    public void saveKmExamPublic(KmExamPublic kmExamPublic) {
    	kmExamPublicDao.saveKmExamPublic(kmExamPublic);
    }
    
    public void removeKmExamPublic(final String id) {
    	kmExamPublicDao.removeKmExamPublic(id);
    }
    
    public Map getKmExamPublics(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExamPublicDao.getKmExamPublics(curPage, pageSize, whereStr);
	}
	
}