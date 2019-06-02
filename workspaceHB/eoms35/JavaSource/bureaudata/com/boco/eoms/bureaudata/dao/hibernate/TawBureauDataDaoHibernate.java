
package com.boco.eoms.bureaudata.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.bureaudata.model.TawBureauData;
import com.boco.eoms.bureaudata.dao.ITawBureauDataDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawBureauDataDaoHibernate extends BaseDaoHibernate implements ITawBureauDataDao {

    /**
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#getTawBureauDatas(com.boco.eoms.bureaudata.model.TawBureauData)
     */
    public List getTawBureauDatas(final TawBureauData tawBureauData) {
        return getHibernateTemplate().find("from TawBureauData");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawBureauData == null) {
            return getHibernateTemplate().find("from TawBureauData");
        } else {
            // filter on properties set in the tawBureauData
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawBureauData).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawBureauData.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#getTawBureauData(String id)
     */
    public TawBureauData getTawBureauData(final String id) {
        TawBureauData tawBureauData = (TawBureauData) getHibernateTemplate().get(TawBureauData.class, id);
        if (tawBureauData == null) {
            throw new ObjectRetrievalFailureException(TawBureauData.class, id);
        }

        return tawBureauData;
    }

    /**
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#saveTawBureauData(TawBureauData tawBureauData)
     */    
    public void saveTawBureauData(final TawBureauData tawBureauData) {
        if ((tawBureauData.getId() == null) || (tawBureauData.getId().equals("")))
			getHibernateTemplate().save(tawBureauData);
		else
			getHibernateTemplate().saveOrUpdate(tawBureauData);
    }

    /**
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#removeTawBureauData(String id)
     */
    public void removeTawBureauData(final String id) {
        getHibernateTemplate().delete(getTawBureauData(id));
    }
    /**
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#getTawBureauDatas(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawBureauDatas(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawBureauData
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawBureauData";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
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
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#getTawBureauDatas(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawBureauDatas(final Integer curPage, final Integer pageSize) {
			return this.getTawBureauDatas(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.bureaudata.dao.TawBureauDataDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawBureauData obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
