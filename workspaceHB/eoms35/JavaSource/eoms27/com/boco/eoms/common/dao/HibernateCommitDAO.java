package com.boco.eoms.common.dao;

import java.util.List;
/*import net.sf.hibernate.type.Type;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Query;*/
import org.hibernate.type.Type;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.common.oo.DataObject;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class HibernateCommitDAO
    implements HibernateDAOInterface {

  public HibernateCommitDAO() {
  }

  public Object load(String id, Class clazz) throws HibernateException {
    Object obj = null;
    Session s = HibernateUtil.currentCommitSession();
    obj = s.load(clazz, id);
    return obj;
  }

  public void save(Object object) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    s.save(object);
    s.flush();
    try {
      s.connection().commit();
    }
    catch (Exception e) {
     e.getMessage();
    }
  }

  public void update(Object object) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    s.update(object);
    s.flush();
    try {
     s.connection().commit();
   }
   catch (Exception e) {
    e.getMessage();
   }
  }

  public void saveOrUpdate(Object object) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    s.saveOrUpdate(object);
    s.flush();
    try {
     s.connection().commit();
   }
   catch (Exception e) {
    e.getMessage();
   }
  }

  public void delete(DataObject object) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    s.delete(object);
    s.flush();
    try {
     s.connection().commit();
   }
   catch (Exception e) {
    e.getMessage();
   }
  }

  public List query(String query) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    return s.createQuery(query).list();
  }

  public void delete(String query) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    s.delete(query);
    s.flush();
    try {
     s.connection().commit();
   }
   catch (Exception e) {
    e.getMessage();
   }
  }

 /* public int count(String hsql, Object[] obj, Type[] typ) throws
      HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    int amount = 0;
    int sql_index = hsql.indexOf("from");
    int sql_orderby = hsql.indexOf("order by");
    String countStr = "";
    if (sql_orderby > 0) {
      countStr = "select count(*) " + hsql.substring(sql_index, sql_orderby);
    }
    else
      countStr = "select count(*) " + hsql.substring(sql_index);
    List list = s.find(countStr, obj, typ);
    if (!list.isEmpty()) {
      amount = ( (Integer) list.get(0)).intValue();
    }
    else {
      amount = 0;
    }
    return amount;

  }*/

  public int count(String hsql) throws HibernateException {
    Session s = HibernateUtil.currentCommitSession();
    int amount = 0;
    int sql_distinct = hsql.indexOf("distinct");
    int sql_index = hsql.indexOf("from");
    int sql_orderby = hsql.indexOf("order by");

    String countStr = "";
    if (sql_distinct > 0)
      countStr = "select count(" + hsql.substring(sql_distinct, sql_index)+") ";
    else
      countStr = "select count(*) ";

    if (sql_orderby > 0)
      countStr +=  hsql.substring(sql_index, sql_orderby);
    else
      countStr +=  hsql.substring(sql_index);

    Query query = s.createQuery(countStr);
    if (!query.list().isEmpty()) {
      amount = ( (Integer) query.list().get(0)).intValue();
    }
    else {
      amount = 0;
    }
    return amount;
  }

}
