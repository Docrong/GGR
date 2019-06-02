package com.boco.eoms.km.expert.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertCom;
import com.boco.eoms.km.expert.mgr.KmExpertComMgr;
import com.boco.eoms.km.expert.dao.KmExpertComDao;

/**
 * <p>
 * Title:技术交流竞赛表彰
 * </p>
 * <p>
 * Description:技术交流竞赛表彰
 * </p>
 * <p>
 * Mon Jun 15 18:07:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertComMgrImpl implements KmExpertComMgr {
 
	private KmExpertComDao  kmExpertComDao;
 	
	public KmExpertComDao getKmExpertComDao() {
		return this.kmExpertComDao;
	}
 	
	public void setKmExpertComDao(KmExpertComDao kmExpertComDao) {
		this.kmExpertComDao = kmExpertComDao;
	}
 	
    public List getKmExpertComs() {
    	return kmExpertComDao.getKmExpertComs();
    }
    
    public KmExpertCom getKmExpertCom(final String id) {
    	return kmExpertComDao.getKmExpertCom(id);
    }
    
    public void saveKmExpertCom(KmExpertCom kmExpertCom) {
    	kmExpertComDao.saveKmExpertCom(kmExpertCom);
    }
    
    public void removeKmExpertCom(final String id) {
    	kmExpertComDao.removeKmExpertCom(id);
    }

    /**
     * 根据id批量删除技术交流竞赛表彰
     * @param id 主键
     * 
     */
     public void removeKmExpertComs(final String[] ids){
    	 kmExpertComDao.removeKmExpertComs(ids);
     }
    
    public Map getKmExpertComs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExpertComDao.getKmExpertComs(curPage, pageSize, whereStr);
	}

	public Map getKmExpertComsByUserId(final Integer curPage, final Integer pageSize,
			final String userId) {
		return kmExpertComDao.getKmExpertComsByUserId(curPage, pageSize, userId);
	}
	
}