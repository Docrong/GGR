package com.boco.eoms.commons.statistic.base.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;

/**
 * @author liuxy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface IStatJdbcDAO  {

	public List getListKpiResult(KpiDefine kpiDefine, Map param,String[] condSql,String statID);
	
	public int queryForInt(String sql);

	
	public String getKpiInfo(int id);

	/*
	 * (non-Javadoc)
	 * 根据查询条件得到工单列表
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHolds()
	 */
//	public List getMainList(final Integer curPage, final Integer pageSize,final String queryStr)
//			throws HibernateException ;
	/*
	 * (non-Javadoc)
	 * 根据查询条件得到总工单数
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHoldsCount()
	 */
//	public Integer getTotalCount(final String queryStr) throws HibernateException;
	
	/**
	 * 判断数据库表是否存在
	 * @param tableName
	 * @return
	 */
//	public boolean isTableExit(final String tableName);
	
	 /**
     * 创建数据库表
     * @param tableName
     * @param columnNameMap
     */
//    public boolean createTable(final String tableName,final List columnNameList);
    
    /**
    * 存储数据到数据库表
    * @param tableName
    * @param columnNameMap
    */
//  public boolean saveKPI2DB(final String tableName,final List columnList,final List valueList);
}
