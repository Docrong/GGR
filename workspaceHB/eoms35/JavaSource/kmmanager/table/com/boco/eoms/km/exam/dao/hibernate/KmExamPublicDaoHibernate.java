package com.boco.eoms.km.exam.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.exam.dao.KmExamPublicDao;
import com.boco.eoms.km.exam.model.KmExamPublic;

/**
 * <p>
 * Title:考试发布 dao的hibernate实现
 * </p>
 * <p>
 * Description:考试发布
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmExamPublicDaoHibernate extends BaseDaoHibernate implements KmExamPublicDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.exam.KmExamPublicDao#getKmExamPublics()
     */
    public List getKmExamPublics() {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmExamPublic kmExamPublic";
                Query query = session.createQuery(queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamPublicDao#getKmExamPublic(java.lang.String)
     */
    public KmExamPublic getKmExamPublic(final String id) {
        KmExamPublic kmExamPublic = (KmExamPublic) getHibernateTemplate().get(KmExamPublic.class, id);
        if (kmExamPublic == null) {
            kmExamPublic = new KmExamPublic();
        }
        return kmExamPublic;
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamPublicDao#saveKmExamPublics(com.boco.eoms.km.exam.KmExamPublic)
     */
    public void saveKmExamPublic(final KmExamPublic kmExamPublic) {
        if ((kmExamPublic.getId() == null) || (kmExamPublic.getId().equals("")))
            getHibernateTemplate().save(kmExamPublic);
        else
            getHibernateTemplate().saveOrUpdate(kmExamPublic);
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamPublicDao#removeKmExamPublics(java.lang.String)
     */
    public void removeKmExamPublic(final String id) {
        getHibernateTemplate().delete(getKmExamPublic(id));
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmExamPublic kmExamPublic = this.getKmExamPublic(id);
        if (kmExamPublic == null) {
            return "";
        }
        //TODO 请修改代码
        return null;
    }

    /**
     * @see com.boco.eoms.km.exam.KmExamPublicDao#getKmExamPublics(java.lang.Integer, java.lang.Integer, java.lang.String)
     */
    public Map getKmExamPublics(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmExamPublic kmExamPublic ");
                if (whereStr != null && whereStr.length() > 0)
                    queryStr.append(whereStr);

                StringBuffer queryCountStr = new StringBuffer("select count(kmExamPublic.id) ");
                queryCountStr.append(queryStr);

                int total = ((Integer) session.createQuery(queryCountStr.toString())
                        .iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    Query query = session.createQuery(queryStr.toString());
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                    result = query.list();
                }

                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

}