package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertEdu;
import com.boco.eoms.km.expert.mgr.KmExpertEduMgr;
import com.boco.eoms.km.expert.dao.KmExpertEduDao;

/**
 * <p>
 * Title:教育背景
 * </p>
 * <p>
 * Description:专家教育背景
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertEduMgrImpl implements KmExpertEduMgr {
 
	private KmExpertEduDao  kmExpertEduDao;
 	
	public KmExpertEduDao getKmExpertEduDao() {
		return this.kmExpertEduDao;
	}
 	
	public void setKmExpertEduDao(KmExpertEduDao kmExpertEduDao) {
		this.kmExpertEduDao = kmExpertEduDao;
	}
 	
    public List getKmExpertEdus() {
    	return kmExpertEduDao.getKmExpertEdus();
    }
    
    public KmExpertEdu getKmExpertEdu(final String id) {
    	return kmExpertEduDao.getKmExpertEdu(id);
    }
    
    public void saveKmExpertEdu(KmExpertEdu kmExpertEdu) {
    	kmExpertEduDao.saveKmExpertEdu(kmExpertEdu);
    }
    
    public void removeKmExpertEdu(final String id) {
    	kmExpertEduDao.removeKmExpertEdu(id);
    }
    
    /**
     * 根据id批量删除教育背景
     * @param id 主键
     * 
     */
    public void removeKmExpertEdus(final String[] ids){
    	kmExpertEduDao.removeKmExpertEdus(ids);
    }
    
    public Map getKmExpertEdus(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExpertEduDao.getKmExpertEdus(curPage, pageSize, whereStr);
	}

	public Map getKmExpertEdusByUserId(final Integer curPage, final Integer pageSize,
			final String userId) {
		return kmExpertEduDao.getKmExpertEdusByUserId(curPage, pageSize, userId);
	}
	
}