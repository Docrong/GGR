package com.boco.eoms.cutapply.mgr.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.cutapply.model.CutApply;
import com.boco.eoms.cutapply.mgr.CutApplyMgr;
import com.boco.eoms.cutapply.dao.CutApplyDao;

/**
 * <p>
 * Title:干线割接管理
 * </p>
 * <p>
 * Description:干线割接管理
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 * 
 * @author wangsixuan
 * @version 3.5
 * 
 */
public class CutApplyMgrImpl implements CutApplyMgr {
 
	private CutApplyDao  cutApplyDao;
 	
	public CutApplyDao getCutApplyDao() {
		return this.cutApplyDao;
	}
 	
	public void setCutApplyDao(CutApplyDao cutApplyDao) {
		this.cutApplyDao = cutApplyDao;
	}
 	
    public List getCutApplys() {
    	return cutApplyDao.getCutApplys();
    }
    
    public CutApply getCutApply(final String id) {
    	return cutApplyDao.getCutApply(id);
    }
    
    public void saveCutApply(CutApply cutApply) {
    	cutApplyDao.saveCutApply(cutApply);
    }
    
    public void removeCutApply(final String id) {
    	cutApplyDao.removeCutApply(id);
    }
    
    public Map getCutApplys(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return cutApplyDao.getCutApplys(curPage, pageSize, whereStr);
	}
    
    public List getCutApplysByCondition(final String hql){
    	return cutApplyDao.getCutApplysByCondition(hql);
    }
    
    /*
	 * name2Id，即字典id转为字典名称
	 * 
	 * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
	 */
	public String name2Id(final String dictName,final String parentDictId) {
		return cutApplyDao.name2Id(dictName, parentDictId);
	}
	
}