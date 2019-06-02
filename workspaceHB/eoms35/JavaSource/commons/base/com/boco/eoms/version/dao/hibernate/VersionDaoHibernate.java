package com.boco.eoms.version.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.version.MoudleVersion;
import com.boco.eoms.version.dao.VersionDao;

/**
 * 版本dao hibernate实现
 * 
 * @author leo
 * 
 */
public class VersionDaoHibernate extends BaseDaoHibernate implements VersionDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.version.dao.VersionDao#addVersion(com.boco.eoms.version.MoudleVersion)
	 */
	public void addVersion(MoudleVersion moudleVersion) {
		saveObject(moudleVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.version.dao.VersionDao#getVersion(java.lang.String)
	 */
	public MoudleVersion getVersion(final String patch, final String type) {
		// filter on properties set in the thread
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from MoudleVersion m where m.patch=:patch and m.type=:type";

				Query query = session.createQuery(queryStr);
				query.setString("patch", patch);
				query.setString("type", type);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				MoudleVersion mv = (result == null || result.isEmpty()) ? new MoudleVersion()
						: (MoudleVersion) result.iterator().next();
				return mv;
			}
		};
		return (MoudleVersion) getHibernateTemplate().execute(callback);
	}
}
