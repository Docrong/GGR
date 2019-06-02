package com.boco.eoms.system.mappingstorage.mgr.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.boco.eoms.system.mappingstorage.model.Mapping;
import com.boco.eoms.system.mappingstorage.mgr.MappingMgr;
import com.boco.eoms.system.mappingstorage.dao.MappingDao;

/**
 * <p>
 * Title:存储映射
 * </p>
 * <p>
 * Description:存储映射
 * </p>
 * <p>
 * Wed Apr 08 09:10:47 CST 2009
 * </p>
 * 
 * @author sam
 * @version 1.0
 * 
 */
public class MappingMgrImpl implements MappingMgr {
 
	private MappingDao  mappingDao;
	
 	
	public MappingDao getMappingDao() {
		return this.mappingDao;
	}
 	
	public void setMappingDao(MappingDao mappingDao) {
		this.mappingDao = mappingDao;
	}
 	
    public List getMappings() {
    	return mappingDao.getMappings();
    }
    
    public Mapping getMapping(final String id) {
    	return mappingDao.getMapping(id);
    }
    
    public void saveMapping(Mapping mapping) {
    	mappingDao.saveMapping(mapping);
    }
    
    public void removeMapping(final String id) {
    	mappingDao.removeMapping(id);
    }
    
    public Map getMappings(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return mappingDao.getMappings(curPage, pageSize, whereStr);
	}

	public void genTable(String tableName) throws SQLException{
		mappingDao.genTable(tableName);
	}
	
	public String  insertValue(String appcode,  
			String sheetkey, String rootId,String dictid) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return mappingDao.insertValue(appcode,sheetkey, rootId,dictid);
	}

	public String reverseDisplay(String beanid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updateMapping(Mapping mapping) {
			mappingDao.updateMapping(mapping );
				
		}

	public String dictIdToName(String appcode,String sheetkey) {
		return mappingDao.dictIdToName(appcode,sheetkey);
	}

	public String checkUnique(String tableName) {
		return mappingDao.checkUnique(tableName);
		
	}
	
	

	
	
}