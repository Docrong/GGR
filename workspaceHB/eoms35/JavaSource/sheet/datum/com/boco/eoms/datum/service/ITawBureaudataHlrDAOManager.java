/*
 * Created on 2007-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;


/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ITawBureaudataHlrDAOManager {
	
	public void saveObject(Object o) throws HibernateException;
	
	public Object getObject(Class clazz, Serializable id) throws HibernateException;
	
	public void removeObject(Class clazz, Serializable id) throws HibernateException;
	
	public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal,Map condtion, String queryNumber) throws HibernateException;
	public List getBureaudatedept()throws HibernateException;
	public void saveOrUpdate(Object o)throws HibernateException;
	public List getHlrList(Object o) throws HibernateException;
	public Object getObject(String id)throws HibernateException;
	public Map getAllHLRIntoMapKeySignalId() throws HibernateException;
	public Object getHlrsignalidObject(String hlrsignalid)throws HibernateException;
}
