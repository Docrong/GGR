
package com.boco.eoms.workbench.memo.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoSendLogDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawWorkbenchMemoSendLogDaoHibernate extends BaseDaoHibernate implements TawWorkbenchMemoSendLogDao {

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoSendLogDao#getTawWorkbenchMemoSendLogs(com.boco.eoms.commons.workbench.model.TawWorkbenchMemoSendLog)
     */
    public List getTawWorkbenchMemoSendLogs(final TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog) {
        return getHibernateTemplate().find("from TawWorkbenchMemoSendLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawWorkbenchMemoSendLog == null) {
            return getHibernateTemplate().find("from TawWorkbenchMemoSendLog");
        } else {
            // filter on properties set in the tawWorkbenchMemoSendLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawWorkbenchMemoSendLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawWorkbenchMemoSendLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoSendLogDao#getTawWorkbenchMemoSendLog(String id)
     */
    public TawWorkbenchMemoSendLog getTawWorkbenchMemoSendLog(final String id) {
        TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog = (TawWorkbenchMemoSendLog) getHibernateTemplate().get(TawWorkbenchMemoSendLog.class, id);
        if (tawWorkbenchMemoSendLog == null) {
            throw new ObjectRetrievalFailureException(TawWorkbenchMemoSendLog.class, id);
        }

        return tawWorkbenchMemoSendLog;
    }

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoSendLogDao#saveTawWorkbenchMemoSendLog(TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog)
     */    
    public void saveTawWorkbenchMemoSendLog(final TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog) {
        if ((tawWorkbenchMemoSendLog.getId() == null) || (tawWorkbenchMemoSendLog.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchMemoSendLog);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkbenchMemoSendLog);
    }

    /**
     * @see com.boco.eoms.commons.workbench.dao.TawWorkbenchMemoSendLogDao#removeTawWorkbenchMemoSendLog(String id)
     */
    public void removeTawWorkbenchMemoSendLog(final String id) {
        getHibernateTemplate().delete(getTawWorkbenchMemoSendLog(id));
    }
    /**
     * ���ڷ�ҳ��ʾ
     * curPage ��ǰҳ��
     * pageSize ÿҳ��ʾ��
     * whereStr where�������䣬������"where"��ͷ,����Ϊ��
     */
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawWorkbenchMemoSendLog
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawWorkbenchMemoSendLog";
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
							map.put("total", total+"");
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    public Map getTawWorkbenchMemoSendLogs(final Integer curPage, final Integer pageSize) {
			return this.getTawWorkbenchMemoSendLogs(curPage,pageSize,null);
		}

}
