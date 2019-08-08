package com.boco.eoms.common.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.oo.DataObject;
import com.boco.eoms.db.hibernate.HibernateUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class HibernateDAO
        implements HibernateDAOInterface {

    public HibernateDAO() {
    }

    public Object load(String id, Class clazz) throws HibernateException {
        Object obj = null;
        Session s = HibernateUtil.currentSession();

        obj = s.load(clazz, id);
        return obj;
    }

    public void save(Object object) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        HibernateUtil.currentTransaction();
        s.save(object);
        HibernateUtil.commitTransaction();


    }

    public void saveTemp(Object object) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        s.save(object);
    }

    public void update(Object object) throws HibernateException {
        Session s = HibernateUtil.currentSession();

        HibernateUtil.currentTransaction();
        s.update(object);
        HibernateUtil.commitTransaction();


    }

    public void saveOrUpdate(Object object) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        HibernateUtil.currentTransaction();
        s.saveOrUpdate(object);
        HibernateUtil.commitTransaction();

    }

    public void delete(DataObject object) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        HibernateUtil.currentTransaction();
        s.delete(object);
        HibernateUtil.commitTransaction();


    }

    public List query(String query) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        s.clear();
        return s.createQuery(query).list();
    }

    public void delete(String query) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        HibernateUtil.currentTransaction();
        s.delete(query);

        HibernateUtil.commitTransaction();
    }


    public int count(String hsql) throws HibernateException {
        Session s = HibernateUtil.currentSession();
        int amount = 0;
        int sql_distinct = hsql.indexOf("distinct");
        int sql_index = hsql.indexOf("from");
        int sql_orderby = hsql.indexOf("order by");

        String countStr = "";
        if (sql_distinct > 0)
            countStr = "select count(" + hsql.substring(sql_distinct, sql_index) + ") ";
        else
            countStr = "select count(*) ";

        if (sql_orderby > 0)
            countStr += hsql.substring(sql_index, sql_orderby);
        else
            countStr += hsql.substring(sql_index);

        Query query = s.createQuery(countStr);
        if (!query.list().isEmpty()) {
            amount = ((Integer) query.list().get(0)).intValue();
        } else {
            amount = 0;
        }
        return amount;
    }

    //需要及时加载的
    public Object get(String id, Class clazz) throws HibernateException {
        Object obj = null;
        Session s = HibernateUtil.currentSession();
        s.clear();
        obj = s.load(clazz, id);
        return obj;
    }

}