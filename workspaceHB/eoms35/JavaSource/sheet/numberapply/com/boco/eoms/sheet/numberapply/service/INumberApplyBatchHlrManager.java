package com.boco.eoms.sheet.numberapply.service;

import java.util.List;


public interface INumberApplyBatchHlrManager {
	
	/**
	 * 批量增加
	 * @param mainid
	 * @param columnValue
	 * @throws Exception
	 */
	public List batchInsert(final String mainid, final List columnValue) throws Exception;
	
	/**
	 * 批量更新
	 * @param columnValue
	 * @throws Exception
	 */
	public void batchPreUpdate(final String mainid) throws Exception;
	
	/**
	 * 批量更新（根据各种条件拼成各种sql传递过来）
	 * @param sql
	 * @throws Exception
	 */
	public void batchUpdate(List sql) throws Exception;
	
	
	
}
