package com.boco.eoms.datum.dao;

import java.io.Serializable;
import java.util.*;

import org.hibernate.HibernateException;

public interface ITawBureaudataHlrDAO {
public void saveObject(Object o) throws HibernateException;
	
	public Object getObject(Class clazz, Serializable id) throws HibernateException;
	
	public void removeObject(Class clazz, Serializable id) throws HibernateException;
	
	public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal,String hql, String countHql,String queryNubmer) throws HibernateException;

	public List loadList(String hql) throws HibernateException;
	
	public void saveOrUpdate(Object o)throws HibernateException;
	
	
	
	public Map getAllHLRIntoMapKeySignalId() throws HibernateException;
}
