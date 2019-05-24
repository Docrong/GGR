
package com.boco.eoms.sheet.safetyproblem.dao.hibernate;

import java.util.HashMap;
//import java.util.List;
import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
//import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.safetyproblem.dao.ISafetyProblemMainDAO;
//import com.boco.eoms.sheet.safetyproblem.model.SafetyProblemMain;

public class SafetyProblemMainDAOHibernate extends MainDAO implements ISafetyProblemMainDAO {

	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	public Integer getDiffDeptStartCount(String s, Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap getDiffDeptStartList(String s, Integer integer, Integer integer1, Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getSameDeptStartCount(String s, Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap getSameDeptStartList(String s, Integer integer, Integer integer1, Object obj) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

}

