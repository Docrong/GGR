package com.boco.eoms.datum.dao;

import java.io.Serializable;
import java.util.*;

import org.hibernate.HibernateException;


public interface ITawBureaudataCityzoneDao  {
	
	public Map getAllCityIntoMapKeyZoneNum()throws HibernateException;
	public Map getAllCityIntoMapKeyCityName() throws HibernateException;
	public List loadList(String hql) throws HibernateException;
	public Object getObject(Class clazz, Serializable id) throws HibernateException;
	public void removeObject(Class clazz, Serializable id) throws HibernateException;
	public void saveOrUpdate(Object o)throws HibernateException;	
}
