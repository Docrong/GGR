package com.boco.eoms.duty.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.AttemperLog;
import com.boco.eoms.duty.mgr.AttemperLogMgr;
import com.boco.eoms.duty.dao.AttemperLogDao;

/**
 * <p>
 * Title:网调日志记录
 * </p>
 * <p>
 * Description:网调日志记录
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperLogMgrImpl implements AttemperLogMgr {
 
	private AttemperLogDao  attemperLogDao;
 	
	public AttemperLogDao getAttemperLogDao() {
		return this.attemperLogDao;
	}
 	
	public void setAttemperLogDao(AttemperLogDao attemperLogDao) {
		this.attemperLogDao = attemperLogDao;
	}
 	
    public List getAttemperLogs() {
    	return attemperLogDao.getAttemperLogs();
    }
    
    public AttemperLog getAttemperLog(final String id) {
    	return attemperLogDao.getAttemperLog(id);
    }
    
    public void saveAttemperLog(AttemperLog attemperLog) {
    	attemperLogDao.saveAttemperLog(attemperLog);
    }
    
    public void removeAttemperLog(final String id) {
    	attemperLogDao.removeAttemperLog(id);
    }
    
    public Map getAttemperLogs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return attemperLogDao.getAttemperLogs(curPage, pageSize, whereStr);
	}
	
}