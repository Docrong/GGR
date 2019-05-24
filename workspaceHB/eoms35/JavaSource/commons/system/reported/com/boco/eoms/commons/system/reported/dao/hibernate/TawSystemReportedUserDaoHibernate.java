package com.boco.eoms.commons.system.reported.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.reported.dao.TawSystemReportedUserDao;
import com.boco.eoms.commons.system.reported.model.TawSystemReportedUser;

public class TawSystemReportedUserDaoHibernate extends BaseDaoHibernate
		implements TawSystemReportedUserDao {

	public List getTawSystemReportedUsers(
			final TawSystemReportedUser tawSystemReportedUser) {
		return getHibernateTemplate().find("from TawSystemReportedUser");
	}

	public void saveTawSystemReportedUser(
			final TawSystemReportedUser tawSystemReportedUser) {
		if ((tawSystemReportedUser.getId() == null)
				|| (tawSystemReportedUser.getId().equals("")))
			getHibernateTemplate().save(tawSystemReportedUser);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemReportedUser);
	}

	public void removeTawSystemReportedUser(final String id) {
		getHibernateTemplate().delete(getTawSystemReportedUser(id));
	}

	public TawSystemReportedUser getTawSystemReportedUser(final String id) {
		// filter on properties set in the forums
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemReportedUser tawSystemReportedUser where tawSystemReportedUser.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawSystemReportedUser) result.iterator().next();
				} else {
					return new TawSystemReportedUser();
				}
			}
		};
		return (TawSystemReportedUser) getHibernateTemplate().execute(callback);
	}

	public List getTawSystemReportedUsersByReportId(final String reportedId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemReportedUser tawSystemReportedUser where tawSystemReportedUser.reportedId=:reportedId";
				Query query = session.createQuery(queryStr);
				query.setString("reportedId", reportedId);
				List result = query.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);	
	}
}
