package com.boco.eoms.km.knowledge.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.knowledge.dao.KmContentsSubscribeDao;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribe;
import com.boco.eoms.km.knowledge.model.KmContentsSubscribeTable;

public class KmContentsSubscribeDaoHibernate extends BaseDaoHibernate implements KmContentsSubscribeDao,
        ID2NameDAO {

    /**
     * 知识订阅相关
     * 根据订阅人查询 订阅信息
     *
     * @param subscribeUser
     * @return该订阅人的订阅信息列表
     */
    public List listKmContentsSubscribe(final String subscribeUser) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmContentsSubscribe kmContentsSubscribe ");
                queryStr.append("where kmContentsSubscribe.subscribeUser=? ");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, subscribeUser);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 知识订阅相关
     * 根据订阅人批量删除创建人订阅信息
     *
     * @param subscribeUser
     */
    public void removeKmContentsSubscribe(final String subscribeUser) {
        //查询
        List list = listKmContentsSubscribe(subscribeUser);
        for (int i = 0; i < list.size(); i++) {
            KmContentsSubscribe kmContentsSubscribe = (KmContentsSubscribe) list.get(i);
            getHibernateTemplate().delete(kmContentsSubscribe);
        }
    }

    /**
     * 知识订阅相关
     * 保存订阅信息
     *
     * @param kmContentsSubscribe
     */
    public void saveKmContentsSubscribe(KmContentsSubscribe kmContentsSubscribe) {
        if (kmContentsSubscribe.getId() == null || "".equals(kmContentsSubscribe.getId())) {
            getHibernateTemplate().save(kmContentsSubscribe);
        } else {
            getHibernateTemplate().saveOrUpdate(kmContentsSubscribe);
        }
    }

    /**
     * 根据id查询订阅信息
     *
     * @param id
     * @return
     */
    public KmContentsSubscribe getKmContentsSubscribe(final String id) {
        KmContentsSubscribe kmContentsSubscribe = (KmContentsSubscribe) getHibernateTemplate().get(KmContentsSubscribe.class, id);
        if (kmContentsSubscribe == null) {
            kmContentsSubscribe = new KmContentsSubscribe();
        }
        return kmContentsSubscribe;
    }

    /**
     * 根据id删除订阅信息
     *
     * @param id
     */
    public void removeKmContentsSubscribeById(final String id) {
        getHibernateTemplate().delete(getKmContentsSubscribe(id));
    }

    //----------------------------根据分类去定义---------------------------

    /**
     * 根据订阅人 查询所用订阅分类的信息
     *
     * @param subscribeUser
     */
    public List listKmContentsSubscribeTable(final String subscribeUser) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer queryStr = new StringBuffer("from KmContentsSubscribeTable kmContentsSubscribeTable ");
                queryStr.append("where kmContentsSubscribeTable.subscribeUser=? ");

                Query query = session.createQuery(queryStr.toString());
                query.setString(0, subscribeUser);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 删除根据分类的订阅信息
     *
     * @param subscribeUser
     */
    public void removeKmContentsSubscribeTable(final String subscribeUser) {
        //查询
        List list = listKmContentsSubscribeTable(subscribeUser);
        for (int i = 0; i < list.size(); i++) {
            KmContentsSubscribeTable kmContentsSubscribeTable = (KmContentsSubscribeTable) list.get(i);
            getHibernateTemplate().delete(kmContentsSubscribeTable);
        }
    }

    /**
     * 保存根据分类订阅的知识信息
     *
     * @param kmContentsSubscribeTable
     */
    public void saveKmContentsSubscribeTable(KmContentsSubscribeTable kmContentsSubscribeTable) {
        if (kmContentsSubscribeTable.getId() == null || "".equals(kmContentsSubscribeTable.getId())) {
            getHibernateTemplate().save(kmContentsSubscribeTable);
        } else {
            getHibernateTemplate().saveOrUpdate(kmContentsSubscribeTable);
        }
    }

    /**
     * 根据id查询订阅信息
     *
     * @param id
     * @return
     */
    public KmContentsSubscribeTable getKmContentsSubscribeTable(final String id) {
        KmContentsSubscribeTable kmContentsSubscribeTable = (KmContentsSubscribeTable) getHibernateTemplate().get(KmContentsSubscribeTable.class, id);
        if (kmContentsSubscribeTable == null) {
            kmContentsSubscribeTable = new KmContentsSubscribeTable();
        }
        return kmContentsSubscribeTable;
    }

    /**
     * 根据id删除订阅信息
     *
     * @param id
     */
    public void removeKmContentsSubscribeTableById(final String id) {
        getHibernateTemplate().delete(getKmContentsSubscribeTable(id));
    }


    public String id2Name(String id) throws DictDAOException {
        // TODO Auto-generated method stub
        return null;
    }
}
