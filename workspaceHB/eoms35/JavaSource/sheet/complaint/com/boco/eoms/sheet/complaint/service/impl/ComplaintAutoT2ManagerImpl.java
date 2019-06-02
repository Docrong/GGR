/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.complaint.dao.IComplaintAutoT2DAO;
import com.boco.eoms.sheet.complaint.service.IComplaintAutoT2Manager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintAutoT2ManagerImpl  implements IComplaintAutoT2Manager {
	
	private IComplaintAutoT2DAO autoT2DAO;
	
	

	public IComplaintAutoT2DAO getAutoT2DAO() {
		return autoT2DAO;
	}

	public void setAutoT2DAO(IComplaintAutoT2DAO autoT2DAO) {
		this.autoT2DAO = autoT2DAO;
	}

	public Object getObject(Class clazz, Serializable id) throws HibernateException {
		return autoT2DAO.getObject(clazz, id);
	}

	public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, Map condition, String queryNumber) throws HibernateException {
		
		String hql = " from ComplaintAutoT2 ";
		String countHql = "select count(*) from ComplaintAutoT2 ";
		String where = (String)condition.get("where");
		hql = hql + where;
		countHql = countHql + where;
		return autoT2DAO.getObjectsByCondtion(curPage, pageSize, aTotal, hql, countHql,queryNumber);
	}

	public void removeObject(Class clazz, Serializable id) throws HibernateException {
		autoT2DAO.removeObject(clazz, id);
		
	}

	public void saveObject(Object o) throws HibernateException {
		autoT2DAO.saveObject(o);
		
	}

}
