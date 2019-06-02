package com.boco.eoms.sheet.autoCrm.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.autoCrm.dao.IAutoT1CrmDAO;
import com.boco.eoms.sheet.autoCrm.service.IAutoT1CrmManager;

public class AutoT1CrmManagerImpl implements IAutoT1CrmManager {
	private IAutoT1CrmDAO autoT1CrmDAO;

	public List getObjectsByCondtion(Integer curPage, Integer pageSize,
			int[] aTotal, Map condtion, String queryNumber)
			throws HibernateException {
		String hql = " from Autot1Crm ";
		String countHql = "select count(*) from Autot1Crm ";
		String where = (String) condtion.get("where");
		hql = hql + where;
		countHql = countHql + where;
		return autoT1CrmDAO.getObjectsByCondtion(curPage, pageSize, aTotal,
				hql, countHql, queryNumber);
	}

	public IAutoT1CrmDAO getAutoT1CrmDAO() {
		return autoT1CrmDAO;
	}

	public void setAutoT1CrmDAO(IAutoT1CrmDAO autoT1CrmDAO) {
		this.autoT1CrmDAO = autoT1CrmDAO;
	}

	public Object getObject(Class clazz, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return autoT1CrmDAO.getObject(clazz, id);
	}

	public void removeObject(Class clazz, Serializable id) throws HibernateException {
		autoT1CrmDAO.removeObject(clazz, id);
		
	}

	public void saveObject(Object o) throws HibernateException {
		autoT1CrmDAO.saveObject(o);
	}

}
