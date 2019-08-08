
package com.boco.eoms.sheet.nbproducts.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.dao.hibernate.*;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.sheet.nbproducts.dao.INBProductsDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class NBProductsDaoHibernate extends BaseSheetDaoHibernate implements INBProductsDao {

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#getNBProductss(com.boco.eoms.sheet.nbproducts.model.NBProducts)
     */
    public List getNBProductss() {
        return getHibernateTemplate().find("from NBProducts as nbp where nbp.deleted = 0 order by nbp.recordTime desc");

    }

    public List getNBProductssDeleted() {
        return getHibernateTemplate().find("from NBProducts as nbp where nbp.deleted = 1 order by nbp.recordTime desc");
        
        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (nbproducts == null) {
            return getHibernateTemplate().find("from NBProducts");
        } else {
            // filter on properties set in the nbproducts
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(nbproducts).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(NBProducts.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#getNBProducts(String id)
     */
    public NBProducts getNBProducts(final String id) {
        NBProducts nbproducts = (NBProducts) getHibernateTemplate().get(NBProducts.class, id);
        if (nbproducts == null) {
            throw new ObjectRetrievalFailureException(NBProducts.class, id);
        }
        return nbproducts;
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#saveNBProducts(NBProducts nbproducts)
     */
    public void saveNBProducts(final NBProducts nbproducts) {
        if ((nbproducts.getId() == null) || (nbproducts.getId().equals("")))
            getHibernateTemplate().save(nbproducts);
        else
            getHibernateTemplate().saveOrUpdate(nbproducts);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#removeNBProducts(String id)
     */
    public void removeNBProducts(final String id) {
        NBProducts nbp = getNBProducts(id);
        nbp.setDeleted(new Integer(1));
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#removeNBProducts(String id)
     */
    public void restoreNBProducts(final String id) {
        NBProducts nbp = getNBProducts(id);
        nbp.setDeleted(new Integer(0));
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#getNBProductss(final Integer curPage, final Integer pageSize, final String whereStr)
     */
    public Map getNBProductss(final Integer curPage, final Integer pageSize, final String whereStr) {
        // filter on properties set in the nbproducts
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from NBProducts nbp";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr).iterate()
                        .next();
                Query query = session.createQuery(queryStr + " order by nbp.recordTime");
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#getNBProductss(final Integer curPage, final Integer pageSize)
     */
    public Map getNBProductss(final Integer curPage, final Integer pageSize) {
        return this.getNBProductss(curPage, pageSize, null);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.NBProductsDao#getChildList(String parentId)
     */
    public ArrayList getChildList(String parentId) {
        String hql = " from NBProducts obj where obj.parentId='"
                + parentId + "' order by obj.name";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * @see com.boco.eoms.sheet.nbproducts.dao.INBProductsDao#getNBProductssByCondition(java.lang.String)
     */
    public List getNBProductssByHql(String hql) {
        return getHibernateTemplate().find(hql);
    }
}
