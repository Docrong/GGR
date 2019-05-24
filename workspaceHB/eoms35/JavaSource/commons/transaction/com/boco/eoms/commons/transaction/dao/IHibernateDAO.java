/**
 * com.boco.eoms.commons.transaction.dao.IHibernateDAO
 */
package com.boco.eoms.commons.transaction.dao;

// java standard library
import java.util.List;

// hibernate library
import org.hibernate.HibernateException;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public interface IHibernateDAO {

    public abstract Object load(String id, Class clazz)
            throws HibernateException;

    public abstract void save(Object object) throws HibernateException;

    public abstract void saveOrUpdate(Object object) throws HibernateException;

    public abstract void update(Object object) throws HibernateException;

    public abstract void delete(Object object) throws HibernateException;

    public abstract List query(String query) throws HibernateException;

    public abstract void delete(String query) throws HibernateException;

}
