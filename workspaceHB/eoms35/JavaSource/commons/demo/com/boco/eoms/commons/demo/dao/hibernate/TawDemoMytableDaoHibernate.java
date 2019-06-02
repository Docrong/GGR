
package com.boco.eoms.commons.demo.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.demo.model.TawDemoMytable;
import com.boco.eoms.commons.demo.dao.TawDemoMytableDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawDemoMytableDaoHibernate extends BaseDaoHibernate implements TawDemoMytableDao {
	
	public Map getTawDemoMytables(final Integer curPage, final Integer pageSize) {
		return this.getTawDemoMytables(curPage,pageSize,null);
	}

    /**
     * 用于分页显示
     * curPage 当前页数
     * pageSize 每页显示数
     * whereStr where的条件语句，必须以"where"开头,可以为空
     */
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawDemoMytable
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	String queryStr = "from TawDemoMytable ";            	
            	if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr).iterate()
						.next()).intValue();
				
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue()
						* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.commons.demo.dao.TawDemoMytableDao#getTawDemoMytable(Integer id)
     */
    public TawDemoMytable getTawDemoMytable(final Integer id) {
        TawDemoMytable tawDemoMytable = (TawDemoMytable) getHibernateTemplate().get(TawDemoMytable.class, id);
        if (tawDemoMytable == null) {
        	BocoLog.warn(this,"uh oh, tawDemoMytable with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(TawDemoMytable.class, id);
        }

        return tawDemoMytable;
    }

    /**
     * @see com.boco.eoms.commons.demo.dao.TawDemoMytableDao#saveTawDemoMytable(TawDemoMytable tawDemoMytable)
     */    
    public void saveTawDemoMytable(final TawDemoMytable tawDemoMytable) {
        if ((tawDemoMytable.getId() == null) || (tawDemoMytable.getId().equals("")))
			getHibernateTemplate().save(tawDemoMytable);
		else
			getHibernateTemplate().saveOrUpdate(tawDemoMytable);
    }

    /**
     * @see com.boco.eoms.commons.demo.dao.TawDemoMytableDao#removeTawDemoMytable(Integer id)
     */
    public void removeTawDemoMytable(final Integer id) {
        getHibernateTemplate().delete(getTawDemoMytable(id));
    }

	public List getTawDemoMytables(TawDemoMytable tawDemoMytable) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from TawDemoMytable");
	}
}
