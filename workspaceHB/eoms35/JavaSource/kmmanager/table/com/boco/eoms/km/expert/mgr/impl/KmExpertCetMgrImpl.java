package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertCet;
import com.boco.eoms.km.expert.mgr.KmExpertCetMgr;
import com.boco.eoms.km.expert.dao.KmExpertCetDao;

/**
 * <p>
 * Title:证书管理
 * </p>
 * <p>
 * Description:证书管理
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertCetMgrImpl implements KmExpertCetMgr {
 
	private KmExpertCetDao  kmExpertCetDao;
 	
	public KmExpertCetDao getKmExpertCetDao() {
		return this.kmExpertCetDao;
	}
 	
	public void setKmExpertCetDao(KmExpertCetDao kmExpertCetDao) {
		this.kmExpertCetDao = kmExpertCetDao;
	}
 	
    public List getKmExpertCets() {
    	return kmExpertCetDao.getKmExpertCets();
    }
    
    public KmExpertCet getKmExpertCet(final String id) {
    	return kmExpertCetDao.getKmExpertCet(id);
    }
    
    public void saveKmExpertCet(KmExpertCet kmExpertCet) {
    	kmExpertCetDao.saveKmExpertCet(kmExpertCet);
    }
    
    public void removeKmExpertCet(final String id) {
    	kmExpertCetDao.removeKmExpertCet(id);
    }
    
    /**
     * 根据id批量删除证书
     * @param id 主键
     * add by liju @ 2009-06-20
     */
    public void removeKmExpertCets(final String[] ids){
    	kmExpertCetDao.removeKmExpertCets(ids);
    }
    
    public Map getKmExpertCets(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExpertCetDao.getKmExpertCets(curPage, pageSize, whereStr);
	}

	public Map getKmExpertCetsByUserId(final Integer curPage, final Integer pageSize,
			final String userId) {
		return kmExpertCetDao.getKmExpertCetsByUserId(curPage, pageSize, userId);
	}
	
}