package com.boco.eoms.system.mappingstorage.dao;

public interface MappingJdbcDao {
	
	public String dictToName(String appcode,String sheetkey);
	
	public void genTable(String tablename, String dbType);
	
	public void insertValue(String appcode, 
			String sheetKey, String rootId,String dict)throws Exception;

}
