
package com.boco.eoms.parter.baseinfo.carmgr.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr;
import com.boco.eoms.parter.baseinfo.carmgr.webapp.form.CarMgrForm;
import com.boco.eoms.parter.baseinfo.carmgr.dao.ICarMgrDao;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class CarMgrDaoHibernate extends BaseDaoHibernate implements ICarMgrDao {

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#getCarMgrs(com.boco.eoms.parter.baseinfo.carmgr.model.CarMgr)
     */
    public List getCarMgrs(final CarMgr carMgr) {
        return getHibernateTemplate().find("from CarMgr");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (carMgr == null) {
            return getHibernateTemplate().find("from CarMgr");
        } else {
            // filter on properties set in the carMgr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(carMgr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(CarMgr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#getCarMgr(String id)
     */
    public CarMgr getCarMgr(final String id) {
        CarMgr carMgr = (CarMgr) getHibernateTemplate().get(CarMgr.class, id);
        if (carMgr == null) {
            throw new ObjectRetrievalFailureException(CarMgr.class, id);
        }

        return carMgr;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#saveCarMgr(CarMgr carMgr)
     */    
    public void saveCarMgr(final CarMgr carMgr) {
        if ((carMgr.getId() == null) || (carMgr.getId().equals("")))
			getHibernateTemplate().save(carMgr);
		else
			getHibernateTemplate().saveOrUpdate(carMgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#removeCarMgr(String id)
     */
    public void removeCarMgr(final String id) {
        getHibernateTemplate().delete(getCarMgr(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#getCarMgrs(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getCarMgrs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the carMgr
       HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from CarMgr";
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
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#getCarMgrs(final Integer curPage, final Integer pageSize)
     */    
    public Map getCarMgrs(final Integer curPage, final Integer pageSize) {
			return this.getCarMgrs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.parter.baseinfo.carmgr.dao.CarMgrDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from CarMgr obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
}
