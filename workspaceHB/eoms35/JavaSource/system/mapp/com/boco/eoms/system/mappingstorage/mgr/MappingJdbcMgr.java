package com.boco.eoms.system.mappingstorage.mgr;

public interface MappingJdbcMgr {
	
	//通过dictiid得到所对应字符串
	public String dictToName(String appcode,String sheetkey);
	
	//通过配置文件中的表名生成新表
	
	public void genTable(String tablename, String dbType);
	
	//将前台得到的数据插入到新表
	public void insertValue(String appcode, 
			String sheetKey, String rootId,String dict)throws Exception;
	

}
