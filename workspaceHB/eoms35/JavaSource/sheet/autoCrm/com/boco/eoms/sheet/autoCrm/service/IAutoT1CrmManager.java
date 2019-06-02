package com.boco.eoms.sheet.autoCrm.service;

import java.io.Serializable;
import java.util.*;

import org.hibernate.HibernateException;

public interface IAutoT1CrmManager {
	
	public void saveObject(Object o) throws HibernateException;
	
	public Object getObject(Class clazz, Serializable id) throws HibernateException;
	
	public void removeObject(Class clazz, Serializable id) throws HibernateException;
	
	public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal,Map condtion, String queryNumber) throws HibernateException;
}
