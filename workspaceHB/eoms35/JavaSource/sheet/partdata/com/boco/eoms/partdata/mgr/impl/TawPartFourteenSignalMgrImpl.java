package com.boco.eoms.partdata.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawPartFourteenSignal;
import com.boco.eoms.partdata.mgr.TawPartFourteenSignalMgr;
import com.boco.eoms.partdata.dao.TawPartFourteenSignalDao;

/**
 * <p>
 * Title:14位信令点
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 * 
 * @author Josh
 * @version 3.5
 * 
 */
public class TawPartFourteenSignalMgrImpl implements TawPartFourteenSignalMgr {
 
	private TawPartFourteenSignalDao  tawPartFourteenSignalDao;
 	
	public TawPartFourteenSignalDao getTawPartFourteenSignalDao() {
		return this.tawPartFourteenSignalDao;
	}
 	
	public void setTawPartFourteenSignalDao(TawPartFourteenSignalDao tawPartFourteenSignalDao) {
		this.tawPartFourteenSignalDao = tawPartFourteenSignalDao;
	}
 	
    public List getTawPartFourteenSignals() {
    	return tawPartFourteenSignalDao.getTawPartFourteenSignals();
    }
    
    public TawPartFourteenSignal getTawPartFourteenSignal(final String id) {
    	return tawPartFourteenSignalDao.getTawPartFourteenSignal(id);
    }
    
    public void saveTawPartFourteenSignal(TawPartFourteenSignal tawPartFourteenSignal) {
    	tawPartFourteenSignalDao.saveTawPartFourteenSignal(tawPartFourteenSignal);
    }
    
    public void removeTawPartFourteenSignal(final String id) {
    	tawPartFourteenSignalDao.removeTawPartFourteenSignal(id);
    }
    
    public Map getTawPartFourteenSignals(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return tawPartFourteenSignalDao.getTawPartFourteenSignals(curPage, pageSize, whereStr);
	}
    
    public Map getExistTawPartFourteenSignals(final Integer startNum, final Integer endNum){
    	return tawPartFourteenSignalDao.getExistTawPartFourteenSignals(startNum, endNum);
    }
 
    public Map getAllTawPartFourteenSignals(final String whereStr){
    	return tawPartFourteenSignalDao.getAllTawPartFourteenSignals(whereStr);
    }
}