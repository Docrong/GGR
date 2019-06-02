package com.boco.eoms.sheet.branchindexreduction.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.branchindexreduction.dao.ISubtractTableDao;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTable;
import com.boco.eoms.sheet.branchindexreduction.service.ISubtractTableMgr;

public class SubtractTableMgrImpl implements ISubtractTableMgr{
	private ISubtractTableDao subtractTableDao;
	

	public ISubtractTableDao getSubtractTableDao() {
		return subtractTableDao;
	}

	public void setSubtractTableDao(ISubtractTableDao subtractTableDao) {
		this.subtractTableDao = subtractTableDao;
	}

	
	public SubtractTable getSubtractTable(String id) {
		
		return subtractTableDao.getSubtractTable(id);
	}

	public List getSubtractTables() {
		// TODO Auto-generated method stub
		return subtractTableDao.getSubtractTables();
	}

	public Map getSubtractTables(Integer curPage, Integer pageSize, String whereStr) {
		// TODO Auto-generated method stub
		return subtractTableDao.getSubtractTables(curPage, pageSize, whereStr);
	}

	public void removeSubtractTable(String id) {
		subtractTableDao.removeSubtractTable(id);
		
	}
	
	public void removeSubtractTable(final String[] ids) {
	        if (null != ids) {
	            for (int i = 0; i < ids.length; i++) {
	                this.removeSubtractTable(ids[i]);
	            }
	        }
	    }
	public void saveSubtractTable(SubtractTable subtractTable) {
		subtractTableDao.saveSubtractTable(subtractTable);
		
	}

	public List getSubtractTablesByCondition(String condition) {
		
		return subtractTableDao.getSubtractTablesByCondition(condition);
	}

}
