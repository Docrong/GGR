
package com.boco.eoms.workbench.report.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.report.model.TawWorkbenchReport;
import com.boco.eoms.workbench.report.dao.ITawWorkbenchReportDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawWorkbenchReportDaoHibernate extends BaseDaoHibernate implements ITawWorkbenchReportDao {

    /**
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#getTawWorkbenchReports(com.boco.eoms.workbench.report.model.TawWorkbenchReport)
     */
    public List getTawWorkbenchReports(final TawWorkbenchReport tawWorkbenchReport) {
        return getHibernateTemplate().find("from TawWorkbenchReport");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawWorkbenchReport == null) {
            return getHibernateTemplate().find("from TawWorkbenchReport");
        } else {
            // filter on properties set in the tawWorkbenchReport
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawWorkbenchReport).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawWorkbenchReport.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#getTawWorkbenchReport(String id)
     */
    public TawWorkbenchReport getTawWorkbenchReport(final String id) {
        TawWorkbenchReport tawWorkbenchReport = (TawWorkbenchReport) getHibernateTemplate().get(TawWorkbenchReport.class, id);
        if (tawWorkbenchReport == null) {
            throw new ObjectRetrievalFailureException(TawWorkbenchReport.class, id);
        }

        return tawWorkbenchReport;
    }

    /**
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#saveTawWorkbenchReport(TawWorkbenchReport tawWorkbenchReport)
     */    
    public void saveTawWorkbenchReport(final TawWorkbenchReport tawWorkbenchReport) {
        if ((tawWorkbenchReport.getId() == null) || (tawWorkbenchReport.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchReport);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkbenchReport);
    }

    /**
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#removeTawWorkbenchReport(String id)
     */
    public void removeTawWorkbenchReport(final String id) {
        getHibernateTemplate().delete(getTawWorkbenchReport(id));
    }
    /**
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#getTawWorkbenchReports(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawWorkbenchReports(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawWorkbenchReport
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawWorkbenchReport";
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
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#getTawWorkbenchReports(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawWorkbenchReports(final Integer curPage, final Integer pageSize) {
			return this.getTawWorkbenchReports(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.workbench.report.dao.TawWorkbenchReportDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawWorkbenchReport obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
