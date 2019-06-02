package com.boco.eoms.system.mappingstorage.mgr.impl;

import com.boco.eoms.system.mappingstorage.dao.MappingJdbcDao;
import com.boco.eoms.system.mappingstorage.mgr.MappingJdbcMgr;

public class MappingJdbcMgrImpl implements MappingJdbcMgr {
	
	private MappingJdbcDao mappingJdbcDao;

     public String dictToName(String appcode,String sheetkey) {
		return mappingJdbcDao.dictToName(appcode,sheetkey);
	   }
     
     
     public void genTable(String tablename, String dbType) {
    	 mappingJdbcDao.genTable(tablename,dbType);
 	}
     
     
     public void insertValue(String appcode, String sheetKey, String rootId,
 			String dict) throws Exception{
    	 mappingJdbcDao.insertValue(appcode, sheetKey, rootId, dict);
 	}


	public MappingJdbcDao getMappingJdbcDao() {
		return mappingJdbcDao;
	}


	public void setMappingJdbcDao(MappingJdbcDao mappingJdbcDao) {
		this.mappingJdbcDao = mappingJdbcDao;
	}
	

}
