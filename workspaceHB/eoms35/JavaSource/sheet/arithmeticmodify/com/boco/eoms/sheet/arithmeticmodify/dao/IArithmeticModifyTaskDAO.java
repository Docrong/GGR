
package com.boco.eoms.sheet.arithmeticmodify.dao;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ITaskDAO;

public interface IArithmeticModifyTaskDAO extends ITaskDAO {
    public Integer getCountOfBrother(final Object taskObject, final String sheetKey, final String parentLevelId) throws HibernateException;
}

