
package com.boco.eoms.sheet.businessimplement.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ITaskDAO;

public interface IBusinessImplementTaskDAO extends ITaskDAO{
	public Integer getCountOfBrother(final Object taskObject, final String sheetKey, final String parentLevelId) throws HibernateException;
	
	/**
     * 根据查询条件查询任务信息, 并进行分页处理
     * @param hsql 查询语句
     * @param curPage 分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */	
    public abstract HashMap getTaskListByCondition(String hsql, 
    		          final Integer curPage,final Integer pageSize)
    		          throws HibernateException;
    
    public void insertsql(HashMap map) throws  HibernateException;
}

