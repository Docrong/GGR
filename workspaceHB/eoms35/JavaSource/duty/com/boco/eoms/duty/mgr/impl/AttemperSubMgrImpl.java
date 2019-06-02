package com.boco.eoms.duty.mgr.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.mgr.AttemperSubMgr;
import com.boco.eoms.duty.dao.AttemperSubDao;

/**
 * <p>
 * Title:网调子过程
 * </p>
 * <p>
 * Description:网调子过程
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
public class AttemperSubMgrImpl implements AttemperSubMgr {
 
	private AttemperSubDao  attemperSubDao;
 	
	public AttemperSubDao getAttemperSubDao() {
		return this.attemperSubDao;
	}
 	
	public void setAttemperSubDao(AttemperSubDao attemperSubDao) {
		this.attemperSubDao = attemperSubDao;
	}
 	
    public List getAttemperSubs() {
    	return attemperSubDao.getAttemperSubs();
    }
    
    public List getAttemperSubs(String attemperId) {
    	return attemperSubDao.getAttemperSubs(attemperId);
    }
    
    public AttemperSub getAttemperSub(final String id) {
    	return attemperSubDao.getAttemperSub(id);
    }
    
    public void saveAttemperSub(AttemperSub attemperSub) {
    	attemperSubDao.saveAttemperSub(attemperSub);
    }
    
    public void removeAttemperSub(final String id) {
    	attemperSubDao.removeAttemperSub(id);
    }
    
    public Map getAttemperSubs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return attemperSubDao.getAttemperSubs(curPage, pageSize, whereStr);
	}
    
    /**
     * 获取网调子过程数量
     * @return String 数量
     */
     public String getNum(String condition) {
    	 return attemperSubDao.getNum(condition);
     }
     
     /**
      * 批量修改子过程数据
      * @return void
      */
 	public void updateState(final String status,final String attemperId){
 		attemperSubDao.updateState(status,attemperId);
 	}
     
}