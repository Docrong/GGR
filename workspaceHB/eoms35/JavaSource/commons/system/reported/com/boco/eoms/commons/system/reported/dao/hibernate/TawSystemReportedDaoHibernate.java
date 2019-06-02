package com.boco.eoms.commons.system.reported.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.reported.dao.TawSystemReportedDao;
import com.boco.eoms.commons.system.reported.model.TawSystemReported;
import com.boco.eoms.base.util.StaticMethod;

public class TawSystemReportedDaoHibernate extends BaseDaoHibernate implements
		TawSystemReportedDao {

	public List getTawSystemReporteds(final TawSystemReported tawSystemReported) {
		return getHibernateTemplate().find("from TawSystemReported");
	}

	public void saveTawSystemReported(final TawSystemReported tawSystemReported) {
		if ((tawSystemReported.getId() == null)
				|| (tawSystemReported.getId().equals(""))){
			tawSystemReported.setCreateTime(StaticMethod.getTimestamp());
			getHibernateTemplate().save(tawSystemReported);
		}else{
			getHibernateTemplate().saveOrUpdate(tawSystemReported);
		}
	}

	public void removeTawSystemReported(final String id) {
		getHibernateTemplate().delete(getTawSystemReported(id));
	}

	public TawSystemReported getTawSystemReported(final String id) {
		// filter on properties set in the forums
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemReported tawSystemReported where tawSystemReported.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawSystemReported) result.iterator().next();
				} else {
					return new TawSystemReported();
				}
			}
		};
		return (TawSystemReported) getHibernateTemplate().execute(callback);
	}

	public Map getTawSystemReporteds(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the TawSystemReported
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemReported tawSystemReported where ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
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

	public List getReportedUsers(final Integer modelId,	final Integer functionId, final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = " from TawSystemReported report,TawSystemReportedUser user where report.modelId="
						+ modelId
						+ " and report.functionId="
						+ functionId
						+ " and report.userId='" + userId + "' and user.reportedId = report.id";
				Query query = session.createQuery(queryStr);
				List list = query.list();
				ArrayList result = new ArrayList(); 
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					result.add(obj[1]);
				}
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
}
