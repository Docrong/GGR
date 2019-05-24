package com.boco.eoms.sheet.daiweiindexreduction.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiSubtractTableMgr;
import com.boco.eoms.sheet.daiweiindexreduction.dao.IDaiSubtractTableDao;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiSubtractTable;



public class DaiSubtractTableMgrImpl implements IDaiSubtractTableMgr{
	private IDaiSubtractTableDao daisubtractTableDao;
	

	public IDaiSubtractTableDao getDaiSubtractTableDao() {
		return daisubtractTableDao;
	}

	public void setDaiSubtractTableDao(IDaiSubtractTableDao daisubtractTableDao) {
		this.daisubtractTableDao = daisubtractTableDao;
	}

	
	public DaiSubtractTable getDaiSubtractTable(String id) {
		
		return daisubtractTableDao.getDaiSubtractTable(id);
	}

	public List getDaiSubtractTables() {
		// TODO Auto-generated method stub
		return daisubtractTableDao.getDaiSubtractTables();
	}

	public Map getDaiSubtractTables(Integer curPage, Integer pageSize, String whereStr) {
		// TODO Auto-generated method stub
		return daisubtractTableDao.getDaiSubtractTables(curPage, pageSize, whereStr);
	}

	public void removeDaiSubtractTable(String id) {
		daisubtractTableDao.removeDaiSubtractTable(id);
		
	}
	
	public void removeDaiSubtractTable(final String[] ids) {
	        if (null != ids) {
	            for (int i = 0; i < ids.length; i++) {
	                this.removeDaiSubtractTable(ids[i]);
	            }
	        }
	    }
	public void saveDaiSubtractTable(DaiSubtractTable daisubtractTable) {
		daisubtractTableDao.saveDaiSubtractTable(daisubtractTable);
		
	}

	public List getDaiSubtractTablesByCondition(String condition) {
		
		return daisubtractTableDao.getDaiSubtractTablesByCondition(condition);
	}

}
