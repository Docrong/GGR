
package com.boco.eoms.commons.sample.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.sample.model.SampleTable;
import com.boco.eoms.commons.sample.dao.SampleTableDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SampleTableDaoHibernate extends BaseDaoHibernate implements SampleTableDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.SampleTableDao#getSampleTables(com.boco.eoms.commons.sample.model.SampleTable)
     */
    public List getSampleTables(final SampleTable sampleTable) {
        return getHibernateTemplate().find("from SampleTable");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (sampleTable == null) {
            return getHibernateTemplate().find("from SampleTable");
        } else {
            // filter on properties set in the sampleTable
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(sampleTable).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(SampleTable.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.SampleTableDao#getSampleTable(String id)
     */
    public SampleTable getSampleTable(final String id) {
        SampleTable sampleTable = (SampleTable) getHibernateTemplate().get(SampleTable.class, id);
        if (sampleTable == null) {
            throw new ObjectRetrievalFailureException(SampleTable.class, id);
        }

        return sampleTable;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.SampleTableDao#saveSampleTable(SampleTable sampleTable)
     */    
    public void saveSampleTable(final SampleTable sampleTable) {
        if ((sampleTable.getId() == null) || (sampleTable.getId().equals("")))
			getHibernateTemplate().save(sampleTable);
		else
			getHibernateTemplate().saveOrUpdate(sampleTable);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.SampleTableDao#removeSampleTable(String id)
     */
    public void removeSampleTable(final String id) {
        getHibernateTemplate().delete(getSampleTable(id));
    }

    public Map getSampleTables(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the sampleTable
        HibernateCallback callback = new HibernateCallback() {
        	
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from SampleTable";
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
    public Map getSampleTables(final Integer curPage, final Integer pageSize) {
			return this.getSampleTables(curPage,pageSize,null);
		}

}
