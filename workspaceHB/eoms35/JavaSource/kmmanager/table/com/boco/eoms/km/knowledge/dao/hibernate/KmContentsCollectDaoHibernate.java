package com.boco.eoms.km.knowledge.dao.hibernate;

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
import com.boco.eoms.km.knowledge.dao.KmContentsCollectDao;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.knowledge.model.KmContentsCollect;


public class KmContentsCollectDaoHibernate extends BaseDaoHibernate implements KmContentsCollectDao,
        ID2NameDAO {

    /**
     * 根据id删除收藏信息
     *
     * @param id
     */
    public void removeKmContentsCollect(final String contentId) {
        getHibernateTemplate().delete(getKmContentsCollect(contentId));
    }

    /**
     * 根据知识id查询
     *
     * @param
     * @return
     */
    public KmContentsCollect getKmContentsCollect(final String contentId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select collect from KmContentsCollect collect, KmContents contents where collect.contentId=contents.contentId and contents.contentStatus!=4 and collect.contentId=:contentId";
                Query query = session.createQuery(queryStr);
                query.setString("contentId", contentId);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmContentsCollect) result.iterator().next();
                } else {
                    return new KmContentsCollect();
                }
            }
        };
        return (KmContentsCollect) getHibernateTemplate().execute(callback);
    }

    /**
     * 查询当前人的所收藏的知识集合
     *
     * @param subscribeUser
     * @return
     */
    public List getKmContentsCollectList(final String collectUser) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select collect from KmContentsCollect collect, KmContents contents where collect.contentId=contents.contentId and contents.contentStatus!=4 and collect.collectUser=:collectUser";
                Query query = session.createQuery(queryStr);
                query.setString("collectUser", collectUser);
                query.setFirstResult(0);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public KmContentsCollect getKmContentsCollect(String collectUser,
                                                  String contentId) {

        return null;
    }

    /**
     * 分页查询收藏信息
     *
     * @param curPage
     * @param pageSize
     * @param whereStr
     * @return
     */
    public Map getKmContentsCollect(final Integer curPage, final Integer pageSize,
                                    final String whereStr) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmContentsCollect kmContentsCollect";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();

                List result = new ArrayList();
                if (total > 0) {
                    Query query = session.createQuery(queryStr);
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

    /**
     * 保存收藏信息
     */
    public void savaKmContentsCollect(KmContentsCollect kmContentsCollect) {
        getHibernateTemplate().save(kmContentsCollect);
    }

    public String id2Name(String id) throws DictDAOException {
        // TODO Auto-generated method stub
        return null;
    }
}