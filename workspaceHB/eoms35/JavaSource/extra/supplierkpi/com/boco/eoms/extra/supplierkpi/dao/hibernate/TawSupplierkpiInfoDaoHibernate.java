
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInfoDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiInfoDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiInfoDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInfoDao#getTawSupplierkpiInfos(com.boco.eoms.commons.sample.model.TawSupplierkpiInfo)
     */
    public List getTawSupplierkpiInfos(final TawSupplierkpiInfo tawSupplierkpiInfo) {
        return getHibernateTemplate().find("from TawSupplierkpiInfo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiInfo == null) {
            return getHibernateTemplate().find("from TawSupplierkpiInfo");
        } else {
            // filter on properties set in the tawSupplierkpiInfo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiInfo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiInfo.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInfoDao#getTawSupplierkpiInfo(String id)
     */
    public TawSupplierkpiInfo getTawSupplierkpiInfo(final String id) {
        TawSupplierkpiInfo tawSupplierkpiInfo = (TawSupplierkpiInfo) getHibernateTemplate().get(TawSupplierkpiInfo.class, id);
        if (tawSupplierkpiInfo == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiInfo.class, id);
        }

        return tawSupplierkpiInfo;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInfoDao#saveTawSupplierkpiInfo(TawSupplierkpiInfo tawSupplierkpiInfo)
     */    
    public void saveTawSupplierkpiInfo(final TawSupplierkpiInfo tawSupplierkpiInfo) {
        if ((tawSupplierkpiInfo.getId() == null) || (tawSupplierkpiInfo.getId().equals("")))
			getHibernateTemplate().save(tawSupplierkpiInfo);
		else
			getHibernateTemplate().saveOrUpdate(tawSupplierkpiInfo);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInfoDao#removeTawSupplierkpiInfo(String id)
     */
    public void removeTawSupplierkpiInfo(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiInfo(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiInfos(final int curPage, final int pageSize,final String whereStr) {
        // filter on properties set in the tawSupplierkpiInfo
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiInfo";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize
									* (curPage));
							query.setMaxResults(pageSize);
							List result = query.list();
							HashMap map = new HashMap();
							map.put("total", total);
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getTawSupplierkpiInfos(final int curPage, final int pageSize) {
			return this.getTawSupplierkpiInfos(curPage,pageSize,null);
		}

}
