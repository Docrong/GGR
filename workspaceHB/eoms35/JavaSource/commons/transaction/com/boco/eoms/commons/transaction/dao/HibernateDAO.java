/**
 * com.boco.eoms.commons.transaction.dao.HibernateDAO
 */
package com.boco.eoms.commons.transaction.dao;

// java standard library
import java.util.List;

// hibernate library
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class HibernateDAO implements IHibernateDAO {

    // properties block
    private Session m_objSession;

    /**
     * minimal constructor
     * 
     * @param session
     */
    public HibernateDAO(Session session) {
        super();
        m_objSession = session;
    }

    public Object load(String id, Class clazz) throws HibernateException {
        Object obj = null;
        obj = m_objSession.load(clazz, id);
        return obj;
    }

    public void save(Object object) throws HibernateException {
        m_objSession.save(object);
    }

    public void update(Object object) throws HibernateException {
        m_objSession.update(object);
    }

    public void saveOrUpdate(Object object) throws HibernateException {
        m_objSession.saveOrUpdate(object);
    }

    public void delete(Object object) throws HibernateException {
        m_objSession.delete(object);
    }

    public List query(String query) throws HibernateException {
        //return m_objSession.find(query);
        return m_objSession.createQuery(query).list();
    }

    public void delete(String query) throws HibernateException {
        m_objSession.delete(query);
    }

}
