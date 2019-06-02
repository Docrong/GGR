package com.boco.eoms.km.table.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.km.table.dao.KmTableColumnDao;

/**
 * <p>
 * Title:模型字段定义表
 * </p>
 * <p>
 * Description:模型字段表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
public class KmTableColumnMgrImpl implements KmTableColumnMgr {
 
	private KmTableColumnDao  kmTableColumnDao;
 	
	public KmTableColumnDao getKmTableColumnDao() {
		return this.kmTableColumnDao;
	}
 	
	public void setKmTableColumnDao(KmTableColumnDao kmTableColumnDao) {
		this.kmTableColumnDao = kmTableColumnDao;
	}
 	
    public List getKmTableColumns() {
    	return kmTableColumnDao.getKmTableColumns();
    }

    public List getKmTableColumnsByTableId(final String tableId) {
    	return kmTableColumnDao.getKmTableColumnsByTableId(tableId);
    }

    public KmTableColumn getKmTableColumn(final String id) {
    	return kmTableColumnDao.getKmTableColumn(id);
    }
 
    public void saveKmTableColumn(KmTableColumn kmTableColumn) {
    	kmTableColumnDao.saveKmTableColumn(kmTableColumn);
    }
    
    public void removeKmTableColumn(final String id) {
    	kmTableColumnDao.removeKmTableColumn(id);
    }
    
    public Map getKmTableColumns(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmTableColumnDao.getKmTableColumns(curPage, pageSize, whereStr);
	}
	
}