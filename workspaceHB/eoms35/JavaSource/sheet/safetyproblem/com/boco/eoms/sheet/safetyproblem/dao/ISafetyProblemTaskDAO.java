package com.boco.eoms.sheet.safetyproblem.dao;

import java.util.*;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ITaskDAO;

public interface ISafetyProblemTaskDAO extends ITaskDAO {
	public abstract HashMap getSplitAndReplyList(Map map, String s, String s1,
			String s2, Integer integer, Integer integer1)
			throws HibernateException;

	public abstract HashMap getSplitNotReplyList(Map map, String s, String s1,
			String s2, Integer integer, Integer integer1)
			throws HibernateException;
	/**
	 * 根据count SQL语句查询
	 * @param queryCountStr
	 * @return
	 * @throws HibernateException
	 */
	public Integer getCountTaskBySQL(final String queryCountStr) throws HibernateException;
	
	
	/**
	 * 根据SQL语句查询任务信息, 并进行分页处理
	 * @param queryStr
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */	
	public HashMap getTaskListBySQL(final String queryStr,  final Integer curPage,final Integer pageSize) throws HibernateException;

}
