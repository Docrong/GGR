package com.boco.eoms.commons.mms.mmsreport.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;
import com.boco.eoms.commons.mms.mmsreport.mgr.MmsreportMgr;
import com.boco.eoms.commons.mms.mmsreport.dao.MmsreportDao;

/**
 * <p>
 * Title:彩信报实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 18:16:20 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
public class MmsreportMgrImpl implements MmsreportMgr {
 
	private MmsreportDao  mmsreportDao;
 	
	public MmsreportDao getMmsreportDao() {
		return this.mmsreportDao;
	}
 	
	public void setMmsreportDao(MmsreportDao mmsreportDao) {
		this.mmsreportDao = mmsreportDao;
	}
 	
    public List getMmsreports() {
    	return mmsreportDao.getMmsreports();
    }
    
    public Mmsreport getMmsreport(final String id) {
    	return mmsreportDao.getMmsreport(id);
    }
    
    public void saveMmsreport(Mmsreport mmsreport) {
    	mmsreportDao.saveMmsreport(mmsreport);
    }
    
    public void removeMmsreport(final String id) {
    	mmsreportDao.removeMmsreport(id);
    }
    
    public Map getMmsreports(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return mmsreportDao.getMmsreports(curPage, pageSize, whereStr);
	}
	
}