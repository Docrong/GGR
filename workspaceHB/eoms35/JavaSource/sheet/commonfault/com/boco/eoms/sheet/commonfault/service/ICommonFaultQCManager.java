package com.boco.eoms.sheet.commonfault.service;

import java.util.Map;

public interface ICommonFaultQCManager
{
	/**
	 * 根据sql进行分页查询
	 * @param sql
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public abstract Map selectObject(String sql, final Integer curPage, final Integer pageSize);
	/**
	 * 根据条件拼写sql
	 * @param actionMap
	 * @return
	 */
	public abstract String getSqlByCondition(Map actionMap);
	/**
	 * 查询结果集记录条数
	 * @param sql
	 * @return
	 */
	public abstract int getTotalBySql(String sql);
}