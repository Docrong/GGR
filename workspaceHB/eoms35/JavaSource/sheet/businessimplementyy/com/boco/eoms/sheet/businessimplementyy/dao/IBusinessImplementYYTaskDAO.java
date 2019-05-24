
package com.boco.eoms.sheet.businessimplementyy.dao;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ITaskDAO;

public interface IBusinessImplementYYTaskDAO extends ITaskDAO{
	public Integer getCountOfBrother(final Object taskObject, final String sheetKey, final String parentLevelId) throws HibernateException;
}

