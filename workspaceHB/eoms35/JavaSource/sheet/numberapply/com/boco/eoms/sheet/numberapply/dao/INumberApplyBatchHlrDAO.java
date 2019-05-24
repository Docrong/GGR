package com.boco.eoms.sheet.numberapply.dao;

import java.util.List;

public interface INumberApplyBatchHlrDAO {
	/**
	 * 批量插入
	 * @param columnValue
	 * @throws Exception
	 */
	public List batchInsert(final String mainid, final List columnValue) throws Exception;
	
	/**
	 * 批量更新(预处理提交时将动态表中的isVaild字段设为‘1’，表示该数据有效)
	 * @param columnValue
	 * @throws Exception
	 */
	public void batchPreUpdate( final String mainid) throws Exception;
	
	/**
	 * 批量更新（根据各种条件拼成各种sql传递过来）
	 * @param sql
	 * @throws Exception
	 */
	public void batchUpdate(List sql) throws Exception;
}
