package com.boco.eoms.sheet.numberapply.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.numberapply.dao.INumberApplyHlrDAO;
import com.boco.eoms.sheet.numberapply.model.NumberApplyHlrid;

public class NumberApplyHlrDAOHibernate extends BaseDaoHibernate implements INumberApplyHlrDAO {

    /**
     * 删除
     */
    public void delNumberApplyHlrid(NumberApplyHlrid numberApplyHlrid) throws HibernateException {
        getHibernateTemplate().delete(numberApplyHlrid);
    }

    /**
     * 根据工单号获得全部HLR信息
     */
    public HashMap getAllNumberApplyHlridByMainid(final String mainid, final Integer pageSize, final Integer curPage)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                HashMap map = new HashMap();
                String hql = "from NumberApplyHlrid as  agent where (agent.mainid =  '" + mainid + "' )";
                Integer totalCount;
                String queryCountStr = "select count(*) c from (select * from  numberapply_hlrid    agent where agent.mainid =  '" + mainid + "' )";
                SQLQuery queryCount = session.createSQLQuery(queryCountStr);
                queryCount.addScalar("c", Hibernate.INTEGER);
                List list = queryCount.list();
                if (!list.isEmpty()) {
                    totalCount = (Integer) list.get(0);
                } else
                    totalCount = new Integer(0);

                Query query = session.createQuery(hql);
//			  分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                }
                map.put("total", totalCount);
                map.put("taskList", query.list());
                return map;
            }
        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据id获得单条记录
     */
    public NumberApplyHlrid getNumberApplyHlrid(final String id)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                NumberApplyHlrid numberApplyHlrid = new NumberApplyHlrid();
                String hql = "from NumberApplyHlrid as  agent where (agent.id =  '" + id + "' )";
                Query queryCount = session.createQuery(hql);
                List list = queryCount.list();
                numberApplyHlrid = (NumberApplyHlrid) list.get(0);
                return numberApplyHlrid;
            }
        };
        return (NumberApplyHlrid) getHibernateTemplate().execute(callback);
    }

    /**
     * 保存单条记录
     */
    public void saveNumberApplyHlrid(NumberApplyHlrid numberApplyHlrid)
            throws HibernateException {
        this.getHibernateTemplate().saveOrUpdate(numberApplyHlrid);

    }

}
