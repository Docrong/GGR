package com.boco.eoms.sheet.kpi.dao;

import java.util.*;

import org.hibernate.HibernateException;

public interface IKpiBaseDAO {
	/***
	 * 解析XML配置文件获取列名称(中,英),sql
	 * @return
	 * @throws HibernateException
	 */
	public List getXMLList(String xmlName) throws HibernateException;
	/**
	 * 获取SQl查询结果集
	 * @return
	 * @throws HibernateException
	 */
	public List getQuerySheetByCondition(final String hsql, 
			final Integer curPage, final Integer pageSize, int[] aTotal, String queryType);
	
	
	public List getReportByDept(final String hsql,String filename);
	
}
