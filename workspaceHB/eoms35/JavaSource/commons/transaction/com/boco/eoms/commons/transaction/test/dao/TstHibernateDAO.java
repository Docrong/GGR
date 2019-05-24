/**
 * 
 */
package com.boco.eoms.commons.transaction.test.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.boco.eoms.commons.transaction.dao.HibernateDAO;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TstHibernateDAO extends HibernateDAO {

    /**
     * @param session
     */
    public TstHibernateDAO(Session session) {
        super(session);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.transaction.dao.HibernateDAO#delete(java.lang.Object)
     */
    public void delete(Object object) throws HibernateException {
        super.delete(object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.transaction.dao.HibernateDAO#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(Object object) throws HibernateException {
        super.saveOrUpdate(object);
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.commons.transaction.dao.HibernateDAO#query(java.lang.String)
     */
    public List query(String query) throws HibernateException {
        // TODO Auto-generated method stub
        return super.query(query);
    }

}
