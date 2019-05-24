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
import java.util.Set;

import org.hibernate.HibernateException;

/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface ITawBureaudataBasicDAOManager {

	public void saveObject(Object o) throws HibernateException;

	public Object getObject(Class clazz, Serializable id)
			throws HibernateException;

	public void removeObject(Class clazz, Serializable id)
			throws HibernateException;

	public Set getAllBureaudataBasic() throws HibernateException;

	public List getObjectsByCondtion(Integer curPage, Integer pageSize,
			int[] aTotal, Map condtion, String queryNumber)
			throws HibernateException;

	public List getDepBureaudateInfo(String deptid, int belongflag)
			throws HibernateException;

	public List getBelongSegment(String deptid) throws HibernateException;

	public List getRelatedBelongsegment(String sheetId,String type)
			throws HibernateException;

	public void updateBase(List list, int belongflag) throws HibernateException;

	public List getDepBureaudateInfoSheet(String sheetId)
			throws HibernateException;

	public List getBureaudataHlrAreaList(String where)
			throws HibernateException;

	public void saveOrUpdateAll(List list) throws HibernateException;

	public Map getHeadsegMapForNew(String bureauId) throws HibernateException;

	public List getNewBureauDataForExcel(Map headSegMap, String bureauId)
			throws HibernateException;

	public Map getHeadsegMapForCityAdjust(String bureauId)
			throws HibernateException;

	public List getCityAdjustBureauDataForExcel(Map headSegMap, String bureauId)
			throws HibernateException;

	public Map getHeadsegMapForHLRAdjust() throws HibernateException;

	public Map getHLRAdjustBureauDataForExcel(Map headSegMap)
			throws HibernateException;

	public List getAllTawBureaudata() throws HibernateException;
	
	
	public void updatenew(String sheetId,String type)throws HibernateException;
}
