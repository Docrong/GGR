package com.boco.eoms.sheet.base.dao;

import java.util.List;

public interface IDownLoadSheetAccessiorsDAO {
	
	/**
	 * 根据sql语句获取工单的附件列表
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List getSheetAccessoriesList(String sql) throws Exception;
	/**
	 * 根据sql语句update相关的task对象
	 * @param sql
	 * @return
	 * @throws Exception
	 */
    public void updateTasks(String sql)throws Exception;    
    
    /**
	 * 根据sql语句update相关的task对象
     * @param procedureSql
	 * @return
	 * @throws Exception
	 */
    public boolean executeProcedure(String procedureSql)throws Exception;   
    
       
}
