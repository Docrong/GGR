package com.boco.eoms.datum.dao;

import java.io.Serializable;
import java.util.*;

import org.hibernate.HibernateException;

public interface ITawBureaudataBasicDAO {
	public void saveObject(Object o) throws HibernateException;

	public Object getObject(Class clazz, Serializable id)
			throws HibernateException;

	public void removeObject(Class clazz, Serializable id)
			throws HibernateException;

	public List getObjectsByCondtion(Integer curPage, Integer pageSize,
			int[] aTotal, String hql, String countHql, String queryNubmer)
			throws HibernateException;

	public List loadList(String hql) throws HibernateException;

	public void saveOrUpdate(List seglist, int belongflag)
			throws HibernateException;

	public Set getAllBureaudataBasic() throws HibernateException;

	public void saveOrUpdateAll(List list) throws HibernateException;

	public Map getHeadsegMapForNew(String bureauId) throws HibernateException;

	public Map getHeadsegMapForCityAdjust(String bureauId)
			throws HibernateException;

	public List getCityAdjustBureauDataForExcel(Map headSegMap, String bureauId)
			throws HibernateException;

	public Map getHeadsegMapForHLRAdjust() throws HibernateException;

	public Map getHLRAdjustBureauDataForExcel(Map headSegMap)
			throws HibernateException;

	public List getAllTawBureaudata() throws HibernateException;

	public List getCityAdjustBureauData(String bureauId)
			throws HibernateException;

	public List getHLRAdjustBureauData() throws HibernateException;

	public List getNewBureauData(String bureauId) throws HibernateException;
	
	public void updateData(List seglist, int belongflag)throws HibernateException;
}
