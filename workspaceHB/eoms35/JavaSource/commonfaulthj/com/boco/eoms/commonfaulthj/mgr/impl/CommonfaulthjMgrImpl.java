package com.boco.eoms.commonfaulthj.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commonfaulthj.model.Commonfaulthj;
import com.boco.eoms.commonfaulthj.mgr.CommonfaulthjMgr;
import com.boco.eoms.commonfaulthj.dao.CommonfaulthjDao;

/**
 * <p>
 * Title:commonfaulthj
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 * 
 * @author zhoupan
 * @version 3.5
 * 
 */
public class CommonfaulthjMgrImpl implements CommonfaulthjMgr {
 
	private CommonfaulthjDao  commonfaulthjDao;
 	
	public CommonfaulthjDao getCommonfaulthjDao() {
		return this.commonfaulthjDao;
	}
 	
	public void setCommonfaulthjDao(CommonfaulthjDao commonfaulthjDao) {
		this.commonfaulthjDao = commonfaulthjDao;
	}
 	
    public List getCommonfaulthjs() {
    	return commonfaulthjDao.getCommonfaulthjs();
    }
    
    public Commonfaulthj getCommonfaulthj(final String id) {
    	return commonfaulthjDao.getCommonfaulthj(id);
    }
    
    public void saveCommonfaulthj(Commonfaulthj commonfaulthj) {
    	commonfaulthjDao.saveCommonfaulthj(commonfaulthj);
    }
    
    public void removeCommonfaulthj(final String id) {
    	commonfaulthjDao.removeCommonfaulthj(id);
    }
    
    public Map getCommonfaulthjs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return commonfaulthjDao.getCommonfaulthjs(curPage, pageSize, whereStr);
	}
    
    public Map getMapList(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return commonfaulthjDao.getMapList(curPage, pageSize, whereStr);
	}
}