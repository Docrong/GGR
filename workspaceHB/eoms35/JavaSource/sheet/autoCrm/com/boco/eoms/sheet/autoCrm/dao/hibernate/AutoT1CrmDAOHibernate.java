package com.boco.eoms.sheet.autoCrm.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.autoCrm.dao.IAutoT1CrmDAO;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;

public class AutoT1CrmDAOHibernate extends BaseSheetDaoHibernate implements
        IAutoT1CrmDAO {

    public Object getObject(Class clazz, Serializable id)
            throws HibernateException {

        return super.getObject(clazz, id);
    }

    public void removeObject(Class clazz, Serializable id)
            throws HibernateException {
        super.removeObject(clazz, id);

    }

    public void saveObject(Object o) throws HibernateException {
        super.saveObject(o);
    }

    public List getObjectsByCondtion(final Integer curPage,
                                     final Integer pageSize, int[] aTotal, final String hql,
                                     String countHql, String queryNubmer) throws HibernateException {

        aTotal[0] = ((Integer) getHibernateTemplate().find(countHql)
                .listIterator().next()).intValue();
        if (!queryNubmer.equals("number")) {
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException {
                    Query query = session.createQuery(hql);
                    if (pageSize.intValue() != -1) {
                        // 分页查询条
                        query.setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                        query.setMaxResults(pageSize.intValue());
                    }
                    return query.list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
        return new ArrayList();

    }

}
