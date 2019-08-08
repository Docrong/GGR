
package com.boco.eoms.sheet.netdata.dao;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;

public interface INetDataMainDAO extends IMainDAO {
    public void DeleteEarlyEmptyMain(Object mainObject);
}

